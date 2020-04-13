package leetcode;

/**
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。
 * 对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * 来源：力扣（LeetCode） 494
 * 链接：https://leetcode-cn.com/problems/target-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 * @since 2019-12-22
 */
public class TargetSum {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        TargetSum targetSum = new TargetSum();
        int[] nums = {1, 999};
        System.out.println(targetSum.findTargetSumWays(nums, 998));
        System.out.println(targetSum.findTargetSumWaysLk(nums, 998));
    }

    /**
     * 计算可以使最终数组和为目标数 S 的所有添加符号的方法数
     *
     * @param nums 数组
     * @param S    目标
     * @return 和为目标的计算方式的数量
     */
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 1) {
            // 这是考虑目标是0的情况
            int result = 0;
            if (nums[0] == S) {
                result++;
            }
            if (-nums[0] == S) {
                result++;
            }
            return result;
        }
        int[] subNums = new int[nums.length - 1];
        System.arraycopy(nums, 1, subNums, 0, subNums.length);
        return findTargetSumWays(subNums, S + nums[0]) + findTargetSumWays(subNums, S - nums[0]);
    }

    /**
     * 计算可以使最终数组和为目标数 S 的所有添加符号的方法数
     * 动态规划计算答案
     *
     * @param nums 数组
     * @param S    目标
     * @return 和为目标的计算方式的数量
     */
    public int findTargetSumWaysLk(int[] nums, int S) {
        if (nums == null) {
            return 0;
        }
        if (S > 1000) {
            return 0;
        }
        int[][] dp = new int[nums.length][2001];
        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = -1000; j <= 1000; j++) {
                // 表示之前的钱不可能小于1000
                if (j - nums[i] + 1000 >= 0) {
                    dp[i][j + 1000] += dp[i - 1][j - nums[i] + 1000];
                }
                // 表示之前的钱不可能大于1000
                if (j + nums[i] + 1000 < 2001) {
                    dp[i][j + 1000] += dp[i - 1][j + nums[i] + 1000];
                }
            }
        }
        return dp[nums.length - 1][S + 1000];
    }
}
