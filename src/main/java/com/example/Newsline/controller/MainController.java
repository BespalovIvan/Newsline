package com.example.Newsline.controller;

import com.example.Newsline.domain.News;
import com.example.Newsline.dto.NewsDto;
import com.example.Newsline.repos.NewsRepo;
import com.example.Newsline.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {

    @Autowired
    private NewsRepo newsRepo;

    private final NewsService newsService;

    public MainController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Model model,
                       @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC)Pageable pageable
    ){
        //Page<News> page;
        //page = newsRepo.findAll(pageable);
        //model.addAttribute("page",page);
        model.addAttribute("url","/main");
        model.addAttribute("newsList", newsService.findAll(pageable));
        return "main";
    }

    @PostMapping("/main")
    public String add(Model model, NewsDto newsDto,
                      @RequestParam MultipartFile file,
                      @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC)Pageable pageable
    ) {
        Page<News> page;
        model.addAttribute("newsList", newsService.findAll(pageable));
        newsService.saveNews(newsDto, file);
        page = newsRepo.findAll(pageable);
        model.addAttribute("url", "/main");
        model.addAttribute("page", page);
        return "main";
    }

}
