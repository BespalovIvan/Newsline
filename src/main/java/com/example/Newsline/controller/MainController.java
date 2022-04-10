package com.example.Newsline.controller;

import com.example.Newsline.dto.NewsDto;
import com.example.Newsline.service.NewsService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
     @return file name ftlh for shows to UI.
    */
    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    /*
     Method sends all news to UI.
     @param model - to transfer objects to UI.
     @param pageable - paginator, for page output of objects.
        By default, reverse sorting is applied.
     @return file name ftlh for shows to UI.
    */

    @GetMapping("/main")
    public String findAll(Model model,
                          @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        model.addAttribute("url", "/main");
        model.addAttribute("page", newsService.findAll(pageable));
        return "main";
    }

    @ResponseBody
    @GetMapping(value = "/img/news/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {

         return newsService.getImage(id,response);



    }

    /*
     Method to adds news.
     @param model - to transfer objects to UI.
     @param newsDto - dto object News.
     @param file - image News.
     @param pageable - paginator, for page output of objects.
         By default, reverse sorting is applied.
        Transferred back to UI from news.
     @return file name ftlh for shows to UI.
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
            model.addAttribute("error", e.getMessage());
        } catch (IOException e) {
            model.addAttribute("error", "Error saving image! ");
            e.printStackTrace();
        } catch (Exception e) {
            model.addAttribute("error", "Error saving news!");
        }
        model.addAttribute("page", newsService.findAll(pageable));
        model.addAttribute("url", "/main");
        return "main";
    }


}