package controllers;

import models.user.Administrator;
import models.user.Editor;
import models.user.User;

import java.util.ArrayList;

/**
 * Clase UserController - Gestiona usuarios y autenticación
 */

public class UserController {
    private ArrayList<User> users;
    private User authenticatedUser;

    public UserController() {
        this.users = new ArrayList<>();
        initializeDefaultUsers();
    }

    private void initializeDefaultUsers() {
        users.add(new Administrator("admin", "admin@ega.edu.gt", "admin123"));
        users.add(new Editor("editor1", "editor@ega.edu.gt", "editor123"));
    }

    public User authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.checkPassword(password)) {
                authenticatedUser = user;
                return user;
            }
        }
        return null;
    }

    public boolean validatePermission(User user, String action) {
        switch(action) {
            case "publish": return user.canPublish();
            case "delete": return user.canDelete();
            default: return true;
        }
    }

    public boolean registerUser(User user) {
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                System.out.println("✗ El nombre de usuario ya existe.");
                return false;
            }
            if (u.getEmail().equals(user.getEmail())) {
                System.out.println("✗ El correo electrónico ya está registrado.");
                return false;
            }
        }
        users.add(user);
        System.out.println("✓ Usuario registrado exitosamente.");
        return true;
    }

    public void listAllUsers() {
        System.out.println("\n--- LISTA DE USUARIOS ---");
        for (User u : users) {
            System.out.println("ID: " + u.getId() + " | Usuario: " + u.getUsername() +
                    " | Rol: " + u.getRole() + " | Email: " + u.getEmail());
        }
    }

    public User getAuthenticatedUser() { return authenticatedUser; }
    public ArrayList<User> getAllUsers() { return users; }
}

