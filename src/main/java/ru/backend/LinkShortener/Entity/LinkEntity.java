package ru.backend.LinkShortener.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "link")
public class LinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_link", nullable = false)
    private String fullLink;

    @Column(name = "shorted_link", nullable = false)
    private String shortedLink;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullLink() {
        return fullLink;
    }

    public void setFullLink(String fullLink) {
        this.fullLink = fullLink;
    }

    public String getShortedLink() {
        return shortedLink;
    }

    public void setShortedLink(String shortedLink) {
        this.shortedLink = shortedLink;
    }

    public LinkEntity() {
    }
}