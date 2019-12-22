package leetcode;

import java.util.HashMap;

/**
 * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
 * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
 * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 * 你的目标是确切地知道 F 的值是多少。
 * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
 * 来源：力扣（LeetCode） 887
 * 链接：https://leetcode-cn.com/problems/super-egg-drop
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 * @since 2019-12-22
 */
public class EggDrop {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        EggDrop eggDrop = new EggDrop();
        System.out.println(eggDrop.superEggDrop(2, 6));
        System.out.println(eggDrop.superEggDropLk(2, 6));
    }

    /**
     * 常规的动态规划解题法，会超时 97 / 121 个通过测试用例
     *
     * @param K 鸡蛋个数
     * @param N 楼层高度
     * @return
     */
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            dp[1][i] = i;
        }

        for (int i = 2; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                // 进入这个循环，表示现在有i个鸡蛋，j层楼
                int min = j;
                // 在这里，每层楼都试一下，看看哪层楼的最坏情况次数最少
                for (int x = 1; x <= j; x++) {
                    // 在每一层判断鸡蛋碎还是不碎，碎了则 i->i-1,j->j-1 不碎则 i不变，j -> N-j
                    int max = 1 + Math.max(dp[i - 1][x - 1], dp[i][j - x]);
                    min = Math.min(min, max);
                }
                dp[i][j] = min;
            }
        }
        return dp[K][N];
    }

    /**
     * 用于缓存，防止重复计算
     */
    HashMap<String, Integer> hashMap = new HashMap<>();

    /**
     * 根据每种情况的单调性，进行二分
     * 但是这种情况时间和空间复杂度依然很高很高，差点没过
     *
     * @param K 鸡蛋个数
     * @param N 楼层高度
     * @return
     */
    public int superEggDropLk(int K, int N) {
        // 只有一个鸡蛋的时候，有多少楼尝试多少次
        if (K == 1) {
            return N;
        }

        // 没鸡蛋或者没楼层的，那就直接返回0
        if (K == 0 || N == 0) {
            return 0;
        }

        String key = K + "-" + N;
        if (hashMap.containsKey(key)) {
            return hashMap.get(key);
        }

        int low = 1;
        int high = N;
        while (low + 1 < high) {
            int middle = (low + high) / 2;
            int lowValue = superEggDropLk(K - 1, middle - 1);
            int highValue = superEggDropLk(K, N - middle);
            if (lowValue < highValue) {
                low = middle;
            } else if (lowValue > highValue) {
                high = middle;
            } else {
                low = high = middle;
            }
        }

        // 最高最低都需要尝试一次
        int minimum = 1 + Math.min(
                Math.max(superEggDropLk(K - 1, low - 1), superEggDropLk(K, N - low)),
                Math.max(superEggDropLk(K - 1, high - 1), superEggDropLk(K, N - high)));
        hashMap.put(key, minimum);
        return minimum;
    }
}
