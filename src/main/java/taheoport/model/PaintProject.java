package taheoport.model;

import taheoport.repository.PolygonRepository;

import java.util.LinkedList;
import java.util.List;

/**
 * This class converts coordinates for displayed on a panel
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class PaintProject extends LinkedList<PaintPoint>{
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;
    private double scale;
    private int x0;
    private int y0;

    /**
     * Constructor
     */
    public PaintProject() {
        super();
    }

    /**
     * Creates panel for survey project
     * @param surveyStations list of survey Station
     * @param pWidth width of panel
     * @param pHeight heigth of panel
     */
    public void createSurveyPaintProject(List<SurveyStation> surveyStations, int pWidth, int pHeight) {
        if (surveyStations != null) {
            PaintPoint sPoint;
            for (SurveyStation surveyStation : surveyStations) {
                sPoint = new PaintPoint(surveyStation.getName(),
                        surveyStation.getX(),
                        surveyStation.getY(),
                        surveyStation.getZ());
                sPoint.setxOr(surveyStation.getxOr());
                sPoint.setyOr(surveyStation.getyOr());
                sPoint.setStatus(true);
                add(sPoint);

                sPoint = new PaintPoint(
                        surveyStation.getNameOr(),
                        surveyStation.getxOr(),
                        surveyStation.getyOr(),
                        surveyStation.getzOr());
                sPoint.setxOr(surveyStation.getX());
                sPoint.setyOr(surveyStation.getY());
                sPoint.setStatus(false);
                add(sPoint);

                for (int j = 0; j < surveyStation.sizePickets(); j++) {
                    PaintPoint pPoint = new PaintPoint(surveyStation.getPicket(j).getpName(),
                            surveyStation.getPicket(j).getX(),
                            surveyStation.getPicket(j).getY(),
                            surveyStation.getPicket(j).getZ());
                    pPoint.setxOr(surveyStation.getX());
                    pPoint.setyOr(surveyStation.getY());
                    pPoint.setStatus(false);
                    add(pPoint);
                }
            }
            xMin = Double.parseDouble(get(0).getX());
            xMax = Double.parseDouble(get(0).getX());
            yMax = Double.parseDouble((get(0).getY()));
            yMin = Double.parseDouble((get(0).getY()));
            for (PaintPoint paintPoint : this) {
                xMin = Math.min(xMin, Double.parseDouble(paintPoint.getX()));
                yMin = Math.min(yMin, Double.parseDouble(paintPoint.getY()));
                xMax = Math.max(xMax, Double.parseDouble(paintPoint.getX()));
                yMax = Math.max(yMax, Double.parseDouble(paintPoint.getY()));
            }
                scale = (xMax - xMin) / (pHeight - 20);
                if (scale < ((yMax - yMin) / (pWidth - 20))) {
                    scale = (yMax - yMin) / (pWidth - 20);
                    x0 = 0;
                    y0 = (pHeight - (int) ((xMax - xMin) / scale)) / 2;
                } else {
                    x0 = (pWidth - (int) ((yMax - yMin) / scale)) / 2;
                    y0 = 0;
                }
                if (scale == 0) {
                    scale = 1.0;
                }

            PaintPoint point;
            for (PaintPoint paintPoint : this) {
                point = paintPoint;
                point.setxPaint(x0 + 10 + (int) ((point.getyDbl() - yMin) / scale));
                point.setxOrPaint(x0 + 10 + (int) ((point.getyOrDbl() - yMin) / scale));
                point.setyPaint(pHeight - y0 - 10 - (int) ((point.getxDbl() - xMin) / scale));
                point.setyOrPaint(pHeight - y0 - 10 - (int) ((point.getxOrDbl() - xMin) / scale));
            }
        }
    }

    /**
     * Creates panel for polygon project
     * @param polygonStations Polygon station list
     * @param pWidth width of panel
     * @param pHeight height of panel
     */
    public void createPolygonPaintProject(List<PolygonStation> polygonStations, int pWidth, int pHeight) {
        if (polygonStations != null) {
            PaintPoint tPoint;
            for (PolygonStation polygonStation: polygonStations) {
                tPoint = new PaintPoint(polygonStation.getName(),
                        polygonStation.getX(),
                        polygonStation.getY(),
                        polygonStation.getZ());
                tPoint.setStatus(polygonStation.getStatus());
                add(tPoint);
            }
            xMin = Double.parseDouble(get(0).getX());
            xMax = Double.parseDouble(get(0).getX());
            yMax = Double.parseDouble((get(0).getY()));
            yMin = Double.parseDouble((get(0).getY()));
            for (PaintPoint paintPoint : this) {
                xMin = Math.min(xMin, Double.parseDouble(paintPoint.getX()));
                yMin = Math.min(yMin, Double.parseDouble(paintPoint.getY()));
                xMax = Math.max(xMax, Double.parseDouble(paintPoint.getX()));
                yMax = Math.max(yMax, Double.parseDouble(paintPoint.getY()));
            }
            scale = (xMax - xMin) / (pHeight - 20);

            if (scale < ((yMax - yMin) / (pWidth - 20))) {
                scale = (yMax - yMin) / (pWidth - 20);
                x0 = 0;
                y0 = (pHeight - (int) ((xMax - xMin) / scale)) / 2;
            } else {
                x0 = (pWidth - (int) ((yMax - yMin) / scale)) / 2;
                y0 = 0;
            }

            if (scale == 0) {
                scale = 1.0;
            }
            for (PaintPoint paintPoint : this) {
                paintPoint.setxPaint(x0 + 10 + (int) ((paintPoint.getyDbl() - yMin) / scale));
                paintPoint.setyPaint(pHeight - y0 - 10 - (int) ((paintPoint.getxDbl() - xMin) / scale));
            }
        }
    }

    /**
     * Constructor with LinkedList
     * @param paintPoints linkedList
     * @param pWidth width of panel
     * @param pHeight height of panel
     */
//    public void createPaintProject(List<PaintPoint> paintPoints, int pWidth, int pHeight ) {
//        if (paintPoints != null) {
//            this.addAll(paintPoints);
//
//            xMin = Double.parseDouble(get(0).getX());
//            xMax = Double.parseDouble(get(0).getX());
//            yMax = Double.parseDouble((get(0).getY()));
//            yMin = Double.parseDouble((get(0).getY()));
//            for (PaintPoint paintPoint : this) {
//                if (!paintPoint.getStatus()) {
//                    xMin = Math.min(xMin, Double.parseDouble(paintPoint.getX()));
//                    yMin = Math.min(yMin, Double.parseDouble(paintPoint.getY()));
//                    xMax = Math.max(xMax, Double.parseDouble(paintPoint.getX()));
//                    yMax = Math.max(yMax, Double.parseDouble(paintPoint.getY()));
//                    xMin = Math.min(xMin, Double.parseDouble(paintPoint.getxOr()));
//                    yMin = Math.min(yMin, Double.parseDouble(paintPoint.getyOr()));
//                    xMax = Math.max(xMax, Double.parseDouble(paintPoint.getxOr()));
//                    yMax = Math.max(yMax, Double.parseDouble(paintPoint.getyOr()));
//
//                }
//            }
//            scale = (xMax - xMin) / (pHeight);
//
//            if (scale < ((yMax - yMin) / (pWidth))) {
//                scale = (yMax - yMin) / (pWidth);
//                x0 = 0;
//                y0 = (pHeight - (int) ((xMax - xMin) / scale)) / 2;
//            } else {
//                x0 = (pWidth - (int) ((yMax - yMin) / scale)) / 2;
//                y0 = 0;
//            }
//
//            if (scale == 0) {
//                scale = 1.0;
//            }
//            for (PaintPoint paintPoint : this) {
//                paintPoint.setxPaint(x0 + (int) ((paintPoint.getyDbl() - yMin) / scale));
//                paintPoint.setyPaint(pHeight - y0 - (int) ((paintPoint.getxDbl() - xMin) / scale));
//                if (!paintPoint.getStatus()) {
//                    paintPoint.setxOrPaint(x0 + (int) ((paintPoint.getyOrDbl() - yMin) / scale));
//                    paintPoint.setyPaint(pHeight - y0 - (int) ((paintPoint.getxOrDbl() - xMin) / scale));
//                } else {
//                    paintPoint.setxOrPaint((int) (paintPoint.getyOrDbl() / scale));
//                    paintPoint.setyPaint((int) (paintPoint.getxOrDbl() / scale));
//                }
//            }
//        }
//    }

    /**
     * Gets scale
     * @return Scale
     */
    public double getScale() {
        return scale;
    }
}
