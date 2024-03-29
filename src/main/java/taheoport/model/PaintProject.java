package taheoport.model;

import java.util.LinkedList;

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
     * @param surveyProject current SurveyProject
     * @param pWidth width of panel
     * @param pHeight heigth of panel
     */
    public PaintProject(SurveyProject surveyProject, int pWidth, int pHeight) {
        super();
        if (surveyProject != null) {
            PaintPoint sPoint;
            for (int i = 0; i < surveyProject.sizeStations(); i++) {
                sPoint = new PaintPoint(surveyProject.getStation(i).getName(),
                        surveyProject.getStation(i).getX(),
                        surveyProject.getStation(i).getY(),
                        surveyProject.getStation(i).getZ());
                sPoint.setxOr(surveyProject.getStation(i).getxOr());
                sPoint.setyOr(surveyProject.getStation(i).getyOr());
                sPoint.setStatus(true);
                add(sPoint);

                sPoint = new PaintPoint(surveyProject.getStation(i).getNameOr(), surveyProject.getStation(i).getxOr(), surveyProject.getStation(i).getyOr(), surveyProject.getStation(i).getzOr());
                sPoint.setxOr(surveyProject.getStation(i).getX());
                sPoint.setyOr(surveyProject.getStation(i).getY());
                sPoint.setStatus(false);
                add(sPoint);

                for (int j = 0; j < surveyProject.getStation(i).sizePickets(); j++) {
                    PaintPoint pPoint = new PaintPoint(surveyProject.getStation(i).getPicket(j).getpName(),
                            surveyProject.getStation(i).getPicket(j).getX(),
                            surveyProject.getStation(i).getPicket(j).getY(),
                            surveyProject.getStation(i).getPicket(j).getZ());
                    pPoint.setxOr(surveyProject.getStation(i).getX());
                    pPoint.setyOr(surveyProject.getStation(i).getY());
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
     * Constructor with TheoProject
     * @param polygonProject TheoProject
     * @param pWidth width of panel
     * @param pHeight height of panel
     */
    public PaintProject(PolygonProject polygonProject, int pWidth, int pHeight) {
        super();
        if (polygonProject != null) {
            PaintPoint tPoint;
            for (int i = 0; i < polygonProject.getSizePolygonStations(); i++) {
                tPoint = new PaintPoint(polygonProject.getPolygonStation(i).getName(),
                        polygonProject.getPolygonStation(i).getX(),
                        polygonProject.getPolygonStation(i).getY(),
                        polygonProject.getPolygonStation(i).getZ());
                tPoint.setStatus(polygonProject.getPolygonStation(i).getStatus());
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
    public PaintProject(LinkedList<PaintPoint> paintPoints, int pWidth, int pHeight ) {
        super();
        if (paintPoints != null) {
            this.addAll(paintPoints);

            xMin = Double.parseDouble(get(0).getX());
            xMax = Double.parseDouble(get(0).getX());
            yMax = Double.parseDouble((get(0).getY()));
            yMin = Double.parseDouble((get(0).getY()));
            for (PaintPoint paintPoint : this) {
                if (!paintPoint.getStatus()) {
                    xMin = Math.min(xMin, Double.parseDouble(paintPoint.getX()));
                    yMin = Math.min(yMin, Double.parseDouble(paintPoint.getY()));
                    xMax = Math.max(xMax, Double.parseDouble(paintPoint.getX()));
                    yMax = Math.max(yMax, Double.parseDouble(paintPoint.getY()));
                    xMin = Math.min(xMin, Double.parseDouble(paintPoint.getxOr()));
                    yMin = Math.min(yMin, Double.parseDouble(paintPoint.getyOr()));
                    xMax = Math.max(xMax, Double.parseDouble(paintPoint.getxOr()));
                    yMax = Math.max(yMax, Double.parseDouble(paintPoint.getyOr()));

                }
            }
            scale = (xMax - xMin) / (pHeight);

            if (scale < ((yMax - yMin) / (pWidth))) {
                scale = (yMax - yMin) / (pWidth);
                x0 = 0;
                y0 = (pHeight - (int) ((xMax - xMin) / scale)) / 2;
            } else {
                x0 = (pWidth - (int) ((yMax - yMin) / scale)) / 2;
                y0 = 0;
            }

            if (scale == 0) {
                scale = 1.0;
            }
//            PaintPoint point;
            for (PaintPoint paintPoint : this) {
//                point = paintPoint;
                paintPoint.setxPaint(x0 + (int) ((paintPoint.getyDbl() - yMin) / scale));
                paintPoint.setyPaint(pHeight - y0 - (int) ((paintPoint.getxDbl() - xMin) / scale));
                if (!paintPoint.getStatus()) {
                    paintPoint.setxOrPaint(x0 + (int) ((paintPoint.getyOrDbl() - yMin) / scale));
                    paintPoint.setyPaint(pHeight - y0 - (int) ((paintPoint.getxOrDbl() - xMin) / scale));
                } else {
                    paintPoint.setxOrPaint((int) (paintPoint.getyOrDbl() / scale));
                    paintPoint.setyPaint((int) (paintPoint.getxOrDbl() / scale));
                }
            }
        }
    }

    /**
     * Gets scale
     * @return Scale
     */
    public double getScale() {
        return scale;
    }
}
