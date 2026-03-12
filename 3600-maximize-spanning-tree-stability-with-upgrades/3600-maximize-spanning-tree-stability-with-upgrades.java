import java.util.*;

class Solution {

    class DSU {
        int[] parent, rank;
        int components;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            components = n;
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int a, int b) {
            int pa = find(a), pb = find(b);
            if (pa == pb) return false;

            if (rank[pa] < rank[pb]) parent[pa] = pb;
            else if (rank[pb] < rank[pa]) parent[pb] = pa;
            else {
                parent[pb] = pa;
                rank[pa]++;
            }
            components--;
            return true;
        }
    }

    public int maxStability(int n, int[][] edges, int k) {
        int left = 0, right = 200000, ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (can(n, edges, k, mid)) {
                ans = mid;
                left = mid + 1;
            } else right = mid - 1;
        }

        return ans;
    }

    private boolean can(int n, int[][] edges, int k, int target) {
        DSU dsu = new DSU(n);
        int upgrades = 0;

        List<int[]> normal = new ArrayList<>();
        List<int[]> upgrade = new ArrayList<>();

        // Process mandatory edges
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];

            if (must == 1) {
                if (s < target) return false;
                if (!dsu.union(u, v)) return false; // cycle
            } else {
                if (s >= target) normal.add(e);
                else if (2 * s >= target) upgrade.add(e);
            }
        }

        // Use normal edges first
        for (int[] e : normal) {
            if (dsu.union(e[0], e[1])) {
                if (dsu.components == 1) return true;
            }
        }

        // Use upgrade edges
        for (int[] e : upgrade) {
            if (upgrades == k) break;
            if (dsu.union(e[0], e[1])) {
                upgrades++;
                if (dsu.components == 1) return true;
            }
        }

        return dsu.components == 1;
    }
}