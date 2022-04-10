package com.example.Newsline.repos;

import com.example.Newsline.domain.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NewsRepo extends CrudRepository<News,Long> {
    Page<News> findAll(Pageable pageable);

}
