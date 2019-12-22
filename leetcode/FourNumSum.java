package leetcode;

import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
 * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 *
 * 来源：力扣（LeetCode） 18
 * 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author yangchang
 * @since 2019-12-22
 */
public class FourNumSum {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        FourNumSum fourNumSum = new FourNumSum();
        int[] nums = {1, 0, -1, 0, -2, 2};
        List<List<Integer>> result = fourNumSum.fourSum(nums, 0);
        for (List list : result) {
            System.out.println(list);
        }
    }

    /**
     * 利用HashMap
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> resultList = new ArrayList<>();
        HashMap<Integer, List<List<Integer>>> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                Integer key = nums[i] + nums[j];
                // 我的value总是递增的
                List<Integer> valueList = Arrays.asList(i, j);
                int targetValue = target - key;
                if (hashMap.containsKey(targetValue)) {
                    List<List<Integer>> list = hashMap.get(targetValue);
                    for (List<Integer> subList : list) {
                        List<Integer> result = mergeList(subList, valueList);
                        if (result.size() > 0) {
                            resultList.add(result);
                        }
                    }
                }

                if (hashMap.containsKey(key)) {
                    List<List<Integer>> list = hashMap.get(key);
                    list.add(valueList);
                } else {
                    List<List<Integer>> list = new ArrayList<>();
                    list.add(valueList);
                    hashMap.put(key, list);
                }
            }
        }

        List<List<Integer>> resultNumList = new ArrayList<>();
        HashSet<String> hashSet = new HashSet<>();
        for (List<Integer> indexList : resultList) {
            List<Integer> numList = new ArrayList<>();
            for (Integer i : indexList) {
                numList.add(nums[i]);
            }
            Collections.sort(numList);
            if (!hashSet.contains(numList.toString())) {
                resultNumList.add(numList);
                hashSet.add(numList.toString());
            }
        }
        return resultNumList;
    }

    /**
     * 合并两个集合，并且要保证索引是递增的
     *
     * @param source
     * @param target
     * @return
     */
    public List<Integer> mergeList(List<Integer> source, List<Integer> target) {
        List<Integer> list = new ArrayList<>();
        if (source.get(1) >= target.get(0)) {
            return list;
        }
        list.addAll(source);
        list.addAll(target);
        return list;
    }
}
