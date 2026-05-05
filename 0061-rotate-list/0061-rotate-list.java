class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        ListNode cur = head;
        int len = 1;
        while (cur.next != null) {
            cur = cur.next;
            len++;
        }
        cur.next = head; // make circular

        k %= len;
        int steps = len - k;
        while (steps-- > 0) cur = cur.next;

        head = cur.next;
        cur.next = null;
        return head;
    }
}