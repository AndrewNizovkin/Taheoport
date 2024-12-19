package taheoport.gui;

import taheoport.dispatcher.MainActionListener;
import taheoport.service.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

/**
 * This main window of program
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class MainWin extends JFrame implements MainRenderer{
    private final IOService ioService;
    private final SurveyService surveyService;
    private final PolygonService polygonService;
    private final ExtractService extractService;
    private final CatalogService catalogService;
    private final SettingsService settingsService;
    private final Security security;
    private final JTabbedPane tpMain;
    private final JPanel pnlMeasurements;
    private final JPanel pnlPolygon;
    private final Shell shell;
    private HashMap<String, String> titles;
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
    private SurveyEditorStandard surveyEditor;
    private PolygonEditorStandart polygonEditor;
    private final JLabel lblCatalog;
    private JPopupMenu ppImport;


    /**
     * Constructor
     */
    public MainWin() {
        super("Taheoport");
        ioService = new IOServiceDefault(this);
        settingsService = new SettingsServiceDefault(this);
        surveyService = new SurveyServiceDefault(this);
        polygonService = new PolygonServiceDefault(this);
        extractService = new ExtractServiceDefault(this);
        catalogService = new CatalogServiceDefault(this);
        security = new SecurityImpl();
        shell = new Shell(settingsService);
        titles = shell.getTitles();
        ActionListener actionListener = new MainActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
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

        JMenuBar mbr = new JMenuBar();
        tpMain = new JTabbedPane();

        //region menu mFile

        mFile = new JMenu(titles.get("MWmFile"));
            fNew = new JMenuItem(titles.get("MWfNew"));
            fNew.setActionCommand("fNew");
            fNew.setIcon(new ImageIcon("images/new.png"));
            fNew.addActionListener(actionListener);

            fOpen  = new JMenuItem(titles.get("MWfOpen"), new ImageIcon("images/open.png"));
            fOpen.setActionCommand("fOpen");
            fOpen.addActionListener(actionListener);
            fOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));

            mImport = new JMenu(titles.get("MWmImport"));
                JMenuItem iLeica = new JMenuItem("Leica");
                iLeica.setActionCommand("iLeica");
                iLeica.addActionListener(actionListener);

                JMenuItem iNicon = new JMenuItem("Nicon");
                iNicon.setActionCommand("iNicon");
                iNicon.addActionListener(actionListener);

                JMenuItem iTopcon = new JMenuItem("Topcon");
                iTopcon.setActionCommand("iTopcon");
                iTopcon.addActionListener(actionListener);

            mImport.add(iLeica);
            mImport.add(iNicon);
            mImport.add(iTopcon);

            fSave = new JMenuItem(titles.get("MWfSave"), new ImageIcon("images/save.png"));
            fSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
            fSave.setActionCommand("fSave");
            fSave.addActionListener(actionListener);
            fSave.setEnabled(false);

            fSaveAs = new JMenuItem(titles.get("MWfSaveAs"));
            fSaveAs.setActionCommand("fSaveAs");
            fSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_DOWN_MASK));
            fSaveAs.addActionListener(actionListener);
            fSaveAs.setEnabled(false);

            fExit = new JMenuItem(titles.get("MWfExit"));
            fExit.setActionCommand("fExit");
            fExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
            fExit.addActionListener(actionListener);

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
            tLoadCat.setActionCommand("tLoadCat");
            tLoadCat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
            tLoadCat.setEnabled(false);
            tLoadCat.addActionListener(actionListener);

            tUpdate = new JMenuItem(titles.get("MWtUpdate"));
            tUpdate.setActionCommand("tUpdate");
            tUpdate.setEnabled(false);
            tUpdate.addActionListener(actionListener);

            tRun = new JMenuItem(titles.get("MWtRun"), new ImageIcon("images/run.png"));
            tRun.setActionCommand("tRun");
            tRun.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
            tRun.addActionListener(actionListener);
            tRun.setEnabled(false);

            tView = new JMenuItem(titles.get("MWtView"), new ImageIcon("images/view.png"));
            tView.setActionCommand("tView");
            tView.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
            tView.setEnabled(false);
            tView.addActionListener(actionListener);

            tExtractPol = new JMenuItem(titles.get("MWtExtractPol"));
            tExtractPol.setActionCommand("tExtractPol");
            tExtractPol.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
            tExtractPol.setEnabled(false);
            tExtractPol.addActionListener(actionListener);

            tOptions = new JMenuItem(titles.get("MWtOptions"));
            tOptions.setActionCommand("tOptions");
            tOptions.setEnabled(true);
            tOptions.addActionListener(actionListener);


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
            hAbout.setActionCommand("hAbout");
            hAbout.addActionListener(actionListener);
            hAbout.setEnabled(true);

            hHelp = new JMenuItem(titles.get("MWhHelp"));
            hHelp.setActionCommand("hHelp");
            hHelp.addActionListener(actionListener);
            hHelp.setEnabled(true);
        mHelp.add(hAbout);
        mHelp.add(hHelp);
        mbr.add(mHelp);

        //endregion

        this.setJMenuBar(mbr);

        //region toolbar tb

        JToolBar tb = new JToolBar();

            btnNew = new JButton(new ImageIcon("images/new.png"));
            btnNew.setActionCommand("bntNew");
            btnNew.setToolTipText(titles.get("MWbtnNewTT"));
            btnNew.addActionListener(actionListener);


            btnOpen = new JButton(new ImageIcon("images/open.png"));
            btnOpen.setActionCommand("btnOpen");
            btnOpen.setToolTipText(titles.get("MWbtnOpenTT"));
            btnOpen.addActionListener(actionListener);

            btnImport = new JButton(new ImageIcon("images/import.png"));
            btnImport.setActionCommand("btnImport");
            btnImport.setToolTipText(titles.get("MWbtnImportTT"));
            btnImport.addActionListener(e -> ppImport.show(this,
                    btnImport.getX(),
                    btnImport.getY() + 20));

        ppImport = new JPopupMenu();
        JMenuItem ppiLeica = new JMenuItem("Leica");
        ppiLeica.setActionCommand("ppiLeica");
        ppiLeica.addActionListener(actionListener);

        JMenuItem ppiNicon = new JMenuItem("Nicon");
        ppiNicon.setActionCommand("ppiNicon");
        ppiNicon.addActionListener(actionListener);

        JMenuItem ppiTopcon = new JMenuItem("Topcon");
        ppiTopcon.setActionCommand("ppiTopcon");
        ppiTopcon.addActionListener(actionListener);

        ppImport.add(ppiLeica);
        ppImport.add(ppiNicon);
        ppImport.add(ppiTopcon);
        add(ppImport);

            btnSave = new JButton(new ImageIcon("images/save.png"));
            btnSave.setActionCommand("btnSave");
            btnSave.addActionListener(actionListener);
            btnSave.setToolTipText(titles.get("MWbtnSaveTT"));
            btnSave.setEnabled(false);

            btnRun = new JButton(new ImageIcon("images/run.png"));
            btnRun.setActionCommand("btnRun");
            btnRun.setToolTipText(titles.get("MWbtnRunTT"));
            btnRun.addActionListener(actionListener);
            btnRun.setEnabled(false);

            btnView = new JButton(new ImageIcon("images/view.png"));
            btnView.setActionCommand("btnView");
            btnView.setToolTipText(titles.get("MWbtnVewTT"));
            btnView.setEnabled(false);
            btnView.addActionListener(actionListener);

            btnLoadCat = new JButton(new ImageIcon("images/database.png"));
            btnLoadCat.setActionCommand("btnLoadCat");
            btnLoadCat.setToolTipText(titles.get("MWbtnLoadCatTT"));
            btnLoadCat.setEnabled(false);
            btnLoadCat.addActionListener(actionListener);

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
                    setTitle("Taheoport: " + polygonService.getAbsolutePolPath());
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
        polygonService.newProject();
        reloadPolygonEditor();
        setTitle("Taheoport: ");
        polygonEditor.setFocusTable(0, 0);

        surveyService.newProject();
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
    public IOService getIoService() {
        return this.ioService;
    }

    public CatalogService getCatalogService() {
        return catalogService;
    }

    /**
     * Gets this.surveyController
     * @return SurveyController
     */
    public SurveyService getSurveyService() {
        return surveyService;
    }

    /**
     * Gets this.polygonController
     * @return PolygonController
     */
    public PolygonService getPolygonService() {
        return polygonService;
    }

    /**
     * Gets this.extractController
     * @return ExtractController
     */
    public ExtractService getExtractService() {
        return extractService;
    }


    public SettingsService getSettingsService() {
        return settingsService;
    }

    /**
     * Return sp
     * @return SurveyProject sp
     */

    /**
     * gets HashMap titles
     * @return titles
     */
    public HashMap<String, String> getTitles() {
        return titles;
    }

    /**
     * Gets object translating shell
     * @return shell
     */
    public Shell getShell() {
        return shell;
    }

    /**
     * Return is Catalog
     * @return Boolean
     */
    public Boolean hasCatalog() {
        return !catalogService.isEmpty();
    }

    public String getPathWorkDir() {
        return settingsService.getPathWorkDir();
    }

    /**
     * Translate interface
     */
    public void translate() {
        titles = shell.getTitles();
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
        if (catalogService.isEmpty()) {
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
     * Gets index of active tab
     * @return int
     */
    public int getMode() {
        return tpMain.getSelectedIndex();
    }

    /**
     * Sets current mode
     * @param mode int mode
     */
    public void setMode(int mode) {
        tpMain.setSelectedIndex(mode);
    }

    /**
     * Reload surveyEditor
     */
    public void reloadSurveyEditor() {
        if (surveyEditor != null) {
            pnlMeasurements.remove(surveyEditor);
        }
        surveyEditor = new SurveyEditorStandard(this, 0);
        pnlMeasurements.add(surveyEditor);
        setTitle("Taheoport: " + surveyService.getAbsoluteTahPath());
        setFocusTraversalPolicy(new TahEditorFocusTransversalPolicy(surveyEditor.getOrder()));
        revalidate();
        surveyEditor.setFocusStations();
        setControlsOn();
        setTitle("Taheoport: " + surveyService.getAbsoluteTahPath());
    }

    /**
     * Reload PolygonEditor
     */
    public void reloadPolygonEditor() {
        if (polygonEditor != null) {
            pnlPolygon.remove(polygonEditor);
        }
        polygonEditor = new PolygonEditorStandart(this);
        pnlPolygon.add(polygonEditor);
        setControlsOn();
        setTitle("Taheoport: " + polygonService.getAbsolutePolPath());
        polygonEditor.setBindings();
        polygonEditor.setFocusTable(0, 0);
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
     * Sets or off current catalog
     * @param turnOn choice action
     */
    public void setCurrentCatalog(boolean turnOn) {
        if (turnOn) {
            lblCatalog.setEnabled(true);
            lblCatalog.setText(catalogService.getAbsoluteCatalogPath());
            tUpdate.setEnabled(true);
            if(surveyEditor != null) {
                surveyEditor.getBtnStationName().setEnabled(true);
                surveyEditor.getBtnOrName().setEnabled(true);
            }

        } else {
            lblCatalog.setEnabled(false);
            lblCatalog.setText("Каталог не установлен");
        }

    }

    /**
     * Gets parentFrame
     * @return MainWin
     */
    @Override
    public MainWin getParentFrame() {
        return this;
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
