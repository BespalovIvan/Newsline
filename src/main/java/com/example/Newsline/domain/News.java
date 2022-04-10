package com.example.Newsline.domain;

import com.example.Newsline.dto.NewsDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;
    private String header;
    private LocalDateTime createDateTime;


    private byte[] image;


    public News() {
    }

    public News(NewsDto newsDto) {
        this.text = newsDto.getText();
        this.header = newsDto.getHeader();
        this.createDateTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public String getCreateDateTime() {
        return createDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yy HH:mm"));
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }
}

