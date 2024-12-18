package taheoport.gui;

import taheoport.service.DataHandler;
import taheoport.service.SurveyService;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Class TmodelPickets encapsulates tableModel of tblPickets
 */

public class TmodelPickets extends AbstractTableModel {
    private int columnCount = 5;
    private final ArrayList<String []> dataArrayList;
    private final SurveyService surveyService;
    private final MainWin parentFrame;
    private int index;
    /**
     *Constructor
     */
    public TmodelPickets(MainWin frame, int index) {
        super();
        surveyService = frame.getSurveyService();
        parentFrame = frame;
        this.index = index;
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
                    surveyService.findStationById(index).getPicket(rowIndex).setpName((String) str);
                    row [columnIndex] = (String) str;
                    dataArrayList.set(rowIndex, row);
                }
            }
            case 1 -> {
                if (dataHandler.isPositiveNumber()) {
                    surveyService.findStationById(index).getPicket(rowIndex).setLine(dataHandler.format(3).getStr());
                    row[columnIndex] = dataHandler.format(3).getStr();
                    dataArrayList.set(rowIndex, row);
                }
            }
            case 2 -> {
                if (dataHandler.isPositiveNumber()) {
                    surveyService.findStationById(index).getPicket(rowIndex).setHor(dataHandler.format(4).getStr());
                    row[columnIndex] = dataHandler.format(4).getStr();
                    dataArrayList.set(rowIndex, row);
                }
            }
            case 3 -> {
                if (dataHandler.isNumber()) {
                    surveyService.findStationById(index).getPicket(rowIndex).setVert(dataHandler.format(4).getStr());
                    row[columnIndex] = dataHandler.format(4).getStr();
                    dataArrayList.set(rowIndex, row);
                }
            }
            case 4 -> {
                if (dataHandler.isNumber()) {
                    surveyService.findStationById(index).getPicket(rowIndex).setV(dataHandler.format(3).getStr());
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
