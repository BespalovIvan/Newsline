package com.example.Newsline.service;

import com.example.Newsline.dto.NewsDto;
import com.example.Newsline.dto.NewsDtoResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NewsService {

    void saveNews(NewsDto newsDto, MultipartFile file);

    List<NewsDtoResponse> findAll(Pageable pageable);


}
