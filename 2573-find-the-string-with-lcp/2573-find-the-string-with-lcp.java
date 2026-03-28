class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;

        // Step 1: Validate diagonal
        for (int i = 0; i < n; i++) {
            if (lcp[i][i] != n - i) return "";
        }

        // DSU setup
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        // Find
        java.util.function.IntUnaryOperator find = new java.util.function.IntUnaryOperator() {
            public int applyAsInt(int x) {
                if (parent[x] != x) parent[x] = applyAsInt(parent[x]);
                return parent[x];
            }
        };

        // Union
        java.util.function.BiConsumer<Integer, Integer> union = (a, b) -> {
            int pa = find.applyAsInt(a);
            int pb = find.applyAsInt(b);
            if (pa != pb) parent[pb] = pa;
        };

        // Step 2: Group indices
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (lcp[i][j] > 0) {
                    union.accept(i, j);
                }
            }
        }

        // Step 3: Assign characters
        char[] res = new char[n];
        java.util.Map<Integer, Character> map = new java.util.HashMap<>();
        char ch = 'a';

        for (int i = 0; i < n; i++) {
            int root = find.applyAsInt(i);
            if (!map.containsKey(root)) {
                if (ch > 'z') return ""; // too many groups
                map.put(root, ch++);
            }
            res[i] = map.get(root);
        }

        String word = new String(res);

        // Step 4: Validate LCP
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (word.charAt(i) == word.charAt(j)) {
                    if (i + 1 < n && j + 1 < n)
                        dp[i][j] = 1 + dp[i + 1][j + 1];
                    else
                        dp[i][j] = 1;
                }
            }
        }

        // Compare matrices
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] != lcp[i][j]) return "";
            }
        }

        return word;
    }
}