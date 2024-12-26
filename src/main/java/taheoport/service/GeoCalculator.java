package taheoport.service;

/**
 * This class encapsulates the geodesic tasks
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class GeoCalculator {

    /**
     * gets direction AB in radians
     * @param xA x-coordinate of A-point
     * @param yA y-coordinate of A-point
     * @param xB x-coordinate of B-point
     * @param yB y-coordinate of B-point
     * @return double direction
     */
    public static double getDirAB(String xA, String yA, String xB, String yB) {
        double dir = 0.0;
        double dX = Double.parseDouble(xB) - Double.parseDouble(xA);
        double dY = Double.parseDouble(yB) - Double.parseDouble(yA);

        if (Math.signum(dX) == 0) {
            if (Math.signum(dY) == 1) {
                return Math.PI / 2;
            }
            if (Math.signum(dY) == -1) {
                return Math.PI + Math.PI / 2;
            }

        } else {
            dir = Math.atan(dY / dX);
            if (Math.signum(dY) == 0) {
                if (Math.signum(dX) == -1) {
                    return Math.PI;
                } else {
                    return 0.0;
                }

            } if ( Math.signum(dY) == -1) {
                if (Math.signum(dX) == -1) {
                    dir += Math.PI;
                } else {
                    dir += 2 * Math.PI;
                }

            } else {
                if (dX < 0.0) {
                    dir += Math.PI;
                }
            }
        }
        return dir;
    }

    /**
     * gets horizontal line projection
     * @param line measured inclined length
     * @param vert tilt angle of line in DMS
     * @return double length of line
     */
    public static double getHorLine(String line, String vert) {
        return Double.parseDouble(line) * Math.cos(new DataHandler(vert).dmsToRad());
    }

    /**
     * gets DX
     * @param horLine horizontal line in meters
     * @param direction direction in radians
     * @return double DX
     */
    public static double getDX(double horLine, double direction) {
        return horLine * Math.cos(direction);
    }

    /**
     * gets DY
     * @param horLine horizontal line in meters
     * @param direction direction in radians
     * @return double DY
     */
    public static double getDY(double horLine, double direction) {
        return horLine * Math.sin(direction);
    }

}
