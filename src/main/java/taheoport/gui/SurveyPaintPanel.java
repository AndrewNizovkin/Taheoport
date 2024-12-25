package taheoport.gui;

import taheoport.model.PaintPoint;
import taheoport.repository.PaintPointRepository;
import taheoport.model.SurveyStation;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Display results of processing and highlights current position
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class SurveyPaintPanel extends JPanel {
    private int index;
    private final List<SurveyStation> surveyStations;

    /**
     * Constructor
     * @param sellRow index of selected row
     * @param surveyStationList SurveyStation list
     */
    public SurveyPaintPanel(List<SurveyStation> surveyStationList, int sellRow) {
        super();
        surveyStations = surveyStationList;
        index = sellRow;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        PaintPointRepository ppPaintPoints = new PaintPointRepository();
        ppPaintPoints.createSurveyPaintRepository(surveyStations, this.getWidth(), this.getHeight());
        if (ppPaintPoints.getScale() < 10) {
            for (PaintPoint paintPoint : ppPaintPoints) {
                if (!paintPoint.getStatus()) {
                    g.setColor(Color.GRAY);

                    g.drawLine(paintPoint.getxPaint(),
                            paintPoint.getyPaint(),
                            paintPoint.getxOrPaint(),
                            paintPoint.getyOrPaint());
                }

            }

            for (PaintPoint ppPaintPoint : ppPaintPoints) {
                if (!ppPaintPoint.getStatus()) {
                    g.setColor(Color.GRAY);
                    g.fillOval(ppPaintPoint.getxPaint() - 3, ppPaintPoint.getyPaint() - 3, 6, 6);

                }
            }
            for (PaintPoint ppPaintPoint : ppPaintPoints) {
                if (ppPaintPoint.getStatus()) {
                    g.setColor(Color.BLUE);

                    g.drawLine(ppPaintPoint.getxPaint(),
                            ppPaintPoint.getyPaint(),
                            ppPaintPoint.getxOrPaint(),
                            ppPaintPoint.getyOrPaint());
                }
            }
            for (PaintPoint ppPaintPoint : ppPaintPoints) {
                if (ppPaintPoint.getStatus()) {
                    g.setColor(Color.BLUE);
                    g.fillRect(ppPaintPoint.getxPaint() - 5, ppPaintPoint.getyPaint() - 5, 10, 10);

                }
            }
            if (index >= 0) {
                g.setColor(Color.RED);
                if (ppPaintPoints.get(index).getStatus()) {
                    g.fillRect(ppPaintPoints.get(index).getxPaint() - 5, ppPaintPoints.get(index).getyPaint() - 5, 10, 10);
                } else {
                    g.fillOval(ppPaintPoints.get(index).getxPaint() - 3, ppPaintPoints.get(index).getyPaint() - 3, 6, 6);
                }
                g.drawLine(ppPaintPoints.get(index).getxPaint(),
                        ppPaintPoints.get(index).getyPaint(),
                        ppPaintPoints.get(index).getxOrPaint(),
                        ppPaintPoints.get(index).getyOrPaint());
            }
        }
    }
}
