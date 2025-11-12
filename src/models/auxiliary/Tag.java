package models.auxiliary;


/**
 * Clase Tag - Representa una etiqueta para contenido
 */
public class Tag {
    private static int nextId = 1;
    private int id;
    private String name;

    public Tag(String name) {
        this.id = nextId++;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return name;
    }
}
