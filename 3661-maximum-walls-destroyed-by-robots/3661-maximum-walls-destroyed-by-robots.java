import java.util.*;

class Solution {

    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;

        // map robot -> distance
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(robots[i], distance[i]);
        }

        Arrays.sort(robots);
        Arrays.sort(walls);

        int[] left = new int[n];
        int[] right = new int[n];
        int[] num = new int[n];

        // compute left, right, num using binary search
        for (int i = 0; i < n; i++) {
            int pos = robots[i];
            int dist = map.get(pos);

            int lBound = pos - dist;
            if (i > 0) lBound = Math.max(lBound, robots[i - 1] + 1);

            int rBound = pos + dist;
            if (i < n - 1) rBound = Math.min(rBound, robots[i + 1] - 1);

            left[i] = count(walls, lBound, pos);
            right[i] = count(walls, pos, rBound);

            if (i > 0) {
                num[i] = count(walls, robots[i - 1], robots[i]);
            }
        }

        // DP
        int prevLeft = left[0];
        int prevRight = right[0];

        for (int i = 1; i < n; i++) {

            int curLeft = Math.max(
                prevLeft + left[i],
                prevRight - right[i - 1] + Math.min(right[i - 1] + left[i], num[i])
            );

            int curRight = Math.max(
                prevLeft + right[i],
                prevRight + right[i]
            );

            prevLeft = curLeft;
            prevRight = curRight;
        }

        return Math.max(prevLeft, prevRight);
    }

    private int count(int[] walls, int l, int r) {
        int left = lowerBound(walls, l);
        int right = upperBound(walls, r);
        return right - left;
    }

    private int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] < target) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] <= target) l = mid + 1;
            else r = mid;
        }
        return l;
    }
}