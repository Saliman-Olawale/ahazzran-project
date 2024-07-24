package ci.orange.digital.center.ahazzran.webservice.controllers;

import java.io.IOException;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import ci.orange.digital.center.ahazzran.webservice.services.RepeterService;

@RestController
@RequestMapping("/api/voice")
public class VoiceComparisonController {

    private RepeterService repeterService;

    public VoiceComparisonController(RepeterService repeterService) {
        this.repeterService = repeterService;
    }

    @PostMapping(value="/compare", consumes="multipart/form-data")
    public ResponseEntity<String> compareVoice(@RequestPart("inputVoice") MultipartFile inputVoice,
                                               @RequestPart("storedVoiceUrl") String storedVoiceUrl) throws Exception  {
        try {
            boolean match = repeterService.compareVoice(inputVoice, storedVoiceUrl);
            if (match) {
                return ResponseEntity.ok("Voices match!");
            } else {
                return ResponseEntity.ok("Voices do not match.");
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error processing files: " + e.getMessage());
        }
    }
}

