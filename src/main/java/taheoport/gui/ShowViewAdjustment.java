package taheoport.gui;

import taheoport.dispatcher.DependencyInjector;
import taheoport.model.PolygonStation;
import taheoport.repository.CatalogRepository;
import taheoport.model.CatalogPoint;
import taheoport.service.IOService;
import taheoport.service.PolygonService;
import taheoport.service.SettingsService;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

/**
 * This class encapsulates form for display result of polygon adjustment
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class ShowViewAdjustment extends JDialog {
    private final PolygonService polygonService;
    private final SettingsService settingsService;
    private final IOService ioService;
    private PolygonPaintPanel pnlViewNXYZ;
    private int sellRow;
    private JTabbedPane tp;

    /**
     * Constructor
     * @param dependencyInjector DependencyInjector
     */
    public ShowViewAdjustment(DependencyInjector dependencyInjector) {
        super(dependencyInjector.getMainFrame(), dependencyInjector.getShell().getTitles().get("SVAdialogTitle"), true);
        polygonService = dependencyInjector.getPolygonService();
        settingsService = dependencyInjector.getSettingsService();
        ioService = dependencyInjector.getIoService();
        JFrame parentFrame = dependencyInjector.getMainFrame();
        HashMap<String, String> titles = dependencyInjector.getTitles();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        int widthFrame = parentFrame.getWidth() * 3 / 2;
        int heightFrame = parentFrame.getHeight();
        setBounds(parentFrame.getX() + parentFrame.getWidth() / 2 - widthFrame / 2,
                parentFrame.getY() + parentFrame.getHeight() / 2 - heightFrame / 2, widthFrame, heightFrame);
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
            switch (tp.getSelectedIndex()) {
                case 0 -> ioService.writeTextFile(polygonService.getReportNXYZ(),
                        settingsService.getPathWorkDir(),
                        "kat",
                        titles.get("SVRsaveTitle0"));
                case 1 -> ioService.writeTextFile(polygonService.getReportXY(),
                        settingsService.getPathWorkDir(),
                        "txt",
                        titles.get("SVRsaveTitle1"));
                case 2 -> ioService.writeTextFile(polygonService.getReportZ(),
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

// spReportXY_______________________________________________________________________

        JTextArea textAreaXY = new JTextArea();
        List<String> llReportXY = polygonService.getReportXY();
        String s;
        llReportXY.remove(0);
        while (!llReportXY.isEmpty()) {
            s = llReportXY.remove(0);
            textAreaXY.append(s + "\n");

        }
        textAreaXY.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        textAreaXY.setLineWrap(false);
        textAreaXY.setWrapStyleWord(true);
        textAreaXY.setEditable(false);
        textAreaXY.setMargin(new Insets(10, 10, 10, 10));
        textAreaXY.setCaretPosition(0);
        JScrollPane spReportXY = new JScrollPane(textAreaXY);


// spReportZ_________________________________________________________________________

        JPanel pnlReportZ = new JPanel();
        JTextArea textAreaZ = new JTextArea();
        pnlReportZ.setLayout(new GridLayout(0, 1));
        List<String> llReportZ = polygonService.getReportZ();
        llReportZ.forEach(x -> textAreaZ.append(x + "\n"));
        textAreaZ.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        textAreaZ.setLineWrap(false);
        textAreaZ.setWrapStyleWord(true);
        textAreaZ.setEditable(false);
        textAreaZ.setMargin(new Insets(10, 10, 10, 10));
        textAreaZ.setCaretPosition(0);
        JScrollPane spReportZ = new JScrollPane(textAreaZ);

// pnlNXYZ______________________________________________________________________

        JPanel pnlNXYZ = new JPanel();
        pnlNXYZ.setLayout(new BorderLayout());

// tblNXYZ_______________________________________________________________________

            CatalogRepository catalogRepositoryNXYZ = new CatalogRepository();
            for (PolygonStation polygonStation: polygonService.getAllPolygonStations()) {
                catalogRepositoryNXYZ.add(new CatalogPoint(
                        polygonStation.getName(),
                        polygonStation.getX(),
                        polygonStation.getY(),
                        polygonStation.getZ()));

            }

            JTable tblNXYZ = new JTable(new TmodelCatalog(catalogRepositoryNXYZ));
            tblNXYZ.getTableHeader().setReorderingAllowed(false);
            tblNXYZ.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            sellRow = -1;
            tblNXYZ.getSelectionModel().addListSelectionListener(e -> {
                sellRow = tblNXYZ.getSelectedRow();
                pnlViewNXYZ.setIndex(sellRow);
                pnlViewNXYZ.repaint();
            });

            JScrollPane spNXYZ = new JScrollPane(tblNXYZ);
            spNXYZ.setPreferredSize(new Dimension(widthFrame / 5 * 2, heightFrame));

        pnlNXYZ.add(spNXYZ, BorderLayout.WEST);
        pnlViewNXYZ = new PolygonPaintPanel(polygonService.getAllPolygonStations(), sellRow);
        pnlNXYZ.add(pnlViewNXYZ, BorderLayout.CENTER);

//        reloadPnlViewNXYZ();

// tp____________________________________________________________________________

        tp = new JTabbedPane();
        tp.add(pnlNXYZ);
        tp.add(spReportXY);
        tp.add(spReportZ);
        tp.setTitleAt(0, titles.get("SVRtpSurveyTitle0"));
        tp.setTitleAt(1, titles.get("SVRtpSurveyTitle1"));
        tp.setTitleAt(2, titles.get("SVRtpSurveyTitle2"));
        add(tp);
        setResizable(true);
        setVisible(true);
    }
}
