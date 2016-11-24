package example;

import containers.AssociativeArray;
import net.mindview.util.TextFile;

import java.util.ArrayList;

/**
 * Created by Anton on 24.11.2016.
 */
public class EWords {
    public static void main(String[] arg) {
        ArrayList<String> al = new TextFile("C:\\Users\\Anton\\IdeaProjects\\JavaBook\\src\\example\\text.txt", "\\s");
        AssociativeArray<String, Integer> aa = new AssociativeArray<String, Integer>(10);
        for (String s : al) {
          // System.out.println(s);

            try {

                aa.put(s, aa.get(s) + 1);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error");
                break;
            }

        }
        System.out.println(al.toString());
    }

}
