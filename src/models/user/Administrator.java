package models.user;
/**
 * Clase Administrator - Usuario con permisos completos
 */

public class Administrator extends User {
    private int adminLevel;

    public Administrator(String username, String email, String password) {
        super(username, email, password);
        this.role = "Administrador";
        this.adminLevel = 1;
    }

    @Override
    public boolean canPublish() { return true; }

    @Override
    public boolean canDelete() { return true; }

    public int getAdminLevel() { return adminLevel; }
}
