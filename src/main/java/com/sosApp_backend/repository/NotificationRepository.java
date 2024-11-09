package com.sosApp_backend.repository;

import com.sosApp_backend.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    // Obtener todas las notificaciones asociadas a un usuario específico
    @Query(value= "SELECT n FROM Notification n WHERE n.user.user_id = :userId ORDER BY n.created_at DESC", nativeQuery = true)
    List<Notification> findByUserId(@Param("userId") UUID userId);

    // Obtener una notificación específica por su ID
    @Query(value= "SELECT n FROM Notification n WHERE n.notification_id = :notificationId", nativeQuery = true)
    Notification findByNotificationId(@Param("notificationId") UUID notificationId);

    // Eliminar una notificación por su ID
    @Query(value= "DELETE FROM Notification n WHERE n.notification_id = :notificationId", nativeQuery = true)
    void deleteByNotificationId(@Param("notificationId") UUID notificationId);

    // Obtener todas las notificaciones (ordenadas por fecha de creación)
    @Query(value= "SELECT n FROM Notification n ORDER BY n.created_at DESC", nativeQuery = true)
    List<Notification> findAllNotifications();

    // Buscar notificaciones que contengan una palabra clave en el contenido (opcional)
    @Query(value= "SELECT n FROM Notification n WHERE LOWER(n.content) LIKE LOWER(CONCAT('%', :keyword, '%')) ORDER BY n.created_at DESC", nativeQuery = true)
    List<Notification> findByContentContaining(@Param("keyword") String keyword);
}
