class Solution {
    static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        // Base case for length = 2
        long[] base = new long[2 * m];

        // up[v] at index v-1
        // down[v] at index m + (v-1)
        for (int v = 1; v <= m; v++) {
            base[v - 1] = v - 1;       // up[v]
            base[m + v - 1] = m - v;   // down[v]
        }

        // If n == 2 (not needed for this problem since n >= 3, but safe)
        if (n == 2) {
            long ans = 0;
            for (long x : base) ans = (ans + x) % MOD;
            return (int) ans;
        }

        // Build transition matrix of size 2m x 2m
        long[][] T = new long[2 * m][2 * m];

        // up'[v] = sum of down[x] for x < v
        for (int v = 1; v <= m; v++) {
            int row = v - 1; // row for up'[v]
            for (int x = 1; x < v; x++) {
                int col = m + (x - 1); // down[x]
                T[row][col] = 1;
            }
        }

        // down'[v] = sum of up[x] for x > v
        for (int v = 1; v <= m; v++) {
            int row = m + (v - 1); // row for down'[v]
            for (int x = v + 1; x <= m; x++) {
                int col = x - 1; // up[x]
                T[row][col] = 1;
            }
        }

        // Need transitions from length 2 -> length n
        long[][] Tpow = matPow(T, n - 2);

        long[] res = multiply(Tpow, base);

        long ans = 0;
        for (long val : res) {
            ans = (ans + val) % MOD;
        }

        return (int) ans;
    }

    private long[][] matPow(long[][] mat, int exp) {
        int size = mat.length;
        long[][] result = new long[size][size];

        // Identity matrix
        for (int i = 0; i < size; i++) {
            result[i][i] = 1;
        }

        long[][] base = mat;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = multiply(result, base);
            }
            base = multiply(base, base);
            exp >>= 1;
        }

        return result;
    }

    private long[][] multiply(long[][] A, long[][] B) {
        int n = A.length;
        long[][] C = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] == 0) continue;
                long a = A[i][k];
                for (int j = 0; j < n; j++) {
                    if (B[k][j] == 0) continue;
                    C[i][j] = (C[i][j] + a * B[k][j]) % MOD;
                }
            }
        }

        return C;
    }

    private long[] multiply(long[][] A, long[] v) {
        int n = A.length;
        long[] res = new long[n];

        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 0 || v[j] == 0) continue;
                sum = (sum + A[i][j] * v[j]) % MOD;
            }
            res[i] = sum;
        }

        return res;
    }
}