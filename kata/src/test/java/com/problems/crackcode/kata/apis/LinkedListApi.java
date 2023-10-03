package com.problems.crackcode.kata.apis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Objects;

public class LinkedListApi {

    @Test
    @DisplayName("Test Remove Dups")
    void testRemoveDups() {
        // Create a sample linked list with duplicate elements
        LinkedListNode node1 = new LinkedListNode(1);
        LinkedListNode node2 = new LinkedListNode(2);
        LinkedListNode node3 = new LinkedListNode(2); // Duplicate
        LinkedListNode node4 = new LinkedListNode(3);
        LinkedListNode node5 = new LinkedListNode(3); // Duplicate
        LinkedListNode node6 = new LinkedListNode(4);

        // Connect the nodes to form a linked list
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        removeDuplicatesOn(node1);

        System.out.println(node1);
    }


    //formatter:off

    /**
     * Remove Dups! Write code to remove duplicates from an unsorted linked list.
     * FOLLOW UP
     * How would you solve this problem if a temporary buffer is not allowed?
     */
    //formatter:on

    void removeDuplicatesOn(LinkedListNode node) {
        HashSet<LinkedListNode> hs = new HashSet<>();
        LinkedListNode prev = null;
        while (node != null) {
            if (hs.contains(node)) {
                prev.next = node.next;
            } else {
                hs.add(node);
                prev = node;
            }
            node = node.next;
        }
    }


    @Test
    @DisplayName("Test Remove Dups")
    void testRemoveDupsWithoutBuffer() {
        // Create a sample linked list with duplicate elements
        LinkedListNode node1 = new LinkedListNode(1);
        LinkedListNode node2 = new LinkedListNode(2);
        LinkedListNode node3 = new LinkedListNode(2); // Duplicate
        LinkedListNode node4 = new LinkedListNode(3);
        LinkedListNode node5 = new LinkedListNode(3); // Duplicate
        LinkedListNode node6 = new LinkedListNode(4);

        // Connect the nodes to form a linked list
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        removeDupsNoBuffer(node1);

        System.out.println(node1);
    }

    //if buffer is not allowed
    void removeDupsNoBuffer(LinkedListNode node) {
        // this is a bad soln with O(n^2)
        LinkedListNode curr = node;
        while (curr != null) {
            LinkedListNode runner = curr;
            while (runner != null) {
                if (runner.next.x == curr.x) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            curr = curr.next;
        }
    }

    @Test
    @DisplayName("Find Kth from Last")
    void findKthFromLast() {
        LinkedListNode node1 = new LinkedListNode(1);
        LinkedListNode node2 = new LinkedListNode(2);
        LinkedListNode node3 = new LinkedListNode(3); // Duplicate
        LinkedListNode node4 = new LinkedListNode(4);
        LinkedListNode node5 = new LinkedListNode(5); // Duplicate
        LinkedListNode node6 = new LinkedListNode(6);

        // Connect the nodes to form a linked list
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        int kthFromTheLast = findKthFromTheLast(node1, 2);
        Assertions.assertEquals(5, kthFromTheLast);
    }


    int findKthFromTheLast(LinkedListNode head, int k) {
        LinkedListNode counter = head;
        int lt = 0;
        while (counter != null) {
            lt++;
            counter = counter.next;
        }

        LinkedListNode node = head;
        int ctr = 1;
        while (node != null) {
            if (ctr == lt - k + 1) {
                return node.x;
            }
            ctr++;
            node = node.next;
        }

        throw new RuntimeException("This should not happen really !");
    }

    /**
     * Delete Middle Node: Implement an algorithm to delete a node in the middle (i.e., any node but
     * the first and last node, not necessarily the exact middle) of a singly linked list, given only access to
     * that node.
     * <p>
     * EXAMPLE
     * lnput:the node c from the linked lista->b->c->d->e->f
     * Result: nothing is returned, but the new linked list looks like a->b->d->e->f
     */

    @Test
    @DisplayName("Test Remove Middle Or Any Other Element")
    void testRemoveMiddleOrAnyOtherElement() {
        LinkedListNode node1 = new LinkedListNode(1);
        LinkedListNode node2 = new LinkedListNode(2);
        LinkedListNode node3 = new LinkedListNode(3); // Duplicate
        LinkedListNode node4 = new LinkedListNode(4);
        LinkedListNode node5 = new LinkedListNode(5); // Duplicate
        LinkedListNode node6 = new LinkedListNode(6);

        // Connect the nodes to form a linked list
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        System.out.println(node1);

        removeMiddleOrAnyElement(node4);

        System.out.println(node1);
    }


    void removeMiddleOrAnyElement(LinkedListNode node) {
        if (node != null || node.next != null) {
            node.x = node.next.x;
            node.next = node.next.next;
        } else {
            throw new RuntimeException("This is not possible !");
        }
    }


    @Test
    @DisplayName("Partition the linked list")
    void testpartitionTheLinkedList() {
        LinkedListNode node1 = new LinkedListNode(3);
        LinkedListNode node2 = new LinkedListNode(5);
        LinkedListNode node3 = new LinkedListNode(8); // Duplicate
        LinkedListNode node4 = new LinkedListNode(5);
        LinkedListNode node5 = new LinkedListNode(10); // Duplicate
        LinkedListNode node6 = new LinkedListNode(2);
        LinkedListNode node7 = new LinkedListNode(1);

        // Connect the nodes to form a linked list
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        System.out.println(node1);

        partitionTheLinkedList(node1, 5);

        System.out.println(node1);
    }


    void partitionTheLinkedList(LinkedListNode node, int d) {
        LinkedListNode itr = node;
        LinkedListNode k = node;

        while (itr != null) {
            if (itr.x < d) {
                _swapNodes(k, itr);
                k = k.next;
            }
            itr = itr.next;
        }
    }

    private static void _swapNodes(LinkedListNode k, LinkedListNode itr) {
        int temp = k.x;
        k.x = itr.x;
        itr.x = temp;
    }


    @Test
    @DisplayName("Test Sum Two LLs")
    void testSumTwoLLs() {
        LinkedListNode n11 = new LinkedListNode(7);
        LinkedListNode n12 = new LinkedListNode(1);
        LinkedListNode n13 = new LinkedListNode(6);

        n11.next = n12;
        n12.next = n13;

        LinkedListNode n21 = new LinkedListNode(5);
        LinkedListNode n22 = new LinkedListNode(9);
        LinkedListNode n23 = new LinkedListNode(2);

        n21.next = n22;
        n22.next = n23;

        LinkedListNode e1 = new LinkedListNode(2);
        LinkedListNode e2 = new LinkedListNode(1);
        LinkedListNode e3 = new LinkedListNode(9);

        e1.next = e2;
        e2.next = e3;


        LinkedListNode op = sumTwoNums(n11, n21);

        LinkedListNode opItr = op;
        LinkedListNode eItr = e1;

        System.out.println(op);

        Assertions.assertNotNull(op);

        while (opItr != null && eItr != null) {
            Assertions.assertEquals(eItr.x, opItr.x);
            opItr = opItr.next;
            eItr = eItr.next;
        }

    }


    LinkedListNode sumTwoNums(LinkedListNode n1, LinkedListNode n2) {
        LinkedListNode sum = null;
        LinkedListNode toReturn = sum;
        int carry = 0;


        while (n1 != null && n2 != null) {
            LinkedListNode sumItr = null;

            int cs = n1.x + n2.x;
            if (cs > 9) {
                int curVal = cs;
                cs = cs % 10 + carry;
                carry = curVal / 10;
            } else {
                cs = cs + carry;
            }
            sumItr = new LinkedListNode(cs);

            if (sum == null) {
                sum = sumItr;
                toReturn = sum;
            } else {
                sum.next = sumItr;
                sum = sum.next;
            }

            n1 = n1.next;
            n2 = n2.next;
        }

        return toReturn;
    }


    @Test
    @DisplayName("Test If Palindorme")
    void testIfPalindorme() {
        LinkedListNode n11 = new LinkedListNode(1);
        LinkedListNode n12 = new LinkedListNode(3);
        LinkedListNode n13 = new LinkedListNode(1);

        n11.next = n12;
        n12.next = n13;

        Assertions.assertTrue(checkPalindrome(n11));
    }


    @Test
    @DisplayName("Test If Palindorme")
    void testIfPalindorme1() {
        LinkedListNode n11 = new LinkedListNode(1);
        LinkedListNode n12 = new LinkedListNode(3);
        LinkedListNode n13 = new LinkedListNode(12);

        n11.next = n12;
        n12.next = n13;

        Assertions.assertFalse(checkPalindrome(n11));
    }


    boolean checkPalindrome(LinkedListNode node) {
        LinkedListNode end = node;
        LinkedListNode start = node;

        return _palindromeHelper(start, end) == null;
    }

    private LinkedListNode _palindromeHelper(LinkedListNode start, LinkedListNode end) {
        if (end == null) {
            return start;
        }

        LinkedListNode nextNode = _palindromeHelper(start, end.next);

        if (end.x != nextNode.x) {
            return nextNode;
        }
        return nextNode == null ? null : nextNode.next;
    }


    //@formatter:off
    /**
     *
     * below represents two linked lists that intersect with each other
     * apparently (this is not understandable by me why) two intersecting LLs
     * must end on the same node !!!
     *
     *  a->b->c->d->e->null
     *        |
     *  f->g->h
     * */
    //@formatter:on


    LinkedListNode findTheIntersectionPoint(LinkedListNode n1, LinkedListNode n2) {
        //u can use a set too, but let's do the college way

        int l1 = findLtOfLl(n1);
        int l2 = findLtOfLl(n2);

        LinkedListNode longer = null;
        LinkedListNode shorter = null;

        //check if both lls end at the same node - can oo this in findLtOfLl method
        // if they do - then stop the execution

        longer = l1 > l2 ? n1 : n2;
        shorter = l1 > l2 ? n2 : n1;

        LinkedListNode offset = getLongerNodesOffsetPos(longer, Math.abs(l1 - l2));

        while (offset != shorter) {
            offset = offset.next;
            shorter = shorter.next;
        }

        return offset;
    }

    private LinkedListNode getLongerNodesOffsetPos(LinkedListNode longer, int diff) {
        while (diff > 0) {
            if (longer == null) {
                throw new RuntimeException("This is not possible !");
            }
            longer = longer.next;
            diff--;
        }
        return longer;
    }

    int findLtOfLl(LinkedListNode node) {
        int l = 0;
        while (node != null) {
            l++;
            node = node.next;
        }
        return l;
    }
}


class Wrapper {
    Integer i;
    Boolean flag;

    public Wrapper(Integer i) {
        this.i = i;
    }

    public Wrapper(Boolean flag) {
        this.flag = flag;
    }
}

class LinkedListNode {
    public LinkedListNode() {

    }

    public LinkedListNode(int x) {
        this.x = x;
    }

    int x;
    LinkedListNode next;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedListNode that = (LinkedListNode) o;
        return x == that.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x);
    }

    @Override
    public String toString() {
        return "LLN{" + "x=" + x + "}--->" + next;
    }
}