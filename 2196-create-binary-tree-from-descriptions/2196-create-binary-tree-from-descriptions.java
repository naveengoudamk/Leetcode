class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> map = new HashMap<>();
        Set<Integer> children = new HashSet<>();

        for (int[] d : descriptions) {
            int parent = d[0], child = d[1], isLeft = d[2];

            map.putIfAbsent(parent, new TreeNode(parent));
            map.putIfAbsent(child, new TreeNode(child));

            if (isLeft == 1) {
                map.get(parent).left = map.get(child);
            } else {
                map.get(parent).right = map.get(child);
            }
            children.add(child);
        }

        for (int key : map.keySet()) {
            if (!children.contains(key)) {
                return map.get(key);
            }
        }
        return null;
    }
}
