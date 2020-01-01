package leetcode;

/**
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每次交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 返回获得利润的最大值。
 * 来源：力扣（LeetCode） 714
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 * @since 2019-12-25
 */
public class StockSell2 {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        StockSell2 stockSell = new StockSell2();
        int[] prices = {1, 3, 2, 8, 4, 9};
        System.out.println(stockSell.maxProfit(prices, 2));
        System.out.println(stockSell.maxProfit2(prices, 2));
    }

    /**
     * 核心思想：动态规划
     *
     * @param prices 股票每天的价格
     * @param fee    每次交易的手续费
     * @return 最大收益
     */
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int[][] dp = new int[prices.length + 1][2];
        for (int i = prices.length - 1; i >= 0; i--) {
            // 没有股票的时候，买还是不买
            dp[i][0] = Math.max(dp[i + 1][1] - prices[i], dp[i + 1][0]);
            // 有股票的时候，卖还是不卖，卖了需要扣手续费
            dp[i][1] = Math.max(dp[i + 1][0] + prices[i] - fee, dp[i + 1][1]);
        }
        return dp[0][0];
    }

    /**
     * 一种比较绕的思路
     *
     * @param prices 股票每天的价格
     * @param fee    每次交易的手续费
     * @return 最大收益
     */
    public int maxProfit2(int[] prices, int fee) {
        int noHold = 0, hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            // 未持有状态---1.昨天也是未持有状态，2.昨天是持有状态今天卖掉股票。
            noHold = Math.max(noHold, hold + prices[i] - fee);
            // 持有状态-----1.昨天也是持有状态，2.昨天是未持有状态，今天买入股票导致变成持有状态
            hold = Math.max(hold, noHold - prices[i]);
        }
        // 股票价值不可能是负数，所以最后一天未持有状态肯定是最大的
        return noHold;
    }
}
