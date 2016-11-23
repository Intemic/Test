package example;

/**
 * Created by Anton on 16.11.2016.
 */

import java.util.*;
import net.mindview.util.*;

public class ContFile {
    public static void main(String[] arg){
      //List<String> lst = new TextFile("C:\\Users\\Anton\\IdeaProjects\\JavaBook\\src\\example\\ContFile.java", " ");
      List<String> lst = new ArrayList<>();
      for(String word : new TextFile("C:\\Users\\Anton\\IdeaProjects\\JavaBook\\src\\example\\ContFile.java", " ")){
        lst.add(word);
        System.out.println(word);
      }

    }
}
