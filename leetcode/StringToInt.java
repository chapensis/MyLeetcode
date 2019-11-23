package leetcode;

/**
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，
 * 作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * 来源：力扣（LeetCode） 8
 * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 */
public class StringToInt {
    public static void main(String[] args) {
        StringToInt stringToInt = new StringToInt();
        System.out.println(stringToInt.myAtoi("-91283472332"));
    }

    /**
     * 我的解法就是找出合法字符，统一转换
     *
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        if (str == null || str.trim().length() == 0) {
            return 0;
        }
        str = str.trim();
        int flag = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                sb.append(str.charAt(i));
            } else if (str.charAt(i) == '+') {
                // 需要保证+号只出现一次，且出现符号的时候还没出现数字
                if (flag == 0 && sb.length() == 0) {
                    flag = 1;
                } else {
                    break;
                }
            } else if (str.charAt(i) == '-') {
                // 需要保证+号只出现一次，且出现符号的时候还没出现数字
                if (flag == 0 && sb.length() == 0) {
                    flag = -1;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        if (sb.length() == 0) {
            return 0;
        }

        // 没有设置符号就默认是正数
        if (flag == 0) {
            flag = 1;
        }

        double result = flag * Double.parseDouble(sb.toString());
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else {
            return (int) result;
        }
    }
}
