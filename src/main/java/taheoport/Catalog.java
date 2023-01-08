package taheoport;
import java.util.LinkedList;
public class Catalog extends LinkedList <CatalogPoint>{
//    private LinkedList <CatalogPoint> llCatalog;
    private String absoluteCatalogPath;

    /**
     * Constructor
     * @param l LinkedList
     */
    public Catalog loadCatalogList(LinkedList<String> l) {
//        LinkedList <String> l = new MyChooser().readTextFile(path, "kat", "Выбор каталога координат");
        if (l != null) {
            this.absoluteCatalogPath = l.removeFirst();
//            llCatalog = new LinkedList<CatalogPoint>();
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

    public Catalog addCatalogPoint (String name, String x, String y, String z) {


        return this;
    }

    /**
     * Returns a Catalog item by index
     * @param index
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

    public String getAbsoluteCatalogPath() {
        return absoluteCatalogPath;
    }
}

