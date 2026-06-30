// Q2. Number of Substrings Containing All Three Characters (Medium)
class Solution {
    public int numberOfSubstrings(String s) {
        int[] cnt = new int[3];
        int left = 0, res = 0;

        for (int right = 0; right < s.length(); right++) {
            cnt[s.charAt(right) - 'a']++;
            while (cnt[0] > 0 && cnt[1] > 0 && cnt[2] > 0) {
                res += s.length() - right;
                cnt[s.charAt(left) - 'a']--;
                left++;
            }
        }
        return res;
    }
}
