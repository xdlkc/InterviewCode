package chapter1;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * <p>q：设计一个有getMin功能的栈 </p>
 * <p>a：两个栈，一个存实际元素，一个存目前栈中最小值</p>
 * <p>
 * </p>
 * DATE 2020/3/16.
 *
 * @author zhangjunbo.
 */
public class Q1 {
    class MyStack<T extends Comparable<T>> {
        private volatile Stack<T> stack;
        private volatile Stack<T> minStack;

        public MyStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        /**
         * 出栈
         *
         * @return
         */
        public synchronized T pop() {
            if (stack == null || stack.isEmpty()) {
                throw new EmptyStackException();
            }
            T t = stack.pop();
            T min = minStack.peek();
            // 入栈方式决定了 实际栈顶 >= 最小栈顶
            if (t.compareTo(min) == 0) {
                minStack.pop();
            }
            return t;
        }

        /**
         * 入栈
         *
         * @param t
         */
        public void push(T t) {
            stack.push(t);
            T min = minStack.peek();
            if (t.compareTo(min) <= 0) {
                minStack.push(t);
            }
        }

        /**
         * 获取栈中最小值
         *
         * @return
         */
        public synchronized T getMin() {
            if (minStack == null || minStack.isEmpty()) {
                throw new EmptyStackException();
            }
            return minStack.peek();
        }

    }
}
