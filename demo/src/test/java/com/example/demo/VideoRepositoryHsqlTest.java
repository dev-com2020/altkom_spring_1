package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class VideoRepositoryHsqlTest {
    @Autowired VideoRepository repository;

    @BeforeEach
    void setUp(){
        repository.saveAll(
                List.of(
                        new VideoEntity(
                                "alicja",
                                "Need HELP with your SPRING BOOT 3 App?",
                                "SPRING BOOT 3 will only speed things up."
                        ),
                        new VideoEntity(
                                "bob",
                                "Need HELP with your SPRING BOOT 3 App?",
                                "SPRING BOOT 3 will only speed things up."
                        ),
                        new VideoEntity(
                                "alicja",
                                "Need HELP with your SPRING BOOT 3 App?",
                                "SPRING BOOT 3 will only speed things up."
                        )));
    }
    @Test
    void findAllShouldProduceAllVideos(){
        List<VideoEntity> videos = repository.findAll();
        assertThat(videos).hasSize(3);
    }
}
