package leetcode;

import java.util.HashSet;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 来源：力扣（LeetCode） 3
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 */
public class LongestSubstringWithoutRepeatingCharacters {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters lswrc = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(lswrc.lengthOfLongestSubstring("aab"));
    }

    /**
     * 找出其中不含有重复字符的 最长子串 的长度
     *
     * @param s 字符串
     * @return 不含有重复字符的 最长子串 的长度
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int start = 0;
        int length = 0;
        HashSet<Character> hashSet = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            if (!hashSet.contains(ch)) {
                hashSet.add(ch);
                length = Math.max(length, hashSet.size());
            } else {
                int j = start;
                for (; j < i; j++) {
                    Character repeatCh = s.charAt(j);
                    hashSet.remove(repeatCh);
                    if (repeatCh == ch) {
                        hashSet.add(ch);
                        break;
                    }
                }
                start = ++j;
            }
        }
        return length;
    }
}
