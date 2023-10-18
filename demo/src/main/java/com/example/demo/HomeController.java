package com.example.demo;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    record Video(String name) {}
    List<Video> videos = List.of(
            new Video("Pierwsza aplikacja w Spring3"),
            new Video("Tutaj druga opcja"),
            new Video("To też powinno się wyświetlić")
    );

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("video",videos);
        return "index";
    }
}