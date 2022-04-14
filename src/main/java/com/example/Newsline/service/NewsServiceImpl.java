package com.example.Newsline.service;

import com.example.Newsline.domain.News;
import com.example.Newsline.dto.NewsDto;
import com.example.Newsline.repos.NewsRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/*
@author Bespalov Ivan
*/

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepo newsRepo;

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
            news.setImage(file.getBytes());
        }
        newsRepo.save(news);
    }

    @Override
    public byte[] getImage (Long id, HttpServletResponse response) throws IOException{

        Optional<News> newsSearchResult = newsRepo.findById(id);

        if(newsSearchResult.isPresent()){
            News news = newsSearchResult.get();
            if(news.getImage() != null && news.getImage().length > 0){
                byte[] img = news.getImage();

                response.setContentType("image/jpeg");
                response.setContentLength(img.length);
                response.getOutputStream().write(img);

                return news.getImage();
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image Not Found");
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
