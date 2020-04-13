package leetcode;

/**
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 * leetcode 495
 * https://leetcode-cn.com/problems/max-consecutive-ones/
 *
 * @author yangchang
 */
public class MaxConsecutiveOnes {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        MaxConsecutiveOnes maxConsecutiveOnes = new MaxConsecutiveOnes();
        System.out.println(maxConsecutiveOnes.findMaxConsecutiveOnes(null));
    }

    /**
     * 计算其中最大连续1的个数
     *
     * @param nums 数组
     * @return 最大连续1的个数
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxLength = 0;
        int length = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                length += 1;
                maxLength = Math.max(maxLength, length);
            } else {
                length = 0;
            }
        }
        return maxLength;
    }

}
