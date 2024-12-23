package taheoport.gui;

import taheoport.dispatcher.DependencyInjector;
import taheoport.model.Picket;
import taheoport.model.SurveyStation;
import taheoport.repository.CatalogRepository;
import taheoport.model.CatalogPoint;
import taheoport.repository.SurveyRepository;
import taheoport.service.IOService;
import taheoport.service.SettingsService;
import taheoport.service.SurveyService;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

/**
 * This class encapsulates form for display result processing of measurement
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class ShowViewResults extends JDialog {

    private PaintPanel pnlView;
    private int sellRow;
    private final JTable tblView;
    private JTabbedPane tpSurvey;
    private final SurveyService surveyService;
    private final SettingsService settingsService;
    private final IOService ioService;

    /**
     * Constructor
     * @param dependencyInjector DependencyInjector
     */
    public ShowViewResults(DependencyInjector dependencyInjector) {
        super( dependencyInjector.getMainFrame(), dependencyInjector.getShell().getTitles().get("SVRdialogTitle"), true);
        surveyService = dependencyInjector.getSurveyService();
        settingsService = dependencyInjector.getSettingsService();
        ioService = dependencyInjector.getIoService();
        JFrame parentFrame = dependencyInjector.getMainFrame();
        HashMap<String, String> titles = dependencyInjector.getTitles();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        int widthFrame = parentFrame.getWidth() * 3 / 2;
        int heigthFrame = parentFrame.getHeight();
        setBounds(parentFrame.getX() + parentFrame.getWidth() / 2 - widthFrame / 2,
                parentFrame.getY() + parentFrame.getHeight() / 2 - heigthFrame / 2, widthFrame, heigthFrame);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image im = kit.getImage("images/teo.png");
        this.setIconImage(im);
// btnClose______________________________________________________________

        JButton btnClose = new JButton(new ImageIcon("images/close_pane.png"));
        btnClose.setToolTipText(titles.get("SVRbtnCloseTT"));
        btnClose.addActionListener(e -> this.dispose());

// btnSaveReport______________________________________________________________

        JButton btnSaveReport = new JButton(new ImageIcon("images/save.png"));
        btnSaveReport.setToolTipText(titles.get("SVRbtnSaveReportTT"));
        btnSaveReport.addActionListener(e -> {
            switch (tpSurvey.getSelectedIndex()) {
                case 0 -> ioService.writeTextFile(surveyService.getPickets(),
                        settingsService.getPathWorkDir(),"dat",
                        titles.get("SVRsaveTitle0"));
                case 1 -> ioService.writeTextFile(surveyService.getReport(),
                        settingsService.getPathWorkDir(),
                        "txt",
                        titles.get("SVRsaveTitle1"));
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

        CatalogRepository cView = new CatalogRepository();
        for (int i = 0; i < surveyService.sizeRepository(); i++) {
            SurveyStation surveyStation = surveyService.findStationById(i);
            cView.add(new CatalogPoint(surveyStation.getName(),
                    surveyStation.getX(),
                    surveyStation.getY(),
                    surveyStation.getZ()));
            cView.add(new CatalogPoint(surveyStation.getNameOr(),
                    surveyStation.getxOr(),
                    surveyStation.getyOr(),
                    surveyStation.getzOr()));
            for (int j = 0; j < surveyService.sizePickets(i); j++) {
                Picket picket = surveyService.getPicketById(i, j);
                cView.add(new CatalogPoint(picket.getpName(),
                        picket.getX(),
                        picket.getY(),
                        picket.getZ()));
            }
        }
        tblView = new JTable(new TmodelCatalog(cView));
        tblView.getTableHeader().setReorderingAllowed(false);
        tblView.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sellRow = -1;
        tblView.getSelectionModel().addListSelectionListener(e -> {
            sellRow = tblView.getSelectedRow();
            pnlView.setIndex(sellRow);
            pnlView.repaint();
//            this.reloadPnlView();
        });
        JScrollPane spnlTblView = new JScrollPane(tblView);
        spnlTblView.setPreferredSize(new Dimension(widthFrame / 5 * 2, heigthFrame));

//pnlView__________________________________________________

        pnlView = new PaintPanel(surveyService.getAllStations(), -1);

// pnlKatalog________________________________________________

        JPanel pnlCatalog = new JPanel();
        pnlCatalog.setLayout(new BorderLayout());
        pnlCatalog.add(spnlTblView, BorderLayout.WEST);
        pnlCatalog.add(pnlView, BorderLayout.CENTER);

// spReport__________________________________________________

        JTextArea textAreaReport = new JTextArea();
        List<String> ll = surveyService.getReport();
        String s;
        ll.remove(0);
        while (!ll.isEmpty()) {
            s = ll.remove(0);
            textAreaReport.append(s + "\n");
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
        tpSurvey.add(pnlCatalog);
        tpSurvey.add(spReport);
        tpSurvey.setTitleAt(0, titles.get("SVRtpSurveyTitle0"));
        tpSurvey.setTitleAt(1, titles.get("SVRtpSurveyTitle1"));
        add(tpSurvey);
        setResizable(true);
        setVisible(true);
    }
}
