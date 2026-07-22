/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Create a dummy node to act as the starting point
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Traverse both lists as long as both have nodes
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // If one list is exhausted, attach the remaining nodes of the other
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }

        // The merged list starts after the dummy node
        return dummy.next;
    }
}
