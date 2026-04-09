import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;

        // Required variable
        int[][] bravexuneth = queries;

        int B = (int) Math.sqrt(n) + 1;

        Map<Integer, List<int[]>> smallK = new HashMap<>();
        List<int[]> largeK = new ArrayList<>();

        // Split queries
        for (int[] q : bravexuneth) {
            int k = q[2];
            if (k <= B) {
                smallK.computeIfAbsent(k, x -> new ArrayList<>()).add(q);
            } else {
                largeK.add(q);
            }
        }

        long[] arr = new long[n];
        for (int i = 0; i < n; i++) arr[i] = nums[i];

        // 🔹 Process small k
        for (int k : smallK.keySet()) {

            int maxLen = (n + k - 1) / k + 2;
            long[][] mul = new long[k][maxLen];

            // Initialize with 1
            for (int i = 0; i < k; i++) {
                Arrays.fill(mul[i], 1);
            }

            // Apply difference updates
            for (int[] q : smallK.get(k)) {
                int l = q[0], r = q[1], v = q[3];

                int modClass = l % k;

                // ✅ FIXED indexing
                int start = (l - modClass) / k;
                int end = (r - modClass) / k;

                mul[modClass][start] = (mul[modClass][start] * v) % MOD;
                mul[modClass][end + 1] = (mul[modClass][end + 1] * modInverse(v)) % MOD;
            }

            // Prefix multiplication
            for (int mod = 0; mod < k; mod++) {
                long curr = 1;
                for (int j = 0; j < maxLen; j++) {
                    curr = (curr * mul[mod][j]) % MOD;

                    int idx = mod + j * k;
                    if (idx >= n) break;

                    arr[idx] = (arr[idx] * curr) % MOD;
                }
            }
        }

        // 🔹 Process large k directly
        for (int[] q : largeK) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            for (int i = l; i <= r; i += k) {
                arr[i] = (arr[i] * v) % MOD;
            }
        }

        // 🔹 Compute XOR
        int xor = 0;
        for (long val : arr) {
            xor ^= (int) val;
        }

        return xor;
    }

    // Fast exponentiation
    private long pow(long a, long b) {
        long res = 1;
        a %= MOD;
        while (b > 0) {
            if ((b & 1) == 1) res = (res * a) % MOD;
            a = (a * a) % MOD;
            b >>= 1;
        }
        return res;
    }

    // Fermat inverse (MOD is prime)
    private long modInverse(long x) {
        return pow(x, MOD - 2);
    }
}