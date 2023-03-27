package com.whilefor1010.wineForFun.controllers;


import com.whilefor1010.wineForFun.models.Wine;
import com.whilefor1010.wineForFun.repo.WineRepository;
import com.whilefor1010.wineForFun.services.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CatalogController {

    @Autowired
    private WineRepository wineRepository;

    @Autowired
    private WineService wineService;

    @GetMapping("/catalogSkip")
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


    @RequestMapping(value = "/listWines", method = RequestMethod.GET)
    public String listWines(
                            Model model,
                            @RequestParam("page") Optional<Integer> page,
                            @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);

        Page<Wine> winePage = wineService.findPaginated(PageRequest.of(currentPage - 1, pageSize),"id");

        model.addAttribute("winePage", winePage);

        int totalPages = winePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "listWines.html";
    }

   // @GetMapping("/catalog")
   @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public String catalogOf(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("sort") Optional<String> sort
            ){

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);
        String sortField = sort.orElse("id");

        if (!sortField.equals("id")
            && !sortField.equals("alcohol")
                && !sortField.equals("title")){
            sortField="id";
        }

        Page<Wine> winePage = wineService.findPaginated(PageRequest.of(currentPage - 1, pageSize), sortField);

        model.addAttribute("title", "Select your wine");
        model.addAttribute("winePage", winePage);

        int totalPages = winePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }


        return "catalogOf";
    }

}
