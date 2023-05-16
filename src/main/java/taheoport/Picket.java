package taheoport;

/**
 * This class encapsulated measurement to picket
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class Picket{
    private String pName;
    private String pAltName;
    private String line;
    private String hor;
    private String vert;
    private String v;
    private double direction = 0.0;
    private double dX = 0.0;
    private double dY = 0.0;
    private double dZ = 0.0;
    private double x = 0.0;
    private double y = 0.0;
    private double z = 0.0;
    private double horLine =0.0;

    /**
     * Constructor
     * @param pName pickets name
     * @param pLine inclined distance
     * @param pHor left horizontal angle
     * @param pVert tilt angle
     * @param pV target height
     * @param pAltName alternative name
     * @param station parent station
     */
    public Picket(String pName,
                  String pLine,
                  String pHor,
                  String pVert,
                  String pV,
                  String pAltName,
                  SurveyStation station) {
        if (new DataHandler(pName).isValidName()) {
            this.pName = pName;
        } else {
            this.pName = "noname";
        }

        if (new DataHandler(pV).isPositiveNumber()) {
            this.v = new DataHandler(pV).format(3).getStr();
        } else {
            this.v = "0.000";
        }

        if (new DataHandler(pLine).isPositiveNumber()) {
            this.line = new DataHandler(pLine).format(3).getStr();
        } else {
            this.line = "0.000";
        }

        if (new DataHandler(pHor).isPositiveNumber()) {
            this.hor = new DataHandler(pHor).format(4).getStr();
        } else {
            this.hor = "0.0000";
        }

        if (new DataHandler(pVert).isNumber()) {
            this.vert = new DataHandler(pVert).format(4).getStr();
        } else {
            this.vert = "0.000";
        }

        if (new DataHandler(pAltName).isValidName()) {
            this.pAltName = pAltName;
        } else {
            this.pAltName = "noname";
        }
    }

    /**
     * Overrides the method toString
     * @return String
     */
    public String toString(){
        return pName + "\t" + pAltName + "\t" + line + "\t" + hor + "\t" + vert + "\t" + v;
    }

    /**
     * Sets pickets name
     * @param pName name
     */
    public void setpName(String pName) {
        if (new DataHandler(pName).isValidName()) {
            this.pName = pName;
        } else {
            this.pName = "None";
        }
    }

    /**
     * Sets tilt distance to picket
     * @param line line
     */
    public void setLine(String line) {
            this.line = line;
    }

    /**
     * Sets horizontal angle to picket
     * @param hor angle, G.MMSS
     */
    public void setHor(String hor) {
            this.hor = hor;
    }

    /**
     * Sets tilt angle line to picket
     * @param vert angle in, G.MMSS
     */
    public void setVert(String vert) {
            this.vert = vert;
    }

    /**
     * Sets target height
     * @param v target height, m.
     */
    public void setV(String v) {
            this.v = v;
    }

    /**
     * Sets direction line to picket
     * @param direction angle, radian
     */
    public void setDirection(double direction) {
        this.direction = direction;
    }

    /**
     * Sets horizontal distance to picket
     * @param horLine distance, m.
     */
    public void setHorLine(double horLine) {
        this.horLine = horLine;
    }

    /**
     * Sets distance projection on the X-axis
     * @param dX double, m.
     */
    public void setdX(double dX) {
        this.dX = dX;
    }

    /**
     * Sets distance projection on the Y-axis
     * @param dY double, m.
     */
    public void setdY(double dY) {
        this.dY = dY;
    }

    /**
     * Sets elevation to picket
     * @param dZ double, m.
     */
    public void setdZ(double dZ) {
        this.dZ = dZ;
    }

    /**
     * Sets pickets coordinates X
     * @param x double, m.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets pickets coordinates Y
     * @param y double, m.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Sets pickets coordinates Z
     * @param z double, m.
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * Gets pickets name
     * @return String
     */
    public String getpName() {
        return pName;
    }

    /**
     * Get pickets Y
     * @return String pickets Y-coordinate
     */
    public String getY() {
        return new DataHandler(y).format(3).getStr();
    }

    /**
     * gets pickets DY
     * @return String DY
     */
    public String getDY() {
        return new DataHandler(dY).format(3).getStr();
    }

    /**
     * Get pickets X-coordinate
     * @return String coordinate X picket
     */
    public String getX() {
        return new DataHandler(x).format(3).getStr();
    }

    /**
     * gets pickets DX
     * @return String DX
     */
    public String getDX() {
        return new DataHandler(dX).format(3).getStr();
    }

    /**
     * Return direction to picket
     * @return Double angle in radians
     */
    public String getPDirection() {

        return new DataHandler().degToDms(Math.toDegrees(direction)).format(4).getStr();
    }

    /**
     * Get pickets Z-coordinate
     * @return String
     */
    public String getZ() {
        return new DataHandler(z).format(3).getStr();
    }

    public String getLine() {
        return line;
    }

    /**
     * Gets horizontal distance
     * @return double
     */
    public String getpHorLine() {
        return new DataHandler(horLine).format(3).getStr();
    }

    /**
     * Gets pickets dz = l * sin(vert)
     * @return double
     */
    public String getDZ() {
        return new DataHandler(dZ).format(3).getStr();
    }

    /**
     * Gets horizontal angle
     * @return String
     */
    public String getHor() {
        return hor;
    }

    /**
     * Gets tilt angle
     * @return String
     */
    public String getVert() {
        return vert;
    }

    /**
     * Gets target height
     * @return String
     */
    public String getV() {
        return v;
    }
}
