package leetcode;

/**
 * 我们正在玩一个猜数字游戏。 游戏规则如下：
 * 我从 1 到 n 选择一个数字。 你需要猜我选择了哪个数字。
 * 每次你猜错了，我会告诉你这个数字是大了还是小了。
 * 你调用一个预先定义好的接口 guess(int num)，它会返回 3 个可能的结果（-1，1 或 0）：
 * -1 : 我的数字比较小
 * 1 : 我的数字比较大
 * 0 : 恭喜！你猜对了！
 * 来源：力扣（LeetCode） 374
 * 链接：https://leetcode-cn.com/problems/guess-number-higher-or-lower
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 * @since 2019-12-22
 */
public class GuessGame {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        GuessGame guessGame = new GuessGame();
        System.out.println(guessGame.guessNumber(2126753390));
    }

    /**
     * 在 1-n 中猜我选的数字
     *
     * @param n 最大数字范围n
     * @return 我选的数字
     */
    public int guessNumber(int n) {
        int left = 1;
        int right = n;
        while (left <= right) {
            // 如果是简单的整数相加，下列结果可能会溢出
            // int middle = (left + right) / 2;
            // left和right都是奇数的情况下，这种方法会丢失一位
            // int middle = (left >>> 2) + (right >>> 2);
            int middle = left + (right - left) / 2;
            int result = guess(middle);
            if (result == 0) {
                return middle;
            } else if (result == -1) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    /**
     * -1 : 我的数字比较小
     * 1 : 我的数字比较大
     * 0 : 恭喜！你猜对了！
     *
     * @param num 你猜的数组
     * @return 大于还是小于还是等于
     */
    public int guess(int num) {
        if (1702766719 > num) {
            return 1;
        } else if (1702766719 < num) {
            return -1;
        } else {
            return 0;
        }
    }
}
