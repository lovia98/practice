package com.example.study.algorithm.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.assertj.core.api.Java6Assertions;
import org.junit.Test;

public class QueuePractice {

    class Queue<T> {

        Node<T> first, last;

        public void push(T data) {

            if (first == null) {
                first = new Node(data);
                last = first;
            } else {

                Node temp = new Node(data);

                last.next = temp;
                last = temp;
            }

        }

        public T pop() {

            if(first != null) {
                T firstData = (T) first.data;
                first = first.next;

                return firstData;

            }else {
                return null;
            }
        }

        private class Node<T> {

            private Node next;
            private T data;

            public Node(T data) {
                this.data = data;
            }
        }
    }

    @Test
    public void test() {

        Queue<Integer> queue = new Queue<>();

        queue.push(3);
        queue.push(5);
        queue.push(4);
        queue.push(0);
        queue.push(6);

        Java6Assertions.assertThat(queue.pop()).isEqualTo(3);
        Java6Assertions.assertThat(queue.pop()).isEqualTo(5);
        Java6Assertions.assertThat(queue.pop()).isEqualTo(4);
        Java6Assertions.assertThat(queue.pop()).isEqualTo(0);
        Java6Assertions.assertThat(queue.pop()).isEqualTo(6);
        Java6Assertions.assertThat(queue.pop()).isNull();
    }


}
