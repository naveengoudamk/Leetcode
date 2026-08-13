class Solution {

    class Node {
        char leftChar;
        char rightChar;

        int leftLen;
        int rightLen;
        int maxLen;
        int len;

        Node() {}

        Node(char c) {
            leftChar = c;
            rightChar = c;
            leftLen = 1;
            rightLen = 1;
            maxLen = 1;
            len = 1;
        }
    }

    Node[] tree;
    char[] s;

    public int[] longestRepeating(
            String str,
            String queryCharacters,
            int[] queryIndices) {

        s = str.toCharArray();

        int n = s.length;
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int q = queryIndices.length;
        int[] answer = new int[q];

        for (int i = 0; i < q; i++) {

            int index = queryIndices[i];
            char c = queryCharacters.charAt(i);

            s[index] = c;

            update(1, 0, n - 1, index, c);

            answer[i] = tree[1].maxLen;
        }

        return answer;
    }

    private void build(int node, int left, int right) {

        if (left == right) {
            tree[node] = new Node(s[left]);
            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(
            int node,
            int left,
            int right,
            int index,
            char c) {

        if (left == right) {
            tree[node] = new Node(c);
            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, c);
        } else {
            update(node * 2 + 1, mid + 1, right, index, c);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node merge(Node a, Node b) {

        Node res = new Node();

        res.len = a.len + b.len;

        res.leftChar = a.leftChar;
        res.rightChar = b.rightChar;

        // Length of prefix
        res.leftLen = a.leftLen;

        if (a.leftLen == a.len && a.rightChar == b.leftChar) {
            res.leftLen = a.len + b.leftLen;
        }

        // Length of suffix
        res.rightLen = b.rightLen;

        if (b.rightLen == b.len && a.rightChar == b.leftChar) {
            res.rightLen = b.len + a.rightLen;
        }

        // Best segment entirely inside left/right
        res.maxLen = Math.max(a.maxLen, b.maxLen);

        // Segment crossing the boundary
        if (a.rightChar == b.leftChar) {
            res.maxLen = Math.max(
                    res.maxLen,
                    a.rightLen + b.leftLen
            );
        }

        return res;
    }
}