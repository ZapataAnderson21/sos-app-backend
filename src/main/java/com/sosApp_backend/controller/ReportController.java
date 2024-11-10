package com.sosApp_backend.controller;

import com.sosApp_backend.model.Report;
import com.sosApp_backend.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/report")
@CrossOrigin("*")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createReport(@RequestBody Report report) {
        Map<String, Object> response = new HashMap<>();

        if (report.getContent() == null || report.getContent().isEmpty()) {
            response.put("status", false);
            response.put("message", "El contenido del reporte no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (report.getUser() == null || report.getUser().getUser_id() == null) {
            response.put("status", false);
            response.put("message", "El usuario asociado al reporte no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (report.getCommunity() == null || report.getCommunity().getCommunity_id() == null) {
            response.put("status", false);
            response.put("message", "La comunidad asociada al reporte no puede estar vacía");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Report createdReport = reportService.create(report);
        response.put("status", true);
        response.put("message", "Reporte creado correctamente");
        response.put("data", createdReport);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> reports = reportService.getAll();
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Map<String, Object>> getReportById(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID del reporte no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Report report = reportService.getById(id);
        if (report == null) {
            response.put("status", false);
            response.put("message", "No se encontró el reporte con el ID proporcionado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("message", "Reporte encontrado");
        response.put("data", report);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateReport(@PathVariable UUID id, @RequestBody Report report) {
        Map<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID del reporte no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (report.getContent() == null || report.getContent().isEmpty()) {
            response.put("status", false);
            response.put("message", "El contenido del reporte no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Report updatedReport = reportService.update(id, report);
        if (updatedReport == null) {
            response.put("status", false);
            response.put("message", "No se pudo actualizar el reporte. ID no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("message", "Reporte actualizado correctamente");
        response.put("data", updatedReport);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteReport(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();

        if (id == null) {
            response.put("status", false);
            response.put("message", "El ID del reporte no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        boolean deleted = reportService.delete(id);
        if (!deleted) {
            response.put("status", false);
            response.put("message", "No se pudo eliminar el reporte. ID no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("message", "Reporte eliminado correctamente");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/community/{communityId}")
    public ResponseEntity<Map<String, Object>> getReportsByCommunityId(@PathVariable UUID communityId) {
        Map<String, Object> response = new HashMap<>();

        if (communityId == null) {
            response.put("status", false);
            response.put("message", "El ID de la comunidad no puede estar vacío");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        List<Report> reports = reportService.getByCommunityId(communityId);
        if (reports == null || reports.isEmpty()) {
            response.put("status", false);
            response.put("message", "No se encontraron reportes para la comunidad con el ID proporcionado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("status", true);
        response.put("reports", reports);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
