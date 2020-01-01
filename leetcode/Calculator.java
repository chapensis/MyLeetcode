package leetcode;

/**
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格
 * leetcode 224
 * https://leetcode-cn.com/problems/basic-calculator/
 *
 * @author yangchang
 * @since 2019-12-22
 */
public class Calculator {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.calculate("(5-(1+(5)))"));
    }

    /**
     * 表达式计算
     *
     * @param s 字符串表达式
     * @return 计算结果
     */
    public int calculate(String s) {
        if (s == null) {
            return 0;
        }

        s = s.replace(" ", "");
        if (s.length() == 0 || "".equals(s)) {
            return 0;
        }

        if (s.indexOf('(') >= 0) {
            int lastLeftBracket = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    lastLeftBracket = i;
                } else if (s.charAt(i) == ')') {
                    String subS = s.substring(lastLeftBracket + 1, i);
                    return calculate(s.substring(0, lastLeftBracket) + calculate(subS) + s.substring(i + 1, s.length()));
                }
            }
        }

        s = s.replace("+-", "-");
        s = s.replace("--", "+");
        String[] addArray = s.split("\\+");
        int result = 0;
        for (int i = 0; i < addArray.length; i++) {
            String addStr = addArray[i];
            result = result + getSubResult(addStr);
        }
        return result;
    }

    /**
     * 计算普通减法
     *
     * @param s 字符串
     * @return 结果
     */
    public int getSubResult(String s) {
        // 可能是减号开头，因此需要添加 0
        if (s.startsWith("-")) {
            s = "0" + s;
        }
        String[] subArray = s.split("-");
        int result = Integer.parseInt(subArray[0]);
        for (int i = 1; i < subArray.length; i++) {
            result = result - Integer.parseInt(subArray[i]);
        }
        return result;
    }
}
