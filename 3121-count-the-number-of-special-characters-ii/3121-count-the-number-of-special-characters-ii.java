class Solution {
    public int numberOfSpecialChars(String word) {
        int[] lastLower = new int[26];
        int[] firstUpper = new int[26];

        // Initialize
        for (int i = 0; i < 26; i++) {
            lastLower[i] = -1;
            firstUpper[i] = Integer.MAX_VALUE;
        }

        // Record positions
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (Character.isLowerCase(ch)) {
                lastLower[ch - 'a'] = i;
            } else {
                int idx = ch - 'A';
                firstUpper[idx] = Math.min(firstUpper[idx], i);
            }
        }

        int count = 0;

        // Check special characters
        for (int i = 0; i < 26; i++) {
            if (lastLower[i] != -1 &&
                firstUpper[i] != Integer.MAX_VALUE &&
                lastLower[i] < firstUpper[i]) {
                count++;
            }
        }

        return count;
    }
}