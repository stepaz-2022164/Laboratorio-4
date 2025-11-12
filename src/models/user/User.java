package models.user;

/**
 * Clase abstracta User - Clase base para usuarios del sistema
 */
public abstract class User {
    protected static int nextId = 1;
    protected int id;
    protected String username;
    protected String email;
    protected String password;
    protected String role;

    public User(String username, String email, String password) {
        this.id = nextId++;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public abstract boolean canPublish();
    public abstract boolean canDelete();

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public boolean checkPassword(String password) { return this.password.equals(password); }
}
