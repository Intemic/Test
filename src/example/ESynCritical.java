package example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ETestSynCritical {
    private static Object sync = new Object();
    private static Lock lk = new ReentrantLock();

    public void m1() {
        synchronized (sync) {
            for (int i = 0; i < 5; i++) {
                System.out.println("m1");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void m2() {
        synchronized (sync) {
            for (int i = 0; i < 5; i++) {
                System.out.println("m2");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void m3() {
        synchronized (sync) {
            for (int i = 0; i < 5; i++) {
                System.out.println("m3");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void m1lock() {
        lk.lock();
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("m1");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lk.unlock();
        }
    }

    public void m2lock() {
        lk.lock();
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("m2");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally{
           lk.unlock();
        }
    }

    public void m3lock() {
        lk.lock();
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("m3");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            lk.unlock();
        }
    }

}

class ETestRun1 implements Runnable {
    @Override
    public void run() {
        new ETestSynCritical().m1lock();
    }
}

class ETestRun2 implements Runnable {
    @Override
    public void run() {
        new ETestSynCritical().m2lock();
    }
}

class ETestRun3 implements Runnable {
    @Override
    public void run() {
        new ETestSynCritical().m3lock();
    }
}

public class ESynCritical {
    public static void main(String[] arg) {
        ExecutorService ex = Executors.newCachedThreadPool();
        ex.execute(new ETestRun1());
        ex.execute(new ETestRun2());
        ex.execute(new ETestRun2());

        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

