import java.util.*;

class Solution {

    static class Interval {
        int l, r, w, index;

        Interval(int l, int r, int w, int index) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.index = index;
        }
    }

    static class Result {
        long score;
        int[] indices;

        Result(long score, int[] indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    Interval[] arr;
    int n;
    Result[][] dp;

    public int[] maximumWeight(List<List<Integer>> intervals) {

        n = intervals.size();
        arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            List<Integer> x = intervals.get(i);

            arr[i] = new Interval(
                x.get(0),
                x.get(1),
                x.get(2),
                i
            );
        }

        // Sort by starting position
        Arrays.sort(arr, (a, b) -> {
            if (a.l != b.l) {
                return Integer.compare(a.l, b.l);
            }
            return Integer.compare(a.r, b.r);
        });

        dp = new Result[n + 1][5];

        // If no intervals are left
        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new Result(0, new int[0]);
        }

        // DP from right to left
        for (int i = n - 1; i >= 0; i--) {

            for (int k = 0; k <= 4; k++) {

                // Option 1: Don't take this interval
                Result best = dp[i + 1][k];

                // Option 2: Take this interval
                if (k > 0) {

                    int next = findNext(i);

                    Result nextResult = dp[next][k - 1];

                    int[] indices =
                        new int[nextResult.indices.length + 1];

                    indices[0] = arr[i].index;

                    System.arraycopy(
                        nextResult.indices,
                        0,
                        indices,
                        1,
                        nextResult.indices.length
                    );

                    // Original indices must be sorted
                    Arrays.sort(indices);

                    Result take = new Result(
                        arr[i].w + nextResult.score,
                        indices
                    );

                    if (better(take, best)) {
                        best = take;
                    }
                }

                dp[i][k] = best;
            }
        }

        return dp[0][4].indices;
    }

    // First interval whose left > current interval's right
    private int findNext(int i) {

        int target = arr[i].r;

        int left = i + 1;
        int right = n;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr[mid].l > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean better(Result a, Result b) {

        // Higher score is better
        if (a.score != b.score) {
            return a.score > b.score;
        }

        // Same score -> lexicographically smaller
        return lexicographicallySmaller(a.indices, b.indices);
    }

    private boolean lexicographicallySmaller(int[] a, int[] b) {

        int len = Math.min(a.length, b.length);

        for (int i = 0; i < len; i++) {

            if (a[i] != b[i]) {
                return a[i] < b[i];
            }
        }

        return a.length < b.length;
    }
}