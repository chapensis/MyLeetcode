package leetcode;

/**
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 来源：力扣（LeetCode）　188
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 * @since 2019-12-25
 */
public class StockSell３ {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        StockSell３ stockSell = new StockSell３();
        int[] prices = {3, 2, 6, 5, 0, 3};
        System.out.println(stockSell.maxProfit(2, prices));
    }

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int[][][] dp = new int[prices.length + 1][k + 1][2];
        for (int i = prices.length - 1; i >= 0; i--) {
            for (int j = 1; j <= k; j++) {
                dp[i][j][0] = Math.max(dp[i + 1][j][1] - prices[i], dp[i + 1][j][0]);
                dp[i][j][1] = Math.max(dp[i + 1][j - 1][0] + prices[i], dp[i + 1][j][1]);
            }
        }
        return dp[0][k][0];
    }
}
