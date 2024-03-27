package com.tcs.rest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.tcs.domain.AudioFile;
import com.tcs.service.AudioFileService;
@RestController
@RequestMapping("/api/audio")
public class AudioFileController {

    @Autowired
    private AudioFileService audioFileService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadAudioFile(@RequestParam("name") String name,
                                                      @RequestParam("file") MultipartFile file) {
        try {
            byte[] fileData = file.getBytes();
             byte[] saveAudioFile = audioFileService.saveAudioFile(name, fileData);
            if (saveAudioFile != null) {
                return ResponseEntity.status(HttpStatus.OK).body(saveAudioFile);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

 @GetMapping("/{id}")
    public ResponseEntity<byte[]> getAudioFile(@PathVariable Long id) {
        byte[] audioData = audioFileService.getAudioFile(id);
        if (audioData != null) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(audioData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
