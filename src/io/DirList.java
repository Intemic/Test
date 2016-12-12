package io;//: io/DirList.java
// Display a directory listing using regular expressions.
// {Args: "D.*\.java"}

import java.util.regex.*;
import java.io.*;
import java.util.*;

import net.mindview.util.*;

import javax.xml.soap.Text;

public class DirList {
    public static void main(String[] args) {
        File path = new File("C:\\Users\\Anton\\IdeaProjects\\JavaBook\\src\\io");
        String[] list;
        if (args.length == 0)
            list = path.list();
        else if (args.length == 2)
            list = path.list(new DirFilter(args[0], args[1]));
        else
            list = path.list(new DirFilter(args[0]));
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list)
            System.out.println(dirItem);
    }
}

class DirFilter implements FilenameFilter {
    private Pattern pattern;
    private Pattern patWord = null;

    public DirFilter(String regex) {
        pattern = Pattern.compile(regex);
    }

    public DirFilter(String regex, String word) {
        pattern = Pattern.compile(regex);
        patWord = Pattern.compile(word);
    }

    public boolean accept(File dir, String name) {
        boolean result;
        String content;

        if (name == "xfiles")
            return false;
        else {
            String fn = dir + "\\" + name;
            //return pattern.matcher(name).matches();
            result = pattern.matcher(name).matches();
            if (patWord == null)
                return result;

            if (result) {
                content = TextFile.read(fn);
                return patWord.matcher(content).find();
            } else
                return result;
        }
    }
} /* Output:
DirectoryDemo.java
DirList.java
DirList2.java
DirList3.java
*///:~
