package ci.orange.digital.center.ahazzran.webservice.controllers;

import java.io.IOException;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ci.orange.digital.center.ahazzran.webservice.services.IUploadFileService;

@RestController
@RequestMapping("/uploads")
public class UploadFileController {

    private IUploadFileService iUploadFileService;

    public UploadFileController(final IUploadFileService iUploadFileService) {
        this.iUploadFileService = iUploadFileService;
    }

    @PostMapping(value = "/downloadfile", consumes = "multipart/form-data")
    
        public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String url = iUploadFileService.uploadFile(file);
        return ResponseEntity.ok(url);
    }
 
    @GetMapping("/{fileName}")
    public ResponseEntity<ByteArrayResource> viewFile(@PathVariable String fileName) {
        try {
            byte[] data = iUploadFileService.readFile(fileName);
            String contentType = iUploadFileService.getContentType(fileName);

            ByteArrayResource resource = new ByteArrayResource(data);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .contentLength(data.length)
                    .body(resource);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
}


/*
 * 
 *  Pour telecharger l'image ou le son.
 * 
 * @GetMapping("/view/{fileName:.+}")
   public ResponseEntity<Resource> getFile(@PathVariable String fileName)  {
        try {
            byte[] data = iUploadFileService.readFile(fileName);
            ByteArrayResource resource = new ByteArrayResource(data);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(data.length)
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
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
            default:
                return "application/octet-stream";
        }
    }
 */