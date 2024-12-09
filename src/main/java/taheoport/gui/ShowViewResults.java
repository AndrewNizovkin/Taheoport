package taheoport.gui;

import taheoport.repository.CatalogRepository;
import taheoport.model.CatalogPoint;
import taheoport.repository.SurveyRepository;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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
    private final SurveyRepository surveyRepository;
    private final JTable tblView;
    private JTabbedPane tpSurvey;

    /**
     * Constructor
     * @param frame parent MainWin
     */
    public ShowViewResults(MainWin frame) {
        super( frame, frame.getTitles().get("SVRdialogTitle"), true);
        this.parentFrame = frame;
        surveyRepository = parentFrame.getSurveyRepository();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        int widthFrame = parentFrame.getWidthMain() * 3 / 2;
        int heigthFrame = parentFrame.getHeightMain();
        setBounds(parentFrame.getX() + parentFrame.getWidth() / 2 - widthFrame / 2,
                parentFrame.getY() + parentFrame.getHeight() / 2 - heigthFrame / 2, widthFrame, heigthFrame);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image im = kit.getImage("images/teo.png");
        this.setIconImage(im);
// btnClose______________________________________________________________

        JButton btnClose = new JButton(new ImageIcon("images/close_pane.png"));
        btnClose.setToolTipText(parentFrame.getTitles().get("SVRbtnCloseTT"));
        btnClose.addActionListener(e -> this.dispose());

// btnSaveReport______________________________________________________________

        JButton btnSaveReport = new JButton(new ImageIcon("images/save.png"));
        btnSaveReport.setToolTipText(parentFrame.getTitles().get("SVRbtnSaveReportTT"));
        btnSaveReport.addActionListener(e -> {
            switch (tpSurvey.getSelectedIndex()) {
                case 0 -> parentFrame.getIoController().writeTextFile(parentFrame.getSurveyService().getPickets(),
                        parentFrame.getPathWorkDir(),"dat",
                        parentFrame.getTitles().get("SVRsaveTitle0"));
                case 1 -> parentFrame.getIoController().writeTextFile(parentFrame.getSurveyService().getReport(),
                        this.parentFrame.getPathWorkDir(),
                        "txt",
                        this.parentFrame.getTitles().get("SVRsaveTitle1"));
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
        for (int i = 0; i < surveyRepository.sizeStations(); i++) {
            cView.add(new CatalogPoint(surveyRepository.findById(i).getName(),
                    surveyRepository.findById(i).getX(),
                    surveyRepository.findById(i).getY(),
                    surveyRepository.findById(i).getZ()));
            cView.add(new CatalogPoint(surveyRepository.findById(i).getNameOr(),
                    surveyRepository.findById(i).getxOr(),
                    surveyRepository.findById(i).getyOr(),
                    surveyRepository.findById(i).getzOr()));
            for (int j = 0; j < surveyRepository.findById(i).sizePickets(); j++) {
                cView.add(new CatalogPoint(surveyRepository.findById(i).getPicket(j).getpName(),
                        surveyRepository.findById(i).getPicket(j).getX(),
                        surveyRepository.findById(i).getPicket(j).getY(),
                        surveyRepository.findById(i).getPicket(j).getZ()));
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

        pnlView = new PaintPanel(this, -1);

// pnlKatalog________________________________________________

        pnlKatalog = new JPanel();
        pnlKatalog.setLayout(new BorderLayout());
        pnlKatalog.add(spnlTblView, BorderLayout.WEST);
        pnlKatalog.add(pnlView, BorderLayout.CENTER);

// spReport__________________________________________________

        JTextArea textAreaReport = new JTextArea();
        List<String> ll = parentFrame.getSurveyService().getReport();
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
    public SurveyRepository getSurveyProject() {
        return surveyRepository;
    }


}
