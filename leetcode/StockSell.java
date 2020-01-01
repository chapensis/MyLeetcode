package leetcode;

/**
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 来源：力扣（LeetCode） 309
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 * @since 2019-12-25
 */
public class StockSell {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        StockSell stockSell = new StockSell();
        int [] prices = {1,2,3,0,2};
        System.out.println(stockSell.maxProfit(prices));
    }

    /**
     * 核心思想：动态规划
     * @param prices 股票每天的价格
     * @return 最大收益
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int[][] dp = new int[prices.length + 2][2];
        for (int i = prices.length - 1; i >= 0; i--) {
            // 没有股票的时候，买还是不买
            dp[i][0] = Math.max(dp[i + 1][1] - prices[i], dp[i + 1][0]);
            // 有股票的时候，卖还是不卖，卖了有一天冷却期
            dp[i][1] = Math.max(dp[i + 2][0] + prices[i], dp[i + 1][1]);
        }
        return dp[0][0];
    }
}
