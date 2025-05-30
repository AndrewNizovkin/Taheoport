package taheoport.gui;

import taheoport.dispatcher.DependencyContainer;
import taheoport.dispatcher.DependencyInjector;
import taheoport.service.DataHandler;
import taheoport.service.SettingsService;
import taheoport.service.Shell;

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
public class ShowChangeAngle extends JDialog implements ChangeListener, ActionListener {

    private final JLabel lblOffset;
    private final JFrame parentFrame;
    private final SettingsService settingsService;
    private final Shell shell;
    private final JRadioButton rbOffset = new JRadioButton();
    private final JRadioButton rbCopy = new JRadioButton();
    private final String titleMode;
    private final JTextField tfOffset;


    /**
     * Constructor
     * @param titleMode dialogs title
     */
    public ShowChangeAngle(String titleMode) {
        super( DependencyContainer
                        .getInstance()
                        .getMainFrame(),
                titleMode,
                true);
        DependencyInjector dependencyInjector = DependencyContainer.getInstance();
        settingsService = dependencyInjector.getSettingsService();
        parentFrame  = dependencyInjector.getMainFrame();
        shell = dependencyInjector.getShell();
        this.titleMode = titleMode;
        HashMap<String, String> titles = dependencyInjector.getTitles();
        settingsService.setChanged(false);
        setBounds(parentFrame.getX() + parentFrame.getWidth() / 2 - parentFrame.getWidth() / 3 * 2 / 2,
                parentFrame.getY() + parentFrame.getHeight() / 2 - parentFrame.getHeight() / 2 / 2,
                290,
                210);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image im = kit.getImage("images/teo.png");
        this.setIconImage(im);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


// rbCopy_____________________________________________________________________

        rbCopy.setText(titles.get("SCArbCopy"));
        rbCopy.addChangeListener(this);



// rbOffset__________________________________________________________________

        rbOffset.setText(titles.get("SCArbOffset"));
        rbOffset.addChangeListener(this);


// lblOffset________________________________________________________________

        lblOffset = new JLabel(titles.get("SCAlblOffset"), JLabel.CENTER);

// tfOffset__________________________________________________________________

        tfOffset = new JTextField(15);
        if (titleMode.equals(titles.get("SCAtitleChangeDirection"))) {
            tfOffset.setText(settingsService.getOffsetDirection());
            switch (settingsService.getOffsetDirectionType()) {
                case 0 -> {
                    rbCopy.setSelected(true);
                }
                case 1 -> {
                    rbOffset.setSelected(true);
                }
            }
        }
        if (titleMode.equals(titles.get("SCAtitleChangeTiltAngle"))) {
            tfOffset.setText(settingsService.getOffsetTiltAngle());
            switch (settingsService.getOffsetTiltType()) {
                case 0 -> {
                    rbCopy.setSelected(true);
                }
                case 1 -> {
                    rbOffset.setSelected(true);
                }
            }

        }
        tfOffset.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tfOffset.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfOffset.getText().equals("")) {
            tfOffset.setText("0.0000");
                }
            }
        });
        tfOffset.addActionListener(this);
        tfOffset.addKeyListener(new KeyAdapter() {
            /**
             * Invoked when a key has been typed.
             * This event occurs when a key press is followed by a key release.
             *
             * @param e key press event
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

// bgChangeType______________________________________________________________

        ButtonGroup bgChangeType = new ButtonGroup();
        bgChangeType.add(rbCopy);
        bgChangeType.add(rbOffset);

// pnlChangeAngle____________________________________________________________

        JPanel pnlChangeAngle = new JPanel(new GridBagLayout());

        pnlChangeAngle.add(rbCopy,new GridBagConstraints(
                0,
                0,
                1,
                1,
                1,
                0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH,
                new Insets(
                        0,
                        0,
                        0,
                        0),
                0,
                0));

        pnlChangeAngle.add(rbOffset, new GridBagConstraints(
                0,
                1,
                1,
                1,
                1,
                0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH,
                new Insets(
                        0,
                        0,
                        0,
                        0),
                0,
                0));

        pnlChangeAngle.add(lblOffset, new GridBagConstraints(
                0,
                2,
                1,
                1,
                1,
                0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH,
                new Insets(
                        0,
                        0,
                        0,
                        0),
                0,
                10));

        pnlChangeAngle.add(tfOffset,new GridBagConstraints(
                0,
                3,
                1,
                1,
                1,
                0,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE,
                new Insets(
                        0,
                        0,
                        0,
                        0),
                0,
                0));

        add(pnlChangeAngle, BorderLayout.CENTER);

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

        updateStatus();
        setResizable(true);
        setVisible(true);
    }

    /**
     * Sets mode and value of Enabled for rbCopy, rbOffset, tfOffset, lblOffset
     */
    private void updateStatus() {
        if (titleMode.equals(shell.getTitles().get("SCAtitleChangeDirection"))) {
            switch (settingsService.getOffsetDirectionType()) {
                case 0 -> {
                    lblOffset.setEnabled(false);
                    tfOffset.setEnabled(false);
                }
                case 1 -> {
                    lblOffset.setEnabled(true);
                    tfOffset.setEnabled(true);
                    tfOffset.requestFocusInWindow();
                }
            }
        }
        if (titleMode.equals(shell.getTitles().get("SCAtitleChangeTiltAngle"))) {
            switch (settingsService.getOffsetTiltType()) {
                case 0 -> {
                    lblOffset.setEnabled(false);
                    tfOffset.setEnabled(false);
                }
                case 1 -> {
                    lblOffset.setEnabled(true);
                    tfOffset.setEnabled(true);
                }
            }
        }
    }

    /**
     * Invoked when the target of the listener has changed its state.
     *
     * @param e a ChangeEvent object
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        if (rbCopy.isSelected()) {
            if (titleMode.equals(shell.getTitles().get("SCAtitleChangeDirection"))) {
                settingsService.setOffsetDirectionType(0);
            }
            if (titleMode.equals(shell.getTitles().get("SCAtitleChangeTiltAngle"))) {
                settingsService.setOffsetTiltType(0);
            }
        }
        if (rbOffset.isSelected()){
            if (titleMode.equals(shell.getTitles().get("SCAtitleChangeDirection"))) {
                settingsService.setOffsetDirectionType(1);
                tfOffset.requestFocusInWindow();
            }
            if (titleMode.equals(shell.getTitles().get("SCAtitleChangeTiltAngle"))) {
                settingsService.setOffsetTiltType(1);
                tfOffset.requestFocusInWindow();
            }

        }

        settingsService.saveOptions();
        updateStatus();
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (tfOffset.getText().isEmpty()) {
            tfOffset.setText("0.0000");
        }
        if (titleMode.equals(shell.getTitles().get("SCAtitleChangeDirection"))) {
            switch (settingsService.getOffsetDirectionType()) {
                case 0 -> {
                    settingsService.setChanged(true);
                }
                case 1 -> {
                    DataHandler dataHandler = new DataHandler(tfOffset.getText()).commaToPoint();
                    if (dataHandler.isNumber()) {
                        settingsService.setOffsetDirection(dataHandler.format(4).getStr());
                        settingsService.setChanged(true);
                    }

                }
            }

        }

        if (titleMode.equals(shell.getTitles().get("SCAtitleChangeTiltAngle"))) {
            switch (settingsService.getOffsetTiltType()) {
                case 0 -> {
                    settingsService.setChanged(true);
                }
                case 1 -> {
                    DataHandler dataHandler = new DataHandler(tfOffset.getText()).commaToPoint();
                    if (dataHandler.isNumber()) {
                        settingsService.setOffsetTiltAngle(dataHandler.format(4).getStr());
                        settingsService.setChanged(true);
                    }

                }
            }
        }
        this.dispose();
    }

// The END of ShowAngle
}
