package taheoport;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class encapsulated panel for displaying and editing of polygon
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class PolygonEditorStandart extends JPanel {
    private final JLabel lblAngleResidue;
    private final JLabel lblFXResidue;
    private final JLabel lblFYResidue;
    private final JLabel lblFAbsoluteResidue;
    private final JLabel lblFRelativeResidue;
    private final JLabel lblPerValue;
    private final JLabel lblHeightResidue;
    private final MainWin parentFrame;
    private int selColumn;
    private int selRow;
    private JTable tblStations;
    private TmodelPolygonStations tmPolygonStations;

    /**
     * Constructor
     * @param frame parent MainWin
     */
    public PolygonEditorStandart(MainWin frame) {
        super();
        parentFrame = frame;
        setLayout(new BorderLayout());

// btnDeleteRow_______________________________________________________

        JButton btnDeleteRow = new JButton(new ImageIcon("images/delete_row.png"));
        btnDeleteRow.setEnabled(true);
        btnDeleteRow.setToolTipText(parentFrame.getTitles().get("TAHbtnDeleteRowTT"));
        btnDeleteRow.addActionListener(e -> {
            if (tmPolygonStations.getRowCount() > 1) {
                int k = selRow;
                tmPolygonStations.removeRow(selRow);
                if (k == parentFrame.getPolygonProject().getSizePolygonStations()) k--;
                selRow = k;
                tblStations.getSelectionModel().setSelectionInterval(selRow, selRow);
                tblStations.getColumnModel().getSelectionModel().setSelectionInterval(selColumn, selColumn);
                tblStations.requestFocusInWindow();
            }
        });

// btnInsertRowBefore_______________________________________________________

        JButton btnInsertRowBefore = new JButton(new ImageIcon("images/insert_row.png"));
        btnInsertRowBefore.setEnabled(true);
        btnInsertRowBefore.setToolTipText(parentFrame.getTitles().get("TAHbtnInsertRowBeforeTT"));
        btnInsertRowBefore.addActionListener(e -> {
            if (parentFrame.getPolygonProject().isInsertBefore(selRow)) {
//            int k = selRow - 1;
                parentFrame.getPolygonProject().addStation(selRow);
                tmPolygonStations.addRow(selRow, new Object[]{
                        "noname",
                        "0.0000",
                        "0.000",
                        "0.000",
                        "",
                        "",
                        "",
                        false

                });
                tblStations.getSelectionModel().setSelectionInterval(selRow - 1, selRow - 1);
                tblStations.getColumnModel().getSelectionModel().setSelectionInterval(selColumn, selColumn);
                tblStations.requestFocusInWindow();
            }
        });

// btnInsertRowAfter_______________________________________________________

        JButton btnInsertRowAfter = new JButton(new ImageIcon("images/insert_row_after.png"));
        btnInsertRowAfter.setEnabled(true);
        btnInsertRowAfter.setToolTipText(parentFrame.getTitles().get("TAHbtnInsertRowAfterTT"));
        btnInsertRowAfter.addActionListener(e -> {
            if (parentFrame.getPolygonProject().isInsertAfter(selRow)) {
                selRow++;
                parentFrame.getPolygonProject().addStation(selRow);
                tmPolygonStations.addRow(selRow, new Object[]{
                        "noname",
                        "0.0000",
                        "0.000",
                        "0.000",
                        "",
                        "",
                        "",
                        false

                });
                tblStations.getSelectionModel().setSelectionInterval(selRow, selRow);
                tblStations.getColumnModel().getSelectionModel().setSelectionInterval(selColumn, selColumn);
                tblStations.requestFocusInWindow();
            }
        });

// btnImportFromCatalog_______________________________________________________

        JButton btnImportFromCatalog = new JButton(new ImageIcon("images/database_export.png"));
        btnImportFromCatalog.setEnabled(true);
        btnImportFromCatalog.setToolTipText(parentFrame.getTitles().get("TAHbtnImportFromCatalogTT"));
        btnImportFromCatalog.addActionListener(e -> {
            if (parentFrame.getPolygonProject().getPolygonStation(selRow).getStatus()) {
                new ShowCatalog(parentFrame, selRow, "TheoStation");
                tmPolygonStations.setValueAt(parentFrame.getPolygonProject().getPolygonStation(selRow).getName(), selRow, 0);
                tmPolygonStations.setValueAt(parentFrame.getPolygonProject().getPolygonStation(selRow).getX(), selRow, 4);
                tmPolygonStations.setValueAt(parentFrame.getPolygonProject().getPolygonStation(selRow).getY(), selRow, 5);
                tmPolygonStations.setValueAt(parentFrame.getPolygonProject().getPolygonStation(selRow).getZ(), selRow, 6);
            }
        });



// tbTheoStations__________________________________________________________

        JToolBar tbTheoStations = new JToolBar();
        tbTheoStations.setBorder(BorderFactory.createEtchedBorder());
        tbTheoStations.setFloatable(false);
        tbTheoStations.add(btnDeleteRow);
        tbTheoStations.add(btnInsertRowBefore);
        tbTheoStations.add(btnInsertRowAfter);
        tbTheoStations.addSeparator();
        tbTheoStations.add(btnImportFromCatalog);

        add(tbTheoStations, BorderLayout.NORTH);

// pnlAdjustment____________________________________________________________

        JPanel pnlAdjustment = new JPanel(new GridLayout(7 , 2));
        pnlAdjustment.setBorder(BorderFactory.createTitledBorder(null, parentFrame.getTitles().get("THEOlblTitleBinding"), TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.DIALOG, Font.PLAIN, 12), Color.BLUE));

//lblHeight____________________________________________________

        JLabel lblHeight = new JLabel(parentFrame.getTitles().get("THEOlblHeight"), JLabel.RIGHT);
        pnlAdjustment.add(lblHeight);

//lblHeightResidue________________________________________________

        lblHeightResidue = new JLabel("-.-");
        pnlAdjustment.add(lblHeightResidue);

//lblAngle_______________________________________________________

        JLabel lblAngle = new JLabel(parentFrame.getTitles().get("THEOlblAngle"), JLabel.RIGHT);
        pnlAdjustment.add(lblAngle);

//lblAngleResidue_______________________________________________________

        lblAngleResidue = new JLabel("-.-");
        pnlAdjustment.add(lblAngleResidue);

//lblFX_______________________________________________________

        JLabel lblFX = new JLabel(parentFrame.getTitles().get("THEOlblFX"), JLabel.RIGHT);
        pnlAdjustment.add(lblFX);

//lblFXResidue_______________________________________________________

        lblFXResidue = new JLabel("-.-");
        pnlAdjustment.add(lblFXResidue);

//lblFY_______________________________________________________

        JLabel lblFY = new JLabel(parentFrame.getTitles().get("THEOlblFY"), JLabel.RIGHT);
        pnlAdjustment.add(lblFY);

//lblFYResidue_______________________________________________________

        lblFYResidue = new JLabel("-.-");
        pnlAdjustment.add(lblFYResidue);

//lblFAbsolute_______________________________________________________

        JLabel lblFAbsolute = new JLabel(parentFrame.getTitles().get("THEOlblFAbsolute"), JLabel.RIGHT);
        pnlAdjustment.add(lblFAbsolute);

//lblFAbsoluteResidue_______________________________________________________

        lblFAbsoluteResidue = new JLabel("-.-");
        pnlAdjustment.add(lblFAbsoluteResidue);

//lblFRelative_______________________________________________________

        JLabel lblFRelative = new JLabel(parentFrame.getTitles().get("THEOlblRelative"), JLabel.RIGHT);
        pnlAdjustment.add(lblFRelative);

//lblFRelativeResidue_______________________________________________________

        lblFRelativeResidue = new JLabel("-.-");
        pnlAdjustment.add(lblFRelativeResidue);

//lblPer_______________________________________________________

        JLabel lblPer = new JLabel(parentFrame.getTitles().get("THEOlblPer"), JLabel.RIGHT);
        pnlAdjustment.add(lblPer);

//lblPerValue_______________________________________________________

        lblPerValue = new JLabel("-.-");
        pnlAdjustment.add(lblPerValue);

// pnlPaint_______________________________________________________

        JPanel pnlPaint = new JPanel(new BorderLayout());
        pnlPaint.setPreferredSize(new Dimension(200, 200));
        pnlPaint.setBorder(BorderFactory.createTitledBorder(null, parentFrame.getTitles().get("THEOpnlPaintTitle"), TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.DIALOG, Font.PLAIN, 12), Color.BLUE));

        if (parentFrame.getPolygonProject().getPerimeter() == 0.0) {
            JPanel pnlBlank = new JPanel();
            pnlPaint.add(pnlBlank, BorderLayout.CENTER);

        } else {
            PolygonPaintPanel pnlTheoPaintPanel = new PolygonPaintPanel(parentFrame.getPolygonProject(), -1);
            pnlPaint.add(pnlTheoPaintPanel, BorderLayout.CENTER);
        }

// pnlBottom______________________________________________________

        JPanel pnlBottom = new JPanel(new GridBagLayout());
        pnlBottom.add(pnlAdjustment, new GridBagConstraints(0, 0, 1, 1, 1, 0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        pnlBottom.add(pnlPaint, new GridBagConstraints(1, 0, 1, 1, 1, 0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        add(pnlBottom, BorderLayout.SOUTH);

//tblTheoStations__________________________________________________________

        tmPolygonStations = new TmodelPolygonStations();
        tblStations = new JTable(tmPolygonStations);
        Object[] array;
        for (int i = 0; i < parentFrame.getPolygonProject().getSizePolygonStations(); i++) {
            array = new Object[8];
            if (parentFrame.getPolygonProject().getPolygonStation(i).getName().equals("Not")) {
                array[0] = "";
            } else {
                array[0] = parentFrame.getPolygonProject().getPolygonStation(i).getName();
            }
            if (parentFrame.getPolygonProject().getPolygonStation(i).getHor().equals("Not")) {
                array[1] = "";
            } else {
                array[1] = parentFrame.getPolygonProject().getPolygonStation(i).getHor();
            }
            if (parentFrame.getPolygonProject().getPolygonStation(i).getLine().equals("Not")) {
                array[2] = "";
            } else {
                array[2] = parentFrame.getPolygonProject().getPolygonStation(i).getLine();
            }
            if (parentFrame.getPolygonProject().getPolygonStation(i).getDZ().equals("Not")) {
                array[3] = "";
            } else {
                array[3] = parentFrame.getPolygonProject().getPolygonStation(i).getDZ();
            }
            if (parentFrame.getPolygonProject().getPolygonStation(i).getStatus()) {
                if (parentFrame.getPolygonProject().getPolygonStation(i).getX().equals("Not")) {
                    array[4] = "";
                } else {
                    array[4] = parentFrame.getPolygonProject().getPolygonStation(i).getX();
                }
                if (parentFrame.getPolygonProject().getPolygonStation(i).getY().equals("Not")) {
                    array[5] = "";
                } else {
                    array[5] = parentFrame.getPolygonProject().getPolygonStation(i).getY();
                }
                if (parentFrame.getPolygonProject().getPolygonStation(i).getZ().equals("Not")) {
                    array[6] = "";
                } else {
                    array[6] = parentFrame.getPolygonProject().getPolygonStation(i).getZ();
                }
            } else {
                array[4] = "";
                array[5] = "";
                array[6] = "";
            }
            array[7] = parentFrame.getPolygonProject().getPolygonStation(i).getStatus();

            tmPolygonStations.addRow(array);

        }
        tblStations.getTableHeader().setReorderingAllowed(false);
        tblStations.setColumnSelectionAllowed(true);
        ListSelectionModel columnSelectionModel = tblStations.getColumnModel().getSelectionModel();
        columnSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblStations.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblStations.getSelectionModel().addListSelectionListener(e -> {
            selRow = tblStations.getSelectedRow();
        });
        tblStations.getColumnModel().getSelectionModel().addListSelectionListener(e -> {
            selColumn = tblStations.getSelectedColumn();
        });

        JScrollPane scpTheoStations = new JScrollPane(tblStations);
        add(scpTheoStations, BorderLayout.CENTER);
    }

    /**
     * set focus with current selRow and selColumn
     */
    public void setFocusTable() {
        selRow = 0;
        selColumn = 0;
        tblStations.getSelectionModel().setSelectionInterval(selRow, selRow);
        tblStations.getColumnModel().getSelectionModel().setSelectionInterval(selColumn, selColumn);
        tblStations.requestFocusInWindow();

    }

    /**
     * sets bindings value
     */
    public void setBindings() {
        switch (parentFrame.getPolygonProject().getBindType()) {
            case ZZ -> {
                lblAngleResidue.setText("-.-");
                lblHeightResidue.setText("-.-");
                lblFXResidue.setText("-.-");
                lblFYResidue.setText("-.-");
                lblFAbsoluteResidue.setText("-.-");
                lblFRelativeResidue.setText("-.-");
                lblPerValue.setText("-.-");
            }
            case TT -> {
                if (parentFrame.getOptions().getValueFHor() *
                        Math.sqrt(parentFrame.getPolygonProject().getSizePolygonStations()) >
                        Math.abs(parentFrame.getPolygonProject().getfHor())) {
                    lblAngleResidue.setForeground(Color.GREEN);
                } else {
                    lblAngleResidue.setForeground(Color.RED);
                }
                lblAngleResidue.setText(new DataHandler(parentFrame.getPolygonProject().getfHor()).format(2).getStr());
                if (parentFrame.getOptions().getValueFH() *
                        Math.sqrt(parentFrame.getPolygonProject().getPerimeter() / 1000) >
                        Math.abs(parentFrame.getPolygonProject().getfZ() * 1000)) {
                    lblHeightResidue.setForeground(Color.GREEN);
                } else {
                    lblHeightResidue.setForeground(Color.RED);
                }
                lblHeightResidue.setText(new DataHandler(parentFrame.getPolygonProject().getfZ()).format(3).getStr());
                if (parentFrame.getOptions().getValueFAbs() > parentFrame.getPolygonProject().getfAbs()) {
                    lblFXResidue.setForeground(Color.GREEN);
                    lblFYResidue.setForeground(Color.GREEN);
                    lblFAbsoluteResidue.setForeground(Color.GREEN);
                } else {
                    lblFXResidue.setForeground(Color.RED);
                    lblFYResidue.setForeground(Color.RED);
                    lblFAbsoluteResidue.setForeground(Color.RED);
                }
                lblFXResidue.setText(new DataHandler(parentFrame.getPolygonProject().getfX()).format(3).getStr());
                lblFYResidue.setText(new DataHandler(parentFrame.getPolygonProject().getfY()).format(3).getStr());
                lblFAbsoluteResidue.setText(new DataHandler(parentFrame.getPolygonProject().getfAbs()).format(3).getStr());
                if (Double.parseDouble(parentFrame.getOptions().getValueFOtn()) <
                        Double.parseDouble(parentFrame.getPolygonProject().getfOtn())) {
                    lblFRelativeResidue.setForeground(Color.GREEN);
                } else {
                    lblFRelativeResidue.setForeground(Color.RED);
                }
                lblFRelativeResidue.setText("1 : " + parentFrame.getPolygonProject().getfOtn());
                lblPerValue.setText(new DataHandler(parentFrame.getPolygonProject().getPerimeter()).format(3).getStr());
            }
            case OO, OT, TO -> {
                lblAngleResidue.setText("-.-");
                if (parentFrame.getOptions().getValueFH() *
                        Math.sqrt(parentFrame.getPolygonProject().getPerimeter() / 1000) >
                        Math.abs(parentFrame.getPolygonProject().getfZ() * 1000)) {
                    lblHeightResidue.setForeground(Color.GREEN);
                } else {
                    lblHeightResidue.setForeground(Color.RED);
                }
                lblHeightResidue.setText(new DataHandler(parentFrame.getPolygonProject().getfZ()).format(3).getStr());
                if (parentFrame.getOptions().getValueFAbs() > parentFrame.getPolygonProject().getfAbs()) {
                    lblFXResidue.setForeground(Color.GREEN);
                    lblFYResidue.setForeground(Color.GREEN);
                    lblFAbsoluteResidue.setForeground(Color.GREEN);
                } else {
                    lblFXResidue.setForeground(Color.RED);
                    lblFYResidue.setForeground(Color.RED);
                    lblFAbsoluteResidue.setForeground(Color.RED);
                }
                lblFXResidue.setText(new DataHandler(parentFrame.getPolygonProject().getfX()).format(3).getStr());
                lblFYResidue.setText(new DataHandler(parentFrame.getPolygonProject().getfY()).format(3).getStr());
                lblFAbsoluteResidue.setText(new DataHandler(parentFrame.getPolygonProject().getfAbs()).format(3).getStr());
                if (Double.parseDouble(parentFrame.getOptions().getValueFOtn()) <
                        Double.parseDouble(parentFrame.getPolygonProject().getfOtn())) {
                    lblFRelativeResidue.setForeground(Color.GREEN);
                } else {
                    lblFRelativeResidue.setForeground(Color.RED);
                }
                lblFRelativeResidue.setText(parentFrame.getPolygonProject().getfOtn());
                lblPerValue.setText(new DataHandler(parentFrame.getPolygonProject().getPerimeter()).format(3).getStr());
            }
            case ZT, TZ -> {
                lblAngleResidue.setText("-.-");
                lblHeightResidue.setText("-.-");
                lblFXResidue.setText("-.-");
                lblFYResidue.setText("-.-");
                lblFAbsoluteResidue.setText("-.-");
                lblFRelativeResidue.setText("-.-");
                lblPerValue.setText(new DataHandler(parentFrame.getPolygonProject().getPerimeter()).format(3).getStr());
            }
        }
    }

    /**
     * This model of tblTheoStations
     */
    private class TmodelPolygonStations extends AbstractTableModel {
        private final int columnCount = 8;
        private final ArrayList <Object []> dataArrayList;

        public TmodelPolygonStations() {
            dataArrayList = new ArrayList<Object[]>();
            for (int i = 0; i < dataArrayList.size(); i++) {
                dataArrayList.add(new String[getColumnCount()]);
            }
        }
        /**
         * Returns the number of rows in the model. A
         * <code>JTable</code> uses this method to determine how many rows it
         * should display.  This method should be quick, as it
         * is called frequently during rendering.
         *
         * @return the number of rows in the model
         * @see #getColumnCount
         */
        @Override
        public int getRowCount() {
            return dataArrayList.size();
        }

        /**
         * Returns the number of columns in the model. A
         * <code>JTable</code> uses this method to determine how many columns it
         * should create and display by default.
         *
         * @return the number of columns in the model
         * @see #getRowCount
         */
        @Override
        public int getColumnCount() {
            return columnCount;
        }

        /**
         * Returns the value for the cell at <code>columnIndex</code> and
         * <code>rowIndex</code>.
         *
         * @param rowIndex    the row whose value is to be queried
         * @param columnIndex the column whose value is to be queried
         * @return the value Object at the specified cell
         */
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Object [] row = dataArrayList.get(rowIndex);
            return row [columnIndex];
        }

        @Override
        public String getColumnName(int column) {
            return switch (column) {
                case 0 -> parentFrame.getTitles().get("THEOtmColumnName0");
                case 1 -> parentFrame.getTitles().get("THEOtmColumnName1");
                case 2 -> parentFrame.getTitles().get("THEOtmColumnName2");
                case 3 -> parentFrame.getTitles().get("THEOtmColumnName3");
                case 4 -> "X";
                case 5 -> "Y";
                case 6 -> "Z";
                case 7 -> parentFrame.getTitles().get("THEOtmColumnName7");
                default -> "";
            };
        }


        public Class getColumnClass(int c) {
            return getValueAt(0,c).getClass();
        }

        public boolean isCellEditable(int rowIndex, int columnCount) {
            return true;
        }

        public void setValueAt(Object object, int rowIndex, int columnIndex) {
            Object [] row = dataArrayList.get(rowIndex);
            DataHandler dataHandler = new DataHandler();
            if (columnIndex != 7) dataHandler = new DataHandler((String) object).commaToPoint();
/*
            row [columnIndex] = object;
            dataArrayList.set(rowIndex, row);
*/
            switch (columnIndex) {
                case 0 -> {
                    if (dataHandler.getStr().equals("")) {
                        parentFrame.getPolygonProject().getPolygonStation(rowIndex).setName("noname");

                        row[columnIndex] = "noname";
                        dataArrayList.set(rowIndex, row);

                    } else {
                        if (dataHandler.isValidName()) {
                            parentFrame.getPolygonProject().getPolygonStation(rowIndex).setName((String) object);
                            row[columnIndex] = object;
                            dataArrayList.set(rowIndex, row);
                        }
                    }
                }

                case 1 -> {
                    if (dataHandler.getStr().equals("")) {
                        parentFrame.getPolygonProject().getPolygonStation(rowIndex).setHor("0.0000");
                        row[columnIndex] = "0.0000";
                        dataArrayList.set(rowIndex, row);

                    } else {
                        if (dataHandler.isPositiveNumber()) {
                            parentFrame.getPolygonProject().getPolygonStation(rowIndex).setHor(dataHandler.format(4).getStr());
                            row[columnIndex] = dataHandler.format(4).getStr();
                            dataArrayList.set(rowIndex, row);
                        }

                    }
                }

                case 2 -> {
                    if (dataHandler.getStr().equals("")) {
                        parentFrame.getPolygonProject().getPolygonStation(rowIndex).setLine("0.000");
                        row[columnIndex] = "0.000";
                        dataArrayList.set(rowIndex, row);
                    } else {
                        if (dataHandler.isPositiveNumber()) {
                            parentFrame.getPolygonProject().getPolygonStation(rowIndex).setLine(dataHandler.format(3).getStr());
                            row[columnIndex] = dataHandler.format(3).getStr();
                            dataArrayList.set(rowIndex, row);
                        }
                    }
                }

                case 3 -> {
                    if (dataHandler.getStr().equals("")) {
                        parentFrame.getPolygonProject().getPolygonStation(rowIndex).setdZ("0.000");
                        row[columnIndex] = "0.000";
                        dataArrayList.set(rowIndex, row);
                    } else {
                        if (dataHandler.isNumber()) {
                            parentFrame.getPolygonProject().getPolygonStation(rowIndex).setdZ(dataHandler.format(3).getStr());
                            row[columnIndex] = dataHandler.format(3).getStr();
                            dataArrayList.set(rowIndex, row);
                        }
                    }
                }

                case 4 -> {
                    if ((Boolean) row [7]) {
                        if (dataHandler.getStr().equals("")) {
                            parentFrame.getPolygonProject().getPolygonStation(rowIndex).setX("0.000");
                            row[columnIndex] = "0.000";
                            dataArrayList.set(rowIndex, row);

                        } else {
                            if (dataHandler.isNumber()) {
                                parentFrame.getPolygonProject().getPolygonStation(rowIndex).setX(dataHandler.format(3).getStr());
                                row[columnIndex] = dataHandler.format(3).getStr();
                                dataArrayList.set(rowIndex, row);
                            }
                        }
                    }
                }
                case 5 -> {
                    if ((Boolean) row [7]) {
                        if (dataHandler.getStr().equals("")) {
                            parentFrame.getPolygonProject().getPolygonStation(rowIndex).setY("0.000");
                            row[columnIndex] = "0.000";
                            dataArrayList.set(rowIndex, row);
                        } else {
                            if (dataHandler.isNumber()) {
                                parentFrame.getPolygonProject().getPolygonStation(rowIndex).setY(dataHandler.format(3).getStr());
                                row[columnIndex] = dataHandler.format(3).getStr();
                                dataArrayList.set(rowIndex, row);
                            }
                        }
                    }
                }
                case 6 -> {
                    if ((Boolean) row [7]) {
                        if (dataHandler.getStr().equals("")) {
                            parentFrame.getPolygonProject().getPolygonStation(rowIndex).setZ("0.000");
                            row[columnIndex] = "0.000";
                            dataArrayList.set(rowIndex, row);
                        } else {
                            if (dataHandler.isNumber()) {
                                parentFrame.getPolygonProject().getPolygonStation(rowIndex).setZ(dataHandler.format(3).getStr());
                                row[columnIndex] = dataHandler.format(3).getStr();
                                dataArrayList.set(rowIndex, row);
                            }
                        }
                    }
                }
                case 7 -> {
                    if (!(rowIndex > 1 & rowIndex < dataArrayList.size() - 2)) {
                        parentFrame.getPolygonProject().getPolygonStation(rowIndex).setStatus((Boolean) object);
                        row[columnIndex] = object;
                        dataArrayList.set(rowIndex, row);

                        if ((Boolean) object) {
                            if (parentFrame.getPolygonProject().getPolygonStation(rowIndex).getX().equals("Not")) {
                                row[4] = "0.000";
                                dataArrayList.set(rowIndex, row);
                            } else {
                                row[4] = parentFrame.getPolygonProject().getPolygonStation(rowIndex).getX();
                                dataArrayList.set(rowIndex, row);
                            }
                            if (parentFrame.getPolygonProject().getPolygonStation(rowIndex).getY().equals("Not")) {
                                row[5] = "0.000";
                                dataArrayList.set(rowIndex, row);
                            } else {
                                row[5] = parentFrame.getPolygonProject().getPolygonStation(rowIndex).getY();
                                dataArrayList.set(rowIndex, row);
                                                            }
                            if (parentFrame.getPolygonProject().getPolygonStation(rowIndex).getZ().equals("Not")) {
                                row[6] = "0.000";
                                dataArrayList.set(rowIndex, row);
                            } else {
                                row[6] = parentFrame.getPolygonProject().getPolygonStation(rowIndex).getZ();
                                dataArrayList.set(rowIndex, row);
                            }
                        } else {
                            row[4] = "";
                            row[5] = "";
                            row[6] = "";
                            dataArrayList.set(rowIndex, row);
                            parentFrame.getPolygonProject().getPolygonStation(rowIndex).setX("Not");
                            parentFrame.getPolygonProject().getPolygonStation(rowIndex).setY("Not");
                            parentFrame.getPolygonProject().getPolygonStation(rowIndex).setZ("Not");
                        }
                        fireTableCellUpdated(rowIndex, 4);
                        fireTableCellUpdated(rowIndex, 5);
                        fireTableCellUpdated(rowIndex, 6);

                    }
                }

            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }

        /**
         * add row to dataArrayList
         * @param row
         */
        public void addRow(Object [] row) {
            dataArrayList.add(row);
        }

        public void addRow(int index, Object [] row) {
            dataArrayList.add(index, row);
            fireTableRowsInserted(index, index);
        }

        /**
         * remove row from dataArrayList
         * @param index
         */
        public void removeRow(int index) {
            dataArrayList.remove(index);
            parentFrame.getPolygonProject().removeStation(index);
            fireTableRowsDeleted(index, index);
        }
    }
}
