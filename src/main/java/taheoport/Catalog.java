package taheoport;

import java.util.LinkedList;

/**
 *This class encapsulates base points coordinates
 *@author Andrey Nizovkin
 *Copyright Nizovkin A.V. 2022
 */
    public class Catalog extends LinkedList <CatalogPoint>{
    private String absoluteCatalogPath;

    /**
     * Downloads catalog from list
     * @param l LinkedList
     */
    public Catalog loadCatalogList(LinkedList<String> l) {
        if (l != null) {
            this.absoluteCatalogPath = l.removeFirst();
            int i = 0;
            while (l.size() > 0) {
                String s = new DataHandler(l.removeFirst()).compress(" ").getStr();
                String[] array = s.split(" ");
                if (array.length >= 4) {
                    CatalogPoint cPoint = new CatalogPoint(array[0],
                            new DataHandler(array[1]).commaToPoint().format(3).getStr(),
                            new DataHandler(array[2]).commaToPoint().format(3).getStr(),
                            new DataHandler(array[3]).commaToPoint().format(3).getStr());
                    add(cPoint);
                }
                i++;
            }
        }
        return this;
    }

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
}

