    package taheoport.model;

    import taheoport.gui.MainWin;
    import java.util.LinkedList;

    /**
     * this class encapsulates data in a list of stations llTeoStation and
     * provides methods for editing, adjustment
     * @author Andrew Nizovkin
     * Copyright Nizovkin A.V. 2022
     */
public class PolygonProject {
        private String absolutePolPath = "";
        private BindType bindType = BindType.ZZ;
        private double fHor = 0.0;
        private double fX = 0.0;
        private double fY = 0.0;
        private double fZ = 0.0;
        private double fAbs = 0.0;
        private String fOtn = "Not";
        private final LinkedList<PolygonStation> listPolygonStatons = new LinkedList<>();
        private final MainWin parentFrame;
        private double perimeter = 0.0;

    public enum BindType {ZZ,TT, TO, OT, OO, TZ, ZT}

        /**
         * Constructor
         * @param frame parent MainWin
         */
    public PolygonProject(MainWin frame) {
        parentFrame = frame;
    }

        /**
         * sets absolutePolPath
         * @param absolutePolPath String path
         */
        public void setAbsolutePolPath(String absolutePolPath) {
            this.absolutePolPath = absolutePolPath;
        }

        /**
         * Return this.absolutePolPath
         * @return String absolutePolPath
         */
    public String getAbsolutePolPath() {
        return absolutePolPath;
    }

        /**
         * return TheoStation from llTeoStations[idx]
         * @param idx int index
         * @return String
         */
        public PolygonStation getPolygonStation(int idx) {
        return listPolygonStatons.get(idx);
        }

        /**
         * return size of llStations
         * @return int size
         */
        public int getSizePolygonStations() {
            return listPolygonStatons.size();
        }

        /**
         * get perimeter
         * @return double perimeter
         */
        public double getPerimeter() {
            return perimeter;
        }

        /**
         * Gets this.fHor
         * @return double fHor
         */
        public double getfHor() {
            return fHor;
        }

        /**
         * Gets this.fX
         * @return double
         */
        public double getfX() {
            return fX;
        }

        /**
         * Gets this.fY
         * @return double
         */
        public double getfY() {
            return fY;
        }

        /**
         * Gets this.fZ
         * @return double
         */
        public double getfZ() {
            return fZ;
        }

        /**
         * Gets this.fAbs
         * @return double
         */
        public double getfAbs() {
            return fAbs;
        }

        /**
         * Gets this.fOtn
         * @return String
         */
        public String getfOtn() {
            return fOtn;
        }

        /**
         * Gets this.bindType
         * @return BindType
         */
        public BindType getBindType() {
            return bindType;
        }

        /**
         * remove Station from llTheoStations
         * @param idx int index removed element
         */
        public void removeStation(int idx) {
            listPolygonStatons.remove(idx);
        }

        /**
         * Adds EMPTY Station at idx position
         * @param idx int idx
         */
        public void addStation(int idx) {
            listPolygonStatons.add(idx, new PolygonStation());
        }

        /**
         * Adds polygonStation to end of listPolygonStations
         * @param polygonStation instance of PolygonStation
         */
        public void addStation(PolygonStation polygonStation) {
            listPolygonStatons.add(polygonStation);
        }

        /**
         * Sets fHor
         * @param fHor
         */
        public void setfHor(double fHor) {
            this.fHor = fHor;
        }

        /**
         * Sets perimeter
         * @param perimeter double
         */
        public void setPerimeter(double perimeter) {
            this.perimeter = perimeter;
        }

        /**
         * Sets bindType
         * @param bindType
         */
        public void setBindType(BindType bindType) {
            this.bindType = bindType;
        }

        /**
         * Sets fX
         * @param fX double fX
         */
        public void setfX(double fX) {
            this.fX = fX;
        }

        /**
         * Sets fY
         * @param fY double fY
         */
        public void setfY(double fY) {
            this.fY = fY;
        }

        /**
         * Sets fZ
         * @param fZ double fZ
         */
        public void setfZ(double fZ) {
            this.fZ = fZ;
        }

        /**
         * Sets fAbs
         * @param fAbs double fAbs
         */
        public void setfAbs(double fAbs) {
            this.fAbs = fAbs;
        }

        /**
         * Sets fOtn
         * @param fOtn String fOtn
         */
        public void setfOtn(String fOtn) {
            this.fOtn = fOtn;
        }
    }
