package models.content;

import interfaces.IPublishable;
import interfaces.ISearchable;
import models.auxiliary.Category;
import models.auxiliary.Tag;

import java.util.ArrayList;


/**
 * Clase abstracta Content - Clase base para todos los tipos de contenido
 */
public abstract class Content implements IPublishable, ISearchable {
    protected static int nextId = 1;
    protected int id;
    protected String title;
    protected String author;
    protected String creationDate;
    protected String lastModified;
    protected String status; // "borrador", "publicado", "archivado"
    protected Category category;
    protected ArrayList<Tag> tags;

    public Content(String title, String author) {
        this.id = nextId++;
        this.title = title;
        this.author = author;
        this.creationDate = java.time.LocalDate.now().toString();
        this.lastModified = this.creationDate;
        this.status = "borrador";
        this.tags = new ArrayList<>();
    }

    // Métodos abstractos que deben implementar las subclases
    public abstract String display();
    public abstract void edit();

    // Implementación de IPublishable
    @Override
    public void publish() {
        this.status = "publicado";
        this.lastModified = java.time.LocalDate.now().toString();
        System.out.println("Contenido '" + title + "' publicado exitosamente.");
    }

    @Override
    public void unpublish() {
        this.status = "borrador";
        this.lastModified = java.time.LocalDate.now().toString();
        System.out.println("Contenido '" + title + "' despublicado.");
    }

    @Override
    public boolean isPublished() {
        return status.equals("publicado");
    }

    // Implementación de ISearchable
    @Override
    public boolean matchesKeyword(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return title.toLowerCase().contains(lowerKeyword) ||
                author.toLowerCase().contains(lowerKeyword);
    }

    @Override
    public String[] getSearchableFields() {
        return new String[]{title, author, status};
    }

    // Getters y setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) {
        this.title = title;
        this.lastModified = java.time.LocalDate.now().toString();
    }
    public String getAuthor() { return author; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public void addTag(Tag tag) { this.tags.add(tag); }
    public ArrayList<Tag> getTags() { return tags; }
    public String getCreationDate() { return creationDate; }

    public String getType() {
        return this.getClass().getSimpleName();
    }
}
