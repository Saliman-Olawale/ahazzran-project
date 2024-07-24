package ci.orange.digital.center.ahazzran.webservice.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@SuppressWarnings("null")
public class UploadFileService implements IUploadFileService{

    @Value("${server.port}")
    private int port;

    Path uploadDirectory = Paths.get( System.getProperty("user.home"), "Desktop", "equipe_5_ahazzran_web_service", "web-service","uploads");

    private final String upload = uploadDirectory.toString();

    private final String uploadDir = upload + File.separator + "images_ahazzran" + File.separator ;

    private final String uploadDirAudio = upload + File.separator + "audios_ahazzran" + File.separator;


    @Override
    public String uploadFile(MultipartFile file) {

        if (file.isEmpty()) {
            return ("fichier vide");
        }

        String contentType = file.getContentType();
        System.out.println(contentType);

        if (!isValidContentType(contentType)) {
            return ("Ne prends pas en compte ce type de fichier" + contentType );
        } 

        try {
            
            // Create directory if not exists
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Save file
            String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String fileName = file.getOriginalFilename();

            String uploadPath = getUploadPath(extension);
            String fileUrl = getFileUrl(extension, fileName);
            
            saveFile(file, uploadPath);
            
            return (fileUrl);
            
            // Return file URL
        } catch (IOException e) {
            return ("File upload failed" + e.getMessage());
    }
}
    private String getUploadPath(String extension) {
        if (extension.equals(".jpg") || extension.equals(".jpeg") || extension.equals(".png")) {
            return uploadDir;           
        } else {
            return uploadDirAudio;
        }
    }
    
    private String getFileUrl(String extension, String fileName) {
        if (extension.equals(".mp3") || extension.equals(".mpeg") || extension.equals(".m4a") || extension.equals(".wav")) {
            return "192.168.252.156:"+port+"/uploads/" + fileName;
        } else {
            return "192.168.252.156:"+port+"/uploads/" + fileName;
        }
    }
    
    private void saveFile(MultipartFile file, String uploadPath) throws IOException {
        Path path = Paths.get(uploadPath + File.separator + file.getOriginalFilename());
        Files.write(path, file.getBytes());
    }

    private boolean isValidContentType(String contentType) {
        return  contentType.equals("audio/mpeg") || contentType.equals("audio/x-m4a") || contentType.equals("audio/wav")
        || contentType.equals("image/jpeg") || contentType.equals("image/png") || contentType.equals("image/jpg") ;
    }


    @Override
    public byte[] readFile(String fileName) throws IOException {

        String extension = fileName.substring(fileName.lastIndexOf("."));
        String path = getUploadPath(extension);
        
        Path filePath = Paths.get(path + fileName);


        if (Files.exists(filePath)) {
            return Files.readAllBytes(filePath);
        } else {
            throw new IOException("File not found");
        }
    }
    
    public String getContentType(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf("."));
        switch (extension.toLowerCase()) {
            case ".jpg":
            case ".jpeg":
                return "image/jpeg";
            case ".png":
                return "image/png";
            case ".mp3":
                return "audio/mpeg";
            case ".wav":
                return "audio/wav";
            case ".m4a":
                return "audio/x-m4a";
            default:
                return "application/octet-stream";
        }
    }
}
