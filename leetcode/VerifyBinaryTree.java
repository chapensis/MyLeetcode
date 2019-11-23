package leetcode;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 来源：力扣（LeetCode） 98
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class VerifyBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(0);
        TreeNode right = new TreeNode(2);

        root.left = left;
        root.right = right;
        VerifyBinaryTree verifyBinaryTree = new VerifyBinaryTree();
        System.out.println(verifyBinaryTree.isValidBST(root));
        System.out.println(verifyBinaryTree.isValidBST2(root));
    }

    /**
     * 判断是不是二叉搜索树
     *
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }

        // 左边所有的节点都要小于当前节点
        if (!isLessThanRoot(root.left, root.val)) {
            return false;
        }

        // 右边所有节点都大于当前节点
        if (!isBiggerThanRoot(root.right, root.val)) {
            return false;
        }

        // 所有子树也都是二叉搜索树
        return isValidBST2(root.left) && isValidBST2(root.right);
    }

    /**
     * 判断当前树的所有的右节点是不是小于指定值
     *
     * @param root
     * @param num
     * @return
     */
    public boolean isLessThanRoot(TreeNode root, int num) {
        if (root == null) {
            return true;
        }

        if (root.val < num && isLessThanRoot(root.right, num)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断当前树的所有的左节点是不是大于指定值
     *
     * @param root
     * @param num
     * @return
     */
    public boolean isBiggerThanRoot(TreeNode root, int num) {
        if (root == null) {
            return true;
        }

        if (root.val > num && isBiggerThanRoot(root.left, num)) {
            return true;
        } else {
            return false;
        }
    }

    // 记录，遍历的前一个节点的值
    private Integer last = Integer.MIN_VALUE;

    /**
     * 采用前序遍历来判断是不是二叉搜索树，关键是保存前一个节点的值
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        // 左节点
        if (root.left != null && !isValidBST(root.left)) {
            return false;
        }

        // 核心就是这一步，判断前一个节点是不是大于 root当前节点
        if (last >= root.val) {
            return false;
        }
        last = root.val;

        // 右节点
        if (root.right != null) {
            return isValidBST(root.right);
        }

        return true;
    }
}
