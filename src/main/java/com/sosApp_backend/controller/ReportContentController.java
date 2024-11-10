package com.sosApp_backend.controller;

import com.sosApp_backend.implement.ReportServiceImplements;
import com.sosApp_backend.model.Report;
import com.sosApp_backend.model.ReportContent;
import com.sosApp_backend.service.ReportContentService;
import com.sosApp_backend.utils.UTPBinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/report-content")
@CrossOrigin("*")
public class ReportContentController {

    @Autowired
    private ReportContentService reportContentService;

    @Autowired
    private ReportServiceImplements reportServiceImplements;

    private static final String UPLOAD_DIR = "/tmp"; // Directorio donde se almacenarán los archivos

    @PostMapping("/create")
    public ResponseEntity<ReportContent> createReportContent(@RequestBody ReportContent reportContent) {
        ReportContent createdReportContent = reportContentService.createReportContent(reportContent);
        return new ResponseEntity<>(createdReportContent, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReportContent>> getAllReportContents() {
        List<ReportContent> reportContents = reportContentService.getAll();
        return new ResponseEntity<>(reportContents, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ReportContent> getReportContentById(@PathVariable UUID id) {
        ReportContent reportContent = reportContentService.getById(id);
        if (reportContent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reportContent, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ReportContent> updateReportContent(@RequestBody ReportContent reportContent) {
        ReportContent updatedReportContent = reportContentService.update(reportContent);
        if (updatedReportContent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedReportContent, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReportContent(@PathVariable UUID id) {
        boolean deleted = reportContentService.delete(id);
        if (!deleted) {
            return new ResponseEntity<>("Report content not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Report content deleted successfully", HttpStatus.OK);
    }

    /**
     * Método para subir y guardar archivos multimedia en el sistema y registrar la información en la base de datos.
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadMediaFiles(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("reportId") UUID reportId
    ) {
        // Validar si el reporte existe
        Report report = reportServiceImplements.getById(reportId);
        if (report == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Reporte con ID " + reportId + " no encontrado");
        }

        for (MultipartFile file : files) {
            try {
                // Validar si el archivo no está vacío
                if (file.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("El archivo " + file.getOriginalFilename() + " está vacío");
                }

                // Generar la ruta para guardar el archivo
                Path path = Paths.get(UPLOAD_DIR, file.getOriginalFilename());

                // Guardar el archivo utilizando UTPBinary
                UTPBinary.writeFile(file.getBytes(), path.toString());

                // Crear una instancia de ReportContent
                ReportContent reportContent = new ReportContent();
                reportContent.setReport(report);
                reportContent.setContent(file.getOriginalFilename());
                reportContent.setContentType(file.getContentType());

                // Guardar la información del archivo en la base de datos
                reportContentService.createReportContent(reportContent);

            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al guardar el archivo: " + file.getOriginalFilename());
            }
        }
        return ResponseEntity.ok("Archivos subidos y registrados con éxito");
    }
}
