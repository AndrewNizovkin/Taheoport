package taheoport.gui;

import taheoport.dispatcher.DependencyInjector;
import taheoport.dispatcher.PolygonEditorActionListener;
import taheoport.model.PolygonStation;
import taheoport.service.DataHandler;
import taheoport.service.PolygonService;
import taheoport.service.SettingsService;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

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
//    private final JFrame parentFrame;
    private int selColumn;
    private int selRow;
    private final JTable tblStations;
    private final TmodelPolygonStations tmPolygonStations;
    private final PolygonService polygonService;
    private final SettingsService settingsService;
    HashMap<String, String> titles;

    /**
     * Constructor
     * @param dependencyInjector DependencyInjector
     */
    public PolygonEditorStandart(DependencyInjector dependencyInjector) {
        super();
//        parentFrame = dependencyInjector.getMainFrame();
        polygonService = dependencyInjector.getPolygonService();
        settingsService = dependencyInjector.getSettingsService();
        titles = dependencyInjector.getShell().getTitles();
        tmPolygonStations = new TmodelPolygonStations(dependencyInjector);
        tblStations = new JTable(tmPolygonStations);
        ActionListener polygonActionListener = new PolygonEditorActionListener(dependencyInjector,this);
        setLayout(new BorderLayout());

//region btnDeleteRow

        JButton btnDeleteRow = new JButton(new ImageIcon("images/delete_row.png"));
        btnDeleteRow.setActionCommand("btnDeleteRow");
        btnDeleteRow.setEnabled(true);
        btnDeleteRow.setToolTipText(titles.get("TAHbtnDeleteRowTT"));
        btnDeleteRow.addActionListener(polygonActionListener);

        //endregion

//region btnInsertRowBefore

        JButton btnInsertRowBefore = new JButton(new ImageIcon("images/insert_row.png"));
        btnInsertRowBefore.setActionCommand("btnInsertRowBefore");
        btnInsertRowBefore.setEnabled(true);
        btnInsertRowBefore.setToolTipText(titles.get("TAHbtnInsertRowBeforeTT"));
        btnInsertRowBefore.addActionListener(polygonActionListener);
//endregion

// region btnInsertRowAfter

        JButton btnInsertRowAfter = new JButton(new ImageIcon("images/insert_row_after.png"));
        btnInsertRowAfter.setActionCommand("btnInsertRowAfter");
        btnInsertRowAfter.setEnabled(true);
        btnInsertRowAfter.setToolTipText(titles.get("TAHbtnInsertRowAfterTT"));
        btnInsertRowAfter.addActionListener(polygonActionListener);
//endregion

//region btnImportFromCatalog

        JButton btnImportFromCatalog = new JButton(new ImageIcon("images/database_export.png"));
        btnImportFromCatalog.setActionCommand("btnImportFromCatalog");
        btnImportFromCatalog.setEnabled(true);
        btnImportFromCatalog.setToolTipText(titles.get("TAHbtnImportFromCatalogTT"));
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
        pnlAdjustment.setBorder(BorderFactory.createTitledBorder(
                null,
                titles.get("THEOlblTitleBinding"),
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font(
                        Font.DIALOG,
                        Font.PLAIN,
                        12),
                Color.BLUE));
//endregion

//region lblHeight

        JLabel lblHeight = new JLabel(titles.get("THEOlblHeight"), JLabel.RIGHT);
        pnlAdjustment.add(lblHeight);
//endregion

//region lblHeightResidue

        lblHeightResidue = new JLabel("-.-");
        pnlAdjustment.add(lblHeightResidue);
//endregion

//region lblAngle
        JLabel lblAngle = new JLabel(titles.get("THEOlblAngle"), JLabel.RIGHT);
        pnlAdjustment.add(lblAngle);
//endregion

//region lblAngleResidue

        lblAngleResidue = new JLabel("-.-");
        pnlAdjustment.add(lblAngleResidue);
//endregion

//region lblFX

        JLabel lblFX = new JLabel(titles.get("THEOlblFX"), JLabel.RIGHT);
        pnlAdjustment.add(lblFX);
//endregion

//region lblFXResidue

        lblFXResidue = new JLabel("-.-");
        pnlAdjustment.add(lblFXResidue);
//endregion

//region lblFY

        JLabel lblFY = new JLabel(titles.get("THEOlblFY"), JLabel.RIGHT);
        pnlAdjustment.add(lblFY);
//endregion

//region lblFYResidue

        lblFYResidue = new JLabel("-.-");
        pnlAdjustment.add(lblFYResidue);
//endregion

//region lblFAbsolute

        JLabel lblFAbsolute = new JLabel(titles.get("THEOlblFAbsolute"), JLabel.RIGHT);
        pnlAdjustment.add(lblFAbsolute);
//endregion

//region lblFAbsoluteResidue

        lblFAbsoluteResidue = new JLabel("-.-");
        pnlAdjustment.add(lblFAbsoluteResidue);
//endregion

//region lblFRelative

        JLabel lblFRelative = new JLabel(titles.get("THEOlblRelative"), JLabel.RIGHT);
        pnlAdjustment.add(lblFRelative);
//endregion

//region lblFRelativeResidue

        lblFRelativeResidue = new JLabel("-.-");
        pnlAdjustment.add(lblFRelativeResidue);
//endregion

//region lblPer

        JLabel lblPer = new JLabel(titles.get("THEOlblPer"), JLabel.RIGHT);
        pnlAdjustment.add(lblPer);
//endregion

//region lblPerValue

        lblPerValue = new JLabel("-.-");
        pnlAdjustment.add(lblPerValue);
//endregion

//region pnlPaint

        JPanel pnlPaint = new JPanel(new BorderLayout());
        pnlPaint.setPreferredSize(new Dimension(200, 200));
        pnlPaint.setBorder(BorderFactory.createTitledBorder(null, titles.get("THEOpnlPaintTitle"),
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font(Font.DIALOG, Font.PLAIN, 12),
                Color.BLUE));

        if (polygonService.getPerimeter() == 0.0) {
            JPanel pnlBlank = new JPanel();
            pnlPaint.add(pnlBlank, BorderLayout.CENTER);

        } else {
            PolygonPaintPanel pnlTheoPaintPanel = new PolygonPaintPanel(polygonService.getAllPolygonStations(), -1);
            pnlPaint.add(pnlTheoPaintPanel, BorderLayout.CENTER);
        }
//endregion

//region pnlBottom

        JPanel pnlBottom = new JPanel(new GridBagLayout());
        pnlBottom.add(pnlAdjustment, new GridBagConstraints(
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
        pnlBottom.add(pnlPaint, new GridBagConstraints(
                1,
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
        switch (polygonService.getBindType()) {
            case ZZ -> {
                lblAngleResidue.setText("-.-");
                lblAngleResidue.setForeground(Color.BLACK);

                lblHeightResidue.setText("-.-");
                lblHeightResidue.setForeground(Color.BLACK);

                lblFXResidue.setText("-.-");
                lblFXResidue.setForeground(Color.BLACK);

                lblFYResidue.setText("-.-");
                lblFYResidue.setForeground(Color.BLACK);

                lblFAbsoluteResidue.setText("-.-");
                lblFAbsoluteResidue.setForeground(Color.BLACK);

                lblFRelativeResidue.setText("-.-");
                lblFRelativeResidue.setForeground(Color.BLACK);

                lblPerValue.setText("-.-");
                lblPerValue.setForeground(Color.BLACK);
            }
            case TT -> {
                if (settingsService.getValueFHor() *
                        Math.sqrt(polygonService.getSizePolygonStations()) >
                        Math.abs(polygonService.getfHor())) {
                    lblAngleResidue.setForeground(Color.GREEN);
                } else {
                    lblAngleResidue.setForeground(Color.RED);
                }
                lblAngleResidue.setText(new DataHandler(polygonService.getfHor()).format(2).getStr());
                if (settingsService.getValueFH() *
                        Math.sqrt(polygonService.getPerimeter() / 1000) >
                        Math.abs(polygonService.getfZ() * 1000)) {
                    lblHeightResidue.setForeground(Color.GREEN);
                } else {
                    lblHeightResidue.setForeground(Color.RED);
                }
                lblHeightResidue.setText(new DataHandler(polygonService.getfZ()).format(3).getStr());
                if (settingsService.getValueFAbs() > polygonService.getfAbs()) {
                    lblFXResidue.setForeground(Color.GREEN);
                    lblFYResidue.setForeground(Color.GREEN);
                    lblFAbsoluteResidue.setForeground(Color.GREEN);
                } else {
                    lblFXResidue.setForeground(Color.RED);
                    lblFYResidue.setForeground(Color.RED);
                    lblFAbsoluteResidue.setForeground(Color.RED);
                }
                lblFXResidue.setText(new DataHandler(polygonService.getfX()).format(3).getStr());
                lblFYResidue.setText(new DataHandler(polygonService.getfY()).format(3).getStr());
                lblFAbsoluteResidue.setText(new DataHandler(polygonService.getfAbs()).format(3).getStr());
                if (Double.parseDouble(settingsService.getValueFOtn()) <
                        Double.parseDouble(polygonService.getfOtn())) {
                    lblFRelativeResidue.setForeground(Color.GREEN);
                } else {
                    lblFRelativeResidue.setForeground(Color.RED);
                }
                lblFRelativeResidue.setText("1 : " + polygonService.getfOtn());
                lblPerValue.setText(new DataHandler(polygonService.getPerimeter()).format(3).getStr());
            }
            case OO, OT, TO -> {
                lblAngleResidue.setText("-.-");
                if (settingsService.getValueFH() *
                        Math.sqrt(polygonService.getPerimeter() / 1000) >
                        Math.abs(polygonService.getfZ() * 1000)) {
                    lblHeightResidue.setForeground(Color.GREEN);
                } else {
                    lblHeightResidue.setForeground(Color.RED);
                }
                lblHeightResidue.setText(new DataHandler(polygonService.getfZ()).format(3).getStr());
                if (settingsService.getValueFAbs() > polygonService.getfAbs()) {
                    lblFXResidue.setForeground(Color.GREEN);
                    lblFYResidue.setForeground(Color.GREEN);
                    lblFAbsoluteResidue.setForeground(Color.GREEN);
                } else {
                    lblFXResidue.setForeground(Color.RED);
                    lblFYResidue.setForeground(Color.RED);
                    lblFAbsoluteResidue.setForeground(Color.RED);
                }
                lblFXResidue.setText(new DataHandler(polygonService.getfX()).format(3).getStr());
                lblFYResidue.setText(new DataHandler(polygonService.getfY()).format(3).getStr());
                lblFAbsoluteResidue.setText(new DataHandler(polygonService.getfAbs()).format(3).getStr());
                if (Double.parseDouble(settingsService.getValueFOtn()) <
                        Double.parseDouble(polygonService.getfOtn())) {
                    lblFRelativeResidue.setForeground(Color.GREEN);
                } else {
                    lblFRelativeResidue.setForeground(Color.RED);
                }
                lblFRelativeResidue.setText(polygonService.getfOtn());
                lblPerValue.setText(new DataHandler(polygonService.getPerimeter()).format(3).getStr());
            }
            case ZT, TZ -> {
                lblAngleResidue.setText("-.-");
                lblHeightResidue.setText("-.-");
                lblFXResidue.setText("-.-");
                lblFYResidue.setText("-.-");
                lblFAbsoluteResidue.setText("-.-");
                lblFRelativeResidue.setText("-.-");
                lblPerValue.setText(new DataHandler(polygonService.getPerimeter()).format(3).getStr());
            }
        }
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
