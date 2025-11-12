package models.content;
/**
 * Clase Video - Representa un video
 */

public class Video extends Content {
    private String url;
    private int duration; // en segundos
    private String resolution;
    private String format;

    public Video(String title, String author, String url, int duration) {
        super(title, author);
        this.url = url;
        this.duration = duration;
        this.resolution = "1080p";
        this.format = "mp4";
    }

    @Override
    public String display() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== VIDEO ===\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Título: ").append(title).append("\n");
        sb.append("Autor: ").append(author).append("\n");
        sb.append("Estado: ").append(status).append("\n");
        if (category != null) sb.append("Categoría: ").append(category.getName()).append("\n");
        sb.append("URL: ").append(url).append("\n");
        sb.append("Duración: ").append(duration / 60).append(" min ").append(duration % 60).append(" seg\n");
        sb.append("Resolución: ").append(resolution).append("\n");
        sb.append("Formato: ").append(format).append("\n");
        return sb.toString();
    }

    @Override
    public void edit() {
        System.out.println("Editando video: " + title);
        lastModified = java.time.LocalDate.now().toString();
    }

    @Override
    public void publish() {
        super.publish();
        System.out.println("Video disponible para visualización.");
    }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public int getDuration() { return duration; }
    public String getResolution() { return resolution; }
    public void setResolution(String resolution) { this.resolution = resolution; }
}
