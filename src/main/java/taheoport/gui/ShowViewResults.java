package taheoport.gui;

import taheoport.model.Catalog;
import taheoport.model.CatalogPoint;
import taheoport.model.SurveyProject;
import taheoport.controllers.MyChooser;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * This class encapsulates form for display result processing of measurement
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class ShowViewResults extends JDialog {

    private final MainWin parentFrame;
    private final JPanel pnlKatalog;
    private PaintPanel pnlView;
    private int sellRow;
    private final SurveyProject surveyProject;
    private final JTable tblView;
    private JTabbedPane tpSurvey;

    /**
     * Constructor
     * @param parentFrame parent MainWin
     */
    public ShowViewResults(MainWin parentFrame) {
        super( parentFrame, parentFrame.getTitles().get("SVRdialogTitle"), true);
        this.parentFrame = parentFrame;
        surveyProject = this.parentFrame.getSurveyProject();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        int widthFrame = this.parentFrame.getWidthMain() * 3 / 2;
        int heigthFrame = this.parentFrame.getHeightMain();
        setBounds(this.parentFrame.getX() + this.parentFrame.getWidth() / 2 - widthFrame / 2,
                this.parentFrame.getY() + this.parentFrame.getHeight() / 2 - heigthFrame / 2, widthFrame, heigthFrame);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image im = kit.getImage("images/teo.png");
        this.setIconImage(im);
// btnClose______________________________________________________________

        JButton btnClose = new JButton(new ImageIcon("images/close_pane.png"));
        btnClose.setToolTipText(parentFrame.getTitles().get("SVRbtnCloseTT"));
        btnClose.addActionListener(e -> this.dispose());

// btnSaveReport______________________________________________________________

        JButton btnSaveReport = new JButton(new ImageIcon("images/save.png"));
        btnSaveReport.setToolTipText(this.parentFrame.getTitles().get("SVRbtnSaveReportTT"));
        btnSaveReport.addActionListener(e -> {
            switch (tpSurvey.getSelectedIndex()) {
                case 0 -> new MyChooser(this.parentFrame).writeTextFile(this.parentFrame.getPathWorkDir(),"dat",
                        this.parentFrame.getTitles().get("SVRsaveTitle0"),
                        this.parentFrame.getSurveyProject().getPicketsList());
                case 1 -> new MyChooser(this.parentFrame).writeTextFile(this.parentFrame.getPathWorkDir(),
                        "txt",
                        this.parentFrame.getTitles().get("SVRsaveTitle1"),
                        this.parentFrame.getSurveyProject().getReportList());
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
        for (int i = 0; i < surveyProject.sizeStations(); i++) {
            cView.add(new CatalogPoint(surveyProject.getStation(i).getName(),
                    surveyProject.getStation(i).getX(),
                    surveyProject.getStation(i).getY(),
                    surveyProject.getStation(i).getZ()));
            cView.add(new CatalogPoint(surveyProject.getStation(i).getNameOr(),
                    surveyProject.getStation(i).getxOr(),
                    surveyProject.getStation(i).getyOr(),
                    surveyProject.getStation(i).getzOr()));
            for (int j = 0; j < surveyProject.getStation(i).sizePickets(); j++) {
                cView.add(new CatalogPoint(surveyProject.getStation(i).getPicket(j).getpName(),
                        surveyProject.getStation(i).getPicket(j).getX(),
                        surveyProject.getStation(i).getPicket(j).getY(),
                        surveyProject.getStation(i).getPicket(j).getZ()));
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
        JScrollPane spnlTblView = new JScrollPane(tblView);
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
        LinkedList<String> ll = this.parentFrame.getSurveyProject().getReportList();
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
        tpSurvey.setTitleAt(0, this.parentFrame.getTitles().get("SVRtpSurveyTitle0"));
        tpSurvey.setTitleAt(1, this.parentFrame.getTitles().get("SVRtpSurveyTitle1"));
        add(tpSurvey);
        setResizable(true);
        setVisible(true);
    }

    /**
     * Gets this.surveyProject
     * @return SurveyProject
     */
    public SurveyProject getSurveyProject() {
        return surveyProject;
    }

    /**
     * Updates pnlView
     */
    private void reloadPnlView() {
        pnlKatalog.remove(pnlView);
        pnlView = new PaintPanel(this, sellRow);
        pnlKatalog.add(pnlView, BorderLayout.CENTER);
        revalidate();
    }
}
