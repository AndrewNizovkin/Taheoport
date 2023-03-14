package taheoport;

/**
 * This class encapsulated measurement to picket
 * @author Andrey Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class Picket{
    private String pName = "Not";
    private String pAltName = "Not";
    private String line = "0.000";
    private String hor = "0.000";
    private String vert = "0.000";
    private String v = "0.000";
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
     * входящие параметры:
     * @param pName название пикета (номер или код)
     * @param pLine измеренная наклонная длина линии
     * @param pHor левый горизонтальный угол
     * @param pVert вертикальный угол
     * @param pV высота наведения
     */
    public Picket(String pName, String pLine, String pHor, String pVert, String pV, String pAltName, SurveyStation st) {
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

    public String toString(){
        return pName + "\t" + pAltName + "\t" + line + "\t" + hor + "\t" + vert + "\t" + v;
    }

    //---Setters

    public void setpName(String pName) {
        if (new DataHandler(pName).isValidName()) {
            this.pName = pName;
        }
    }

    public void setpAltName(String pAltName) {
        if (new DataHandler(pAltName).isValidName()) {
            this.pAltName = pAltName;
        }
    }

    public void setLine(String line) {
        if (new DataHandler(line).isPositiveNumber()) {
            this.line = line;
        }
    }

    public void setHor(String hor) {
        if (new DataHandler(hor).isPositiveNumber()) {
            this.hor = hor;
        }
    }

    public void setVert(String vert) {
        if (new DataHandler(vert).isNumber()) {
            this.vert = vert;
        }
    }

    public void setV(String v) {
        if (new DataHandler(v).isNumber()) {
            this.v = v;
        }
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public void setHorLine(double horLine) {
        this.horLine = horLine;
    }

    public void setdX(double dX) {
        this.dX = dX;
    }

    public void setdY(double dY) {
        this.dY = dY;
    }

    public void setdZ(double dZ) {
        this.dZ = dZ;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    //---Getters

    public String getpName() {
        return pName;
    }

    public String getpAltName() {
        return pAltName;
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
        return new DataHandler( dX).format(3).getStr();
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
     * Get horizontal distance
     * @return double
     */
    private String getpHorLine() {
        return new DataHandler(horLine).format(3).getStr();
    }

    /**
     * Get pickets dz = l * sin(vert)
     * @return double
     */
    public String getDZ() {
        return new DataHandler(dZ).format(3).getStr();
    }

    public String getHor() {
        return hor;
    }

    public String getVert() {
        return vert;
    }

    public String getV() {
        return v;
    }
}
