package leetcode;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * @author yangchang
 */
public class MergeSortedKNodeList {
    public static void main(String[] args) {

    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode result = new ListNode(0);
        ListNode move = result;
        while (true) {
            int min = Integer.MAX_VALUE;
            int index = 0;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null && lists[i].val < min) {
                    min = lists[i].val;
                    index = i;
                }
            }
            if (min == Integer.MAX_VALUE) {
                break;
            }
            lists[index] = lists[index].next;
            ListNode next = new ListNode(min);
            move.next = next;
            move = move.next;
        }
        return result.next;
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
