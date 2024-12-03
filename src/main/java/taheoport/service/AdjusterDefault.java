package taheoport.service;

import taheoport.gui.MainWin;
import taheoport.repository.PolygonRepository;
import taheoport.model.PolygonStation;
import taheoport.model.SurveyStation;

import javax.swing.*;

/**
 * This class encapsulates data and methods for mathematics processing of polygon
 */
public class AdjusterDefault implements Adjuster{
    private final MainWin parentFrame;
    private PolygonRepository polygonRepository;

    public AdjusterDefault(MainWin frame) {

        parentFrame = frame;
        polygonRepository = parentFrame.getPolygonProject();
    }

    /**
     * Adjust the polygon
     */
    @Override
    public void adjustPolygon() {
        polygonRepository = parentFrame.getPolygonProject();
        int countStations = polygonRepository.getSizePolygonStations();
        polygonRepository.setPerimeter(0.0);
        double sumDX = 0.0;
        double sumDY = 0.0;
        double sumDZ = 0.0;
        GeoCalc geoCalc = new GeoCalc();
        defBindType();
        switch (polygonRepository.getBindType()) {
            case ZZ -> JOptionPane.showMessageDialog(parentFrame,
                    parentFrame.getTitles().get("TPmessageNoAdjustment"),
                    parentFrame.getTitles().get("TPmessageNoAdjustmentTitle"),
                    JOptionPane.ERROR_MESSAGE);
            case TT -> {
                iniDDs();
                defPerimeter(1, countStations - 3);
                polygonRepository.getPolygonStation(0).setDirection(Math.toDegrees(geoCalc.getDirAB(
                        polygonRepository.getPolygonStation(0).getX(),
                        polygonRepository.getPolygonStation(0).getY(),
                        polygonRepository.getPolygonStation(1).getX(),
                        polygonRepository.getPolygonStation(1).getY())));
                polygonRepository.getPolygonStation(countStations - 2).setDirection(Math.toDegrees(geoCalc.getDirAB(
                        polygonRepository.getPolygonStation(countStations - 2).getX(),
                        polygonRepository.getPolygonStation(countStations - 2).getY(),
                        polygonRepository.getPolygonStation(countStations - 1).getX(),
                        polygonRepository.getPolygonStation(countStations - 1).getY())));
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
                polygonRepository.getPolygonStation(0).setDirection(Math.toDegrees(new SurveyStation("Not",
                        polygonRepository.getPolygonStation(0).getX(),
                        polygonRepository.getPolygonStation(0).getY(),
                        polygonRepository.getPolygonStation(0).getZ(),
                        "Not",
                        polygonRepository.getPolygonStation(1).getX(),
                        polygonRepository.getPolygonStation(1).getY(),
                        polygonRepository.getPolygonStation(1).getZ(),
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
                polygonRepository.getPolygonStation(countStations - 2).setDirection(Math.toDegrees(new SurveyStation("Not",
                        polygonRepository.getPolygonStation(countStations - 2).getX(),
                        polygonRepository.getPolygonStation(countStations - 2).getY(),
                        polygonRepository.getPolygonStation(countStations - 2).getZ(),
                        "Not",
                        polygonRepository.getPolygonStation(countStations - 1).getX(),
                        polygonRepository.getPolygonStation(countStations - 1).getY(),
                        polygonRepository.getPolygonStation(countStations - 1).getZ(),
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
                polygonRepository.getPolygonStation(0).setDirection(0.0);
                setDirections(1, countStations - 2);
                setDXDYs(0, countStations - 2);
                for (int i = 0; i <= countStations - 2; i++) {
                    sumDX = sumDX + polygonRepository.getPolygonStation(i).getDX();
                    sumDY = sumDY + polygonRepository.getPolygonStation(i).getDY();
                }
                sumDX = sumDX + Double.parseDouble(polygonRepository.getPolygonStation(0).getX());
                sumDY = sumDY + Double.parseDouble(polygonRepository.getPolygonStation(0).getY());
                sumDZ = Math.toDegrees(new SurveyStation("Not",
                        polygonRepository.getPolygonStation(0).getX(),
                        polygonRepository.getPolygonStation(0).getY(),
                        "0.000",
                        "Not",
                        polygonRepository.getPolygonStation(countStations - 1).getX(),
                        polygonRepository.getPolygonStation(countStations - 1).getY(),
                        "0.000",
                        "0.000").getDirection()) -
                        Math.toDegrees(new SurveyStation("Not",
                                polygonRepository.getPolygonStation(0).getX(),
                                polygonRepository.getPolygonStation(0).getY(),
                                "0.000",
                                "Not",
                                new DataHandler(sumDX).format(3).getStr(),
                                new DataHandler(sumDY).format(3).getStr(),
                                "0.000",
                                "0.000").getDirection());
                while (sumDZ < 0.0) {
                    sumDZ = sumDZ + 360;
                }
                polygonRepository.getPolygonStation(0).setDirection(sumDZ);
                setDirections(1, countStations - 2);
                setDXDYs(0, countStations - 2);
                setDDXDDYs(0, countStations - 2);
                setDDZs(0, countStations - 2);
                setXYZs(1, countStations - 2);
            }
            case TZ -> {
                iniDDs();
                defPerimeter(1,countStations - 2);
                polygonRepository.getPolygonStation(0).setDirection(Math.toDegrees(new SurveyStation("Not",
                        polygonRepository.getPolygonStation(0).getX(),
                        polygonRepository.getPolygonStation(0).getY(),
                        polygonRepository.getPolygonStation(0).getZ(),
                        "Not",
                        polygonRepository.getPolygonStation(1).getX(),
                        polygonRepository.getPolygonStation(1).getY(),
                        polygonRepository.getPolygonStation(1).getZ(),
                        "0.000").getDirection()));
                setDirections(1, countStations - 2);
                setDXDYs(1, countStations - 2);
                setXYZs(2, countStations - 1);
            }
            case ZT -> {
                iniDDs();
                defPerimeter(0, countStations - 3);
                polygonRepository.getPolygonStation(countStations - 2).setDirection(Math.toDegrees(new SurveyStation("Not",
                        polygonRepository.getPolygonStation(countStations - 2).getX(),
                        polygonRepository.getPolygonStation(countStations - 2).getY(),
                        polygonRepository.getPolygonStation(countStations - 2).getZ(),
                        "Not",
                        polygonRepository.getPolygonStation(countStations - 1).getX(),
                        polygonRepository.getPolygonStation(countStations - 1).getY(),
                        polygonRepository.getPolygonStation(countStations - 1).getZ(),
                        "0.000").getDirection()));
                setDirections(countStations - 3, 0);
                setDXDYs(0, countStations - 3);
                for (int i = 0; i <= countStations - 3; i++) {
                    sumDX = sumDX + polygonRepository.getPolygonStation(i).getDX();
                    sumDY = sumDY + polygonRepository.getPolygonStation(i).getDY();
                    sumDZ = sumDZ + Double.parseDouble(polygonRepository.getPolygonStation(i).getDZ());
                }
                polygonRepository.getPolygonStation(0).setX(
                        new DataHandler(Double.parseDouble(polygonRepository.getPolygonStation(countStations - 2).getX()) -
                                sumDX).format(3).getStr());
                polygonRepository.getPolygonStation(0).setY(
                        new DataHandler(Double.parseDouble(polygonRepository.getPolygonStation(countStations - 2).getY()) -
                                sumDY).format(3).getStr());
                polygonRepository.getPolygonStation(0).setZ(
                        new DataHandler(Double.parseDouble(polygonRepository.getPolygonStation(countStations - 2).getZ()) -
                                sumDZ).format(3).getStr());
                setXYZs(1, countStations - 3);
            }
        }
    }

    /**
     * set and return bindings type of current TheoProject
     */
    private void defBindType() {
        polygonRepository.setBindType(PolygonRepository.BindType.ZZ);
        int countStations = polygonRepository.getSizePolygonStations();
        if (countStations > 2) {
            if (polygonRepository.getPolygonStation(0).getStatus() &
                    polygonRepository.getPolygonStation(1).getStatus() &
                    polygonRepository.getPolygonStation(countStations - 1).getStatus() &
                    polygonRepository.getPolygonStation(countStations - 2).getStatus()) {
                polygonRepository.setBindType(PolygonRepository.BindType.TT);
                if (isValidSourceData(1, countStations - 3) |
                        !new DataHandler(polygonRepository.getPolygonStation(countStations - 2).getHor()).isPositiveNumber()) {
                    polygonRepository.setBindType(PolygonRepository.BindType.ZZ);
                    JOptionPane.showMessageDialog(parentFrame,
                            parentFrame.getTitles().get("TPmessageError"),
                            parentFrame.getTitles().get("TPmessageErrorTitle"),
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if (polygonRepository.getPolygonStation(0).getStatus() &
                    polygonRepository.getPolygonStation(1).getStatus() &
                    polygonRepository.getPolygonStation(countStations - 1).getStatus() &
                    !polygonRepository.getPolygonStation(countStations - 2).getStatus()) {
                polygonRepository.setBindType(PolygonRepository.BindType.TO);
                if (isValidSourceData(1, countStations - 2)) {
                    polygonRepository.setBindType(PolygonRepository.BindType.ZZ);
                    JOptionPane.showMessageDialog(null,
                            parentFrame.getTitles().get("TPmessageError"),
                            parentFrame.getTitles().get("TPmessageErrorTitle"),
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if (polygonRepository.getPolygonStation(0).getStatus() &
                    !polygonRepository.getPolygonStation(1).getStatus() &
                    polygonRepository.getPolygonStation(countStations - 1).getStatus() &
                    polygonRepository.getPolygonStation(countStations - 2).getStatus()) {
                polygonRepository.setBindType(PolygonRepository.BindType.OT);
                if (isValidSourceData(0, countStations - 3) |
                        !new DataHandler(polygonRepository.getPolygonStation(countStations - 2).getHor()).isPositiveNumber()) {
                    polygonRepository.setBindType(PolygonRepository.BindType.ZZ);
                    JOptionPane.showMessageDialog(null,
                            parentFrame.getTitles().get("TPmessageError"),
                            parentFrame.getTitles().get("TPmessageErrorTitle"),
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if (polygonRepository.getPolygonStation(0).getStatus() &
                    !polygonRepository.getPolygonStation(1).getStatus() &
                    polygonRepository.getPolygonStation(countStations - 1).getStatus() &
                    !polygonRepository.getPolygonStation(countStations - 2).getStatus()) {
                polygonRepository.setBindType(PolygonRepository.BindType.OO);
                if (isValidSourceData(0, countStations - 2)) {
                    polygonRepository.setBindType(PolygonRepository.BindType.ZZ);
                    JOptionPane.showMessageDialog(null,
                            parentFrame.getTitles().get("TPmessageError"),
                            parentFrame.getTitles().get("TPmessageErrorTitle"),
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if (polygonRepository.getPolygonStation(0).getStatus() &
                    polygonRepository.getPolygonStation(1).getStatus() &
                    !polygonRepository.getPolygonStation(countStations - 1).getStatus() &
                    !polygonRepository.getPolygonStation(countStations - 2).getStatus()) {
                polygonRepository.setBindType(PolygonRepository.BindType.TZ);
                if (isValidSourceData(1, countStations - 3) |
                        !new DataHandler(polygonRepository.getPolygonStation(countStations - 2).getHor()).isPositiveNumber()) {
                    polygonRepository.setBindType(PolygonRepository.BindType.ZZ);
                    JOptionPane.showMessageDialog(null,
                            parentFrame.getTitles().get("TPmessageError"),
                            parentFrame.getTitles().get("TPmessageErrorTitle"),
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if (!polygonRepository.getPolygonStation(0).getStatus() &
                    !polygonRepository.getPolygonStation(1).getStatus() &
                    polygonRepository.getPolygonStation(countStations - 1).getStatus() &
                    polygonRepository.getPolygonStation(countStations - 2).getStatus()) {
                polygonRepository.setBindType(PolygonRepository.BindType.ZT);
                if (isValidSourceData(0, countStations - 3) |
                        !new DataHandler(polygonRepository.getPolygonStation(countStations - 2).getHor()).isPositiveNumber()) {
                    polygonRepository.setBindType(PolygonRepository.BindType.ZZ);
                    JOptionPane.showMessageDialog(null,
                            parentFrame.getTitles().get("TPmessageError"),
                            parentFrame.getTitles().get("TPmessageErrorTitle"),
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (noNeed()) {
            polygonRepository.setBindType(PolygonRepository.BindType.ZZ);
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
            if (!new DataHandler(polygonRepository.getPolygonStation(i).getName()).isValidName() |
                    !new DataHandler(polygonRepository.getPolygonStation(i).getHor()).isPositiveNumber() |
                    !new DataHandler(polygonRepository.getPolygonStation(i).getLine()).isPositiveNumber() |
                    !new DataHandler(polygonRepository.getPolygonStation(i).getDZ()).isNumber()) {
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
        for (int i = 0; i < polygonRepository.getSizePolygonStations(); i++) {
            if (!polygonRepository.getPolygonStation(i).getStatus()) {
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
            sum += Double.parseDouble(polygonRepository.getPolygonStation(i).getLine());
        }
        polygonRepository.setPerimeter(sum);
    }


    /**
     * initializes ddHor, ddX, ddY, ddZ for all listPolygonStations
     */
    private void iniDDs() {
//        for (PolygonStation station : listPolygonStatons) {
        PolygonStation station;
        for (int i = 0; i < polygonRepository.getSizePolygonStations(); i++) {
            station = polygonRepository.getPolygonStation(i);
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
        int countStations = polygonRepository.getSizePolygonStations();
        double d = polygonRepository.getPolygonStation(0).getDirection();
        for (int i = 1; i <= countStations - 2; i++) {
            hor = new DataHandler(polygonRepository.getPolygonStation(i).getHor());
            d = d + hor.dmsToDeg() + 180;
        }
        while (d >= 360) {
            d = d - 360;
        }
        polygonRepository.setfHor((d - polygonRepository.getPolygonStation(countStations - 2).getDirection()) * 3600);
        for (int i = 1; i <= countStations - 2; i++) {
            d = -1 * polygonRepository.getfHor() / (countStations - 2);
            polygonRepository.getPolygonStation(i).setDDHor(d);
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
                hor = new DataHandler(polygonRepository.getPolygonStation(i).getHor());
                dir = polygonRepository.getPolygonStation(i - 1).getDirection() + 180 + hor.dmsToDeg() +
                        polygonRepository.getPolygonStation(i).getDDHor() / 3600;
                while (dir >= 360) {
                    dir = dir - 360;
                }
                polygonRepository.getPolygonStation(i).setDirection(dir);
            }
        } else {
            for (int i = start; i >= finish; i--) {
                hor = new DataHandler(polygonRepository.getPolygonStation(i+1).getHor());
                dir = polygonRepository.getPolygonStation(i +1).getDirection() - 180 - hor.dmsToDeg();
                while (dir < 0) {
                    dir = dir + 360;
                }
                polygonRepository.getPolygonStation(i).setDirection(dir);
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
            line = new DataHandler(polygonRepository.getPolygonStation(i).getLine());
            polygonRepository.getPolygonStation(i).setDX(line.getDbl() *
                    Math.cos(Math.toRadians(polygonRepository.getPolygonStation(i).getDirection())));
            polygonRepository.getPolygonStation(i).setDY(line.getDbl() *
                    Math.sin(Math.toRadians(polygonRepository.getPolygonStation(i).getDirection())));
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
            sumDX = sumDX + polygonRepository.getPolygonStation(i).getDX();
            sumDY = sumDY + polygonRepository.getPolygonStation(i).getDY();
        }
        polygonRepository.setfX(sumDX + Double.parseDouble(polygonRepository.getPolygonStation(start).getX()) -
                Double.parseDouble(polygonRepository.getPolygonStation(end + 1).getX()));
        polygonRepository.setfY(sumDY + Double.parseDouble(polygonRepository.getPolygonStation(start).getY()) -
                Double.parseDouble(polygonRepository.getPolygonStation(end + 1).getY()));
        polygonRepository.setfAbs(Math.hypot(polygonRepository.getfX(), polygonRepository.getfY()));
        polygonRepository.setfOtn(new DataHandler(1 / (new DataHandler(polygonRepository.getfAbs()).format(3).getDbl() /
                new DataHandler(polygonRepository.getPerimeter()).format(3).getDbl())).format(0).getStr());
        for (int i = start; i <= end; i++) {
            polygonRepository.getPolygonStation(i).setDDX(-1 * polygonRepository.getfX() / polygonRepository.getPerimeter() *
                    Double.parseDouble(polygonRepository.getPolygonStation(i).getLine()));
            polygonRepository.getPolygonStation(i).setDDY(-1 * polygonRepository.getfY() / polygonRepository.getPerimeter() *
                    Double.parseDouble(polygonRepository.getPolygonStation(i).getLine()));
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
            sumZ = sumZ + Double.parseDouble(polygonRepository.getPolygonStation(i).getDZ());
        }
        polygonRepository.setfZ(sumZ + Double.parseDouble(polygonRepository.getPolygonStation(start).getZ()) -
                Double.parseDouble(polygonRepository.getPolygonStation(finish +1).getZ()));
        for (int i = start; i <= finish; i++) {
            polygonRepository.getPolygonStation(i).setDDZ(-1 * polygonRepository.getfZ() / polygonRepository.getPerimeter() *
                    Double.parseDouble(polygonRepository.getPolygonStation(i).getLine()));
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
            parentX = Double.parseDouble(polygonRepository.getPolygonStation(i - 1).getX());
            parentY = Double.parseDouble(polygonRepository.getPolygonStation(i - 1).getY());
            parentZ = Double.parseDouble(polygonRepository.getPolygonStation(i -1).getZ());
            polygonRepository.getPolygonStation(i).setX(new DataHandler(parentX +
                    polygonRepository.getPolygonStation(i - 1).getDX() +
                    polygonRepository.getPolygonStation(i - 1).getDDX()).format(3).getStr());
            polygonRepository.getPolygonStation(i).setY(new DataHandler(parentY +
                    polygonRepository.getPolygonStation(i - 1).getDY() +
                    polygonRepository.getPolygonStation(i - 1).getDDY()).format(3).getStr());
            polygonRepository.getPolygonStation(i).setZ(new DataHandler(parentZ +
                    Double.parseDouble(polygonRepository.getPolygonStation(i -1).getDZ()) +
                    polygonRepository.getPolygonStation(i - 1).getDDZ()).format(3).getStr());
        }

    }

}
