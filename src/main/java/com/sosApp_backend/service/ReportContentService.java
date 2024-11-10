package com.sosApp_backend.service;

import com.sosApp_backend.model.Report;
import com.sosApp_backend.model.ReportContent;

import java.util.List;
import java.util.UUID;

public interface ReportContentService {
    ReportContent createReportContent(ReportContent reportContent);
    List<ReportContent> getAll();
    ReportContent getById(UUID id);
    ReportContent update(ReportContent reportContent);
    boolean delete(UUID id);
    List<ReportContent> getByReportId(Report report);
}
