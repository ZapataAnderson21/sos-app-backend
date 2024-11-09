package com.sosApp_backend.controller;

import com.sosApp_backend.model.Post;
import com.sosApp_backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public ResponseEntity<?> createPost() {
        // Crear un nuevo post
        Map<String, Object>  response= new HashMap<>();
        response.put("status", true);
        response.put("mensaje", "Publicacion creada correctamente");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        Post post = new Post();
        return new ResponseEntity<>("Obteniendo todos los post", HttpStatus.CREATED);
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
