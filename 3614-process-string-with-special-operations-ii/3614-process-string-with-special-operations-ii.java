class Solution {
    public char processStr(String s, long k) {
        int n = s.length();

        long[] len = new long[n + 1];

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c >= 'a' && c <= 'z') {
                len[i + 1] = len[i] + 1;
            } else if (c == '*') {
                len[i + 1] = Math.max(0, len[i] - 1);
            } else if (c == '#') {
                len[i + 1] = len[i] * 2;
            } else { // %
                len[i + 1] = len[i];
            }
        }

        if (k >= len[n]) {
            return '.';
        }

        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);

            long after = len[i + 1];
            long before = len[i];

            if (c >= 'a' && c <= 'z') {
                if (k == before) {
                    return c;
                }
            } 
            else if (c == '*') {
                // backward: string had one more char before deletion
            } 
            else if (c == '#') {
                if (before > 0 && k >= before) {
                    k -= before;
                }
            } 
            else { // %
                k = before - 1 - k;
            }
        }

        return '.';
    }
}