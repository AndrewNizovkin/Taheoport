package taheoport;

import java.util.LinkedList;

/**
 * This class converts coordinates for displayed on a panel
 * @author Andrey Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class PaintProject extends LinkedList<PaintPoint>{
    private Double xMin;
    private Double xMax;
    private Double yMin;
    private Double yMax;
    private Double scale;
    private int x0;
    private int y0;

    /**
     * Constructor
     * @param sp current SurveyProject
     * @param pWidth width of panel
     * @param pHeight heigth of panel
     */
    public PaintProject(SurveyProject sp, int pWidth, int pHeight) {
        super();
        if (sp != null) {
            PaintPoint sPoint;
            for (int i = 0; i < sp.sizeStations(); i++) {
                sPoint = new PaintPoint(sp.getStation(i).getName(), sp.getStation(i).getX(), sp.getStation(i).getY(), sp.getStation(i).getZ());
                sPoint.setxOr(sp.getStation(i).getxOr());
                sPoint.setyOr(sp.getStation(i).getyOr());
                sPoint.setStatus(true);
                add(sPoint);

                sPoint = new PaintPoint(sp.getStation(i).getNameOr(), sp.getStation(i).getxOr(), sp.getStation(i).getyOr(), sp.getStation(i).getzOr());
                sPoint.setxOr(sp.getStation(i).getX());
                sPoint.setyOr(sp.getStation(i).getY());
                sPoint.setStatus(false);
                add(sPoint);

                for (int j = 0; j < sp.getStation(i).sizePickets(); j++) {
                    PaintPoint pPoint = new PaintPoint(sp.getStation(i).getPicket(j).getpName(),
                            sp.getStation(i).getPicket(j).getX(),
                            sp.getStation(i).getPicket(j).getY(),
                            sp.getStation(i).getPicket(j).getZ());
                    pPoint.setxOr(sp.getStation(i).getX());
                    pPoint.setyOr(sp.getStation(i).getY());
                    pPoint.setStatus(false);
                    add(pPoint);
                }
            }
            xMin = Double.parseDouble(get(0).getX());
            xMax = Double.parseDouble(get(0).getX());
            yMax = Double.parseDouble((get(0).getY()));
            yMin = Double.parseDouble((get(0).getY()));
            for (PaintPoint paintPoint : this) {
                xMin = xMin < Double.parseDouble(paintPoint.getX()) ? xMin : Double.parseDouble(paintPoint.getX());
                yMin = yMin < Double.parseDouble(paintPoint.getY()) ? yMin : Double.parseDouble(paintPoint.getY());
                xMax = xMax > Double.parseDouble(paintPoint.getX()) ? xMax : Double.parseDouble(paintPoint.getX());
                yMax = yMax > Double.parseDouble(paintPoint.getY()) ? yMax : Double.parseDouble(paintPoint.getY());
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
     * @param theoProject TheoProject
     * @param pWidth width of panel
     * @param pHeight height of panel
     */
    public PaintProject(PolygonProject theoProject, int pWidth, int pHeight) {
        super();
        if (theoProject != null) {
            PaintPoint tPoint;
            for (int i = 0; i < theoProject.getSizeTheoStations(); i++) {
                tPoint = new PaintPoint(theoProject.getTheoStation(i).getName(),
                        theoProject.getTheoStation(i).getX(),
                        theoProject.getTheoStation(i).getY(),
                        theoProject.getTheoStation(i).getZ());
                tPoint.setStatus(theoProject.getTheoStation(i).getStatus());
                add(tPoint);
            }
            xMin = Double.parseDouble(get(0).getX());
            xMax = Double.parseDouble(get(0).getX());
            yMax = Double.parseDouble((get(0).getY()));
            yMin = Double.parseDouble((get(0).getY()));
            for (PaintPoint paintPoint : this) {
                xMin = xMin < Double.parseDouble(paintPoint.getX()) ? xMin : Double.parseDouble(paintPoint.getX());
                yMin = yMin < Double.parseDouble(paintPoint.getY()) ? yMin : Double.parseDouble(paintPoint.getY());
                xMax = xMax > Double.parseDouble(paintPoint.getX()) ? xMax : Double.parseDouble(paintPoint.getX());
                yMax = yMax > Double.parseDouble(paintPoint.getY()) ? yMax : Double.parseDouble(paintPoint.getY());
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
                    xMin = xMin < Double.parseDouble(paintPoint.getX()) ? xMin : Double.parseDouble(paintPoint.getX());
                    yMin = yMin < Double.parseDouble(paintPoint.getY()) ? yMin : Double.parseDouble(paintPoint.getY());
                    xMax = xMax > Double.parseDouble(paintPoint.getX()) ? xMax : Double.parseDouble(paintPoint.getX());
                    yMax = yMax > Double.parseDouble(paintPoint.getY()) ? yMax : Double.parseDouble(paintPoint.getY());
                    xMin = xMin < Double.parseDouble(paintPoint.getxOr()) ? xMin : Double.parseDouble(paintPoint.getxOr());
                    yMin = yMin < Double.parseDouble(paintPoint.getyOr()) ? yMin : Double.parseDouble(paintPoint.getyOr());
                    xMax = xMax > Double.parseDouble(paintPoint.getxOr()) ? xMax : Double.parseDouble(paintPoint.getxOr());
                    yMax = yMax > Double.parseDouble(paintPoint.getyOr()) ? yMax : Double.parseDouble(paintPoint.getyOr());

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
    public Double getScale() {
        return scale;
    }
}
