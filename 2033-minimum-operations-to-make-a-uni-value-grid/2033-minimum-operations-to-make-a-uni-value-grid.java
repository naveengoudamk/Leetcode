import java.util.*;

class Solution {
    public int minOperations(int[][] grid, int x) {
        List<Integer> list = new ArrayList<>();
        
        // Flatten grid
        for (int[] row : grid) {
            for (int num : row) {
                list.add(num);
            }
        }
        
        // Check modulo condition
        int remainder = list.get(0) % x;
        for (int num : list) {
            if (num % x != remainder) {
                return -1;
            }
        }
        
        // Sort and find median
        Collections.sort(list);
        int median = list.get(list.size() / 2);
        
        // Count operations
        int operations = 0;
        for (int num : list) {
            operations += Math.abs(num - median) / x;
        }
        
        return operations;
    }
}