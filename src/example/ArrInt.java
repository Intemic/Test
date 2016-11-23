package example;

import net.mindview.util.CountingGenerator;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Anton on 14.11.2016.
 */
public class ArrInt {
  public static void main(String[] arg){
      Random rd = new Random(100);
      Integer[] ai = new Integer[25];
      for (int i = 0; i < ai.length; i++)
        ai[i] = rd.nextInt(100);
      Arrays.sort(ai, Collections.reverseOrder());
      System.out.println(Arrays.toString(ai));
  }
}
