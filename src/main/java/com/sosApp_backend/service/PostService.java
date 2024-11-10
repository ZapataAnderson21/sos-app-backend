package com.sosApp_backend.service;

import com.sosApp_backend.model.Post;
import java.util.List;
import java.util.UUID;

public interface PostService {
    Post create(Post post);
    List<Post> getAll();
    Post getById(UUID id);
    Post update(UUID id, Post post);
    boolean delete(UUID id);
    List<Post> getByUserId(UUID userId);
}
