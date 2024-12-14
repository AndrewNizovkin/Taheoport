package taheoport.gui;

import taheoport.dispatcher.PolygonEditorActionListener;
import taheoport.model.PolygonStation;
import taheoport.service.DataHandler;
import taheoport.service.PolygonService;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class encapsulated panel for displaying and editing of polygon
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class PolygonEditorStandart extends JPanel implements PolygonEditorRenderer {
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
    private final PolygonService polygonService;
    private final ActionListener polygonActionListener;

    /**
     * Constructor
     * @param frame parent MainWin
     */
    public PolygonEditorStandart(MainWin frame) {
        super();
        parentFrame = frame;
        polygonService = parentFrame.getPolygonService();
        tmPolygonStations = new TmodelPolygonStations(parentFrame);
        tblStations = new JTable(tmPolygonStations);
        polygonActionListener = new PolygonEditorActionListener(this);
        setLayout(new BorderLayout());

//region btnDeleteRow

        JButton btnDeleteRow = new JButton(new ImageIcon("images/delete_row.png"));
        btnDeleteRow.setActionCommand("btnDeleteRow");
        btnDeleteRow.setEnabled(true);
        btnDeleteRow.setToolTipText(parentFrame.getTitles().get("TAHbtnDeleteRowTT"));
        btnDeleteRow.addActionListener(polygonActionListener);

        //endregion

//region btnInsertRowBefore

        JButton btnInsertRowBefore = new JButton(new ImageIcon("images/insert_row.png"));
        btnInsertRowBefore.setActionCommand("btnInsertRowBefore");
        btnInsertRowBefore.setEnabled(true);
        btnInsertRowBefore.setToolTipText(parentFrame.getTitles().get("TAHbtnInsertRowBeforeTT"));
        btnInsertRowBefore.addActionListener(polygonActionListener);
//endregion

// region btnInsertRowAfter

        JButton btnInsertRowAfter = new JButton(new ImageIcon("images/insert_row_after.png"));
        btnInsertRowAfter.setActionCommand("btnInsertRowAfter");
        btnInsertRowAfter.setEnabled(true);
        btnInsertRowAfter.setToolTipText(parentFrame.getTitles().get("TAHbtnInsertRowAfterTT"));
        btnInsertRowAfter.addActionListener(polygonActionListener);
//endregion

//region btnImportFromCatalog

        JButton btnImportFromCatalog = new JButton(new ImageIcon("images/database_export.png"));
        btnImportFromCatalog.setActionCommand("btnImportFromCatalog");
        btnImportFromCatalog.setEnabled(true);
        btnImportFromCatalog.setToolTipText(parentFrame.getTitles().get("TAHbtnImportFromCatalogTT"));
        btnImportFromCatalog.addActionListener(polygonActionListener);
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
        PolygonStation polygonStation;
        Object[] array;
        for (int i = 0; i < polygonService.getSizePolygonStations(); i++) {
            polygonStation = polygonService.findById(i);
            array = new Object[8];
            if (polygonStation.getName().equals("Not")) {
                array[0] = "";
            } else {
                array[0] = polygonStation.getName();
            }
            if (polygonStation.getHor().equals("Not")) {
                array[1] = "";
            } else {
                array[1] = polygonStation.getHor();
            }
            if (polygonStation.getLine().equals("Not")) {
                array[2] = "";
            } else {
                array[2] = polygonStation.getLine();
            }
            if (polygonStation.getDZ().equals("Not")) {
                array[3] = "";
            } else {
                array[3] = polygonStation.getDZ();
            }
            if (polygonStation.getStatus()) {
                if (polygonStation.getX().equals("Not")) {
                    array[4] = "";
                } else {
                    array[4] = polygonStation.getX();
                }
                if (polygonStation.getY().equals("Not")) {
                    array[5] = "";
                } else {
                    array[5] = polygonStation.getY();
                }
                if (polygonStation.getZ().equals("Not")) {
                    array[6] = "";
                } else {
                    array[6] = polygonStation.getZ();
                }
            } else {
                array[4] = "";
                array[5] = "";
                array[6] = "";
            }
            array[7] = polygonStation.getStatus();

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
     * set focus on tblStations
     */
    public void setFocusTable(int row, int column) {
        selRow = row;
        selColumn = column;
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
     * Gets parentFrame
     * @return MainWin
     */
    @Override
    public MainWin getParentFrame() {
        return parentFrame;
    }

    /**
     * Gets polygon stations table
     * @return JTable
     */
    @Override
    public TmodelPolygonStations getModel() {
        return tmPolygonStations;
    }

    /**
     * Gets index of selected row in polygon station table
     * @return int index
     */
    @Override
    public int getSelRow() {
        return selRow;
    }

    /**
     * Sets new value to selRow
     * @param selRow int index
     */
    @Override
    public void setSelRow(int selRow) {
        this.selRow = selRow;
    }

    /**
     * Gets index of selected column in polygon station table
     *
     * @return int
     */
    @Override
    public int getSelColumn() {
        return selColumn;
    }
}
