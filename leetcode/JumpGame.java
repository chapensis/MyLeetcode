package leetcode;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个位置。
 * leetcode 55
 * https://leetcode-cn.com/problems/jump-game/
 *
 * @author yangchang
 */
public class JumpGame {
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 1, 4};
        JumpGame jumpGame = new JumpGame();
        System.out.println(jumpGame.canJump(nums));
    }

    /**
     * 每次找下一跳的位置和当前跳的距离最大的位置，作为新位置
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        int pos = 0;
        while (nums[pos] != 0) {
            int num = nums[pos];
            int max = 0;
            int newPos = 0;
            for (int step = 1; step <= num; step++) {
                if (step + pos >= nums.length - 1) {
                    return true;
                }
                if (step + nums[pos + step] > max) {
                    max = step + nums[pos + step];
                    newPos = pos + step;
                }
            }
            pos = newPos;
        }
        return false;
    }
}
