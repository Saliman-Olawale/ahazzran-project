package ci.orange.digital.center.ahazzran.webservice.services;

import org.springframework.web.multipart.MultipartFile;

public interface IRepeterService {

    boolean compareVoice(MultipartFile inputVoice, String storedVoiceUrl) throws Exception;
    
}
