package models.content;

/**
 * Clase Article - Representa un artículo
 */
public class Article extends Content {
    private String content;
    private int wordCount;
    private int readTime;

    public Article(String title, String author, String content) {
        super(title, author);
        this.content = content;
        calculateReadTime();
    }

    @Override
    public String display() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== ARTÍCULO ===\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Título: ").append(title).append("\n");
        sb.append("Autor: ").append(author).append("\n");
        sb.append("Estado: ").append(status).append("\n");
        if (category != null) sb.append("Categoría: ").append(category.getName()).append("\n");
        sb.append("Palabras: ").append(wordCount).append("\n");
        sb.append("Tiempo de lectura: ").append(readTime).append(" min\n");
        sb.append("Contenido: ").append(content.substring(0, Math.min(100, content.length()))).append("...\n");
        return sb.toString();
    }

    @Override
    public void edit() {
        System.out.println("Editando artículo: " + title);
        lastModified = java.time.LocalDate.now().toString();
    }

    @Override
    public void publish() {
        super.publish();
        System.out.println("Artículo listo para lectura.");
    }

    private void calculateReadTime() {
        this.wordCount = content.split("\\s+").length;
        this.readTime = Math.max(1, wordCount / 200); // 200 palabras por minuto
    }

    public String getContent() { return content; }
    public void setContent(String content) {
        this.content = content;
        calculateReadTime();
        this.lastModified = java.time.LocalDate.now().toString();
    }
    public int getWordCount() { return wordCount; }
}
