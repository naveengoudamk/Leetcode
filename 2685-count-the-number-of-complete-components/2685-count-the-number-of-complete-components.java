class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        boolean[] visited = new boolean[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int[] comp = new int[2]; // [nodes, edges]
                dfs(i, graph, visited, comp);
                int nodes = comp[0];
                int edgeCount = comp[1] / 2; // undirected
                if (edgeCount == nodes * (nodes - 1) / 2) count++;
            }
        }
        return count;
    }

    private void dfs(int node, List<List<Integer>> graph, boolean[] visited, int[] comp) {
        visited[node] = true;
        comp[0]++;
        comp[1] += graph.get(node).size();

        for (int nei : graph.get(node)) {
            if (!visited[nei]) {
                dfs(nei, graph, visited, comp);
            }
        }
    }
}
