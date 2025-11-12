package view;

import models.content.Content;
import models.user.Administrator;
import models.user.User;

import java.util.ArrayList;
import java.util.Scanner;

public class CMSView {
    private Scanner scanner;

    public CMSView() {
        this.scanner = new Scanner(System.in);
    }

    public void showMainMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║    SISTEMA CMS - Estudio EGA           ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Registrar nuevo usuario");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public void showContentMenu(User user) {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         MENÚ DE CONTENIDOS             ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Usuario: " + user.getUsername() + " (" + user.getRole() + ")");
        System.out.println("\n1. Crear contenido");
        System.out.println("2. Listar todos los contenidos");
        System.out.println("3. Buscar contenido");
        System.out.println("4. Editar contenido");
        if (user.canPublish()) System.out.println("5. Publicar contenido");
        if (user.canDelete()) System.out.println("6. Eliminar contenido");
        System.out.println("7. Generar reporte");
        if (user instanceof Administrator) System.out.println("8. Gestionar usuarios");
        System.out.println("9. Cerrar sesión");
        System.out.print("Seleccione una opción: ");
    }

    public void showUserManagementMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║       GESTIÓN DE USUARIOS              ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("1. Listar todos los usuarios");
        System.out.println("2. Registrar nuevo usuario");
        System.out.println("3. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
    }

    public void displayContentList(ArrayList<Content> contents) {
        System.out.println("\n--- LISTA DE CONTENIDOS ---");
        if (contents.isEmpty()) {
            System.out.println("No hay contenidos disponibles.");
        } else {
            for (Content c : contents) {
                System.out.println(c.display());
            }
        }
    }

    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int getIntInput(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public Scanner getScanner() { return scanner; }
}