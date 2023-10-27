package taheoport.controllers;

import java.util.LinkedList;

public interface IOController {

    /**
     * Reads from specified text file
     * @param args may be:
     *              String [3] args {path, filter, title}
     *              String [1] args {absolutePath}
     * @return LinkedList The first item in the list is the absolute path to the file
     */
    LinkedList<String> readTextFile(String ... args);

    /**
     * Writes the list items to a text file of the specified type
     * @param args may be:
     *              String [3] args {path, filter, title}
     *              String [1] args {absolutePath}
     * @return LinkedList The first item in the list is the absolute path to the file
     */

    String writeTextFile(LinkedList<String> list, String... args);
}
