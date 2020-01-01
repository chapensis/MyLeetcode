package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。
 * 只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。
 * 在完成所有删除操作后，请你返回列表中剩余区间的数目。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-covered-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 * @since 2019-12-22
 */
public class DeleteCoveredRange {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        DeleteCoveredRange deleteCoveredRange = new DeleteCoveredRange();
        int[][] intervals = {{1, 4}, {3, 6}, {2, 8}};
        System.out.println(deleteCoveredRange.removeCoveredIntervals(intervals));
    }

    /**
     * 获得列表中剩余区间的数目。
     * 核心思想： 先按照区间左边界排序，再从0开始扫描，持续更新最大值，如果最大值有变化，则count++
     *
     * @param intervals 列表
     * @return 剩余区间的数目
     */
    public int removeCoveredIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        // 根据左边界排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int max = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < intervals.length; i++) {
            // 如果大于最大值，说明这个值不能被其他区间吞并，小于或者等于则一定被吞并，因为已经排过序了
            if (intervals[i][1] > max) {
                max = intervals[i][1];
                count++;
            }
        }
        return count;
    }
}
