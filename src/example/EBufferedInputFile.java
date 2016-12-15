package example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Anton on 15.12.2016.
 */
public class EBufferedInputFile {
    // Throw exceptions to console:
    public static String read(String filename, String regx) throws IOException {
        Matcher mt = Pattern.compile(regx).matcher("");
        //Pattern pt = Pattern.compile(regx);
        LinkedList<String> ll = new LinkedList<>();

        // Reading input by lines:
        BufferedReader in = new BufferedReader(
                new FileReader(filename));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = in.readLine()) != null)
            if (mt.reset(s).find())
                ll.add(s);

        in.close();

        ListIterator<String> li = ll.listIterator(ll.size());
        while (li.hasPrevious())
            sb.append(li.previous() + "\n");

        return sb.toString();
    }

    public static void main(String[] args)
            throws IOException {
        String fileName = null;
        if (args.length != 2) {
            System.out.println("Введите имя файла, шаблон поиска");
            System.exit(1);
        }

        System.out.print(read(args[0], args[1]));
    }
}
