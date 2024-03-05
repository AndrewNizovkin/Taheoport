package taheoport.controllers;

import taheoport.gui.MainWin;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.LinkedList;

public class IOControllerDefault implements IOController {
    private final MainWin parentFrame;

    public IOControllerDefault(MainWin parentFrame) {
        this.parentFrame = parentFrame;
    }

    /**
     * Reads from specified text file
     *
     * @param args may be:
     *             String [3] args {path, filter, title}
     *             String [1] args {absolutePath}
     * @return LinkedList The first item in the list is the absolute path to the file
     */
    @Override
    public LinkedList<String> readTextFile(String... args) {
        LinkedList <String> list = new LinkedList<>();
        File file = new File("noname.txt");
        int res = 0;
        String str;
        if (args != null) {
            if (args.length == 3) {
                JFileChooser fileChooser = new JFileChooser(args[0]);

                FileNameExtensionFilter fileNameFilter = new FileNameExtensionFilter("*." + args[1], args[1]);
                fileChooser.setFileFilter(fileNameFilter);
                fileChooser.setDialogTitle(args[2]);
                res = fileChooser.showOpenDialog(parentFrame);
                if (res != JFileChooser.APPROVE_OPTION) {
                    fileChooser.cancelSelection();
                    return null;
                }
                file = fileChooser.getSelectedFile();
                list.add(file.getAbsolutePath());
            }
            if (args.length == 1) {
                list.add(args[0]);
                file = new File(args[0]);
            }

            try (BufferedReader input = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
                while ((str = input.readLine()) != null) {
                    list.addLast(str);
                }
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
            } catch (IOException e) {
                System.out.println("File access error");
            }

        }
        return list;
    }

    /**
     * Writes the list items to a text file of the specified type
     *
     * @param list
     * @param args may be:
     *             String [3] args {path, filter, title}
     *             String [1] args {absolutePath}
     * @return LinkedList The first item in the list is the absolute path to the file
     */
    @Override
    public String writeTextFile(LinkedList<String> list, String... args) {
        String absolutePath = parentFrame.getPathWorkDir();
        if (args.length != 0) {
            switch (args.length) {
                case 1 -> {
                    absolutePath = args[0];
                    if (!(list == null)) {
                        File f = new File(absolutePath);

                        try (BufferedWriter output = new BufferedWriter(new FileWriter(f.getAbsolutePath()))){
                            while (list.size() > 0) {
                                output.write(list.removeFirst());
                                output.newLine();
                            }
                        } catch (FileNotFoundException e) {
                            System.out.println("file not found");
                        } catch (IOException e) {
                            System.out.println("File access error");
                        }
                    }
                }
                case 3 -> {
                    int result;
                    String path = args[0];
                    String filter = args[1];
                    String title = args[2];
                    JFileChooser fileChooser = new JFileChooser(path);
                    FileNameExtensionFilter fileNameFilter = new FileNameExtensionFilter("*."+ filter, filter);
                    fileChooser.setFileFilter(fileNameFilter);
                    fileChooser.setDialogTitle(title);
                    result = fileChooser.showSaveDialog(parentFrame);
                    if (result != JFileChooser.APPROVE_OPTION) {
                        fileChooser.cancelSelection();
                        return null;
                    }
                    File f = fileChooser.getSelectedFile();
                    if (!f.getAbsolutePath().endsWith(filter)) {
                        absolutePath = f.getAbsolutePath() + "." + filter;
                    } else {
                        absolutePath = f.getAbsolutePath();
                    }
                    try (BufferedWriter output = new BufferedWriter(new FileWriter(absolutePath))){
                        while (list.size() > 0) {
                            output.write(list.removeFirst());
                            output.newLine();
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("file not found");
                    } catch (IOException e) {
                        System.out.println("File access error");
                    }
                }
            }
        }
        return absolutePath;
    }
}
