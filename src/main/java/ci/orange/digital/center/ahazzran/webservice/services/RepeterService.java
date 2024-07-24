package ci.orange.digital.center.ahazzran.webservice.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
import be.tarsos.dsp.mfcc.MFCC;

import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.UnsupportedAudioFileException;


@Service
public class RepeterService implements IRepeterService{

    Path uploadDirectory = Paths.get(System.getProperty("user.home"), "Desktop", "equipe_5_ahazzran_web_service", "web-service", "uploads");
    private final String uploadDir = uploadDirectory.resolve("audios_ahazzran").toString() + File.separator;
    
    @Override
    public boolean compareVoice(MultipartFile inputVoice, String storedVoiceUrl) throws Exception {
        
        // Convert input voice to WAV
        File inputWavFile = convertToWav(inputVoice);

        // Convert stored voice to WAV
        String fileName = storedVoiceUrl.substring(storedVoiceUrl.lastIndexOf("/") + 1);
        File storedFile = new File(uploadDir + fileName);
        File storedWavFile = convertToWav(storedFile);

        // Extract MFCC features
        float[][] inputMfcc = extractMFCC(inputWavFile);
        float[][] storedMfcc = extractMFCC(storedWavFile);

        // Extract delta and delta-delta features
        float[][] inputDelta = calculateDelta(inputMfcc);
        float[][] storedDelta = calculateDelta(storedMfcc);
        float[][] inputDeltaDelta = calculateDelta(inputDelta);
        float[][] storedDeltaDelta = calculateDelta(storedDelta);

        // Calculate distances
        float mfccDistance = calculateDistance(inputMfcc, storedMfcc);
        float deltaDistance = calculateDistance(inputDelta, storedDelta);
        float deltaDeltaDistance = calculateDistance(inputDeltaDelta, storedDeltaDelta);

        // Combine distances
        float combinedDistance = mfccDistance + deltaDistance + deltaDeltaDistance;

        // Calculate correlation
        float correlation = calculateCorrelation(inputMfcc, storedMfcc);

        // Threshold for comparison (you may need to tune this value)
        float threshold = 30; // Adjusted threshold

        // Clean up temporary files
        inputWavFile.delete();
        if (!storedFile.equals(storedWavFile)) {
            storedWavFile.delete();
        }

        return combinedDistance < threshold && correlation > 0.8; // Correlation threshold
    }

    private File convertToWav(MultipartFile file) throws Exception {
        File tempFile = File.createTempFile("temp_", file.getOriginalFilename());
        file.transferTo(tempFile);
        return convertToWav(tempFile);
    }

    private File convertToWav(File inputFile) throws Exception {
        File outputFile = File.createTempFile("converted_", ".wav");
        
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("pcm_s16le");
        audio.setSamplingRate(44100);
        audio.setChannels(2);
    
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setOutputFormat("wav");
        attrs.setAudioAttributes(audio);
    
        Encoder encoder = new Encoder();
        encoder.encode(new MultimediaObject(inputFile), outputFile, attrs);
    
        return outputFile;
    }

    private float[][] extractMFCC(File wavFile) throws IOException, UnsupportedAudioFileException {

        AudioDispatcher dispatcher = AudioDispatcherFactory.fromFile(wavFile, 1024, 512);
        int sampleRate = 44100;
        int numCoefficients = 20;

        MFCC mfcc = new MFCC(1024, sampleRate, numCoefficients, 20, 50, 3000);

        List<float[]> mfccList = new ArrayList<>();

        dispatcher.addAudioProcessor(new AudioProcessor() {
            @Override
            public boolean process(AudioEvent audioEvent) {
                mfcc.process(audioEvent);
                mfccList.add(mfcc.getMFCC());
                return true;
            }

            @Override
            public void processingFinished() {
                // No action needed
            }
        });

        dispatcher.run();

        return mfccList.toArray(new float[0][]);
    }

    private float calculateDistance(float[][] mfcc1, float[][] mfcc2) {
        float distance = 0;
        int len = Math.min(mfcc1.length, mfcc2.length);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < mfcc1[i].length; j++) {
                distance += Math.pow(mfcc1[i][j] - mfcc2[i][j], 2);
            }
        }
        return (float) Math.sqrt(distance);
    }

    private float[][] calculateDelta(float[][] mfcc) {
        int n = mfcc.length;
        int numCoefficients = mfcc[0].length;
        float[][] delta = new float[n][numCoefficients];

        for (int i = 2; i < n - 2; i++) {
            for (int j = 0; j < numCoefficients; j++) {
                delta[i][j] = (mfcc[i + 2][j] - mfcc[i - 2][j] + 2 * (mfcc[i + 1][j] - mfcc[i - 1][j])) / 10;
            }
        }

        return delta;
    }

    private float calculateCorrelation(float[][] mfcc1, float[][] mfcc2) {
        float mean1 = 0, mean2 = 0;
        int len = Math.min(mfcc1.length, mfcc2.length);

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < mfcc1[i].length; j++) {
                mean1 += mfcc1[i][j];
                mean2 += mfcc2[i][j];
            }
        }

        mean1 /= (len * mfcc1[0].length);
        mean2 /= (len * mfcc2[0].length);

        float num = 0, den1 = 0, den2 = 0;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < mfcc1[i].length; j++) {
                num += (mfcc1[i][j] - mean1) * (mfcc2[i][j] - mean2);
                den1 += Math.pow(mfcc1[i][j] - mean1, 2);
                den2 += Math.pow(mfcc2[i][j] - mean2, 2);
            }
        }

        return (float) (num / Math.sqrt(den1 * den2));
    }
}

