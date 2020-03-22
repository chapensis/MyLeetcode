package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 * 来源：力扣（LeetCode） 448
 * 链接：https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 * @since 2019-12-22
 */
public class FindAllNumbersDisappearedInAnArray {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        FindAllNumbersDisappearedInAnArray fandiaa = new FindAllNumbersDisappearedInAnArray();
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
//        System.out.println(fandiaa.findDisappearedNumbers(nums));
        System.out.println(fandiaa.findDisappearedNumbersLk(nums));
    }

    /**
     * 找到消失的数字
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        int startIndex = 1;
        int nextIndex = 1;
        boolean isStart = true;
        while (startIndex <= nums.length) {
            int curIndex = nextIndex;
            int curNum = nums[curIndex - 1];
            if (isStart && curIndex != curNum) {
                nums[curIndex - 1] = -1;
            } else {
                nums[curIndex - 1] = curIndex;
            }
            if (curNum == -1 || curNum == curIndex) {
                nextIndex = ++startIndex;
                isStart = true;
            } else {
                nextIndex = curNum;
                isStart = false;
            }
        }
        for (int i = 1; i <= nums.length; i++) {
            if (nums[i - 1] == -1) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * 找到消失的数字
     * 把对应的位置的数设置为负数,一轮过后，全部是正数的位置就是缺失的数
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbersLk(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] = 0 - nums[index];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        return result;
    }
}
