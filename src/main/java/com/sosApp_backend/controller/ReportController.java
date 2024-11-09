package com.sosApp_backend.controller;

import com.sosApp_backend.model.Report;
import com.sosApp_backend.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reports")
@CrossOrigin("*")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/create")
    public Report createReport(@RequestBody Report report) {
        // Crear un nuevo reporte
        return reportService.create(report);
    }

    @GetMapping("/all")
    public List<Report> getAllReports() {
        // Obtener todos los reportes
        return reportService.getAll();
    }

    @GetMapping("/id/{id}")
    public Report getReportById(@PathVariable UUID id) {
        // Obtener un reporte por su ID
        return reportService.getById(id);
    }

    @PutMapping("/update/{id}")
    public Report updateReport(@PathVariable UUID id, @RequestBody Report report) {
        // Actualizar un reporte por su ID
        return reportService.update(id, report);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteReport(@PathVariable UUID id) {
        // Eliminar un reporte por su ID
        reportService.delete(id);
    }

    @GetMapping("/community/{communityId}")
    public List<Report> getReportsByCommunityId(@PathVariable UUID communityId) {
        // Obtener todos los reportes asociados a una comunidad específica
        return reportService.getByCommunityId(communityId);
    }
}
