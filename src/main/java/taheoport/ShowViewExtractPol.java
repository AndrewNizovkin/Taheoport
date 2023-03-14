    package taheoport;

    import javax.swing.*;
    import java.awt.*;
    import java.util.LinkedList;

    /**
     * This class encapsulates form for display results of extracting polygon from measurement
     * @author Andrey Nizovkin
     * Copyright Nizovkin A.V. 2022
     */
    public class ShowViewExtractPol extends JDialog {
        private final MainWin parentFrame;

        /**
         * Constructor
         * @param parentFrame MainWin parent frame
         */
        public ShowViewExtractPol(MainWin parentFrame) {
            super(parentFrame, parentFrame.getTitles().get("SVEdialogTitle"), true);
            this.parentFrame = parentFrame;
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
                new MyChooser(this.parentFrame).writeTextFile(this.parentFrame.getPathWorkDir(), "txt",
                        this.parentFrame.getTitles().get("SVRsaveTitle1"),
                        this.parentFrame.getExtractProject().getExtractReport());
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
            LinkedList<String> llExtractReport = parentFrame.getExtractProject().getExtractReport();
            String s = llExtractReport.pollFirst();
            while (s != null) {
                textArea.append(s + "\n");
                s = llExtractReport.pollFirst();
            }
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
