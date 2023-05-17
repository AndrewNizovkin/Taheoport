package taheoport;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * This class encapsulates form for display result of polygon adjustment
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class ShowViewAdjustment extends JDialog {
    private final MainWin parentFrame;
    private final JPanel pnlNXYZ;
    private PolygonPaintPanel pnlViewNXYZ;
    private int sellRow;
    private JTabbedPane tp;

    /**
     * Constructor
     * @param parentFrame MainWin parent parentFrame
     */
    public ShowViewAdjustment(MainWin parentFrame) {
        super(parentFrame,parentFrame.getTitles().get("SVAdialogTitle"), true);
        this.parentFrame = parentFrame;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
//        setDefaultLookAndFeelDecorated(false);
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        int widthFrame = this.parentFrame.getWidthMain() * 3 / 2;
        int heightFrame = this.parentFrame.getHeightMain();
        setBounds(this.parentFrame.getX() + this.parentFrame.getWidth() / 2 - widthFrame / 2,
                this.parentFrame.getY() + this.parentFrame.getHeight() / 2 - heightFrame / 2, widthFrame, heightFrame);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image im = kit.getImage("images/teo.png");
        this.setIconImage(im);
// btnClose______________________________________________________________

        JButton btnClose = new JButton(new ImageIcon("images/close_pane.png"));
        btnClose.setToolTipText(this.parentFrame.getTitles().get("SVRbtnCloseTT"));
        btnClose.addActionListener(e -> this.dispose());

// btnSaveReport______________________________________________________________

        JButton btnSaveReport = new JButton(new ImageIcon("images/save.png"));
        btnSaveReport.setToolTipText(this.parentFrame.getTitles().get("SVRbtnSaveReportTT"));
        btnSaveReport.addActionListener(e -> {
            switch (tp.getSelectedIndex()) {
                case 0 -> new MyChooser(this.parentFrame).writeTextFile(this.parentFrame.getPathWorkDir(),
                        "kat",
                        this.parentFrame.getTitles().get("SVRsaveTitle0"),
                        this.parentFrame.getPolygonProject().getReportNXYZ());
                case 1 -> new MyChooser(this.parentFrame).writeTextFile(this.parentFrame.getPathWorkDir(),
                        "txt",
                        this.parentFrame.getTitles().get("SVRsaveTitle1"),
                        this.parentFrame.getPolygonProject().getReportXY());
                case 2 -> new MyChooser(this.parentFrame).writeTextFile(this.parentFrame.getPathWorkDir(),
                        "txt",
                        this.parentFrame.getTitles().get("SVRsaveTitle1"),
                        this.parentFrame.getPolygonProject().getReportZ());

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
        LinkedList<String> llReportXY = this.parentFrame.getPolygonProject().getReportXY();
        String s = llReportXY.pollFirst();
        while (s != null) {
            textAreaXY.append(s + "\n");
            s = llReportXY.pollFirst();
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
        LinkedList<String> llReportZ = this.parentFrame.getPolygonProject().getReportZ();
        s = llReportZ.pollFirst();
        while (s != null) {
            textAreaZ.append(s + "\n");
            s = llReportZ.pollFirst();
        }
        textAreaZ.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        textAreaZ.setLineWrap(false);
        textAreaZ.setWrapStyleWord(true);
        textAreaZ.setEditable(false);
        textAreaZ.setMargin(new Insets(10, 10, 10, 10));
        textAreaZ.setCaretPosition(0);
        JScrollPane spReportZ = new JScrollPane(textAreaZ);

// pnlNXYZ______________________________________________________________________

        pnlNXYZ = new JPanel();
        pnlNXYZ.setLayout(new BorderLayout());

// tblNXYZ_______________________________________________________________________

            Catalog catalogNXYZ = new Catalog();
            for (int i = 0; i < this.parentFrame.getPolygonProject().getSizePolygonStations(); i++) {
                catalogNXYZ.add(new CatalogPoint(this.parentFrame.getPolygonProject().getPolygonStation(i).getName(),
                        this.parentFrame.getPolygonProject().getPolygonStation(i).getX(),
                        this.parentFrame.getPolygonProject().getPolygonStation(i).getY(),
                        this.parentFrame.getPolygonProject().getPolygonStation(i).getZ()));
            }
            JTable tblNXYZ = new JTable(new TmodelCatalog(catalogNXYZ));
            tblNXYZ.getTableHeader().setReorderingAllowed(false);
            tblNXYZ.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            sellRow = -1;
            tblNXYZ.getSelectionModel().addListSelectionListener(e -> {
                sellRow = tblNXYZ.getSelectedRow();
                this.reloadPnlViewNXYZ();
            });

            JScrollPane spNXYZ = new JScrollPane(tblNXYZ);
            spNXYZ.setPreferredSize(new Dimension(widthFrame / 5 * 2, heightFrame));

        pnlNXYZ.add(spNXYZ, BorderLayout.WEST);
        reloadPnlViewNXYZ();

// tp____________________________________________________________________________

        tp = new JTabbedPane();
        tp.add(pnlNXYZ);
        tp.add(spReportXY);
        tp.add(spReportZ);
        tp.setTitleAt(0, this.parentFrame.getTitles().get("SVRtpSurveyTitle0"));
        tp.setTitleAt(1, this.parentFrame.getTitles().get("SVRtpSurveyTitle1"));
        tp.setTitleAt(2, this.parentFrame.getTitles().get("SVRtpSurveyTitle2"));
        add(tp);
        setResizable(true);
        setVisible(true);
//-->> The End of constructor
    }

    /**
     * add pnlViewNXYZ to pnlNXYZ
     */
    private void reloadPnlViewNXYZ() {
        if (pnlViewNXYZ != null) {
            pnlNXYZ.remove(pnlViewNXYZ);
        }
        pnlViewNXYZ = new PolygonPaintPanel(parentFrame.getPolygonProject(), sellRow);
        pnlNXYZ.add(pnlViewNXYZ, BorderLayout.CENTER);
        revalidate();
    }

//-->> The END of class ShowViewAdjustment
}
