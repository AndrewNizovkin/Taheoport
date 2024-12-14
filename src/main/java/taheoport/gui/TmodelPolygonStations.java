package taheoport.gui;

import taheoport.service.DataHandler;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TmodelPolygonStations extends AbstractTableModel {
    private final ArrayList<Object []> dataArrayList;
    private final MainWin parentFrame;

    /**
     * Constructor
     */
    public TmodelPolygonStations(MainWin frame) {
        dataArrayList = new ArrayList<>();
        parentFrame = frame;
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