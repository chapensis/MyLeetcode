package leetcode;

import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 来源：力扣（LeetCode）16
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 */
public class ThreeNumLeastSum {
    public static void main(String[] args) {
        ThreeNumLeastSum threeNumLeastSum = new ThreeNumLeastSum();
        int[] nums = {1, 2, 4, 8, 16, 32, 64, 128};
        System.out.println(threeNumLeastSum.threeSumClosest(nums, 82));
    }

    /**
     * 排序后利用双指针，可以避免所有数字都组合一次
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closet = Integer.MAX_VALUE / 2;
        for (int i = 0; i < nums.length - 2; i++) {
            // TODO 这里还可以去重哦
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(sum - target) < Math.abs(closet - target)) {
                    closet = sum;
                }

                if (sum == target) {
                    return sum;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return closet;
    }
}
