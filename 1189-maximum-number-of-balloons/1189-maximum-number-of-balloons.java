class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] cnt = new int[26];

        for (char c : text.toCharArray()) {
            cnt[c - 'a']++;
        }

        return Math.min(
            Math.min(cnt['b' - 'a'], cnt['a' - 'a']),
            Math.min(
                cnt['n' - 'a'],
                Math.min(cnt['l' - 'a'] / 2, cnt['o' - 'a'] / 2)
            )
        );
    }
}