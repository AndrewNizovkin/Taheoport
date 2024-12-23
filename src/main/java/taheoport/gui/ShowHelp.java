package taheoport.gui;

import taheoport.dispatcher.DependencyInjector;
import taheoport.service.ManualService;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.util.HashMap;

/**
 * This class encapsulates form for users guid
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class ShowHelp extends JDialog {

    private final CardLayout cardLayout = new CardLayout();
    private final ManualService manualService;
    private final JPanel pnlViewContent;
    private final JPanel pnlToolbarDemo;
    private JScrollPane spToolbarDemo;
    private final JTree tContent;
    private final JTree tViewContent;
    private final DefaultMutableTreeNode tnContent;
    private final DefaultMutableTreeNode tnIntroduction;
    private final DefaultMutableTreeNode tnImport;
    private final DefaultMutableTreeNode tnExtract;
    private final DefaultMutableTreeNode tnAdjustment;
    private final DefaultMutableTreeNode tnMainMenu;
    private final DefaultMutableTreeNode tnToolbar;
    private final DefaultMutableTreeNode tnMeasurements;
    private final DefaultMutableTreeNode tnPolygon;
    private final DefaultMutableTreeNode tnFiles;
    private final DefaultMutableTreeNode tnOptions;

    /**
     * Constructor
     * @param dependencyInjector DependencyInjector
     */
    public ShowHelp(DependencyInjector dependencyInjector) {
        super(dependencyInjector.getMainFrame(), dependencyInjector.getShell().getTitles().get("SHtitleFrame"), false);
        HashMap<String, String> titles = dependencyInjector.getShell().getTitles();
        JFrame parentFrame = dependencyInjector.getMainFrame();
        manualService = dependencyInjector.getManual();
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

        JToolBar tb = new JToolBar();
        tb.setFloatable(false);
        tb.setBorder(BorderFactory.createEtchedBorder());
        tb.add(btnClose);
        add(tb, BorderLayout.NORTH);


// tnContent_&_tn**_________________________________________________________________________________

        tnContent = new DefaultMutableTreeNode(titles.get("SHtnContent"));
            tnIntroduction = new DefaultMutableTreeNode(titles.get("SHtnIntroduction"));
        tnContent.add(tnIntroduction);
        DefaultMutableTreeNode tnTasks = new DefaultMutableTreeNode(titles.get("SHtnTasks"));
        tnContent.add(tnTasks);
                tnImport = new DefaultMutableTreeNode(titles.get("SHtnImport"));
                tnExtract = new DefaultMutableTreeNode(titles.get("SHtnExtract"));
                tnAdjustment = new DefaultMutableTreeNode(titles.get("SHtnAdjustment"));
                tnTasks.add(tnImport);
                tnTasks.add(tnExtract);
            tnTasks.add(tnAdjustment);
        DefaultMutableTreeNode tnInterface = new DefaultMutableTreeNode(titles.get("SHtnInterface"));
        tnContent.add(tnInterface);
                tnMainMenu = new DefaultMutableTreeNode(titles.get("SHtnMainMenu"));
                tnInterface.add(tnMainMenu);
        tnToolbar = new DefaultMutableTreeNode(titles.get("SHtnToolbar"));
        tnInterface.add(tnToolbar);
        tnMeasurements = new DefaultMutableTreeNode(titles.get("SHtnMeasurements"));
        tnInterface.add(tnMeasurements);
        tnPolygon = new DefaultMutableTreeNode(titles.get("SHtnPolygon"));
        tnInterface.add(tnPolygon);
        tnOptions = new DefaultMutableTreeNode(titles.get("SHtnOptions"));
        tnInterface.add(tnOptions);
        tnFiles = new DefaultMutableTreeNode(titles.get("SHtnFiles"));
        tnContent.add(tnFiles);

// tContent__________________________________________________________

        tContent = new JTree(tnContent);
        tContent.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tContent.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tContent.getLastSelectedPathComponent();
            updateViewContent(node);


        });
        JScrollPane spContent = new JScrollPane(tContent);
        add(spContent, BorderLayout.WEST);

// pnlContent________________________________________________________

        JPanel pnlContent = new JPanel(new BorderLayout());
        JLabel lblContent = new JLabel(titles.get("SHtnContent"));
        lblContent.setForeground(Color.BLUE);
        lblContent.setHorizontalAlignment(JLabel.CENTER);
        pnlContent.add(lblContent, BorderLayout.NORTH);

        tViewContent = new JTree(tnContent);

        tViewContent.getSelectionModel().addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tViewContent.getLastSelectedPathComponent();
            updateViewContent(node);
        });

        JScrollPane scrollPane = new JScrollPane(tViewContent);
        pnlContent.add(scrollPane, BorderLayout.CENTER);

// pnlIntroduction________________________________________________________

        JPanel pnlIntroduction = new JPanel(new BorderLayout());
        JLabel lblIntroduction = new JLabel(titles.get("SHtnIntroduction"));
        lblIntroduction.setForeground(Color.BLUE);
        lblIntroduction.setHorizontalAlignment(JLabel.CENTER);
        pnlIntroduction.add(lblIntroduction, BorderLayout.NORTH);
        pnlIntroduction.add(new JScrollPane(manualService.getIntroduction()),BorderLayout.CENTER);

// pnlImport________________________________________________________

        JPanel pnlImport = new JPanel(new BorderLayout());
        JLabel lblImport = new JLabel(titles.get("SHtnImport"));
        lblImport.setForeground(Color.BLUE);
        lblImport.setHorizontalAlignment(JLabel.CENTER);
        pnlImport.add(lblImport, BorderLayout.NORTH);
        pnlImport.add(new JScrollPane(manualService.getImport()), BorderLayout.CENTER);

// pnlExtract________________________________________________________

        JPanel pnlExtract = new JPanel(new BorderLayout());
        JLabel lblExtract = new JLabel(titles.get("SHtnExtract"));
        lblExtract.setForeground(Color.BLUE);
        lblExtract.setHorizontalAlignment(JLabel.CENTER);
        pnlExtract.add(lblExtract, BorderLayout.NORTH);
        pnlExtract.add(new JScrollPane(manualService.getExtract()), BorderLayout.CENTER);

// pnlAdjustment________________________________________________________

        JPanel pnlAdjustment = new JPanel(new BorderLayout());
        JLabel lblAdjust = new JLabel(titles.get("SHtnAdjustment"));
        lblAdjust.setForeground(Color.BLUE);
        lblAdjust.setHorizontalAlignment(JLabel.CENTER);
        pnlAdjustment.add(lblAdjust, BorderLayout.NORTH);
        pnlAdjustment.add(new JScrollPane(manualService.getAdjustment()), BorderLayout.CENTER);

// pnlMainMenu________________________________________________________

        JPanel pnlMainMenu = new JPanel(new BorderLayout());
        JLabel lblMainMenu = new JLabel(titles.get("SHtnMainMenu"));
        lblMainMenu.setForeground(Color.BLUE);
        lblMainMenu.setHorizontalAlignment(JLabel.CENTER);
        pnlMainMenu.add(lblMainMenu, BorderLayout.NORTH);
        pnlMainMenu.add(new JScrollPane(manualService.getMainMenu()), BorderLayout.CENTER);

// pnlToolbarDemo_____________________________________________________

        pnlToolbarDemo = new JPanel();
        pnlToolbarDemo.setLayout(new BorderLayout());

        tb = new JToolBar();

        JButton btnNew = new JButton(new ImageIcon("images/new.png"));
        btnNew.setToolTipText(titles.get("MWbtnNewTT"));
        btnNew.addActionListener(e -> updateToolbarDemo(manualService.getToolbarNew()));

        JButton btnOpen = new JButton(new ImageIcon("images/open.png"));
        btnOpen.setToolTipText(titles.get("MWbtnOpenTT"));
        btnOpen.addActionListener(e -> updateToolbarDemo(manualService.getToolbarOpen()));

        JButton btnImport = new JButton(new ImageIcon("images/import.png"));
        btnImport.setToolTipText(titles.get("MWbtnImportTT"));
        btnImport.addActionListener(e -> updateToolbarDemo(manualService.getToolbarImport()));

        JButton btnSave = new JButton(new ImageIcon("images/save.png"));
        btnSave.setToolTipText(titles.get("MWbtnSaveTT"));
        btnSave.addActionListener(e -> updateToolbarDemo(manualService.getToolbarSave()));

        JButton btnRun = new JButton(new ImageIcon("images/run.png"));
        btnRun.setToolTipText(titles.get("MWbtnRunTT"));
        btnRun.addActionListener(e -> updateToolbarDemo(manualService.getToolbarRun()));


        JButton btnView = new JButton(new ImageIcon("images/view.png"));
        btnView.setToolTipText(titles.get("MWbtnVewTT"));
        btnView.addActionListener(e -> updateToolbarDemo(manualService.getToolbarView()));

        JButton btnLoadCat = new JButton(new ImageIcon("images/database.png"));
        btnLoadCat.setToolTipText(titles.get("MWbtnLoadCatTT"));
        btnLoadCat.addActionListener(e -> updateToolbarDemo(manualService.getToolbarLoadCat()));

        JLabel lblCatalog = new JLabel(titles.get("MWlblCatalog"));
        lblCatalog.setToolTipText(titles.get("MWlblCatalogTT"));
        lblCatalog.setEnabled(true);
        lblCatalog.setBorder(BorderFactory.createBevelBorder(1));

        tb.add(btnNew);
        tb.add(btnOpen);
        tb.add(btnImport);
        tb.add(btnSave);
        tb.addSeparator();
        tb.add(btnRun);
        tb.add(btnView);
        tb.add(btnLoadCat);
        tb.addSeparator();
        tb.add(lblCatalog);
        tb.setFloatable(false);
        tb.setBorder(BorderFactory.createEtchedBorder());

        pnlToolbarDemo.add(tb, BorderLayout.NORTH);
        spToolbarDemo = new JScrollPane(manualService.getToolbarDemo());
        pnlToolbarDemo.add(spToolbarDemo, BorderLayout.CENTER);

// pnlToolbar________________________________________________________

        JPanel pnlToolbar = new JPanel(new BorderLayout());
        JLabel lblToolbar = new JLabel(titles.get("SHtnToolbar"));
        lblToolbar.setForeground(Color.BLUE);
        lblToolbar.setHorizontalAlignment(JLabel.CENTER);
        pnlToolbar.add(lblToolbar, BorderLayout.NORTH);
        pnlToolbar.add(pnlToolbarDemo, BorderLayout.CENTER);

// pnlMeasurements________________________________________________________

        JPanel pnlMeasurements = new JPanel(new BorderLayout());
        JLabel lblMeasurements = new JLabel(titles.get("SHtnMeasurements"));
        lblMeasurements.setForeground(Color.BLUE);
        lblMeasurements.setHorizontalAlignment(JLabel.CENTER);
        pnlMeasurements.add(lblMeasurements, BorderLayout.NORTH);
        pnlMeasurements.add(new JScrollPane(manualService.getMeasurements()), BorderLayout.CENTER);

// pnlPolygon________________________________________________________

        JPanel pnlPolygon = new JPanel(new BorderLayout());
        JLabel lblPolygon = new JLabel(titles.get("SHtnPolygon"));
        lblPolygon.setForeground(Color.BLUE);
        lblPolygon.setHorizontalAlignment(JLabel.CENTER);
        pnlPolygon.add(lblPolygon, BorderLayout.NORTH);
        pnlPolygon.add(new JScrollPane(manualService.getPolygon()), BorderLayout.CENTER);

// pnlOptions________________________________________________________

        JPanel pnlOptions = new JPanel(new BorderLayout());
        JLabel lblOptions = new JLabel(titles.get("SHtnOptions"));
        lblOptions.setForeground(Color.BLUE);
        lblOptions.setHorizontalAlignment(JLabel.CENTER);
        pnlOptions.add(lblOptions, BorderLayout.NORTH);
        pnlOptions.add(new JScrollPane(manualService.getSettings()), BorderLayout.CENTER);

// pnlFiles________________________________________________________

        JPanel pnlFiles = new JPanel(new BorderLayout());
        JLabel lblFiles = new JLabel(titles.get("SHtnFiles"));
        lblFiles.setForeground(Color.BLUE);
        lblFiles.setHorizontalAlignment(JLabel.CENTER);
        pnlFiles.add(lblFiles, BorderLayout.NORTH);
        pnlFiles.add(new JScrollPane(manualService.getFiles()), BorderLayout.CENTER);


// pnlViewContent_____________________________________________________

            pnlViewContent = new JPanel(cardLayout);
            pnlViewContent.add(pnlContent,"SHtnContent");
            pnlViewContent.add(pnlIntroduction,"SHtnIntroduction");
            pnlViewContent.add(pnlImport,"SHtnImport");
            pnlViewContent.add(pnlExtract,"SHtnExtract");
            pnlViewContent.add(pnlAdjustment,"SHtnAdjust");
            pnlViewContent.add(pnlMainMenu,"SHtnMainMenu");
            pnlViewContent.add(pnlToolbar,"SHtnToolbar");
            pnlViewContent.add(pnlMeasurements,"SHtnMeasurements");
            pnlViewContent.add(pnlPolygon,"SHtnPolygon");
            pnlViewContent.add(pnlOptions,"SHtnOptions");
            pnlViewContent.add(pnlFiles,"SHtnFiles");

        add(pnlViewContent, BorderLayout.CENTER);

        setResizable(true);
        setVisible(true);

// The END of Constructor
    }


    /**
     * Updates content of ShowHelp
     * @param node DefaultMutableTreeNode
     */
    private void updateViewContent(DefaultMutableTreeNode node) {
        if (node == null || node == tnContent) cardLayout.show(pnlViewContent, "SHtnContent");
        if (node == tnIntroduction) cardLayout.show(pnlViewContent, "SHtnIntroduction");
        if (node == tnImport) cardLayout.show(pnlViewContent, "SHtnImport");
        if (node == tnExtract) cardLayout.show(pnlViewContent, "SHtnExtract");
        if (node == tnAdjustment) cardLayout.show(pnlViewContent, "SHtnAdjust");
        if (node == tnMainMenu) cardLayout.show(pnlViewContent, "SHtnMainMenu");
        if (node == tnToolbar) cardLayout.show(pnlViewContent, "SHtnToolbar");
        if (node == tnMeasurements) cardLayout.show(pnlViewContent, "SHtnMeasurements");
        if (node == tnPolygon) cardLayout.show(pnlViewContent, "SHtnPolygon");
        if (node == tnFiles) cardLayout.show(pnlViewContent, "SHtnFiles");
        if (node == tnOptions) cardLayout.show(pnlViewContent, "SHtnOptions");
    }

    /**
     * Updates content ToolbarDemo
     * @param textArea JTextArea
     */
    private void updateToolbarDemo(JTextArea textArea) {
        pnlToolbarDemo.remove(spToolbarDemo);
        spToolbarDemo = new JScrollPane(textArea);
        pnlToolbarDemo.add(spToolbarDemo, BorderLayout.CENTER);
        revalidate();

    }

// The END of Class ShowHelp
}
