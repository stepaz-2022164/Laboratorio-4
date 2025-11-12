package interfaces;

/**
 * Interfaz para contenidos que pueden ser buscados
 */
public interface ISearchable {
    boolean matchesKeyword(String keyword);
    String[] getSearchableFields();
}
