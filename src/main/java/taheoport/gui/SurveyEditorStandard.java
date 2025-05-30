package taheoport.gui;
import taheoport.dispatcher.DependencyContainer;
import taheoport.dispatcher.DependencyInjector;
import taheoport.dispatcher.SurveyEditorActionListener;
import taheoport.repository.SurveyRepository;
import taheoport.service.CatalogService;
import taheoport.service.DataHandler;
import taheoport.model.SurveyStation;
import taheoport.service.Shell;
import taheoport.service.SurveyService;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class encapsulates panel for display editing survey measurement
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class SurveyEditorStandard extends JPanel implements SurveyEditorRenderer {
    private final JButton btnDeleteRow;
    private final JButton btnInsertRowBefore;
    private final JButton btnInsertRowAfter;
    private final JButton btnChangeDistance,
            btnChangeDirection,
            btnChangeTilt;
    private final JButton btnDeleteStation;
    private final JButton btnInsertStationBefore;
    private final JButton btnInsertStationAfter;
    private final JButton btnStationName;
    private final JButton btnOrName;
    private int currentStationIndex;
    private final JLabel lblStationI;
    private JList<String> lstStations;
    private final Vector<Component> order;
    private final JFrame parentFrame;
    private final JPanel pnlPickets;
    private final JPanel pnlStation;
    private final JPanel pnlStations;
    private int selRow;
    private int selColumn;
    private JScrollPane scpPickets;
    private JScrollPane scpStations;
    private JTextField tfStationName;
    private JTextField tfStationX;
    private JTextField tfStationY;
    private JTextField tfStationZ;
    private JTextField tfStationI;
    private JTextField tfOrName;
    private JTextField tfOrX;
    private JTextField tfOrY;
    private JTable tblPickets;
    private final SurveyService surveyService;
    private final CatalogService catalogService;
    private final Shell shell;

    /**
     * Constructor
     * @param index int index of current SurveyStation of SurveyProject
     */
    public SurveyEditorStandard(int index) {
        super();
        DependencyInjector dependencyInjector = DependencyContainer.getInstance();
        surveyService = dependencyInjector.getSurveyService();
        catalogService = dependencyInjector.getCatalogService();
        shell = dependencyInjector.getShell();
        currentStationIndex = index;
        parentFrame = dependencyInjector.getMainFrame();
        ActionListener surveyEditorActionListener = new SurveyEditorActionListener(this);
        HashMap<String, String> titles = dependencyInjector.getTitles();

            ImageIcon imageDeleteRow = new ImageIcon("images/delete_row.png");
            ImageIcon imageInsertRowBefore = new ImageIcon("images/insert_row.png");
            ImageIcon imageInsertRowAfter = new ImageIcon("images/insert_row_after.png");
            setLayout(new GridBagLayout());

            pnlStations = new JPanel();
            pnlStations.setLayout(new BorderLayout());
            pnlStations.setBorder(BorderFactory.createTitledBorder(null,
                    titles.get("TAHlblStationListTitle"),
                    TitledBorder.CENTER,
                    TitledBorder.TOP,
                    new Font(Font.DIALOG, Font.PLAIN, 12),
                    Color.BLUE));

//region pnlStation

            pnlStation = new JPanel(new GridBagLayout());
            pnlStation.setBorder(BorderFactory.createTitledBorder(null,
                    titles.get("TAHlblStationTitle"),
                    TitledBorder.CENTER,
                    TitledBorder.TOP,
                    new Font(Font.DIALOG, Font.PLAIN, 12),
                    Color.BLUE));
            pnlStation.setPreferredSize(new Dimension(this.parentFrame.getWidth() / 2,
                    parentFrame.getHeight()));
//endregion

//region btnStationName

            btnStationName = new JButton();
            btnStationName.setActionCommand("btnStationName");
            btnStationName.addActionListener(surveyEditorActionListener);
            btnStationName.setBorder(BorderFactory.createEtchedBorder());
            btnStationName.setText(titles.get("TAHbtnStationName"));
            btnStationName.setToolTipText(titles.get("TAHbtnStationNameTT"));

            pnlStation.add(btnStationName, new GridBagConstraints(0, 0, 1, 1, 0, 1,
                    GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 10, 0));
//endregion

//region tfStationName

            tfStationName = new JTextField(15);
            tfStationName.setText(surveyService.findStationById(currentStationIndex).getName());
            tfStationName.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    tfStationName.selectAll();
                }

                @Override
                public void focusLost(FocusEvent e) {

                    if (tfStationName.getText().isEmpty()) {
                        tfStationName.setText("noname");
                        surveyService.findStationById(currentStationIndex).setName("noname");
                        reloadStations(lstStations.getSelectedIndex());
                    } else {
                        surveyService.findStationById(currentStationIndex).setName(tfStationName.getText());
                        reloadStations(lstStations.getSelectedIndex());
                    }
                }
            });
            tfStationName.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if ((e.getKeyChar() == ' ') | (e.getKeyChar() == '/')) {
                        e.consume();
                    }

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        tfStationX.requestFocusInWindow();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
            tfStationName.addActionListener(e -> tfStationX.requestFocusInWindow());

            pnlStation.add(tfStationName, new GridBagConstraints(1, 0, 1, 1, 1, 0,
                    GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 20, 0));
//endregion

//region lblStationX

            JLabel lblStationX = new JLabel("X =  ", JLabel.RIGHT);
            pnlStation.add(lblStationX, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                    GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
//endregion

//region tfStationX

            tfStationX = new JTextField(surveyService.findStationById(currentStationIndex).getX(), 15);
            tfStationX.setBorder(BorderFactory.createEtchedBorder());
            tfStationX.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    tfStationX.selectAll();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (tfStationX.getText().isEmpty() | tfStationX.getText().equals(".") | tfStationX.getText().equals(",")) {
                        tfStationX.setText("0.000");
                        surveyService.findStationById(currentStationIndex).setX("0.000");
                    } else {
                        surveyService.findStationById(currentStationIndex).setX(new DataHandler(tfStationX.getText()).commaToPoint().format(3).getStr());
                    }

                }
            });
            tfStationX.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    Matcher m = Pattern.compile("[0-9/.,]").matcher(String.valueOf(e.getKeyChar()));
                    if (!m.matches() | e.getKeyChar() == '/') {
                        e.consume();
                    }
                    m = Pattern.compile("[/.,]").matcher(tfStationX.getText());
                    if (m.find() & (e.getKeyChar() == '.' | e.getKeyChar() == ',')) {
                        e.consume();
                    }

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP -> tfStationName.requestFocusInWindow();
                        case KeyEvent.VK_DOWN -> tfStationY.requestFocusInWindow();
                    }

                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
            tfStationX.addActionListener(e -> tfStationY.requestFocusInWindow());
            pnlStation.add(tfStationX, new GridBagConstraints(
                    1,
                    1,
                    1,
                    1,
                    1,
                    0,
                    GridBagConstraints.EAST,
                    GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 20, 0));
//endregion

//region lblStationY

            JLabel lblStationY = new JLabel("Y =  ", JLabel.RIGHT);
            pnlStation.add(lblStationY, new GridBagConstraints(0, 2, 1, 1, 0, 0,
                    GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
//endregion

//region tfStationY

            tfStationY = new JTextField(surveyService.findStationById(currentStationIndex).getY(), 15);
            tfStationY.setBorder(BorderFactory.createEtchedBorder());
            tfStationY.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    tfStationY.selectAll();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (tfStationY.getText().isEmpty() | tfStationY.getText().equals(".") | tfStationY.getText().equals(",")) {
                        tfStationY.setText("0.000");
                        surveyService.findStationById(currentStationIndex).setY("0.000");
                    } else {
                        surveyService.findStationById(currentStationIndex).setY(new DataHandler(tfStationY.getText()).commaToPoint().format(3).getStr());
                    }
                }
            });
            tfStationY.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    Matcher m = Pattern.compile("[0-9/.,]").matcher(String.valueOf(e.getKeyChar()));
                    if (!m.matches() | e.getKeyChar() == '/') {
                        e.consume();
                    }
                    m = Pattern.compile("[/.,]").matcher(tfStationY.getText());
                    if (m.find() & (e.getKeyChar() == '.' | e.getKeyChar() == ',')) {
                        e.consume();
                    }

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_DOWN -> tfStationZ.requestFocusInWindow();
                        case KeyEvent.VK_UP -> tfStationX.requestFocusInWindow();
                    }

                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
            tfStationY.addActionListener(e -> tfStationZ.requestFocusInWindow());

            pnlStation.add(tfStationY, new GridBagConstraints(1, 2, 1, 1, 1, 0,
                    GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 20, 0));
//endregion

//region lblStationZ

            JLabel lblStationZ = new JLabel("Z =  ", JLabel.RIGHT);

            pnlStation.add(lblStationZ, new GridBagConstraints(0, 3, 1, 1, 0, 0,
                    GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
//endregion

//region tfStationZ

            tfStationZ = new JTextField(surveyService.findStationById(currentStationIndex).getZ(), 15);
            tfStationZ.setBorder(BorderFactory.createEtchedBorder());
            tfStationZ.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    tfStationZ.selectAll();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (tfStationZ.getText().isEmpty() | tfStationZ.getText().equals(".") | tfStationZ.getText().equals(",")) {
                        tfStationZ.setText("0.000");
                        surveyService.findStationById(currentStationIndex).setZ("0.000");
                    } else {
                        surveyService.findStationById(currentStationIndex).setZ(new DataHandler(tfStationZ.getText()).commaToPoint().format(3).getStr());
                    }
                }
            });
            tfStationZ.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    Matcher m = Pattern.compile("[0-9/.,]").matcher(String.valueOf(e.getKeyChar()));
                    if (!m.matches() | e.getKeyChar() == '/') {
                        e.consume();
                    }
                    m = Pattern.compile("[/.,]").matcher(tfStationZ.getText());
                    if (m.find() & (e.getKeyChar() == '.' | e.getKeyChar() == ',')) {
                        e.consume();
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_DOWN -> tfStationI.requestFocusInWindow();
                        case KeyEvent.VK_UP -> tfStationY.requestFocusInWindow();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
            tfStationZ.addActionListener(e -> tfStationI.requestFocusInWindow());

            pnlStation.add(tfStationZ, new GridBagConstraints(1, 3, 1, 1, 1, 0,
                    GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 20, 0));
//endregion

//region lblStationI

            lblStationI = new JLabel(titles.get("TAHlblStationI"), JLabel.RIGHT);

            pnlStation.add(lblStationI, new GridBagConstraints(0, 4, 1, 1, 0, 0,
                    GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
//endregion

//region tfStationI

            tfStationI = new JTextField(surveyService.findStationById(currentStationIndex).getVi(), 15);
            tfStationI.setBorder(BorderFactory.createEtchedBorder());
            tfStationI.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    tfStationI.selectAll();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (tfStationI.getText().isEmpty() | tfStationI.getText().equals(".") | tfStationI.getText().equals(",")) {
                        tfStationI.setText("0.000");
                        surveyService.findStationById(currentStationIndex).setVi("0.000");
                    } else {
                        surveyService.findStationById(currentStationIndex).setVi(new DataHandler(tfStationI.getText()).commaToPoint().format(3).getStr());
                    }
                }
            });
            tfStationI.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    Matcher m = Pattern.compile("[0-9/.,]").matcher(String.valueOf(e.getKeyChar()));
                    if (!m.matches() | e.getKeyChar() == '/') {
                        e.consume();
                    }
                    m = Pattern.compile("[/.,]").matcher(tfStationI.getText());
                    if (m.find() & (e.getKeyChar() == '.' | e.getKeyChar() == ',')) {
                        e.consume();
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_DOWN -> tfOrName.requestFocusInWindow();
                        case KeyEvent.VK_UP -> tfStationZ.requestFocusInWindow();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
            tfStationI.addActionListener(e -> tfOrName.requestFocusInWindow());

            pnlStation.add(tfStationI, new GridBagConstraints(
                    1,
                    4,
                    1,
                    1,
                    1,
                    0,
                    GridBagConstraints.EAST,
                    GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 20, 0));
//endregion

//region btnOrName

            btnOrName = new JButton();
            btnOrName.setActionCommand("btnOrName");
            btnOrName.addActionListener(surveyEditorActionListener);
            btnOrName.setBorder(BorderFactory.createEtchedBorder());
            btnOrName.setText(titles.get("TAHbtnOrName"));
            btnOrName.setToolTipText(titles.get("TAHbtnOrNameTT"));

            pnlStation.add(btnOrName, new GridBagConstraints(
                    0,
                    5,
                    1,
                    1,
                    0,
                    1,
                    GridBagConstraints.EAST,
                    GridBagConstraints.NONE,
                    new Insets(0, 0, 0, 0), 0, 0));
//endregion

//region tfOrName

            tfOrName = new JTextField(surveyService.findStationById(currentStationIndex).getNameOr(), 15);
            tfOrName.setBorder(BorderFactory.createEtchedBorder());
            tfOrName.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    tfOrName.selectAll();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (tfOrName.getText().isEmpty()) {
                        tfOrName.setText("noname");
                        surveyService.findStationById(currentStationIndex).setNameOr("noname");
                    } else {
                        surveyService.findStationById(currentStationIndex).setNameOr(tfOrName.getText());
                    }
                }
            });
            tfOrName.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if ((e.getKeyChar() == ' ') | (e.getKeyChar() == '/')) {
                        e.consume();
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_DOWN -> tfOrX.requestFocusInWindow();
                        case KeyEvent.VK_UP -> tfStationI.requestFocusInWindow();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
            tfOrName.addActionListener(e -> tfOrX.requestFocusInWindow());

            pnlStation.add(tfOrName, new GridBagConstraints(
                    1,
                    5,
                    1,
                    1,
                    1,
                    0,
                    GridBagConstraints.EAST,
                    GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 20, 0));
//endregion

//region lblOrX

            JLabel lblOrX = new JLabel("X =  ", JLabel.RIGHT);

            pnlStation.add(lblOrX, new GridBagConstraints(
                    0,
                    6,
                    1,
                    1,
                    0,
                    0,
                    GridBagConstraints.EAST,
                    GridBagConstraints.NONE,
                    new Insets(0, 0, 0, 0), 0, 0));
//endregion

//region tfOrX

            tfOrX = new JTextField(surveyService.findStationById(currentStationIndex).getxOr());
            tfOrX.setBorder(BorderFactory.createEtchedBorder());
            tfOrX.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    tfOrX.selectAll();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (tfOrX.getText().isEmpty() | tfOrX.getText().equals(".") | tfOrX.getText().equals(",")) {
                        tfOrX.setText("0.000");
                        surveyService.findStationById(currentStationIndex).setxOr("0.000");
                    } else {
                        surveyService.findStationById(currentStationIndex).setxOr(new DataHandler(tfOrX.getText()).commaToPoint().format(3).getStr());
                    }

                }
            });
            tfOrX.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    Matcher m = Pattern.compile("[0-9/.,]").matcher(String.valueOf(e.getKeyChar()));
                    if (!m.matches() | e.getKeyChar() == '/') {
                        e.consume();
                    }
                    m = Pattern.compile("[/.,]").matcher(tfOrX.getText());
                    if (m.find() & (e.getKeyChar() == '.' | e.getKeyChar() == ',')) {
                        e.consume();
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_DOWN -> tfOrY.requestFocusInWindow();
                        case KeyEvent.VK_UP -> tfOrName.requestFocusInWindow();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
            tfOrX.addActionListener(e -> tfOrY.requestFocusInWindow());

            pnlStation.add(tfOrX, new GridBagConstraints(
                    1,
                    6,
                    1,
                    1,
                    1,
                    0,
                    GridBagConstraints.EAST,
                    GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
//endregion

//region lblOrY

            JLabel lblOrY = new JLabel("Y =  ", JLabel.RIGHT);

            pnlStation.add(lblOrY, new GridBagConstraints(
                    0,
                    7,
                    1,
                    1,
                    0,
                    0,
                    GridBagConstraints.EAST,
                    GridBagConstraints.NONE,
                    new Insets(0, 0, 0, 0), 0, 0));
//endregion

//region tfOrY

            tfOrY = new JTextField(surveyService.findStationById(currentStationIndex).getY());
            tfOrY.setBorder(BorderFactory.createEtchedBorder());
            tfOrY.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    tfOrY.selectAll();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (tfOrY.getText().isEmpty() | tfOrY.getText().equals(".") | tfOrY.getText().equals(",")) {
                        tfOrY.setText("0.000");
                        surveyService.findStationById(currentStationIndex).setyOr("0.000");
                    } else {
                        surveyService.findStationById(currentStationIndex).setyOr(new DataHandler(tfOrY.getText()).commaToPoint().format(3).getStr());
                    }
                }
            });
            tfOrY.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    Matcher m = Pattern.compile("[0-9/.,]").matcher(String.valueOf(e.getKeyChar()));
                    if (!m.matches() | e.getKeyChar() == '/') {
                        e.consume();
                    }
                    m = Pattern.compile("[/.,]").matcher(tfOrY.getText());
                    if (m.find() & (e.getKeyChar() == '.' | e.getKeyChar() == ',')) {
                        e.consume();
                    }

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        tfOrX.requestFocusInWindow();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
            tfOrY.addActionListener(e -> {
                if (tfOrY.getText().isEmpty() | tfOrY.getText().equals(".")) {
                    tfOrY.setText("0.000");
                    surveyService.findStationById(currentStationIndex).setyOr("0.000");
                } else {
                    surveyService.findStationById(currentStationIndex).setyOr(new DataHandler(tfOrY.getText()).format(3).getStr());
                }
            });

            pnlStation.add(tfOrY, new GridBagConstraints(1, 7, 1, 1, 1, 0,
                    GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

            this.add(pnlStation,new GridBagConstraints(
                    0,
                    0,
                    1,
                    1,
                    1,
                    0,
                    GridBagConstraints.EAST,
                    GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
//endregion

//region btnDeleteStation

                btnDeleteStation = new JButton(imageDeleteRow);
                btnDeleteStation.setActionCommand("btnDeleteStation");
                btnDeleteStation.setToolTipText(titles.get("TAHbtnDeleteStationTT"));
                btnDeleteStation.addActionListener(surveyEditorActionListener);
//endregion

//region btnInsertStationBefore

                btnInsertStationBefore = new JButton(imageInsertRowBefore);
                btnInsertStationBefore.setActionCommand("btnInsertStationBefore");
                btnInsertStationBefore.setToolTipText(titles.get("TAHbtnInsertStationBeforeTT"));
                btnInsertStationBefore.addActionListener(surveyEditorActionListener);
//endregion

//region btnInsertStationAfter

                btnInsertStationAfter = new JButton(imageInsertRowAfter);
                btnInsertStationAfter.setActionCommand("btnInsertStationAfter");
                btnInsertStationAfter.setToolTipText(titles.get("TAHbtnInsertStationAfterTT"));
                btnInsertStationAfter.addActionListener(surveyEditorActionListener);
//endregion

//region tbStation

                JToolBar tbStations = new JToolBar();
                tbStations.add(btnDeleteStation);
                tbStations.add(btnInsertStationBefore);
                tbStations.add(btnInsertStationAfter);
                tbStations.setFloatable(false);
                tbStations.setBorder(BorderFactory.createEtchedBorder());
//endregion

//region pnlStations
            pnlStations.add(tbStations, BorderLayout.NORTH);
            reloadStations(0);
            pnlStations.add(scpStations, BorderLayout.CENTER);

            pnlStations.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    setFocusStations();
                }

                @Override
                public void focusLost(FocusEvent e) {

                }
            });

            this.add(pnlStations, new GridBagConstraints(
                    0,
                    1,
                    1,
                    GridBagConstraints.REMAINDER,
                    1,
                    0,
                    GridBagConstraints.EAST,
                    GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
//endregion

            pnlPickets = new JPanel();
            pnlPickets.setLayout(new BorderLayout());
            pnlPickets.setBorder(BorderFactory.createTitledBorder(
                    null,
                    titles.get("TAHlblPicketsTitle"),
                    TitledBorder.CENTER,
                    TitledBorder.TOP,
                    new Font(Font.DIALOG, Font.PLAIN, 12),
                    Color.BLUE));

//region btnDeleteRow

            btnDeleteRow = new JButton(new ImageIcon("images/delete_row.png"));
            btnDeleteRow.setActionCommand("btnDeleteRow");
            btnDeleteRow.setToolTipText(titles.get("TAHbtnDeleteRowTT"));
            btnDeleteRow.addActionListener(surveyEditorActionListener);
//endregion

//region btnInsertRowBefore

            btnInsertRowBefore = new JButton(new ImageIcon("images/insert_row.png"));
            btnInsertRowBefore.setActionCommand("btnInsertRowBefore");
            btnInsertRowBefore.setToolTipText(titles.get("TAHbtnInsertRowBeforeTT"));
            btnInsertRowBefore.addActionListener(surveyEditorActionListener);
//endregion

//region btnInsertRowAfter

            btnInsertRowAfter = new JButton(new ImageIcon("images/insert_row_after.png"));
            btnInsertRowAfter.setActionCommand("btnInsertRowAfter");
            btnInsertRowAfter.setToolTipText(titles.get("TAHbtnInsertRowAfterTT"));
            btnInsertRowAfter.addActionListener(surveyEditorActionListener);
//endregion

//region btnChangeDistance

            btnChangeDistance = new JButton(new ImageIcon("images/rearrange.png"));
            btnChangeDistance.setActionCommand("btnChangeDistance");
            btnChangeDistance.setToolTipText(titles.get("TAHbtnAddDistanceTT"));
            btnChangeDistance.addActionListener(surveyEditorActionListener);
//endregion

//region btnChangeDirection

            btnChangeDirection = new JButton(new ImageIcon("images/change_direction.png"));
            btnChangeDirection.setActionCommand("btnChangeDirection");
            btnChangeDirection.setToolTipText(titles.get("TAHbtnChangeDirectionTT"));
            btnChangeDirection.addActionListener(surveyEditorActionListener);
//endregion

//region btnChangeTiltAngle

            btnChangeTilt = new JButton(new ImageIcon("images/change_tilt.png"));
            btnChangeTilt.setActionCommand("btnChangeTilt");
            btnChangeTilt.setToolTipText(titles.get("TAHbtnChangeTiltAngleTT"));
            btnChangeTilt.addActionListener(surveyEditorActionListener);
//endregion

//region tbPickets

            JToolBar tbPickets = new JToolBar();
            tbPickets.setBorder(BorderFactory.createEtchedBorder());
            tbPickets.add(btnDeleteRow);
            tbPickets.add(btnInsertRowBefore);
            tbPickets.add(btnInsertRowAfter);
            tbPickets.addSeparator();
            tbPickets.add(btnChangeDistance);
            tbPickets.add(btnChangeDirection);
            tbPickets.add(btnChangeTilt);
            tbPickets.setFloatable(false);
            pnlPickets.add(tbPickets, BorderLayout.NORTH);
//endregion

//region tblPickets

            updateTblPickets();
            this.add(pnlPickets, new GridBagConstraints(
                    1,
                    0,
                    1,
                    2,
                    1,
                    1,
                    GridBagConstraints.EAST,
                    GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 120, 0));
//endregion

//region order

            order = new Vector<Component>(9);
            order.add(tfStationName);
            order.add(tfStationX);
            order.add(tfStationY);
            order.add(tfStationZ);
            order.add(tfStationI);
            order.add(tfOrName);
            order.add(tfOrX);
            order.add(tfOrY);
            order.add(pnlStations);
//endregion
//        }
    }

    /**
     * Updates text elements of GUI.
     */
    public void translate() {
        HashMap<String, String> titles = shell.getTitles();
        pnlStation.setBorder(BorderFactory.createTitledBorder(null,
                titles.get("TAHlblStationTitle"),
                TitledBorder.CENTER, TitledBorder.TOP,
                new Font(Font.DIALOG, Font.PLAIN, 12), Color.BLUE));
        pnlStations.setBorder(BorderFactory.createTitledBorder(null,
                titles.get("TAHlblStationListTitle"),
                TitledBorder.CENTER, TitledBorder.TOP,
                new Font(Font.DIALOG, Font.PLAIN, 12), Color.BLUE));
        pnlPickets.setBorder(BorderFactory.createTitledBorder(null,
                titles.get("TAHlblPicketsTitle"),
                TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.DIALOG,
                        Font.PLAIN, 12), Color.BLUE));
        lblStationI.setText(titles.get("TAHlblStationI"));
        btnStationName.setText(titles.get("TAHbtnStationName"));
        btnStationName.setToolTipText(titles.get("TAHbtnStationNameTT"));
        btnOrName.setText(titles.get("TAHbtnOrName"));
        btnOrName.setToolTipText(titles.get("TAHbtnOrNameTT"));
        btnDeleteStation.setToolTipText(titles.get("TAHbtnDeleteStationTT"));
        btnInsertStationBefore.setToolTipText(titles.get("TAHbtnInsertStationBeforeTT"));
        btnInsertStationAfter.setToolTipText(titles.get("TAHbtnInsertStationAfterTT"));
        btnDeleteRow.setToolTipText(titles.get("TAHbtnDeleteRowTT"));
        btnInsertRowBefore.setToolTipText(titles.get("TAHbtnInsertRowBeforeTT"));
        btnInsertRowAfter.setToolTipText(titles.get("TAHbtnInsertRowAfterTT"));
        btnChangeDistance.setToolTipText(titles.get("TAHbtnAddDistanceTT"));
        btnChangeDirection.setToolTipText(titles.get("TAHbtnChangeDirectionTT"));
        btnChangeTilt.setToolTipText(titles.get("TAHbtnChangeTiltAngleTT"));

        reloadStationPickets(currentStationIndex);

        revalidate();
    }

    /**
     * Sets current station index
     *
     * @param index int
     */
    @Override
    public void setCurrentStationIndex(int index) {
        currentStationIndex = index;
    }

    /**
     * Gets current station index
     *
     * @return int
     */
    @Override
    public int getCurrentStationIndex() {
        return currentStationIndex;
    }

    /**
     * Gets ParentFrame
     *
     * @return MainWin
     */
    @Override
    public JFrame getParentFrame() {
        return parentFrame;
    }

    /**
     * Sets controls enable is true
     */
    @Override
    public void controlOn() {
        btnStationName.setEnabled(true);
        btnOrName.setEnabled(true);
    }

    /**
     * Sets controls enable is false
     */
    @Override
    public void controlOff() {
        btnStationName.setEnabled(false);
        btnOrName.setEnabled(false);

    }

    /**
     * reload pnlStation
     * @param index int index of current SurveyStation of SurveyProject
     */
    public void reloadStations(int index) {
        if (scpStations != null) {
            pnlStations.remove(scpStations);
        }
        String [] stations = new String[surveyService.sizeRepository()];
        for (int i = 0; i < surveyService.sizeRepository(); i++) {
            stations [i] = surveyService.findStationById(i).getName();
        }
        lstStations = new JList<>(stations);
        lstStations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstStations.setSelectedIndex(index);
        lstStations.addListSelectionListener(e -> {
            currentStationIndex = lstStations.getSelectedIndex();
            reloadStationPickets(lstStations.getSelectedIndex());
        });
        scpStations = new JScrollPane(lstStations);
        pnlStations.add(scpStations,BorderLayout.CENTER);

        if (!catalogService.isEmpty()) {
            controlOn();
        } else {
            controlOff();
        }
        lstStations.requestFocusInWindow();
        revalidate();
    }

    /**
     * reload
     * @param index int index of current SurveyStation of SurveyProject
     */
    public void reloadStationPickets(int index) {
        SurveyStation surveyStation = surveyService.findStationById(index);
        tfStationName.setText(surveyStation.getName());
        tfStationX.setText(surveyStation.getX());
        tfStationY.setText(surveyStation.getY());
        tfStationZ.setText(surveyStation.getZ());
        tfStationI.setText(surveyStation.getVi());
        tfOrName.setText(surveyStation.getNameOr());
        tfOrX.setText(surveyStation.getNameOr());
        tfOrX.setText(surveyStation.getxOr());
        tfOrY.setText(surveyStation.getyOr());
        remove(pnlPickets);
        updateTblPickets();
        selRow = 0;
        selColumn = 0;
        tblPickets.getSelectionModel().setSelectionInterval(selRow, selRow);
        tblPickets.getColumnModel().getSelectionModel().setSelectionInterval(selColumn, selColumn);
        add(pnlPickets, new GridBagConstraints(1, 0, 1,GridBagConstraints.REMAINDER, 1, 1,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 120, 0));
        revalidate();
    }

    /**
     * Gets tmodelPickets
     *
     * @return TmodelPickets
     */
    @Override
    public JTable getTablePickets() {
        return tblPickets;
    }

    /**
     * Gets selected row index of tablePickets
     *
     * @return int
     */
    @Override
    public int getSelRow() {
        return selRow;
    }

    /**
     * Sets selRow
     */
    @Override
    public void setSelRow(int index) {
        selRow = index;
    }

    @Override
    public int getSelColumn() {
        return selColumn;
    }

    /**
     * set Focus lstStation
     */
    public void setFocusStations() {
        lstStations.requestFocusInWindow();
    }

    /**
     * return btnStationName
     * @return btnStationName
     */
    public JButton getBtnStationName() {
        return btnStationName;
    }

    /**
     * return btnOrName
     * @return JButton
     */

    public JButton getBtnOrName() {
        return btnOrName;
    }

    public Vector<Component> getOrder() {
        return this.order;
    }

    public void updateStation(SurveyStation surveyStation) {
        tfStationName.setText(surveyStation.getName());
        tfStationX.setText(surveyStation.getX());
        tfStationY.setText(surveyStation.getY());
        tfStationZ.setText(surveyStation.getZ());
        tfStationI.setText(surveyStation.getVi());
        tfOrName.setText(surveyStation.getNameOr());
        tfOrX.setText(surveyStation.getNameOr());
        tfOrX.setText(surveyStation.getxOr());
        tfOrY.setText(surveyStation.getyOr());
        reloadStations(currentStationIndex);
    }

    /**
     * Updates tblPickets
     */
    private void updateTblPickets() {
        SurveyStation surveyStation = surveyService.findStationById(currentStationIndex);
        if (scpPickets != null) {
            pnlPickets.remove(scpPickets);
        }

        TmodelPickets tmodelPickets = new TmodelPickets(currentStationIndex);
        tblPickets = new JTable(tmodelPickets);
        String[] str;
        for (int i = 0; i < surveyStation.sizePickets(); i++) {
            str = new String[5];
            str[0] = surveyStation.getPicket(i).getpName();
            str[1] = surveyStation.getPicket(i).getLine();
            str[2] = surveyStation.getPicket(i).getHor();
            str[3] = surveyStation.getPicket(i).getVert();
            str[4] = surveyStation.getPicket(i).getV();
            tmodelPickets.addRow(str);
        }

        tblPickets.getTableHeader().setReorderingAllowed(false);
        tblPickets.setColumnSelectionAllowed(true);
        ListSelectionModel columnSelectionModel = tblPickets.getColumnModel().getSelectionModel();
        columnSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblPickets.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ListSelectionModel selModel = tblPickets.getSelectionModel();
        selModel.addListSelectionListener(e -> selRow = tblPickets.getSelectedRow());
        tblPickets.getColumnModel().getSelectionModel().addListSelectionListener(e -> {
            selColumn = tblPickets.getSelectedColumn();
        });

        tmodelPickets.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {

            }
        });

        selRow = 0;
        selColumn = 0;
        tblPickets.getSelectionModel().setSelectionInterval(selRow, selRow);
        tblPickets.getColumnModel().getSelectionModel().setSelectionInterval(selColumn, selColumn);
        tblPickets.requestFocusInWindow();
        scpPickets = new JScrollPane(tblPickets);
        pnlPickets.add(scpPickets, BorderLayout.CENTER);
    }
}
