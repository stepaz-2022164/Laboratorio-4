package models.auxiliary;

import models.content.Content;

import java.util.ArrayList;

public class Report {
    private String generationDate;
    private ArrayList<Content> contents;

    public Report(ArrayList<Content> contents) {
        this.contents = contents;
        this.generationDate = java.time.LocalDate.now().toString();
    }

    public String generateSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n╔════════════════════════════════════════╗\n");
        sb.append("║       REPORTE DEL SISTEMA CMS          ║\n");
        sb.append("╚════════════════════════════════════════╝\n\n");
        sb.append("Fecha: ").append(generationDate).append("\n\n");

        sb.append("Total de contenidos: ").append(getTotalContents()).append("\n");
        sb.append("Contenidos publicados: ").append(getPublishedCount()).append("\n\n");

        sb.append("--- Por Tipo ---\n");
        sb.append("Artículos: ").append(getContentsByType("Article")).append("\n");
        sb.append("Videos: ").append(getContentsByType("Video")).append("\n");
        sb.append("Imágenes: ").append(getContentsByType("Image")).append("\n\n");

        return sb.toString();
    }

    public int getContentsByType(String type) {
        return (int) contents.stream().filter(c -> c.getType().equals(type)).count();
    }

    public int getPublishedCount() {
        return (int) contents.stream().filter(Content::isPublished).count();
    }

    public int getTotalContents() {
        return contents.size();
    }

    public String exportReport() {
        return generateSummary();
    }
}

