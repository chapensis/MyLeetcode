package leetcode;

import java.util.Arrays;

/**
 * 给定在 xy 平面上的一组点，确定由这些点组成的任何矩形的最小面积，
 * 其中矩形的边不一定平行于 x 轴和 y 轴。
 * 如果没有任何矩形，就返回 0。
 *
 * @author yangchang
 * @since 2019-12-22
 */
public class MinimumAreaRectangle2 {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        MinimumAreaRectangle2 minimumAreaRectangle2 = new MinimumAreaRectangle2();
        int[][] points = {{3, 1}, {1, 1}, {0, 1}, {2, 1}, {3, 3}, {3, 2}, {0, 2}, {2, 3}};
        System.out.println(minimumAreaRectangle2.minAreaFreeRect(points));
    }

    /**
     * 暴力计算矩形面积
     * @param points 所有点的位置
     * @return 最小矩形面积
     */
    public double minAreaFreeRect(int[][] points) {
        if (points == null || points.length < 4) {
            return 0;
        }

        double minArea = Double.MAX_VALUE;
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int k = j + 1; k < points.length - 1; k++) {
                    for (int m = k + 1; m < points.length; m++) {
                        double area = getMinAreaRect(points[i], points[j], points[k], points[m]);
                        if (area < minArea) {
                            minArea = area;
                        }
                    }
                }
            }
        }
        if (minArea == Double.MAX_VALUE) {
            return 0;
        }
        return minArea;
    }

    /**
     * 任意给定四个点，先判断是否构成矩形，再计算矩形面积
     * @param point1 point1
     * @param point2 point2
     * @param point3 point3
     * @param point4 point4
     * @return 矩形面积
     */
    public double getMinAreaRect(int[] point1, int[] point2, int[] point3, int[] point4) {
        double length1_2 = Math.pow((point1[0] - point2[0]), 2) + Math.pow((point1[1] - point2[1]), 2);
        double length3_4 = Math.pow((point3[0] - point4[0]), 2) + Math.pow((point3[1] - point4[1]), 2);
        if (Math.abs(length1_2 - length3_4) > 0.0000001) {
            return Double.MAX_VALUE;
        }

        double length1_4 = Math.pow((point1[0] - point4[0]), 2) + Math.pow((point1[1] - point4[1]), 2);
        double length2_3 = Math.pow((point2[0] - point3[0]), 2) + Math.pow((point2[1] - point3[1]), 2);
        if (Math.abs(length1_4 - length2_3) > 0.0000001) {
            return Double.MAX_VALUE;
        }

        double length1_3 = Math.pow((point1[0] - point3[0]), 2) + Math.pow((point1[1] - point3[1]), 2);
        double length2_4 = Math.pow((point2[0] - point4[0]), 2) + Math.pow((point2[1] - point4[1]), 2);
        if (Math.abs(length1_3 - length2_4) > 0.0000001) {
            return Double.MAX_VALUE;
        }

        double[] lengths = {length1_2, length1_3, length1_4};
        Arrays.sort(lengths);
        double area = Math.pow(lengths[0], 0.5) * Math.pow(lengths[1], 0.5);
        return area;
    }
}
