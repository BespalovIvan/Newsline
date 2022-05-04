package com.example.Newsline.service;

import com.example.Newsline.domain.News;
import com.example.Newsline.domain.User;
import com.example.Newsline.dto.NewsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface NewsService {

    void saveNews(NewsDto newsDto, MultipartFile file, User user) throws IOException;

    Page<News> findAll(Pageable pageable);

    byte[] getImage (Long id, HttpServletResponse response) throws IOException;

}
