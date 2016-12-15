package example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by Anton on 15.12.2016.
 */
public class EBufferedInputFile {
    // Throw exceptions to console:
    public static String read(String filename) throws IOException {
        LinkedList<String> ll = new LinkedList<>();

        // Reading input by lines:
        BufferedReader in = new BufferedReader(
                new FileReader(filename));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = in.readLine()) != null)
            ll.add(s);
        in.close();

        ListIterator<String> li = ll.listIterator(ll.size());
        while (li.hasPrevious())
            sb.append(li.previous() + "\n");

        return sb.toString();
    }

    public static void main(String[] args)
            throws IOException {
        System.out.print(read("C:\\Users\\Anton\\IdeaProjects\\JavaBook\\src\\io\\BufferedInputFile.java"));
    }
}
