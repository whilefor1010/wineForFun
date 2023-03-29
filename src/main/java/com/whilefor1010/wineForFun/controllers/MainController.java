package com.whilefor1010.wineForFun.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Main");
        model.addAttribute("name", "my dear friend");
        return "home";
    }

    @GetMapping("/error")
    public String itsError(Model model) {
        model.addAttribute("title", "Main");
        model.addAttribute("name", "my dear friend, 404 is coming...");
        return "home";
    }

    //TODO quizzz..
    @GetMapping("/quiz")
    public String quiz(Model model) {
        model.addAttribute("title", "Quizzz...");
        model.addAttribute("name", "my dear friend");
        return "quiz";
    }

}
