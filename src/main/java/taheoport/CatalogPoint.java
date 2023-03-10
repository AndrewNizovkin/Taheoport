    package taheoport;
/**
 * This class defines a point by the 3D coordinates
 * and height of the measurements
 */
    public class CatalogPoint {
    private String name = "Not";   // Point name
    private String x = "0.000";    // x coordinate of the point
    private String y = "0.000";    // y coordinate of the point
    private String z = "0.000";    // z coordinate of the point

    /**
     * Constructor
     * @param name point name
     * @param x x-coordinate of point
     * @param y y-coordinate of point
     * @param z z-coordinate of point
     */
    public CatalogPoint(String name, String x, String y, String z) {
        this.name = name;
        this.x = x.equals("Not") ? "0.000" : new DataHandler(x).format(3).getStr();
        this.y = y.equals("Not") ? "0.000" : new DataHandler(y).format(3).getStr();
        this.z = z.equals("Not") ? "0.000" : new DataHandler(z).format(3).getStr();
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setX(String x) {
        this.x = x;
    }

    public void setY(String y) {
        this.y = y;
    }

    public void setZ(String z) {
        this.z = z;
    }


    public String getName() {
        return name;
    }

    // Getters
    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public String getZ() {
        return z;
    }
}
