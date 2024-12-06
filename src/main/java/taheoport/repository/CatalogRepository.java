package taheoport.repository;

import taheoport.model.CatalogPoint;

import java.util.LinkedList;

/**
 *This class encapsulates base points coordinates
 *@author Andrew Nizovkin
 *Copyright Nizovkin A.V. 2022
 */
    public class CatalogRepository extends LinkedList <CatalogPoint>{
    private String absoluteCatalogPath;

    /**
     * Constructs an empty list.
     */
    public CatalogRepository() {
        super();
    }

    /**
     * Returns a Catalog item by index
     * @param index int index of catalog
     * @return CatalogPoint
     */
    public CatalogPoint findById(int index) {
        return this.get(index);
    }

    /**
     * Returns a Catalog size
     * @return this.size
     */
    public int getSizeCatalog() {
            return this.size();
    }

    /**
     * Gets absolute path of file with basic point catalog
     * @return string absoluteCatalogPath
     */
    public String getAbsoluteCatalogPath() {
        return absoluteCatalogPath;
    }

    /**
     * Sets absoluteCatalogPath
     * @param absoluteCatalogPath String
     */
    public void setAbsoluteCatalogPath(String absoluteCatalogPath) {
        this.absoluteCatalogPath = absoluteCatalogPath;
    }
}

