package com.sosApp_backend.repository;

import com.sosApp_backend.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CommunityRepository extends JpaRepository<Community, UUID> {

    // Obtener todas las comunidades creadas por un usuario específico
    @Query("SELECT c FROM Community c WHERE c.creator.user_id = :userId")
    List<Community> findByUserId(@Param("userId") UUID userId);

    // Obtener una comunidad por su ID
    @Query("SELECT c FROM Community c WHERE c.community_id = :communityId")
    Community findByCommunityId(@Param("communityId") UUID communityId);

    // Eliminar una comunidad por su ID
    @Query("DELETE FROM Community c WHERE c.community_id = :communityId")
    void deleteByCommunityId(@Param("communityId") UUID communityId);

    // Obtener comunidades por nombre, usando búsqueda parcial (opcional)
    @Query("SELECT c FROM Community c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Community> findByNameContaining(@Param("name") String name);

    // Obtener todas las comunidades de un departamento
    @Query("SELECT c FROM Community c WHERE LOWER(c.department) = LOWER(:department)")
    List<Community> findByDepartment(@Param("department") String department);
}
