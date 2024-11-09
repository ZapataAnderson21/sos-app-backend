package com.sosApp_backend.repository;

import com.sosApp_backend.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CommunityRepository extends JpaRepository<Community, UUID> {

    // Obtener todas las comunidades creadas por un usuario específico
    @Query(value= "SELECT c FROM Community c WHERE c.creator.user_id = :userId", nativeQuery = true)
    List<Community> findByUserId(@Param("userId") UUID userId);

    // Obtener una comunidad por su ID
    @Query(value= "SELECT c FROM Community c WHERE c.community_id = :communityId", nativeQuery = true)
    Community findByCommunityId(@Param("communityId") UUID communityId);

    // Eliminar una comunidad por su ID
    @Query(value= "DELETE FROM Community c WHERE c.community_id = :communityId", nativeQuery = true)
    void deleteByCommunityId(@Param("communityId") UUID communityId);

    // Obtener comunidades por nombre, usando búsqueda parcial (opcional)
    @Query(value= "SELECT c FROM Community c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))", nativeQuery = true)
    List<Community> findByNameContaining(@Param("name") String name);

    // Obtener todas las comunidades de un departamento
    @Query(value= "SELECT c FROM Community c WHERE LOWER(c.department) = LOWER(:department)", nativeQuery = true)
    List<Community> findByDepartment(@Param("department") String department);
}
