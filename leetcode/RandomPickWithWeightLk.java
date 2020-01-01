package leetcode;

/**
 * 给定一个正整数数组 w ，其中 w[i] 代表位置 i 的权重，请写一个函数 pickIndex ，它可以随机地获取位置 i，选取位置 i 的概率与 w[i] 成正比。
 * 说明:
 * 1 <= w.length <= 10000
 * 1 <= w[i] <= 10^5
 * pickIndex 将被调用不超过 10000 次
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/random-pick-with-weight
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 * @since 2019-12-22
 */
public class RandomPickWithWeightLk {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        RandomPickWithWeightLk randomPickWithWeight = new RandomPickWithWeightLk();
        int[] w = {1, 2};
        randomPickWithWeight.Solution(w);
        int num0 = 0;
        int num1 = 0;
        for (int i = 0; i < 10000; i++) {
            int result = randomPickWithWeight.pickIndex();
            if (result == 0) {
                num0++;
            } else if (result == 1) {
                num1++;
            }
        }
        System.out.println(num0);
        System.out.println(num1);
    }

    // 还可以提前计算好权重，weight[i] 就是到索引i的累积权重和，下次就不用再一个个累加了
    private int[] weight;

    /**
     * 假设给定数组是{1,3,2,4},
     * 则对应的权重是 1 4 6 10
     * 对应的区间是[0,1) [1,4) [4,6) [6,10)
     *
     * @param w 初始数组
     */
    public void Solution(int[] w) {
        this.weight = new int[w.length];
        weight[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            weight[i] = weight[i - 1] + w[i];
        }
    }

    /**
     * 按照权重随机选择索引
     * 核心思想：二分查找对应的值
     *
     * @return 索引下标
     */
    public int pickIndex() {
        // 随机生成一个[0, total)的数字
        int val = (int) (Math.random() * weight[weight.length - 1]);
        int low = 0;
        int high = weight.length - 1;
        while (low < high) {
            int middle = low + ((high - low) >> 2);
            // 随机出来的数字小于中间值，则 high = middle,并不是middle-1,因为是有可能就是middle这个数字下标
            if (val < weight[middle]) {
                high = middle;
                // 如果是大于等于，则肯定不是当前数字，因为当前数字代表的区间不包括数字本身，例如：6 代表 [4,6)
            } else if (val >= weight[middle]) {
                low = middle + 1;
            }
        }
        return low;
    }
}
