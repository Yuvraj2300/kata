package com.problems.crackcode.kata.apis;

import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
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
}


class LinkedListNode {
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
        return "LLN{" +
                "x=" + x +
                "}--->" +
                next;
    }
}