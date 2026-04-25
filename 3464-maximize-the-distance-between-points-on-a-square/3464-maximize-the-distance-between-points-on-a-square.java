import java.util.*;

class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long perimeter = 4L * side;

        // Step 1: map to 1D
        long[] pos = new long[n];
        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];

            if (y == 0) pos[i] = x;
            else if (x == side) pos[i] = side + y;
            else if (y == side) pos[i] = 3L * side - x;
            else pos[i] = 4L * side - y;
        }

        Arrays.sort(pos);

        // duplicate for circular handling
        long[] arr = new long[2 * n];
        for (int i = 0; i < n; i++) {
            arr[i] = pos[i];
            arr[i + n] = pos[i] + perimeter;
        }

        long left = 0, right = perimeter, ans = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            if (canPick(arr, n, k, mid, perimeter)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return (int) ans;
    }

    private boolean canPick(long[] arr, int n, int k, long d, long perimeter) {
        for (int start = 0; start < n; start++) {
            if (n - start < k) break; // small optimization

            int count = 1;
            long last = arr[start];
            int idx = start;

            while (count < k) {
                long target = last + d;
                int next = lowerBound(arr, idx + 1, start + n, target);

                if (next == start + n) break;

                last = arr[next];
                idx = next;
                count++;
            }

            if (count >= k && perimeter - (last - arr[start]) >= d) {
                return true;
            }
        }
        return false;
    }

    private int lowerBound(long[] arr, int left, int right, long target) {
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (arr[mid] >= target) right = mid;
            else left = mid + 1;
        }
        return left;
    }
}