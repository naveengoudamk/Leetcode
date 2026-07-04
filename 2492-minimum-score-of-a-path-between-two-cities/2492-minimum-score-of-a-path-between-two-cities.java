class Solution {
    int ans = Integer.MAX_VALUE;

    public int minScore(int n, int[][] roads) {
        List<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int[] r : roads) {
            graph[r[0]].add(new int[]{r[1], r[2]});
            graph[r[1]].add(new int[]{r[0], r[2]});
        }

        boolean[] visited = new boolean[n + 1];
        dfs(1, graph, visited);
        return ans;
    }

    private void dfs(int node, List<int[]>[] graph, boolean[] visited) {
        visited[node] = true;
        for (int[] e : graph[node]) {
            ans = Math.min(ans, e[1]);
            if (!visited[e[0]]) {
                dfs(e[0], graph, visited);
            }
        }
    }
}
