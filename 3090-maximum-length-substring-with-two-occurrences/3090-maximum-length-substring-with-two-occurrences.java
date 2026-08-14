class Solution {
    public int maximumLengthSubstring(String s) {

        int[] freq = new int[26];

        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {

            int index = s.charAt(right) - 'a';
            freq[index]++;

            // If current character occurs more than twice,
            // shrink the window from the left.
            while (freq[index] > 2) {

                freq[s.charAt(left) - 'a']--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
