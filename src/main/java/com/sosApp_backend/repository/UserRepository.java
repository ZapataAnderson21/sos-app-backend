package com.sosApp_backend.repository;

import com.sosApp_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    // Buscar un usuario por su username
    @Query("SELECT u FROM User u WHERE LOWER(u.usernmae) = LOWER(:username)")
    User findByUsername(@Param("username") String username);

    // Buscar un usuario por su DNI
    @Query("SELECT u FROM User u WHERE u.dni = :dni")
    User findByDni(@Param("dni") String dni);

    // Obtener un usuario por su ID
    @Query("SELECT u FROM User u WHERE u.user_id = :userId")
    User findByUserId(@Param("userId") UUID userId);

    // Eliminar un usuario por su ID
    @Query("DELETE FROM User u WHERE u.user_id = :userId")
    void deleteByUserId(@Param("userId") UUID userId);

    // Obtener todos los usuarios (ordenados por nombre completo)
    @Query("SELECT u FROM User u ORDER BY u.full_name")
    List<User> findAllUsers();

    // Buscar usuarios cuyo nombre completo contenga una palabra clave (opcional)
    @Query("SELECT u FROM User u WHERE LOWER(u.full_name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<User> findByFullNameContaining(@Param("keyword") String keyword);
}
