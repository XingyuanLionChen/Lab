package com.lion.lab.java;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    private static SimpleDateFormat simpleDateFormat;

    private static void init() {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    }

    private static void print(String message) {
        System.out.println(simpleDateFormat.format(new Date()) + " " + Thread.currentThread() + " " + message);
    }

    public static void main(String[] args) {
        init();
        ExecutorService executor = Executors.newCachedThreadPool();
        Object lock = new Object();
        StringBuffer item = new StringBuffer();
        WaitTask task = new WaitTask(lock, item);
        WaitTask task2 = new WaitTask(lock, item);
        NotifyTask task3 = new NotifyTask(lock, item);
//        FutureTask<Integer> futureTask = new FutureTask<>(task);
//        futureTask.run();
//        futureTask.cancel(true);
        executor.submit(task);
        executor.submit(task2);
        executor.submit(task3);
        executor.shutdown();

//        try {
//            print("task运行结果" + result.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
    }

    static class WaitTask implements Callable<Void> {
        private final Object lock;
        private StringBuffer s;

        WaitTask(Object lock, StringBuffer s) {
            this.lock = lock;
            this.s = s;
        }

        @Override
        public Void call() throws Exception {
            synchronized (lock) {
                while (s.length() == 0) {
                    print("wait start");
                    lock.wait();
                    print("wait end");
                }
            }
            return null;
        }
    }

    static class NotifyTask implements Callable<Void> {
        private final Object lock;
        private StringBuffer s;

        NotifyTask(Object lock, StringBuffer s) {
            this.lock = lock;
            this.s = s;
        }

        @Override
        public Void call() {
            synchronized (lock) {
                print("notify start");
                s.append("a");
                lock.notifyAll();
                print("notify end");
            }
            return null;
        }
    }
}
