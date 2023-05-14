package taheoport;

/**
 * This class defines a point by the 3D coordinates
 * and height of the measurements
 * @author Andrey Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class PaintPoint {
    private String name = "Not";
    private String x = "0.000";
    private String y = "0.000";
    private String z = "0.000";
    private String xOr = "0.000";
    private String yOr = "0.000";
    private boolean status = false;
    private int xPaint = 0;
    private int yPaint = 0;
    private int xOrPaint = 0;
    private int yOrPaint = 0;

    /**
     * Constructor
     * @param name String points name
     * @param x Coordinate X, m.
     * @param y Coordinate Y, m.
     * @param z Coordinate Z, m.
     */
    public PaintPoint(String name, String x, String y, String z) {
       if (!(x == null) & !(y == null) & !(z == null)) {
           this.name = name;
           this.x = x.equals("Not") ? "0.000" : new DataHandler(x).format(3).getStr();
           this.y = y.equals("Not") ? "0.000" : new DataHandler(y).format(3).getStr();
           this.z = z.equals("Not") ? "0.000" : new DataHandler(z).format(3).getStr();
       }
    }

    // Setters

    public void setxPaint(int xPaint) {
        this.xPaint = xPaint;
    }

    public void setyPaint(int yPaint) {
        this.yPaint = yPaint;
    }

    public void setxOrPaint(int xOrPaint) {
        this.xOrPaint = xOrPaint;
    }

    public void setyOrPaint(int yOrPaint) {
        this.yOrPaint = yOrPaint;
    }

    public void setxOr(String xOr) {
        this.xOr = new DataHandler(xOr).format(3).getStr();
    }

    public void setyOr(String yOr) {
        this.yOr = new DataHandler(yOr).format(3).getStr();
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

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


    public int getxPaint() {
        return xPaint;
    }

    public double getxDbl() {
        return Double.parseDouble(x);
    }


    public int getyPaint() {
        return yPaint;
    }

    public double getyDbl() {
        return Double.parseDouble(y);
    }

    public int getxOrPaint() {
        return xOrPaint;
    }

    public double getxOrDbl() {
        return Double.parseDouble(xOr);
    }

    public int getyOrPaint() {
        return yOrPaint;
    }

    public  Double getyOrDbl() {
        return Double.parseDouble(yOr);
    }

    public String getxOr() {
        return xOr;
    }

    public String getyOr() {
        return yOr;
    }

    public boolean getStatus() {
        return status;
    }

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
