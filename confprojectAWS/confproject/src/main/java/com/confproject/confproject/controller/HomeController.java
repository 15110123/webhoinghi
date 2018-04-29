package com.confproject.confproject.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.confproject.confproject.model.Admin;
import com.confproject.confproject.model.News;
import com.confproject.confproject.model.Page;
import com.confproject.confproject.service.AdminService;
import com.confproject.confproject.service.NewsService;
import com.confproject.confproject.service.PageService;
import com.confproject.confproject.utils.PathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {
    @Autowired
    private PageService pageservice;

    @Autowired
    private NewsService newsservice;

    @Autowired
    private AdminService adminservice;

    @GetMapping("/")
    public String home(HttpSession session, HttpServletRequest request, ModelMap mm) {
        List<News> lstnews = newsservice.findAll();
        mm.addAttribute("listnews", lstnews);
        Page pg = pageservice.findPage(1);
        mm.addAttribute("titlehp", pg.getTitle());
        mm.addAttribute("bodyhp", pg.getBody());
        mm.addAttribute("isNews", false);
        return "homepage";
    }

    @GetMapping("/newsdetails")
    public String newsdetails(@RequestParam("id") Integer newsId, HttpSession session, HttpServletRequest request, ModelMap mm) {
        List<News> lstnews = newsservice.findAll();
        mm.addAttribute("listnews", lstnews);
        News news = newsservice.findNews(newsId);
        mm.addAttribute("titlehp", news.getTitle());
        mm.addAttribute("bodyhp", news.getBody());
        mm.addAttribute("isNews", true);
        return "homepage";
    }

    @GetMapping("/tinmoicapnhat")
    public String newslist(HttpSession session, HttpServletRequest request, ModelMap mm) {
        List<News> lstnews = newsservice.findAll();
        mm.addAttribute("listnews", lstnews);
        return "newslist";
    }

    @PostMapping("/addNews")
    public String addNews(@ModelAttribute News news, ModelMap mm) {
        mm.addAttribute("id", news.getId());
        return "editnews";
    }

    @PostMapping("/editNews")
    public String editnews(@ModelAttribute News news, ModelMap mm) {
        mm.addAttribute("id", news.getId());
        News foundnews = newsservice.findNews(news.getId());
        mm.addAttribute("title", foundnews.getTitle());
        mm.addAttribute("body", foundnews.getBody());
        return "editnews";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/loginverify")
    public String login(@ModelAttribute Admin admin, HttpSession session, HttpServletRequest request, ModelMap mm) {
        session = request.getSession();
        boolean f = adminservice.validatelogin(admin.getUsername(), admin.getPassword());
        if (f == true) session.setAttribute("isadmin", "true");
        else session.setAttribute("isadmin", "false");
        return "redirect:/";
    }

    @PostMapping("/deleteNews")
    public String deleteNews(@ModelAttribute News news) {
        newsservice.delete(news.getId());
        return "redirect:/tinmoicapnhat";
    }

    @PostMapping("/saveNews")
    public String saveNews(@ModelAttribute News news, @RequestPart(value = "file") MultipartFile file, HttpServletRequest request) throws Exception {
        if (news.getId() != -1) {
            if (file.getSize() != 0) {
                news.setImgurl(newsservice.uploadFile(file));
            }
            //Preserve old image if no file was uploaded
            else {
                News existnews = newsservice.findNews(news.getId());
                if (existnews != null) {
                    news.setImgurl(existnews.getImgurl());
                }
            }
            news.setDate_created(new Date());
            newsservice.save(news);
            return "redirect:/tinmoicapnhat";
        } else {
            Page pg = new Page();
            pg.setId(1);
            pg.setTitle(news.getTitle());
            pg.setBody(news.getBody());
            pageservice.save(pg);
            return "redirect:/";
        }
    }

    @GetMapping("/edithomepage")
    public String edithomepage(ModelMap mm) {
        mm.addAttribute("id", -1);
        Page pg = pageservice.findPage(1);
        mm.addAttribute("title", pg.getTitle());
        mm.addAttribute("body", pg.getBody());
        return "editnews";
    }
}