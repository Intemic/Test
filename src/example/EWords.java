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
        ArrayList<String> al = new TextFile("C:\\Users\\Anton\\IdeaProjects\\JavaBook\\src\\example\\text.txt", "\\s");
        SlowMap<String, Integer> aa = new SlowMap<String, Integer>();
        for (String s : al) {
          // System.out.println(s);

//            try {
                if (aa.get(s) == null)
                    aa.put(s, 1);
                else
                    aa.put(s, aa.get(s) + 1);
/*
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error");
                break;
            }
*/
        }


        for (String s : al) {
            if (aa.get(s) == null)
                aa.put(s, 1);
            else
                aa.put(s, aa.get(s) + 1);
        }

        for(Map.Entry<String, Integer> mp : aa.entrySet()){
          System.out.println("[" + mp.getKey() + "=" + mp.getValue() +"]");
        }


       // System.out.println(al);
    }

}
