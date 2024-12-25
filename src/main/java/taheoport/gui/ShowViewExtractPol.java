    package taheoport.gui;


    import taheoport.dispatcher.DependencyContainer;
    import taheoport.dispatcher.DependencyInjector;
    import taheoport.service.ExtractService;
    import taheoport.service.IOService;
    import taheoport.service.SettingsService;
    import taheoport.service.Shell;

    import javax.swing.*;
    import java.awt.*;
    import java.util.HashMap;
    import java.util.List;

    /**
     * This class encapsulates form for display results of extracting polygon from measurement
     * @author Andrew Nizovkin
     * Copyright Nizovkin A.V. 2022
     */
    public class ShowViewExtractPol extends JDialog {
        private final JFrame parentFrame;
        private final ExtractService extractService;
        private final IOService ioService;
        private final SettingsService settingsService;

        /**
         * Constructor
         */
        public ShowViewExtractPol() {
            super( DependencyContainer
                            .getInstance()
                            .getMainFrame(),
                    DependencyContainer
                            .getInstance()
                            .getTitles()
                            .get("SVEdialogTitle"),
                    true);
//            super(dependencyInjector.getMainFrame(), dependencyInjector.getShell().getTitles().get("SVEdialogTitle"), true);
            DependencyInjector dependencyInjector = DependencyContainer.getInstance();
            extractService = dependencyInjector.getExtractService();
            settingsService = dependencyInjector.getSettingsService();
            ioService = dependencyInjector.getIoService();
            parentFrame = dependencyInjector.getMainFrame();
            HashMap<String, String> titles = dependencyInjector.getShell().getTitles();
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLayout(new BorderLayout());
            setUndecorated(true);
            getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
            setBounds(parentFrame.getX() + parentFrame.getWidth() / 2 - (parentFrame.getWidth() * 3 / 2) / 2,
                    parentFrame.getY() + parentFrame.getHeight() / 2 - parentFrame.getHeight() / 2,
                    parentFrame.getWidth() * 3 / 2,
                    parentFrame.getHeight());
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
                ioService.writeTextFile(extractService.getExtractReport(),
                        settingsService.getPathWorkDir(), "txt",
                        titles.get("SVRsaveTitle1"));
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
