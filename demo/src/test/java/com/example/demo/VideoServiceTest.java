package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {
    VideoService service;
    @Mock
    VideoRepository repository;

    @BeforeEach
    void setUp() {
        this.service = new VideoService(repository);
    }

    @Test
    void getVideosShouldReturnAll() {
//        given
        VideoEntity video1 = new VideoEntity("alicja", "Spring Boot 3 kurs", "Uczymy się");
        VideoEntity video2 = new VideoEntity("alicja", "Spring Boot 3 kurs zaawansowany", "Uczymy się dalej");
        when(repository.findAll()).thenReturn(List.of(video1, video2));
//        when
        List<VideoEntity> videos = service.getVideos();
//        then
        assertThat(videos).containsExactly(video1, video2);
    }

    @Test
    void deletingAVideoShouldWork(){
//        given
        VideoEntity entity = new VideoEntity("alicja","name","desc");
        entity.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
//        when
        service.delete(1L);
//        then
        verify(repository).findById(1L);
        verify(repository).delete(entity);
    }

}
