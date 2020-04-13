package leetcode;

import java.util.Stack;

/**
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格
 * leetcode 224
 * https://leetcode-cn.com/problems/basic-calculator/
 *
 * @author yangchang
 * @since 2019-12-22
 */
public class CalculatorLk {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        CalculatorLk calculator = new CalculatorLk();
//        System.out.println(calculator.calculate("(1+(4+5+2)-3)+(6+8)"));
        System.out.println(calculator.calculate("1*2-3/4+5*6-7*8+9/10"));
    }

    /**
     * 表达式计算
     *
     * @param s 字符串表达式
     * @return 计算结果
     */
    public int calculate(String s) {
        // 存放待计算的数
        Stack<Integer> nums = new Stack<>();
        // 存放计算符号
        Stack<Character> ops = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '+' || ch == '-') {
                if (sb.length() > 0) {
                    nums.push(Integer.parseInt(sb.toString()));
                    sb.delete(0, sb.length());
                }
                // 如果有符号且满足条件一定需要计算完
                while (!ops.isEmpty()) {
                    int result = doOperate(nums, ops);
                    nums.push(result);
                }
                ops.push(ch);
            } else if (ch == '*' || ch == '/') {
                if (sb.length() > 0) {
                    nums.push(Integer.parseInt(sb.toString()));
                    sb.delete(0, sb.length());
                }
                // 如果有符号且满足条件一定需要计算完
                while (!ops.isEmpty() && (ops.peek() == '*' || ops.peek() == '/')) {
                    int result = doOperate(nums, ops);
                    nums.push(result);
                }
                ops.push(ch);
            } else if (ch == '(') {
                // 遇到左括号就去寻找右括号，然后递归计算括号内的数值
                int endIndex = findEndBracket(s, i);
                int result = calculate(s.substring(i + 1, endIndex));
                nums.push(result);
                // i这时候就有一个大的跨度变化
                i = endIndex;
            } else if (Character.isDigit(ch)) {
                sb.append(ch);
            }
        }

        if (sb.length() > 0) {
            nums.push(Integer.parseInt(sb.toString()));
        }
        while (!ops.isEmpty()) {
            int result = doOperate(nums, ops);
            nums.push(result);
        }
        return nums.pop();
    }

    /**
     * 返回匹配的括号
     *
     * @param str        字符串
     * @param startIndex 搜索开始的位置
     * @return 匹配的括号结束位置
     */
    public int findEndBracket(String str, int startIndex) {
        int leftBracket = 0;
        for (int i = startIndex; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                leftBracket++;
            } else if (str.charAt(i) == ')') {
                leftBracket--;
                if (leftBracket == 0) {
                    return i;
                }
            }
        }
        return 0;
    }

    /**
     * 操作一次
     * @param nums 数字
     * @param ops 操作符
     * @return
     */
    public int doOperate(Stack<Integer> nums, Stack<Character> ops) {
        int num2 = nums.pop();
        int num1 = nums.pop();
        char op = ops.pop();
        int result = calculate(num1, num2, op);
        return result;
    }

    /**
     * 按照指定符号计算结果
     *
     * @param num1 数字1
     * @param num2 数字2
     * @param op   操作符
     * @return 结果
     */
    public int calculate(int num1, int num2, char op) {
        if (op == '+') {
            return num1 + num2;
        } else if (op == '-') {
            return num1 - num2;
        } else if (op == '*') {
            return num1 * num2;
        } else if (op == '/') {
            return num1 / num2;
        } else {
            return 0;
        }
    }
}
