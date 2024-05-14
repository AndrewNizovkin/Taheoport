package taheoport.service;

import taheoport.gui.MainWin;
import taheoport.model.PolygonProject;
import taheoport.model.PolygonStation;
import taheoport.model.SurveyStation;

import javax.swing.*;

public class AdjusterDefault implements Adjuster{
    private final MainWin parentFrame;
    private PolygonProject polygonProject;

    public AdjusterDefault(MainWin frame) {

        parentFrame = frame;
        polygonProject = parentFrame.getPolygonProject();
    }

    /**
     * Adjust the polygon
     */
    @Override
    public void adjustPolygon() {
        polygonProject = parentFrame.getPolygonProject();
        int countStations = polygonProject.getSizePolygonStations();
        polygonProject.setPerimeter(0.0);
        double sumDX = 0.0;
        double sumDY = 0.0;
        double sumDZ = 0.0;
        GeoCalc geoCalc = new GeoCalc();
        defBindType();
        switch (polygonProject.getBindType()) {
            case ZZ -> JOptionPane.showMessageDialog(parentFrame,
                    parentFrame.getTitles().get("TPmessageNoAdjustment"),
                    parentFrame.getTitles().get("TPmessageNoAdjustmentTitle"),
                    JOptionPane.ERROR_MESSAGE);
            case TT -> {
                iniDDs();
                defPerimeter(1, countStations - 3);
                polygonProject.getPolygonStation(0).setDirection(Math.toDegrees(geoCalc.getDirAB(
                        polygonProject.getPolygonStation(0).getX(),
                        polygonProject.getPolygonStation(0).getY(),
                        polygonProject.getPolygonStation(1).getX(),
                        polygonProject.getPolygonStation(1).getY())));
                polygonProject.getPolygonStation(countStations - 2).setDirection(Math.toDegrees(geoCalc.getDirAB(
                        polygonProject.getPolygonStation(countStations - 2).getX(),
                        polygonProject.getPolygonStation(countStations - 2).getY(),
                        polygonProject.getPolygonStation(countStations - 1).getX(),
                        polygonProject.getPolygonStation(countStations - 1).getY())));
                setDDHors();
                setDirections(1, countStations - 3);
                setDXDYs(1, countStations - 3);
                setDDXDDYs(1, countStations - 3);
                setDDZs(1, countStations - 3);
                setXYZs(2, countStations - 3);
            }
            case TO -> {
                iniDDs();
                defPerimeter(1, countStations - 2);
                polygonProject.getPolygonStation(0).setDirection(Math.toDegrees(new SurveyStation("Not",
                        polygonProject.getPolygonStation(0).getX(),
                        polygonProject.getPolygonStation(0).getY(),
                        polygonProject.getPolygonStation(0).getZ(),
                        "Not",
                        polygonProject.getPolygonStation(1).getX(),
                        polygonProject.getPolygonStation(1).getY(),
                        polygonProject.getPolygonStation(1).getZ(),
                        "0.000").getDirection()));
                setDirections(1, countStations - 2);
                setDXDYs(1, countStations - 2);
                setDDXDDYs(1, countStations - 2);
                setDDZs(1, countStations - 2);
                setXYZs(2, countStations - 2);
            }
            case OT -> {
                iniDDs();
                defPerimeter(0, countStations - 3);
                polygonProject.getPolygonStation(countStations - 2).setDirection(Math.toDegrees(new SurveyStation("Not",
                        polygonProject.getPolygonStation(countStations - 2).getX(),
                        polygonProject.getPolygonStation(countStations - 2).getY(),
                        polygonProject.getPolygonStation(countStations - 2).getZ(),
                        "Not",
                        polygonProject.getPolygonStation(countStations - 1).getX(),
                        polygonProject.getPolygonStation(countStations - 1).getY(),
                        polygonProject.getPolygonStation(countStations - 1).getZ(),
                        "0.000").getDirection()));
                setDirections(countStations - 3, 0);
                setDXDYs(0, countStations - 3);
                setDDXDDYs(0, countStations - 3);
                setDDZs(0, countStations - 3);
                setXYZs(1, countStations - 3);
            }
            case OO -> {
                iniDDs();
                defPerimeter(0, countStations - 2);
                polygonProject.getPolygonStation(0).setDirection(0.0);
                setDirections(1, countStations - 2);
                setDXDYs(0, countStations - 2);
                for (int i = 0; i <= countStations - 2; i++) {
                    sumDX = sumDX + polygonProject.getPolygonStation(i).getDX();
                    sumDY = sumDY + polygonProject.getPolygonStation(i).getDY();
                }
                sumDX = sumDX + Double.parseDouble(polygonProject.getPolygonStation(0).getX());
                sumDY = sumDY + Double.parseDouble(polygonProject.getPolygonStation(0).getY());
                sumDZ = Math.toDegrees(new SurveyStation("Not",
                        polygonProject.getPolygonStation(0).getX(),
                        polygonProject.getPolygonStation(0).getY(),
                        "0.000",
                        "Not",
                        polygonProject.getPolygonStation(countStations - 1).getX(),
                        polygonProject.getPolygonStation(countStations - 1).getY(),
                        "0.000",
                        "0.000").getDirection()) -
                        Math.toDegrees(new SurveyStation("Not",
                                polygonProject.getPolygonStation(0).getX(),
                                polygonProject.getPolygonStation(0).getY(),
                                "0.000",
                                "Not",
                                new DataHandler(sumDX).format(3).getStr(),
                                new DataHandler(sumDY).format(3).getStr(),
                                "0.000",
                                "0.000").getDirection());
                while (sumDZ < 0.0) {
                    sumDZ = sumDZ + 360;
                }
                polygonProject.getPolygonStation(0).setDirection(sumDZ);
                setDirections(1, countStations - 2);
                setDXDYs(0, countStations - 2);
                setDDXDDYs(0, countStations - 2);
                setDDZs(0, countStations - 2);
                setXYZs(1, countStations - 2);
            }
            case TZ -> {
                iniDDs();
                defPerimeter(1,countStations - 2);
                polygonProject.getPolygonStation(0).setDirection(Math.toDegrees(new SurveyStation("Not",
                        polygonProject.getPolygonStation(0).getX(),
                        polygonProject.getPolygonStation(0).getY(),
                        polygonProject.getPolygonStation(0).getZ(),
                        "Not",
                        polygonProject.getPolygonStation(1).getX(),
                        polygonProject.getPolygonStation(1).getY(),
                        polygonProject.getPolygonStation(1).getZ(),
                        "0.000").getDirection()));
                setDirections(1, countStations - 2);
                setDXDYs(1, countStations - 2);
                setXYZs(2, countStations - 1);
            }
            case ZT -> {
                iniDDs();
                defPerimeter(0, countStations - 3);
                polygonProject.getPolygonStation(countStations - 2).setDirection(Math.toDegrees(new SurveyStation("Not",
                        polygonProject.getPolygonStation(countStations - 2).getX(),
                        polygonProject.getPolygonStation(countStations - 2).getY(),
                        polygonProject.getPolygonStation(countStations - 2).getZ(),
                        "Not",
                        polygonProject.getPolygonStation(countStations - 1).getX(),
                        polygonProject.getPolygonStation(countStations - 1).getY(),
                        polygonProject.getPolygonStation(countStations - 1).getZ(),
                        "0.000").getDirection()));
                setDirections(countStations - 3, 0);
                setDXDYs(0, countStations - 3);
                for (int i = 0; i <= countStations - 3; i++) {
                    sumDX = sumDX + polygonProject.getPolygonStation(i).getDX();
                    sumDY = sumDY + polygonProject.getPolygonStation(i).getDY();
                    sumDZ = sumDZ + Double.parseDouble(polygonProject.getPolygonStation(i).getDZ());
                }
                polygonProject.getPolygonStation(0).setX(
                        new DataHandler(Double.parseDouble(polygonProject.getPolygonStation(countStations - 2).getX()) -
                                sumDX).format(3).getStr());
                polygonProject.getPolygonStation(0).setY(
                        new DataHandler(Double.parseDouble(polygonProject.getPolygonStation(countStations - 2).getY()) -
                                sumDY).format(3).getStr());
                polygonProject.getPolygonStation(0).setZ(
                        new DataHandler(Double.parseDouble(polygonProject.getPolygonStation(countStations - 2).getZ()) -
                                sumDZ).format(3).getStr());
                setXYZs(1, countStations - 3);
            }
        }
    }

    /**
     * set and return bindings type of current TheoProject
     */
    private void defBindType() {
        polygonProject.setBindType(PolygonProject.BindType.ZZ);
        int countStations = polygonProject.getSizePolygonStations();
        if (countStations > 2) {
            if (polygonProject.getPolygonStation(0).getStatus() &
                    polygonProject.getPolygonStation(1).getStatus() &
                    polygonProject.getPolygonStation(countStations - 1).getStatus() &
                    polygonProject.getPolygonStation(countStations - 2).getStatus()) {
                polygonProject.setBindType(PolygonProject.BindType.TT);
                if (isValidSourceData(1, countStations - 3) |
                        !new DataHandler(polygonProject.getPolygonStation(countStations - 2).getHor()).isPositiveNumber()) {
                    polygonProject.setBindType(PolygonProject.BindType.ZZ);
                    JOptionPane.showMessageDialog(parentFrame,
                            parentFrame.getTitles().get("TPmessageError"),
                            parentFrame.getTitles().get("TPmessageErrorTitle"),
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if (polygonProject.getPolygonStation(0).getStatus() &
                    polygonProject.getPolygonStation(1).getStatus() &
                    polygonProject.getPolygonStation(countStations - 1).getStatus() &
                    !polygonProject.getPolygonStation(countStations - 2).getStatus()) {
                polygonProject.setBindType(PolygonProject.BindType.TO);
                if (isValidSourceData(1, countStations - 2)) {
                    polygonProject.setBindType(PolygonProject.BindType.ZZ);
                    JOptionPane.showMessageDialog(null,
                            parentFrame.getTitles().get("TPmessageError"),
                            parentFrame.getTitles().get("TPmessageErrorTitle"),
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if (polygonProject.getPolygonStation(0).getStatus() &
                    !polygonProject.getPolygonStation(1).getStatus() &
                    polygonProject.getPolygonStation(countStations - 1).getStatus() &
                    polygonProject.getPolygonStation(countStations - 2).getStatus()) {
                polygonProject.setBindType(PolygonProject.BindType.OT);
                if (isValidSourceData(0, countStations - 3) |
                        !new DataHandler(polygonProject.getPolygonStation(countStations - 2).getHor()).isPositiveNumber()) {
                    polygonProject.setBindType(PolygonProject.BindType.ZZ);
                    JOptionPane.showMessageDialog(null,
                            parentFrame.getTitles().get("TPmessageError"),
                            parentFrame.getTitles().get("TPmessageErrorTitle"),
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if (polygonProject.getPolygonStation(0).getStatus() &
                    !polygonProject.getPolygonStation(1).getStatus() &
                    polygonProject.getPolygonStation(countStations - 1).getStatus() &
                    !polygonProject.getPolygonStation(countStations - 2).getStatus()) {
                polygonProject.setBindType(PolygonProject.BindType.OO);
                if (isValidSourceData(0, countStations - 2)) {
                    polygonProject.setBindType(PolygonProject.BindType.ZZ);
                    JOptionPane.showMessageDialog(null,
                            parentFrame.getTitles().get("TPmessageError"),
                            parentFrame.getTitles().get("TPmessageErrorTitle"),
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if (polygonProject.getPolygonStation(0).getStatus() &
                    polygonProject.getPolygonStation(1).getStatus() &
                    !polygonProject.getPolygonStation(countStations - 1).getStatus() &
                    !polygonProject.getPolygonStation(countStations - 2).getStatus()) {
                polygonProject.setBindType(PolygonProject.BindType.TZ);
                if (isValidSourceData(1, countStations - 3) |
                        !new DataHandler(polygonProject.getPolygonStation(countStations - 2).getHor()).isPositiveNumber()) {
                    polygonProject.setBindType(PolygonProject.BindType.ZZ);
                    JOptionPane.showMessageDialog(null,
                            parentFrame.getTitles().get("TPmessageError"),
                            parentFrame.getTitles().get("TPmessageErrorTitle"),
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if (!polygonProject.getPolygonStation(0).getStatus() &
                    !polygonProject.getPolygonStation(1).getStatus() &
                    polygonProject.getPolygonStation(countStations - 1).getStatus() &
                    polygonProject.getPolygonStation(countStations - 2).getStatus()) {
                polygonProject.setBindType(PolygonProject.BindType.ZT);
                if (isValidSourceData(0, countStations - 3) |
                        !new DataHandler(polygonProject.getPolygonStation(countStations - 2).getHor()).isPositiveNumber()) {
                    polygonProject.setBindType(PolygonProject.BindType.ZZ);
                    JOptionPane.showMessageDialog(null,
                            parentFrame.getTitles().get("TPmessageError"),
                            parentFrame.getTitles().get("TPmessageErrorTitle"),
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (noNeed()) {
            polygonProject.setBindType(PolygonProject.BindType.ZZ);
        }
    }

    /**
     * Сhecks the source data for errors
     * @param start index of the start of the search
     * @param finish index of the end of the search
     * @return boolean
     */
    private boolean isValidSourceData(int start, int finish) {
        for (int i = start; i <= finish; i++) {
            if (!new DataHandler(polygonProject.getPolygonStation(i).getName()).isValidName() |
                    !new DataHandler(polygonProject.getPolygonStation(i).getHor()).isPositiveNumber() |
                    !new DataHandler(polygonProject.getPolygonStation(i).getLine()).isPositiveNumber() |
                    !new DataHandler(polygonProject.getPolygonStation(i).getDZ()).isNumber()) {
                return true;
            }
        }
        return false;
    }

    /**
     * checks the need for adjustment
     * @return boolen
     */
    private boolean noNeed() {
        for (int i = 0; i < polygonProject.getSizePolygonStations(); i++) {
            if (!polygonProject.getPolygonStation(i).getStatus()) {
                return false;
            }
        }
        return true;
    }

    /**
     * calculate and set perimeter
     * @param start index of the start of the search
     * @param finish index of the end of the search
     */
    private void defPerimeter(int start, int finish) {
        double sum = 0.0;
        for (int i = start; i <= finish; i++) {
            sum += Double.parseDouble(polygonProject.getPolygonStation(i).getLine());
        }
        polygonProject.setPerimeter(sum);
    }


    /**
     * initializes ddHor, ddX, ddY, ddZ for all listPolygonStations
     */
    private void iniDDs() {
//        for (PolygonStation station : listPolygonStatons) {
        PolygonStation station;
        for (int i = 0; i < polygonProject.getSizePolygonStations(); i++) {
            station = polygonProject.getPolygonStation(i);
            station.setDDHor(0.0);
            station.setDDX(0.0);
            station.setDDY(0.0);
            station.setDDZ(0.0);
        }
    }

    /**
     * calculate fHor and sets ddHor for all llTheoStations with bindType
     */
    private void setDDHors() {
        DataHandler hor;
        int countStations = polygonProject.getSizePolygonStations();
        double d = polygonProject.getPolygonStation(0).getDirection();
        for (int i = 1; i <= countStations - 2; i++) {
            hor = new DataHandler(polygonProject.getPolygonStation(i).getHor());
            d = d + hor.dmsToDeg() + 180;
        }
        while (d >= 360) {
            d = d - 360;
        }
        polygonProject.setfHor((d - polygonProject.getPolygonStation(countStations - 2).getDirection()) * 3600);
        for (int i = 1; i <= countStations - 2; i++) {
            d = -1 * polygonProject.getfHor() / (countStations - 2);
            polygonProject.getPolygonStation(i).setDDHor(d);
        }
    }

    /**
     * sets Directions for llTheoStations in the specified range
     * @param start index of the start of the search
     * @param finish index of the end of the search
     */
    private void setDirections(int start, int finish) {
        double dir;
        DataHandler hor;
        if (start <= finish) {
            for (int i = start; i <= finish; i++) {
                hor = new DataHandler(polygonProject.getPolygonStation(i).getHor());
                dir = polygonProject.getPolygonStation(i - 1).getDirection() + 180 + hor.dmsToDeg() +
                        polygonProject.getPolygonStation(i).getDDHor() / 3600;
                while (dir >= 360) {
                    dir = dir - 360;
                }
                polygonProject.getPolygonStation(i).setDirection(dir);
            }
        } else {
            for (int i = start; i >= finish; i--) {
                hor = new DataHandler(polygonProject.getPolygonStation(i+1).getHor());
                dir = polygonProject.getPolygonStation(i +1).getDirection() - 180 - hor.dmsToDeg();
                while (dir < 0) {
                    dir = dir + 360;
                }
                polygonProject.getPolygonStation(i).setDirection(dir);
            }
        }
    }
    /**
     * sets dX and dY for listPolygonStations in the specified range
     * @param start index of the start of the search
     * @param finish index of the end of the search
     */
    private void setDXDYs(int start, int finish) {
        DataHandler line;
        for (int i = start; i <= finish; i++) {
            line = new DataHandler(polygonProject.getPolygonStation(i).getLine());
            polygonProject.getPolygonStation(i).setDX(line.getDbl() *
                    Math.cos(Math.toRadians(polygonProject.getPolygonStation(i).getDirection())));
            polygonProject.getPolygonStation(i).setDY(line.getDbl() *
                    Math.sin(Math.toRadians(polygonProject.getPolygonStation(i).getDirection())));
        }
    }

    /**
     * calculate fX, fY and sets ddX, ddY for all llTheoStations with interval
     * @param start index of the start of the search
     * @param end index of the end of the search
     */
    private void setDDXDDYs(int start, int end) {
        double sumDX = 0.0;
        double sumDY = 0.0;
        for (int i = start; i <= end; i++) {
            sumDX = sumDX + polygonProject.getPolygonStation(i).getDX();
            sumDY = sumDY + polygonProject.getPolygonStation(i).getDY();
        }
        polygonProject.setfX(sumDX + Double.parseDouble(polygonProject.getPolygonStation(start).getX()) -
                Double.parseDouble(polygonProject.getPolygonStation(end + 1).getX()));
        polygonProject.setfY(sumDY + Double.parseDouble(polygonProject.getPolygonStation(start).getY()) -
                Double.parseDouble(polygonProject.getPolygonStation(end + 1).getY()));
        polygonProject.setfAbs(Math.hypot(polygonProject.getfX(), polygonProject.getfY()));
        polygonProject.setfOtn(new DataHandler(1 / (new DataHandler(polygonProject.getfAbs()).format(3).getDbl() /
                new DataHandler(polygonProject.getPerimeter()).format(3).getDbl())).format(0).getStr());
        for (int i = start; i <= end; i++) {
            polygonProject.getPolygonStation(i).setDDX(-1 * polygonProject.getfX() / polygonProject.getPerimeter() *
                    Double.parseDouble(polygonProject.getPolygonStation(i).getLine()));
            polygonProject.getPolygonStation(i).setDDY(-1 * polygonProject.getfY() / polygonProject.getPerimeter() *
                    Double.parseDouble(polygonProject.getPolygonStation(i).getLine()));
        }
    }

    /**
     * calculate fZ and sets ddZ for all llTheoStations with interval
     * @param start index of the start of the search
     * @param finish index of the end of the search
     */
    private void setDDZs(int start, int finish) {
        double sumZ = 0.0;
        for (int i = start; i <= finish; i++) {
            sumZ = sumZ + Double.parseDouble(polygonProject.getPolygonStation(i).getDZ());
        }
        polygonProject.setfZ(sumZ + Double.parseDouble(polygonProject.getPolygonStation(start).getZ()) -
                Double.parseDouble(polygonProject.getPolygonStation(finish +1).getZ()));
        for (int i = start; i <= finish; i++) {
            polygonProject.getPolygonStation(i).setDDZ(-1 * polygonProject.getfZ() / polygonProject.getPerimeter() *
                    Double.parseDouble(polygonProject.getPolygonStation(i).getLine()));
        }
    }

    /**
     * sets coordinates X, Y, Z in the specified range
     * @param start index of the start of the search
     * @param finish index of the end of the search
     */
    private void setXYZs(int start, int finish) {
        double parentX;
        double parentY;
        double parentZ;
        for (int i = start; i <= finish; i++) {
            parentX = Double.parseDouble(polygonProject.getPolygonStation(i - 1).getX());
            parentY = Double.parseDouble(polygonProject.getPolygonStation(i - 1).getY());
            parentZ = Double.parseDouble(polygonProject.getPolygonStation(i -1).getZ());
            polygonProject.getPolygonStation(i).setX(new DataHandler(parentX +
                    polygonProject.getPolygonStation(i - 1).getDX() +
                    polygonProject.getPolygonStation(i - 1).getDDX()).format(3).getStr());
            polygonProject.getPolygonStation(i).setY(new DataHandler(parentY +
                    polygonProject.getPolygonStation(i - 1).getDY() +
                    polygonProject.getPolygonStation(i - 1).getDDY()).format(3).getStr());
            polygonProject.getPolygonStation(i).setZ(new DataHandler(parentZ +
                    Double.parseDouble(polygonProject.getPolygonStation(i -1).getDZ()) +
                    polygonProject.getPolygonStation(i - 1).getDDZ()).format(3).getStr());
        }

    }

}
