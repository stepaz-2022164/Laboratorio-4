package interfaces;

/**
 * Interfaz para contenidos que pueden ser publicados
 */
public interface IPublishable {
    void publish();
    void unpublish();
    boolean isPublished();
}
