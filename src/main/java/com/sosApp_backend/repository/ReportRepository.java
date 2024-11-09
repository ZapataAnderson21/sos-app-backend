package com.sosApp_backend.repository;

import com.sosApp_backend.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ReportRepository extends JpaRepository<Report, UUID> {

    // Obtener todos los reportes asociados a una comunidad específica
    @Query("SELECT r FROM Report r WHERE r.community.community_id = :communityId ORDER BY r.created_at DESC")
    List<Report> findByCommunityId(@Param("communityId") UUID communityId);

    // Obtener un reporte específico por su ID
    @Query("SELECT r FROM Report r WHERE r.report_id = :reportId")
    Report findByReportId(@Param("reportId") UUID reportId);

    // Eliminar un reporte por su ID
    @Query("DELETE FROM Report r WHERE r.report_id = :reportId")
    void deleteByReportId(@Param("reportId") UUID reportId);

    // Obtener todos los reportes (ordenados por fecha de creación)
    @Query("SELECT r FROM Report r ORDER BY r.created_at DESC")
    List<Report> findAllReports();

    // Buscar reportes que contengan una palabra clave en el contenido (opcional)
    @Query("SELECT r FROM Report r WHERE LOWER(r.content) LIKE LOWER(CONCAT('%', :keyword, '%')) ORDER BY r.created_at DESC")
    List<Report> findByContentContaining(@Param("keyword") String keyword);
}
