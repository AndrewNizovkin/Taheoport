package taheoport.gui;
import taheoport.dispatcher.DependencyInjector;
import taheoport.service.CatalogService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.HashMap;

/**
 * This class display external coordinate catalog
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class ShowCatalog extends JDialog {
    private final CatalogService catalogService;
    private int selRow;
    private final JTable tblPoints;

    /**
     * Constructor
     * @param dependencyInjector DependencyInjector
     */
    public ShowCatalog(DependencyInjector dependencyInjector) {
        super( dependencyInjector.getMainFrame(), dependencyInjector.getShell().getTitles().get("SCdialogTitle"), true);
        catalogService = dependencyInjector.getCatalogService();
        catalogService.setChoice(-1);
        JFrame parentFrame = dependencyInjector.getMainFrame();
        HashMap<String, String> titles = dependencyInjector.getTitles();

        Toolkit kit = Toolkit.getDefaultToolkit();
        Image im = kit.getImage("images/teo.png");
        this.setIconImage(im);
        selRow = -1;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        int w = parentFrame.getWidth() / 2;
        int h = parentFrame.getHeight() / 2;
        setBounds(parentFrame.getX() + parentFrame.getWidth() / 2 - w / 2,parentFrame.getY() + parentFrame.getHeight() / 2 - h / 2, w, h);
        setLayout(new BorderLayout());



// tblPoints_______________________________________________________

        tblPoints = new JTable(new TmodelCatalog(catalogService.getAllCatalogPoints()));

        tblPoints.getTableHeader().setReorderingAllowed(false);
        tblPoints.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);



        tblPoints.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selRow = tblPoints.getSelectedRow();
            }
        });

        //
        JScrollPane spnlPoints = new JScrollPane(tblPoints);
        add(spnlPoints, BorderLayout.CENTER);

// pnlControl__________________________________________________

        JPanel pnlControl = new JPanel();
        pnlControl.setLayout(new FlowLayout());

// btnInsertCoordinates________________________________________

        JButton btnInsertCoordinates = new JButton(titles.get("SCbtnInsertCoordinates"));
        btnInsertCoordinates.setActionCommand("btnInsertCoordinates");
        btnInsertCoordinates.setToolTipText(titles.get("SCbtnInsertCoordinatesTT"));
        btnInsertCoordinates.addActionListener(e -> {
            catalogService.setChoice(selRow);
            this.dispose();
        });
        pnlControl.add(btnInsertCoordinates);
// btnCancel________________________________________

        JButton btnCancel = new JButton(titles.get("SCbtnCancel"));
        btnCancel.setToolTipText(titles.get("SCbtnCancel"));
        btnCancel.addActionListener(e -> {
            this.dispose();
        });
        pnlControl.add(btnCancel);
        add(pnlControl, BorderLayout.SOUTH);

        setResizable(false);
        setVisible(true);
        tblPoints.getSelectionModel().setSelectionInterval(0, 0);
        tblPoints.getColumnModel().getSelectionModel().setSelectionInterval(0, 0);
        tblPoints.requestFocusInWindow();
        revalidate();
    }
}
