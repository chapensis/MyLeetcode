package leetcode;

import java.util.ArrayList;
import java.util.List;

public class ProComsumer {
    public static volatile int MAX_SIZE = 5;

    public static String lock = "lock";

    public static List list = new ArrayList();

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            try {
                producer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                comsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void producer() throws Exception {
        while (true) {
            synchronized (lock) {
                Thread.sleep(1000);
                if (list.size() < MAX_SIZE) {
                    list.add(1);
                    System.out.println("add");
                    lock.notify(); // 自己会重新参与竞争，并且更大概率也会获得锁
                }
                // 所以通知完，立马进入wait状态，就一定会把锁释放出去，给有需要的人
                lock.wait();
            }

        }
    }

    public static void comsumer() throws Exception {
        while (true) {
            synchronized (lock) {
                Thread.sleep(1000);
                if (!list.isEmpty()) {
                    list.remove(0);
                    System.out.println("remove");
                    lock.notify();
                }
                lock.wait();
            }
        }
    }
}

