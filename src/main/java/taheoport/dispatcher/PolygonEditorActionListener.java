package taheoport.dispatcher;

import taheoport.gui.PolygonEditorRenderer;
import taheoport.gui.ShowCatalog;
import taheoport.model.CatalogPoint;
import taheoport.model.PolygonStation;
import taheoport.service.CatalogService;
import taheoport.service.PolygonService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PolygonEditorActionListener implements ActionListener {
    private final PolygonService polygonService;
    private final CatalogService catalogService;
    private final PolygonEditorRenderer renderer;

    /**
     * Constructor
     * @param renderer
     */
    public PolygonEditorActionListener(PolygonEditorRenderer renderer) {
        this.renderer = renderer;
        polygonService = renderer.getParentFrame().getPolygonService();
        catalogService = renderer.getParentFrame().getCatalogService();
    }

    /**
     * Invoked when an action occurs.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "btnDeleteRow" -> deleteRow();
            case "btnInsertRowBefore" -> insertStationBefore();
            case "btnInsertRowAfter" -> insertStationAfter();
            case "btnImportFromCatalog" -> setCoordinatesFromCatalog();
        }

    }

    /**
     * Removes row from polygon station table
     */
    private void deleteRow() {
        int selRow = renderer.getSelRow();
        if (renderer.getModel().getRowCount() > 1) {
            int k = selRow;
            renderer.getModel().removeRow(selRow);
            if (k == polygonService.getSizePolygonStations()) k--;
            renderer.setFocusTable(k, renderer.getSelColumn());
        }
    }

    /**
     * Inserts polygon station before of current position
     */
    private void insertStationBefore() {
        if (polygonService.isInsertBefore(renderer.getSelRow())) {
            polygonService.insertStation(renderer.getSelRow());
            renderer.getModel().addRow(renderer.getSelRow(), new Object[]{
                    "noname",
                    "0.0000",
                    "0.000",
                    "0.000",
                    "",
                    "",
                    "",
                    false
            });
            renderer.setFocusTable(renderer.getSelRow() - 1, renderer.getSelColumn());
        }
    }

    /**
     * Insert new polygon station after current position
     */
    private void insertStationAfter() {
        int selRow = renderer.getSelRow();
        if (polygonService.isInsertAfter(selRow)) {
            selRow++;
            polygonService.insertStation(selRow);
            renderer.getModel().addRow(selRow, new Object[]{
                    "noname",
                    "0.0000",
                    "0.000",
                    "0.000",
                    "",
                    "",
                    "",
                    false
            });
            renderer.setFocusTable(selRow, renderer.getSelColumn());
        }
    }

    /**
     * Sets coordinates from current catalog for selected base point
     */
    private void setCoordinatesFromCatalog() {
        if (polygonService.findById(renderer.getSelRow()).getStatus()) {
            new ShowCatalog(renderer.getParentFrame());
            if (catalogService.getChoice() >= 0) {
                PolygonStation polygonStation = polygonService.findById(renderer.getSelRow());
                CatalogPoint catalogPoint = catalogService.findById(catalogService.getChoice());
                polygonStation.setName(catalogPoint.getName());
                polygonStation.setX(catalogPoint.getX());
                polygonStation.setY(catalogPoint.getY());
                polygonStation.setZ(catalogPoint.getZ());
                renderer.getModel().setValueAt(polygonStation.getName(), renderer.getSelRow(), 0);
                renderer.getModel().setValueAt(polygonStation.getX(), renderer.getSelRow(), 4);
                renderer.getModel().setValueAt(polygonStation.getY(), renderer.getSelRow(), 5);
                renderer.getModel().setValueAt(polygonStation.getZ(), renderer.getSelRow(), 6);
            }
        }
    }
}
