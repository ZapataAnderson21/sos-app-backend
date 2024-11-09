package com.sosApp_backend.service;

import com.sosApp_backend.model.Report;
import java.util.List;
import java.util.UUID;

public interface ReportService {
    Report create(Report report);
    List<Report> getAll();
    Report getById(UUID id);
    Report update(UUID id, Report report);
    void delete(UUID id);
    List<Report> getByCommunityId(UUID communityId);
}
