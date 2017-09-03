package example;

import generics.Fibonacci;

import java.util.Random;

/**
 * Created by Anton on 29.08.2017.
 */
class EFibonacci extends Fibonacci implements Runnable  {
    private static int counter = 0;
    private final int id = counter++;
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
      Random rd = new Random(47);
      for (int i = 0; i < 5; i++){
        new Thread(new EFibonacci(rd.nextInt(20))).start();
      }
    }
}
