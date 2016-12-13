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
      ProcessFiles pf = new ProcessFiles(new ProcessFiles.Strategy(){
         //private long lg = 0;

         public void process(File file){
           lgo += file.length();
         }
     }, arg[1]);

     st[0] = arg[0];
     pf.start(st);

     System.out.print("Total size - " + lgo);
  }
}
