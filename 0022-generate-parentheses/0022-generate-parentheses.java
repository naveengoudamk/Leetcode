import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(res, "", 0, 0, n);
        return res;
    }

    private void helper(List<String> res, String s, int o, int c, int n) {
        if (s.length() == n * 2) {
            res.add(s);
            return;
        }

        if (o < n) {
            helper(res, s + "(", o + 1, c, n);
        }

        if (c < o) {
            helper(res, s + ")", o, c + 1, n);
        }
    }
}
