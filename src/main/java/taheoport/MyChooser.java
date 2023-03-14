package taheoport;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.LinkedList;

/**
 * Provides methods for reading and writing text files
 * @author Andrey Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class MyChooser {
    MainWin parentFrame;

    public MyChooser(MainWin frame) {
        parentFrame = frame;
    }


    /**
     * Writes the list items to a text file of the specified type
     * @param path String
     * @param filter String
     * @param title String
     * @param l LinkedList
     */
    public String writeTextFile(String path, String filter, String title, LinkedList <String> l) {
        String absolutePath;
        int res = 0;
        String str;
        JFileChooser fch = new JFileChooser(path);
        FileNameExtensionFilter fileNameFilter = new FileNameExtensionFilter("*."+filter, filter);
        fch.setFileFilter(fileNameFilter);
        fch.setDialogTitle(title);
        res = fch.showSaveDialog(parentFrame);
        if (res != JFileChooser.APPROVE_OPTION) {
            fch.cancelSelection();
            return null;
        }
        File f = fch.getSelectedFile();
        if (f.isFile()) {
            f.delete();
        }
        absolutePath = f.getAbsolutePath();
//        f.setWritable(true);
//        System.out.println(f.canWrite());
        try {
            BufferedWriter output;
            if (!f.getAbsolutePath().endsWith(filter)) {
                output = new BufferedWriter(new FileWriter(f.getAbsolutePath() + "." + filter));
            } else {
                output = new BufferedWriter(new FileWriter(f.getAbsolutePath()));
            }
            while (l.size() > 0) {
                output.write(l.removeFirst());
                output.newLine();
            }
            output.close();
            fch = null;
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println("File access error");
        }
        return absolutePath;
    }

    public String writeTextFile(String absolutePath, LinkedList <String> l) {
        if (!(l == null)) {
            File f = new File(absolutePath);

            try {
                BufferedWriter output = new BufferedWriter(new FileWriter(f.getAbsolutePath()));
                while (l.size() > 0) {
                    output.write(l.removeFirst());
                    output.newLine();
                }
                output.close();
//            fch = null;
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
            } catch (IOException e) {
                System.out.println("File access error");
            }
        }
        return absolutePath;

    }



    /**
     * Returns a list of lines of the specified text file
     * @param array may be:
     *              String [3] array {path, filter, title}
     *              String [1] array {absolutePath}
     * @return LinkedList The first item in the list is the absolute path to the file
     */
//    public LinkedList<String> readTextFile(String path, String filter, String title) {
    public LinkedList<String> readTextFile(String ... array) {
        LinkedList <String> l = new LinkedList<String>();
        File f = new File("noname.txt");
        int res = 0;
        String str;
        if (array != null) {
            if (array.length == 3) {
                JFileChooser fch = new JFileChooser(array[0]);


                FileNameExtensionFilter fileNameFilter = new FileNameExtensionFilter("*." + array[1], array[1]);
                fch.setFileFilter(fileNameFilter);
                fch.setDialogTitle(array[2]);
                res = fch.showOpenDialog(parentFrame);
                if (res != JFileChooser.APPROVE_OPTION) {
                    fch.cancelSelection();
                    return null;
                }
                f = fch.getSelectedFile();
                l.add(f.getAbsolutePath());
            }
            if (array.length == 1) {
                l.add(array[0]);
                f = new File(array[0]);
            }

            try {
                BufferedReader input = new BufferedReader(new FileReader(f.getAbsoluteFile()));
                while ((str = input.readLine()) != null) {
                    l.addLast(str);
                }
                input.close();
//            fch = null;
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
            } catch (IOException e) {
                System.out.println("File access error");
            }

        }
            return l;
        }
    }
