import controllers.CMSController;
import models.user.Administrator;
import models.user.Editor;
import models.user.User;
import view.CMSView;

/**
 * Clase Main - Punto de entrada del sistema
 * @author Sergio Eduardo Tepaz Vela - 25787
 * Sistema de Gestión de Contenidos (CMS) para Estudio de Grabación Audiovisual
 */
public class Main {
    private static CMSController controller;
    private static CMSView view;

    public static void main(String[] args) {
        initializeSystem();
        runApplication();
    }

    private static void initializeSystem() {
        controller = new CMSController();
        view = new CMSView();
        System.out.println("Sistema CMS inicializado correctamente.");
    }

    private static void runApplication() {
        boolean running = true;

        while (running) {
            view.showMainMenu();
            int option = view.getIntInput("");

            switch (option) {
                case 1:
                    handleLogin();
                    break;
                case 2:
                    handleUserRegistration();
                    break;
                case 3:
                    System.out.println("¡Gracias por usar el sistema CMS!");
                    running = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void handleUserRegistration() {
        System.out.println("\n--- REGISTRO DE NUEVO USUARIO ---");

        String username = view.getInput("Nombre de usuario: ");
        String email = view.getInput("Correo electrónico: ");
        String password = view.getInput("Contraseña: ");

        System.out.println("\nTipo de usuario:");
        System.out.println("1. Administrador");
        System.out.println("2. Editor");
        int userType = view.getIntInput("Seleccione: ");

        User newUser = null;

        switch (userType) {
            case 1:
                newUser = new Administrator(username, email, password);
                break;
            case 2:
                newUser = new Editor(username, email, password);
                break;
            default:
                System.out.println("✗ Tipo de usuario inválido.");
                return;
        }

        if (newUser != null) {
            controller.getUserController().registerUser(newUser);
        }
    }

    private static void handleLogin() {
        String username = view.getInput("Usuario: ");
        String password = view.getInput("Contraseña: ");

        if (controller.login(username, password)) {
            System.out.println("\n✓ Inicio de sesión exitoso!");
            handleContentManagement();
        } else {
            System.out.println("\n✗ Credenciales incorrectas.");
        }
    }

    private static void handleContentManagement() {
        boolean inSession = true;
        User currentUser = controller.getCurrentUser();

        while (inSession) {
            view.showContentMenu(currentUser);
            int option = view.getIntInput("");

            switch (option) {
                case 1:
                    handleCreateContent();
                    break;
                case 2:
                    handleListContents();
                    break;
                case 3:
                    handleSearchContent();
                    break;
                case 4:
                    handleEditContent();
                    break;
                case 5:
                    if (currentUser.canPublish()) handlePublishContent();
                    break;
                case 6:
                    if (currentUser.canDelete()) handleDeleteContent();
                    break;
                case 7:
                    controller.generateReport();
                    break;
                case 8:
                    if (currentUser instanceof Administrator) handleUserManagement();  // ← NUEVA LÍNEA
                    else inSession = handleLogout();                                    // ← NUEVA LÍNEA
                    break;
                case 9:                              // ← CAMBIO DE 8 A 9
                    inSession = handleLogout();
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void handleCreateContent() {
        System.out.println("\n--- CREAR CONTENIDO ---");
        System.out.println("1. Artículo");
        System.out.println("2. Video");
        System.out.println("3. Imagen");
        int type = view.getIntInput("Seleccione tipo: ");

        String typeName = "";
        switch (type) {
            case 1: typeName = "articulo"; break;
            case 2: typeName = "video"; break;
            case 3: typeName = "imagen"; break;
            default:
                System.out.println("Tipo inválido.");
                return;
        }

        controller.getContentController().createContent(
                typeName,
                controller.getCurrentUser(),
                view.getScanner()
        );
    }

    private static void handleListContents() {
        view.displayContentList(controller.getContentController().getAllContents());
    }

    private static void handleSearchContent() {
        System.out.println("\n--- BUSCAR CONTENIDO ---");
        System.out.println("1. Por tipo");
        System.out.println("2. Mostrar todos");
        int option = view.getIntInput("Opción: ");

        if (option == 1) {
            String type = view.getInput("Tipo (Article/Video/Image): ");
            view.displayContentList(controller.getContentController().searchByType(type));
        } else {
            handleListContents();
        }
    }

    private static void handleEditContent() {
        int id = view.getIntInput("ID del contenido a editar: ");
        controller.getContentController().editContent(id, controller.getCurrentUser());
    }

    private static void handlePublishContent() {
        int id = view.getIntInput("ID del contenido a publicar: ");
        controller.getContentController().publishContent(id, controller.getCurrentUser());
    }

    private static void handleDeleteContent() {
        int id = view.getIntInput("ID del contenido a eliminar: ");
        controller.getContentController().deleteContent(id, controller.getCurrentUser());
    }

    private static boolean handleLogout() {
        controller.logout();
        System.out.println("Sesión cerrada.");
        return false;
    }

    private static void handleUserManagement() {
        boolean inUserMenu = true;

        while (inUserMenu) {
            view.showUserManagementMenu();
            int option = view.getIntInput("");

            switch (option) {
                case 1:
                    controller.getUserController().listAllUsers();
                    break;
                case 2:
                    handleUserRegistration();
                    break;
                case 3:
                    inUserMenu = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}