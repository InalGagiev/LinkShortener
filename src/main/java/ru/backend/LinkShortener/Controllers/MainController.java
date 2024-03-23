package ru.backend.LinkShortener.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.backend.LinkShortener.Entity.LinkEntity;
import ru.backend.LinkShortener.Repository.LinkRepository;
import ru.backend.LinkShortener.service.LinkedService;

import java.util.Optional;

@RestController
@RequestMapping()
public class MainController {
    private LinkedService linkedService;

    public MainController(LinkedService linkedService) {
        this.linkedService = linkedService;
    }

    @PostMapping
    public ResponseEntity<?> addHashLink(@RequestParam String link){
        return linkedService.addHashLink(link);
    }

    @GetMapping("/{short_link}")
    public ResponseEntity<?> getShortLink(@PathVariable String short_link){
        return linkedService.getShortLink(short_link);
    }
}