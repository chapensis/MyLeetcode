package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 * 返回仅包含 1 的最长（连续）子数组的长度。
 * https://leetcode-cn.com/problems/max-consecutive-ones-iii/
 *
 * @author yangchang
 */
public class MaxConsecutiveOnes3 {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        MaxConsecutiveOnes3 maxConsecutiveOnes = new MaxConsecutiveOnes3();
        int[] A = {1, 0, 0, 0, 1, 1, 0, 0,
                1, 1, 0, 0, 0, 0, 0, 0,
                1, 1, 1, 1, 0, 1, 0, 1,
                1, 1, 1, 1, 1, 0, 1, 0,
                1, 0, 0, 1, 1, 0, 1, 1};
        System.out.println(maxConsecutiveOnes.longestOnes(A, 8));
    }

    /**
     * 计算仅包含 1 的最长（连续）子数组的长度
     *
     * @param A 数组
     * @param K 可转换的K长度
     * @return
     */
    public int longestOnes(int[] A, int K) {
        int startIndex = 0;
        int length = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                // 如果有K，则进来先使用K
                if (K > 0) {
                    queue.add(i);
                    K--;
                } else {
                    // K不够了，再使用之前转换过的，判断是否之前转换过
                    if (queue.size() > 0) {
                        int index = queue.poll();
                        queue.add(i); // 上一个弹出来，这一个可以放进去
                        startIndex = index + 1;
                    } else {
                        startIndex = i + 1;
                    }
                }
            }
            int tempLen = i - startIndex + 1;
            length = Math.max(length, tempLen);
        }
        return length;
    }

    /**
     * 计算仅包含 1 的最长（连续）子数组的长度
     *
     * @param A 数组
     * @param K 可转换的K长度
     * @return
     */
    public int longestOnesLk(int[] A, int K) {
        int max = K;
        int cur = 0;
        int start = 0;
        int end = 0;
        int[] count = new int[2];
        while (end < A.length) {
            int value = A[end];
            int x = ++count[value];
            if (value == 1) {
                cur = x;
            }
            if (end - start + 1 - cur > K) {
                count[A[start++]]--;
            }
            max = Math.max(max, cur + K);
            end++;
        }
        max = cur + K >= A.length ? A.length : max;
        return max;
    }
}
