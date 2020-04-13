package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 3、k个有序数组的合并
 * ArrayList<Integer> merge(ArrayList<ArrayList<Integer>> lists) {
 * }
 */
public class ArrayMergeTest {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        ArrayMergeTest arrayMergeTest = new ArrayMergeTest();
        List<Integer> list1 = Arrays.asList(-9, -8, -7, -6, -5, -2, -1);
        ArrayList<Integer> arraylist1 = new ArrayList<>(list1);

        List<Integer> list2 = Arrays.asList(1, 3, 5, 7, 9);
        ArrayList<Integer> arraylist2 = new ArrayList<>(list2);

        List<Integer> list3 = Arrays.asList(2, 4, 6, 8, 10);
        ArrayList<Integer> arraylist3 = new ArrayList<>(list3);

        ArrayList<Integer> newArrayList = arrayMergeTest.merge(new ArrayList<>(Arrays.asList(arraylist1, arraylist2, arraylist3)));
        System.out.println(newArrayList);

    }

    /**
     * O(k * n)   logk * n
     * 1 2 3 4 5
     * 2 3 4 5 6
     * 4 5 6 7 8
     * 6 7 8 9 10
     *
     * @param lists
     * @return
     */
    ArrayList<Integer> merge(ArrayList<ArrayList<Integer>> lists) {
        if (lists == null) {
            return null;
        }
        while (lists.size() != 1) {
            ArrayList<Integer> list1 = lists.remove(0);
            ArrayList<Integer> list2 = lists.remove(0);
            ArrayList<Integer> newList = merge(list1, list2);
            lists.add(newList);
        }
        return lists.get(0);
    }

    /**
     * 合并两个有序链表
     *
     * @param list1 链表1
     * @param list2 链表2
     * @return 合并后的链表
     */
    ArrayList<Integer> merge(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> newList = new ArrayList<>();
        int indexList1 = 0;
        int indexList2 = 0;
        while (indexList1 < list1.size() && indexList2 < list2.size()) {
            if (list1.get(indexList1) < list2.get(indexList2)) {
                newList.add(list1.get(indexList1));
                indexList1++;
            } else {
                newList.add(list2.get(indexList2));
                indexList2++;
            }
        }

        while (indexList1 < list1.size()) {
            newList.add(list1.get(indexList1));
            indexList1++;
        }

        while (indexList2 < list2.size()) {
            newList.add(list2.get(indexList2));
            indexList2++;
        }

        return newList;
    }
}
