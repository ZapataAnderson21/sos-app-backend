package com.sosApp_backend.implement;

import com.sosApp_backend.model.Post;
import com.sosApp_backend.model.Report;
import com.sosApp_backend.model.ReportContent;
import com.sosApp_backend.repository.ReportContentRepository;
import com.sosApp_backend.service.ReportContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReportContentImplements implements ReportContentService {
    @Autowired
    private ReportContentRepository reportContentRepository;

    @Override
    public ReportContent createReportContent(ReportContent reportContent) {
        return reportContentRepository.save(reportContent);
    }

    @Override
    public List<ReportContent> getAll() {
        return reportContentRepository.findAll();
    }

    @Override
    public ReportContent getById(UUID id) {
        Optional<ReportContent> reportContent = reportContentRepository.findById(id);
        return reportContent.orElse(null);
    }

    @Override
    public ReportContent update(ReportContent reportContent) {
        if (reportContentRepository.existsById(reportContent.getReport().getReport_id())) {
            return reportContentRepository.save(reportContent);
        }
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        if (reportContentRepository.existsById(id)) {
            reportContentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<ReportContent> getByReportId(Report report) {
        return reportContentRepository.findByReportId(report.getReport_id());
    }
}
