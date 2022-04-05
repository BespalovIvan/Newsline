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

import java.io.IOException;

/*
@author Bespalov Ivan
*/

@Controller
public class MainController {

    private final NewsService newsService;

    public MainController(NewsService newsService) {
        this.newsService = newsService;
    }

    /*
     Method for displaying the main page,
     @return file name ftlh to display on the UI.
    */

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    /*
     Method to send all news to UI.
     @param model - to transfer objects to UI.
     @param pageable - paginator, for page output of objects.
        By default, reverse sorting is applied.
     @return file name ftlh to display on the UI.
    */

    @GetMapping("/main")
    public String findAll(Model model,
                          @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        model.addAttribute("url", "/main");
        model.addAttribute("page", newsService.findAll(pageable));
        return "main";
    }

    /*

     Method to add news.
     @param model - to transfer objects to UI.
     @param newsDto - dto object News.
     @param file - image News.
     @param pageable - paginator, for page output of objects.
         By default, reverse sorting is applied.
        Transferred back to UI from news.
     @return file name ftlh to display on the UI.
    */

    @PostMapping("/main")
    public String addNews(Model model, NewsDto newsDto,
                      @RequestParam MultipartFile file,
                      @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        try {
            newsService.saveNews(newsDto, file);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error saving image! ");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error saving news!");
        }

        model.addAttribute("page", newsService.findAll(pageable));
        model.addAttribute("url", "/main");
        return "main";
    }

}
