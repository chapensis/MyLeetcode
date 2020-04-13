package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1、有一个已经排序的数组（升序），数组中可能有正数、负数或0，求数组中元素的绝对值最小的数。要求复杂度小于O(n)
 * 例如，数组{-20，-13，-4, 6, 77,200,300,500,600} ，绝对值最小的是-4。
 * Integer find(Arraylist<Integer> list) {
 * }
 */
public class ArrayTest {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        ArrayTest arrayTest = new ArrayTest();
        List<Integer> list = Arrays.asList(-9, -8, -7, -6, -5, -2, -1);
        ArrayList<Integer> arraylist = new ArrayList<>(list);
        System.out.println(arrayTest.find(arraylist));
    }

    /**
     * 二分查找绝对值最小的值
     *
     * @param list 集合
     * @return 绝对值最小的值
     */
    Integer find(ArrayList<Integer> list) {
        int low = 0;
        int high = list.size();
        while (true) {
            int middle = low + (high - low) / 2;
            if ((middle + 1 >= list.size() || Math.abs(list.get(middle)) < Math.abs(list.get(middle + 1)))
                    && (middle - 1 < 0 || Math.abs(list.get(middle)) < Math.abs(list.get(middle - 1)))) {
                return list.get(middle);
            } else if ((middle + 1 >= list.size() || Math.abs(list.get(middle)) < Math.abs(list.get(middle + 1)))
                    && (middle - 1 < 0 || Math.abs(list.get(middle)) > Math.abs(list.get(middle - 1)))) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
    }
}
