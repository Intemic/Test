package example;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Anton on 15.12.2016.
 */

import io.*;

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
        while ((s = in.readLine()) != null) {
            if (regx != "") {
                if (mt.reset(s).find())
                    ll.add(s);
            } else
                ll.add(s);
        }

        in.close();

        ListIterator<String> li = ll.listIterator(ll.size());
        while (li.hasPrevious())
            sb.append(li.previous() + "\n");

        return sb.toString();
    }

    public static void main(String[] args)
            throws IOException {
        String fileName = null, s = null, regx = "";
        int line = 0;

        if ((args.length != 2) && (args.length != 1)) {
            System.out.println("Введите имя файла, шаблон поиска");
            System.exit(1);
        } else if (args.length == 2)
            regx = args[1];

        BufferedReader in = new BufferedReader(new StringReader(read(args[0], regx)));
        PrintWriter out = new PrintWriter("C:\\Users\\Anton\\IdeaProjects\\JavaBook\\src\\example\\EBufferedInputFile");

        while ((s = in.readLine()) != null)
            out.println(++line + " : " + s);

        out.close();
        System.out.print(BufferedInputFile.read("C:\\Users\\Anton\\IdeaProjects\\JavaBook\\src\\example\\EBufferedInputFile"));
    }
}
