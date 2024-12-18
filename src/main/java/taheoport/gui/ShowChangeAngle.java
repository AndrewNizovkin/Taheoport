package taheoport.gui;

import taheoport.service.DataHandler;
import taheoport.service.SettingsController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class encapsulates form for changes angle
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class ShowChangeAngle extends JDialog implements ChangeListener, ActionListener {

    private final JLabel lblOffset;
    private final MainWin parentFrame;
    private final SettingsController settingsController;
    private final JRadioButton rbOffset = new JRadioButton();
    private final JRadioButton rbCopy = new JRadioButton();
    private final String title;
    private final JTextField tfOffset;


    /**
     * Constructor
     * @param frame parent MainWin
     * @param title dialogs title
     */
    public ShowChangeAngle(MainWin frame, String title) {
        super(frame, title, true);
        settingsController = frame.getSettingsController();
        parentFrame  = frame;
        this.title = title;
        settingsController.setChanged(false);
        setBounds(parentFrame.getX() + parentFrame.getWidthMain() / 2 - parentFrame.getWidthMain() / 3 * 2 / 2,
                parentFrame.getY() + parentFrame.getHeightMain() / 2 - parentFrame.getHeightMain() / 2 / 2,
                290,
                210);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image im = kit.getImage("images/teo.png");
        this.setIconImage(im);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


// rbCopy_____________________________________________________________________

        rbCopy.setText(parentFrame.getTitles().get("SCArbCopy"));
        rbCopy.addChangeListener(this);



// rbOffset__________________________________________________________________

        rbOffset.setText(parentFrame.getTitles().get("SCArbOffset"));
        rbOffset.addChangeListener(this);


// lblOffset________________________________________________________________

        lblOffset = new JLabel(parentFrame.getTitles().get("SCAlblOffset"), JLabel.CENTER);

// tfOffset__________________________________________________________________

        tfOffset = new JTextField(15);
        if (title.equals(parentFrame.getTitles().get("SCAtitleChangeDirection"))) {
            tfOffset.setText(settingsController.getOffsetDirection());
            switch (settingsController.getOffsetDirectionType()) {
                case 0 -> {
                    rbCopy.setSelected(true);
                }
                case 1 -> {
                    rbOffset.setSelected(true);
                }
            }
        }
        if (title.equals(parentFrame.getTitles().get("SCAtitleChangeTiltAngle"))) {
            tfOffset.setText(settingsController.getOffsetTiltAngle());
            switch (settingsController.getOffsetTiltType()) {
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
        JButton btnApprove = new JButton(parentFrame.getTitles().get("SObtnApprove"));
        btnApprove.addActionListener(this);

        pnlControl.add(btnApprove);

// btnCancel________________________________________________________

        JButton btnCancel = new JButton(parentFrame.getTitles().get("SObtnCancel"));
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
        if (title.equals(parentFrame.getTitles().get("SCAtitleChangeDirection"))) {
            switch (settingsController.getOffsetDirectionType()) {
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
        if (title.equals(parentFrame.getTitles().get("SCAtitleChangeTiltAngle"))) {
            switch (settingsController.getOffsetTiltType()) {
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
            if (title.equals(parentFrame.getTitles().get("SCAtitleChangeDirection"))) {
                settingsController.setOffsetDirectionType(0);
            }
            if (title.equals(parentFrame.getTitles().get("SCAtitleChangeTiltAngle"))) {
                settingsController.setOffsetTiltType(0);
            }
        }
        if (rbOffset.isSelected()){
            if (title.equals(parentFrame.getTitles().get("SCAtitleChangeDirection"))) {
                settingsController.setOffsetDirectionType(1);
                tfOffset.requestFocusInWindow();
            }
            if (title.equals(parentFrame.getTitles().get("SCAtitleChangeTiltAngle"))) {
                settingsController.setOffsetTiltType(1);
                tfOffset.requestFocusInWindow();
            }

        }

        parentFrame.getSettingsController().saveOptions();
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
        if (title.equals(parentFrame.getTitles().get("SCAtitleChangeDirection"))) {
            switch (settingsController.getOffsetDirectionType()) {
                case 0 -> {
                    settingsController.setChanged(true);
                }
                case 1 -> {
                    DataHandler dataHandler = new DataHandler(tfOffset.getText()).commaToPoint();
                    if (dataHandler.isNumber()) {
                        settingsController.setOffsetDirection(dataHandler.format(4).getStr());
                        settingsController.setChanged(true);
                    }

                }
            }

        }

        if (title.equals(parentFrame.getTitles().get("SCAtitleChangeTiltAngle"))) {
            switch (settingsController.getOffsetTiltType()) {
                case 0 -> {
                    settingsController.setChanged(true);
                }
                case 1 -> {
                    DataHandler dataHandler = new DataHandler(tfOffset.getText()).commaToPoint();
                    if (dataHandler.isNumber()) {
                        settingsController.setOffsetTiltAngle(dataHandler.format(4).getStr());
                        settingsController.setChanged(true);
                    }

                }
            }
        }
        this.dispose();
    }

// The END of ShowAngle
}
