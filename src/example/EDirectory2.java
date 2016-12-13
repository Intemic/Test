package example;

import net.mindview.util.ProcessFiles;
import java.io.File;

/**
 * Created by Anton on 13.12.2016.
 */
public class EDirectory2 {
  public static long lgo = 0;

  public static void main(String[] arg){
      String[] st = new String[1];

      st[0] = arg[0];

     new ProcessFiles(new ProcessFiles.Strategy() {
         public void process(File file){
           lgo += file.length();
         }
     }, arg[1]).start(st);

     //pf.start(st);

     System.out.print("Total size - " + lgo);
  }
}
