package taheoport.model;

import taheoport.service.DataHandler;

import java.util.LinkedList;

/**
 * This class encapsulates parameters of SurveyStation
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class SurveyStation {
    private String name;
    private String x;
    private String y;
    private String z;
    private String nameOr;
    private String xOr;
    private String yOr;
    private String zOr;
    private String vi;
    private final LinkedList <Picket> pickets;

    /**
     * Constructor without parameters
     */
    public SurveyStation() {
        pickets = new LinkedList<>();
        this.name = "noname";
        this.x = "0.000";
        this.y = "0.000";
        this.z = "0.000";
        this.nameOr = "noname";
        this.xOr = "0.000";
        this.yOr = "0.000";
        this.zOr = "0.000";
        this.vi = "0.000";
    }

    /**
     * Constructor
     * @param name Наименование станции
     * @param x Координата X станции
     * @param y Координата Y станции
     * @param z Координата Z станции
     * @param sNameOr Наименование ориентира
     * @param sXor Координата X ориентира
     * @param sYor Координата Y ориентира
     * @param sZor Координата Z ориентира
     * @param vi Высота инструмента
     */
    public SurveyStation(String name, String  x, String y,
                         String z, String sNameOr, String sXor,
                         String sYor, String sZor, String vi) {
        if (new DataHandler(name).isValidName()) {
            this.name = name;
        } else {
            this.name = "noname";
        }

        if (new DataHandler(x).isNumber()) {
            this.x = new DataHandler(x).format(3).getStr();
        } else {
            this.x = "0.000";
        }

        if (new DataHandler(y).isNumber()) {
            this.y = new DataHandler(y).format(3).getStr();
        } else {
            this.y = "0.000";
        }

        if (new DataHandler(z).isNumber()) {
            this.z = new DataHandler(z).format(3).getStr();
        } else {
            this.z = "0.000";
        }

        if (new DataHandler(sNameOr).isValidName()) {
            this.nameOr = sNameOr;
        } else {
            this.nameOr = "noname";
        }

        if (new DataHandler(sXor).isNumber()) {
            this.xOr = new DataHandler(sXor).format(3).getStr();
        } else {
            this.xOr = "0.000";
        }

        if (new DataHandler(sYor).isNumber()) {
            this.yOr = new DataHandler(sYor).format(3).getStr();
        } else {
            this.yOr = "0.000";
        }

        if (new DataHandler(sZor).isNumber()) {
            this.zOr = new DataHandler(sZor).format(3).getStr();
        } else {
            this.zOr = "0.000";
        }

        if (new DataHandler(vi).isNumber()) {
            this.vi = vi;
        } else {
            this.vi = "0.000";
        }
        pickets = new LinkedList <>();
    }

    @Override
    public String toString(){
        return  name + " " +
                x + " " +
                y + " " +
                z + " " +
                nameOr + " " +
                xOr + " " +
                yOr + " " +
                zOr + " " +
                vi;
    }

    /**
     * Get itital direction St -> Or
     * @return Double angle in radians
     */
    public double getDirection() {
        double dir = 0.0;
        double dX = Double.parseDouble(this.xOr) - Double.parseDouble(this.x);
        double dY = Double.parseDouble(this.yOr) - Double.parseDouble(this.y);
        if (dX == 0.0) {
            if (dY > 0) {
                dir = Math.PI / 2;
            }
            if (dY < 0) {
                dir = Math.PI + Math.PI / 2;
            }

        } else {
            dir = Math.atan(dY / dX);
            if (dY == 0) {
                if (dX < 0) {
                    dir = Math.PI;
                } else {
                    dir = 0.0;
                }
            } if ( dY < 0) {
                if (dX < 0) {
                    dir += Math.PI;
                } else {
                    dir += 2 *Math.PI;
                }
            } else {
                if (dX < 0) {
                    dir += Math.PI;
                }
            }
        }

        return dir;
    }

    /**
     * Append new instance Picket with arguments to end of this.pickets
     * @param pName picket name
     * @param pLine inclined distance from station to picket
     * @param pHor horizontal direction to picket
     * @param pVert tilt angle to target
     * @param pV target height
     * @param pAltName alternative picket name
     * @return Picket
     * @throws NullPointerException
     */
    public Picket addPicket(String pName,
                            String pLine,
                            String pHor,
                            String pVert,
                            String pV,
                            String pAltName) throws NullPointerException{
        pickets.add(new Picket(pName, pLine, pHor, pVert, pV, pAltName));
        return pickets.getLast();
    }

    /**
     * Append new Empty instance Picket to the end of this.pickets
     * @return Picket
     */
    public Picket addPicket() {
        return addPicket("noname", "0.000", "0.0000",
                "0.0000", "0.000", "noname");
    }

    /**
     * Append new Empty instance of Picket to index position of this.pickets
     * @param index int index
     */
    public void addPicket(int index) {
        pickets.add(index, new Picket("noname", "0.000", "0.000",
                "0.000", "0.000", "noname"));
    }

    /**
     * Gets this.pickets.get(index)
     * @param index index of picket
     * @return Picket
     */
    public Picket getPicket(int index){
        return pickets.get(index);
    }

    /**
     * Removes index element of this.pickets
     * @param index int pickets index
     */
    public void removePicket(int index){
        pickets.remove(index);
    }

    /**
     * Gets size list of pickets
     * @return int
     */
    public int sizePickets(){
        return pickets.size();
    }

    /**
     * Sets station name
     * @param name String station name
     */
    public void setName(String name) {
        if (new DataHandler(name).isValidName()) {
            this.name = name;
        }
    }

    /**
     * Sets station X coordinate
     * @param x String x station
     */
    public void setX(String x) {
        if (new DataHandler(x).isNumber()) {
            this.x = x;
        }
    }

    /**
     * Sets station Y coordinate
     * @param y String y station
     */
    public void setY(String y) {
        if (new DataHandler(y).isNumber()) {
            this.y = y;
        }
    }

    /**
     * Sets station Z coordinate
     * @param z String z station
     */
    public void setZ(String z) {
        if (new DataHandler(z).isNumber()) {
            this.z = z;
        }
    }

    /**
     * Sets orientir name
     * @param nameOr String orientir name
     */
    public void setNameOr(String nameOr) {
        if (new DataHandler(nameOr).isValidName()) {
            this.nameOr = nameOr;
        }
    }

    /**
     * Sets orientir X coordinate
     * @param xOr String x orientir
     */
    public void setxOr(String xOr) {
        if (new DataHandler(xOr).isNumber()) {
            this.xOr = xOr;
        }
    }

    /**
     * Sets orientir Y coordinate
     * @param yOr String y orientir
     */
    public void setyOr(String yOr) {
        if (new DataHandler(yOr).isNumber()) {
            this.yOr = yOr;
        }
    }

    /**
     * Sets orientir z coordinate
     * @param zOr String z orientir
     */
    public void setzOr(String zOr) {
        if (new DataHandler(zOr).isNumber()) {
            this.zOr = zOr;
        }
    }

    /**
     * Sets target height
     * @param vi String target height
     */
    public void setVi(String vi) {
        if (new DataHandler(vi).isNumber()) {
            this.vi = vi;
        }
    }

    /**
     * Gets this.name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Gets station X coordinate this.x
     * @return String
     */
    public String getX() {
        return x;
    }

    /**
     * Gets station Y coordinate this.y
     * @return String
     */
    public String getY() {
        return y;
    }

    /**
     * Gets station Z coordinate this.z
     * @return String
     */
    public String getZ() {
        return z;
    }

    /**
     * Gets orientir name this.nameOr
     * @return String
     */
    public String getNameOr() {
        return nameOr;
    }

    /**
     * Gets orientir X coordinate this.xOr
     * @return String
     */
    public String getxOr() {
        return xOr;
    }

    /**
     * Gets orientir Y coordinate this.yOr
     * @return String
     */
    public String getyOr() {
        return yOr;
    }

    /**
     * Gets orientir Z coordinate this.z
     * @return String
     */
    public String getzOr() {
        return zOr;
    }

    /**
     * Gets target height this.vi
     * @return String
     */
    public String getVi() {
        return vi;
    }
}
