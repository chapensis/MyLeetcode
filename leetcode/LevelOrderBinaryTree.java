package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * leetcode 102
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 *
 * @author yangchang
 * @since 2019-12-22
 */
public class LevelOrderBinaryTree {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        LevelOrderBinaryTree levelOrderBinaryTree = new LevelOrderBinaryTree();
        TreeNode root1 = new TreeNode(3);
        TreeNode root2 = new TreeNode(9);
        TreeNode root3 = new TreeNode(20);
        TreeNode root4 = new TreeNode(15);
        TreeNode root5 = new TreeNode(7);
        root1.left = root2;
        root1.right = root3;

        root3.left = root4;
        root3.right = root5;
        List<List<Integer>> result = levelOrderBinaryTree.levelOrder(root1);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }

    /**
     * 层次遍历，核心就在于利用队列实现入队，出队
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 1;
        while (!queue.isEmpty()) {
            List<Integer> subList = new ArrayList<>();
            while (subList.size() < count) {
                TreeNode node = queue.poll();
                subList.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            count = queue.size();
            result.add(subList);
        }
        return result;
    }
}
