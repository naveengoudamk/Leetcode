class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {

        long ans = Long.MAX_VALUE;

        Helper waterHelper = new Helper(waterStartTime, waterDuration);
        for (int i = 0; i < landStartTime.length; i++) {
            long landFinish = (long) landStartTime[i] + landDuration[i];
            ans = Math.min(ans, waterHelper.query(landFinish));
        }

        Helper landHelper = new Helper(landStartTime, landDuration);
        for (int j = 0; j < waterStartTime.length; j++) {
            long waterFinish = (long) waterStartTime[j] + waterDuration[j];
            ans = Math.min(ans, landHelper.query(waterFinish));
        }

        return (int) ans;
    }

    static class Helper {
        int n;
        int[] start;
        long[] prefMinDur;
        long[] suffMinFinish;

        Helper(int[] startTime, int[] duration) {
            n = startTime.length;

            int[][] rides = new int[n][2];
            for (int i = 0; i < n; i++) {
                rides[i][0] = startTime[i];
                rides[i][1] = duration[i];
            }

            Arrays.sort(rides, Comparator.comparingInt(a -> a[0]));

            start = new int[n];
            prefMinDur = new long[n];
            suffMinFinish = new long[n];

            for (int i = 0; i < n; i++) {
                start[i] = rides[i][0];
            }

            prefMinDur[0] = rides[0][1];
            for (int i = 1; i < n; i++) {
                prefMinDur[i] = Math.min(prefMinDur[i - 1], rides[i][1]);
            }

            suffMinFinish[n - 1] = (long) rides[n - 1][0] + rides[n - 1][1];
            for (int i = n - 2; i >= 0; i--) {
                long val = (long) rides[i][0] + rides[i][1];
                suffMinFinish[i] = Math.min(suffMinFinish[i + 1], val);
            }
        }

        long query(long x) {
            int idx = upperBound(start, (int) x) - 1;

            long res = Long.MAX_VALUE;

            if (idx >= 0) {
                res = Math.min(res, x + prefMinDur[idx]);
            }

            if (idx + 1 < n) {
                res = Math.min(res, suffMinFinish[idx + 1]);
            }

            return res;
        }

        private int upperBound(int[] arr, int target) {
            int l = 0, r = arr.length;
            while (l < r) {
                int mid = (l + r) >>> 1;
                if (arr[mid] <= target) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return l;
        }
    }
}