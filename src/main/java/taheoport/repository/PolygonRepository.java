    package taheoport.repository;

    import taheoport.model.PolygonStation;

    import java.util.LinkedList;
    import java.util.List;

    /**
     * this class encapsulates data in a list of stations llTeoStation and
     * provides methods for editing, adjustment
     * @author Andrew Nizovkin
     * Copyright Nizovkin A.V. 2022
     */
public class PolygonRepository {
        private String absolutePolPath;
        private BindType bindType;
        private double fHor;
        private double fX;
        private double fY;
        private double fZ;
        private double fAbs;
        private String fOtn;
        private final List<PolygonStation> listPolygonStations;
        private double perimeter;

    public enum BindType {ZZ,TT, TO, OT, OO, TZ, ZT}

        /**
         * Constructor
         */
    public PolygonRepository() {
        absolutePolPath = "";
        bindType = BindType.ZZ;
        fHor = 0.0;
        fX = 0.0;
        fY = 0.0;
        fAbs = 0.0;
        fOtn = "Not";
        perimeter = 0.0;
        listPolygonStations = new LinkedList<>();
    }

        /**
         * Removes all elements from listPolygonStations
         */
    public void clear() {
        listPolygonStations.clear();
        absolutePolPath = "";
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
         * Find polygonStation by index
         * @param idx int index
         * @return String
         */
        public PolygonStation findById(int idx) {
        return listPolygonStations.get(idx);
        }

        /**
         * return size of llStations
         * @return int size
         */
        public int getSizePolygonStations() {
            return listPolygonStations.size();
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
            listPolygonStations.remove(idx);
        }

        /**
         * Adds EMPTY Station at idx position
         * @param idx int idx
         */
        public void insertStation(int idx) {
            listPolygonStations.add(idx, new PolygonStation());
        }

        /**
         * Adds polygonStation to end of listPolygonStations
         * @param polygonStation instance of PolygonStation
         */
        public void addStation(PolygonStation polygonStation) {
            listPolygonStations.add(polygonStation);
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
         * @param bindType BindType
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
