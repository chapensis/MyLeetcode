package leetcode;

import java.util.Arrays;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，
 * 找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
 * 示例: 
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 * 来源：力扣（LeetCode） 209
 * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 */
public class MinimumSizeSubarraySum {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        MinimumSizeSubarraySum minimumSizeSubarraySum = new MinimumSizeSubarraySum();
        int[] nums = {2, 3, 1, 2, 4, 3};
        System.out.println(minimumSizeSubarraySum.minSubArrayLen(7, nums));
    }

    /**
     * 找出该数组中满足其和 ≥ s 的长度最小的连续子数组
     *
     * @param s    正整数
     * @param nums 数组
     * @return 最小的连续子数组长度
     */
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0;
        int length = Integer.MAX_VALUE;
        int leftIndex = 0;
        int rightIndex = 0;
        sum += nums[rightIndex];
        while (true) {
            if (sum >= s) {
                length = Math.min(length, rightIndex - leftIndex + 1);
                sum -= nums[leftIndex];
                leftIndex++;
            } else {
                rightIndex++;
                if (rightIndex < nums.length) {
                    sum += nums[rightIndex];
                } else {
                    break;
                }
            }
        }
        return length == Integer.MAX_VALUE ? 0 : length;
    }
}
