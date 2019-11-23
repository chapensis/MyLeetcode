package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。
 * 如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
 * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，
 * 表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 * <p>
 * 来源：力扣（LeetCode） 547
 * 链接：https://leetcode-cn.com/problems/friend-circles
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 */
public class FriendCircle {
    public static void main(String[] args) {
        int[][] M = {{1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}};
        FriendCircle friendCircle = new FriendCircle();
        System.out.println(friendCircle.findCircleNum(M));
        System.out.println(friendCircle.findCircleNum2(M));
    }

    /**
     * 使用深度优先遍历，本质是递归查找，核心是维护一个visit数组
     *
     * @param M
     * @param visit
     * @param i
     */
    public void dfs(int[][] M, int[] visit, int i) {
        for (int j = 0; j < M[i].length; j++) {
            if (M[i][j] == 1 && visit[j] == 0) {
                // 下边两步的顺序不能变，否则会无限递归
                visit[j] = 1;
                dfs(M, visit, j);
            }
        }
    }

    /**
     * 使用深度优先查找朋友圈的数量
     *
     * @param M
     * @return
     */
    public int findCircleNum(int[][] M) {
        int[] visit = new int[M.length];
        int count = 0;
        // 遍历所有人
        for (int i = 0; i < M.length; i++) {
            // 如果没有被访问过，则深度遍历该节点
            if (visit[i] == 0) {
                count++;
                dfs(M, visit, i);
            }
        }
        return count;
    }

    /**
     * 使用广度优先算法遍历，本质是使用队列来保存需要访问的节点
     *
     * @param M
     * @return
     */
    public int findCircleNum2(int[][] M) {
        int[] visit = new int[M.length];
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < M.length; i++) {
            if (visit[i] == 0) {
                count++;
                queue.add(i);
                while (!queue.isEmpty()) {
                    int index = queue.remove();
                    // 需要使用index作为起始点，则标记index为已访问
                    visit[index] = 1;
                    for (int j = 0; j < M[i].length; j++) {
                        // 条件是能连通，并且不是被访问过的点，否则会出现无限循环
                        // （0找1，则0被标记，1再找0的时候，0就不会再添加进队列了）
                        if (M[index][j] == 1 && visit[j] == 0) {
                            queue.add(j);
                        }
                    }
                }
            }
        }
        return count;
    }
}
