package controllers;

import models.auxiliary.Category;
import models.auxiliary.Tag;
import models.content.Article;
import models.content.Content;
import models.content.Image;
import models.content.Video;
import models.user.User;

import java.util.ArrayList;
import java.util.Scanner;

public class ContentController {
    private ArrayList<Content> contents;
    private ArrayList<Category> categories;
    private ArrayList<Tag> tags;

    public ContentController() {
        this.contents = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.tags = new ArrayList<>();
        initializeDefaultData();
    }

    private void initializeDefaultData() {
        // Categorías por defecto
        categories.add(new Category("Programación", "Contenido sobre desarrollo de software"));
        categories.add(new Category("Ciencias", "Contenido científico y académico"));
        categories.add(new Category("Artes", "Contenido artístico y creativo"));

        // Tags por defecto
        tags.add(new Tag("Java"));
        tags.add(new Tag("POO"));
        tags.add(new Tag("Tutorial"));
    }

    public Content createContent(String type, User user, Scanner scanner) {
        System.out.print("Título: ");
        String title = scanner.nextLine();
        String author = user.getUsername();

        Content content = null;

        switch(type.toLowerCase()) {
            case "articulo":
                System.out.print("Contenido del artículo: ");
                String articleContent = scanner.nextLine();
                content = new Article(title, author, articleContent);
                break;
            case "video":
                System.out.print("URL del video: ");
                String url = scanner.nextLine();
                System.out.print("Duración en segundos: ");
                int duration = Integer.parseInt(scanner.nextLine());
                content = new Video(title, author, url, duration);
                break;
            case "imagen":
                System.out.print("URL de la imagen: ");
                String imgUrl = scanner.nextLine();
                content = new Image(title, author, imgUrl);
                break;
        }

        if (content != null) {
            contents.add(content);
            System.out.println("\n✓ Contenido creado exitosamente (ID: " + content.getId() + ")");
        }

        return content;
    }

    public boolean editContent(int id, User user) {
        Content content = getContentById(id);
        if (content != null) {
            content.edit();
            return true;
        }
        return false;
    }

    public boolean deleteContent(int id, User user) {
        if (!user.canDelete()) {
            System.out.println("✗ No tienes permisos para eliminar contenido.");
            return false;
        }

        Content content = getContentById(id);
        if (content != null) {
            contents.remove(content);
            System.out.println("✓ Contenido eliminado exitosamente.");
            return true;
        }
        return false;
    }

    public boolean publishContent(int id, User user) {
        if (!user.canPublish()) {
            System.out.println("✗ No tienes permisos para publicar contenido.");
            return false;
        }

        Content content = getContentById(id);
        if (content != null) {
            content.publish();
            return true;
        }
        return false;
    }

    public ArrayList<Content> searchByType(String type) {
        ArrayList<Content> results = new ArrayList<>();
        for (Content c : contents) {
            if (c.getType().equalsIgnoreCase(type)) {
                results.add(c);
            }
        }
        return results;
    }

    public ArrayList<Content> searchByCategory(Category category) {
        ArrayList<Content> results = new ArrayList<>();
        for (Content c : contents) {
            if (c.getCategory() != null && c.getCategory().getId() == category.getId()) {
                results.add(c);
            }
        }
        return results;
    }

    public ArrayList<Content> getAllContents() { return contents; }

    public Content getContentById(int id) {
        for (Content c : contents) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    public ArrayList<Category> getCategories() { return categories; }
    public ArrayList<Tag> getTags() { return tags; }
}
