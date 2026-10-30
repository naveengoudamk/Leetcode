import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build adjacency list for the graph
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            adj.get(u).add(new int[]{v, w});
        }

        // dist[node][consecutive_count] stores the minimum cost to reach 'node'
        // with a specific number of consecutive identical characters ending there.
        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // Priority Queue stores arrays of format: {weight, node, consecutive_count}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // Base case: starting at node 0 with 1 consecutive character of its own type
        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currWeight = curr[0];
            int u = curr[1];
            int consecutive = curr[2];

            // If we found a shorter path to this state already, skip it
            if (currWeight > dist[u][consecutive]) {
                continue;
            }

            // If we reached the target node, since it's Dijkstra, it's the shortest valid path
            if (u == n - 1) {
                return currWeight;
            }

            // Explore neighbors
            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int w = neighbor[1];

                // Determine the consecutive count for the next state
                int nextConsecutive = (labels.charAt(u) == labels.charAt(v)) ? consecutive + 1 : 1;

                // Only proceed if the state satisfies the at-most-K condition
                if (nextConsecutive <= k) {
                    int nextWeight = currWeight + w;
                    if (nextWeight < dist[v][nextConsecutive]) {
                        dist[v][nextConsecutive] = nextWeight;
                        pq.offer(new int[]{nextWeight, v, nextConsecutive});
                    }
                }
            }
        }

        // If node n - 1 is unreachable under the given constraints
        return -1;
    }
}
