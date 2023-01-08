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

public class SurveyEditorStandart extends JPanel  {
    private MainWin parentFrame;
    private Vector<Component> order;
    private JPanel pnlPickets;
    private JScrollPane spnlStations;
    private JPanel pnlStation;
    private JPanel pnlStations;
    private JList <String> lstStations;
    private int index;
    private SurveyStation st;
    private SurveyProject sp;
    private JLabel lblStationI;
    private JTextField tfStationName,
            tfStationX,
            tfStationY,
            tfStationZ,
            tfStationI,
            tfOrName,
            tfOrX,
            tfOrY;
    private JButton btnDeleteStation;
    private JButton btnInsertStationBefore;
    private JButton btnInsertStationAfter;
    private JButton btnStationName;
    private JButton btnOrName;
    private int selRow;
    private int selColumn;
    private JTable tblPickets;
    private TmodelPickets tm;
    private JButton btnDeleteRow;
    private JButton btnInsertRowBefore;
    private JButton btnInsertRowAfter;
    private JButton btnChangeDistance,
    btnChangeDirection, btnChangeTilt;
    private JScrollPane scpPickets;

    /**
     * Constructor
     * @param frame parent frame
     * @param index int index of current SurveyStation of SurveyProject
     */
    public SurveyEditorStandart(MainWin frame, int index) {
//--
        if (!(frame == null)) {
            this.index = index;
            this.sp = frame.getSurveyProject();
            this.parentFrame = frame;
            st = this.sp.getStation(index);
            //    private CatalogPoint cpStation;
            SetCoordinates actionSetStation = new SetCoordinates("StationName");
            SetCoordinates actionSetOr = new SetCoordinates("OrName");
            if (parentFrame.isCatalog()) {
                actionSetStation.setEnabled(true);
                actionSetOr.setEnabled(true);
            }
            ImageIcon imageDeleteRow = new ImageIcon("images/delete_row.png");
            ImageIcon imageInsertRowBefore = new ImageIcon("images/insert_row.png");
            ImageIcon imageInsertRowAfter = new ImageIcon("images/insert_row_after.png");
            setLayout(new GridBagLayout());

// pnlStation_________________________________________________________________________

            pnlStation = new JPanel(new GridBagLayout());
            pnlStation.setBorder(BorderFactory.createTitledBorder(null, parentFrame.getTitles().get("TAHlblStationTitle"), TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.DIALOG, Font.PLAIN, 12), Color.BLUE));
            pnlStation.setPreferredSize(new Dimension(parentFrame.getWidthMain() / 2, parentFrame.getHeightMain()));


// btnStationName_________________________________________________________________

            btnStationName = new JButton(actionSetStation);
            btnStationName.setBorder(BorderFactory.createEtchedBorder());
            btnStationName.setText(parentFrame.getTitles().get("TAHbtnStationName"));
            btnStationName.setToolTipText(parentFrame.getTitles().get("TAHbtnStationNameTT"));

            pnlStation.add(btnStationName, new GridBagConstraints(0, 0, 1, 1, 0, 1,
                    GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 10, 0));

// tfStationName----------------------------------------------------------------------

            tfStationName = new JTextField(15);
            tfStationName.setText(st.getName());
            tfStationName.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    tfStationName.selectAll();
                }

                @Override
                public void focusLost(FocusEvent e) {

                    if (tfStationName.getText().isEmpty()) {
                        tfStationName.setText("noname");
                        st.setName("noname");
                        reloadStations(lstStations.getSelectedIndex());
                    } else {
                        st.setName(tfStationName.getText());
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

            tfStationX = new JTextField(st.getX(), 15);
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
                        st.setX("0.000");
                    } else {
                        st.setX(new DataHandler(tfStationX.getText()).commaToPoint().format(3).getStr());
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

            tfStationY = new JTextField(st.getY(), 15);
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
                        st.setY("0.000");
                    } else {
                        st.setY(new DataHandler(tfStationY.getText()).commaToPoint().format(3).getStr());
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

            tfStationZ = new JTextField(st.getZ(), 15);
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
                        st.setZ("0.000");
                    } else {
                        st.setZ(new DataHandler(tfStationZ.getText()).commaToPoint().format(3).getStr());
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

            lblStationI = new JLabel(parentFrame.getTitles().get("TAHlblStationI"), JLabel.RIGHT);

            pnlStation.add(lblStationI, new GridBagConstraints(0, 4, 1, 1, 0, 0,
                    GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

// tfStationI___________________________________________________________________

            tfStationI = new JTextField(st.getVi(), 15);
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
                        st.setVi("0.000");
                    } else {
                        st.setVi(new DataHandler(tfStationI.getText()).commaToPoint().format(3).getStr());
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
            btnOrName.setText(parentFrame.getTitles().get("TAHbtnOrName"));
            btnOrName.setToolTipText(parentFrame.getTitles().get("TAHbtnOrNameTT"));

            pnlStation.add(btnOrName, new GridBagConstraints(0, 5, 1, 1, 0, 1,
                    GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

// tfOrName_______________________________________________________________

            tfOrName = new JTextField(st.getNameOr(), 15);
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
                        st.setNameOr("noname");
                    } else {
                        st.setNameOr(tfOrName.getText());
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

            tfOrX = new JTextField(st.getxOr());
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
                        st.setxOr("0.000");
                    } else {
                        st.setxOr(new DataHandler(tfOrX.getText()).commaToPoint().format(3).getStr());
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

            tfOrY = new JTextField(st.getY());
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
                        st.setyOr("0.000");
                    } else {
                        st.setyOr(new DataHandler(tfOrY.getText()).commaToPoint().format(3).getStr());
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
                    st.setyOr("0.000");
                } else {
                    st.setyOr(new DataHandler(tfOrY.getText()).format(3).getStr());
                }
            });

            pnlStation.add(tfOrY, new GridBagConstraints(1, 7, 1, 1, 1, 0,
                    GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

            this.add(pnlStation,new GridBagConstraints(0, 0, 1, 1, 1, 0,
                    GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

// pnlStations________________________________________________________________________

            pnlStations = new JPanel();
            pnlStations.setLayout(new BorderLayout());
            pnlStations.setBorder(BorderFactory.createTitledBorder(null, parentFrame.getTitles().get("TAHlblStationListTitle"), TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.DIALOG, Font.PLAIN, 12), Color.BLUE));

// btnDeleteStation____________________________________________________________________

                btnDeleteStation = new JButton(imageDeleteRow);
                btnDeleteStation.setToolTipText(parentFrame.getTitles().get("TAHbtnDeleteStationTT"));
                btnDeleteStation.addActionListener(e -> {
                    if (sp.sizeStations() > 1) {
                        sp.removeStation(this.index);
                        if (this.index == sp.sizeStations()) {
                            this.index--;
                        }
                        reloadStations(this.index);
                        reloadPickets(this.index);
                        lstStations.requestFocusInWindow();
//                        setFocusStations();
                    }

                });

// btnInsertStationBefore____________________________________________________________________

                btnInsertStationBefore = new JButton(imageInsertRowBefore);
                btnInsertStationBefore.setToolTipText(parentFrame.getTitles().get("TAHbtnInsertStationBeforeTT"));
                btnInsertStationBefore.addActionListener(e -> {
                    st = sp.addStation(this.index);
                    reloadStations(this.index);
                    reloadPickets(this.index);
                    lstStations.requestFocusInWindow();
//                    setFocusStations();
                });

// btnInsertStationAfter____________________________________________________________________

                btnInsertStationAfter = new JButton(imageInsertRowAfter);
                btnInsertStationAfter.setToolTipText(parentFrame.getTitles().get("TAHbtnInsertStationAfterTT"));
                btnInsertStationAfter.addActionListener(e -> {
                    this.index++;
                    st = sp.addStation(this.index);
                    reloadStations(this.index);
                    reloadPickets(this.index);
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
            pnlStations.add(spnlStations, BorderLayout.CENTER);

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
            pnlPickets.setBorder(BorderFactory.createTitledBorder(null, parentFrame.getTitles().get("TAHlblPicketsTitle"), TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.DIALOG, Font.PLAIN, 12), Color.BLUE));

// btnDeleteRow_______________________________________________________

            btnDeleteRow = new JButton(new ImageIcon("images/delete_row.png"));
            btnDeleteRow.setToolTipText(parentFrame.getTitles().get("TAHbtnDeleteRowTT"));
            btnDeleteRow.addActionListener(e -> {
                if (tm.getRowCount() > 1) {
                    TmodelPickets model = (TmodelPickets) tblPickets.getModel();
                    int k = selRow;
                    st.removePicket(selRow);
                    model.removeRow(selRow);
                    if (k == st.sizePickets()) {
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
            btnInsertRowBefore.setToolTipText(parentFrame.getTitles().get("TAHbtnInsertRowBeforeTT"));
            btnInsertRowBefore.addActionListener(e -> {
                if (selRow >= 0) {
                    st.addPicket(selRow);
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
            btnInsertRowAfter.setToolTipText(parentFrame.getTitles().get("TAHbtnInsertRowAfterTT"));

            btnInsertRowAfter.addActionListener(e -> {
                if (selRow >= 0) {
                    selRow++;
                    st.addPicket(selRow);
                    TmodelPickets model = (TmodelPickets) tblPickets.getModel();
                    model.addRow(selRow, new String[]{"noname", "0.000", "0.0000", "0.0000", "0.000"});
                    tblPickets.getSelectionModel().setSelectionInterval(selRow, selRow);
                    tblPickets.getColumnModel().getSelectionModel().setSelectionInterval(selColumn, selColumn);
                    tblPickets.requestFocusInWindow();
                }
            });

// btnChangeDistance_________________________________________________

            btnChangeDistance = new JButton(new ImageIcon("images/rearrange.png"));
            btnChangeDistance.setToolTipText(parentFrame.getTitles().get("TAHbtnAddDistanceTT"));
            btnChangeDistance.addActionListener(e -> {
                changeDistance();
            });

// btnChangeDirection_________________________________________________

            btnChangeDirection = new JButton(new ImageIcon("images/change_direction.png"));
            btnChangeDirection.setToolTipText(parentFrame.getTitles().get("TAHbtnChangeDirectionTT"));
            btnChangeDirection.addActionListener(e -> changeDirection());

// btnChangeTiltAngle_________________________________________________

            btnChangeTilt = new JButton(new ImageIcon("images/change_tilt.png"));
            btnChangeTilt.setToolTipText(parentFrame.getTitles().get("TAHbtnChangeTiltAngleTT"));
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
                    GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 120, 0));

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


// Tne END of Constructor
    }

    public void translate() {
        pnlStation.setBorder(BorderFactory.createTitledBorder(null, parentFrame.getTitles().get("TAHlblStationTitle"), TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.DIALOG, Font.PLAIN, 12), Color.BLUE));
        pnlStations.setBorder(BorderFactory.createTitledBorder(null, parentFrame.getTitles().get("TAHlblStationListTitle"), TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.DIALOG, Font.PLAIN, 12), Color.BLUE));
        pnlPickets.setBorder(BorderFactory.createTitledBorder(null, parentFrame.getTitles().get("TAHlblPicketsTitle"), TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.DIALOG, Font.PLAIN, 12), Color.BLUE));
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

        reloadPickets(index);



        revalidate();
    }
    /**
     * reload pnlStation
     * @param index int index of current SurveyStation of SurveyProject
     */
    private void reloadStations(int index) {
        if (spnlStations != null) {
            pnlStations.remove(spnlStations);
        }
        String [] stations = new String[sp.sizeStations()];
        for (int i = 0; i < sp.sizeStations(); i++) {
            stations [i] = sp.getStation(i).getName();
        }
        lstStations = new JList<String>(stations);
        lstStations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstStations.setSelectedIndex(index);
        lstStations.addListSelectionListener(e -> {
            this.index = lstStations.getSelectedIndex();
            reloadPickets(lstStations.getSelectedIndex());
        });
        spnlStations = new JScrollPane(lstStations);


        pnlStations.add(spnlStations,BorderLayout.CENTER);
        revalidate();
    }

    /**
     * reload
     * @param index int index of current SurveyStation of SurveyProject
     */
    private void reloadPickets(int index) {
        st = sp.getStation(index);
        tfStationName.setText(st.getName());
        tfStationX.setText(st.getX());
        tfStationY.setText(st.getY());
        tfStationZ.setText(st.getZ());
        tfStationI.setText(st.getVi());
        tfOrName.setText(st.getNameOr());
        tfOrX.setText(st.getNameOr());
        tfOrX.setText(st.getxOr());
        tfOrY.setText(st.getyOr());
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
        if (parentFrame.getOptions().getChanged()) {
            String str;
            double line = Double.parseDouble((String) tm.getValueAt(selRow, 1));
            double tilt = new DataHandler((String) tm.getValueAt(selRow, 3)).dmsToRad();
            double offset = Double.parseDouble(parentFrame.getOptions().getOffsetDistance());
            switch (parentFrame.getOptions().getOffsetDistanceType()) {
                case 0 -> {
                    str = new DataHandler(line + offset / Math.cos(tilt)).format(3).getStr();
                    tm.setValueAt(str, selRow, 1);
                }
                case 1 -> {
                    str = new DataHandler(line + offset).format(3).getStr();
                    tm.setValueAt(str, selRow, 1);
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
        if (parentFrame.getOptions().getChanged()) {
            switch (parentFrame.getOptions().getOffsetDirectionType()) {
                case 0 -> {
                    if (selRow < tm.getRowCount() - 1) {
                        tm.setValueAt(tm.getValueAt(selRow + 1, 2), selRow, 2);
                    }
                }
                case 1 -> {
                    double angle = new DataHandler((String) tm.getValueAt(selRow, 2)).dmsToDeg();
                    double offset = new DataHandler(parentFrame.getOptions().getOffsetDirection()).dmsToDeg();
                    angle += offset;
                    while (angle < 0) {
                        angle += 360;
                    }
                    while (angle > 360) {
                        angle -= 360;
                    }
                    tm.setValueAt(new DataHandler().degToDms(angle).getStr(), selRow, 2);
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
        if (parentFrame.getOptions().getChanged()) {
            double line = Double.parseDouble((String) tm.getValueAt(selRow, 1));
            double tilt = new DataHandler((String) tm.getValueAt(selRow, 3)).dmsToRad();
            switch (parentFrame.getOptions().getOffsetTiltType()) {
                case 0 -> {
                    if (selRow < tm.getRowCount() - 1) {
                        double tiltNext = new DataHandler((String) tm.getValueAt(selRow + 1, 3)).dmsToRad();
                        line = line * Math.cos(tilt) / Math.cos(tiltNext);
                        tm.setValueAt(new DataHandler(line).format(3).getStr(), selRow, 1);
                        tm.setValueAt(tm.getValueAt(selRow + 1, 3), selRow, 3);
                        tm.setValueAt("0.000", selRow,4);
                    }

                }
                case 1 -> {
                    double angle = new DataHandler((String) tm.getValueAt(selRow, 3)).dmsToDeg();
                    double offset = new DataHandler(parentFrame.getOptions().getOffsetTiltAngle()).dmsToDeg();
                    line = line * Math.cos(tilt) / Math.cos(new DataHandler().degToDms(angle + offset).dmsToRad());
                    tm.setValueAt(new DataHandler(line).format(3).getStr(), selRow, 1);
                    tm.setValueAt(new DataHandler().degToDms(angle + offset).getStr(), selRow, 3);
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
     * @return
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

        String name;

        public SetCoordinates(String name) {
            super();
            this.name = name;
            if (parentFrame.isCatalog()) {
                setEnabled(true);
            } else {
                setEnabled(false);
            }
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (parentFrame.isCatalog()) {
                new ShowCatalog(parentFrame, index, name);
                st = sp.getStation(index);
                tfStationName.setText(st.getName());
                tfStationX.setText(st.getX());
                tfStationY.setText(st.getY());
                tfStationZ.setText(st.getZ());
                tfStationI.setText(st.getVi());
                tfOrName.setText(st.getNameOr());
                tfOrX.setText(st.getNameOr());
                tfOrX.setText(st.getxOr());
                tfOrY.setText(st.getyOr());
                reloadStations(index);
            }
        }
    }

    private void updateTblPickets() {
        if (scpPickets != null) {
            pnlPickets.remove(scpPickets);
        }

        tm = new TmodelPickets();
        tblPickets = new JTable(tm);
        String[] str;
        for (int i = 0; i < st.sizePickets(); i++) {
            str = new String[5];
            str[0] = st.getPicket(i).getpName();
            str[1] = st.getPicket(i).getLine();
            str[2] = st.getPicket(i).getHor();
            str[3] = st.getPicket(i).getVert();
            str[4] = st.getPicket(i).getV();
            tm.addRow(str);
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

        tm.addTableModelListener(new TableModelListener() {
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
