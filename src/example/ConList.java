package example;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.mindview.util.*;

/**
 * Created by Anton on 16.11.2016.
 */
public class ConList {
    public static void main(String[] arg){
        List<String> res = new ArrayList<String>();

        Pattern p = Pattern.compile("^A.*");
        Matcher m = p.matcher("");
        for(String lst : Countries.names()){
           m.reset(lst);
           if (m.find())
             res.add(lst);
        }

/*
        List<String> lst = new ArrayList<String>(Countries.names());
        Collections.sort(lst);
        System.out.println(lst);
        Collections.shuffle(lst);
        System.out.println(lst);
        Collections.shuffle(lst);
*/
        System.out.println(Countries.names());
        System.out.println(res);
    }
}
