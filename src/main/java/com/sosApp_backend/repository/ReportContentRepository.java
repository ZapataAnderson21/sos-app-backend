package com.sosApp_backend.repository;

import com.sosApp_backend.model.ReportContent;
import com.sosApp_backend.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface ReportContentRepository extends JpaRepository<ReportContent, UUID> {

    @Query(value = "SELECT rc FROM ReportContent rc WHERE rc.report.report_id = :reportId ORDER BY rc.content", nativeQuery = true)
    List<ReportContent> findByReportId(@Param("reportId") UUID reportId);

    @Query(value = "SELECT rc FROM ReportContent rc WHERE rc.id = :contentId", nativeQuery = true)
    ReportContent findByContentId(@Param("contentId") UUID contentId);

    @Query(value = "DELETE FROM ReportContent rc WHERE rc.id = :contentId", nativeQuery = true)
    void deleteByContentId(@Param("contentId") UUID contentId);

    @Query(value = "SELECT rc FROM ReportContent rc ORDER BY rc.content_type", nativeQuery = true)
    List<ReportContent> findAllOrderedByContentType();

    @Query(value = "SELECT rc FROM ReportContent rc WHERE LOWER(rc.content) LIKE LOWER(CONCAT('%', :keyword, '%')) ORDER BY rc.content", nativeQuery = true)
    List<ReportContent> findByContentContent(@Param("keyword") String keyword);

    @Query(value = "SELECT rc FROM ReportContent rc WHERE LOWER(rc.content_type) LIKE LOWER(CONCAT('%', :keyword, '%')) ORDER BY rc.content_type", nativeQuery = true)
    List<ReportContent> findByContentTypeContent(@Param("keyword") String keyword);
}