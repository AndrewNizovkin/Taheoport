package taheoport;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * Этот класс предоставляет возможность вставить параметры
 * станции из раннее установленного каталога
 */
public class ShowCatalog extends JDialog {
    private SurveyProject sp;
    private String name;
    private MainWin parentFrame;
    private int selRow;
    private int index;
    private int w;
    private int h;
    private JScrollPane spnlPoints; //
    private JButton btnInsertCoordinates;
    private JButton btnCancel;
    private JTable tblPoints;
    private JPanel pnlControl;
    private Catalog catalog;

    public ShowCatalog(MainWin frame, int index, String name) {
        super( frame,frame.getTitles().get("SCdialogTitle"), true);
        parentFrame = frame;

        Toolkit kit = Toolkit.getDefaultToolkit();
        Image im = kit.getImage("images/teo.png");
        this.setIconImage(im);

        this.sp = frame.getSurveyProject();
        this.name = name;
        this.index = index;
        this.catalog = frame.getCatalog();
        selRow = -1;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        w = parentFrame.getWidthMain() / 2;
        h = parentFrame.getHeightMain() / 2;
        setBounds(frame.getX() + frame.getWidth() / 2 - w / 2,frame.getY() + frame.getHeight() / 2 - h / 2, w, h);
        setLayout(new BorderLayout());


// tblPoints_______________________________________________________

        tblPoints = new JTable(new TmodelCatalog(catalog));

        tblPoints.getTableHeader().setReorderingAllowed(false);
        tblPoints.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);



        tblPoints.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selRow = tblPoints.getSelectedRow();
            }
        });

        spnlPoints = new JScrollPane(tblPoints);
        add(spnlPoints, BorderLayout.CENTER);

// pnlControl__________________________________________________

        pnlControl = new JPanel();
        pnlControl.setLayout(new FlowLayout());

// btnInsertCoordinates________________________________________

        btnInsertCoordinates = new JButton(parentFrame.getTitles().get("SCbtnInsertCoordinates"));
        btnInsertCoordinates.setToolTipText(parentFrame.getTitles().get("SCbtnInsertCoordinatesTT"));
        btnInsertCoordinates.addActionListener(e -> {
            setCoordinates();
        });
        pnlControl.add(btnInsertCoordinates);
// btnCancel________________________________________

        btnCancel = new JButton(parentFrame.getTitles().get("SCbtnCancel"));
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

    private void setCoordinates () {
        if (selRow >= 0) {
//            Station st = this.sp.getStation(index);
            CatalogPoint cp = this.catalog.getCatalogPoint(selRow);
            if (name.equals("StationName")) {
                sp.getStation(index).setName(cp.getName());
                sp.getStation(index).setX(cp.getX());
                sp.getStation(index).setY(cp.getY());
                sp.getStation(index).setZ(cp.getZ());
            }
            if (name.equals("OrName")) {
                sp.getStation(index).setNameOr(cp.getName());
                sp.getStation(index).setxOr(cp.getX());
                sp.getStation(index).setyOr(cp.getY());
            }
            if (name.equals("TheoStation")) {
                parentFrame.getPolygonProject().getTheoStation(index).setName(cp.getName());
                parentFrame.getPolygonProject().getTheoStation(index).setX(cp.getX());
                parentFrame.getPolygonProject().getTheoStation(index).setY(cp.getY());
                parentFrame.getPolygonProject().getTheoStation(index).setZ(cp.getZ());
            }
            this.dispose();
        }

    }
}
