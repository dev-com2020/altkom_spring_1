package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {

    private final VideoRepository repository;

    public VideoService(VideoRepository repository){
        this.repository = repository;
    }

    public List<VideoEntity> getVideos() {
        return repository.findAll();
    }

    public VideoEntity create(NewVideo newVideo) {
        return repository.saveAndFlush(new VideoEntity(newVideo.name(), newVideo.description()));
    }

    public List<VideoEntity> search(VideoSearch videoSearch){
        if (StringUtils.hasText(videoSearch.name()) && StringUtils.hasText(videoSearch.description()))
            return repository.findByNameContainsOrDescriptionContainsAllIngoreCase(videoSearch.name(), videoSearch.description());
    }
}
