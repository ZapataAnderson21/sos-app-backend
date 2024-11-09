package com.sosApp_backend.repository;

import com.sosApp_backend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    // Obtener todas las publicaciones realizadas por un usuario específico
    @Query("SELECT p FROM Post p WHERE p.user.user_id = :userId ORDER BY p.title")
    List<Post> findByUserId(@Param("userId") UUID userId);

    // Obtener una publicación específica por su ID
    @Query("SELECT p FROM Post p WHERE p.post_id = :postId")
    Post findByPostId(@Param("postId") UUID postId);

    // Eliminar una publicación por su ID
    @Query("DELETE FROM Post p WHERE p.post_id = :postId")
    void deleteByPostId(@Param("postId") UUID postId);

    // Obtener todas las publicaciones (ordenadas por título)
    @Query("SELECT p FROM Post p ORDER BY p.title")
    List<Post> findAllPosts();

    // Buscar publicaciones que contengan una palabra clave en el título (opcional)
    @Query("SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) ORDER BY p.title")
    List<Post> findByTitleContaining(@Param("keyword") String keyword);

    // Buscar publicaciones que contengan una palabra clave en el contenido (opcional)
    @Query("SELECT p FROM Post p WHERE LOWER(p.content) LIKE LOWER(CONCAT('%', :keyword, '%')) ORDER BY p.title")
    List<Post> findByContentContaining(@Param("keyword") String keyword);
}