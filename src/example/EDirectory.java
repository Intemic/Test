package example;

/**
 * Created by Anton on 13.12.2016.
 */

import net.mindview.util.*;
import java.io.File;

public class EDirectory {
    public static void main(String[] arg) {
        long lg = 0;
        String ei;
        Directory.TreeInfo ti = null;

        if (arg.length == 0)
            ti = Directory.walk(".", ".*\\..*");
        else if (arg.length == 1)
            ti = Directory.walk(arg[0], ".*\\..*");
        else if (arg.length == 2)
            ti = Directory.walk(arg[0], arg[1]);
        else {
            System.out.print("Введите параметры программы : Путь рег.выражение");
            System.exit(0);
        }

       for (File fl : ti){
         lg += fl.length();
         System.out.println(fl.getName());
       }

       //if (lg > 1024)
       //  lg = lg % 1024;

       System.out.println("Total size - " + lg);
    }

}
