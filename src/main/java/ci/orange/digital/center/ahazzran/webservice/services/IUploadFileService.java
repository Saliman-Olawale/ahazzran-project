package ci.orange.digital.center.ahazzran.webservice.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {

    String uploadFile(MultipartFile file);
    byte[] readFile(String fileName) throws IOException;
    String getContentType(String fileName) ;
}
