package leetcode;

import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 来源：力扣（LeetCode）15
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 */
public class ThreeNumSum {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        ThreeNumSum threeNumSum = new ThreeNumSum();
        List<List<Integer>> result = threeNumSum.threeSum(nums);
        for (List list : result) {
            System.out.println(list);
        }
    }

    /**
     * 暴力破解三数之和，有超时用例
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        HashSet<String> hashSet = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        Collections.sort(list);
                        if (!hashSet.contains(list.toString())) {
                            hashSet.add(list.toString());
                            resultList.add(list);
                        }
                    }
                }
            }
        }
        return resultList;
    }

    /**
     * 排序之后，使用双指针，才能不超时
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            // 不要和前一个重复
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int leftIndex = i + 1;
            int rightIndex = nums.length - 1;
            while (leftIndex < rightIndex) {
                int sum = nums[leftIndex] + nums[rightIndex] + nums[i];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[leftIndex], nums[rightIndex]));

                    // 只需要在左边找一个不重复的即可
                    while (leftIndex < rightIndex && nums[leftIndex] == nums[leftIndex + 1]) {
                        leftIndex++;
                    }
                    leftIndex++;
                } else if (sum < 0) {
                    leftIndex++;
                } else {
                    rightIndex--;
                }
            }
        }
        return result;
    }

    /**
     * 利用hashmap存数据，还是会有超时情况发生
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> indexList = new ArrayList<>();
        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (hashMap.containsKey(nums[j])) {
                    List<Integer> list = hashMap.get(nums[j]);
                    if (!list.contains(j)) {
                        list.add(j);
                        indexList.add(list);
                        hashMap.remove(nums[j]);
                    }
                }
                List<Integer> list = new ArrayList<>();
                list.add(i);
                list.add(j);
                hashMap.put(0 - nums[i] - nums[j], list);
            }
        }

        List<List<Integer>> numsList = new ArrayList<>();
        HashSet<String> hashSet = new HashSet<>();
        for (List<Integer> indexs : indexList) {
            List<Integer> numList = new ArrayList<>();
            for (Integer index : indexs) {
                numList.add(nums[index]);
            }
            Collections.sort(numList);
            if (!hashSet.contains(numList.toString())) {
                numsList.add(numList);
                hashSet.add(numList.toString());
            }
        }
        return numsList;
    }
}
