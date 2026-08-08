class Solution {
    public int[] validSequence(String word1, String word2) {

        char[] a = word1.toCharArray();
        char[] b = word2.toCharArray();

        int n = a.length;
        int m = b.length;

        /*
         * dp[i] = maximum number of characters of the remaining
         * suffix of word2 that can be matched from word1[i...]
         *
         * We calculate this from right to left.
         */
        int[] dp = new int[n + 1];

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {

            if (j >= 0 && a[i] == b[j]) {
                dp[i] = dp[i + 1] + 1;
                j--;
            } else {
                dp[i] = dp[i + 1];
            }
        }

        int[] ans = new int[m];

        int i = 0;
        j = 0;

        /*
         * First phase:
         * Greedily choose the smallest possible indices.
         *
         * If characters are equal, use the index directly.
         *
         * If they are different, we can use our one allowed
         * modification, but only if the remaining characters
         * can still be matched.
         */
        while (i < n && j < m) {

            if (a[i] == b[j]) {

                ans[j] = i;
                j++;

            } else {

                /*
                 * We use the one allowed mismatch here.
                 *
                 * After choosing i, we need m-j-1
                 * characters to be matched.
                 */
                if (dp[i + 1] >= m - 1 - j) {

                    ans[j] = i;
                    j++;

                    /*
                     * Skip this position because the mismatch
                     * has now been used.
                     */
                    i++;

                    break;
                }
            }

            i++;
        }

        /*
         * Not enough characters were found.
         */
        if (j < m && i == n) {
            return new int[0];
        }

        /*
         * Second phase:
         * The mismatch has already been used (or we found an
         * exact prefix), so all remaining characters must match.
         */
        while (j < m && i < n) {

            if (a[i] == b[j]) {
                ans[j] = i;
                j++;
            }

            i++;
        }

        /*
         * If we couldn't construct the complete sequence,
         * return an empty array.
         */
        if (j < m) {
            return new int[0];
        }

        return ans;
    }
}