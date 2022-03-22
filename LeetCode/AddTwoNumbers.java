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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = null;
        ListNode cur = null;

        int c = 0;
        while(l1 != null || l2 != null){
            int sum = c;
            if(l1 != null)
                sum += l1.val;

            if(l2 != null)
                sum += l2.val;

            if(sum > 9){
                c = 1;
                sum -= 10;
            }else{
                c = 0;
            }

            ListNode temp = new ListNode();
            temp.val = sum;

            if(ans == null){
                ans = temp;
                cur = temp;
            }else{
                cur.next = temp;
                cur = cur.next;
            }

            if(l1 != null)
                l1 = l1.next;
            if(l2 != null)
                l2 = l2.next;
        }

        if(c != 0)
            cur.next = new ListNode(c);


        return ans;
    }
}