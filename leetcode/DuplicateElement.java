package leetcode;

/**
 * 给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，
 * 使得 nums [i] 和 nums [j] 的差的绝对值最大为 t，并且 i 和 j 之间的差的绝对值最大为 ķ。
 * <p>
 * 来源：力扣（LeetCode） 220
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 * @since 2019-12-22
 */
public class DuplicateElement {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        DuplicateElement duplicateElement = new DuplicateElement();
        int[] nums = {-1, 2147483647};
        System.out.println(duplicateElement.containsNearbyAlmostDuplicate(nums, 1, 2147483647));
    }

    /**
     * 暴力求解答案，遍历所有可能的答案
     *
     * @param nums 给定数组
     * @param k    索引差的绝对值最大为 k
     * @param t    数字差的绝对值最大为 t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length; i++) {
            // 因为索引差不大于k,所以是从1到k
            for (int j = 1; j <= k; j++) {
                if (i + j < nums.length) {
                    if (Math.abs((long) nums[i] - nums[i + j]) <= t) {
                        return true;
                    }
                } else {
                    break;
                }
            }
        }
        return false;
    }
}
