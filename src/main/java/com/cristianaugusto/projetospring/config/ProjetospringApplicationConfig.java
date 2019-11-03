package com.cristianaugusto.projetospring.config;

import com.cristianaugusto.projetospring.models.Blog;
import com.cristianaugusto.projetospring.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjetospringApplicationConfig implements CommandLineRunner {

    @Autowired
    BlogRepository repository;

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();
        Blog blog1 = new Blog(null,"Teste","Conteudo");
        repository.save(blog1);

    }
}
