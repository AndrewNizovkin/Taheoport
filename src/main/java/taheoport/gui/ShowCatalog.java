package taheoport.gui;
import taheoport.repository.CatalogRepository;
import taheoport.model.CatalogPoint;

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

    private final CatalogRepository catalogRepository;
    private final int index;
    private final MainWin parentFrame;
    private int selRow;
    private final String target;
    private final JTable tblPoints;

    /**
     * Constructor
     * @param frame parent MainWin
     * @param index station selected index in surveyProject
     * @param target parameter to changed
     */
    public ShowCatalog(MainWin frame, int index, String target) {
        super( frame,frame.getTitles().get("SCdialogTitle"), true);
        parentFrame = frame;

        Toolkit kit = Toolkit.getDefaultToolkit();
        Image im = kit.getImage("images/teo.png");
        this.setIconImage(im);

        this.target = target;
        this.index = index;
        this.catalogRepository = frame.getCatalog();
        selRow = -1;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        int w = parentFrame.getWidthMain() / 2;
        int h = parentFrame.getHeightMain() / 2;
        setBounds(frame.getX() + frame.getWidth() / 2 - w / 2,frame.getY() + frame.getHeight() / 2 - h / 2, w, h);
        setLayout(new BorderLayout());


// tblPoints_______________________________________________________

        tblPoints = new JTable(new TmodelCatalog(catalogRepository));

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
        btnInsertCoordinates.setToolTipText(parentFrame.getTitles().get("SCbtnInsertCoordinatesTT"));
        btnInsertCoordinates.addActionListener(e -> {
            replaceCoordinates();
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

    private void replaceCoordinates() {
        if (selRow >= 0) {
//            Station st = this.sp.getStation(index);
            CatalogPoint cp = this.catalogRepository.findById(selRow);
            if (target.equals("StationName")) {
                parentFrame.getSurveyRepository().findById(index).setName(cp.getName());
                parentFrame.getSurveyRepository().findById(index).setX(cp.getX());
                parentFrame.getSurveyRepository().findById(index).setY(cp.getY());
                parentFrame.getSurveyRepository().findById(index).setZ(cp.getZ());
            }
            if (target.equals("OrName")) {
                parentFrame.getSurveyRepository().findById(index).setNameOr(cp.getName());
                parentFrame.getSurveyRepository().findById(index).setxOr(cp.getX());
                parentFrame.getSurveyRepository().findById(index).setyOr(cp.getY());
            }
            if (target.equals("TheoStation")) {
                parentFrame.getPolygonRepository().findById(index).setName(cp.getName());
                parentFrame.getPolygonRepository().findById(index).setX(cp.getX());
                parentFrame.getPolygonRepository().findById(index).setY(cp.getY());
                parentFrame.getPolygonRepository().findById(index).setZ(cp.getZ());
            }
            this.dispose();
        }

    }
}
