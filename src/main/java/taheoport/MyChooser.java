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
     * @param list LinkedList
     */
    public String writeTextFile(String path, String filter, String title, LinkedList <String> list) {
        String absolutePath;
        int result;
        JFileChooser fileChooser = new JFileChooser(path);
        FileNameExtensionFilter fileNameFilter = new FileNameExtensionFilter("*."+filter, filter);
        fileChooser.setFileFilter(fileNameFilter);
        fileChooser.setDialogTitle(title);
        result = fileChooser.showSaveDialog(parentFrame);
        if (result != JFileChooser.APPROVE_OPTION) {
            fileChooser.cancelSelection();
            return null;
        }
        File f = fileChooser.getSelectedFile();
        if (f.isFile()) {
            f.delete();
        }
        absolutePath = f.getAbsolutePath();
        try {
            BufferedWriter output;
            if (!f.getAbsolutePath().endsWith(filter)) {
                output = new BufferedWriter(new FileWriter(f.getAbsolutePath() + "." + filter));
            } else {
                output = new BufferedWriter(new FileWriter(f.getAbsolutePath()));
            }
            while (list.size() > 0) {
                output.write(list.removeFirst());
                output.newLine();
            }
            output.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println("File access error");
        }
        return absolutePath;
    }

    /**
     * Writes the list items to a text file of the specified type.
     * @param absolutePath String path to file
     * @param list LinkedList<String>
     * @return String absolutePath
     */
    public String writeTextFile(String absolutePath, LinkedList<String> list) {
        if (!(list == null)) {
            File f = new File(absolutePath);

            try {
                BufferedWriter output = new BufferedWriter(new FileWriter(f.getAbsolutePath()));
                while (list.size() > 0) {
                    output.write(list.removeFirst());
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
        LinkedList <String> list = new LinkedList<String>();
        File file = new File("noname.txt");
        int res = 0;
        String str;
        if (array != null) {
            if (array.length == 3) {
                JFileChooser fileChooser = new JFileChooser(array[0]);

                FileNameExtensionFilter fileNameFilter = new FileNameExtensionFilter("*." + array[1], array[1]);
                fileChooser.setFileFilter(fileNameFilter);
                fileChooser.setDialogTitle(array[2]);
                res = fileChooser.showOpenDialog(parentFrame);
                if (res != JFileChooser.APPROVE_OPTION) {
                    fileChooser.cancelSelection();
                    return null;
                }
                file = fileChooser.getSelectedFile();
                list.add(file.getAbsolutePath());
            }
            if (array.length == 1) {
                list.add(array[0]);
                file = new File(array[0]);
            }

            try {
                BufferedReader input = new BufferedReader(new FileReader(file.getAbsoluteFile()));
                while ((str = input.readLine()) != null) {
                    list.addLast(str);
                }
                input.close();
//            fch = null;
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
            } catch (IOException e) {
                System.out.println("File access error");
            }

        }
            return list;
        }
    }
