package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 2、佳佳的老师在黑板上写了一个由 n 个正整数组成的数列，要求佳佳进行如下操作：
 * 每次擦去其中的两个数 a 和 b，然后在数列中加入一个数a×b+1，
 * 如此下去直至黑板上剩下一个数为止，在所有按这种操作方式最后得到的数中，
 * 最大的为max，最小的为 min， 则该数列的极差定义为 M=max-min。
 * 由于佳佳忙于准备期末考试，现请你帮助他，对于给定的数列，计算出相应的极差 M。
 */
public class ShulieTest {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        ShulieTest shulieTest = new ShulieTest();
        List<Integer> list = Arrays.asList(1, 2, 3);
        ArrayList<Integer> arraylist = new ArrayList<>(list);
        System.out.println(shulieTest.getM(arraylist));
    }

    /**
     * 求积差
     * max就是每次选择最小的两个数进行a*b + 1操作，
     * min就是每次选择最大的两个数进行a*b + 1操作
     * {1,2,3,4,5,6,7}
     *
     * @param list 集合
     * @return 极差
     */
    Integer getM(ArrayList<Integer> list) {
        int maxValue = getMax(list);
        int minValue = getMin(list);
        System.out.println(maxValue);
        System.out.println(minValue);
        int valm = maxValue - minValue;
        return valm;
    }

    /**
     * 获得最大值
     *
     * @param list 集合
     * @return 最大值
     */
    public int getMax(ArrayList<Integer> list) {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>(list);
        while (minQueue.size() != 1) {
            int vala = minQueue.poll();
            int valb = minQueue.poll();
            minQueue.add(vala * valb + 1);
        }
        return minQueue.poll();
    }

    /**
     * 获得最大值
     *
     * @param list 集合
     * @return 最大值
     */
    public int getMin(ArrayList<Integer> list) {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (Integer val : list) {
            minQueue.add(val);
        }
        while (minQueue.size() != 1) {
            int vala = minQueue.poll();
            int valb = minQueue.poll();
            minQueue.add(vala * valb + 1);
        }
        return minQueue.poll();
    }
}
