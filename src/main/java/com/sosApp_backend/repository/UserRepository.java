package com.sosApp_backend.repository;

import com.sosApp_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findByUsernameContaining(String username);

    // Buscar un usuario por su username
    @Query(value= "SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username)", nativeQuery = true)
    User findByUsername(@Param("username") String username);

    // Buscar un usuario por su DNI
    @Query(value= "SELECT u FROM User u WHERE u.dni = :dni", nativeQuery = true)
    User findByDni(@Param("dni") String dni);

    // Obtener un usuario por su ID
    @Query(value= "SELECT u FROM User u WHERE u.user_id = :userId", nativeQuery = true)
    User findByUserId(@Param("userId") UUID userId);

    // Eliminar un usuario por su ID
    @Query(value= "DELETE FROM User u WHERE u.user_id = :userId", nativeQuery = true)
    void deleteByUserId(@Param("userId") UUID userId);

    // Obtener todos los usuarios (ordenados por nombre completo)
    @Query(value= "SELECT u FROM User u ORDER BY u.full_name", nativeQuery = true)
    List<User> findAllUsers();

    // Buscar usuarios cuyo nombre completo contenga una palabra clave (opcional)
    @Query(value= "SELECT u FROM User u WHERE LOWER(u.full_name) LIKE LOWER(CONCAT('%', :keyword, '%'))", nativeQuery = true)
    List<User> findByFullNameContaining(@Param("keyword") String keyword);
}
