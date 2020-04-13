package leetcode;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 生产者消费者模型
 *
 * @author yangchang
 */
public class ProConsumer3 {
    /**
     * 假设生产者最大的生产数量
     */
    public static volatile int MAX_SIZE = 1;

    public static ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<Integer>(MAX_SIZE);

    /**
     * 主函数
     *
     * @param args 参数
     */
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
            blockingQueue.put(1);
            System.out.println("add");
            Thread.sleep(1);
        }
    }

    /**
     * 消费者
     *
     * @throws Exception
     */
    public static void consumer() throws Exception {
        while (true) {
            blockingQueue.take();
            System.out.println("remove");
            Thread.sleep(1);
        }
    }
}

