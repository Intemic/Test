package example;

import generics.Fibonacci;

/**
 * Created by Anton on 29.08.2017.
 */
public class EFibonacci extends Fibonacci implements Runnable  {
   private int count = 0;

    public EFibonacci(int cnt){
      count = cnt;
   }

    @Override
    public void run() {
      for (int i = 0; i < count; i++)
        System.out.print(next() + " ");
    }

    public static void main(String[] arg){
      EFibonacci ef = new EFibonacci(18);
      ef.run();
    }
}
