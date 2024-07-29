package taheoport.gui;

import taheoport.model.PaintPoint;
import taheoport.model.PaintProject;

import javax.swing.*;
import java.awt.*;

/**
 * Display results of processing and highlights current position
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class PaintPanel extends JPanel {
    private int index;
    private final ShowViewResults parentFrame;
    private PaintProject ppPaintPoints;

//    private LinkedList <JLabel> llLabel;

    /**
     * Constructor
     * @param frame ShowViewResults
     * @param sellRow index of selected row
     */
    public PaintPanel(ShowViewResults frame, int sellRow) {
        super();
        parentFrame = frame;
        index = sellRow;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    /*
    public PaintProject getPpPaintPoints() {
        return ppPaintPoints;
    }
*/


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        Insets insets = getInsets();
        ppPaintPoints = new PaintProject(parentFrame.getSurveyProject(), this.getWidth(), this.getHeight());
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


//--->>
        }
    }

}
