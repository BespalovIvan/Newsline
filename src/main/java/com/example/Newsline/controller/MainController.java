package com.example.Newsline.controller;

import com.example.Newsline.dto.NewsDto;
import com.example.Newsline.service.NewsService;
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
                       @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        model.addAttribute("url", "/main");
        model.addAttribute("page", newsService.findAll(pageable));
        return "main";
    }

    @PostMapping("/main")
    public String add(Model model, NewsDto newsDto,
                      @RequestParam MultipartFile file,
                      @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        newsService.saveNews(newsDto, file);
        model.addAttribute("page", newsService.findAll(pageable));
        model.addAttribute("url", "/main");
        return "main";
    }

}
