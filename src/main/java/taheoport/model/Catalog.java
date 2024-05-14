package taheoport.model;

import java.util.LinkedList;

/**
 *This class encapsulates base points coordinates
 *@author Andrew Nizovkin
 *Copyright Nizovkin A.V. 2022
 */
    public class Catalog extends LinkedList <CatalogPoint>{
    private String absoluteCatalogPath;

    /**
     * Returns a Catalog item by index
     * @param index int index of catalog
     * @return CatalogPoint
     */
    public CatalogPoint getCatalogPoint(int index) {
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

