class Solution {

    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();

        // Count characters in s
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        /*
         * Try every position from right to left.
         *
         * We want the longest prefix to be equal to target,
         * and then make the next character just slightly bigger.
         */
        for (int i = n - 1; i >= 0; i--) {

            int[] remaining = freq.clone();

            // Match target[0 ... i-1]
            boolean possible = true;

            for (int j = 0; j < i; j++) {

                int c = target.charAt(j) - 'a';

                if (remaining[c] == 0) {
                    possible = false;
                    break;
                }

                remaining[c]--;
            }

            if (!possible) {
                continue;
            }

            // At position i, choose the smallest character
            // that is greater than target[i].
            int targetChar = target.charAt(i) - 'a';

            for (int c = targetChar + 1; c < 26; c++) {

                if (remaining[c] > 0) {

                    char[] ans = new char[n];

                    // Copy equal prefix
                    for (int j = 0; j < i; j++) {
                        ans[j] = target.charAt(j);
                    }

                    // Make answer greater here
                    ans[i] = (char) ('a' + c);
                    remaining[c]--;

                    // Put remaining characters in sorted order
                    int index = i + 1;

                    for (int x = 0; x < 26; x++) {

                        while (remaining[x] > 0) {
                            ans[index++] = (char) ('a' + x);
                            remaining[x]--;
                        }
                    }

                    return new String(ans);
                }
            }
        }

        // No permutation is greater than target
        return "";
    }
}