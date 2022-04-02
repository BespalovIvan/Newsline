package com.example.Newsline.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewsDtoResponse {

    private final String header;
    private final String text;
    private final String file;
    private final LocalDateTime createDateTime;

    public NewsDtoResponse(String header, String text, String file, LocalDateTime createDateTime) {
        this.header = header;
        this.text = text;
        this.file = file;
        this.createDateTime = createDateTime;
    }

    public String getHeader() {
        return header;
    }

    public String getText() {
        return text;
    }

    public String getFile() {
        return file;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }
}