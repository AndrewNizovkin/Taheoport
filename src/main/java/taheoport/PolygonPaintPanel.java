package taheoport;

import javax.swing.*;
import java.awt.*;

public class PolygonPaintPanel extends JPanel {
    private PaintProject ppTheoPaintPoints;
    private PolygonProject theoProject;
    private int index;

    public PolygonPaintPanel(PolygonProject theoProject, int idx) {
        this.theoProject = theoProject;
        index = idx;

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ppTheoPaintPoints = new PaintProject(theoProject, this.getWidth(), this.getHeight());
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
