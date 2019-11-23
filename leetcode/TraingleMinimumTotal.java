package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TraingleMinimumTotal {
    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(new Integer[]{2}));
        triangle.add(Arrays.asList(new Integer[]{3, 4}));
        triangle.add(Arrays.asList(new Integer[]{6, 5, 7}));
        triangle.add(Arrays.asList(new Integer[]{4, 1, 8, 3}));
        TraingleMinimumTotal t = new TraingleMinimumTotal();
        System.out.println(t.minimumTotal(triangle));
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        int length = triangle.size();
        int[][] dp = new int[length][length];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < length; i++) {
            List<Integer> list = triangle.get(i);
            for (int j = 0; j < list.size(); j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + list.get(j);
                } else if (j == (list.size() - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + list.get(j);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + list.get(j);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            if (dp[length - 1][i] < min) {
                min = dp[length - 1][i];
            }
        }
        return min;
    }
}
