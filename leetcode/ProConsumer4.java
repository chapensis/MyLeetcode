package leetcode;

import java.util.concurrent.Semaphore;

/**
 * 生产者消费者模型
 *
 * @author yangchang
 */
public class ProConsumer4 {
    /**
     * 假设生产者最大的生产数量
     */
    public static volatile int MAX_SIZE = 1;

    // 仓库的最大容量
    public static final Semaphore notFull = new Semaphore(1);
    // 将线程挂起，等待其他来触发
    public static final Semaphore notEmpty = new Semaphore(0);

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
            notFull.acquire();
            System.out.println("add");
            Thread.sleep(1);
            notEmpty.release();
        }
    }

    /**
     * 消费者
     *
     * @throws Exception
     */
    public static void consumer() throws Exception {
        while (true) {
            notEmpty.acquire();
            System.out.println("remove");
            Thread.sleep(1);
            notFull.release();
        }
    }
}

