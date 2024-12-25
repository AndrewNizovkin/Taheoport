package taheoport.service;

import taheoport.dispatcher.DependencyContainer;
import taheoport.dispatcher.DependencyInjector;
import taheoport.gui.MainWin;
import taheoport.model.BindType;
import taheoport.model.PolygonStation;
import taheoport.model.SurveyStation;

import javax.swing.*;
import java.util.HashMap;

/**
 * This class encapsulates data and methods for mathematics processing of polygon
 */
public class AdjusterDefault implements Adjuster{
    private final JFrame parentFrame;
    private final PolygonService polygonService;
    private final Shell shell;

    public AdjusterDefault(PolygonService polygonService) {
        DependencyInjector dependencyInjector = DependencyContainer.getInstance();
        this.polygonService = polygonService;
        shell = dependencyInjector.getShell();
        parentFrame = dependencyInjector.getMainFrame();
    }

    /**
     * Adjust the polygon
     */
    @Override
    public void adjustPolygon() {
        int countStations = polygonService.getSizePolygonStations();
        if (countStations < 2) {
            JOptionPane.showMessageDialog(parentFrame,
                    shell.getTitles().get("TPmessageNoAdjustment"),
                    shell.getTitles().get("TPmessageNoAdjustmentTitle"),
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        polygonService.setPerimeter(0.0);
        double sumDX = 0.0;
        double sumDY = 0.0;
        double sumDZ = 0.0;
//        GeoCalculator geoCalculator = new GeoCalculator();
        defBindType();
        PolygonStation basePointA = polygonService.findById(0);
        PolygonStation basePointB = polygonService.findById(1);
        PolygonStation basePointC = polygonService.findById(countStations - 2);
        PolygonStation basePointD = polygonService.findById(countStations - 1);
        switch (polygonService.getBindType()) {
            case ZZ -> JOptionPane.showMessageDialog(parentFrame,
                    shell.getTitles().get("TPmessageNoAdjustment"),
                    shell.getTitles().get("TPmessageNoAdjustmentTitle"),
                    JOptionPane.ERROR_MESSAGE);
            case TT -> {
                iniDDs();
                defPerimeter(1, countStations - 3);
                basePointA.setDirection(Math.toDegrees(GeoCalculator.getDirAB(
                        basePointA.getX(),
                        basePointA.getY(),
                        basePointB.getX(),
                        basePointB.getY())));
                basePointC.setDirection(Math.toDegrees(GeoCalculator.getDirAB(
                        basePointC.getX(),
                        basePointC.getY(),
                        basePointD.getX(),
                        basePointD.getY())));
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
                basePointA.setDirection(Math.toDegrees(new SurveyStation("Not",
                        basePointA.getX(),
                        basePointA.getY(),
                        basePointA.getZ(),
                        "Not",
                        basePointB.getX(),
                        basePointB.getY(),
                        basePointB.getZ(),
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
                basePointC.setDirection(Math.toDegrees(new SurveyStation("Not",
                        basePointC.getX(),
                        basePointC.getY(),
                        basePointC.getZ(),
                        "Not",
                        basePointD.getX(),
                        basePointD.getY(),
                        basePointD.getZ(),
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
                basePointA.setDirection(0.0);
                setDirections(1, countStations - 2);
                setDXDYs(0, countStations - 2);
                for (int i = 0; i <= countStations - 2; i++) {
                    sumDX = sumDX + polygonService.findById(i).getDX();
                    sumDY = sumDY + polygonService.findById(i).getDY();
                }
                sumDX = sumDX + Double.parseDouble(basePointA.getX());
                sumDY = sumDY + Double.parseDouble(basePointA.getY());
                sumDZ = Math.toDegrees(new SurveyStation("Not",
                        basePointA.getX(),
                        basePointA.getY(),
                        "0.000",
                        "Not",
                        basePointD.getX(),
                        basePointD.getY(),
                        "0.000",
                        "0.000").getDirection()) -
                        Math.toDegrees(new SurveyStation("Not",
                                basePointA.getX(),
                                basePointA.getY(),
                                "0.000",
                                "Not",
                                new DataHandler(sumDX).format(3).getStr(),
                                new DataHandler(sumDY).format(3).getStr(),
                                "0.000",
                                "0.000").getDirection());
                while (sumDZ < 0.0) {
                    sumDZ = sumDZ + 360;
                }
                basePointA.setDirection(sumDZ);
                setDirections(1, countStations - 2);
                setDXDYs(0, countStations - 2);
                setDDXDDYs(0, countStations - 2);
                setDDZs(0, countStations - 2);
                setXYZs(1, countStations - 2);
            }
            case TZ -> {
                iniDDs();
                defPerimeter(1,countStations - 2);
                basePointA.setDirection(Math.toDegrees(new SurveyStation("Not",
                        basePointA.getX(),
                        basePointA.getY(),
                        basePointA.getZ(),
                        "Not",
                        basePointB.getX(),
                        basePointB.getY(),
                        basePointB.getZ(),
                        "0.000").getDirection()));
                setDirections(1, countStations - 2);
                setDXDYs(1, countStations - 2);
                setXYZs(2, countStations - 1);
            }
            case ZT -> {
                iniDDs();
                defPerimeter(0, countStations - 3);
                basePointC.setDirection(Math.toDegrees(new SurveyStation("Not",
                        basePointC.getX(),
                        basePointC.getY(),
                        basePointC.getZ(),
                        "Not",
                        basePointD.getX(),
                        basePointD.getY(),
                        basePointD.getZ(),
                        "0.000").getDirection()));
                setDirections(countStations - 3, 0);
                setDXDYs(0, countStations - 3);
                for (int i = 0; i <= countStations - 3; i++) {
                    sumDX = sumDX + polygonService.findById(i).getDX();
                    sumDY = sumDY + polygonService.findById(i).getDY();
                    sumDZ = sumDZ + Double.parseDouble(polygonService.findById(i).getDZ());
                }
                basePointA.setX(
                        new DataHandler(Double.parseDouble(basePointC.getX()) -
                                sumDX).format(3).getStr());
                basePointA.setY(
                        new DataHandler(Double.parseDouble(basePointC.getY()) -
                                sumDY).format(3).getStr());
                basePointA.setZ(
                        new DataHandler(Double.parseDouble(basePointC.getZ()) -
                                sumDZ).format(3).getStr());
                setXYZs(1, countStations - 3);
            }
        }
    }

    /**
     * set and return bindings type of current TheoProject
     */
    private void defBindType() {
        HashMap<String,String> titles = shell.getTitles();
        polygonService.setBindType(BindType.ZZ);
        int countStations = polygonService.getSizePolygonStations();
        PolygonStation basePointA = polygonService.findById(0);
        PolygonStation basePointB = polygonService.findById(1);
        PolygonStation basePointC = polygonService.findById(countStations - 2);
        PolygonStation basePointD = polygonService.findById(countStations - 1);

        if (countStations > 2) {
            if (basePointA.getStatus() &
                    basePointB.getStatus() &
                    basePointD.getStatus() &
                    basePointC.getStatus()) {
                polygonService.setBindType(BindType.TT);
                if (isValidSourceData(1, countStations - 3) |
                        !new DataHandler(basePointC.getHor()).isPositiveNumber()) {
                    polygonService.setBindType(BindType.ZZ);
                    JOptionPane.showMessageDialog(parentFrame,
                            titles.get("TPmessageError"),
                            titles.get("TPmessageErrorTitle"),
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if (basePointA.getStatus() &
                    basePointB.getStatus() &
                    basePointD.getStatus() &
                    !basePointC.getStatus()) {
                polygonService.setBindType(BindType.TO);
                if (isValidSourceData(1, countStations - 2)) {
                    polygonService.setBindType(BindType.ZZ);
                    JOptionPane.showMessageDialog(null,
                            titles.get("TPmessageError"),
                            titles.get("TPmessageErrorTitle"),
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if (basePointA.getStatus() &
                    !basePointB.getStatus() &
                    basePointD.getStatus() &
                    basePointC.getStatus()) {
                polygonService.setBindType(BindType.OT);
                if (isValidSourceData(0, countStations - 3) |
                        !new DataHandler(basePointC.getHor()).isPositiveNumber()) {
                    polygonService.setBindType(BindType.ZZ);
                    JOptionPane.showMessageDialog(null,
                            titles.get("TPmessageError"),
                            titles.get("TPmessageErrorTitle"),
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if (basePointA.getStatus() &
                    !basePointB.getStatus() &
                    basePointD.getStatus() &
                    !basePointC.getStatus()) {
                polygonService.setBindType(BindType.OO);
                if (isValidSourceData(0, countStations - 2)) {
                    polygonService.setBindType(BindType.ZZ);
                    JOptionPane.showMessageDialog(null,
                            titles.get("TPmessageError"),
                            titles.get("TPmessageErrorTitle"),
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if (basePointA.getStatus() &
                    basePointB.getStatus() &
                    !basePointD.getStatus() &
                    !basePointC.getStatus()) {
                polygonService.setBindType(BindType.TZ);
                if (isValidSourceData(1, countStations - 3) |
                        !new DataHandler(basePointC.getHor()).isPositiveNumber()) {
                    polygonService.setBindType(BindType.ZZ);
                    JOptionPane.showMessageDialog(null,
                            titles.get("TPmessageError"),
                            titles.get("TPmessageErrorTitle"),
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if (!basePointA.getStatus() &
                    !basePointB.getStatus() &
                    basePointD.getStatus() &
                    basePointC.getStatus()) {
                polygonService.setBindType(BindType.ZT);
                if (isValidSourceData(0, countStations - 3) |
                        !new DataHandler(basePointC.getHor()).isPositiveNumber()) {
                    polygonService.setBindType(BindType.ZZ);
                    JOptionPane.showMessageDialog(null,
                            titles.get("TPmessageError"),
                            titles.get("TPmessageErrorTitle"),
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (noNeed()) {
            polygonService.setBindType(BindType.ZZ);
        }
    }

    /**
     * Сhecks the source data for errors
     * @param start index of the start of the search
     * @param finish index of the end of the search
     * @return boolean
     */
    private boolean isValidSourceData(int start, int finish) {
//        polygonRepository = parentFrame.getPolygonRepository();
        for (int i = start; i <= finish; i++) {
            if (!new DataHandler(polygonService.findById(i).getName()).isValidName() |
                    !new DataHandler(polygonService.findById(i).getHor()).isPositiveNumber() |
                    !new DataHandler(polygonService.findById(i).getLine()).isPositiveNumber() |
                    !new DataHandler(polygonService.findById(i).getDZ()).isNumber()) {
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
//        polygonRepository = parentFrame.getPolygonRepository();
        for (int i = 0; i < polygonService.getSizePolygonStations(); i++) {
            if (!polygonService.findById(i).getStatus()) {
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
            sum += Double.parseDouble(polygonService.findById(i).getLine());
        }
        polygonService.setPerimeter(sum);
    }


    /**
     * initializes ddHor, ddX, ddY, ddZ for all listPolygonStations
     */
    private void iniDDs() {
//        for (PolygonStation station : listPolygonStatons) {
        PolygonStation station;
        for (int i = 0; i < polygonService.getSizePolygonStations(); i++) {
            station = polygonService.findById(i);
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
        int countStations = polygonService.getSizePolygonStations();
        double d = polygonService.findById(0).getDirection();
        for (int i = 1; i <= countStations - 2; i++) {
            hor = new DataHandler(polygonService.findById(i).getHor());
            d = d + hor.dmsToDeg() + 180;
        }
        while (d >= 360) {
            d = d - 360;
        }
        polygonService.setfHor((d - polygonService.findById(countStations - 2).getDirection()) * 3600);
        for (int i = 1; i <= countStations - 2; i++) {
            d = -1 * polygonService.getfHor() / (countStations - 2);
            polygonService.findById(i).setDDHor(d);
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
                hor = new DataHandler(polygonService.findById(i).getHor());
                dir = polygonService.findById(i - 1).getDirection() + 180 + hor.dmsToDeg() +
                        polygonService.findById(i).getDDHor() / 3600;
                while (dir >= 360) {
                    dir = dir - 360;
                }
                polygonService.findById(i).setDirection(dir);
            }
        } else {
            for (int i = start; i >= finish; i--) {
                hor = new DataHandler(polygonService.findById(i + 1).getHor());
                dir = polygonService.findById(i + 1).getDirection() - 180 - hor.dmsToDeg();
                while (dir < 0) {
                    dir = dir + 360;
                }
                polygonService.findById(i).setDirection(dir);
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
            line = new DataHandler(polygonService.findById(i).getLine());
            polygonService.findById(i).setDX(line.getDbl() *
                    Math.cos(Math.toRadians(polygonService.findById(i).getDirection())));
            polygonService.findById(i).setDY(line.getDbl() *
                    Math.sin(Math.toRadians(polygonService.findById(i).getDirection())));
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
            sumDX = sumDX + polygonService.findById(i).getDX();
            sumDY = sumDY + polygonService.findById(i).getDY();
        }
        polygonService.setfX(sumDX + Double.parseDouble(polygonService.findById(start).getX()) -
                Double.parseDouble(polygonService.findById(end + 1).getX()));
        polygonService.setfY(sumDY + Double.parseDouble(polygonService.findById(start).getY()) -
                Double.parseDouble(polygonService.findById(end + 1).getY()));
        polygonService.setfAbs(Math.hypot(polygonService.getfX(), polygonService.getfY()));
        polygonService.setfOtn(new DataHandler(1 / (new DataHandler(polygonService.getfAbs()).format(3).getDbl() /
                new DataHandler(polygonService.getPerimeter()).format(3).getDbl())).format(0).getStr());
        for (int i = start; i <= end; i++) {
            polygonService.findById(i).setDDX(-1 * polygonService.getfX() / polygonService.getPerimeter() *
                    Double.parseDouble(polygonService.findById(i).getLine()));
            polygonService.findById(i).setDDY(-1 * polygonService.getfY() / polygonService.getPerimeter() *
                    Double.parseDouble(polygonService.findById(i).getLine()));
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
            sumZ = sumZ + Double.parseDouble(polygonService.findById(i).getDZ());
        }
        polygonService.setfZ(sumZ + Double.parseDouble(polygonService.findById(start).getZ()) -
                Double.parseDouble(polygonService.findById(finish +1).getZ()));
        for (int i = start; i <= finish; i++) {
            polygonService.findById(i).setDDZ(-1 * polygonService.getfZ() / polygonService.getPerimeter() *
                    Double.parseDouble(polygonService.findById(i).getLine()));
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
            parentX = Double.parseDouble(polygonService.findById(i - 1).getX());
            parentY = Double.parseDouble(polygonService.findById(i - 1).getY());
            parentZ = Double.parseDouble(polygonService.findById(i -1).getZ());
            polygonService.findById(i).setX(new DataHandler(parentX +
                    polygonService.findById(i - 1).getDX() +
                    polygonService.findById(i - 1).getDDX()).format(3).getStr());
            polygonService.findById(i).setY(new DataHandler(parentY +
                    polygonService.findById(i - 1).getDY() +
                    polygonService.findById(i - 1).getDDY()).format(3).getStr());
            polygonService.findById(i).setZ(new DataHandler(parentZ +
                    Double.parseDouble(polygonService.findById(i -1).getDZ()) +
                    polygonService.findById(i - 1).getDDZ()).format(3).getStr());
        }

    }
}
