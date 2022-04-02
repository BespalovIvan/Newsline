package com.example.Newsline.dto;

public class NewsDto {

    private String header;
    private String text;

    public NewsDto() {
    }

    public NewsDto(String header, String text) {
        this.header = header;
        this.text = text;
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

}
