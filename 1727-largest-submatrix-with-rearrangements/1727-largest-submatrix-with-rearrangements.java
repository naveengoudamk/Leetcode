import java.util.*;

class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] height = new int[n];
        int maxArea = 0;

        for (int i = 0; i < m; i++) {
            // Step 1: build heights
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    height[j] += 1;
                } else {
                    height[j] = 0;
                }
            }

            // Step 2: copy & sort descending
            int[] sorted = height.clone();
            Arrays.sort(sorted);

            // Step 3: calculate max area
            for (int k = 0; k < n; k++) {
                int h = sorted[n - 1 - k]; // descending
                int width = k + 1;
                maxArea = Math.max(maxArea, h * width);
            }
        }

        return maxArea;
    }
}