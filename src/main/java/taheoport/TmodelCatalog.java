package taheoport;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * This class encapsulates table model of catalog
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class TmodelCatalog extends AbstractTableModel {
    private int columnCount = 4;
    private ArrayList<String []> dataArrayList;

    public TmodelCatalog(Catalog catalog) {
        dataArrayList = new ArrayList<String[]>();
        if (!(catalog == null)) {
            for (int i = 0; i < catalog.getSizeCatalog(); i++) {
                String[] array = new String[4];
                array[0] = catalog.getCatalogPoint(i).getName();
                array[1] = catalog.getCatalogPoint(i).getX();
                array[2] = catalog.getCatalogPoint(i).getY();
                array[3] = catalog.getCatalogPoint(i).getZ();
                dataArrayList.add(array);
            }
            fireTableDataChanged();
        }
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        String [] row = dataArrayList.get(rowIndex);
        return row [columnIndex];
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "Name";
            case 1: return "X";
            case 2: return "Y";
            case 3: return "Z";
        }
        return "";
    }

    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

}
