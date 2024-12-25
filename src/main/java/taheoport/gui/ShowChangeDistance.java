package taheoport.gui;

import taheoport.dispatcher.DependencyContainer;
import taheoport.dispatcher.DependencyInjector;
import taheoport.service.DataHandler;
import taheoport.service.SettingsService;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class encapsulates form for changes angle
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class ShowChangeDistance extends JDialog implements ChangeListener, ActionListener {
    private final SettingsService settingsService;
    private LinearOffsetPaintPanel pnlPaintPanel;
    private final JRadioButton rbHor = new JRadioButton();
    private final JTextField tfOffset;


    /**
     * Constructor
     */
    public ShowChangeDistance() {
        super( DependencyContainer
                        .getInstance()
                        .getMainFrame(),
                DependencyContainer
                        .getInstance()
                        .getTitles()
                        .get("SADtitle"),
                true);
        DependencyInjector dependencyInjector = DependencyContainer.getInstance();
        JFrame parentFrame = dependencyInjector.getMainFrame();
        settingsService = dependencyInjector.getSettingsService();
        settingsService.setChanged(false);
        HashMap<String, String> titles = dependencyInjector.getTitles();
        setBounds(parentFrame.getX() + parentFrame.getWidth() / 2 - parentFrame.getWidth() / 3 * 2 / 2,
                parentFrame.getY() + parentFrame.getHeight() / 2 - parentFrame.getHeight() / 2 / 2,
                290,
                210);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image im = kit.getImage("images/teo.png");
        this.setIconImage(im);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

// pnlPaintPanel_________________________________________________________

        reloadPaintPanel();

// pnlOffset_______________________________________________________

        JPanel pnlOffset = new JPanel();
        pnlOffset.setLayout(new GridBagLayout());

        pnlOffset.setBorder(BorderFactory.createTitledBorder(titles.get("SADpnlOffsetTitle")));

// rbInc____________________________________________________________

        JRadioButton rbInc = new JRadioButton();
        rbInc.setText(titles.get("SADrbInc"));
        rbInc.setFocusable(false);
        rbInc.addChangeListener(this);
        if (settingsService.getOffsetDistanceType() == 1) {
            rbInc.setSelected(true);
        }

        pnlOffset.add(rbInc, new GridBagConstraints(0, 0, 1, 1, 1, 0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

// rbHor____________________________________________________________

        rbHor.setText(titles.get("SADrbHor"));
        rbHor.setFocusable(false);
        rbHor.addChangeListener(this);
        if (settingsService.getOffsetDistanceType() == 0) {
            rbHor.setSelected(true);
        }

        pnlOffset.add(rbHor, new GridBagConstraints(0, 1, 1, 1, 1, 0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

// bgOffset________________________________________________________

        ButtonGroup bgOffset = new ButtonGroup();
        bgOffset.add(rbInc);
        bgOffset.add(rbHor);

// lblOffset________________________________________________________

        JLabel lblOffset = new JLabel(titles.get("SADlblOffset"));

// tfOffset_________________________________________________________

        tfOffset = new JTextField();
        tfOffset.setText(settingsService.getOffsetDistance());
        tfOffset.addActionListener(this);
        tfOffset.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tfOffset.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfOffset.getText().equals("")) {
                    tfOffset.setText("0.000");
                }
            }
        });
        tfOffset.addKeyListener(new KeyAdapter() {
            /**
             * Invoked when a key has been typed.
             * This event occurs when a key press is followed by a key release.
             *
             * @param e
             */
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                Matcher m = Pattern.compile("[0-9/.,-]").matcher(String.valueOf(e.getKeyChar()));
                if (!m.matches() | e.getKeyChar() == '/') {
                    e.consume();
                }
                m = Pattern.compile("[/.,]").matcher(tfOffset.getText());
                if (m.find() & (e.getKeyChar() == '.' | e.getKeyChar() == ',')) {
                    e.consume();
                }
                m = Pattern.compile("[-]").matcher(tfOffset.getText());
                if (m.find() & e.getKeyChar() == '-') {
                    e.consume();
                }
                if (e.getKeyChar() == '-' & tfOffset.getText().length() > 1) {
                    e.consume();
                }
            }
        });

// pnlBlank________________________________________________________

    JPanel pnlBlank = new JPanel();

// pnlContent_______________________________________________________

    JPanel pnlContent = new JPanel();
    pnlContent.setLayout(new GridBagLayout());

    pnlContent.add(pnlOffset, new GridBagConstraints(0, 0, 1, 1, 0, 0,
            GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 10));

    pnlContent.add(lblOffset, new GridBagConstraints(0, 1, 1, 1, 1, 0,
            GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 10));

    pnlContent.add(tfOffset, new GridBagConstraints(0, 2, 1, 1, 1, 0,
            GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 10));

    pnlContent.add(pnlBlank, new GridBagConstraints(0, 3, 1, 1, 1, 1,
            GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

    add(pnlContent, BorderLayout.CENTER);



// pnlControl________________________________________________________

        JPanel pnlControl = new JPanel();
    pnlControl.setLayout(new FlowLayout());

// btnApprove________________________________________________________
        JButton btnApprove = new JButton(titles.get("SObtnApprove"));
    btnApprove.addActionListener(this);

    pnlControl.add(btnApprove);

// btnCancel________________________________________________________

        JButton btnCancel = new JButton(titles.get("SObtnCancel"));
        btnCancel.addActionListener(e -> this.dispose());

        pnlControl.add(btnCancel);
        add(pnlControl, BorderLayout.SOUTH);

        setResizable(false);
        setVisible(true);
        tfOffset.requestFocusInWindow();

        // The END of constructor
}

    /**
     * Reloads pnlPaintPanel
     */
    private void reloadPaintPanel() {
        if (pnlPaintPanel != null) {
            remove(pnlPaintPanel);
        }
         pnlPaintPanel = new LinearOffsetPaintPanel(settingsService);
         add(pnlPaintPanel, BorderLayout.WEST);
        revalidate();
    }

    /**
     * Invoked when the target of the listener has changed its state.
     *
     * @param e a ChangeEvent object
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        if (rbHor.isSelected()) {
            settingsService.setOffsetDistanceType(0);
        } else {
            settingsService.setOffsetDistanceType(1);
        }
        settingsService.saveOptions();
        reloadPaintPanel();

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (tfOffset.getText().isEmpty()) {
            tfOffset.setText("0.000");
        }
        if (new DataHandler(tfOffset.getText()).commaToPoint().isNumber()) {
            settingsService.setOffsetDistance(new DataHandler(tfOffset.getText()).commaToPoint().format(3).getStr());
            settingsService.setChanged(true);
        } else {
            settingsService.setOffsetDistance("0.000");
        }
        this.dispose();
    }

// The End of ShowAddDistance
}
