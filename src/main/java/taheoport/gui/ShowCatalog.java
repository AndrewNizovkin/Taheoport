package taheoport.gui;
import taheoport.repository.CatalogRepository;
import taheoport.model.CatalogPoint;
import taheoport.service.CatalogService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

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
     * @param parentFrame parent MainWin
     */
    public ShowCatalog(MainWin parentFrame) {
        super( parentFrame, parentFrame.getTitles().get("SCdialogTitle"), true);
        catalogService = parentFrame.getCatalogService();
        catalogService.setChoice(-1);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Image im = kit.getImage("images/teo.png");
        this.setIconImage(im);
        selRow = -1;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        int w = parentFrame.getWidthMain() / 2;
        int h = parentFrame.getHeightMain() / 2;
        setBounds(parentFrame.getX() + parentFrame.getWidth() / 2 - w / 2,parentFrame.getY() + parentFrame.getHeight() / 2 - h / 2, w, h);
        setLayout(new BorderLayout());



// tblPoints_______________________________________________________

        tblPoints = new JTable(new TmodelCatalog(catalogService.getCatalogRepository()));

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

        JButton btnInsertCoordinates = new JButton(parentFrame.getTitles().get("SCbtnInsertCoordinates"));
        btnInsertCoordinates.setActionCommand("btnInsertCoordinates");
        btnInsertCoordinates.setToolTipText(parentFrame.getTitles().get("SCbtnInsertCoordinatesTT"));
        btnInsertCoordinates.addActionListener(e -> {
            catalogService.setChoice(selRow);
            this.dispose();
        });
        pnlControl.add(btnInsertCoordinates);
// btnCancel________________________________________

        JButton btnCancel = new JButton(parentFrame.getTitles().get("SCbtnCancel"));
        btnCancel.setToolTipText(parentFrame.getTitles().get("SCbtnCancel"));
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
