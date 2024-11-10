package com.sosApp_backend.implement;

import com.sosApp_backend.model.Post;
import com.sosApp_backend.repository.PostRepository;
import com.sosApp_backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImplements implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post create(Post post) {
        // Guarda un nuevo post en la base de datos
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAll() {
        // Retorna todos los posts
        return postRepository.findAll();
    }

    @Override
    public Post getById(UUID id) {
        // Busca un post por su ID
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + id));
    }

    @Override
    public Post update(UUID id, Post post) {
        // Actualiza un post si existe
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + id));
        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());
        existingPost.setPhoto(post.getPhoto());
        existingPost.setUser(post.getUser());
        return postRepository.save(existingPost);
    }

    @Override
    public boolean delete(UUID id) {
        // Verifica si el post existe antes de eliminarlo
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Post> getByUserId(UUID userId) {
        // Retorna todos los posts asociados a un usuario específico
        return postRepository.findByUserId(userId);
    }
}
