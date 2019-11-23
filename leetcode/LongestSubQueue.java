package leetcode;

import java.util.ArrayList;
import java.util.HashMap;

public class LongestSubQueue {
    public static void main(String[] args) {
        LongestSubQueue longestSubQueue = new LongestSubQueue();
        int[] A = {20, 1, 15, 3, 10, 5, 8};
        System.out.println(longestSubQueue.longestArithSeqLength(A));
    }

    public int longestArithSeqLength(int[] A) {
        if (A == null) {
            return 0;
        }
        if (A.length <= 2) {
            return A.length;
        }
        int longestArithSeqLengthValue = 2;
        ArrayList<HashMap<Integer, Integer>> list = new ArrayList<>();
        list.add(new HashMap<>());
        for (int i = 1; i < A.length; i++) {
            HashMap<Integer, Integer> curHashMap = new HashMap<>();
            for (int j = 0; j < i; j++) {
                HashMap<Integer, Integer> lastHashMap = list.get(j);
                int diff = A[i] - A[j];
                if (lastHashMap.containsKey(diff)) {
                    int curValue = lastHashMap.get(diff);
                    curValue += 1;
                    curHashMap.put(diff, curValue);
                    if (curValue > longestArithSeqLengthValue) {
                        longestArithSeqLengthValue = curValue;
                    }
                } else {
                    curHashMap.put(diff, 2);
                }
            }
            list.add(curHashMap);
        }
        return longestArithSeqLengthValue;
    }
}
