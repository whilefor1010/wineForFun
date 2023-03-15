package com.whilefor.blogITP.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String up1(Model model) {
        model.addAttribute("title", "Главная страница");
        model.addAttribute("name", "my dear friend");
        return "home";
    }

    @GetMapping("/up")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        model.addAttribute("name", "my dear friend");
        return "index";
    }



    @GetMapping("/quiz")
    public String quiz(Model model) {
        model.addAttribute("title", "Quizzz...");
        model.addAttribute("name", "my dear friend");
        return "quiz";
    }

}
