package leetcode;

/**
 * 给你两个长度相同的字符串，s 和 t。
 * 将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。
 * 用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
 * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
 * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
 * 来源：力扣（LeetCode） 1208
 * 链接：https://leetcode-cn.com/problems/get-equal-substrings-within-budget
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 */
public class GetEqualSubstringsWithinBudget {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        GetEqualSubstringsWithinBudget getEqualSubstringsWithinBudget = new GetEqualSubstringsWithinBudget();
        System.out.println(getEqualSubstringsWithinBudget.equalSubstring("abcd", "cdef", 3));
    }

    public int equalSubstring(String s, String t, int maxCost) {
        int[] cost = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            cost[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        int leftIndex = 0;
        int rightIndex = 0;
        int currentCost = cost[rightIndex];
        int length = 0;
        while (true) {
            if (currentCost <= maxCost) {
                length = Math.max(length, rightIndex - leftIndex + 1);
                rightIndex++;
                if (rightIndex < cost.length) {
                    currentCost += cost[rightIndex];
                } else {
                    break;
                }
            } else {
                currentCost -= cost[leftIndex];
                leftIndex++;
            }
        }

        return length;
    }
}
