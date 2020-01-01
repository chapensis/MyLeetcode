package leetcode;

import java.util.*;

/**
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 * 给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 * @since 2019-12-22
 */
public class CourseSchedule {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        CourseSchedule courseSchedule = new CourseSchedule();
        int[][] prerequisites = {{0, 1}, {1, 0}};
        System.out.println(courseSchedule.canFinish(2, prerequisites));
    }

    /**
     * 判断是否能够完成课程
     *
     * @param numCourses    课程总数量
     * @param prerequisites 课程依赖关系数组
     * @return 是否能够完成课程
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        Course[] courses = new Course[numCourses];
        for (int i = 0; i < numCourses; i++) {
            Course course = new Course(i);
            courses[i] = course;
        }

        // 根据课程依赖数组，添加前置和后置课程
        for (int i = 0; i < prerequisites.length; i++) {
            int afterCourseId = prerequisites[i][0];
            int beforeCourseId = prerequisites[i][1];
            Course afterCourse = courses[afterCourseId];
            Course beforeCourse = courses[beforeCourseId];

            // 前置后置课程依次添加相关信息
            afterCourse.beforeCourses.add(beforeCourse);
            beforeCourse.afterCourses.add(afterCourse);
        }

        Set<Integer> learnedCourses = new HashSet<>();
        Queue<Course> toLearnedCoursed = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            Course course = courses[i];
            // 将原始课程中，“入度”为0的课程添加到即将要学习的课程中
            if (course.beforeCourses.size() == 0) {
                toLearnedCoursed.add(course);
            }
        }

        // 没有前置课程的课或者前置课程已经学习完的课，就是接下来要学习的课程
        while (toLearnedCoursed.size() > 0) {
            Course course = toLearnedCoursed.poll();
            learnedCourses.add(course.courseId);
            List<Course> afterCourses = course.afterCourses;
            // 针对当前课程的后置课程，如果所有后置课程都已经学习
            for (Course cs : afterCourses) {
                if (hasLearnedAllCourses(cs, learnedCourses)) {
                    toLearnedCoursed.add(cs);
                }
            }
        }

        // 显然，已经学习完的课程数量等于所有课程的数量，则能够完成所有课程
        if (learnedCourses.size() == numCourses) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断某门课程的前置课程是否都已经学习完
     *
     * @param course
     * @param learnedCourses
     * @return
     */
    public boolean hasLearnedAllCourses(Course course, Set<Integer> learnedCourses) {
        List<Course> beforeCourses = course.beforeCourses;
        for (Course cs : beforeCourses) {
            if (!learnedCourses.contains(cs.courseId)) {
                return false;
            }
        }
        return true;
    }

    class Course {
        int courseId;
        List<Course> beforeCourses;
        List<Course> afterCourses;

        public Course(int courseId) {
            this.courseId = courseId;
            this.beforeCourses = new ArrayList<>();
            this.afterCourses = new ArrayList<>();
        }
    }
}
