package leetcode;

import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
 * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * <p>
 * 来源：力扣（LeetCode） 18
 * 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 * @since 2019-12-22
 */
public class FourNumSumLk {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        FourNumSumLk fourNumSum = new FourNumSumLk();
        int[] nums = {-3, -2, -1, 0, 0, 1, 2, 3};
        List<List<Integer>> result = fourNumSum.fourSum(nums, 0);
        for (List list : result) {
            System.out.println(list);
        }
    }

    /**
     * 最简单的暴力方法，但是可能会超时
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> resultList = new ArrayList<>();
        HashSet<String> hashSet = new HashSet<>();
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                for (int k = j + 1; k < nums.length - 1; k++) {
                    for (int m = k + 1; m < nums.length; m++) {
                        if (nums[i] + nums[j] + nums[k] + nums[m] == target) {
                            List<Integer> list = new ArrayList<>();
                            list.add(nums[i]);
                            list.add(nums[j]);
                            list.add(nums[k]);
                            list.add(nums[m]);
                            Collections.sort(list);
                            if (!hashSet.contains(list.toString())) {
                                hashSet.add(list.toString());
                                resultList.add(list);
                            }
                        }
                    }
                }
            }
        }
        return resultList;
    }

    /**
     * 四个指针
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    if ((nums[i] + nums[j] + nums[left] + nums[right]) == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < nums.length - 1 && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                    } else if ((nums[i] + nums[j] + nums[left] + nums[right]) < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }
}
