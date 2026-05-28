class Solution {

    static class TrieNode {
        TrieNode[] child = new TrieNode[26];

        // best index for this suffix
        int idx;
        int len;

        TrieNode(int idx, int len) {
            this.idx = idx;
            this.len = len;
        }
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {

        // global best for empty suffix
        int bestIdx = 0;
        for (int i = 1; i < wordsContainer.length; i++) {
            if (wordsContainer[i].length() < wordsContainer[bestIdx].length()) {
                bestIdx = i;
            }
        }

        TrieNode root = new TrieNode(bestIdx, wordsContainer[bestIdx].length());

        // Build reversed trie
        for (int i = 0; i < wordsContainer.length; i++) {
            insert(root, wordsContainer[i], i);
        }

        int[] ans = new int[wordsQuery.length];

        for (int i = 0; i < wordsQuery.length; i++) {
            ans[i] = query(root, wordsQuery[i]);
        }

        return ans;
    }

    private void insert(TrieNode root, String word, int idx) {

        TrieNode node = root;
        int n = word.length();

        // traverse from end (suffix trie)
        for (int i = n - 1; i >= 0; i--) {

            int c = word.charAt(i) - 'a';

            if (node.child[c] == null) {
                node.child[c] = new TrieNode(idx, n);
            }

            node = node.child[c];

            // update best candidate
            if (n < node.len) {
                node.len = n;
                node.idx = idx;
            }
        }
    }

    private int query(TrieNode root, String word) {

        TrieNode node = root;
        int ans = root.idx;

        for (int i = word.length() - 1; i >= 0; i--) {

            int c = word.charAt(i) - 'a';

            if (node.child[c] == null) {
                break;
            }

            node = node.child[c];
            ans = node.idx;
        }

        return ans;
    }
}