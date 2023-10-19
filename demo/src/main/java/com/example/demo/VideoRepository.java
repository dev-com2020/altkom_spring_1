package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository
        <VideoEntity, Long> {

    List<VideoEntity> findByNameContainsIngoreCase(String partialName);
    List<VideoEntity> findByDescriptionContainsIngoreCase(String partialDescription);
    List<VideoEntity> findByNameContainsOrDescriptionContainsAllIngoreCase(String partialName, String partialDescription);

}
