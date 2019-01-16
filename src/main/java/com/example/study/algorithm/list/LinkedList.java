package com.example.study.algorithm.list;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class LinkedList {

    public class Node {

        private Node next;
        private int data;

        public Node(int data) {
            this.data = data;
        }

        public void appendToTail(int data) {

            if (this.next == null) {
                this.next = new Node(data);
            } else {
                Node head = this;
                while (head.hasNext()) {
                    head = head.getNext();
                }

                head.setNext(new Node(data));
            }
        }

        public Node getNext() {
            return next;
        }

        public Node setNext(Node next) {
            this.next = next;
            return this;
        }

        public int getData() {
            return data;
        }

        public boolean hasNext() {
            return (getNext() != null) ? true : false;
        }

        public Node searchNodeNthFromBackend(int k) {

            int lastCount = 0;

            //find last node
            Node head = this;
            Node last = this;
            while (head != null) {
                lastCount++;

                last = head;
                head = head.getNext();
            }

            if(k == 0)
                return null;

            int findCount = lastCount - k + 1;
            Node find = null;

            //find k'th node
            int count = 0;
            head = this;
            while (head != null) {

                count++;
                if(count == findCount) {
                    find = head;
                    break;
                }
                head = head.getNext();
            }

            return find;
        }

        public void print_list_data() {

            Node head = this;

            while (head != null) {

                System.out.print("data : " + head.getData());
                if (head.hasNext()) {
                    System.out.print(", ");
                }

                head = head.getNext();
            }
        }
    }

    @Test
    public void test() {

        Node list = new Node(3);

        list.appendToTail(45);
        assertEquals(45, list.getNext().getData());

        list.appendToTail(50);
        assertEquals(50, getLastData(list));
    }

    private int getLastData(Node node) {

        if (node.hasNext()) {
            return getLastData(node.getNext());
        } else {
            return node.getData();
        }
    }

    @Test
    public void unsortedList_to_remove_duplication() {

        Node list = makeTestData();

        remove_duplicate(list);

        list.print_list_data();
    }

    @Test
    public void search_node_nth() {

        Node list = makeTestData();
        assertEquals(5, list.searchNodeNthFromBackend(3).getData());
        assertEquals(10, list.searchNodeNthFromBackend(1).getData());
        assertEquals(null, list.searchNodeNthFromBackend(0));
    }

    private void remove_duplicate(Node list) {
        Set<Integer> uniList = new HashSet<>();

        Node head = list;
        Node prev = null;
        while (head != null) {

            if (uniList.contains(head.getData())) {
                //remove this node
                prev.setNext(head.getNext());

            } else {
                uniList.add(head.getData());
            }

            prev = head;
            head = head.getNext();
        }
    }

    private Node makeTestData() {

        Node list = new Node(0);

        list.appendToTail(10);
        list.appendToTail(5);
        list.appendToTail(7);
        list.appendToTail(5);
        list.appendToTail(2);
        list.appendToTail(10);

        return list;
    }
}
