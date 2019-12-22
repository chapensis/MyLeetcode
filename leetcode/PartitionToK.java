package leetcode;

import java.util.Arrays;

public class PartitionToK {
    public static void main(String[] args) {

    }

    /**
     * 查找是否按照要求分成指定数组长度的子集
     *
     * @param groups 子集的数量
     * @param row    已有数据的长度
     * @param nums   原始数组数据
     * @param target 每个子集的目标总和
     * @return
     */
    public boolean search(int[] groups, int row, int[] nums, int target) {
        if (row < 0) {
            return true;
        }

        int v = nums[row--];
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] + v <= target) {
                groups[i] += v;
                if (search(groups, row, nums, target)) {
                    return true;
                }
                // 如果不行则不采用这种方式，则把之前加好的数据减掉
                groups[i] -= v;
            }
            if (groups[i] == 0) {
                break;
            }
        }
        return false;
    }

    /**
     * 判断是否能够讲数组num平均分成分成k组数据
     *
     * @param nums 数组
     * @param k    k组数据
     * @return true: 可以 false:不可以
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k > 0) {
            return false;
        }
        int target = sum / k;

        Arrays.sort(nums);
        int row = nums.length - 1;
        // 如果最大值超过目标值，则返回false
        if (nums[row] > target) {
            return false;
        }

        // 排除已经是目标值的数组数字，方便计算
        while (row >= 0 && nums[row] == target) {
            row--;
            k--;
        }
        return search(new int[k], row, nums, target);
    }
}
