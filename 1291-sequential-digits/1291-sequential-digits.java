import java.util.*;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> ans = new ArrayList<>();

        String digits = "123456789";

        for (int len = 2; len <= 9; len++) {
            for (int i = 0; i + len <= 9; i++) {
                int num = Integer.parseInt(digits.substring(i, i + len));

                if (num >= low && num <= high) {
                    ans.add(num);
                }
            }
        }

        Collections.sort(ans);
        return ans;
    }
}