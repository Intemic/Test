package example;

import containers.AssociativeArray;
import containers.SlowMap;
import net.mindview.util.TextFile;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Anton on 24.11.2016.
 */
public class EWords {
    public static void main(String[] arg) {
        //ArrayList<String> al = new TextFile("C:\\Users\\Anton\\IdeaProjects\\JavaBook\\src\\example\\text.txt", "\\s");
        ArrayList<String> al =
                new TextFile("C:\\Users\\Anton\\IdeaProjects\\JavaBook\\src\\net\\mindview\\util\\TextFile.java", "(\\s)|(\\;)|(\\,)|(\\.)|(\\{)|(\\})");
        AssociativeArray<String, Integer> aa = new AssociativeArray<String, Integer>(100);
        AssociativeArray<String, Integer> a2;
        SlowMap<String, Integer> sl = new SlowMap<String, Integer>();

        /*
        for (String s : al) {
            // System.out.println(s);
            if (s == " ")
                continue;

            try {
                if (aa.get(s) == null)
                    aa.put(s, 1);
                else{
                   a2 = new AssociativeArray<>(100);
                   for(){

                   }
                   aa.put(s, aa.get(s) + 1);
                }


            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error");
                break;
            }
        }
        */


        for (String s : al) {
            if (s == " ")
                continue;

            if (sl.get(s) == null)
                sl.put(s, 1);
            else
                sl.put(s, sl.get(s) + 1);
        }

        for (Map.Entry<String, Integer> mp : sl.entrySet()) {
            System.out.println("[" + mp.getKey() + "=" + mp.getValue() + "]");
        }
    }
}
