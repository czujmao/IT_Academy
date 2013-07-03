package net.rusf.czujmao.calc2.calc;

/**
 * It's my stack class
 */
public class Stack {
    private class StackItem {
        private final Double item;
        private final StackItem next;
        StackItem() {
            this.item = null;
            this.next = null;
        }
        StackItem(double item, StackItem next) {
            this.item = item;
            this.next = next;
        }
    }
    private StackItem p;
    private long count;
    Stack() {
        p = new StackItem();
        count = 0;
    }
    Double pop() {
        if (count > 0) {
            double tmp = p.item;
            p = p.next;
            count--;
            return tmp;
        } else {
            return null;
        }
    }
    void push(double item) {
        p = new StackItem(item, p);
        count++;
    }
    long count() {
        return count;
    }
    Double get() {
        return p.item;
    }
}