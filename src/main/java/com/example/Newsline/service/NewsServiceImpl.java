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

/*
@author Bespalov Ivan
*/

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepo newsRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public NewsServiceImpl(NewsRepo newsRepo) {
        this.newsRepo = newsRepo;
    }

     /*
     Method to save news.
     @param newsDto - dto object news.
     @param file - image news.
    */

    @Override
    public void saveNews(NewsDto newsDto, MultipartFile file) throws IllegalArgumentException, IOException {
        if (newsDto.getHeader().isEmpty() || newsDto.getText().isEmpty()) {
            throw new IllegalArgumentException("Field Header or text not must be empty!");
        }

        News news = new News(newsDto);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            news.setFilename(saveFile(file));
        }
        newsRepo.save(news);
    }

    /*
     Method to save image.
     @param file - image news.
    */
    private String saveFile(MultipartFile file) throws IOException {
        File uploadDir = new File(uploadPath);

        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        String uidFile = UUID.randomUUID().toString();
        String resultFilename = uidFile + "." + file.getOriginalFilename();

        file.transferTo(new File(uploadPath + "/" + resultFilename));
        return resultFilename;
    }

    /*
     Method for finding the required quantity news.
     @param pageable - paginator, for page output of objects.
     @return Page<News> - prepared list of News.
    */

    @Override
    public Page<News> findAll(Pageable pageable) {
        return newsRepo.findAll(pageable);
    }
}
