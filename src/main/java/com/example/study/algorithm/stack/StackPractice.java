package com.example.study.algorithm.stack;

import static org.assertj.core.api.Java6Assertions.assertThat;

import org.junit.Test;

public class StackPractice {

    class Stack<T> {

        Node<T> top;

        public void push(T data) {

            Node<T> pushNode = new Node<>(data);
            pushNode.prev = top;
            top = pushNode;
        }

        public T pop() {

            if (top != null) {
                Node<T> pop = top;
                top = top.prev;

                return pop.data;

            } else {
                return null;
            }
        }

        private class Node<T> {

            private Node prev;
            private T data;

            public Node(T data) {
                this.data = data;
            }

            public T getData() {
                return data;
            }
        }
    }

    @Test
    public void test() {

        Stack<Integer> stack = new Stack<>();

        stack.push(null);
        stack.push(3);
        stack.push(5);

        assertThat(stack.pop()).isEqualTo(5);
        assertThat(stack.pop()).isEqualTo(3);
        assertThat(stack.pop()).isEqualTo(null);
        assertThat(stack.pop()).isEqualTo(null);
    }

}
