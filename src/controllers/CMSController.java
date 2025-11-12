package controllers;

import models.auxiliary.Report;
import models.user.User;

/**
 * Clase CMSController - Controlador principal del sistema
 */

public class CMSController {
    private ContentController contentController;
    private UserController userController;
    private User currentUser;

    public CMSController() {
        this.contentController = new ContentController();
        this.userController = new UserController();
    }

    public boolean login(String username, String password) {
        currentUser = userController.authenticate(username, password);
        return currentUser != null;
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() { return currentUser; }
    public ContentController getContentController() { return contentController; }
    public UserController getUserController() { return userController; }

    public void generateReport() {
        Report report = new Report(contentController.getAllContents());
        System.out.println(report.exportReport());
    }
}
