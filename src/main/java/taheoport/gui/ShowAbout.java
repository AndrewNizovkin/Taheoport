package taheoport.gui;

import taheoport.dispatcher.DependencyContainer;
import taheoport.dispatcher.DependencyInjector;
import taheoport.service.Shell;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * This class encapsulates form about program
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class ShowAbout extends JDialog {

    /**
     * Constructor
     */
    public ShowAbout() {
        super( DependencyContainer
                        .getInstance()
                        .getMainFrame(),
                DependencyContainer
                        .getInstance()
                        .getTitles()
                        .get("SAdialogTitle"),
                true);
        DependencyInjector dependencyInjector = DependencyContainer.getInstance();
        JFrame parentFrame = dependencyInjector.getMainFrame();
        Shell shell = dependencyInjector.getShell();
        setBounds(parentFrame.getX() + parentFrame.getWidth() / 2 - parentFrame.getWidth() / 3 * 2 / 2,
                parentFrame.getY() + parentFrame.getHeight() / 2 - parentFrame.getHeight() / 2 / 2,
                parentFrame.getWidth() / 3 * 2,
                parentFrame.getHeight() / 2);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image im = kit.getImage("images/teo.png");
        this.setIconImage(im);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

// pnlCopyright______________________________________

        JPanel pnlCopyright = new JPanel();
        pnlCopyright.setBorder(BorderFactory.createEtchedBorder());
        pnlCopyright.setLayout(new GridBagLayout());

        pnlCopyright.add(new JLabel(new ImageIcon("images/teo.png")), new GridBagConstraints(
                0,
                1,
                1,
                1,
                1,
                0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH,
                new Insets(
                        0,
                        0,
                        0,
                        0),
                0,
                0));

        JLabel label = new JLabel("Taheoport");
        label.setFont(new Font(Font.DIALOG, Font.BOLD, 14));

        pnlCopyright.add(label, new GridBagConstraints(
                1,
                0,
                1,
                1,
                1,
                    0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH,
                new Insets(
                        0,
                        0,
                        0,
                        0),
                0,
                0));

        label = new JLabel("Version 2025.07.1 (build july 2025)");
        label.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));

        pnlCopyright.add(label, new GridBagConstraints(
                1,
                1,
                1,
                1,
                1,
                0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH,
                new Insets(
                        0,
                        0,
                        0,
                        0),
                0,
                0));

        label = new JLabel("Copyright 2022 Nizovkin A.V.");
        label.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));

        pnlCopyright.add(label, new GridBagConstraints(
                1,
                2,
                1,
                1,
                1,
                0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH,
                new Insets(
                        0,
                        0,
                        0,
                        0),
                0,
                0));

        add(pnlCopyright, BorderLayout.NORTH);

// spLicense_________________________________________

        JTextArea textArea = new JTextArea();
        LinkedList<String> llLicense = shell.getLicense();
        String s = llLicense.pollFirst();

        while (s != null) {
            textArea.append(s + "\n");
            s = llLicense.pollFirst();
        }
        textArea.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
        textArea.setLineWrap(false);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setCaretPosition(0);
        JScrollPane spLicense = new JScrollPane(textArea);

        add(spLicense, BorderLayout.CENTER);

// pnlClose___________________________________________

        JPanel pnlClose = new JPanel();
        pnlClose.setLayout(new FlowLayout());

// btnClose___________________________________________

            JButton btnClose = new JButton(shell.getTitles().get("SAbtnClose"));
            btnClose.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
            btnClose.addActionListener(e -> this.dispose());

        pnlClose.add(btnClose);

        add(pnlClose, BorderLayout.SOUTH);


        setResizable(false);
        setVisible(true);
    }
}
