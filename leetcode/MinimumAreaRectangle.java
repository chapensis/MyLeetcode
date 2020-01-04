package leetcode;

import java.util.*;

/**
 * 给定在 xy 平面上的一组点，确定由这些点组成的矩形的最小面积，
 * 其中矩形的边平行于 x 轴和 y 轴。
 * 如果没有任何矩形，就返回 0。
 *
 * @author yangchang
 * @since 2019-12-22
 */
public class MinimumAreaRectangle {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        MinimumAreaRectangle minimumAreaRectangle = new MinimumAreaRectangle();
        int[][] points = {{1, 1}, {1, 3}, {3, 1}, {3, 3}, {4, 1}, {4, 3}};
        System.out.println(minimumAreaRectangle.minAreaRect(points));
    }

    /**
     * 矩形的最小面积
     *
     * @param points 点的位置参数
     * @return 最下面积
     */
    public int minAreaRect(int[][] points) {
        if (points == null || points.length < 4) {
            return 0;
        }
        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];
            if (hashMap.containsKey(x)) {
                hashMap.get(x).add(y);
            } else {
                List<Integer> list = new ArrayList<>(Arrays.asList(y));
                hashMap.put(x, list);
            }
        }

        int minAreaRect = Integer.MAX_VALUE;
        Iterator<Map.Entry<Integer, List<Integer>>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<Integer>> entry = iterator.next();
            int x = entry.getKey();
            List<Integer> ylist = entry.getValue();
            for (int i = 0; i < ylist.size() - 1; i++) {
                for (int j = i + 1; j < ylist.size(); j++) {
                    int y1 = ylist.get(i);
                    int y2 = ylist.get(j);
                    int area = calAreaRect(x, y1, y2, hashMap);
                    if (area < minAreaRect) {
                        minAreaRect = area;
                    }
                }
            }
        }
        if (minAreaRect == Integer.MAX_VALUE) {
            return 0;
        }
        return minAreaRect;
    }

    /**
     * 根据给定的x和y1,y2，计算已有数组中的所有可能结果中面积最小的
     *
     * @param x1      x
     * @param y1      y1
     * @param y2      y2
     * @param hashMap hashMap
     * @return 最小面积
     */
    public int calAreaRect(int x1, int y1, int y2, HashMap<Integer, List<Integer>> hashMap) {
        int minAreaRect = Integer.MAX_VALUE;
        Iterator<Map.Entry<Integer, List<Integer>>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<Integer>> entry = iterator.next();
            int x2 = entry.getKey();
            if (x1 == x2) {
                continue;
            }
            List<Integer> ylist = entry.getValue();
            if (ylist.contains(y1) && ylist.contains(y2)) {
                int area = Math.abs((y2 - y1) * (x2 - x1));
                if (area < minAreaRect) {
                    minAreaRect = area;
                }
            }
        }
        return minAreaRect;
    }

    /**
     * 按列排序
     * 我们将这些点按照 x 轴（即列）排序，那么对于同一列的两个点 (x, y1) 和 (x, y2)，
     * 我们找出它作为右边界的最小的矩形。这可以通过记录下我们之前遇到的所有 (y1, y2) 点对来做到。
     *
     * @param points 点的位置参数
     * @return 最小面积
     */
    public int minAreaRectLk(int[][] points) {
        Map<Integer, List<Integer>> rows = new TreeMap();
        for (int[] point : points) {
            int x = point[0], y = point[1];
            rows.computeIfAbsent(x, z -> new ArrayList()).add(y);
        }

        int ans = Integer.MAX_VALUE;
        Map<String, Integer> lastx = new HashMap();
        for (int x : rows.keySet()) {
            List<Integer> row = rows.get(x);
            // 要保证y1 < y2
            Collections.sort(row);
            for (int i = 0; i < row.size(); ++i)
                for (int j = i + 1; j < row.size(); ++j) {
                    int y1 = row.get(i);
                    int y2 = row.get(j);
                    String key = y1 + "-" + y2;
                    if (lastx.containsKey(key)) {
                        ans = Math.min(ans, (x - lastx.get(key)) * (y2 - y1));
                    }
                    lastx.put(key, x);
                }
        }
        return ans < Integer.MAX_VALUE ? ans : 0;
    }

    /**
     * 枚举对角线
     * 我们将所有点放入集合中，并枚举矩形对角线上的两个点，
     * 并判断另外两个点是否出现在集合中。例如我们在枚举到 (1, 1) 和 (5, 5) 时，
     * 我们需要判断 (1, 5) 和 (5, 1) 是否也出现在集合中。
     *
     * @param points 点的位置参数
     * @return 最小面积
     */
    public int minAreaRectLk2(int[][] points) {
        Set<Integer> pointSet = new HashSet();
        for (int[] point : points)
            pointSet.add(40001 * point[0] + point[1]);

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; ++i)
            for (int j = i + 1; j < points.length; ++j) {
                if (points[i][0] != points[j][0] && points[i][1] != points[j][1]) {
                    if (pointSet.contains(40001 * points[i][0] + points[j][1]) &&
                            pointSet.contains(40001 * points[j][0] + points[i][1])) {
                        ans = Math.min(ans, Math.abs(points[j][0] - points[i][0]) *
                                Math.abs(points[j][1] - points[i][1]));
                    }
                }
            }

        return ans < Integer.MAX_VALUE ? ans : 0;
    }
}
