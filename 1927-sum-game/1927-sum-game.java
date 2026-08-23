class Solution {
    public boolean sumGame(String num) {

        int n = num.length();
        int half = n / 2;

        int leftSum = 0;
        int rightSum = 0;

        int leftQ = 0;
        int rightQ = 0;

        // First half
        for (int i = 0; i < half; i++) {

            char c = num.charAt(i);

            if (c == '?') {
                leftQ++;
            } else {
                leftSum += c - '0';
            }
        }

        // Second half
        for (int i = half; i < n; i++) {

            char c = num.charAt(i);

            if (c == '?') {
                rightQ++;
            } else {
                rightSum += c - '0';
            }
        }

        // Odd number of '?' means Alice gets the final move
        // and can always make the sums unequal.
        if ((leftQ + rightQ) % 2 == 1) {
            return true;
        }

        /*
         * Bob wins only when he can exactly compensate
         * for the existing difference.
         *
         * Each unmatched pair of '?' can create a difference
         * of at most 9.
         */
        int difference = leftSum - rightSum;

        int required = 9 * (rightQ - leftQ) / 2;

        return difference != required;
    }
}