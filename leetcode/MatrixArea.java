package leetcode;

import java.util.ArrayList;
import java.util.List;

public class MatrixArea {
    static int M = (int) Math.pow(10, 9) + 7;

    public static void main(String[] args) {

    }

    public int rectangleArea(int[][] rectangles) {
        List<Node> nodeList = new ArrayList<>();
        for (int[] r : rectangles) {
            Point p = new Point(r[1], r[3]);
            nodeList.add(new Node(p, r[0], false));
            nodeList.add(new Node(p, r[2], true));
        }
        // 根据矩形的X轴升序排序
        nodeList.sort((o1, o2) -> o1.index - o2.index);

        List<Point> pointList = new ArrayList<>();

        int res = 0;
        int preIndex = 0;
        for (Node node : nodeList) {
            if (node.index != preIndex) {
                res = (res + help(pointList, node.index - preIndex)) % M;
                preIndex = node.index;
            }

            if (node.isEnd) {
                pointList.remove(node.point);
            } else {
                pointList.add(node.point);
            }
        }
        return res;
    }

    /**
     *
     * @param pointList
     * @param cnt 宽度
     * @return
     */
    private int help(List<Point> pointList, int cnt) {
        pointList.sort((o1, o2) -> o1.y1 - o2.y1);
        int sum = 0;
        int y1 = 0;
        int y2 = 0;
        for (Point p : pointList) {
            if (p.y1 > y2) {
                sum = (sum + y2 - y1) % M;
                y1 = p.y1;
                y2 = p.y2;
            } else {
                y2 = Math.max(y2, p.y2);
            }
        }
        sum = (sum + y2 - y1) % M;
        // 面积等于 总高度* 宽度
        sum = (int) ((long) sum * cnt % M);
        return sum;
    }

    // y轴方向边界
    class Point {
        int y1; // 下边界
        int y2; // 上边界

        public Point(int y1, int y2) {
            this.y1 = y1;
            this.y2 = y2;
        }
    }

    // x轴边界
    class Node {
        Point point;
        boolean isEnd;
        int index;

        public Node(Point point, int index, boolean isEnd) {
            this.point = point;
            this.index = index;
            this.isEnd = isEnd;
        }
    }
}
