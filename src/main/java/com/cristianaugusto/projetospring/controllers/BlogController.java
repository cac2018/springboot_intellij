package com.cristianaugusto.projetospring.controllers;

import com.cristianaugusto.projetospring.models.Blog;
import com.cristianaugusto.projetospring.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BlogController {

    @Autowired
    private BlogRepository repository;

    @GetMapping("/blog")
    public List<Blog> index() {
        return repository.findAll();
    }

    @GetMapping("/blog/{id}")
    public Blog show(@PathVariable String id) {
        Long blogId = Long.parseLong(id);
        Optional<Blog> optional = repository.findById(blogId);
        return optional.orElse(null);
    }

    @PostMapping("/blog/search")
    public List<Blog> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
        return repository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
    }

    @PostMapping("/blog")
    public Blog create(@RequestBody Map<String, String> body) {
        Long id = Long.parseLong(body.get("id"));
        String title = body.get("title");
        String content = body.get("content");
        return repository.save(new Blog(null, title, content));
    }

    @PutMapping("/blog/{id}")
    public Blog update(@PathVariable String id, @RequestBody Map<String, String> body) {
        Long blogId = Long.parseLong(id);
        Optional<Blog> optional = repository.findById(blogId);
        if (optional.isPresent()) {
            optional.get().setTitle(body.get("title"));
            optional.get().setContent(body.get("content"));
            optional.get().setId(blogId);
            return repository.save(optional.get());
        }
        return null;
    }


    @DeleteMapping("/blog/{id}")
    public boolean delete(@PathVariable String id) {
        Long blogId = Long.parseLong(id);
        repository.deleteById(blogId);
        return true;
    }
}
