package example;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Anton on 29.08.2017.
 */
public class ERunnable implements Runnable {
   private static int counter = 0;
   private final int id = counter++;

   public ERunnable(){
     System.out.println("Create object id - " + id);
   };

    @Override
    public void run() {
       for (int i = 0; i < 3; i++ ){
         System.out.println("Object id - " + id + ", Interation of cicle - " + ( i + 1 ));
         Thread.yield();
       }
        System.out.println("Object id - " + id + ", Finish close");
    }

   public static void main(String[] arg){
       ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
          es.execute(new ERunnable());
       es.shutdown();
            //new Thread(new ERunnable()).start();
   }
}
