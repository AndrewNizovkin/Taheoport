package taheoport.gui;

import taheoport.model.PaintPoint;
import taheoport.model.PaintProject;
import taheoport.model.PolygonStation;
import taheoport.repository.PolygonRepository;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This class encapsulates panel for display the polygon
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class PolygonPaintPanel extends JPanel {
    private final List<PolygonStation> polygonStations;
    private int index;

    /**
     * Constructor
     * @param polygonRepository current PolygonProject
     * @param idx current index of station
     */
    public PolygonPaintPanel(List<PolygonStation> polygonRepository, int idx) {
        polygonStations = polygonRepository;
        index = idx;

    }

    public void setIndex(int index) {
        this.index = index;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        PaintProject ppTheoPaintPoints = new PaintProject();
        ppTheoPaintPoints.createPolygonPaintProject(polygonStations, this.getWidth(), this.getHeight());
        if (ppTheoPaintPoints.getScale() < 10) {
            g.setColor(Color.BLUE);
            for (PaintPoint ppTheoPintPoint : ppTheoPaintPoints) {
                if (ppTheoPintPoint.getStatus()) {
                    g.fillRect(ppTheoPintPoint.getxPaint() - 5, ppTheoPintPoint.getyPaint() - 5, 10, 10);

                } else {
                    g.fillOval(ppTheoPintPoint.getxPaint() - 3, ppTheoPintPoint.getyPaint() - 3, 6, 6);
                }
            }
            for (int i = 0; i < ppTheoPaintPoints.size() -1; i++) {
                g.drawLine(ppTheoPaintPoints.get(i).getxPaint(),
                        ppTheoPaintPoints.get(i).getyPaint(),
                        ppTheoPaintPoints.get(i + 1).getxPaint(),
                        ppTheoPaintPoints.get(i +1). getyPaint());
            }
            if (index >= 0) {
                g.setColor(Color.RED);
                if (ppTheoPaintPoints.get(index).getStatus()) {
                    g.fillRect(ppTheoPaintPoints.get(index).getxPaint() - 5, ppTheoPaintPoints.get(index).getyPaint() - 5, 10, 10);
                } else {
                    g.fillOval(ppTheoPaintPoints.get(index).getxPaint() - 3, ppTheoPaintPoints.get(index).getyPaint() - 3, 6, 6);
                }
            }


        }
    }
//-- The End of class TheoPaintComponent
}
