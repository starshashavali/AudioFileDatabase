package com.tcs.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.domain.AudioFile;
import com.tcs.repo.AudioFileRepository;
@Service
public class AudioFileService {
    @Autowired
    private AudioFileRepository audioFileRepository;

    public byte[] saveAudioFile(String name, byte[] data) {
        AudioFile audioFile = new AudioFile();
        audioFile.setName(name);
        audioFile.setData(data);
        AudioFile savedAudioFile = audioFileRepository.save(audioFile);
        return savedAudioFile.getData();
    }

    public byte[] getAudioFile(Long id) {
        AudioFile audioFile = audioFileRepository.findById(id).orElse(null);
        if (audioFile != null) {
            return audioFile.getData();
        } else {
            return null;
        }
    }

}

