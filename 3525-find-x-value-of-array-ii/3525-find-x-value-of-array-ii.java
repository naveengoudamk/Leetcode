class Solution {
    // Segment Tree Node to maintain product and remainders tracking
    static class Node {
        int[] remain;
        int prod = 1;
        
        Node(int k) {
            this.remain = new int[k];
        }
    }

    static class SegmentTree {
        int n;
        int k;
        Node[] tree;

        SegmentTree(int[] nums, int k) {
            this.n = nums.length;
            this.k = k;
            this.tree = new Node[4 * n];
            build(nums, 0, 0, n - 1);
        }

        // Combines data from left and right children
        Node merge(Node left, Node right) {
            Node node = new Node(k);
            node.prod = (left.prod * right.prod) % k;
            
            // Prefixes completely within the left subtree
            for (int i = 0; i < k; ++i) {
                node.remain[i] = left.remain[i];
            }
            // Prefixes crossing into the right subtree
            for (int i = 0; i < k; ++i) {
                node.remain[(i * left.prod) % k] += right.remain[i];
            }
            return node;
        }

        void build(int[] nums, int cur, int left, int right) {
            if (left == right) {
                tree[cur] = new Node(k);
                tree[cur].remain[nums[left]] = 1;
                tree[cur].prod = nums[left];
                return;
            }
            int mid = (left + right) / 2;
            build(nums, 2 * cur + 1, left, mid);
            build(nums, 2 * cur + 2, mid + 1, right);
            tree[cur] = merge(tree[2 * cur + 1], tree[2 * cur + 2]);
        }

        void update(int i, int val) {
            update(0, 0, n - 1, i, val);
        }

        void update(int treeIndex, int lo, int hi, int i, int val) {
            if (lo == hi) {
                for (int j = 0; j < k; ++j) {
                    tree[treeIndex].remain[j] = 0;
                }
                tree[treeIndex].remain[val] = 1;
                tree[treeIndex].prod = val;
                return;
            }
            int mid = (lo + hi) / 2;
            if (i <= mid) {
                update(2 * treeIndex + 1, lo, mid, i, val);
            } else {
                update(2 * treeIndex + 2, mid + 1, hi, i, val);
            }
            tree[treeIndex] = merge(tree[2 * treeIndex + 1], tree[2 * treeIndex + 2]);
        }

        Node query(int i, int j) {
            return query(0, 0, n - 1, i, j);
        }

        Node query(int treeIndex, int lo, int hi, int i, int j) {
            if (i <= lo && hi <= j) {
                return tree[treeIndex];
            }
            if (j < lo || hi < i) {
                return new Node(k); // Identity element: all counts 0, prod is 1
            }
            int mid = (lo + hi) / 2;
            return merge(query(2 * treeIndex + 1, lo, mid, i, j), 
                         query(2 * treeIndex + 2, mid + 1, hi, i, j));
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        // Reduce initial constraints modulo k
        for (int i = 0; i < n; i++) {
            nums[i] %= k;
        }
        for (int[] query : queries) {
            query[1] %= k;
        }

        SegmentTree tree = new SegmentTree(nums, k);
        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // 1. Point update persists
            tree.update(index, value);
            
            // 2. Query range from [start_i, n - 1] to calculate x-value counts
            ans[i] = tree.query(start, n - 1).remain[x];
        }

        return ans;
    }
}
