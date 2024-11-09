package com.sosApp_backend.repository;

import com.sosApp_backend.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AlertRepository extends JpaRepository<Alert, UUID> {

    // Obtener todas las alertas creadas por un usuario específico
    @Query(value= "SELECT a FROM Alert a WHERE a.user.user_id = :userId", nativeQuery = true)
    List<Alert> findByUserId(@Param("userId") UUID userId);

    // Obtener todas las alertas asociadas a una comunidad específica
    @Query(value= "SELECT a FROM Alert a WHERE a.community.community_id = :communityId", nativeQuery = true)
    List<Alert> findByCommunityId(@Param("communityId") UUID communityId);

    // Buscar una alerta por su ID
    @Query(value= "SELECT a FROM Alert a WHERE a.alert_id = :alertId", nativeQuery = true)
    Alert findByAlertId(@Param("alertId") UUID alertId);

    // Actualizar una alerta (se implementa en el Service utilizando save())
    // No requiere un query específico, ya que JPA maneja las actualizaciones automáticamente.

    // Eliminar una alerta por su ID
    @Query(value= "DELETE FROM Alert a WHERE a.alert_id = :alertId", nativeQuery = true)
    void deleteByAlertId(@Param("alertId") UUID alertId);

    // Buscar alertas con un título específico (opcional para funcionalidades avanzadas)
    @Query(value= "SELECT a FROM Alert a WHERE LOWER(a.title) LIKE LOWER(CONCAT('%', :title, '%'))", nativeQuery = true)
    List<Alert> findByTitleContaining(@Param("title") String title);
}
