package leetcode;

/**
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 * 注意：
 * 0 ≤ x, y < 231.
 * 来源：力扣（LeetCode） 461
 * 链接：https://leetcode-cn.com/problems/hamming-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 * @since 2019-12-22
 */
public class HammingDistance {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        HammingDistance hammingDistance = new HammingDistance();
        System.out.println(hammingDistance.hammingDistance(2, 4));
    }

    /**
     * 计算汉明距离
     *
     * @param x x
     * @param y y
     * @return 汉明距离
     */
    public int hammingDistance(int x, int y) {
        int temp = x ^ y;
        int result = 0;
        while (temp != 0) {
            result = result + (temp & 1);
            temp = temp >> 1;
        }
        return result;
    }
}
