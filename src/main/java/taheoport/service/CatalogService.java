package taheoport.service;

import taheoport.model.Catalog;

import java.util.List;

/**
 * This interface defines methods for working with the catalog of base points
 */
public interface CatalogService {

    /**
     * Downloads catalog from list
     * @param list LinkedList
     */
    Catalog loadCatalogList(List<String> list);

    /**
     * Updates base points with current catalog
     * @param target 0: updates surveyProject; 1: updates polygonProject
     */
    void updateBasePoints(int target);

}
