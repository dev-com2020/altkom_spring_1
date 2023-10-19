package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoRepository extends JpaRepository<VideoEntity, Long> {

    List<VideoEntity> findByNameContainsIngoreCase(String partialName);
    List<VideoEntity> findByDescriptionContainsIngoreCase(String partialDescription);
    List<VideoEntity> findByNameContainsOrDescriptionContainsAllIngoreCase(String partialName, String partialDescription);

//    @Query("select v from VideoEntity v where v.name = ?1")
//    List<VideoEntity> findCustomerReport(String name) {
//        return null;
//    }
}
