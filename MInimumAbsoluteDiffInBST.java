import javax.swing.tree.TreeNode;

class MInimumAbsoluteDiffInBST {
    private int minDiff = Integer.MAX_VALUE;
    private TreeNode prev = null;

    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return minDiff;
    }

    private void dfs(TreeNode currNode) {
        if (currNode == null)
            return;

        dfs(currNode.left);
        if (prev != null) {
            minDiff = Integer.min(minDiff, Math.abs(prev.val - currNode.val));
        }
        prev = currNode;
        dfs(currNode.right);
    }
}
