package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者模型
 *
 * @author yangchang
 */
public class ProConsumer2 {
    /**
     * 假设生产者最大的生产数量
     */
    public static volatile int MAX_SIZE = 5;

    public static ReentrantLock lock = new ReentrantLock();

    public static Condition producerCondi;

    public static Condition cosumerCondi;

    public static List list = new ArrayList();

    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) throws Exception {
        // condition相当于大门里面的两个房间门
        producerCondi = lock.newCondition();
        cosumerCondi = lock.newCondition();
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
            try {
                lock.lock();
                Thread.sleep(1000);
                if (list.size() == MAX_SIZE) {
                    producerCondi.await();
                }
                list.add(1);
                System.out.println("add");
                cosumerCondi.signal(); // 通知消费者不需要阻塞了
            } finally {
                // 虽然唤醒以后又把锁立马释放了，但是自己还是优先获得锁
                lock.unlock();
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
            try {
                lock.lock();
                Thread.sleep(1000);
                if (list.isEmpty()) {
                    cosumerCondi.await();
                }
                list.remove(0);
                System.out.println("remove");
                producerCondi.signal(); // 通知生产者不需要阻塞了
            } finally {
                // 虽然唤醒以后又把锁立马释放了，但是自己还是优先获得锁
                lock.unlock();
            }
        }
    }
}

