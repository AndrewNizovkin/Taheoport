package taheoport;

import taheoport.gui.MainWin;

import javax.swing.*;

public class Program {

    /**
     * Entry point to the program
     * @param args without arguments
     */
    public static void main(String [] args) {
        SwingUtilities.invokeLater(MainWin::new);
        System.out.println("Hello, I am a dog. My name is Laika");
    }

}
