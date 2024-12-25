package taheoport.gui;
import taheoport.model.CatalogPoint;
import taheoport.repository.CatalogRepository;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * This class encapsulates table model of catalog
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class TmodelCatalog extends AbstractTableModel {
    private int columnCount = 4;
    private final ArrayList<String []> dataArrayList;

    public TmodelCatalog(CatalogRepository catalogRepository) {
        dataArrayList = new ArrayList<String[]>();
        if (!catalogRepository.isEmpty()) {
            for (CatalogPoint catalogPoint: catalogRepository) {
                String[] array = new String[4];
                array[0] = catalogPoint.getName();
                array[1] = catalogPoint.getX();
                array[2] = catalogPoint.getY();
                array[3] = catalogPoint.getZ();
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
