package leetcode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * 进阶：
 * 你能在线性时间复杂度内解决此题吗？
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 * @since 2019-12-22
 */
public class SlidingWindowMaximum {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        SlidingWindowMaximum slidingWindowMaximum = new SlidingWindowMaximum();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(slidingWindowMaximum.maxSlidingWindow(nums, 3)));
    }

    /**
     * 滑动窗口中的最大值的数组
     * 核心思想：双端队列 + 单调递减
     *
     * @param nums 数组
     * @param k k
     * @return 最大值数组结果
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!linkedList.isEmpty() && linkedList.getLast() < nums[i]) {
                linkedList.removeLast();
            }
            linkedList.add(nums[i]);
        }
        result[0] = linkedList.getFirst();
        for (int j = k; j < nums.length; j++) {
            if (linkedList.getFirst() == nums[j - k]) {
                linkedList.removeFirst();
            }
            while (!linkedList.isEmpty() && linkedList.getLast() < nums[j]) {
                linkedList.removeLast();
            }
            linkedList.add(nums[j]);
            result[j - k + 1] = linkedList.getFirst();
        }
        return result;
    }
}
