import java.util.*;

class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> freq = new HashMap<>();

        for (int num : nums) {
            freq.put((long) num, freq.getOrDefault((long) num, 0) + 1);
        }

        int ans = 1;

        // Handle 1 separately
        if (freq.containsKey(1L)) {
            int cnt = freq.get(1L);
            ans = Math.max(ans, (cnt % 2 == 0) ? cnt - 1 : cnt);
        }

        for (long x : freq.keySet()) {
            if (x == 1) continue;

            long cur = x;
            int len = 0;

            while (true) {
                Integer c = freq.get(cur);
                if (c == null) break;

                if (c >= 2) {
                    len += 2;
                } else {
                    len += 1;
                    break;
                }

                // Prevent overflow
                if (cur > 1_000_000_000L / cur) {
                    break;
                }

                cur *= cur;
            }

            if (len % 2 == 0) len--;

            ans = Math.max(ans, len);
        }

        return ans;
    }
}