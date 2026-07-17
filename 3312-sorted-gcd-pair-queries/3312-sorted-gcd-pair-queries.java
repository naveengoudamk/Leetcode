import java.util.*;

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        int[] freq = new int[max + 1];
        for (int x : nums) freq[x]++;

        long[] exact = new long[max + 1];
        int[] cnt = new int[max + 1];

        for (int g = 1; g <= max; g++) {
            int c = 0;
            for (int j = g; j <= max; j += g)
                c += freq[j];
            cnt[g] = c;
            exact[g] = (long) c * (c - 1) / 2;
        }

        for (int g = max; g >= 1; g--) {
            for (int m = g * 2; m <= max; m += g)
                exact[g] -= exact[m];
        }

        long[] prefix = new long[max + 1];
        for (int g = 1; g <= max; g++)
            prefix[g] = prefix[g - 1] + exact[g];

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long k = queries[i] + 1;

            int lo = 1;
            int hi = max;

            while (lo < hi) {
                int mid = (lo + hi) >>> 1;
                if (prefix[mid] >= k)
                    hi = mid;
                else
                    lo = mid + 1;
            }

            ans[i] = lo;
        }

        return ans;
    }
}