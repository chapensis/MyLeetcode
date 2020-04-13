package leetcode;

import java.util.Arrays;

/**
 * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * 你找到的子数组应是最短的，请输出它的长度。
 * 示例 1:
 * 输入: [2, 6, 4, 8, 10, 9, 15]
 * 输出: 5
 * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * 来源：力扣（LeetCode） 581
 * 链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 * @since 2019-12-22
 */
public class ShortestUnsortedContinuousSubarray {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        ShortestUnsortedContinuousSubarray shortestUnsortedContinuousSubarray = new ShortestUnsortedContinuousSubarray();
        int[] nums = {2, 3, 4, 8, 10, 9, 15};
        System.out.println(shortestUnsortedContinuousSubarray.findUnsortedSubarray(nums));
    }

    /**
     * 找到的子数组应是最短的，输出它的长度
     *
     * @param nums 原始数组
     * @return 最短子数组长度
     */
    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] newNums = new int[nums.length];
        System.arraycopy(nums, 0, newNums, 0, nums.length);
        Arrays.sort(newNums);
        int count = nums.length;
        int left = 0;
        for (left = 0; left < nums.length; left++) {
            if (nums[left] == newNums[left]) {
                count--;
            } else {
                break;
            }
        }

        for (int right = nums.length - 1; right > left; right--) {
            if (nums[right] == newNums[right]) {
                count--;
            } else {
                break;
            }
        }

        return count;
    }
}
