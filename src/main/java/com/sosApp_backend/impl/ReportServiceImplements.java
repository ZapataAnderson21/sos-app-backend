package com.sosApp_backend.impl;

import com.sosApp_backend.model.Report;
import com.sosApp_backend.repository.ReportRepository;
import com.sosApp_backend.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReportServiceImplements implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public Report create(Report report) {
        // Guarda un nuevo reporte en la base de datos
        return reportRepository.save(report);
    }

    @Override
    public List<Report> getAll() {
        // Retorna todos los reportes
        return reportRepository.findAll();
    }

    @Override
    public Report getById(UUID id) {
        // Busca un reporte por su ID
        return reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found with ID: " + id));
    }

    @Override
    public Report update(UUID id, Report report) {
        // Actualiza un reporte si existe
        Report existingReport = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found with ID: " + id));
        existingReport.setCommunity(report.getCommunity());
        existingReport.setUser(report.getUser());
        existingReport.setContent(report.getContent());
        existingReport.setCreated_at(report.getCreated_at());
        return reportRepository.save(existingReport);
    }

    @Override
    public void delete(UUID id) {
        // Elimina un reporte por su ID
        if (!reportRepository.existsById(id)) {
            throw new RuntimeException("Report not found with ID: " + id);
        }
        reportRepository.deleteById(id);
    }

    @Override
    public List<Report> getByCommunityId(UUID communityId) {
        // Retorna todos los reportes asociados a una comunidad específica
        return reportRepository.findByCommunityId(communityId);
    }
}
