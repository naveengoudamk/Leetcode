class Solution {

    int answer = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return answer;
    }

    // Returns {sum, count} for the subtree
    private int[] dfs(TreeNode node) {

        if (node == null) {
            return new int[]{0, 0};
        }

        // Get sum and count from left subtree
        int[] left = dfs(node.left);

        // Get sum and count from right subtree
        int[] right = dfs(node.right);

        // Calculate sum of current subtree
        int sum = node.val + left[0] + right[0];

        // Calculate number of nodes in current subtree
        int count = 1 + left[1] + right[1];

        // Floor average using integer division
        int average = sum / count;

        // Check current node
        if (node.val == average) {
            answer++;
        }

        return new int[]{sum, count};
    }
}