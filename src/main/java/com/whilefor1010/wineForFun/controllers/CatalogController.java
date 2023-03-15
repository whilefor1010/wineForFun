package com.whilefor1010.wineForFun.controllers;


import com.whilefor1010.wineForFun.models.Wine;
import com.whilefor1010.wineForFun.repo.WineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class CatalogController {

    @Autowired
    private WineRepository wineRepository;

    @GetMapping("/catalog")
    public String catalog(Model model) {

        Iterable<Wine> wines = wineRepository.findAll();

        model.addAttribute("title", "Select your wine");
        model.addAttribute("wines", wines);
        return "catalog";
    }

    @GetMapping("/catalog/add")
    public String catalogAdd(Model model) {

       return "catalog-add";
    }

    @PostMapping("/catalog/add")
    public String catalogAddWine(@RequestParam String title,
                                 @RequestParam String anons,
                                 @RequestParam String full_text,
                                 Model model){

        Wine wine= new Wine(title, anons, full_text);
        wineRepository.save(wine);

        return "redirect:/catalog";

    }

    @GetMapping("/catalog/{id}")
    public String catalogDetails(@PathVariable(value = "id") long curId, Model model) {

       if (!wineRepository.existsById(curId)){
           return "redirect:/catalog";
       }

        Optional<Wine> wine = wineRepository.findById(curId);

        ArrayList<Wine> res = new ArrayList<>();

        wine.ifPresent(res::add);

        model.addAttribute("wine", res);
        return "catalog-details";
    }

    @GetMapping("/catalog/{id}/edit")
    public String catalogEdit(@PathVariable(value = "id") long curId, Model model) {

        if (!wineRepository.existsById(curId)){
            return "redirect:/catalog";
        }

        Optional<Wine> wine = wineRepository.findById(curId);

        ArrayList<Wine> res = new ArrayList<>();

        wine.ifPresent(res::add);

        model.addAttribute("wine", res);
        return "catalog-edit";
    }

    @PostMapping("/catalog/{id}/edit")
    public String catalogEditWine(@PathVariable(value = "id") long curId,
                                     @RequestParam String title,
                                     @RequestParam String anons,
                                     @RequestParam String full_text,
                                     Model model){

        Wine wine = wineRepository.findById(curId).orElseThrow();

        wine.setTitle(title);
        wine.setAnons(anons);
        wine.setFull_text(full_text);

        wineRepository.save(wine);

        return "redirect:/catalog";

    }

    @PostMapping("/catalog/{id}/remove")
    public String catalogDeleteWine(@PathVariable(value = "id") long curId,
                                        Model model){

        Wine wine = wineRepository.findById(curId).orElseThrow();

        wineRepository.delete(wine);

        return "redirect:/catalog";

    }


}
