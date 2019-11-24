package leetcode;

/**
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 来源：力扣（LeetCode） 29
 * 链接：https://leetcode-cn.com/problems/divide-two-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author yangchang
 */
public class TwoNumDivide {
    public static void main(String[] args) {
        TwoNumDivide twoNumDivide = new TwoNumDivide();
        System.out.println(twoNumDivide.divide(11, 2));
    }

    public int divide(int dividend, int divisor) {
        return myDivide(dividend, divisor);
    }

    public int myDivide(long dividend, long divisor) {
        long dividendLong = dividend;
        long divisorLong = divisor;
        int flag = 1;
        if (divisorLong < 0) {
            flag = 0 - flag;
            divisorLong = 0 - divisorLong;
        }

        if (dividendLong < 0) {
            flag = 0 - flag;
            dividendLong = 0 - dividendLong;
        }

        if (dividendLong < divisorLong) {
            return 0;
        }

        long oringinDividend = dividendLong;
        long oringinDivisor = divisorLong;

        long count = 0;
        // 核心就在于除完了，除数要倍增
        while (dividendLong >= divisorLong) {
            if (count == 0) {
                count = 1;
            } else {
                count += count;
            }

            divisorLong += divisorLong;
            dividendLong = dividendLong - divisorLong;
        }

        // 这里关键在于除数要 除以 2
        count = count + myDivide(oringinDividend - (divisorLong >> 1), oringinDivisor);

        if (flag > 0) {
            if (count > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            return (int) count;
        } else {
            if (0 - count < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            return (int) (0 - count);
        }
    }
}
