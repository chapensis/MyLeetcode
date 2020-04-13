package leetcode;

/**
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，
 * 那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
 * 来源：力扣（LeetCode） 617
 * 链接：https://leetcode-cn.com/problems/merge-two-binary-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangchang
 * @since 2019-12-22
 */
public class MergeTwoBinaryTrees {
    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        MergeTwoBinaryTrees mergeTwoBinaryTrees = new MergeTwoBinaryTrees();
        mergeTwoBinaryTrees.mergeTrees(null, null);
    }

    /**
     * 合并两颗二叉树
     *
     * @param t1 t1
     * @param t2 t2
     * @return 返回合并后的树
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        int val = 0;
        if (t1 != null) {
            val += t1.val;
        }
        if (t2 != null) {
            val += t2.val;
        }
        TreeNode treeNode = new TreeNode(val);
        treeNode.left = mergeTrees(t1 != null ? t1.left : null, t2 != null ? t2.left : null);
        treeNode.right = mergeTrees(t1 != null ? t1.right : null, t2 != null ? t2.right : null);
        return treeNode;
    }
}

