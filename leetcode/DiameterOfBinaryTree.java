package leetcode;

/**
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * leetcode 543
 * https://leetcode-cn.com/problems/diameter-of-binary-tree/
 *
 * @author yangchang
 * @since 2019-12-22
 */
public class DiameterOfBinaryTree {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        DiameterOfBinaryTree diameterOfBinaryTree = new DiameterOfBinaryTree();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        System.out.println(diameterOfBinaryTree.diameterOfBinaryTree(node1));
    }

    int max = 0;

    /**
     * 计算二叉树的直径长度
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        countDiameterOfBinaryTree(root);
        return max;
    }

    /**
     * 计算二叉树的直径长度
     * @param root
     * @return
     */
    public int countDiameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftCount = 0;
        int rightCount = 0;
        if (root.left != null) {
            leftCount = countDiameterOfBinaryTree(root.left) + 1;
        }
        if (root.right != null) {
            rightCount = countDiameterOfBinaryTree(root.right) + 1;
        }
        int count = leftCount + rightCount;
        max = Math.max(count, max);
        return Math.max(leftCount, rightCount);
    }

}
