package example;

import net.mindview.util.ProcessFiles;

import java.io.File;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by Anton on 13.12.2016.
 */
public class EDirectory2 {
    public static long lgo = 0;


    public static void main(final String[] arg) {
        String[] st = new String[1];
        st[0] = arg[0];

        new ProcessFiles(new ProcessFiles.Strategy() {
            private Pattern pt = Pattern.compile(arg[2]);

            public void process(File file) {
                if (pt.matcher(file.getName()).matches())
                    lgo += file.length();
            }
        }, arg[1]).start(st);

        System.out.print("Total size - " + lgo);
    }
}

