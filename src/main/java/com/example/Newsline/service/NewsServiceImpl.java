package com.example.Newsline.service;

import com.example.Newsline.domain.News;
import com.example.Newsline.dto.NewsDto;
import com.example.Newsline.repos.NewsRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepo newsRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public NewsServiceImpl(NewsRepo newsRepo) {
        this.newsRepo = newsRepo;
    }

    @Override
    public void saveNews(NewsDto newsDto, MultipartFile file) {
        News news = new News(newsDto);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uidFile = UUID.randomUUID().toString();
            String resultFilename = uidFile + "." + file.getOriginalFilename();

            try {
                file.transferTo(new File(uploadPath + "/" + resultFilename));
            } catch (IOException e) {
                e.printStackTrace();
                // добавить обработку исключения
            }

            news.setFilename(resultFilename);
        }
        newsRepo.save(news);
    }

    @Override
    public Page<News> findAll(Pageable pageable) {
        return newsRepo.findAll(pageable);
    }
}
