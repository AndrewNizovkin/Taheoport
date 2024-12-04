package taheoport.gui;

import taheoport.repository.PolygonRepository;
import taheoport.repository.SurveyRepository;
import taheoport.service.*;
import taheoport.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;
import java.util.List;

/**
 * This main window of program
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class MainWin extends JFrame{
//    private static TahEditorFocusTransversalPolicy focusPolicy;
    private final IOService ioService;
    private final ImportService importService;
    private final SurveyService surveyService;
    private final PolygonService polygonService;
    private final ExtractService extractService;
    private final CatalogService catalogService;
    private final SettingsService settingsService;
    private final Security security;
    private final JTabbedPane tpMain;
    private final JPanel pnlMeasurements;
    private final JPanel pnlPolygon;
    private Catalog catalog;
//    private SurveyRepository surveyRepository;
    private PolygonRepository polygonRepository;
//    private final String pathWorkDir;
    private final Settings settings;
    private HashMap<String, String> titles;
    private boolean isCatalog;
    private final int wMain;
    private final int hMain;
    private final JMenu mFile;
    private final JMenu mTools;
    private final JMenu mHelp;
    private final JMenu mImport;
    private final JMenuItem fNew;
    private final JMenuItem fOpen;
    private final JMenuItem fSave;
    private final JMenuItem fSaveAs;
    private final JMenuItem fExit;
    private final JMenuItem tOptions;
    private final JMenuItem tRun;
    private final JMenuItem tLoadCat;
    private final JMenuItem tView;
    private final JMenuItem tUpdate;
    private final JMenuItem tExtractPol;
    private final JMenuItem hAbout;
    private final JMenuItem hHelp;
    private final JButton btnNew;
    private final JButton btnOpen;
    private final JButton btnSave;
    private final JButton btnRun;
    private final JButton btnView;
    private final JButton btnLoadCat;
    private final JButton btnImport;
    private SurveyEditorStandart surveyEditor;
    private PolygonEditorStandart polygonEditor;
    private final JLabel lblCatalog;
    private JPopupMenu ppImport;


    /**
     * Constructor
     */
    public MainWin() {
        super("Taheoport");
        ioService = new IOServiceDefault(this);
        importService = new ImportServiceDefault(this);
        surveyService = new SurveyServiceDefault(this);
        polygonService = new PolygonServiceDefault(this);
        extractService = new ExtractServiceDefault(this);
        catalogService = new CatalogServiceDefault(this);
        settingsService = new SettingsServiceDefault(this);
        security = new SecurityImpl();

        settings = new Settings();
        settingsService.loadOptions();
        titles = new Shell(this).getTitles();
//        pathWorkDir = settings.getPathWorkDir();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        isCatalog = false;
        wMain = 640;
        hMain = 650;
        setBounds((screenSize.width - wMain) / 2, (screenSize.height - hMain) / 2, wMain, hMain);
        setResizable(false);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Image im = kit.getImage("images/teo.png");
        this.setIconImage(im);
        setUIFont (new javax.swing.plaf.FontUIResource(Font.DIALOG, Font.PLAIN, 12));
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

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

        JMenuBar mbr = new JMenuBar();
        tpMain = new JTabbedPane();

        //region menu mFile

        mFile = new JMenu(titles.get("MWmFile"));
            fNew = new JMenuItem(titles.get("MWfNew"));
            fNew.setIcon(new ImageIcon("images/new.png"));
            fNew.addActionListener(e -> newFile());

            fOpen  = new JMenuItem(titles.get("MWfOpen"), new ImageIcon("images/open.png"));
            fOpen.addActionListener(e -> openFile());
            fOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));

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

        //endregion

        mbr.add(mFile);

        //region menu mTools

        mTools = new JMenu(titles.get("MWmTools"));

            tLoadCat = new JMenuItem(titles.get("MWtLoadCat"), new ImageIcon("images/database.png"));
            tLoadCat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
            tLoadCat.setEnabled(false);
            tLoadCat.addActionListener(e -> loadCatalog());

            tUpdate = new JMenuItem(titles.get("MWtUpdate"));
            tUpdate.setEnabled(false);
            tUpdate.addActionListener(e -> catalogService.updateBasePoints(tpMain.getSelectedIndex()));

            tRun = new JMenuItem(titles.get("MWtRun"), new ImageIcon("images/run.png"));
            tRun.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
            tRun.addActionListener(e -> processSourceData());
            tRun.setEnabled(false);

            tView = new JMenuItem(titles.get("MWtView"), new ImageIcon("images/view.png"));
            tView.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
            tView.setEnabled(false);
            tView.addActionListener(e -> viewResult());

            tExtractPol = new JMenuItem(titles.get("MWtExtractPol"));
            tExtractPol.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
            tExtractPol.setEnabled(false);
            tExtractPol.addActionListener(e -> extractPol());

            tOptions = new JMenuItem(titles.get("MWtOptions"));
            tOptions.setEnabled(true);
            tOptions.addActionListener(e -> new ShowSettings(this));


        mTools.add(tLoadCat);
        mTools.add(tUpdate);
        mTools.add(tRun);
        mTools.add(tView);
        mTools.add(tExtractPol);
        mTools.add(tOptions);

        //endregion

        mbr.add(mTools);

        //region menu mHelp

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

        //endregion

        this.setJMenuBar(mbr);

        //region toolbar tb

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
            btnView.addActionListener(e -> viewResult());

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

        //endregion

        pnlMeasurements = new JPanel();
        pnlMeasurements.setLayout(new BorderLayout());

        pnlPolygon = new JPanel();
        pnlPolygon.setLayout(new BorderLayout());

        tpMain.add(pnlMeasurements);
        tpMain.add(pnlPolygon);
        tpMain.setTitleAt(0, titles.get("MWtpMain0"));
        tpMain.setTitleAt(1,titles.get("MWtpMain1"));
        tpMain.addChangeListener(e -> {
            if (tpMain.getSelectedIndex() == 0) {
                if (security.pass()) {
                    setTitle("Taheoport: " + surveyService.getAbsoluteTahPath());
                    setControlsOn();
                    mImport.setEnabled(true);
                    btnImport.setEnabled(true);
                } else {
                    setTitle("Taheoport");
                    setControlsOff();
                }
            }
            if (tpMain.getSelectedIndex() == 1) {
                if (security.pass()) {
                    setTitle("Taheoport: " + polygonRepository.getAbsolutePolPath());
                    setControlsOn();
                    mImport.setEnabled(false);
                    btnImport.setEnabled(false);
                } else {
                    setTitle("Taheoport");
                    setControlsOff();
                }
            }
        });
        add(tpMain);

        polygonRepository = new PolygonRepository();
        polygonRepository.addStation(new PolygonStation());
        reloadPolygonEditor();
        setTitle("Taheoport: " + polygonRepository.getAbsolutePolPath());
        polygonEditor.setFocusTable();

        SurveyStation st = surveyService.getSurveyRepository().addStation(new SurveyStation());
        st.addPicket();
        reloadSurveyEditor();

        setVisible(true);
        if (security.pass()) {
            setControlsOn();
        } else {
            setControlsOff();
            JOptionPane.showMessageDialog(
                    this,
                    "Что-то пошло не так. Обратитесь к разработчику",
                    "Ошибка!",
                    JOptionPane.ERROR_MESSAGE);
        }
        surveyEditor.setFocusStations();

    }

    /**
     * gets settings
     * @return this.settings
     */
    public Settings getSettings() {
        return settings;
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
     * Gets this.ioController
     * @return IOController
     */
    public IOService getIoController() {
        return this.ioService;
    }

    /**
     * Gets this.surveyController
     * @return SurveyController
     */
    public SurveyService getSurveyController() {
        return surveyService;
    }

    /**
     * Gets this.polygonController
     * @return PolygonController
     */
    public PolygonService getPolygonController() {
        return polygonService;
    }

    public PolygonRepository getPolygonProject() {
        return polygonRepository;
    }

    /**
     * Gets this.extractController
     * @return ExtractController
     */
    public ExtractService getExtractController() {
        return extractService;
    }

    public SettingsService getSettingsController() {
        return settingsService;
    }

    /**
     * Return sp
     * @return SurveyProject sp
     */
    public SurveyRepository getSurveyRepository() {
        return surveyService.getSurveyRepository();
    }

    /**
     * gets HashMap titles
     * @return titles
     */
    public HashMap<String, String> getTitles() {
        return titles;
    }

//    /**
//     * return theoProject
//     * @return TheoProject theoProject
//     */
//    public PolygonProject getPolygonProject() {
//        return polygonProject;
//    }
//
//    public ExtractProject getExtractProject() {
//        return extractProject;
//    }

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
        return settings.getPathWorkDir();
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
        if (surveyEditor != null) {
            surveyEditor.translate();
        }
        if (polygonEditor != null) {
            reloadPolygonEditor();
        }
        revalidate();
    }

    /**
     * import from Leica
     */
    private void importLeica() {
        switch (tpMain.getSelectedIndex()) {
            case 0 -> {
                List <String>  llLeicaList = ioService.readTextFile(settings.getPathWorkDir(), "gsi",
                        titles.get("MWopenFileTitle"));
                if (llLeicaList != null) {
//                    surveyProject = new SurveyProject(this).loadLeicaList(llLeicaList);
                    surveyService.setSurveyRepository(importService.loadLeica(llLeicaList));
//                    surveyService.setAbsoluteTahPath(surveyRepository.getAbsoluteTahPath());
                    reloadSurveyEditor();
                    setControlsOn();
                    surveyService.setAbsoluteTahPath(ioService.writeTextFile(surveyService.getTahList(),settings.getPathWorkDir(),
                            "tah", "Write Tah"));
                    setTitle("Taheoport: " + surveyService.getAbsoluteTahPath());
                    surveyEditor.setFocusStations();
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
                List <String>  llNiconList = ioService.readTextFile(settings.getPathWorkDir(), "raw", titles.get("MWopenFileTitle"));
                if (llNiconList != null) {
                    surveyService.setSurveyRepository(importService.loadNicon(llNiconList));
//                    surveyService.setAbsoluteTahPath(surveyRepository.getAbsoluteTahPath());
                    reloadSurveyEditor();
                    setControlsOn();
                    surveyService.setAbsoluteTahPath(ioService.writeTextFile(surveyService.getTahList(), settings.getPathWorkDir(), "tah", "write Tah"));
                    setTitle("Taheoport: " + surveyService.getAbsoluteTahPath());
                    surveyEditor.setFocusStations();
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
                List<String> llTopconList = ioService.readTextFile(settings.getPathWorkDir(), "txt", titles.get("MWopenFileTitle"));
                if (llTopconList != null) {
//                    surveyProject = new SurveyProject(this).loadTopconList(llTopconList);
                    surveyService.setSurveyRepository(importService.loadTopcon(llTopconList));
//                    surveyService.setAbsoluteTahPath(surveyRepository.getAbsoluteTahPath());
                    reloadSurveyEditor();
                    setControlsOn();
                    surveyService.setAbsoluteTahPath(ioService.writeTextFile(surveyService.getTahList(), settings.getPathWorkDir(), "tah", "write Tah"));
                    setTitle("Taheoport: " + surveyService.getAbsoluteTahPath());
                    surveyEditor.setFocusStations();
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
                surveyService.setSurveyRepository(new SurveyRepository());
//                surveyService.setAbsoluteTahPath(surveyRepository.getAbsoluteTahPath());
                SurveyStation st = surveyService.getSurveyRepository().addStation(new SurveyStation());
                st.addPicket();
                reloadSurveyEditor();
                setControlsOn();
                surveyEditor.setFocusStations();
            }
            case 1 -> {
                polygonRepository = new PolygonRepository();
                polygonRepository.addStation(new PolygonStation());
                reloadPolygonEditor();
                setControlsOn();
                setTitle("Taheoport: " + polygonRepository.getAbsolutePolPath());
                polygonEditor.setFocusTable();
            }
        }
    }

    /**
     * open Tah file
     */
    private void openFile() {
        switch (tpMain.getSelectedIndex()) {
            case 0 -> {
                List<String> llTahList = ioService.readTextFile(settings.getPathWorkDir(), "tah", titles.get("MWopenFileTitle"));
                if (llTahList != null) {
                    surveyService.setSurveyRepository(importService.loadTah(llTahList));
//                    surveyService.setAbsoluteTahPath(surveyRepository.getAbsoluteTahPath());
                    reloadSurveyEditor();
                    setControlsOn();
                    surveyEditor.setFocusStations();
            }
            }
            case 1 -> {
                List<String> llPolList = ioService.readTextFile(settings.getPathWorkDir(), "pol", titles.get("MWopenFileTitle"));
                if (llPolList != null) {
                    polygonRepository = polygonService.loadPolList(llPolList);
                    reloadPolygonEditor();
                    setControlsOn();
                    setTitle("Taheoport: " + polygonRepository.getAbsolutePolPath());
                    polygonEditor.setFocusTable();
                }
            }
        }
    }

    /**
     * extracts llPolList from this.surveyProject and open new TheoProject
     */
    private void extractPol() {
        if (surveyService.getSurveyRepository() != null) {
            if (surveyService.getSurveyRepository().containPolygon()) {
                surveyService.processSourceData();
//                extractProject = new ExtractProject(this);
                polygonRepository = polygonService.loadPolList(extractService.extractPolygonProject());
                tpMain.setSelectedIndex(1);
                reloadPolygonEditor();
                setControlsOn();
                setTitle("Taheoport: " + polygonRepository.getAbsolutePolPath());
                polygonEditor.setFocusTable();
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
                if (surveyService.getAbsoluteTahPath().isEmpty()) {
                    String s = ioService.writeTextFile(surveyService.getTahList(), settings.getPathWorkDir(), "tah", "Write Tah");
                    if (s != null) {
                        surveyService.setAbsoluteTahPath(s);
                    }
                } else {
                    surveyService.setAbsoluteTahPath(ioService.writeTextFile(surveyService.getTahList(), surveyService.getAbsoluteTahPath()));
                }
                setTitle("Taheoport: " + surveyService.getAbsoluteTahPath());
            }
            case 1 -> {
                if (polygonRepository.getAbsolutePolPath().isEmpty()) {
                    String s = ioService.writeTextFile(polygonService.getPolList(), settings.getPathWorkDir(), "pol", "Write *.pol");
                    if (s != null) {
                        polygonRepository.setAbsolutePolPath(s);
                    }
                } else {
                    polygonRepository.setAbsolutePolPath(ioService.writeTextFile(polygonService.getPolList(), polygonRepository.getAbsolutePolPath()));
                }
                setTitle("Taheoport: " + polygonRepository.getAbsolutePolPath());
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
                String s = ioService.writeTextFile(surveyService.getTahList(), settings.getPathWorkDir(), "tah", titles.get("MWsaveTahTitle"));
                if (s != null) {
                    surveyService.setAbsoluteTahPath(s);
                    setTitle("Taheoport: " + surveyService.getAbsoluteTahPath());
                }
            }
            case 1 -> {
                String s = ioService.writeTextFile(polygonService.getPolList(), settings.getPathWorkDir(), "pol", titles.get("MWsavePolTitle"));
                if (s != null) {
                    polygonRepository.setAbsolutePolPath(s);
                    setTitle("Taheoport: " + polygonRepository.getAbsolutePolPath());
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
                surveyService.processSourceData();
                ioService.writeTextFile(surveyService.getPickets(), settings.getPathWorkDir(), "dat", "Write DAT");
            }
            case 1 -> {
                polygonService.processSourceData();
                reloadPolygonEditor();
                polygonEditor.setBindings();
            }
        }
    }

    /**
     * Reload tahEditorStandart
     */
    public void reloadSurveyEditor() {
        if (surveyEditor != null) {
            pnlMeasurements.remove(surveyEditor);
        }
        surveyEditor = new SurveyEditorStandart(this, 0);
        pnlMeasurements.add(surveyEditor);
        setTitle("Taheoport: " + surveyService.getAbsoluteTahPath());
        setFocusTraversalPolicy(new TahEditorFocusTransversalPolicy(surveyEditor.getOrder()));
        revalidate();
    }

    /**
     * Reload TheoEditor
     */
    public void reloadPolygonEditor() {
        if (polygonEditor != null) {
            pnlPolygon.remove(polygonEditor);
        }
        polygonEditor = new PolygonEditorStandart(this);
        pnlPolygon.add(polygonEditor);
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
        btnLoadCat.setEnabled(true);
        btnView.setEnabled(true);
        tLoadCat.setEnabled(true);
        tView.setEnabled(true);
    }

    /**
     * Sets setEnabled(false) for GUI components
     */
    private void setControlsOff() {
        fOpen.setEnabled(false);
        fNew.setEnabled(false);
        mImport.setEnabled(false);
        fSave.setEnabled(false);
        fSaveAs.setEnabled(false);
        tExtractPol.setEnabled(false);
        btnImport.setEnabled(false);
        btnNew.setEnabled(false);
        btnOpen.setEnabled(false);
        btnSave.setEnabled(false);
        btnRun.setEnabled(false);
        tRun.setEnabled(false);
        btnLoadCat.setEnabled(false);
        btnView.setEnabled(false);
        tLoadCat.setEnabled(false);
        tView.setEnabled(false);
    }

    /**
     * Show result of processing or Adjustments
     */
    private void viewResult() {
        switch (tpMain.getSelectedIndex()) {
            case 0 -> {
                surveyService.processSourceData();
                new ShowViewResults(this);
            }
            case 1 -> {
                processSourceData();
                if (polygonRepository.getPerimeter() > 0.0) {
                    new ShowViewAdjustment(this);
                }

            }

        }

    }

    /**
     * This action load points coordinates from text file *.kat to sp (SurveyProject)
     */
    private void loadCatalog() {
            catalog = catalogService.loadCatalogList(ioService.readTextFile(settings.getPathWorkDir(),
                    "kat",
                    titles.get("MWloadCatalogTitle")));
        if (catalog.getSizeCatalog() > 0) {
            lblCatalog.setEnabled(true);
            lblCatalog.setText(catalog.getAbsoluteCatalogPath());
            tUpdate.setEnabled(true);
            isCatalog = true;
            if(surveyEditor != null) {
                surveyEditor.getBtnStationName().setEnabled(true);
                surveyEditor.getBtnOrName().setEnabled(true);
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

            this.order = new Vector<>(order.size());
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
            int idx = order.indexOf(aComponent) + 1;
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
