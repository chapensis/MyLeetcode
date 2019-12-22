package leetcode;

import java.util.*;

/**
 * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。
 * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，
 * 或者在待命状态。
 * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，
 * 因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * 你需要计算完成所有任务所需要的最短时间。
 * 来源：力扣（LeetCode） 621
 * 链接：https://leetcode-cn.com/problems/task-scheduler
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 * @since 2019-12-22
 */
public class TaskScheduler {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        TaskScheduler taskScheduler = new TaskScheduler();
        char[] tasks = {'A', 'A', 'A', 'B', 'C', 'D'};
        System.out.println(taskScheduler.leastInterval(tasks, 2));
    }

    public int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            Character character = tasks[i];
            if (hashMap.containsKey(character)) {
                hashMap.put(character, hashMap.get(character) + 1);
            } else {
                hashMap.put(character, 1);
            }
        }

        List<Task> list = new ArrayList<>();
        Iterator<Map.Entry<Character, Integer>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Character, Integer> entry = iterator.next();
            Character taskName = entry.getKey();
            int taskNum = entry.getValue();
            Task task = new Task(taskName, taskNum);
            list.add(task);
        }

        // 按照任务数量从大到小排列
        Collections.sort(list, (o1, o2) -> o2.taskNum - o1.taskNum);

        int startTime = 0;
        int moveTime = 0;
        while (list.size() > 0) {
            Iterator iterator1 = list.iterator();
            int i = 0;
            while (iterator1.hasNext()) {
                if (i == 0 && moveTime != 0) {
                    if (moveTime - startTime <= n) {
                        moveTime = startTime + n + 1;
                    }
                    startTime = moveTime;
                } else if (moveTime - startTime > n) {
                    // 如果执行时间大于第一个任务的冷却期，则重新执行第一个数量最大的任务
                    break;
                }
                i++;
                // 取出每个要执行的任务，将任务数量减一
                Task task = (Task) iterator1.next();
                task.taskNum = task.taskNum - 1;
                if (task.taskNum == 0) {
                    iterator1.remove();
                }
                moveTime++;
            }
            // 按照任务数量重新从大到小排列
            Collections.sort(list, (o1, o2) -> o2.taskNum - o1.taskNum);
        }
        return moveTime;
    }

    class Task {
        Character taskName;
        int taskNum;

        public Task(Character taskName, int taskNum) {
            this.taskName = taskName;
            this.taskNum = taskNum;
        }
    }

    /**
     * 由于相同的任务之间必须有 n 的冷却时间，所以我们可以想到按照任务的数量来安排它们，即一种任务的出现次数越多，我们就越早地安排。例如有 5 种任务 A, B, C, D, E，且它们分别有 6, 1, 1, 1, 1 个时，假设冷却时间 n = 2，那么我们首先安排任务 A，随后在 2 单位的冷却时间里，我们安排任务 B, C，随后继续安排任务 A，再安排任务 D, E，以此类推。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/task-scheduler/solution/ren-wu-diao-du-qi-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastIntervalLk(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c : tasks) {
            map[c - 'A']++;
        }
        Arrays.sort(map);
        int time = 0;
        while (map[25] > 0) {
            int i = 0;
            // 反正每次都需要循环 n 次
            while (i <= n) {
                if (map[25] == 0) {
                    break;
                }
                if (i < 26 && map[25 - i] > 0) {
                    map[25 - i]--;
                }
                time++;
                i++;
            }
            // 核心在于，一次循环完了，将数组重新排序
            Arrays.sort(map);
        }
        return time;
    }

    /**
     * 解题思路：
     * 1、将任务按类型分组，正好A-Z用一个int[26]保存任务类型个数
     * 2、对数组进行排序，优先排列个数（count）最大的任务，
     * 如题得到的时间至少为 retCount =（count-1）* (n+1) + 1 ==> A->X->X->A->X->X->A(X为其他任务或者待命)
     * 3、再排序下一个任务，如果下一个任务B个数和最大任务数一致，
     * 则retCount++ ==> A->B->X->A->B->X->A->B
     * 4、如果空位都插满之后还有任务，那就随便在这些间隔里面插入就可以，
     * 因为间隔长度肯定会大于n，在这种情况下就是任务的总数是最小所需时间
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastIntervalLk2(char[] tasks, int n) {
        if (tasks.length <= 1 || n < 1) {
            return tasks.length;
        }
        // 步骤1
        int[] counts = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            counts[tasks[i] - 'A']++;
        }
        // 步骤2
        Arrays.sort(counts);
        int maxCount = counts[25];
        int retCount = (maxCount - 1) * (n + 1) + 1;
        int i = 24;
        // 步骤3
        while (i >= 0 && counts[i] == maxCount) {
            retCount++;
            i--;
        }
        // 步骤4
        return Math.max(retCount, tasks.length);
    }
}
