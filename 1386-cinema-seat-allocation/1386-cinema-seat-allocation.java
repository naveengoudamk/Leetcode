import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        // Store reserved seats for each row.
        Map<Integer, Integer> map = new HashMap<>();

        for (int[] seat : reservedSeats) {

            int row = seat[0];
            int col = seat[1];

            // Bitmask:
            // bit 0 -> seat 1
            // bit 1 -> seat 2
            // ...
            // bit 9 -> seat 10
            map.put(row, map.getOrDefault(row, 0) | (1 << (col - 1)));
        }

        // Every completely empty row can fit 2 groups.
        long answer = (long) (n - map.size()) * 2;

        for (int mask : map.values()) {

            boolean left = (mask & ((1 << 1) | (1 << 2) |
                                   (1 << 3) | (1 << 4))) == 0;

            boolean middle = (mask & ((1 << 3) | (1 << 4) |
                                      (1 << 5) | (1 << 6))) == 0;

            boolean right = (mask & ((1 << 5) | (1 << 6) |
                                     (1 << 7) | (1 << 8))) == 0;

            if (left && right) {
                // Two groups can sit on both sides.
                answer += 2;
            } else if (left || middle || right) {
                // At least one valid block exists.
                answer += 1;
            }
        }

        return (int) answer;
    }
}