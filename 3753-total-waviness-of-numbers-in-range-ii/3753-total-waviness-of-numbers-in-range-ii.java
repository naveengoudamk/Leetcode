class Solution {

    static class Node {
        long count;
        long waviness;

        Node(long count, long waviness) {
            this.count = count;
            this.waviness = waviness;
        }
    }

    private String digits;
    private Node[][][][][] memo;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long n) {
        if (n <= 0) return 0;

        digits = String.valueOf(n);

        int len = digits.length();

        memo = new Node[len + 1][2][3][11][11];

        Node ans = dfs(0, 0, 0, 10, 10, true);

        return ans.waviness;
    }

    private Node dfs(int pos, int started, int lenState,
                     int prev2, int prev1, boolean tight) {

        if (pos == digits.length()) {
            return new Node(1, 0);
        }

        if (!tight && memo[pos][started][lenState][prev2][prev1] != null) {
            return memo[pos][started][lenState][prev2][prev1];
        }

        int limit = tight ? digits.charAt(pos) - '0' : 9;

        long totalCount = 0;
        long totalWaviness = 0;

        for (int d = 0; d <= limit; d++) {

            boolean nextTight = tight && (d == limit);

            if (started == 0 && d == 0) {
                Node child = dfs(pos + 1, 0, 0, 10, 10, nextTight);

                totalCount += child.count;
                totalWaviness += child.waviness;
            } else {

                int add = 0;

                if (lenState == 2) {
                    if ((prev1 > prev2 && prev1 > d) ||
                        (prev1 < prev2 && prev1 < d)) {
                        add = 1;
                    }
                }

                int nextLen;
                int nextPrev2;
                int nextPrev1;

                if (lenState == 0) {
                    nextLen = 1;
                    nextPrev2 = 10;
                    nextPrev1 = d;
                } else if (lenState == 1) {
                    nextLen = 2;
                    nextPrev2 = prev1;
                    nextPrev1 = d;
                } else {
                    nextLen = 2;
                    nextPrev2 = prev1;
                    nextPrev1 = d;
                }

                Node child = dfs(
                        pos + 1,
                        1,
                        nextLen,
                        nextPrev2,
                        nextPrev1,
                        nextTight
                );

                totalCount += child.count;
                totalWaviness += child.waviness + (long) add * child.count;
            }
        }

        Node res = new Node(totalCount, totalWaviness);

        if (!tight) {
            memo[pos][started][lenState][prev2][prev1] = res;
        }

        return res;
    }
}