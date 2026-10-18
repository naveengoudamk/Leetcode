import java.util.ArrayList;
import java.util.List;

class Solution {
    private static final long MOD = 1000000007L;
    private long[][] combTable;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        
        combTable = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            combTable[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                combTable[i][j] = (combTable[i - 1][j - 1] + combTable[i - 1][j]) % MOD;
            }
        }
        
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        
        return (int) ((dfs(list) - 1 + MOD) % MOD);
    }

    private long dfs(List<Integer> list) {
        if (list.size() <= 2) {
            return 1;
        }
        
        int root = list.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        
        for (int i = 1; i < list.size(); i++) {
            int val = list.get(i);
            if (val < root) {
                left.add(val);
            } else {
                right.add(val);
            }
        }
        
        long leftWays = dfs(left);
        long rightWays = dfs(right);
        
        long waysToCombine = combTable[list.size() - 1][left.size()];
        
        return (((waysToCombine * leftWays) % MOD) * rightWays) % MOD;
    }
}
