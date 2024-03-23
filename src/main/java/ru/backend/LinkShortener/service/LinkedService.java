package ru.backend.LinkShortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.backend.LinkShortener.Entity.LinkEntity;
import ru.backend.LinkShortener.Repository.LinkRepository;

import java.util.Optional;

public class LinkedService {
    @Autowired
    private LinkRepository linkRepository;

    public ResponseEntity<?> addHashLink( String link){
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

    public ResponseEntity<?> getShortLink(String short_link){
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
