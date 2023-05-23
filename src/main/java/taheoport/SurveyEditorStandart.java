package taheoport;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class encapsulates panel for display editing survey measurement
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class SurveyEditorStandart extends JPanel  {
    private JButton btnDeleteRow;
    private JButton btnInsertRowBefore;
    private JButton btnInsertRowAfter;
    private JButton btnChangeDistance,
            btnChangeDirection,
            btnChangeTilt;
    private JButton btnDeleteStation;
    private JButton btnInsertStationBefore;
    private JButton btnInsertStationAfter;
    private JButton btnStationName;
    private JButton btnOrName;
    private int index;
    private JLabel lblStationI;
    private JList<String> lstStations;
    private Vector<Component> order;
    private MainWin parentFrame;
    private JPanel pnlPickets;
    private JPanel pnlStation;
    private JPanel pnlStations;
    private int selRow;
    private int selColumn;
    private JScrollPane scpPickets;
    private JScrollPane scpStations;
    private SurveyProject surveyProject;
    private SurveyStation surveyStation;
    private JTextField tfStationName,
            tfStationX,
            tfStationY,
            tfStationZ,
            tfStationI,
            tfOrName,
            tfOrX,
            tfOrY;
    private JTable tblPickets;
    private TmodelPickets tmodelPickets;

    /**
     * Constructor
     * @param parentFrame parent parentFrame
     * @param index int index of current SurveyStation of SurveyProject
     */
    public SurveyEditorStandart(MainWin parentFrame, int index) {
//--
        if (!(parentFrame == null)) {
            this.index = index;
            this.surveyProject = parentFrame.getSurveyProject();
            this.parentFrame = parentFrame;
            surveyStation = this.surveyProject.getStation(index);
            //    private CatalogPoint cpStation;
            SetCoordinates actionSetStation = new SetCoordinates("StationName");
            SetCoordinates actionSetOr = new SetCoordinates("OrName");
            if (this.parentFrame.isCatalog()) {
                actionSetStation.setEnabled(true);
                actionSetOr.setEnabled(true);
            }
            ImageIcon imageDeleteRow = new ImageIcon("images/delete_row.png");
            ImageIcon imageInsertRowBefore = new ImageIcon("images/insert_row.png");
            ImageIcon imageInsertRowAfter = new ImageIcon("images/insert_row_after.png");
            setLayout(new GridBagLayout());

// pnlStation_________________________________________________________________________

            pnlStation = new JPanel(new GridBagLayout());
            pnlStation.setBorder(BorderFactory.createTitledBorder(null, this.parentFrame.getTitles().get("TAHlblStationTitle"), TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.DIALOG, Font.PLAIN, 12), Color.BLUE));
            pnlStation.setPreferredSize(new Dimension(this.parentFrame.getWidthMain() / 2, this.parentFrame.getHeightMain()));


// btnStationName_________________________________________________________________

            btnStationName = new JButton(actionSetStation);
            btnStationName.setBorder(BorderFactory.createEtchedBorder());
            btnStationName.setText(this.parentFrame.getTitles().get("TAHbtnStationName"));
            btnStationName.setToolTipText(this.parentFrame.getTitles().get("TAHbtnStationNameTT"));

            pnlStation.add(btnStationName, new GridBagConstraints(0, 0, 1, 1, 0, 1,
                    GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 10, 0));

// tfStationName----------------------------------------------------------------------

            tfStationName = new JTextField(15);
            tfStationName.setText(surveyStation.getName());
            tfStationName.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    tfStationName.selectAll();
                }

                @Override
                public void focusLost(FocusEvent e) {

                    if (tfStationName.getText().isEmpty()) {
                        tfStationName.setText("noname");
                        surveyStation.setName("noname");
                        reloadStations(lstStations.getSelectedIndex());
                    } else {
                        surveyStation.setName(tfStationName.getText());
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

// lblStationX______________________________________________________________________

            JLabel lblStationX = new JLabel("X =  ", JLabel.RIGHT);
            pnlStation.add(lblStationX, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                    GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

// tfStationX------------------------------------------------------------------------------

            tfStationX = new JTextField(surveyStation.getX(), 15);
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
                        surveyStation.setX("0.000");
                    } else {
                        surveyStation.setX(new DataHandler(tfStationX.getText()).commaToPoint().format(3).getStr());
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
            pnlStation.add(tfStationX, new GridBagConstraints(1, 1, 1, 1, 1, 0,
                    GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 20, 0));

// lblStationY_______________________________________________________________________

            JLabel lblStationY = new JLabel("Y =  ", JLabel.RIGHT);
            pnlStation.add(lblStationY, new GridBagConstraints(0, 2, 1, 1, 0, 0,
                    GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

// tfStationY_________________________________________________________________________

            tfStationY = new JTextField(surveyStation.getY(), 15);
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
                        surveyStation.setY("0.000");
                    } else {
                        surveyStation.setY(new DataHandler(tfStationY.getText()).commaToPoint().format(3).getStr());
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

// lblStationZ______________________________________________________________________

            JLabel lblStationZ = new JLabel("Z =  ", JLabel.RIGHT);

            pnlStation.add(lblStationZ, new GridBagConstraints(0, 3, 1, 1, 0, 0,
                    GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

// tfStationZ_____________________________________________________________________

            tfStationZ = new JTextField(surveyStation.getZ(), 15);
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
                        surveyStation.setZ("0.000");
                    } else {
                        surveyStation.setZ(new DataHandler(tfStationZ.getText()).commaToPoint().format(3).getStr());
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

// lblStationI_______________________________________________________________________

            lblStationI = new JLabel(this.parentFrame.getTitles().get("TAHlblStationI"), JLabel.RIGHT);

            pnlStation.add(lblStationI, new GridBagConstraints(0, 4, 1, 1, 0, 0,
                    GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

// tfStationI___________________________________________________________________

            tfStationI = new JTextField(surveyStation.getVi(), 15);
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
                        surveyStation.setVi("0.000");
                    } else {
                        surveyStation.setVi(new DataHandler(tfStationI.getText()).commaToPoint().format(3).getStr());
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

            pnlStation.add(tfStationI, new GridBagConstraints(1, 4, 1, 1, 1, 0,
                    GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 20, 0));

// btnOrName____________________________________________________________________

            btnOrName = new JButton(actionSetOr);
            btnOrName.setBorder(BorderFactory.createEtchedBorder());
            btnOrName.setText(this.parentFrame.getTitles().get("TAHbtnOrName"));
            btnOrName.setToolTipText(this.parentFrame.getTitles().get("TAHbtnOrNameTT"));

            pnlStation.add(btnOrName, new GridBagConstraints(0, 5, 1, 1, 0, 1,
                    GridBagConstraints.EAST, GridBagConstraints.NONE,
                    new Insets(0, 0, 0, 0), 0, 0));

// tfOrName_______________________________________________________________

            tfOrName = new JTextField(surveyStation.getNameOr(), 15);
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
                        surveyStation.setNameOr("noname");
                    } else {
                        surveyStation.setNameOr(tfOrName.getText());
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

            pnlStation.add(tfOrName, new GridBagConstraints(1, 5, 1, 1, 1, 0,
                    GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 20, 0));

// lblOrX______________________________________________________________________

            JLabel lblOrX = new JLabel("X =  ", JLabel.RIGHT);

            pnlStation.add(lblOrX, new GridBagConstraints(0, 6, 1, 1, 0, 0,
                    GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

// tfOrX________________________________________________________________________

            tfOrX = new JTextField(surveyStation.getxOr());
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
                        surveyStation.setxOr("0.000");
                    } else {
                        surveyStation.setxOr(new DataHandler(tfOrX.getText()).commaToPoint().format(3).getStr());
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

            pnlStation.add(tfOrX, new GridBagConstraints(1, 6, 1, 1, 1, 0,
                    GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

// lblOrY_______________________________________________________________________

            JLabel lblOrY = new JLabel("Y =  ", JLabel.RIGHT);

            pnlStation.add(lblOrY, new GridBagConstraints(0, 7, 1, 1, 0, 0,
                    GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

// tfOrY_______________________________________________________________________

            tfOrY = new JTextField(surveyStation.getY());
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
                        surveyStation.setyOr("0.000");
                    } else {
                        surveyStation.setyOr(new DataHandler(tfOrY.getText()).commaToPoint().format(3).getStr());
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
                    surveyStation.setyOr("0.000");
                } else {
                    surveyStation.setyOr(new DataHandler(tfOrY.getText()).format(3).getStr());
                }
            });

            pnlStation.add(tfOrY, new GridBagConstraints(1, 7, 1, 1, 1, 0,
                    GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

            this.add(pnlStation,new GridBagConstraints(0, 0, 1, 1, 1, 0,
                    GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

// pnlStations________________________________________________________________________

            pnlStations = new JPanel();
            pnlStations.setLayout(new BorderLayout());
            pnlStations.setBorder(BorderFactory.createTitledBorder(null, this.parentFrame.getTitles().get("TAHlblStationListTitle"), TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.DIALOG, Font.PLAIN, 12), Color.BLUE));

// btnDeleteStation____________________________________________________________________

                btnDeleteStation = new JButton(imageDeleteRow);
                btnDeleteStation.setToolTipText(this.parentFrame.getTitles().get("TAHbtnDeleteStationTT"));
                btnDeleteStation.addActionListener(e -> {
                    if (surveyProject.sizeStations() > 1) {
                        surveyProject.removeStation(this.index);
                        if (this.index == surveyProject.sizeStations()) {
                            this.index--;
                        }
                        reloadStations(this.index);
                        reloadStationPickets(this.index);
                        lstStations.requestFocusInWindow();
//                        setFocusStations();
                    }

                });

// btnInsertStationBefore____________________________________________________________________

                btnInsertStationBefore = new JButton(imageInsertRowBefore);
                btnInsertStationBefore.setToolTipText(this.parentFrame.getTitles().get("TAHbtnInsertStationBeforeTT"));
                btnInsertStationBefore.addActionListener(e -> {
                    surveyStation = surveyProject.addStation(this.index);
                    reloadStations(this.index);
                    reloadStationPickets(this.index);
                    lstStations.requestFocusInWindow();
//                    setFocusStations();
                });

// btnInsertStationAfter____________________________________________________________________

                btnInsertStationAfter = new JButton(imageInsertRowAfter);
                btnInsertStationAfter.setToolTipText(this.parentFrame.getTitles().get("TAHbtnInsertStationAfterTT"));
                btnInsertStationAfter.addActionListener(e -> {
                    this.index++;
                    surveyStation = surveyProject.addStation(this.index);
                    reloadStations(this.index);
                    reloadStationPickets(this.index);
                    lstStations.requestFocusInWindow();
//                    setFocusStations();
                });

// tbStation____________________________________________________________________________

                JToolBar tbStations = new JToolBar();
                tbStations.add(btnDeleteStation);
                tbStations.add(btnInsertStationBefore);
                tbStations.add(btnInsertStationAfter);
                tbStations.setFloatable(false);
                tbStations.setBorder(BorderFactory.createEtchedBorder());

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

            this.add(pnlStations, new GridBagConstraints(0, 1, 1,GridBagConstraints.REMAINDER, 1, 0,
                    GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));


// pnlPickets__________________________________________________________________________

            pnlPickets = new JPanel();
            pnlPickets.setLayout(new BorderLayout());
            pnlPickets.setBorder(BorderFactory.createTitledBorder(null, this.parentFrame.getTitles().get("TAHlblPicketsTitle"), TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.DIALOG, Font.PLAIN, 12), Color.BLUE));

// btnDeleteRow_______________________________________________________

            btnDeleteRow = new JButton(new ImageIcon("images/delete_row.png"));
            btnDeleteRow.setToolTipText(this.parentFrame.getTitles().get("TAHbtnDeleteRowTT"));
            btnDeleteRow.addActionListener(e -> {
                if (tmodelPickets.getRowCount() > 1) {
                    TmodelPickets model = (TmodelPickets) tblPickets.getModel();
                    int k = selRow;
                    surveyStation.removePicket(selRow);
                    model.removeRow(selRow);
                    if (k == surveyStation.sizePickets()) {
                        k--;
                    }
                    selRow = k;
                    tblPickets.getSelectionModel().setSelectionInterval(selRow, selRow);
                    tblPickets.getColumnModel().getSelectionModel().setSelectionInterval(selColumn, selColumn);
                    tblPickets.requestFocusInWindow();
                }
            });

// btnInsertRowBefore_______________________________________________________

            btnInsertRowBefore = new JButton(new ImageIcon("images/insert_row.png"));
            btnInsertRowBefore.setToolTipText(this.parentFrame.getTitles().get("TAHbtnInsertRowBeforeTT"));
            btnInsertRowBefore.addActionListener(e -> {
                if (selRow >= 0) {
                    surveyStation.addPicket(selRow);
                    TmodelPickets model = (TmodelPickets) tblPickets.getModel();
                    model.addRow(selRow, new String[]{"noname", "0.000", "0.0000", "0.0000", "0.000"});
                    selRow --;
                    tblPickets.getSelectionModel().setSelectionInterval(selRow, selRow);
                    tblPickets.getColumnModel().getSelectionModel().setSelectionInterval(selColumn, selColumn);
                    tblPickets.requestFocusInWindow();
                }
            });

// btnInsertRowAfter_______________________________________________________

            btnInsertRowAfter = new JButton(new ImageIcon("images/insert_row_after.png"));
            btnInsertRowAfter.setToolTipText(this.parentFrame.getTitles().get("TAHbtnInsertRowAfterTT"));

            btnInsertRowAfter.addActionListener(e -> {
                if (selRow >= 0) {
                    selRow++;
                    surveyStation.addPicket(selRow);
                    TmodelPickets model = (TmodelPickets) tblPickets.getModel();
                    model.addRow(selRow, new String[]{"noname", "0.000", "0.0000", "0.0000", "0.000"});
                    tblPickets.getSelectionModel().setSelectionInterval(selRow, selRow);
                    tblPickets.getColumnModel().getSelectionModel().setSelectionInterval(selColumn, selColumn);
                    tblPickets.requestFocusInWindow();
                }
            });

// btnChangeDistance_________________________________________________

            btnChangeDistance = new JButton(new ImageIcon("images/rearrange.png"));
            btnChangeDistance.setToolTipText(this.parentFrame.getTitles().get("TAHbtnAddDistanceTT"));
            btnChangeDistance.addActionListener(e -> {
                changeDistance();
            });

// btnChangeDirection_________________________________________________

            btnChangeDirection = new JButton(new ImageIcon("images/change_direction.png"));
            btnChangeDirection.setToolTipText(this.parentFrame.getTitles().get("TAHbtnChangeDirectionTT"));
            btnChangeDirection.addActionListener(e -> changeDirection());

// btnChangeTiltAngle_________________________________________________

            btnChangeTilt = new JButton(new ImageIcon("images/change_tilt.png"));
            btnChangeTilt.setToolTipText(this.parentFrame.getTitles().get("TAHbtnChangeTiltAngleTT"));
            btnChangeTilt.addActionListener(e -> changeTilt());

// tbPickets_______________________________________________________

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


// tblPickets_______________________________________________________

            updateTblPickets();
            this.add(pnlPickets, new GridBagConstraints(1, 0, 1, 2, 1, 1,
                    GridBagConstraints.EAST, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0),
                    120, 0));

// order___________________________________________________________

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

        }
    }

    /**
     * Updates text elements of GUI.
     */
    public void translate() {
        pnlStation.setBorder(BorderFactory.createTitledBorder(null,
                parentFrame.getTitles().get("TAHlblStationTitle"),
                TitledBorder.CENTER, TitledBorder.TOP,
                new Font(Font.DIALOG, Font.PLAIN, 12), Color.BLUE));
        pnlStations.setBorder(BorderFactory.createTitledBorder(null,
                parentFrame.getTitles().get("TAHlblStationListTitle"),
                TitledBorder.CENTER, TitledBorder.TOP,
                new Font(Font.DIALOG, Font.PLAIN, 12), Color.BLUE));
        pnlPickets.setBorder(BorderFactory.createTitledBorder(null,
                parentFrame.getTitles().get("TAHlblPicketsTitle"),
                TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.DIALOG,
                        Font.PLAIN, 12), Color.BLUE));
        lblStationI.setText(parentFrame.getTitles().get("TAHlblStationI"));
        btnStationName.setText(parentFrame.getTitles().get("TAHbtnStationName"));
        btnStationName.setToolTipText(parentFrame.getTitles().get("TAHbtnStationNameTT"));
        btnOrName.setText(parentFrame.getTitles().get("TAHbtnOrName"));
        btnOrName.setToolTipText(parentFrame.getTitles().get("TAHbtnOrNameTT"));
        btnDeleteStation.setToolTipText(parentFrame.getTitles().get("TAHbtnDeleteStationTT"));
        btnInsertStationBefore.setToolTipText(parentFrame.getTitles().get("TAHbtnInsertStationBeforeTT"));
        btnInsertStationAfter.setToolTipText(parentFrame.getTitles().get("TAHbtnInsertStationAfterTT"));
        btnDeleteRow.setToolTipText(parentFrame.getTitles().get("TAHbtnDeleteRowTT"));
        btnInsertRowBefore.setToolTipText(parentFrame.getTitles().get("TAHbtnInsertRowBeforeTT"));
        btnInsertRowAfter.setToolTipText(parentFrame.getTitles().get("TAHbtnInsertRowAfterTT"));
        btnChangeDistance.setToolTipText(parentFrame.getTitles().get("TAHbtnAddDistanceTT"));
        btnChangeDirection.setToolTipText(parentFrame.getTitles().get("TAHbtnChangeDirectionTT"));
        btnChangeTilt.setToolTipText(parentFrame.getTitles().get("TAHbtnChangeTiltAngleTT"));

        reloadStationPickets(index);



        revalidate();
    }
    /**
     * reload pnlStation
     * @param index int index of current SurveyStation of SurveyProject
     */
    private void reloadStations(int index) {
        if (scpStations != null) {
            pnlStations.remove(scpStations);
        }
        String [] stations = new String[surveyProject.sizeStations()];
        for (int i = 0; i < surveyProject.sizeStations(); i++) {
            stations [i] = surveyProject.getStation(i).getName();
        }
        lstStations = new JList<String>(stations);
        lstStations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstStations.setSelectedIndex(index);
        lstStations.addListSelectionListener(e -> {
            this.index = lstStations.getSelectedIndex();
            reloadStationPickets(lstStations.getSelectedIndex());
        });
        scpStations = new JScrollPane(lstStations);


        pnlStations.add(scpStations,BorderLayout.CENTER);
        revalidate();
    }

    /**
     * reload
     * @param index int index of current SurveyStation of SurveyProject
     */
    private void reloadStationPickets(int index) {
        surveyStation = surveyProject.getStation(index);
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
     * Change Distance in the tblPickets
     */
    private void changeDistance() {
        new ShowChangeDistance(parentFrame);
        if (parentFrame.getOptions().isChanged()) {
            String str;
            double line = Double.parseDouble((String) tmodelPickets.getValueAt(selRow, 1));
            double tilt = new DataHandler((String) tmodelPickets.getValueAt(selRow, 3)).dmsToRad();
            double offset = Double.parseDouble(parentFrame.getOptions().getOffsetDistance());
            switch (parentFrame.getOptions().getOffsetDistanceType()) {
                case 0 -> {
                    str = new DataHandler(line + offset / Math.cos(tilt)).format(3).getStr();
                    tmodelPickets.setValueAt(str, selRow, 1);
                }
                case 1 -> {
                    str = new DataHandler(line + offset).format(3).getStr();
                    tmodelPickets.setValueAt(str, selRow, 1);
                }
            }
            tblPickets.getSelectionModel().setSelectionInterval(selRow, selRow);
            tblPickets.getColumnModel().getSelectionModel().setSelectionInterval(selColumn, selColumn);
//            tblPickets.requestFocusInWindow();

        }
        tblPickets.requestFocusInWindow();
    }

    /**
     * Changes Direction in the tblPickets
     */
    private void changeDirection() {
        new ShowChangeAngle(parentFrame,parentFrame.getTitles().get("SCAtitleChangeDirection"));
        if (parentFrame.getOptions().isChanged()) {
            switch (parentFrame.getOptions().getOffsetDirectionType()) {
                case 0 -> {
                    if (selRow < tmodelPickets.getRowCount() - 1) {
                        tmodelPickets.setValueAt(tmodelPickets.getValueAt(selRow + 1, 2), selRow, 2);
                    }
                }
                case 1 -> {
                    double angle = new DataHandler((String) tmodelPickets.getValueAt(selRow, 2)).dmsToDeg();
                    double offset = new DataHandler(parentFrame.getOptions().getOffsetDirection()).dmsToDeg();
                    angle += offset;
                    while (angle < 0) {
                        angle += 360;
                    }
                    while (angle > 360) {
                        angle -= 360;
                    }
                    tmodelPickets.setValueAt(new DataHandler().degToDms(angle).getStr(), selRow, 2);
                }
            }
        }


        tblPickets.getSelectionModel().setSelectionInterval(selRow, selRow);
        tblPickets.getColumnModel().getSelectionModel().setSelectionInterval(selColumn, selColumn);
        tblPickets.requestFocusInWindow();
   }

    /**
     * Changes Direction in the tblPickets
     */
    private void changeTilt() {
        new ShowChangeAngle(parentFrame, parentFrame.getTitles().get("SCAtitleChangeTiltAngle"));
        if (parentFrame.getOptions().isChanged()) {
            double line = Double.parseDouble((String) tmodelPickets.getValueAt(selRow, 1));
            double tilt = new DataHandler((String) tmodelPickets.getValueAt(selRow, 3)).dmsToRad();
            switch (parentFrame.getOptions().getOffsetTiltType()) {
                case 0 -> {
                    if (selRow < tmodelPickets.getRowCount() - 1) {
                        double tiltNext = new DataHandler((String) tmodelPickets.getValueAt(selRow + 1, 3)).dmsToRad();
                        line = line * Math.cos(tilt) / Math.cos(tiltNext);
                        tmodelPickets.setValueAt(new DataHandler(line).format(3).getStr(), selRow, 1);
                        tmodelPickets.setValueAt(tmodelPickets.getValueAt(selRow + 1, 3), selRow, 3);
                        tmodelPickets.setValueAt("0.000", selRow,4);
                    }

                }
                case 1 -> {
                    double angle = new DataHandler((String) tmodelPickets.getValueAt(selRow, 3)).dmsToDeg();
                    double offset = new DataHandler(parentFrame.getOptions().getOffsetTiltAngle()).dmsToDeg();
                    line = line * Math.cos(tilt) / Math.cos(new DataHandler().degToDms(angle + offset).dmsToRad());
                    tmodelPickets.setValueAt(new DataHandler(line).format(3).getStr(), selRow, 1);
                    tmodelPickets.setValueAt(new DataHandler().degToDms(angle + offset).getStr(), selRow, 3);
                }
            }
        }



        tblPickets.getSelectionModel().setSelectionInterval(selRow, selRow);
        tblPickets.getColumnModel().getSelectionModel().setSelectionInterval(selColumn, selColumn);
        tblPickets.requestFocusInWindow();
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


    /**
     * This action set points coordinates from Catalog
     */
    public class SetCoordinates extends AbstractAction{

        private final String name;

        public SetCoordinates(String name) {
            super();
            this.name = name;
            setEnabled(parentFrame.isCatalog());
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (parentFrame.isCatalog()) {
                new ShowCatalog(parentFrame, index, name);
                surveyStation = surveyProject.getStation(index);
                tfStationName.setText(surveyStation.getName());
                tfStationX.setText(surveyStation.getX());
                tfStationY.setText(surveyStation.getY());
                tfStationZ.setText(surveyStation.getZ());
                tfStationI.setText(surveyStation.getVi());
                tfOrName.setText(surveyStation.getNameOr());
                tfOrX.setText(surveyStation.getNameOr());
                tfOrX.setText(surveyStation.getxOr());
                tfOrY.setText(surveyStation.getyOr());
                reloadStations(index);
            }
        }
    }

    /**
     * Updates tblPickets
     */
    private void updateTblPickets() {
        if (scpPickets != null) {
            pnlPickets.remove(scpPickets);
        }

        tmodelPickets = new TmodelPickets();
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
        selModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selRow = tblPickets.getSelectedRow();
            }
        });
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

    /**
     * Class TmodelPickets encapsulates tableModel of tblPickets
     */
    private class TmodelPickets extends AbstractTableModel {
        private int columnCount = 5;
        private ArrayList<String []> dataArrayList;
        /**
         *
         */
        public TmodelPickets() {
            dataArrayList = new ArrayList<String[]>();
        }

        @Override
        public int getRowCount() {
            return dataArrayList.size();
        }

        @Override
        public int getColumnCount() {
            return columnCount;
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0: return parentFrame.getTitles().get("TAHtmColumnName0");
                case 1: return parentFrame.getTitles().get("TAHtmColumnName1");
                case 2: return parentFrame.getTitles().get("TAHtmColumnName2");
                case 3: return parentFrame.getTitles().get("TAHtmColumnName3");
                case 4: return parentFrame.getTitles().get("TAHtmColumnName4");
            }


            return "";
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            String [] row = dataArrayList.get(rowIndex);
            return row [columnIndex];
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int rowIndex, int columnCount) {
            return true;
        }

        public void setValueAt(Object str, int rowIndex, int columnIndex) {
            String [] row = dataArrayList.get(rowIndex);
            DataHandler dataHandler = new DataHandler((String) str).commaToPoint();
            switch (columnIndex) {
                case 0 -> {
                    if (dataHandler.isValidName()) {
                        parentFrame.getSurveyProject().getStation(index).getPicket(rowIndex).setpName((String) str);
                        row [columnIndex] = (String) str;
                        dataArrayList.set(rowIndex, row);
                    }
                }
                case 1 -> {
                    if (dataHandler.isPositiveNumber()) {
                        parentFrame.getSurveyProject().getStation(index).getPicket(rowIndex).setLine(dataHandler.format(3).getStr());
                        row[columnIndex] = dataHandler.format(3).getStr();
                        dataArrayList.set(rowIndex, row);
                    }
                }
                case 2 -> {
                    if (dataHandler.isPositiveNumber()) {
                        parentFrame.getSurveyProject().getStation(index).getPicket(rowIndex).setHor(dataHandler.format(4).getStr());
                        row[columnIndex] = dataHandler.format(4).getStr();
                        dataArrayList.set(rowIndex, row);
                    }
                }
                case 3 -> {
                    if (dataHandler.isNumber()) {
                        parentFrame.getSurveyProject().getStation(index).getPicket(rowIndex).setVert(dataHandler.format(4).getStr());
                        row[columnIndex] = dataHandler.format(4).getStr();
                        dataArrayList.set(rowIndex, row);
                    }
                }
                case 4 -> {
                    if (dataHandler.isNumber()) {
                        parentFrame.getSurveyProject().getStation(index).getPicket(rowIndex).setV(dataHandler.format(3).getStr());
                        row[columnIndex] = dataHandler.format(3).getStr();
                        dataArrayList.set(rowIndex, row);
                    }
                }
            }

            fireTableCellUpdated(rowIndex, columnIndex);
        }

        /**
         * add row to dataArrayList
         * @param row
         */
        public void addRow(String [] row) {
//        String [] rowTable = new String[getColumnCount()];
//        rowTable = row;
            dataArrayList.add(row);
        }

        public void addRow(int index, String[] row) {
            dataArrayList.add(index, row);
            fireTableRowsInserted(index, index);
        }

        /**
         * remove row from dataArrayList
         * @param index
         */
        public void removeRow(int index) {
            dataArrayList.remove(index);
            fireTableRowsDeleted(index, index);


        }
    }

// The END of SurveyEditorStandart
}
