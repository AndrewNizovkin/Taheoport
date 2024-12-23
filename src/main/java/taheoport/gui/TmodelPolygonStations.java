package taheoport.gui;

import taheoport.dispatcher.DependencyInjector;
import taheoport.model.PolygonStation;
import taheoport.service.DataHandler;
import taheoport.service.PolygonService;
import taheoport.service.Shell;
import taheoport.service.SurveyService;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TmodelPolygonStations extends AbstractTableModel {
    private final ArrayList<Object []> dataArrayList;
    private final PolygonService polygonService;
    private final Shell shell;

    /**
     * Constructor
     * @param dependencyInjector DependencyInjector
     */
    public TmodelPolygonStations(DependencyInjector dependencyInjector) {
        super();
        polygonService = dependencyInjector.getPolygonService();
        shell = dependencyInjector.getShell();
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
            case 0 -> shell.getTitles().get("THEOtmColumnName0");
            case 1 -> shell.getTitles().get("THEOtmColumnName1");
            case 2 -> shell.getTitles().get("THEOtmColumnName2");
            case 3 -> shell.getTitles().get("THEOtmColumnName3");
            case 4 -> "X";
            case 5 -> "Y";
            case 6 -> "Z";
            case 7 -> shell.getTitles().get("THEOtmColumnName7");
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
        PolygonStation polygonStation = polygonService.findById(rowIndex);
        if (columnIndex != 7) dataHandler = new DataHandler((String) object).commaToPoint();
        switch (columnIndex) {
            case 0 -> {
                if (dataHandler.getStr().equals("")) {
                    polygonStation.setName("noname");

                    row[columnIndex] = "noname";
                    dataArrayList.set(rowIndex, row);

                } else {
                    if (dataHandler.isValidName()) {
                        polygonStation.setName((String) object);
                        row[columnIndex] = object;
                        dataArrayList.set(rowIndex, row);
                    }
                }
            }

            case 1 -> {
                if (dataHandler.getStr().equals("")) {
                    polygonStation.setHor("0.0000");
                    row[columnIndex] = "0.0000";
                    dataArrayList.set(rowIndex, row);

                } else {
                    if (dataHandler.isPositiveNumber()) {
                        polygonStation.setHor(dataHandler.format(4).getStr());
                        row[columnIndex] = dataHandler.format(4).getStr();
                        dataArrayList.set(rowIndex, row);
                    }

                }
            }

            case 2 -> {
                if (dataHandler.getStr().equals("")) {
                    polygonStation.setLine("0.000");
                    row[columnIndex] = "0.000";
                    dataArrayList.set(rowIndex, row);
                } else {
                    if (dataHandler.isPositiveNumber()) {
                        polygonStation.setLine(dataHandler.format(3).getStr());
                        row[columnIndex] = dataHandler.format(3).getStr();
                        dataArrayList.set(rowIndex, row);
                    }
                }
            }

            case 3 -> {
                if (dataHandler.getStr().equals("")) {
                    polygonStation.setdZ("0.000");
                    row[columnIndex] = "0.000";
                    dataArrayList.set(rowIndex, row);
                } else {
                    if (dataHandler.isNumber()) {
                        polygonStation.setdZ(dataHandler.format(3).getStr());
                        row[columnIndex] = dataHandler.format(3).getStr();
                        dataArrayList.set(rowIndex, row);
                    }
                }
            }

            case 4 -> {
                if ((Boolean) row [7]) {
                    if (dataHandler.getStr().equals("")) {
                        polygonStation.setX("0.000");
                        row[columnIndex] = "0.000";
                        dataArrayList.set(rowIndex, row);

                    } else {
                        if (dataHandler.isNumber()) {
                            polygonStation.setX(dataHandler.format(3).getStr());
                            row[columnIndex] = dataHandler.format(3).getStr();
                            dataArrayList.set(rowIndex, row);
                        }
                    }
                }
            }
            case 5 -> {
                if ((Boolean) row [7]) {
                    if (dataHandler.getStr().equals("")) {
                        polygonStation.setY("0.000");
                        row[columnIndex] = "0.000";
                        dataArrayList.set(rowIndex, row);
                    } else {
                        if (dataHandler.isNumber()) {
                            polygonStation.setY(dataHandler.format(3).getStr());
                            row[columnIndex] = dataHandler.format(3).getStr();
                            dataArrayList.set(rowIndex, row);
                        }
                    }
                }
            }
            case 6 -> {
                if ((Boolean) row [7]) {
                    if (dataHandler.getStr().equals("")) {
                        polygonStation.setZ("0.000");
                        row[columnIndex] = "0.000";
                        dataArrayList.set(rowIndex, row);
                    } else {
                        if (dataHandler.isNumber()) {
                            polygonStation.setZ(dataHandler.format(3).getStr());
                            row[columnIndex] = dataHandler.format(3).getStr();
                            dataArrayList.set(rowIndex, row);
                        }
                    }
                }
            }
            case 7 -> {
                if (!(rowIndex > 1 & rowIndex < dataArrayList.size() - 2)) {
                    polygonStation.setStatus((Boolean) object);
                    row[columnIndex] = object;
                    dataArrayList.set(rowIndex, row);

                    if ((Boolean) object) {
                        if (polygonStation.getX().equals("Not")) {
                            row[4] = "0.000";
                            polygonStation.setX("0.000");
                            dataArrayList.set(rowIndex, row);
                        } else {
                            row[4] = polygonStation.getX();
                            dataArrayList.set(rowIndex, row);
                        }
                        if (polygonStation.getY().equals("Not")) {
                            row[5] = "0.000";
                            polygonStation.setY("0.000");
                            dataArrayList.set(rowIndex, row);
                        } else {
                            row[5] = polygonStation.getY();
                            dataArrayList.set(rowIndex, row);
                        }
                        if (polygonStation.getZ().equals("Not")) {
                            row[6] = "0.000";
                            polygonStation.setZ("0.000");
                            dataArrayList.set(rowIndex, row);
                        } else {
                            row[6] = polygonStation.getZ();
                            dataArrayList.set(rowIndex, row);
                        }
                    } else {
                        row[4] = "";
                        row[5] = "";
                        row[6] = "";
                        dataArrayList.set(rowIndex, row);
                        polygonStation.setX("Not");
                        polygonStation.setY("Not");
                        polygonStation.setZ("Not");
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
        polygonService.removeStation(index);
        fireTableRowsDeleted(index, index);
    }
}