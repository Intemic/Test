package example;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Created by Anton on 23.11.2016.
 */

public class EQuene implements Comparable<EQuene>{
  int key;

  public EQuene(int value) {
      this.key = value;
  }

  public int compareTo(EQuene arg) {
   return ( arg.key > key ? -1 : (arg.key == key ? 0 : 1));
  }

  public static void main(String[] arg){
      Random rd = new Random(47);
      PriorityQueue<EQuene> pq = new PriorityQueue<EQuene>();
      for(int i = 0; i < 20; i++)
        pq.offer(new EQuene(rd.nextInt(100)));

      for (int i = 0; i < pq.size() ; i++)
         System.out.println(pq.poll().key);
  }
}
