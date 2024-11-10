package com.sosApp_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "report_content")
public class ReportContent {
    @Id
    @ManyToOne
    @JoinColumn(name = "report_id", referencedColumnName = "report_id", nullable = false)
    private Report report;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "content_type", columnDefinition = "TEXT", nullable = false)
    private String content_type;

    public ReportContent() {}

    public ReportContent(Report report, String content, String content_type) {
        this.report = report;
        this.content = content;
        this.content_type = content_type;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }
}
