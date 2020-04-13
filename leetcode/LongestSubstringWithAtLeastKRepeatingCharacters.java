package leetcode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。
 * 示例 1:
 * 输入:
 * s = "aaabb", k = 3
 * 输出:
 * 3
 * 最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 * 来源：力扣（LeetCode） 395
 * 链接：https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        LongestSubstringWithAtLeastKRepeatingCharacters lswakrc = new LongestSubstringWithAtLeastKRepeatingCharacters();
        System.out.println(lswakrc.longestSubstring("raaaabbbccccb", 4));
    }

    /**
     * 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度
     *
     * @param s 字符串
     * @param k 最少次数
     * @return 最长字串长度
     */
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (k == 1) {
            return s.length();
        }
//        HashMap<Character, Integer> totalHashMap = new HashMap<>();
//        for (int i = 0; i < s.length(); i++) {
//            Character ch = s.charAt(i);
//            totalHashMap.put(ch, totalHashMap.getOrDefault(ch, 0) + 1);
//        }
        int maxLength = 0;
        for (int i = 0; i < s.length() - Math.max(k, maxLength); i++) {
            int length = 0;
            HashMap<Character, Integer> hashMap = new HashMap<>();
            for (int m = i; m < i + k; m++) {
                Character ch = s.charAt(m);
                hashMap.put(ch, hashMap.getOrDefault(ch, 0) + 1);
            }
            if (isOK(hashMap, k)) {
                length = k;
            }
            for (int j = i + k; j < s.length(); j++) {
                Character ch = s.charAt(j);
                hashMap.put(ch, hashMap.getOrDefault(ch, 0) + 1);
                if (isOK(hashMap, k)) {
                    length = j - i + 1;
                }
            }
            maxLength = Math.max(maxLength, length);
        }
        return maxLength;
    }

    /**
     * 判断在hashmap中的所有值是否都满足要求
     *
     * @param hashMap
     * @param k
     * @return
     */
    public boolean isOK(HashMap<Character, Integer> hashMap, int k) {
        for (int value : hashMap.values()) {
            if (value < k) {
                return false;
            }
        }
        return true;
    }
}
