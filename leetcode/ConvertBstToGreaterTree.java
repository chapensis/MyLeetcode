package leetcode;

/**
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，
 * 使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 * 来源：力扣（LeetCode） 538
 * 链接：https://leetcode-cn.com/problems/convert-bst-to-greater-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 * @since 2019-12-22
 */
public class ConvertBstToGreaterTree {
    /**
     * @param args
     */
    public static void main(String[] args) {

    }

    int total = 0;

    /**
     * 转换成为累加树
     * 反中序遍历累加和
     *
     * @param root 根节点
     * @return 累加树根节点
     */
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        convertBST(root.right);
        total = total + root.val;
        root.val = total;
        convertBST(root.left);
        return root;
    }
}
