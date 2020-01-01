package leetcode;

import java.util.HashMap;

/**
 * 我们正在玩一个猜数游戏，游戏规则如下：
 * 我从 1 到 n 之间选择一个数字，你来猜我选了哪个数字。
 * 每次你猜错了，我都会告诉你，我选的数字比你的大了或者小了。
 * 然而，当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。直到你猜到我选的数字，你才算赢得了这个游戏。
 * 来源：力扣（LeetCode） 375
 * 链接：https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 * @since 2019-12-22
 */
public class GuessGame2 {
    /**
     * 用于存储中间结果
     */
    private HashMap<String, Integer> hashMap = new HashMap<>();

    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        GuessGame2 guessGame = new GuessGame2();
        System.out.println(guessGame.getMoneyAmount(10));
        System.out.println(guessGame.getMoneyAmountLk(10));
    }

    /**
     * 在 1-n 中猜我选的数字，要求代价最低
     *
     * @param n 最大数字范围n
     * @return 最低代码
     */
    public int getMoneyAmount(int n) {
        return guessNumber(1, n);
    }

    /**
     * 在最低数字和最高数字之间猜数字
     *
     * @param low  最低数字
     * @param high 最高数字
     * @return 猜测的最低代价
     */
    public int guessNumber(int low, int high) {
        if (low >= high) {
            return 0;
        }
        String key = low + "-" + high;
        if (hashMap.containsKey(key)) {
            return hashMap.get(key);
        }
        int min = Integer.MAX_VALUE;
        // 循环遍历从low到high，找出猜测代价最小的
        for (int i = (low + high) / 2; i <= high; i++) {
            int max = Math.max(guessNumber(low, i - 1), guessNumber(i + 1, high)) + i;
            min = Math.min(min, max);
        }
        hashMap.put(key, min);
        return min;
    }

    /**
     * 在 1-n 中猜我选的数字，要求代价最低
     *
     * @param n 最大数字范围n
     * @return 最低代码
     */
    public int getMoneyAmountLk(int n) {
        int[][] dp = new int[n + 1][n + 1];
        // 长度从2开始
        for (int len = 2; len <= n; len++) {
            for (int start = 1; start <= n - len + 1; start++) {
                int minres = Integer.MAX_VALUE;
                // 从开始start 到结束 end 找出最小代价
                int end = start + len - 1;
                for (int piv = start; piv < end; piv++) {
                    int res = piv + Math.max(dp[start][piv - 1], dp[piv + 1][start + len - 1]);
                    minres = Math.min(res, minres);
                }
                dp[start][start + len - 1] = minres;
            }
        }
        return dp[1][n];
    }
}
