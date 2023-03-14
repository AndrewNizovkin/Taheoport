package taheoport;

/**
 * This class encapsulates fields of PolygonStation
 * @author Andrey Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class PolygonStation {
    private String name = "noname";
    private Boolean status = false;
    private String x = "Not";
    private String y = "Not";
    private String z = "Not";
    private String dZ = "0.000";
    private String hor = "0.0000";
    private String line = "0.000";
    private double dX = 0.0;
    private double dy = 0.0;
    private double ddX = 0.0;
    private double ddY = 0.0;
    private double ddZ = 0.0;
    private double ddHor = 0.0;
    private double direction = 0.0;

    /**
     * Setters
     * @param name string name
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public void setdZ(String dZ) {
        DataHandler dataHandler = new DataHandler(dZ);
        if (dataHandler.isNumber()) {
            this.dZ = dataHandler.format(3).getStr();
        }
    }

    public void setHor(String hor) {
        DataHandler dataHandler = new DataHandler(hor);
        if (dataHandler.isPositiveNumber()) {
            this.hor = dataHandler.format(4).getStr();
        }
    }

    public void setLine(String line) {
        DataHandler dataHandler = new DataHandler(line);
        if (dataHandler.isNumber()) {
            this.line = dataHandler.format(3).getStr();
        }
    }

    public void setDX(double dX) {
        this.dX = dX;
    }

    public void setDY(double dy) {
        this.dy = dy;
    }

    public void setDDX(double ddX) {
        this.ddX = ddX;
    }

    public void setDDY(double ddY) {
        this.ddY = ddY;
    }

    public void setDDZ(double ddZ) {
        this.ddZ = ddZ;
    }

    public void setDDHor(double ddHor) {
        this.ddHor = ddHor;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    /**
     * Getters
     */
    public String getName() {
        return name;
    }

    public Boolean getStatus() {
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

    public String getDZ() {
        return dZ;
    }

    public String getHor() {
        return hor;
    }

    public String getLine() {
        return line;
    }

    public double getDX() {
        return dX;
    }

    public double getDY() {
        return dy;
    }

    public double getDDX() {
        return ddX;
    }

    public double getDDY() {
        return ddY;
    }

    public double getDDZ() {
        return ddZ;
    }

    public double getDDHor() {
        return ddHor;
    }

    public double getDirection() {
        return direction;
    }
}


