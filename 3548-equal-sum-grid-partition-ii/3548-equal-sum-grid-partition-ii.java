import java.util.*;

class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        long total = 0;
        for (int[] row : grid)
            for (int v : row)
                total += v;

        // ---------- HORIZONTAL ----------
        Map<Integer, Integer> bottom = new HashMap<>();
        for (int[] row : grid)
            for (int v : row)
                bottom.put(v, bottom.getOrDefault(v, 0) + 1);

        Map<Integer, Integer> top = new HashMap<>();
        long topSum = 0;

        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++) {
                int v = grid[i][j];
                topSum += v;

                top.put(v, top.getOrDefault(v, 0) + 1);
                bottom.put(v, bottom.get(v) - 1);
                if (bottom.get(v) == 0) bottom.remove(v);
            }

            long bottomSum = total - topSum;
            if (topSum == bottomSum) return true;

            long diff = Math.abs(topSum - bottomSum);

            if (diff > 100000) continue; // 🔥 FIX

            if (topSum > bottomSum) {
                if (check(grid, 0, i, 0, n - 1, diff, top)) return true;
            } else {
                if (check(grid, i + 1, m - 1, 0, n - 1, diff, bottom)) return true;
            }
        }

        // ---------- VERTICAL ----------
        Map<Integer, Integer> right = new HashMap<>();
        for (int[] row : grid)
            for (int v : row)
                right.put(v, right.getOrDefault(v, 0) + 1);

        Map<Integer, Integer> left = new HashMap<>();
        long leftSum = 0;

        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < m; i++) {
                int v = grid[i][j];
                leftSum += v;

                left.put(v, left.getOrDefault(v, 0) + 1);
                right.put(v, right.get(v) - 1);
                if (right.get(v) == 0) right.remove(v);
            }

            long rightSum = total - leftSum;
            if (leftSum == rightSum) return true;

            long diff = Math.abs(leftSum - rightSum);

            if (diff > 100000) continue; // 🔥 FIX

            if (leftSum > rightSum) {
                if (check(grid, 0, m - 1, 0, j, diff, left)) return true;
            } else {
                if (check(grid, 0, m - 1, j + 1, n - 1, diff, right)) return true;
            }
        }

        return false;
    }

    private boolean check(int[][] grid, int r1, int r2, int c1, int c2,
                          long diff, Map<Integer, Integer> map) {

        if (!map.containsKey((int) diff)) return false;

        int rows = r2 - r1 + 1;
        int cols = c2 - c1 + 1;

        if (rows == 1) {
            return grid[r1][c1] == diff || grid[r1][c2] == diff;
        }

        if (cols == 1) {
            return grid[r1][c1] == diff || grid[r2][c1] == diff;
        }

        return true;
    }
}