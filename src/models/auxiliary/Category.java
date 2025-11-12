package models.auxiliary;

/**
 * Clase Category - Representa una categoría de contenido
 */
public class Category {
    private static int nextId = 1;
    private int id;
    private String name;
    private String description;

    public Category(String name, String description) {
        this.id = nextId++;
        this.name = name;
        this.description = description;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return name + " - " + description;
    }
}
