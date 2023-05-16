package taheoport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

/**
 * This class encapsulates form for changes program settings
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class ShowOptions extends JDialog {
    private final MainWin parentFrame;
    private final Options options;
    private final JPanel pnlWorkDir;
    private final JPanel pnlOrientStation;
    private final JPanel pnlExtractor;
    private final JTabbedPane tp;
    private final JTextField tfPathWorkDir;
    private final JButton btnFolder;
    private final JButton btnApprove;
    private final JButton btnCancel;
    private final JRadioButton rbZero;
    private final JRadioButton rbFirst;
    private final JLabel lblLanguage;
    private final JLabel lblPrefixEX;
    private final JComboBox<String> cbFOtn;
    private final JComboBox<String> cbFAbs;
    private final JComboBox<String> cbFHor;
    private final JComboBox<String> cbFH;
    private final JLabel lblFAbs;
    private final JLabel lblFHor;
    private final JLabel lblFH;
    private final JPanel pnlAcceptable;
    private final JLabel lblFOtn;

    /**
     * Constructor
     * @param frame parent frame
     */
    public ShowOptions(MainWin frame) {
        super(frame, frame.getTitles().get("SOtitle"), true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        parentFrame = frame;
        options = parentFrame.getOptions();
        int w = parentFrame.getWidthMain() / 3 * 2;
        int h = parentFrame.getHeightMain() / 2;
        setBounds(parentFrame.getX() + parentFrame.getWidthMain() / 2 - w / 2,
                parentFrame.getY() + parentFrame.getHeightMain() / 2 - h / 2, w, h);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image im = kit.getImage("images/teo.png");
        this.setIconImage(im);

        tp = new JTabbedPane();

// pnlGeneral__________________________________________________________________

        JPanel pnlGeneral = new JPanel();
        pnlGeneral.setLayout(new GridBagLayout());

// pnlLanguage_________________________________________________________________

            JPanel pnlLanguage = new JPanel();
            pnlLanguage.setLayout(new GridBagLayout());

// lblLanguage_________________________________________________________________

            lblLanguage = new JLabel(parentFrame.getTitles().get("SOlblLanguage"), JLabel.CENTER);

            pnlLanguage.add(lblLanguage, new GridBagConstraints(0, 0, 1, 1, 1, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

// cbLanguage_________________________________________________________________

            String[] languages = {"English", "Русский"};
            JComboBox<String> cbLanguage = new JComboBox<>(languages);
            cbLanguage.setSelectedIndex(parentFrame.getOptions().getLanguage());
            cbLanguage.addActionListener(e -> {
                parentFrame.getOptions().setLanguage(cbLanguage.getSelectedIndex());
                parentFrame.translate();
                translate();
            });

            pnlLanguage.add(cbLanguage, new GridBagConstraints(1, 0, 1, 1, 1, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        pnlGeneral.add(pnlLanguage, new GridBagConstraints(0, 0, 1, 1, 1, 0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));


// pnlWorkDir__________________________________________________________________

            pnlWorkDir = new JPanel();
            pnlWorkDir.setLayout(new GridBagLayout());
            pnlWorkDir.setBorder(BorderFactory.createTitledBorder(parentFrame.getTitles().get("SOpnlWorkDirTitle")));

// tfPathWorkDir__________________________________________________________________

                tfPathWorkDir = new JTextField(25);
                tfPathWorkDir.setToolTipText(parentFrame.getTitles().get("SOtfPathWorkDirTT"));
                tfPathWorkDir.setText(options.getPathWorkDir());
                tfPathWorkDir.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        tfPathWorkDir.selectAll();
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        if(new File(tfPathWorkDir.getText()).isDirectory()) {
                            options.setPathWorkDir(tfPathWorkDir.getText());

                        } else {
                            tfPathWorkDir.setText(options.getPathWorkDir());
                        }

                    }
                });
                tfPathWorkDir.addActionListener(e -> {
                    if(new File(tfPathWorkDir.getText()).isDirectory()) {
                        options.setPathWorkDir(tfPathWorkDir.getText());

                    } else {
                        tfPathWorkDir.setText(options.getPathWorkDir());
                    }

                });

                pnlWorkDir.add(tfPathWorkDir, new GridBagConstraints(0, 0, 4, 1, 4, 0,
                        GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

// btnFolder__________________________________________________________________

                btnFolder = new JButton(new ImageIcon("images/browse_folder.png"));
                btnFolder.setToolTipText(parentFrame.getTitles().get("SObtnFolderTT"));
                btnFolder.addActionListener(e -> {
                    File f = new File(options.getPathWorkDir());
                    JFileChooser fileChooser = new JFileChooser(f.getAbsolutePath());
                    fileChooser.setDialogTitle(parentFrame.getTitles().get("SOsetDialogTitle"));
                    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int res = fileChooser.showOpenDialog(this);
                    if (res != JFileChooser.APPROVE_OPTION) {
                        fileChooser.cancelSelection();
                    } else {
                        if (f.isDirectory()) {
                            f = fileChooser.getSelectedFile();
                            tfPathWorkDir.setText(f.getAbsolutePath());
                            options.setPathWorkDir(tfPathWorkDir.getText());
                        }
                    }
                });

                pnlWorkDir.add(btnFolder, new GridBagConstraints(4, 0, 1, 1, 1, 0,
                        GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        pnlGeneral.add(pnlWorkDir, new GridBagConstraints(0, 1, 1, 1, 1, 0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

// pnlOrientStation_________________________________________________

        pnlOrientStation = new JPanel();
        pnlOrientStation.setLayout(new GridBagLayout());
        pnlOrientStation.setBorder(BorderFactory.createTitledBorder(parentFrame.getTitles().get("SOpnlOrientStationTitle")));

// rbZero___________________________________________________________

            rbZero = new JRadioButton(parentFrame.getTitles().get("SOrbZero"));
            rbZero.addActionListener(e -> setOrientStation());

           pnlOrientStation.add(rbZero, new GridBagConstraints(0, 0, 1, 1, 1, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

// rbFirst__________________________________________________________

            rbFirst = new JRadioButton(parentFrame.getTitles().get("SOrbFirst"));
            rbFirst.addActionListener(e -> setOrientStation());

            pnlOrientStation.add(rbFirst, new GridBagConstraints(0, 1, 1, 1, 1, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

// bgOrientStation___________________________________________________

            ButtonGroup bgOrientStation = new ButtonGroup();
            bgOrientStation.add(rbZero);
            bgOrientStation.add(rbFirst);
            showOrientStation();

        pnlGeneral.add(pnlOrientStation, new GridBagConstraints(0, 2, 1, 1, 1, 0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

// pnlExtractor______________________________________________________

        pnlExtractor = new JPanel();
        pnlExtractor.setLayout(new GridBagLayout());
        pnlExtractor.setBorder(BorderFactory.createTitledBorder(parentFrame.getTitles().get("SOpnlExtractorTitle")));

// lblPrefixEX_______________________________________________________

            lblPrefixEX = new JLabel(parentFrame.getTitles().get("SOlblPrefixEX"), JLabel.CENTER);
            lblPrefixEX.setToolTipText((parentFrame.getTitles().get("SOlblPrefixEXTT")));

            pnlExtractor.add(lblPrefixEX, new GridBagConstraints(0, 0, 4, 1, 4, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));


// cbPrefixEX_________________________________________________________

            String[] prefixs = new String[26];
            for (int i = 0; i < prefixs.length; i++) {
                prefixs[i] = " " + (char) (i + 65);
            }
            JComboBox<String> cbPrefixEX = new JComboBox<>(prefixs);
            cbPrefixEX.setSelectedIndex(parentFrame.getOptions().getPrefixEX() - 65);
            cbPrefixEX.addActionListener(e -> parentFrame.getOptions().setPrefixEX(cbPrefixEX.getSelectedIndex() + 65));

            pnlExtractor.add(cbPrefixEX, new GridBagConstraints(4, 0, 1, 1, 1, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        pnlGeneral.add(pnlExtractor, new GridBagConstraints(0, 3, 1, 1, 1, 0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

// pnlBlank__________________________________________________________

        JPanel pnlBlank = new JPanel();

            pnlGeneral.add(pnlBlank, new GridBagConstraints(0, 4, 1, 1, 0, 1,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        tp.add(pnlGeneral);


// pnlMeasurments____________________________________________________

        JPanel pnlMeasurements = new JPanel();
        pnlMeasurements.setLayout(new GridBagLayout());

// pnlAcceptable_____________________________________________________

        pnlAcceptable = new JPanel();
        pnlAcceptable.setBorder(BorderFactory.createTitledBorder(parentFrame.getTitles().get("SOpnlAcceptableTitle")));
        pnlAcceptable.setLayout(new GridBagLayout());

// lblFH_________________________________________________________________________

            lblFH = new JLabel(parentFrame.getTitles().get("SOlblFH"), JLabel.LEFT);

            pnlAcceptable.add(lblFH, new GridBagConstraints(0, 0, 4, 1, 4, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

// lblFHor__________________________________________________________________

            lblFHor  =  new JLabel(parentFrame.getTitles().get("SOlblFHor"), JLabel.LEFT);

            pnlAcceptable.add(lblFHor, new GridBagConstraints(0, 1, 4, 1, 4, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

// lblFAbs_________________________________________________________________

            lblFAbs = new JLabel(parentFrame.getTitles().get("SOlblFAbs"), JLabel.LEFT);

            pnlAcceptable.add(lblFAbs, new GridBagConstraints(0, 2, 4, 1, 4, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

// lblFOtn__________________________________________________________________
            lblFOtn = new JLabel(parentFrame.getTitles().get("SOlblFOtn"), JLabel.LEFT);

            pnlAcceptable.add(lblFOtn, new GridBagConstraints(0, 3, 4, 1, 4, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

// cbFH_______________________________________________________________

            cbFH = new JComboBox<String>(parentFrame.getOptions().getFHs());
            cbFH.setToolTipText(parentFrame.getTitles().get("SOcbFHTT"));
            cbFH.setSelectedIndex(parentFrame.getOptions().getIdxFH());
            cbFH.addActionListener(e -> parentFrame.getOptions().setIdxFH(cbFH.getSelectedIndex()));

            pnlAcceptable.add(cbFH, new GridBagConstraints(4, 0, 1, 1, 1, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

// cbFHor_______________________________________________________________

            cbFHor = new JComboBox<String>(parentFrame.getOptions().getFHors());
            cbFHor.setToolTipText(parentFrame.getTitles().get("SOcbFHorTT"));
            cbFHor.setSelectedIndex(parentFrame.getOptions().getIdxFHor());
            cbFHor.addActionListener(e -> parentFrame.getOptions().setIdxFHor(cbFHor.getSelectedIndex()));

            pnlAcceptable.add(cbFHor, new GridBagConstraints(4, 1, 1, 1, 1, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

// cbFAbs_______________________________________________________________

            cbFAbs = new JComboBox<String>(parentFrame.getOptions().getFAbss());
//            cbFAbs.setFont(parentFrame.getFontMain());
            cbFAbs.setSelectedIndex(parentFrame.getOptions().getIdxFAbs());
            cbFAbs.addActionListener(e -> parentFrame.getOptions().setIdxFAbs(cbFAbs.getSelectedIndex()));

            pnlAcceptable.add(cbFAbs, new GridBagConstraints(4, 2, 1, 1, 1, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

// cbFOtn_______________________________________________________________

            cbFOtn = new JComboBox<String>(parentFrame.getOptions().getFOtns());
            cbFOtn.setSelectedIndex(parentFrame.getOptions().getIdxFOtn());
            cbFOtn.addActionListener(e -> parentFrame.getOptions().setIdxFOtn(cbFOtn.getSelectedIndex()));
            pnlAcceptable.add(cbFOtn, new GridBagConstraints(4, 3, 1, 1, 1, 0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        pnlMeasurements.add(pnlAcceptable, new GridBagConstraints(0, 0, 1, 1, 1, 0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

// pnlBlankM____________________________________________________________

        JPanel pnlBlankM  = new JPanel();

        pnlMeasurements.add(pnlBlankM, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        tp.add(pnlMeasurements);
        tp.setTitleAt(0, parentFrame.getTitles().get("SOtpTitle0"));
        tp.setTitleAt(1, parentFrame.getTitles().get("SOtpTitle1"));
        add(tp);

// pnlControl________________________________________________________

        JPanel pnlControl = new JPanel();
        pnlControl.setLayout(new FlowLayout());

// btnApprove________________________________________________________
            btnApprove = new JButton(parentFrame.getTitles().get("SObtnApprove"));
            btnApprove.setToolTipText(parentFrame.getTitles().get("SObtnApproveTT"));
            btnApprove.addActionListener(e -> {
                    options.saveOptions();
//                    parentFrame.setOptions();
                this.dispose();
            });
            pnlControl.add(btnApprove);

// btnCancel________________________________________________________
            btnCancel = new JButton(parentFrame.getTitles().get("SObtnCancel"));
//            btnCancel.setFont(parentFrame.getFontMain());
            btnCancel.setToolTipText(parentFrame.getTitles().get("SObtnCancelTT"));
            btnCancel.addActionListener(e -> this.dispose());

            pnlControl.add(btnCancel);
        add(pnlControl, BorderLayout.SOUTH);


        setResizable(false);
        setVisible(true);
// The END of the Constructor
    }

    /**
     * sets one of the rbZero or rbFirst from the bgOrientStation
     */
    private void showOrientStation() {
        switch (parentFrame.getOptions().getOrientStation()) {
            case 0 -> rbZero.setSelected(true);
            case 1 -> rbFirst.setSelected(true);
        }
    }

    /**
     * sets parentFrame.getOptions.setOrientStation
     */
    private void setOrientStation() {
        if (rbZero.isSelected()) {
            parentFrame.getOptions().setOrientStation(0);
        } else {
            parentFrame.getOptions().setOrientStation(1);
        }
    }

    /**
     * Translate this
     */
    private void translate() {
        setTitle(parentFrame.getTitles().get("SOtitle"));
        lblLanguage.setText(parentFrame.getTitles().get("SOlblLanguage"));
        pnlWorkDir.setBorder(BorderFactory.createTitledBorder(parentFrame.getTitles().get("SOpnlWorkDirTitle")));
        tfPathWorkDir.setToolTipText(parentFrame.getTitles().get("SOtfPathWorkDirTT"));
        btnFolder.setToolTipText(parentFrame.getTitles().get("SObtnFolderTT"));
        pnlOrientStation.setBorder(BorderFactory.createTitledBorder(parentFrame.getTitles().get("SOpnlOrientStationTitle")));
        rbZero.setText(parentFrame.getTitles().get("SOrbZero"));
        rbFirst.setText(parentFrame.getTitles().get("SOrbFirst"));
        pnlExtractor.setBorder(BorderFactory.createTitledBorder(parentFrame.getTitles().get("SOpnlExtractorTitle")));
        lblPrefixEX.setText(parentFrame.getTitles().get("SOlblPrefixEX"));
        lblPrefixEX.setToolTipText(parentFrame.getTitles().get("SOlblPrefixEXTT"));
        pnlAcceptable.setBorder(BorderFactory.createTitledBorder(parentFrame.getTitles().get("SOpnlAcceptableTitle")));
        lblFH.setText(parentFrame.getTitles().get("SOlblFH"));
        lblFHor.setText(parentFrame.getTitles().get("SOlblFHor"));
        lblFAbs.setText(parentFrame.getTitles().get("SOlblFAbs"));
        lblFOtn.setText(parentFrame.getTitles().get("SOlblFOtn"));
        cbFH.setToolTipText(parentFrame.getTitles().get("SOcbFHTT"));
        cbFHor.setToolTipText(parentFrame.getTitles().get("SOcbFHorTT"));
        tp.setTitleAt(0, parentFrame.getTitles().get("SOtpTitle0"));
        tp.setTitleAt(1, parentFrame.getTitles().get("SOtpTitle1"));
        btnApprove.setText(parentFrame.getTitles().get("SObtnApprove"));
        btnApprove.setToolTipText(parentFrame.getTitles().get("SObtnApproveTT"));
        btnCancel.setText(parentFrame.getTitles().get("SObtnCancel"));
        btnCancel.setToolTipText(parentFrame.getTitles().get("SObtnCancelTT"));

    }

// The END of Class
}
