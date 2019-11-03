package com.cristianaugusto.projetospring.repositories;

import com.cristianaugusto.projetospring.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    List<Blog> findByTitleContainingOrContentContaining(String searchTitle, String searchContent);
}
