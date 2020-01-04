package leetcode;

import java.util.Arrays;

/**
 * 初始时有 n 个灯泡关闭。 第 1 轮，你打开所有的灯泡。
 * 第 2 轮，每两个灯泡你关闭一次。
 * 第 3 轮，每三个灯泡切换一次开关（如果关闭则开启，如果开启则关闭）。
 * 第 i 轮，每 i 个灯泡切换一次开关。
 * 对于第 n 轮，你只切换最后一个灯泡的开关。 找出 n 轮后有多少个亮着的灯泡。
 * 示例:
 * 来源：力扣（LeetCode） 319
 * 链接：https://leetcode-cn.com/problems/bulb-switcher
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 * @since 2019-12-22
 */
public class BulbSwitcher {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        BulbSwitcher bulbSwitcher = new BulbSwitcher();
        System.out.println(bulbSwitcher.bulbSwitch(3));
        System.out.println(bulbSwitcher.bulbSwitchLk(4));
        System.out.println(bulbSwitcher.bulbSwitch(5));
        System.out.println(bulbSwitcher.bulbSwitch(6));
        System.out.println(bulbSwitcher.bulbSwitch(7));
        System.out.println(bulbSwitcher.bulbSwitch(8));
        System.out.println(bulbSwitcher.bulbSwitch(9));
        System.out.println(bulbSwitcher.bulbSwitch(10));
    }

    /**
     * 灯泡开关,模拟多次
     *
     * @param n n个灯泡
     * @return
     */
    public int bulbSwitch(int n) {
        int[] bulbs = new int[n + 1];
        Arrays.fill(bulbs, 1);
        for (int i = 2; i <= n; i++) {
            for (int j = i; j < bulbs.length; j = j + i) {
                bulbs[j] = 1 - bulbs[j];
            }
        }
        int count = 0;
        for (int i = 1; i < bulbs.length; i++) {
            count += bulbs[i];
        }
        return count;
    }

    /**
     * 灯泡开关,所有数字的约数都是成对出现的，只有完全平方数不是，所以只有完全平方数的位置的灯是开的
     * 1-n个数中，完全平方数的个数是 (int) Math.sqrt(n) 个
     *
     * @param n n个灯泡
     * @return
     */
    public int bulbSwitchLk(int n) {
        return (int) Math.sqrt(n);
    }
}
