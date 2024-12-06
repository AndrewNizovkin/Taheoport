package taheoport.gui;

import taheoport.service.DataHandler;

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

//region btnDeleteRow

        JButton btnDeleteRow = new JButton(new ImageIcon("images/delete_row.png"));
        btnDeleteRow.setEnabled(true);
        btnDeleteRow.setToolTipText(parentFrame.getTitles().get("TAHbtnDeleteRowTT"));
        btnDeleteRow.addActionListener(e -> {
            if (tmPolygonStations.getRowCount() > 1) {
                int k = selRow;
                tmPolygonStations.removeRow(selRow);
                if (k == parentFrame.getPolygonRepository().getSizePolygonStations()) k--;
                selRow = k;
                tblStations.getSelectionModel().setSelectionInterval(selRow, selRow);
                tblStations.getColumnModel().getSelectionModel().setSelectionInterval(selColumn, selColumn);
                tblStations.requestFocusInWindow();
            }
        });

        //endregion

//region btnInsertRowBefore

        JButton btnInsertRowBefore = new JButton(new ImageIcon("images/insert_row.png"));
        btnInsertRowBefore.setEnabled(true);
        btnInsertRowBefore.setToolTipText(parentFrame.getTitles().get("TAHbtnInsertRowBeforeTT"));
        btnInsertRowBefore.addActionListener(e -> {
            if (isInsertBefore(selRow)) {
                parentFrame.getPolygonRepository().insertStation(selRow);
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
//endregion

// region btnInsertRowAfter

        JButton btnInsertRowAfter = new JButton(new ImageIcon("images/insert_row_after.png"));
        btnInsertRowAfter.setEnabled(true);
        btnInsertRowAfter.setToolTipText(parentFrame.getTitles().get("TAHbtnInsertRowAfterTT"));
        btnInsertRowAfter.addActionListener(e -> {
            if (isInsertAfter(selRow)) {
                selRow++;
                parentFrame.getPolygonRepository().insertStation(selRow);
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
//endregion

//region btnImportFromCatalog

        JButton btnImportFromCatalog = new JButton(new ImageIcon("images/database_export.png"));
        btnImportFromCatalog.setEnabled(true);
        btnImportFromCatalog.setToolTipText(parentFrame.getTitles().get("TAHbtnImportFromCatalogTT"));
        btnImportFromCatalog.addActionListener(e -> {
            if (parentFrame.getPolygonRepository().findById(selRow).getStatus()) {
                new ShowCatalog(parentFrame, selRow, "TheoStation");
                tmPolygonStations.setValueAt(parentFrame.getPolygonRepository().findById(selRow).getName(), selRow, 0);
                tmPolygonStations.setValueAt(parentFrame.getPolygonRepository().findById(selRow).getX(), selRow, 4);
                tmPolygonStations.setValueAt(parentFrame.getPolygonRepository().findById(selRow).getY(), selRow, 5);
                tmPolygonStations.setValueAt(parentFrame.getPolygonRepository().findById(selRow).getZ(), selRow, 6);
            }
        });
//endregion

//region tbTheoStations

        JToolBar tbTheoStations = new JToolBar();
        tbTheoStations.setBorder(BorderFactory.createEtchedBorder());
        tbTheoStations.setFloatable(false);
        tbTheoStations.add(btnDeleteRow);
        tbTheoStations.add(btnInsertRowBefore);
        tbTheoStations.add(btnInsertRowAfter);
        tbTheoStations.addSeparator();
        tbTheoStations.add(btnImportFromCatalog);

        add(tbTheoStations, BorderLayout.NORTH);
//endregion

//region pnlAdjustment

        JPanel pnlAdjustment = new JPanel(new GridLayout(7 , 2));
        pnlAdjustment.setBorder(BorderFactory.createTitledBorder(null, parentFrame.getTitles().get("THEOlblTitleBinding"), TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.DIALOG, Font.PLAIN, 12), Color.BLUE));
//endregion

//region lblHeight

        JLabel lblHeight = new JLabel(parentFrame.getTitles().get("THEOlblHeight"), JLabel.RIGHT);
        pnlAdjustment.add(lblHeight);
//endregion

//region lblHeightResidue

        lblHeightResidue = new JLabel("-.-");
        pnlAdjustment.add(lblHeightResidue);
//endregion

//region lblAngle

        JLabel lblAngle = new JLabel(parentFrame.getTitles().get("THEOlblAngle"), JLabel.RIGHT);
        pnlAdjustment.add(lblAngle);
//endregion

//region lblAngleResidue

        lblAngleResidue = new JLabel("-.-");
        pnlAdjustment.add(lblAngleResidue);
//endregion

//region lblFX

        JLabel lblFX = new JLabel(parentFrame.getTitles().get("THEOlblFX"), JLabel.RIGHT);
        pnlAdjustment.add(lblFX);
//endregion

//region lblFXResidue

        lblFXResidue = new JLabel("-.-");
        pnlAdjustment.add(lblFXResidue);
//endregion

//region lblFY

        JLabel lblFY = new JLabel(parentFrame.getTitles().get("THEOlblFY"), JLabel.RIGHT);
        pnlAdjustment.add(lblFY);
//endregion

//region lblFYResidue

        lblFYResidue = new JLabel("-.-");
        pnlAdjustment.add(lblFYResidue);
//endregion

//region lblFAbsolute

        JLabel lblFAbsolute = new JLabel(parentFrame.getTitles().get("THEOlblFAbsolute"), JLabel.RIGHT);
        pnlAdjustment.add(lblFAbsolute);
//endregion

//region lblFAbsoluteResidue

        lblFAbsoluteResidue = new JLabel("-.-");
        pnlAdjustment.add(lblFAbsoluteResidue);
//endregion

//region lblFRelative

        JLabel lblFRelative = new JLabel(parentFrame.getTitles().get("THEOlblRelative"), JLabel.RIGHT);
        pnlAdjustment.add(lblFRelative);
//endregion

//region lblFRelativeResidue

        lblFRelativeResidue = new JLabel("-.-");
        pnlAdjustment.add(lblFRelativeResidue);
//endregion

//region lblPer

        JLabel lblPer = new JLabel(parentFrame.getTitles().get("THEOlblPer"), JLabel.RIGHT);
        pnlAdjustment.add(lblPer);
//endregion

//region lblPerValue

        lblPerValue = new JLabel("-.-");
        pnlAdjustment.add(lblPerValue);
//endregion

//region pnlPaint

        JPanel pnlPaint = new JPanel(new BorderLayout());
        pnlPaint.setPreferredSize(new Dimension(200, 200));
        pnlPaint.setBorder(BorderFactory.createTitledBorder(null, parentFrame.getTitles().get("THEOpnlPaintTitle"),
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font(Font.DIALOG, Font.PLAIN, 12),
                Color.BLUE));

        if (parentFrame.getPolygonRepository().getPerimeter() == 0.0) {
            JPanel pnlBlank = new JPanel();
            pnlPaint.add(pnlBlank, BorderLayout.CENTER);

        } else {
            PolygonPaintPanel pnlTheoPaintPanel = new PolygonPaintPanel(parentFrame.getPolygonRepository(), -1);
            pnlPaint.add(pnlTheoPaintPanel, BorderLayout.CENTER);
        }
//endregion

//region pnlBottom

        JPanel pnlBottom = new JPanel(new GridBagLayout());
        pnlBottom.add(pnlAdjustment, new GridBagConstraints(0, 0, 1, 1, 1, 0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        pnlBottom.add(pnlPaint, new GridBagConstraints(1, 0, 1, 1, 1, 0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        add(pnlBottom, BorderLayout.SOUTH);
//endregion

//region tblTheoStations

        tmPolygonStations = new TmodelPolygonStations();
        tblStations = new JTable(tmPolygonStations);
        Object[] array;
        for (int i = 0; i < parentFrame.getPolygonRepository().getSizePolygonStations(); i++) {
            array = new Object[8];
            if (parentFrame.getPolygonRepository().findById(i).getName().equals("Not")) {
                array[0] = "";
            } else {
                array[0] = parentFrame.getPolygonRepository().findById(i).getName();
            }
            if (parentFrame.getPolygonRepository().findById(i).getHor().equals("Not")) {
                array[1] = "";
            } else {
                array[1] = parentFrame.getPolygonRepository().findById(i).getHor();
            }
            if (parentFrame.getPolygonRepository().findById(i).getLine().equals("Not")) {
                array[2] = "";
            } else {
                array[2] = parentFrame.getPolygonRepository().findById(i).getLine();
            }
            if (parentFrame.getPolygonRepository().findById(i).getDZ().equals("Not")) {
                array[3] = "";
            } else {
                array[3] = parentFrame.getPolygonRepository().findById(i).getDZ();
            }
            if (parentFrame.getPolygonRepository().findById(i).getStatus()) {
                if (parentFrame.getPolygonRepository().findById(i).getX().equals("Not")) {
                    array[4] = "";
                } else {
                    array[4] = parentFrame.getPolygonRepository().findById(i).getX();
                }
                if (parentFrame.getPolygonRepository().findById(i).getY().equals("Not")) {
                    array[5] = "";
                } else {
                    array[5] = parentFrame.getPolygonRepository().findById(i).getY();
                }
                if (parentFrame.getPolygonRepository().findById(i).getZ().equals("Not")) {
                    array[6] = "";
                } else {
                    array[6] = parentFrame.getPolygonRepository().findById(i).getZ();
                }
            } else {
                array[4] = "";
                array[5] = "";
                array[6] = "";
            }
            array[7] = parentFrame.getPolygonRepository().findById(i).getStatus();

            tmPolygonStations.addRow(array);

        }
        tblStations.getTableHeader().setReorderingAllowed(false);
        tblStations.setColumnSelectionAllowed(true);
        ListSelectionModel columnSelectionModel = tblStations.getColumnModel().getSelectionModel();
        columnSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblStations.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblStations.getSelectionModel().addListSelectionListener(e -> selRow = tblStations.getSelectedRow());
        tblStations.getColumnModel().getSelectionModel().addListSelectionListener(e -> selColumn = tblStations.getSelectedColumn());

        JScrollPane scpTheoStations = new JScrollPane(tblStations);
        add(scpTheoStations, BorderLayout.CENTER);
//endregion
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
        switch (parentFrame.getPolygonRepository().getBindType()) {
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
                if (parentFrame.getSettings().getValueFHor() *
                        Math.sqrt(parentFrame.getPolygonRepository().getSizePolygonStations()) >
                        Math.abs(parentFrame.getPolygonRepository().getfHor())) {
                    lblAngleResidue.setForeground(Color.GREEN);
                } else {
                    lblAngleResidue.setForeground(Color.RED);
                }
                lblAngleResidue.setText(new DataHandler(parentFrame.getPolygonRepository().getfHor()).format(2).getStr());
                if (parentFrame.getSettings().getValueFH() *
                        Math.sqrt(parentFrame.getPolygonRepository().getPerimeter() / 1000) >
                        Math.abs(parentFrame.getPolygonRepository().getfZ() * 1000)) {
                    lblHeightResidue.setForeground(Color.GREEN);
                } else {
                    lblHeightResidue.setForeground(Color.RED);
                }
                lblHeightResidue.setText(new DataHandler(parentFrame.getPolygonRepository().getfZ()).format(3).getStr());
                if (parentFrame.getSettings().getValueFAbs() > parentFrame.getPolygonRepository().getfAbs()) {
                    lblFXResidue.setForeground(Color.GREEN);
                    lblFYResidue.setForeground(Color.GREEN);
                    lblFAbsoluteResidue.setForeground(Color.GREEN);
                } else {
                    lblFXResidue.setForeground(Color.RED);
                    lblFYResidue.setForeground(Color.RED);
                    lblFAbsoluteResidue.setForeground(Color.RED);
                }
                lblFXResidue.setText(new DataHandler(parentFrame.getPolygonRepository().getfX()).format(3).getStr());
                lblFYResidue.setText(new DataHandler(parentFrame.getPolygonRepository().getfY()).format(3).getStr());
                lblFAbsoluteResidue.setText(new DataHandler(parentFrame.getPolygonRepository().getfAbs()).format(3).getStr());
                if (Double.parseDouble(parentFrame.getSettings().getValueFOtn()) <
                        Double.parseDouble(parentFrame.getPolygonRepository().getfOtn())) {
                    lblFRelativeResidue.setForeground(Color.GREEN);
                } else {
                    lblFRelativeResidue.setForeground(Color.RED);
                }
                lblFRelativeResidue.setText("1 : " + parentFrame.getPolygonRepository().getfOtn());
                lblPerValue.setText(new DataHandler(parentFrame.getPolygonRepository().getPerimeter()).format(3).getStr());
            }
            case OO, OT, TO -> {
                lblAngleResidue.setText("-.-");
                if (parentFrame.getSettings().getValueFH() *
                        Math.sqrt(parentFrame.getPolygonRepository().getPerimeter() / 1000) >
                        Math.abs(parentFrame.getPolygonRepository().getfZ() * 1000)) {
                    lblHeightResidue.setForeground(Color.GREEN);
                } else {
                    lblHeightResidue.setForeground(Color.RED);
                }
                lblHeightResidue.setText(new DataHandler(parentFrame.getPolygonRepository().getfZ()).format(3).getStr());
                if (parentFrame.getSettings().getValueFAbs() > parentFrame.getPolygonRepository().getfAbs()) {
                    lblFXResidue.setForeground(Color.GREEN);
                    lblFYResidue.setForeground(Color.GREEN);
                    lblFAbsoluteResidue.setForeground(Color.GREEN);
                } else {
                    lblFXResidue.setForeground(Color.RED);
                    lblFYResidue.setForeground(Color.RED);
                    lblFAbsoluteResidue.setForeground(Color.RED);
                }
                lblFXResidue.setText(new DataHandler(parentFrame.getPolygonRepository().getfX()).format(3).getStr());
                lblFYResidue.setText(new DataHandler(parentFrame.getPolygonRepository().getfY()).format(3).getStr());
                lblFAbsoluteResidue.setText(new DataHandler(parentFrame.getPolygonRepository().getfAbs()).format(3).getStr());
                if (Double.parseDouble(parentFrame.getSettings().getValueFOtn()) <
                        Double.parseDouble(parentFrame.getPolygonRepository().getfOtn())) {
                    lblFRelativeResidue.setForeground(Color.GREEN);
                } else {
                    lblFRelativeResidue.setForeground(Color.RED);
                }
                lblFRelativeResidue.setText(parentFrame.getPolygonRepository().getfOtn());
                lblPerValue.setText(new DataHandler(parentFrame.getPolygonRepository().getPerimeter()).format(3).getStr());
            }
            case ZT, TZ -> {
                lblAngleResidue.setText("-.-");
                lblHeightResidue.setText("-.-");
                lblFXResidue.setText("-.-");
                lblFYResidue.setText("-.-");
                lblFAbsoluteResidue.setText("-.-");
                lblFRelativeResidue.setText("-.-");
                lblPerValue.setText(new DataHandler(parentFrame.getPolygonRepository().getPerimeter()).format(3).getStr());
            }
        }
    }

    /**
     * Сhecks the possibility of inserting before idx position
     * @param idx int idx
     * @return boolean
     */
    private Boolean isInsertBefore(int idx) {
        if (!parentFrame.getPolygonRepository().findById(idx).getStatus()) return true;
        if (parentFrame.getPolygonRepository().findById(idx).getStatus() & idx > 0) {
            return !parentFrame.getPolygonRepository().findById(idx - 1).getStatus();
        }
        return false;
    }

    /**
     * Сhecks the possibility of inserting after idx position
     * @param idx int idx
     * @return boolean
     */
    private boolean isInsertAfter(int idx) {
        if (!parentFrame.getPolygonRepository().findById(idx).getStatus()) return true;
        if (parentFrame.getPolygonRepository().findById(idx).getStatus() & idx < parentFrame.getPolygonRepository().getSizePolygonStations() - 1) {
            return !parentFrame.getPolygonRepository().findById(idx + 1).getStatus();
        }
        return false;
    }


    /**
     * This model of tblPolygonStations
     */
    private class TmodelPolygonStations extends AbstractTableModel {
        private final ArrayList <Object []> dataArrayList;

        public TmodelPolygonStations() {
            dataArrayList = new ArrayList<>();
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
            return 8;
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
            switch (columnIndex) {
                case 0 -> {
                    if (dataHandler.getStr().equals("")) {
                        parentFrame.getPolygonRepository().findById(rowIndex).setName("noname");

                        row[columnIndex] = "noname";
                        dataArrayList.set(rowIndex, row);

                    } else {
                        if (dataHandler.isValidName()) {
                            parentFrame.getPolygonRepository().findById(rowIndex).setName((String) object);
                            row[columnIndex] = object;
                            dataArrayList.set(rowIndex, row);
                        }
                    }
                }

                case 1 -> {
                    if (dataHandler.getStr().equals("")) {
                        parentFrame.getPolygonRepository().findById(rowIndex).setHor("0.0000");
                        row[columnIndex] = "0.0000";
                        dataArrayList.set(rowIndex, row);

                    } else {
                        if (dataHandler.isPositiveNumber()) {
                            parentFrame.getPolygonRepository().findById(rowIndex).setHor(dataHandler.format(4).getStr());
                            row[columnIndex] = dataHandler.format(4).getStr();
                            dataArrayList.set(rowIndex, row);
                        }

                    }
                }

                case 2 -> {
                    if (dataHandler.getStr().equals("")) {
                        parentFrame.getPolygonRepository().findById(rowIndex).setLine("0.000");
                        row[columnIndex] = "0.000";
                        dataArrayList.set(rowIndex, row);
                    } else {
                        if (dataHandler.isPositiveNumber()) {
                            parentFrame.getPolygonRepository().findById(rowIndex).setLine(dataHandler.format(3).getStr());
                            row[columnIndex] = dataHandler.format(3).getStr();
                            dataArrayList.set(rowIndex, row);
                        }
                    }
                }

                case 3 -> {
                    if (dataHandler.getStr().equals("")) {
                        parentFrame.getPolygonRepository().findById(rowIndex).setdZ("0.000");
                        row[columnIndex] = "0.000";
                        dataArrayList.set(rowIndex, row);
                    } else {
                        if (dataHandler.isNumber()) {
                            parentFrame.getPolygonRepository().findById(rowIndex).setdZ(dataHandler.format(3).getStr());
                            row[columnIndex] = dataHandler.format(3).getStr();
                            dataArrayList.set(rowIndex, row);
                        }
                    }
                }

                case 4 -> {
                    if ((Boolean) row [7]) {
                        if (dataHandler.getStr().equals("")) {
                            parentFrame.getPolygonRepository().findById(rowIndex).setX("0.000");
                            row[columnIndex] = "0.000";
                            dataArrayList.set(rowIndex, row);

                        } else {
                            if (dataHandler.isNumber()) {
                                parentFrame.getPolygonRepository().findById(rowIndex).setX(dataHandler.format(3).getStr());
                                row[columnIndex] = dataHandler.format(3).getStr();
                                dataArrayList.set(rowIndex, row);
                            }
                        }
                    }
                }
                case 5 -> {
                    if ((Boolean) row [7]) {
                        if (dataHandler.getStr().equals("")) {
                            parentFrame.getPolygonRepository().findById(rowIndex).setY("0.000");
                            row[columnIndex] = "0.000";
                            dataArrayList.set(rowIndex, row);
                        } else {
                            if (dataHandler.isNumber()) {
                                parentFrame.getPolygonRepository().findById(rowIndex).setY(dataHandler.format(3).getStr());
                                row[columnIndex] = dataHandler.format(3).getStr();
                                dataArrayList.set(rowIndex, row);
                            }
                        }
                    }
                }
                case 6 -> {
                    if ((Boolean) row [7]) {
                        if (dataHandler.getStr().equals("")) {
                            parentFrame.getPolygonRepository().findById(rowIndex).setZ("0.000");
                            row[columnIndex] = "0.000";
                            dataArrayList.set(rowIndex, row);
                        } else {
                            if (dataHandler.isNumber()) {
                                parentFrame.getPolygonRepository().findById(rowIndex).setZ(dataHandler.format(3).getStr());
                                row[columnIndex] = dataHandler.format(3).getStr();
                                dataArrayList.set(rowIndex, row);
                            }
                        }
                    }
                }
                case 7 -> {
                    if (!(rowIndex > 1 & rowIndex < dataArrayList.size() - 2)) {
                        parentFrame.getPolygonRepository().findById(rowIndex).setStatus((Boolean) object);
                        row[columnIndex] = object;
                        dataArrayList.set(rowIndex, row);

                        if ((Boolean) object) {
                            if (parentFrame.getPolygonRepository().findById(rowIndex).getX().equals("Not")) {
                                row[4] = "0.000";
                                dataArrayList.set(rowIndex, row);
                            } else {
                                row[4] = parentFrame.getPolygonRepository().findById(rowIndex).getX();
                                dataArrayList.set(rowIndex, row);
                            }
                            if (parentFrame.getPolygonRepository().findById(rowIndex).getY().equals("Not")) {
                                row[5] = "0.000";
                                dataArrayList.set(rowIndex, row);
                            } else {
                                row[5] = parentFrame.getPolygonRepository().findById(rowIndex).getY();
                                dataArrayList.set(rowIndex, row);
                                                            }
                            if (parentFrame.getPolygonRepository().findById(rowIndex).getZ().equals("Not")) {
                                row[6] = "0.000";
                                dataArrayList.set(rowIndex, row);
                            } else {
                                row[6] = parentFrame.getPolygonRepository().findById(rowIndex).getZ();
                                dataArrayList.set(rowIndex, row);
                            }
                        } else {
                            row[4] = "";
                            row[5] = "";
                            row[6] = "";
                            dataArrayList.set(rowIndex, row);
                            parentFrame.getPolygonRepository().findById(rowIndex).setX("Not");
                            parentFrame.getPolygonRepository().findById(rowIndex).setY("Not");
                            parentFrame.getPolygonRepository().findById(rowIndex).setZ("Not");
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
         * @param row array of Objects
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
         * @param index index removes row
         */
        public void removeRow(int index) {
            dataArrayList.remove(index);
            parentFrame.getPolygonRepository().removeStation(index);
            fireTableRowsDeleted(index, index);
        }
    }
}
