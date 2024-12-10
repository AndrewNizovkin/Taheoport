package taheoport.service;

import taheoport.model.CatalogPoint;
import taheoport.repository.CatalogRepository;

/**
 * This interface defines methods for working with the catalog of base points
 */
public interface CatalogService {

    /**
     * Checks catalog is Empty
     * @return result
     */
    boolean isEmpty();

    /**
     * Gets catalogRepository
     * @return catalogRepository
     */
    CatalogRepository getCatalogRepository();

    /**
     * Gets absoluteCatalogPath
     * @return String
     */
    String getAbsoluteCatalogPath();

    /**
     * Downloads catalog from list
     */
    void importCatalog();

    /**
     * Updates base points with current catalog
     * @param target 0: updates surveyProject; 1: updates polygonProject
     */
    void updateBasePoints(int target);

    /**
     * Sets the index of the selected item
     * @param choice int
     */
    void setChoice(int choice);

    /**
     * Gets the index of the selected item
     */
    int getChoice();

    /**
     * Gets catalogPoint by id
     * @return CatalogPoint
     */
    CatalogPoint findById(int id);

}
