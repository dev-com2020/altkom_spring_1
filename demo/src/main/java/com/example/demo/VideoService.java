package com.example.demo;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {
    private List<Video> videos = List.of(
            new Video("Pierwsza aplikacja w Spring3"),
            new Video("Tutaj druga opcja"),
            new Video("To też powinno się wyświetlić")
    );
    public List<Video> getVideos() {
        return videos;
    }

    public Video create(Video newVideo) {
        List<Video> extend = new ArrayList<>(videos);
        extend.add(newVideo);
        this.videos = List.copyOf(extend);
        return newVideo;
    }
}
