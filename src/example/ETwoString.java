package example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import net.mindview.util.*;

/**
 * Created by Anton on 08.12.2016.
 */
public class ETwoString implements Comparable<ETwoString>{
   String first;
   String second;

   public ETwoString(String first, String second){
     this.first = first;
     this.second = second;
   }

    public ETwoString(String first){
      this(first, null);
    }

    public ETwoString(){
      this(null, null);
    }

    public int compareTo(ETwoString o){
      return ( first.length() < o.first.length() ? -1 : (first.toLowerCase().compareTo(o.first.toLowerCase())));
    }

    public String toString(){
      return "First - " + first + (second != null ? ", Second - " + second : " ");
    }

   // public int equals(){
   //   HashSet
   // }

    public static void main(String[] arg){
        RandomGenerator.String gen = new RandomGenerator.String();

        List<ETwoString> list = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            list.add(new ETwoString(gen.next()));

        System.out.println("Before sort - " + list);
        Collections.sort(list);
        System.out.println("After sort - " + list);

    }
}
