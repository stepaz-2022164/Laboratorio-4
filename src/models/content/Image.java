package models.content;
/**
 * Clase Image - Representa una imagen
 * */

public class Image extends Content {
    private String url;
    private String dimensions;
    private String format;
    private double fileSize; // en MB

    public Image(String title, String author, String url) {
        super(title, author);
        this.url = url;
        this.dimensions = "1920x1080";
        this.format = "jpg";
        this.fileSize = 2.5;
    }

    @Override
    public String display() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== IMAGEN ===\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Título: ").append(title).append("\n");
        sb.append("Autor: ").append(author).append("\n");
        sb.append("Estado: ").append(status).append("\n");
        if (category != null) sb.append("Categoría: ").append(category.getName()).append("\n");
        sb.append("URL: ").append(url).append("\n");
        sb.append("Dimensiones: ").append(dimensions).append("\n");
        sb.append("Formato: ").append(format).append("\n");
        sb.append("Tamaño: ").append(fileSize).append(" MB\n");
        return sb.toString();
    }

    @Override
    public void edit() {
        System.out.println("Editando imagen: " + title);
        lastModified = java.time.LocalDate.now().toString();
    }

    @Override
    public void publish() {
        super.publish();
        System.out.println("Imagen disponible en galería.");
    }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getDimensions() { return dimensions; }
    public void setDimensions(String dimensions) { this.dimensions = dimensions; }
    public String getFormat() { return format; }
}
