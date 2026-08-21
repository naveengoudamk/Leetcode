class Solution {

    public long findKthSmallest(int[] coins, int k) {

        long left = 1;
        long right = (long) coins[0] * k;

        // Binary search for the answer
        while (left < right) {

            long mid = left + (right - left) / 2;

            long count = countAmounts(mid, coins);

            if (count >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private long countAmounts(long x, int[] coins) {

        int n = coins.length;
        long count = 0;

        // Try every subset of coins
        for (int mask = 1; mask < (1 << n); mask++) {

            long lcm = 1;
            int bits = 0;
            boolean valid = true;

            for (int i = 0; i < n; i++) {

                if ((mask & (1 << i)) != 0) {

                    bits++;

                    lcm = lcm(lcm, coins[i]);

                    // Prevent overflow / unnecessary calculation
                    if (lcm > x) {
                        valid = false;
                        break;
                    }
                }
            }

            if (!valid) {
                continue;
            }

            long multiples = x / lcm;

            // Odd number of elements -> add
            // Even number -> subtract
            if (bits % 2 == 1) {
                count += multiples;
            } else {
                count -= multiples;
            }
        }

        return count;
    }

    private long gcd(long a, long b) {

        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }

    private long lcm(long a, long b) {

        return a / gcd(a, b) * b;
    }
}
