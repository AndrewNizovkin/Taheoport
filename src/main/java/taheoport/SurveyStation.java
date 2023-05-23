package taheoport;

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

    //---Добавляет строку параметров с разделителем
    public String toString(){
//        String txt = sName + "\t" + sXst + "\t" + sYst + "\t" + sZst + "\t" + sNameOr + "\t" + sXor + "\t" + sYor + "\t" + sZor + "\t" + sI;
        return  name + "\t" + x + "\t" + y + "\t" + z + "\t" + nameOr + "\t" + xOr + "\t" + yOr + "\t" + zOr + "\t" + vi;
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

    //---Добавляет новый Picket в конец списка пикетов sll и возвращает ссылку на него
    public Picket addPicket(String pName,
                            String pLine,
                            String pHor,
                            String pVert,
                            String pV,
                            String pAltName, SurveyStation st) throws NullPointerException{
//        p = new Picket(pName, pLine, pHor, pVert, pV, pAltName);
        pickets.add(new Picket(pName, pLine, pHor, pVert, pV, pAltName, st));
//        p = sll.getLast();
        return pickets.getLast();
    }

    public Picket addPicket(SurveyStation st) {
        return addPicket("noname", "0.000", "0.0000",
                "0.0000", "0.000", "noname",st);
    }

    public Picket addPicket(int index) {
        pickets.add(index, new Picket("noname", "0.000", "0.000",
                "0.000", "0.000", "noname", this));

        return pickets.get(index);
    }



    //---Возвращает ссылку (Picket) на элемент списка sll с индексом i
    public Picket getPicket(int k){
//        p = sll.get(k);
        return pickets.get(k);
    }

    //---Удаляет Picket с индексом i из списка sll и возвращает ссылку на него
    public Picket removePicket(int i){
//        p = (Picket) sll.remove(i);
        return pickets.remove(i);
    }

    //--Возвращает размер списка пикетов на текущей станции
    public int sizePickets(){
//        int k = sll.size();
        return pickets.size();
    }

    //---Setters
    public void setName(String name) {
        if (new DataHandler(name).isValidName()) {
            this.name = name;
        }
    }

    public void setX(String x) {
        if (new DataHandler(x).isNumber()) {
            this.x = x;
        }
    }

    public void setY(String y) {
        if (new DataHandler(y).isNumber()) {
            this.y = y;
        }
    }

    public void setZ(String z) {
        if (new DataHandler(z).isNumber()) {
            this.z = z;
        }
    }

    public void setNameOr(String nameOr) {
        if (new DataHandler(nameOr).isValidName()) {
            this.nameOr = nameOr;
        }
    }

    public void setxOr(String xOr) {
        if (new DataHandler(xOr).isNumber()) {
            this.xOr = xOr;
        }
    }

    public void setyOr(String yOr) {
        if (new DataHandler(yOr).isNumber()) {
            this.yOr = yOr;
        }
    }

    public void setzOr(String zOr) {
        if (new DataHandler(zOr).isNumber()) {
            this.zOr = zOr;
        }
    }

    public void setVi(String vi) {
        if (new DataHandler(vi).isNumber()) {
            this.vi = vi;
        }
    }

    // Getters

    public String getName() {
        return name;
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

    public String getNameOr() {
        return nameOr;
    }

    public String getxOr() {
        return xOr;
    }

    public String getyOr() {
        return yOr;
    }

    public String getzOr() {
        return zOr;
    }

    public String getVi() {
        return vi;
    }
}
