package example;

import generics.Fibonacci;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Anton on 30.08.2017.
 */
class EFibonaiCallable extends Fibonacci implements Callable<String> {
    private static int counter = 0;
    private final int id = counter++;
    private int count = 0;

    public EFibonaiCallable(int cnt) {
        count = cnt;
    }

    @Override
    public String call() throws Exception {
        String result = "";
        for (int i = 0; i < count; i++)
            result += next() + " ";
        return "Object id - " + id + " Result : " + result;
    }

    public static void main(String[] arg) {
        System.out.print("Run");
    }
}

public class EFibonachiCalling {
    private static int counter = 0;

    public static Future<String> runTask(final int count) {
        ExecutorService ex = null;
        Callable<String> cs = null;

        if (ex == null) {
            ex = Executors.newCachedThreadPool();

            cs = new Callable<String>() {
                private final int id = counter++;
                private Fibonacci fb = new Fibonacci();

                @Override
                public String call() throws Exception {
                    String result = "";
                    for (int i = 0; i < count; i++)
                        result += fb.next() + " ";
                    return "Object id - " + id + " Result : " + result;
                }
            };
        }

        return ex.submit(cs);
    }

    public static void main(String[] arg) throws ExecutionException {
        Random rd = new Random(47);
//        ExecutorService ex = Executors.newCachedThreadPool();
        ArrayList<Future<String>> al = new ArrayList<Future<String>>();
        for (int i = 0; i < 5; i++)
//            al.add(ex.submit(new EFibonaiCallable(rd.nextInt(25))));
            al.add(runTask(rd.nextInt(25)));
        for (Future<String> fs : al) {
            try {
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
//                ex.shutdown();
            }
        }
    }
}
