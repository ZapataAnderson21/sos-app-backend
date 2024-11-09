package com.sosApp_backend.controller;

import com.sosApp_backend.model.Post;
import com.sosApp_backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public Post createPost(@RequestBody Post post) {
        // Crear un nuevo post
        return postService.create(post);
    }

    @GetMapping("/all")
    public List<Post> getAllPosts() {
        // Obtener todos los posts
        return postService.getAll();
    }

    @GetMapping("/id/{id}")
    public Post getPostById(@PathVariable UUID id) {
        // Obtener un post por su ID
        return postService.getById(id);
    }

    @PutMapping("/update/{id}")
    public Post updatePost(@PathVariable UUID id, @RequestBody Post post) {
        // Actualizar un post por su ID
        return postService.update(id, post);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable UUID id) {
        // Eliminar un post por su ID
        postService.delete(id);
    }

    @GetMapping("/user/{userId}")
    public List<Post> getPostsByUserId(@PathVariable UUID userId) {
        // Obtener todos los posts asociados a un usuario específico
        return postService.getByUserId(userId);
    }
}
