package leetcode;

import java.util.Random;

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
public class RandomPickWithWeight {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        RandomPickWithWeight randomPickWithWeight = new RandomPickWithWeight();
        int[] w = {0, 3};
        randomPickWithWeight.Solution(w);
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
    }

    // 还可以提前计算好权重，weight[i] 就是到索引i的累积权重和，下次就不用再一个个累加了
    private int[] weight;
    private int total;

    public void Solution(int[] w) {
        this.weight = w;
        for (int i = 0; i < weight.length; i++) {
            total = total + weight[i];
        }
    }

    /**
     * 按照权重随机选择索引
     *
     * @return 索引下标
     */
    public int pickIndex() {
        // 随机生成一个[0, total)的数字
        int val = (int) (Math.random() * total);
        int result = 0;
        // 计算累积和，到了累积下标才超过说明就是随机选择的数字
        for (int i = 0; i < weight.length; i++) {
            result = result + weight[i];
            if (result > val) {
                return i;
            }
        }
        // 正常是不会到这一步的
        return weight.length - 1;
    }
}
