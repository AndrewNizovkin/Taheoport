package taheoport;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ShowViewResults extends JDialog {
    private int sellRow;
    private MainWin parentFrame;
    private SurveyProject sp;
    private int widthFrame;
    private int heigthFrame;
    private JButton btnSavePickets;
    private JButton btnZoomOut;
    private JButton btnZoomIn;
    private JButton btnZoomAll;
    private PaintPanel pnlView;
    private JTable tblView;
    private JScrollPane spnlTblView;
    private JScrollPane spnlPnlView;
    private JTabbedPane tpSurvey;
    private JPanel pnlKatalog;

    /**
     * Constructor
     * @param frame
     */
    public ShowViewResults(MainWin frame) {
        super( frame, frame.getTitles().get("SVRdialogTitle"), true);
        parentFrame = frame;
        sp = parentFrame.getSurveyProject();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
//        setDefaultLookAndFeelDecorated(false);
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        widthFrame = parentFrame.getWidthMain() * 3 / 2;
        heigthFrame = parentFrame.getHeightMain();
        setBounds(parentFrame.getX() + parentFrame.getWidth() / 2 - widthFrame / 2,
                parentFrame.getY() + parentFrame.getHeight() / 2 - heigthFrame / 2, widthFrame, heigthFrame);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image im = kit.getImage("images/teo.png");
        this.setIconImage(im);
// btnClose______________________________________________________________

        JButton btnClose = new JButton(new ImageIcon("images/close_pane.png"));
        btnClose.setToolTipText(frame.getTitles().get("SVRbtnCloseTT"));
        btnClose.addActionListener(e -> this.dispose());

// btnSaveReport______________________________________________________________

        JButton btnSaveReport = new JButton(new ImageIcon("images/save.png"));
        btnSaveReport.setToolTipText(parentFrame.getTitles().get("SVRbtnSaveReportTT"));
        btnSaveReport.addActionListener(e -> {
            switch (tpSurvey.getSelectedIndex()) {
                case 0 -> new MyChooser(parentFrame).writeTextFile(parentFrame.getPathWorkDir(),"dat",
                        parentFrame.getTitles().get("SVRsaveTitle0"),
                        parentFrame.getSurveyProject().getPicketsList());
                case 1 -> new MyChooser(parentFrame).writeTextFile(parentFrame.getPathWorkDir(),
                        "txt",
                        parentFrame.getTitles().get("SVRsaveTitle1"),
                        parentFrame.getSurveyProject().getReportList());
            }
                });

// tb_________________________________________________________________________

        JToolBar tb = new JToolBar();
        tb.setFloatable(false);
        tb.setBorder(BorderFactory.createEtchedBorder());
        tb.add(btnClose);
        tb.add(btnSaveReport);

        add(tb, BorderLayout.NORTH);

//tblVeiw______________________________________________

        Catalog cView = new Catalog();
        for (int i = 0; i < sp.sizeStations(); i++) {
            cView.add(new CatalogPoint(sp.getStation(i).getName(),
                    sp.getStation(i).getX(),
                    sp.getStation(i).getY(),
                    sp.getStation(i).getZ()));
            cView.add(new CatalogPoint(sp.getStation(i).getNameOr(),
                    sp.getStation(i).getxOr(),
                    sp.getStation(i).getyOr(),
                    sp.getStation(i).getzOr()));
            for (int j = 0; j < sp.getStation(i).sizePickets(); j++) {
                cView.add(new CatalogPoint(sp.getStation(i).getPicket(j).getpName(),
                        sp.getStation(i).getPicket(j).getX(),
                        sp.getStation(i).getPicket(j).getY(),
                        sp.getStation(i).getPicket(j).getZ()));
            }
        }
        tblView = new JTable(new TmodelCatalog(cView));
        tblView.getTableHeader().setReorderingAllowed(false);
        tblView.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sellRow = -1;
        tblView.getSelectionModel().addListSelectionListener(e -> {
            sellRow = tblView.getSelectedRow();
            this.reloadPnlView();
        });
        spnlTblView = new JScrollPane(tblView);
        spnlTblView.setPreferredSize(new Dimension(widthFrame / 5 * 2, heigthFrame));

//pnlView__________________________________________________

        pnlView = new PaintPanel(this, -1);


// pnlKatalog________________________________________________

        pnlKatalog = new JPanel();
        pnlKatalog.setLayout(new BorderLayout());
        pnlKatalog.add(spnlTblView, BorderLayout.WEST);
        pnlKatalog.add(pnlView, BorderLayout.CENTER);

// spReport__________________________________________________

        JTextArea textAreaReport = new JTextArea();
        LinkedList<String> ll = parentFrame.getSurveyProject().getReportList();
        String s = ll.pollFirst();
        while (s != null) {
            textAreaReport.append(s + "\n");
            s = ll.pollFirst();
        }
        textAreaReport.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        textAreaReport.setLineWrap(false);
        textAreaReport.setWrapStyleWord(true);
        textAreaReport.setEditable(false);
        textAreaReport.setMargin(new Insets(10, 10, 10, 10));
        textAreaReport.setCaretPosition(0);
        JScrollPane spReport = new JScrollPane(textAreaReport);

// tpSurvey____________________________________________________

        tpSurvey = new JTabbedPane();
        tpSurvey.add(pnlKatalog);
        tpSurvey.add(spReport);
        tpSurvey.setTitleAt(0, parentFrame.getTitles().get("SVRtpSurveyTitle0"));
        tpSurvey.setTitleAt(1, parentFrame.getTitles().get("SVRtpSurveyTitle1"));
        add(tpSurvey);
        setResizable(true);
        setVisible(true);


    }

    public SurveyProject getSurveyProject() {
        return sp;
    }

    private void reloadPnlView() {
        pnlKatalog.remove(pnlView);
        pnlView = new PaintPanel(this, sellRow);
        pnlKatalog.add(pnlView, BorderLayout.CENTER);
        revalidate();

    }

}
