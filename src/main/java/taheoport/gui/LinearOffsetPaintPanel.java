package taheoport.gui;

import taheoport.service.SettingsService;

import javax.swing.*;
import java.awt.*;

/**
 * This class encapsulates GUI for entered linear offset to measured lenth
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class LinearOffsetPaintPanel extends JPanel {
    private final SettingsService settingsService;

    /**
     * Constructor
     * @param settingsService settingsController
     */
    public LinearOffsetPaintPanel(SettingsService settingsService) {
        super();
        this.settingsService = settingsService;
        setPreferredSize(new Dimension(110, 110));
    }

    /**
     * gets scheme of measurement
     * @param g graphics
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);
        switch (settingsService.getOffsetDistanceType()) {
            case 0 -> {
                g.drawLine(64, 43, 95, 43);
            }
            case 1 -> {
                g.drawLine(64, 43, 95, 11);
            }
        }

        g.setColor(Color.CYAN);
        g.drawLine(36, 72, 64, 43);

        g.setColor(Color.BLACK);
        g.drawOval(30, 66, 12, 12);

        g.drawLine(10, 110, 100, 110);

        g.drawLine(95, 110, 95, 10);
        g.drawLine(17, 110, 36, 72);
        g.drawLine(36, 72, 54, 110);
        g.setColor(Color.GRAY);
        g.drawLine(64, 110, 64, 10);
    }
}

