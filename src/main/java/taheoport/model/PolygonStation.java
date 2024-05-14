package taheoport.model;

import taheoport.service.DataHandler;

/**
 * This class encapsulates fields of PolygonStation
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class PolygonStation {
    private double dX = 0.0;
    private double dY = 0.0;
    private String dZ = "0.000";
    private double ddX = 0.0;
    private double ddY = 0.0;
    private double ddZ = 0.0;
    private double ddHor = 0.0;
    private double direction = 0.0;
    private String hor = "0.0000";
    private String line = "0.000";
    private String name = "noname";
    private boolean status = false;
    private String x = "Not";
    private String y = "Not";
    private String z = "Not";

    /**
     * Sets this.name
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets this.status
     * @param status boolean
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Sets this.x
     * @param x String
     */
    public void setX(String x) {
        this.x = x;
    }

    /**
     * Sets this.y
     * @param y String
     */
    public void setY(String y) {
        this.y = y;
    }

    /**
     * Sets this.z
     * @param z String
     */
    public void setZ(String z) {
        this.z = z;
    }

    /**
     * Sets this.dZ
     * @param dZ String
     */
    public void setdZ(String dZ) {
        DataHandler dataHandler = new DataHandler(dZ);
        if (dataHandler.isNumber()) {
            this.dZ = dataHandler.format(3).getStr();
        }
    }

    /**
     * Sets this.hor
     * @param hor String
     */
    public void setHor(String hor) {
        DataHandler dataHandler = new DataHandler(hor);
        if (dataHandler.isPositiveNumber()) {
            this.hor = dataHandler.format(4).getStr();
        }
    }

    /**
     * Sets this.line
     * @param line String
     */
    public void setLine(String line) {
        DataHandler dataHandler = new DataHandler(line);
        if (dataHandler.isNumber()) {
            this.line = dataHandler.format(3).getStr();
        }
    }

    /**
     * Sets this.dX
     * @param dX double
     */
    public void setDX(double dX) {
        this.dX = dX;
    }

    /**
     * Sets this.dY
     * @param dy double
     */
    public void setDY(double dy) {
        this.dY = dy;
    }

    /**
     * Sets this.ddX
     * @param ddX double
     */
    public void setDDX(double ddX) {
        this.ddX = ddX;
    }

    /**
     * Sets this.ddY
     * @param ddY double
     */
    public void setDDY(double ddY) {
        this.ddY = ddY;
    }

    /**
     * Sets this.ddZ
     * @param ddZ double
     */
    public void setDDZ(double ddZ) {
        this.ddZ = ddZ;
    }

    /**
     * Sets this.ddHor
     * @param ddHor double
     */
    public void setDDHor(double ddHor) {
        this.ddHor = ddHor;
    }

    /**
     * Sets this.direction
     * @param direction double
     */
    public void setDirection(double direction) {
        this.direction = direction;
    }

    /**
     * Gets this.name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Gets this.Status
     * @return boolean
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Gets this.x
     * @return String
     */
    public String getX() {
        return x;
    }

    /**
     * Gets this.y
     * @return String
     */
    public String getY() {
        return y;
    }

    /**
     * Gets this.y
     * @return String
     */
    public String getZ() {
        return z;
    }

    /**
     * Gets this.dZ
     * @return String
     */
    public String getDZ() {
        return dZ;
    }

    /**
     * Gets this.hor
     * @return String
     */
    public String getHor() {
        return hor;
    }

    /**
     * Gets this.line
     * @return String
     */
    public String getLine() {
        return line;
    }

    /**
     * Gets this.dX
     * @return double
     */
    public double getDX() {
        return dX;
    }

    /**
     * Gets this.dY
     * @return double
     */
    public double getDY() {
        return dY;
    }

    /**
     * Gets this.ddX
     * @return double
     */
    public double getDDX() {
        return ddX;
    }

    /**
     * Gets this.ddy
     * @return double
     */
    public double getDDY() {
        return ddY;
    }

    /**
     * Gets this.ddZ
     * @return double
     */
    public double getDDZ() {
        return ddZ;
    }

    /**
     * Gets this.ddHor
     * @return double
     */
    public double getDDHor() {
        return ddHor;
    }

    /**
     * Gets this.direction
     * @return double
     */
    public double getDirection() {
        return direction;
    }
}


