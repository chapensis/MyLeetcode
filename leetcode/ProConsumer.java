package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 生产者消费者模型
 *
 * @author yangchang
 */
public class ProConsumer {
    /**
     * 假设生产者最大的生产数量
     */
    public static volatile int MAX_SIZE = 5;

    public static String lock = "lock";

    public static List list = new ArrayList();

    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) throws Exception {
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        new Thread(() -> {
            try {
                producer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                consumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 生产者
     *
     * @throws Exception
     */
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

    /**
     * 消费者
     *
     * @throws Exception
     */
    public static void consumer() throws Exception {
        while (true) {
            synchronized (lock) {
                Thread.sleep(1000);
                if (!list.isEmpty()) {
                    list.remove(0);
                    System.out.println("remove");
                    lock.notify();
                }
                // 所以通知完，立马进入wait状态，就一定会把锁释放出去，给有需要的人
                lock.wait();
            }
        }
    }
}

