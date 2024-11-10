package com.sosApp_backend.controller;

import com.sosApp_backend.model.Post;
import com.sosApp_backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/post")
@CrossOrigin("*")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createPost(@RequestBody Post post) {
        Map<String, Object> response = new HashMap<>();

        if (post.getTitle() == null || post.getTitle().isEmpty()) {
            response.put("status", false);
            response.put("message", "El título no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (post.getContent() == null || post.getContent().isEmpty()) {
            response.put("status", false);
            response.put("message", "El contenido no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        postService.create(post);
        response.put("status", true);
        response.put("message", "Publicación creada correctamente");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Map<String, Object>> getPostById(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();
        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Post post = postService.getById(id);
        if (post == null) {
            response.put("status", false);
            response.put("message", "No se encontró la publicación con el ID proporcionado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("post", post);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updatePost(@PathVariable UUID id, @RequestBody Post post) {
        Map<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (post.getTitle() == null || post.getTitle().isEmpty()) {
            response.put("status", false);
            response.put("message", "El título no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (post.getContent() == null || post.getContent().isEmpty()) {
            response.put("status", false);
            response.put("message", "El contenido no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Post updatedPost = postService.update(id, post);
        if (updatedPost == null) {
            response.put("status", false);
            response.put("message", "No se pudo actualizar la publicación. ID no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("message", "Publicación actualizada correctamente");
        response.put("post", updatedPost);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deletePost(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        boolean deleted = postService.delete(id);
        if (!deleted) {
            response.put("status", false);
            response.put("message", "No se pudo eliminar la publicación. ID no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("message", "Publicación eliminada correctamente");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getPostsByUserId(@PathVariable UUID userId) {
        Map<String, Object> response = new HashMap<>();

        if (userId == null) {
            response.put("status", false);
            response.put("message", "El ID de usuario no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        List<Post> posts = postService.getByUserId(userId);
        if (posts == null || posts.isEmpty()) {
            response.put("status", false);
            response.put("message", "No se encontraron publicaciones para el usuario con el ID proporcionado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("posts", posts);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
