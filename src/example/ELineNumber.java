package example;

import io.BufferedInputFile;

import java.io.*;

/**
 * Created by Anton on 15.12.2016.
 */
public class ELineNumber {
    static String file = "C:\\Users\\Anton\\IdeaProjects\\JavaBook\\src\\io\\BasicFileOutput.out";

    public static void main(String[] args)
            throws IOException {

        LineNumberReader in = new LineNumberReader(new BufferedReader(
                new StringReader( BufferedInputFile.read("C:\\Users\\Anton\\IdeaProjects\\JavaBook\\src\\io\\BasicFileOutput.java"))));

        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(file)));
        int lineCount = 1;
        String s;
        while ((s = in.readLine()) != null)
            out.println(in.getLineNumber() + ": " + s);
        out.close();
        // Show the stored file:
        System.out.println(BufferedInputFile.read(file));
    }
}