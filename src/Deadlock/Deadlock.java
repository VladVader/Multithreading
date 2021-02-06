package Deadlock;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

import static java.util.Arrays.*;

public class Deadlock {

    static volatile boolean content = false;

    static void transfer(Queue<String> in, Queue<String> out) {
        synchronized (in) {
            content = true;
            synchronized (out) {
                String res = in.poll();
                if (res != null) {
                    out.add(res);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Queue<String> in = new ArrayDeque<>(Arrays.<String>asList("foo", "bar", "baz"));
        Queue<String> out = new ArrayDeque<>(Arrays.<String>asList("foo", "bar", "baz"));

        Thread t1 = new Thread(()->{
            for (int i = 0; i < 100000; i++) {
                System.out.println("Thread1: "+i);
                transfer(in,out);
            }
        });

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 100000; i++) {
                System.out.println("Thread1: "+i);
                transfer(out,in);
            }
        });

        System.out.println("Started");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Finished");

    }

}
