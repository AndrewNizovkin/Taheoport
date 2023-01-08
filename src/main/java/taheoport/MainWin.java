package taheoport;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

public class MainWin extends JFrame{
    private static TahEditorFocusTransversalPolicy focusPolicy;
    private JTabbedPane tpMain;
    private final JPanel pnlMeasurements;
    private final JPanel pnlPolygon;
    private Catalog catalog;
    private SurveyProject surveyProject;
    private PolygonProject polygonProject;
    private ExtractProject extractProject;
    private String pathWorkDir;
    private final Options options;
    private HashMap<String, String> titles;
    private boolean isCatalog;
    private final int wMain; // main frame width
    private final int hMain; // main frame height
    private final JMenu mFile;
    private final JMenu mTools;
    private final JMenu mHelp;
    private final JMenu mImport; // main frame menu
    private final JMenuItem fNew;
    private final JMenuItem fOpen;
    private final JMenuItem fSave;
    private final JMenuItem fSaveAs;
    private final JMenuItem fExit; // File  menu items
    private final JMenuItem tOptions; // Options menu items
    private final JMenuItem tRun;
    private final JMenuItem tLoadCat;
    private final JMenuItem tView;
    private final JMenuItem tUpdate;
    private final JMenuItem tExtractPol; // Tools menu items
    private final JMenuItem hAbout;
    private final JMenuItem hHelp; // File menu items
    private final JButton btnNew;
    private final JButton btnOpen;
    private final JButton btnSave;
    private final JButton btnRun;
    private final JButton btnView;
    private final JButton btnLoadCat;
    private final JButton btnImport;
    private SurveyEditorStandart tahEditor;
    private PolygonEditorStandart theoEditor;
    private final JLabel lblCatalog;
    private JPopupMenu ppImport;


    /**
     * Constructor
     */
    public MainWin() {
        super("Taheoport");

/*
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
*/
        setUIFont (new javax.swing.plaf.FontUIResource(Font.DIALOG, Font.PLAIN, 12));
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        options = new Options(this);
        titles = new Shell(this).getTitles();
        pathWorkDir = options.getPathWorkDir();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image im = kit.getImage("images/teo.png");
        this.setIconImage(im);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        isCatalog = false;
//        wMain = screenSize.width / 3;
//        hMain = screenSize.height / 6 * 4;
        wMain = 640;
        hMain = 650;

        // main frame menu bar
        JMenuBar mbr = new JMenuBar();

//mFile_______________________________________________________________

        mFile = new JMenu(titles.get("MWmFile"));
            fNew = new JMenuItem(titles.get("MWfNew"));
            fNew.setIcon(new ImageIcon("images/new.png"));
            fNew.addActionListener(e -> newFile());

            fOpen  = new JMenuItem(titles.get("MWfOpen"), new ImageIcon("images/open.png"));
            fOpen.addActionListener(e -> openFile());
            fOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));

// mImport______________________________________________________

            mImport = new JMenu(titles.get("MWmImport"));
        JMenuItem iLeica = new JMenuItem("Leica");
                iLeica.addActionListener(e -> importLeica());

        JMenuItem iNicon = new JMenuItem("Nicon");
                iNicon.addActionListener(e -> importNicon());

        JMenuItem iTopcon = new JMenuItem("Topcon");
                iTopcon.addActionListener(e -> importTopcon());

            mImport.add(iLeica);
            mImport.add(iNicon);
            mImport.add(iTopcon);

            fSave = new JMenuItem(titles.get("MWfSave"), new ImageIcon("images/save.png"));
            fSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
            fSave.addActionListener(e -> save());
            fSave.setEnabled(false);

            fSaveAs = new JMenuItem(titles.get("MWfSaveAs"));
            fSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_DOWN_MASK));
            fSaveAs.addActionListener(e -> saveAs());
            fSaveAs.setEnabled(false);

            fExit = new JMenuItem(titles.get("MWfExit"));
            fExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
            fExit.addActionListener(e -> System.exit(0));

        mFile.add(fNew);
        mFile.add(fOpen);
        mFile.add(mImport);
        mFile.add(fSave);
        mFile.add(fSaveAs);
        mFile.addSeparator();
        mFile.add(fExit);
        mbr.add(mFile);

//mTools_________________________________________________________

        mTools = new JMenu(titles.get("MWmTools"));

            tLoadCat = new JMenuItem(titles.get("MWtLoadCat"), new ImageIcon("images/database.png"));
            tLoadCat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
            tLoadCat.setEnabled(false);
            tLoadCat.addActionListener(e -> loadCatalog());

            tUpdate = new JMenuItem(titles.get("MWtUpdate"));
            tUpdate.setEnabled(false);
            tUpdate.addActionListener(e -> {
                switch (tpMain.getSelectedIndex()) {
                    case 0 -> {
                        updateSurveyStations();
                        reloadTahEditor();
                    }
                    case 1 -> {
                        updateTheoStations();
                        reloadTheoEditor();
                    }
                }
            });


            tRun = new JMenuItem(titles.get("MWtRun"), new ImageIcon("images/run.png"));
            tRun.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
            tRun.addActionListener(e -> processSourceData());
            tRun.setEnabled(false);

            tView = new JMenuItem(titles.get("MWtView"), new ImageIcon("images/view.png"));
            tView.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
            tView.setEnabled(false);
            tView.addActionListener(e -> {
                switch (tpMain.getSelectedIndex()) {
                    case 0 -> {
                        surveyProject.processSourceData();
                        new ShowViewResults(this);
                    }
                    case 1 -> {
                        processSourceData();
                        if (polygonProject.getPerimeter() > 0.0) {
                            new ShowViewAdjustment(this);
                        }

                    }

                }
            });

            tExtractPol = new JMenuItem(titles.get("MWtExtractPol"));
            tExtractPol.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
            tExtractPol.setEnabled(false);
            tExtractPol.addActionListener(e -> extractPol());

            tOptions = new JMenuItem(titles.get("MWtOptions"));
//            tOptions.setFont(fontMain);
            tOptions.setEnabled(true);
            tOptions.addActionListener(e -> new ShowOptions(this));


        mTools.add(tLoadCat);
        mTools.add(tUpdate);
        mTools.add(tRun);
        mTools.add(tView);
        mTools.add(tExtractPol);
        mTools.add(tOptions);
        mbr.add(mTools);

// mHelp___________________________________________________________

        mHelp = new JMenu(titles.get("MWmHelp"));

            hAbout = new JMenuItem(titles.get("MWhAbout"));
            hAbout.addActionListener(e -> new ShowAbout(this));
            hAbout.setEnabled(true);

            hHelp = new JMenuItem(titles.get("MWhHelp"));
            hHelp.addActionListener(e -> new ShowHelp(this));
            hHelp.setEnabled(true);

        mHelp.add(hAbout);
        mHelp.add(hHelp);
        mbr.add(mHelp);
        this.setJMenuBar(mbr);


//tb______________________________________________________

        // toolbar
        JToolBar tb = new JToolBar();

            btnNew = new JButton(new ImageIcon("images/new.png"));
            btnNew.setToolTipText(titles.get("MWbtnNewTT"));
            btnNew.addActionListener(e -> newFile());


            btnOpen = new JButton(new ImageIcon("images/open.png"));
            btnOpen.setToolTipText(titles.get("MWbtnOpenTT"));
            btnOpen.addActionListener(e -> openFile());

            btnImport = new JButton(new ImageIcon("images/import.png"));
            btnImport.setToolTipText(titles.get("MWbtnImportTT"));
            btnImport.addActionListener(e -> ppImport.show(this, btnImport.getX(), btnImport.getY() + 20));

        ppImport = new JPopupMenu();
        JMenuItem ppiLeica = new JMenuItem("Leica");
        ppiLeica.addActionListener(e -> importLeica());

        JMenuItem ppiNicon = new JMenuItem("Nicon");
        ppiNicon.addActionListener(e -> importNicon());

        JMenuItem ppiTopcon = new JMenuItem("Topcon");
        ppiTopcon.addActionListener(e -> importTopcon());


        ppImport.add(ppiLeica);
        ppImport.add(ppiNicon);
        ppImport.add(ppiTopcon);
        add(ppImport);

            btnSave = new JButton(new ImageIcon("images/save.png"));
            btnSave.addActionListener(e -> save());
            btnSave.setToolTipText(titles.get("MWbtnSaveTT"));
            btnSave.setEnabled(false);

            btnRun = new JButton(new ImageIcon("images/run.png"));
            btnRun.setToolTipText(titles.get("MWbtnRunTT"));
            btnRun.addActionListener(e -> processSourceData());
            btnRun.setEnabled(false)
            ;
            btnView = new JButton(new ImageIcon("images/view.png"));
            btnView.setToolTipText(titles.get("MWbtnVewTT"));
            btnView.setEnabled(false);
            btnView.addActionListener(e -> {
                switch (tpMain.getSelectedIndex()) {
                    case 0 -> {
                        surveyProject.processSourceData();
                        new ShowViewResults(this);
                    }
                    case 1 -> {
//                        System.out.println("action from pnlPolygonometry");
                        processSourceData();
                        if (polygonProject.getPerimeter() > 0.0) {
                            new ShowViewAdjustment(this);
                        }
                    }
                }
            });

            btnLoadCat = new JButton(new ImageIcon("images/database.png"));
            btnLoadCat.setToolTipText(titles.get("MWbtnLoadCatTT"));
            btnLoadCat.setEnabled(false);
            btnLoadCat.addActionListener(e -> loadCatalog());

        lblCatalog = new JLabel(titles.get("MWlblCatalog"));
        lblCatalog.setToolTipText(titles.get("MWlblCatalogTT"));
        lblCatalog.setEnabled(false);
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

        this.add(tb, BorderLayout.NORTH);

//tpMain_pnlTaheometry_pnlPolygonometry_________________________________________

        tpMain = new JTabbedPane();
//        tpMain.setFont(fontMain);

        pnlMeasurements = new JPanel();
        pnlMeasurements.setLayout(new BorderLayout());

        pnlPolygon = new JPanel();
        pnlPolygon.setLayout(new BorderLayout());

        tpMain.add(pnlMeasurements);
        tpMain.add(pnlPolygon);
        tpMain.setTitleAt(0, titles.get("MWtpMain0"));
        tpMain.setTitleAt(1,titles.get("MWtpMain1"));
        tpMain.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tpMain.getSelectedIndex() == 0) {
                    if (surveyProject != null) {
                        setTitle("Taheoport: " + surveyProject.getAbsoluteTahPath());
                        setControlsOn();
                    } else {
                        setTitle("Taheoport");
                        setControlsOff();
                    }
                    mImport.setEnabled(true);
                    btnImport.setEnabled(true);
                }
                if (tpMain.getSelectedIndex() == 1) {
                    if (polygonProject != null) {
                        setTitle("Taheoport: " + polygonProject.getAbsolutePolPath());
                        setControlsOn();
                    } else {
                        setTitle("Taheoport");
                        setControlsOff();
                    }
                    mImport.setEnabled(false);
                    btnImport.setEnabled(false);
                }
            }
        });
        add(tpMain);
        setBounds((screenSize.width - wMain) / 2, (screenSize.height - hMain) / 2, wMain, hMain);
        setResizable(false);

        polygonProject = new PolygonProject(this);
        polygonProject.addStation(new PolygonStation());
        reloadTheoEditor();
        setControlsOn();
        setTitle("Taheoport: " + polygonProject.getAbsolutePolPath());
        theoEditor.setFocusTable();


        surveyProject = new SurveyProject(this);
        SurveyStation st = surveyProject.addStation();
        st.addPicket(st);
        reloadTahEditor();
        setControlsOn();
        tahEditor.setFocusStations();


        setVisible(true);

// The END of Constructor MainWin_____________________________________________________________
    }


    public static void main(String [] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWin();
            }
        });
//        new MainWin();
    }

    /**
     * gets options
     * @return Options
     */
    public Options getOptions() {
        return options;
    }

    /**
     * sets options
     */
    public   void setOptions() {
        pathWorkDir = options.getPathWorkDir();
    }

    /**
     * gets width of MainWin
     * @return wMain
     */
    public int getWidthMain() {
        return wMain;
    }

    /**
     * gets height
     * @return hMain
     */
    public int getHeightMain() {
        return hMain;
    }

    /**
     * Return sp
     * @return SurveyProject sp
     */
    public SurveyProject getSurveyProject() {
        return surveyProject;
    }

    /**
     * gets HashMap titles
     * @return titles
     */
    public HashMap<String, String> getTitles() {
        return titles;
    }

    /**
     * return theoProject
     * @return TheoProject theoProject
     */
    public PolygonProject getPolygonProject() {
        return polygonProject;
    }

    public ExtractProject getExtractProject() {
        return extractProject;
    }

    /**
     * Return catalog coordinates
     * @return catalog
     */
    public Catalog getCatalog() {
        return catalog;
    }

    /**
     * Return is Catalog
     * @return Boolean
     */
    public Boolean isCatalog() {
        return isCatalog;
    }

    public String getPathWorkDir() {
        return pathWorkDir;
    }

    /**
     * Translate interface
     */
    public void translate() {
        titles = new Shell(this).getTitles();
        mFile.setText(titles.get("MWmFile"));
        mTools.setText(titles.get("MWmTools"));
        mHelp.setText(titles.get("MWmHelp"));
        mImport.setText(titles.get("MWmImport"));
        fNew.setText(titles.get("MWfNew"));
        fOpen.setText(titles.get("MWfOpen"));
        fSave.setText(titles.get("MWfSave"));
        fSaveAs.setText(titles.get("MWfSaveAs"));
        fExit.setText(titles.get("MWfExit"));
        tLoadCat.setText(titles.get("MWtLoadCat"));
        tUpdate.setText(titles.get("MWtUpdate"));
        tRun.setText(titles.get("MWtRun"));
        tView.setText(titles.get("MWtView"));
        tExtractPol.setText(titles.get("MWtExtractPol"));
        tOptions.setText(titles.get("MWtOptions"));
        hAbout.setText(titles.get("MWhAbout"));
        hHelp.setText(titles.get("MWhHelp"));
        btnNew.setToolTipText(titles.get("MWbtnNewTT"));
        btnOpen.setToolTipText(titles.get("MWbtnOpenTT"));
        btnImport.setToolTipText(titles.get("MWbtnImportTT"));
        btnSave.setToolTipText(titles.get("MWbtnSaveTT"));
        btnRun.setToolTipText(titles.get("MWbtnRunTT"));
        btnView.setToolTipText(titles.get("MWbtnVewTT"));
        btnLoadCat.setToolTipText(titles.get("MWbtnLoadCatTT"));
        lblCatalog.setToolTipText(titles.get("MWlblCatalogTT"));
        if (!isCatalog) {
            lblCatalog.setText(titles.get("MWlblCatalog"));
        }
        tpMain.setTitleAt(0, titles.get("MWtpMain0"));
        tpMain.setTitleAt(1,titles.get("MWtpMain1"));
        if (tahEditor != null) {
            tahEditor.translate();
        }
        if (theoEditor != null) {
            reloadTheoEditor();
//            theoEditor.translate();
        }



        revalidate();
    }

    /**
     * import from Leica
     */
    private void importLeica() {
        switch (tpMain.getSelectedIndex()) {
            case 0 -> {
                LinkedList <String>  llLeicaList = new MyChooser(this).readTextFile(pathWorkDir, "gsi", titles.get("MWopenFileTitle"));
                if (llLeicaList != null) {
                    surveyProject = new SurveyProject(this).loadLeicaList(llLeicaList);
//                surveyProject.setPath(pathWorkDir);
                    reloadTahEditor();
                    setControlsOn();
                    surveyProject.setAbsoluteTahPath(new MyChooser(this).writeTextFile(pathWorkDir, "tah", "Write Tah", surveyProject.getTahList()));
                    setTitle("Taheoport: " + surveyProject.getAbsoluteTahPath());
                    tahEditor.setFocusStations();
                }
            }
            case 1 -> System.out.println("action from pnlPolygonometry");
        }

    }

    /**
     * import from Nicon
     */
    private void importNicon() {
        switch (tpMain.getSelectedIndex()) {
            case 0 -> {
                LinkedList <String>  llNiconList = new MyChooser(this).readTextFile(pathWorkDir, "raw", titles.get("MWopenFileTitle"));
                if (llNiconList != null) {
                surveyProject = new SurveyProject(this).loadNiconList(llNiconList);
//                surveyProject.setPath(pathWorkDir);
//                if (surveyProject.loadNiconList() != null) {
                    reloadTahEditor();
                    setControlsOn();
                    surveyProject.setAbsoluteTahPath(new MyChooser(this).writeTextFile(pathWorkDir, "tah", "write Tah", surveyProject.getTahList()));
                    setTitle("Taheoport: " + surveyProject.getAbsoluteTahPath());
                    tahEditor.setFocusStations();
                }
            }
            case 1 -> System.out.println("action from pnlPolygonometry");
        }
    }

    /**
     * import from Topcon
     */
    private void importTopcon() {
        switch (tpMain.getSelectedIndex()) {
            case 0 -> {
                LinkedList<String> llTopconList = new MyChooser(this).readTextFile(pathWorkDir, "txt", titles.get("MWopenFileTitle"));
                if (llTopconList != null) {
                    surveyProject = new SurveyProject(this).loadTopconList(llTopconList);
                    reloadTahEditor();
                    setControlsOn();
                    surveyProject.setAbsoluteTahPath(new MyChooser(this).writeTextFile(pathWorkDir, "tah", "write Tah", surveyProject.getTahList()));
                    setTitle("Taheoport: " + surveyProject.getAbsoluteTahPath());
                    tahEditor.setFocusStations();
                }
            }
            case 1 -> System.out.println("action from pnlPolygonometry");
        }
    }

    /**
     * Create new sp with one Station with one Picket
     */
    private void newFile() {
        switch (tpMain.getSelectedIndex()) {
            case 0 -> {
                surveyProject = new SurveyProject(this);
                SurveyStation st = surveyProject.addStation();
                st.addPicket(st);
                reloadTahEditor();
                setControlsOn();
                tahEditor.setFocusStations();
            }
            case 1 -> {
                polygonProject = new PolygonProject(this);
                polygonProject.addStation(new PolygonStation());
                reloadTheoEditor();
                setControlsOn();
                setTitle("Taheoport: " + polygonProject.getAbsolutePolPath());
                theoEditor.setFocusTable();
            }
        }
    }

    /**
     * open Tah file
     */
    private void openFile() {
        switch (tpMain.getSelectedIndex()) {
            case 0 -> {
                LinkedList<String> llTahList = new MyChooser(this).readTextFile(pathWorkDir, "tah", titles.get("MWopenFileTitle"));
                if (llTahList != null) {
                    surveyProject = new SurveyProject(this).loadTahList(llTahList);
                    reloadTahEditor();
                    setControlsOn();
                    tahEditor.setFocusStations();
            }
            }
            case 1 -> {
                LinkedList<String> llPolList = new MyChooser(this).readTextFile(pathWorkDir, "pol", titles.get("MWopenFileTitle"));
                if (llPolList != null) {
                    polygonProject = new PolygonProject(this).loadPolList(llPolList);
                    reloadTheoEditor();
                    setControlsOn();
                    setTitle("Taheoport: " + polygonProject.getAbsolutePolPath());
                    theoEditor.setFocusTable();
                }
            }
        }
    }

    /**
     * extracts llPolList from this.surveyProject and open new TheoProject
     */
    private void extractPol() {
        if (surveyProject != null) {
            if (surveyProject.haveTheo()) {
                surveyProject.processSourceData();
                extractProject = new ExtractProject(this);
                polygonProject = new PolygonProject(this).loadPolList(extractProject.extractTheoProject());
                tpMain.setSelectedIndex(1);
                reloadTheoEditor();
                setControlsOn();
                setTitle("Taheoport: " + polygonProject.getAbsolutePolPath());
                theoEditor.setFocusTable();
                new ShowViewExtractPol(this);
            } else {
                JOptionPane.showMessageDialog(this,"Недостаточно данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * save sp <SurveyProject> to Tah file to AbsoluteTahPath if it exists.
     * set absoluteTahPath
     */
    private void save() {
        switch (tpMain.getSelectedIndex()) {
            case 0 -> {
                if (surveyProject.getAbsoluteTahPath().equals("")) {
                    String s = new MyChooser(this).writeTextFile(pathWorkDir, "tah", "Write Tah", surveyProject.getTahList());
                    if (s != null) {
                        surveyProject.setAbsoluteTahPath(s);
                    }
                } else {
                    surveyProject.setAbsoluteTahPath(new MyChooser(this).writeTextFile(surveyProject.getAbsoluteTahPath(), surveyProject.getTahList()));
                }
                setTitle("Taheoport: " + surveyProject.getAbsoluteTahPath());
            }
            case 1 -> {
                if (polygonProject.getAbsolutePolPath().equals("")) {
                    String s = new MyChooser(this).writeTextFile(pathWorkDir, "pol", "Write *.pol", polygonProject.getPolList());
                    if (s != null) {
                        polygonProject.setAbsolutePolPath(s);
                    }
                } else {
                    polygonProject.setAbsolutePolPath(new MyChooser(this).writeTextFile(polygonProject.getAbsolutePolPath(), polygonProject.getPolList()));
                }
                setTitle("Taheoport: " + polygonProject.getAbsolutePolPath());
            }
        }
    }

    /**
     * save sp <SurveyProject> to Tah file
     * set absoluteTahPath
     */
    private void saveAs() {
        switch (tpMain.getSelectedIndex()) {
            case 0 -> {
                String s = new MyChooser(this).writeTextFile(pathWorkDir, "tah", titles.get("MWsaveTahTitle"), surveyProject.getTahList());
                if (s != null) {
                    surveyProject.setAbsoluteTahPath(s);
                    setTitle("Taheoport: " + surveyProject.getAbsoluteTahPath());
                }
            }
            case 1 -> {
                String s = new MyChooser(this).writeTextFile(pathWorkDir, "pol", titles.get("MWsavePolTitle"), polygonProject.getPolList());
                if (s != null) {
                    polygonProject.setAbsolutePolPath(s);
                    setTitle("Taheoport: " + polygonProject.getAbsolutePolPath());
                }
            }
        }
    }

    /**
     * Writes an *.dat file with the coordinates of the pickets to disk
     */
    private void processSourceData() {
        switch (tpMain.getSelectedIndex()) {
            case 0 -> {
                surveyProject.processSourceData();
//                surveyProject.writeDat();
                new MyChooser(this).writeTextFile(pathWorkDir, "dat", "Write DAT", surveyProject.getPicketsList());
            }
//                new MyChooser().writeTextFile(pathWorkDir, "txt", "Write TaheoReport", surveyProject.getListReport());
            case 1 -> {
                polygonProject.processSourceData();
                reloadTheoEditor();
                theoEditor.setBindings();
            }
        }
    }

    /**
     * Reload tahEditorStandart
     */
    private void reloadTahEditor() {
        if (tahEditor != null) {
            pnlMeasurements.remove(tahEditor);
        }
        tahEditor = new SurveyEditorStandart(this, 0);
        pnlMeasurements.add(tahEditor);
        setTitle("Taheoport: " + surveyProject.getAbsoluteTahPath());
        setFocusTraversalPolicy(new TahEditorFocusTransversalPolicy(tahEditor.getOrder()));
        revalidate();
    }

    private void reloadTheoEditor() {
        if (theoEditor != null) {
            pnlPolygon.remove(theoEditor);
        }
        theoEditor = new PolygonEditorStandart(this);
        pnlPolygon.add(theoEditor);
        revalidate();

    }

    /**
     * Sets setEnabled(true) for GUI components
     */
    private void setControlsOn() {
        fSave.setEnabled(true);
        fSaveAs.setEnabled(true);
        tExtractPol.setEnabled(true);
        btnSave.setEnabled(true);
        btnRun.setEnabled(true);
        tRun.setEnabled(true);
        //actionLoadCatalog.setEnabled(true);
        btnLoadCat.setEnabled(true);
        btnView.setEnabled(true);
        tLoadCat.setEnabled(true);
        tView.setEnabled(true);
//        tUpdate.setEnabled(true);
//        tahEditorStandart.setFocusStations();

    }

    /**
     * Sets setEnabled(false) for GUI components
     */
    private void setControlsOff() {
        fSave.setEnabled(false);
        fSaveAs.setEnabled(false);
        tExtractPol.setEnabled(false);
        btnSave.setEnabled(false);
        btnRun.setEnabled(false);
        tRun.setEnabled(false);
//        actionLoadCatalog.setEnabled(false);
        btnLoadCat.setEnabled(false);
        btnView.setEnabled(false);
        tLoadCat.setEnabled(false);
        tView.setEnabled(false);
    }

    /**
     * Updates Stations coordinates from current catalog
     */
    private void updateSurveyStations() {

        if (catalog != null & surveyProject != null) {
            int q = 0;
            for (int i = 0; i < surveyProject.sizeStations(); i++) {
                for (int j = 0; j < catalog.getSizeCatalog(); j++) {
                    if (surveyProject.getStation(i).getName().equals(catalog.get(j).getName())) {
                        surveyProject.getStation(i).setName(catalog.get(j).getName());
                        surveyProject.getStation(i).setX(catalog.get(j).getX());
                        surveyProject.getStation(i).setY(catalog.get(j).getY());
                        surveyProject.getStation(i).setZ(catalog.get(j).getZ());
                        q++;
                    }
                    if (surveyProject.getStation(i).getNameOr().equals(catalog.get(j).getName())) {
                        surveyProject.getStation(i).setNameOr(catalog.get(j).getName());
                        surveyProject.getStation(i).setxOr(catalog.get(j).getX());
                        surveyProject.getStation(i).setyOr(catalog.get(j).getY());
                        surveyProject.getStation(i).setzOr(catalog.get(j).getZ());
                        q++;
                    }
                }
            }
            JOptionPane.showMessageDialog(this, q + titles.get("MWupdateMessage"), titles.get("MWupdateMessageTitle"), JOptionPane.INFORMATION_MESSAGE);

        }
//        return surveyProject;
    }

    private void updateTheoStations() {
        int q = 0;
        for (int i = 0; i < polygonProject.getSizeTheoStations(); i++) {
            for (int j = 0; j < catalog.getSizeCatalog(); j++) {
                if (polygonProject.getTheoStation(i).getName().equals(catalog.get(j).getName()) & polygonProject.getTheoStation(i).getStatus()) {
                    polygonProject.getTheoStation(i).setName(catalog.get(j).getName());
                    polygonProject.getTheoStation(i).setX(catalog.get(j).getX());
                    polygonProject.getTheoStation(i).setY(catalog.get(j).getY());
                    polygonProject.getTheoStation(i).setZ(catalog.get(j).getZ());
                    q++;
                }
            }
        }
        JOptionPane.showMessageDialog(this, q + titles.get("MWupdateMessage"), titles.get("MWupdateMessageTitle"), JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This action load points coordinates from text file *.kat to sp (SurveyProject)
     */

    private void loadCatalog() {
            catalog = new Catalog().loadCatalogList(new MyChooser(this).readTextFile(pathWorkDir, "kat", titles.get("MWloadCatalogTitle")));
        if (catalog.getSizeCatalog() > 0) {
            lblCatalog.setEnabled(true);
            lblCatalog.setText(catalog.getAbsoluteCatalogPath());
            tUpdate.setEnabled(true);
            isCatalog = true;
            if(tahEditor != null) {
                tahEditor.getBtnStationName().setEnabled(true);
                tahEditor.getBtnOrName().setEnabled(true);
            }
        } else {
            lblCatalog.setEnabled(false);
            lblCatalog.setText("Каталог не установлен");
            isCatalog = false;
        }
    }

    /**
     * Sets UIFont
     * @param f Font
     */
    private void setUIFont (javax.swing.plaf.FontUIResource f){
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get (key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put (key, f);
        }
    }

    /**
     * This class set new FocusTraversalPolicy
     */
    public static class TahEditorFocusTransversalPolicy extends FocusTraversalPolicy {
        private final Vector<Component> order;

        public TahEditorFocusTransversalPolicy(Vector<Component> order) {

            this.order = new Vector<Component>(order.size());
            this.order.addAll(order);
        }


        /**
         * Returns the Component that should receive the focus after aComponent.
         * aContainer must be a focus cycle root of aComponent or a focus traversal
         * policy provider.
         *
         * @param aContainer a focus cycle root of aComponent or focus traversal
         *                   policy provider
         * @param aComponent a (possibly indirect) child of aContainer, or
         *                   aContainer itself
         * @return the Component that should receive the focus after aComponent, or
         * null if no suitable Component can be found
         * @throws IllegalArgumentException if aContainer is not a focus cycle
         *                                  root of aComponent or a focus traversal policy provider, or if
         *                                  either aContainer or aComponent is null
         */
        @Override
        public Component getComponentAfter(Container aContainer, Component aComponent) {
            int idx = (order.indexOf(aComponent) + 1) % order.size();
            return order.get(idx);
        }

        /**
         * Returns the Component that should receive the focus before aComponent.
         * aContainer must be a focus cycle root of aComponent or a focus traversal
         * policy provider.
         *
         * @param aContainer a focus cycle root of aComponent or focus traversal
         *                   policy provider
         * @param aComponent a (possibly indirect) child of aContainer, or
         *                   aContainer itself
         * @return the Component that should receive the focus before aComponent,
         * or null if no suitable Component can be found
         * @throws IllegalArgumentException if aContainer is not a focus cycle
         *                                  root of aComponent or a focus traversal policy provider, or if
         *                                  either aContainer or aComponent is null
         */
        @Override
        public Component getComponentBefore(Container aContainer, Component aComponent) {
            int idx = order.indexOf(aComponent) - -1;
            return order.get(idx);
        }

        /**
         * Returns the first Component in the traversal cycle. This method is used
         * to determine the next Component to focus when traversal wraps in the
         * forward direction.
         *
         * @param aContainer the focus cycle root or focus traversal policy provider
         *                   whose first Component is to be returned
         * @return the first Component in the traversal cycle of aContainer,
         * or null if no suitable Component can be found
         * @throws IllegalArgumentException if aContainer is null
         */
        @Override
        public Component getFirstComponent(Container aContainer) {
            return order.get(0);
        }

        /**
         * Returns the last Component in the traversal cycle. This method is used
         * to determine the next Component to focus when traversal wraps in the
         * reverse direction.
         *
         * @param aContainer the focus cycle root or focus traversal policy
         *                   provider whose last Component is to be returned
         * @return the last Component in the traversal cycle of aContainer,
         * or null if no suitable Component can be found
         * @throws IllegalArgumentException if aContainer is null
         */
        @Override
        public Component getLastComponent(Container aContainer) {
            return order.lastElement();
        }

        /**
         * Returns the default Component to focus. This Component will be the first
         * to receive focus when traversing down into a new focus traversal cycle
         * rooted at aContainer.
         *
         * @param aContainer the focus cycle root or focus traversal policy
         *                   provider whose default Component is to be returned
         * @return the default Component in the traversal cycle of aContainer,
         * or null if no suitable Component can be found
         * @throws IllegalArgumentException if aContainer is null
         */
        @Override
        public Component getDefaultComponent(Container aContainer) {
            return order.get(0);
        }
    }



}
