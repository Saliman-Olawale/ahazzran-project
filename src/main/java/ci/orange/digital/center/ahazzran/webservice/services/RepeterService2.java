package ci.orange.digital.center.ahazzran.webservice.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
import be.tarsos.dsp.mfcc.MFCC;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;

import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sound.sampled.UnsupportedAudioFileException;

@Service
public class RepeterService2 {

    Path uploadDirectory = Paths.get(System.getProperty("user.home"), "Desktop", "equipe_5_ahazzran_web_service", "web-service", "uploads");
    private final String uploadDir = uploadDirectory.resolve("audios_ahazzran").toString() + File.separator;

    public boolean compareVoice(MultipartFile inputVoice, String storedVoiceUrl) throws Exception {

        File inputWavFile = convertToWav(inputVoice);
        String fileName = storedVoiceUrl.substring(storedVoiceUrl.lastIndexOf("/") + 1);
        File storedFile = new File(uploadDir + fileName);
        File storedWavFile = convertToWav(storedFile);

        float[][] inputMfcc = extractMFCC(inputWavFile);
        float[][] storedMfcc = extractMFCC(storedWavFile);

        double[] inputPitch = extractPitch(inputWavFile);
        double[] storedPitch = extractPitch(storedWavFile);

        double mfccDistance = calculateMFCCDistance(inputMfcc, storedMfcc);
        double pitchCorrelation = calculatePitchCorrelation(inputPitch, storedPitch);
        double dtwDistance = calculateDTWDistance(inputMfcc, storedMfcc);

        // Combine les trois méthodes (vous pouvez ajuster les poids selon vos besoins)
        double combinedScore = 0.5 * mfccDistance + 0.3 * (1 - pitchCorrelation) + 0.2 * dtwDistance;

        double threshold = 0.3; // Ajustez ce seuil selon vos tests

        inputWavFile.delete();
        if (!storedFile.equals(storedWavFile)) {
            storedWavFile.delete();
        }

        return combinedScore < threshold;

    }

    // Méthodes existantes: convertToWav, extractMFCC

    private double[] extractPitch(File wavFile) throws IOException, UnsupportedAudioFileException {
        AudioDispatcher dispatcher = AudioDispatcherFactory.fromFile(wavFile, 1024, 512);
        final List<Float> pitchValues = new ArrayList<>();

        PitchDetectionHandler pdh = new PitchDetectionHandler() {
            @Override
            public void handlePitch(PitchDetectionResult result, AudioEvent e) {
                if (result.getPitch() != -1) {
                    pitchValues.add(result.getPitch());
                }
            }
        };

        dispatcher.addAudioProcessor(new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.YIN, 44100, 1024, pdh));
        dispatcher.run();

        double[] pitchArray = new double[pitchValues.size()];
        for (int i = 0; i < pitchValues.size(); i++) {
            pitchArray[i] = pitchValues.get(i);
        }
        return pitchArray;
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
       attrs.setOutputFormat("wav"); // Changez setInputFormat à setOutputFormat
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
            }});
       
            dispatcher.run();
            
            return mfccList.toArray(new float[0][]);
            
        }

    private double calculateMFCCDistance(float[][] mfcc1, float[][] mfcc2) {
        double distance = 0;
        int len = Math.min(mfcc1.length, mfcc2.length);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < mfcc1[i].length; j++) {
                distance += Math.pow(mfcc1[i][j] - mfcc2[i][j], 2);
            }
        }
        return Math.sqrt(distance) / len;
    }

    private double calculatePitchCorrelation(double[] inputPitch, double[] storedPitch) {
        int minLength = Math.min(inputPitch.length, storedPitch.length);
        
        // Tronquer les deux vecteurs à la même longueur
        double[] truncatedPitch1 = Arrays.copyOf(inputPitch, minLength);
        double[] truncatedPitch2 = Arrays.copyOf(storedPitch, minLength);
        
        // Convertir en double[] pour PearsonsCorrelation
        double[] doublePitch1 = new double[minLength];
        double[] doublePitch2 = new double[minLength];
        
        for (int i = 0; i < minLength; i++) {
            doublePitch1[i] = truncatedPitch1[i];
            doublePitch2[i] = truncatedPitch2[i];
        }
        
        return new PearsonsCorrelation().correlation(doublePitch1, doublePitch2);
    }

    private double calculateDTWDistance(float[][] mfcc1, float[][] mfcc2) {
        int n = mfcc1.length;
        int m = mfcc2.length;
        double[][] dtw = new double[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dtw[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        dtw[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                double cost = euclideanDistance(mfcc1[i-1], mfcc2[j-1]);
                dtw[i][j] = cost + Math.min(dtw[i-1][j], Math.min(dtw[i][j-1], dtw[i-1][j-1]));
            }
        }

        return dtw[n][m];
    }

    private double euclideanDistance(float[] vec1, float[] vec2) {
        int minLength = Math.min(vec1.length, vec2.length);
        double sum = 0;
        for (int i = 0; i < minLength; i++) {
            sum += Math.pow(vec1[i] - vec2[i], 2);
        }
        return Math.sqrt(sum);
    }

    
} 
