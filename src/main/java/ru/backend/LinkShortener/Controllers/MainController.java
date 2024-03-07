package ru.backend.LinkShortener.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.backend.LinkShortener.Entity.LinkEntity;
import ru.backend.LinkShortener.Repository.LinkRepository;

import java.util.Optional;

@RestController
@RequestMapping()
public class MainController {
    @Autowired
    private LinkRepository linkRepository;

    public MainController(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }
    @PostMapping
    public ResponseEntity<?> addHashLink(@RequestParam String link){
        try {
            LinkEntity linkEntity = new LinkEntity();

            String linkhashcode = String.valueOf(link.hashCode());

            linkEntity.setFullLink(link);
            linkEntity.setShortedLink(linkhashcode);

            linkRepository.save(linkEntity);

            return ResponseEntity.ok("localhost:8080/" + linkhashcode);
        } catch (Exception e) {
            // Логирование ошибки
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{short_link}")
    public ResponseEntity<?> getShortLink(@PathVariable String short_link){
        Optional<LinkEntity> linkEntityOptional = linkRepository.findByShortedLink(short_link);
        if (linkEntityOptional.isPresent()) {
            LinkEntity linkEntity = linkEntityOptional.get();
            // Выполняем редирект на оригинальную ссылку
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", linkEntity.getFullLink());
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        } else {
            // Если короткая ссылка не найдена, возвращаем ошибку 404
            return ResponseEntity.ok("короткая ссылка не найдена");
        }
    }

}