package example;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Anton on 03.11.2016.
 */
public class Array1 {
    public static Random rnd = new Random(47);

    public double[][] init(int l1, int l2, double valf, double valt){
      double[][] da = new double[l1][l2];
      for(int i = 0; i < da.length; i++){
        for(int j = 0; j < da[i].length; j++){
          da[i][j] = valf + ((valt - valf) * rnd.nextDouble());
        }
      }
      return da;
    }

    public void out(double[][] ar){
      System.out.println(Arrays.deepToString(ar));
    }

    public static void main(String[] arg){
       Array1 my = new Array1();
       my.out(my.init(2, 3, 5, 8));
       my.out(my.init(5, 2, 1, 10));

    }
}
