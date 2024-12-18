    package taheoport.gui;


    import taheoport.service.ExtractService;
    import taheoport.service.IOService;

    import javax.swing.*;
    import java.awt.*;
    import java.util.List;

    /**
     * This class encapsulates form for display results of extracting polygon from measurement
     * @author Andrew Nizovkin
     * Copyright Nizovkin A.V. 2022
     */
    public class ShowViewExtractPol extends JDialog {
        private final MainWin parentFrame;
        private final ExtractService extractService;
        private final IOService ioService;

        /**
         * Constructor
         * @param frame MainWin parent frame
         */
        public ShowViewExtractPol(MainWin frame) {
            super(frame, frame.getTitles().get("SVEdialogTitle"), true);
            extractService = frame.getExtractService();
            ioService = frame.getIoService();
            this.parentFrame = frame;
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLayout(new BorderLayout());
            setUndecorated(true);
            getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
            setBounds(parentFrame.getX() + parentFrame.getWidth() / 2 - (parentFrame.getWidthMain() * 3 / 2) / 2,
                    parentFrame.getY() + parentFrame.getHeight() / 2 - parentFrame.getHeightMain() / 2,
                    parentFrame.getWidthMain() * 3 / 2,
                    parentFrame.getHeightMain());
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
                ioService.writeTextFile(extractService.getExtractReport(),
                        parentFrame.getPathWorkDir(), "txt",
                        parentFrame.getTitles().get("SVRsaveTitle1"));
            });

// tb_________________________________________________________________________

            JToolBar tb = new JToolBar();
            tb.setFloatable(false);
            tb.setBorder(BorderFactory.createEtchedBorder());
            tb.add(btnClose);
            tb.add(btnSaveReport);
            add(tb, BorderLayout.NORTH);

// spExtractReport___________________________________________________________

            JTextArea textArea = new JTextArea();
            List<String> llExtractReport = extractService.getExtractReport();
            llExtractReport.forEach(x -> textArea.append(x + "\n"));
            textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            textArea.setLineWrap(false);
            textArea.setWrapStyleWord(true);
            textArea.setEditable(false);
            textArea.setMargin(new Insets(10, 10, 10, 10));
            textArea.setCaretPosition(0);
            JScrollPane spExtractReport = new JScrollPane(textArea);

            add(spExtractReport, BorderLayout.CENTER);
            setResizable(true);
            setVisible(true);
        }
    }
