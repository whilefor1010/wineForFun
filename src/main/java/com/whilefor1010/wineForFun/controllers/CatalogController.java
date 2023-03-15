package com.whilefor.blogITP.controllers;

import com.whilefor.blogITP.models.Post;
import com.whilefor.blogITP.repo.PostRepository;
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
    private PostRepository postRepository;

    @GetMapping("/catalog")
    public String catalog(Model model) {

        Iterable<Post> posts = postRepository.findAll();

        model.addAttribute("title", "Select your wine");
        model.addAttribute("posts", posts);
        return "catalog";
    }

    @GetMapping("/catalog/add")
    public String catalogAdd(Model model) {

       return "catalog-add";
    }

    @PostMapping("/catalog/add")
    public String catalogAddPost(@RequestParam String title,
                                 @RequestParam String anons,
                                 @RequestParam String full_text,
                                 Model model){

        Post post1 = new Post(title, anons, full_text);
        postRepository.save(post1);

        return "redirect:/catalog";

    }

    @GetMapping("/catalog/{id}")
    public String catalogDetails(@PathVariable(value = "id") long curId, Model model) {

       if (!postRepository.existsById(curId)){
           return "redirect:/catalog";
       }

        Optional<Post> post = postRepository.findById(curId);

        ArrayList<Post> res = new ArrayList<>();

        post.ifPresent(res::add);

        model.addAttribute("post", res);
        return "catalog-details";
    }

    @GetMapping("/catalog/{id}/edit")
    public String catalogEdit(@PathVariable(value = "id") long curId, Model model) {

        if (!postRepository.existsById(curId)){
            return "redirect:/catalog";
        }

        Optional<Post> post = postRepository.findById(curId);

        ArrayList<Post> res = new ArrayList<>();

        post.ifPresent(res::add);

        model.addAttribute("post", res);
        return "catalog-edit";
    }

    @PostMapping("/catalog/{id}/edit")
    public String catalogEditPost(@PathVariable(value = "id") long curId,
                                     @RequestParam String title,
                                     @RequestParam String anons,
                                     @RequestParam String full_text,
                                     Model model){

        Post post = postRepository.findById(curId).orElseThrow();

        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);

        postRepository.save(post);

        return "redirect:/catalog";

    }

    @PostMapping("/catalog/{id}/remove")
    public String catalogDeletePost(@PathVariable(value = "id") long curId,
                                        Model model){

        Post post = postRepository.findById(curId).orElseThrow();

        postRepository.delete(post);

        return "redirect:/catalog";

    }


}
