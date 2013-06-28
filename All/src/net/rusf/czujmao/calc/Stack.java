package net.rusf.czujmao.calc;

/**
 * It's my stack class
 */
public class Stack {
    class StackItem {
        final Double item;
        final long count;
        final StackItem next;
        StackItem() {
            this.item = null;
            this.count = 0;
            this.next = null;
        }
        StackItem(double item, long count, StackItem next) {
            this.item = item;
            this.count = count;
            this.next = next;
        }
    }
    private StackItem p;
    Stack() {
        p = new StackItem();
    }
    public Double pop() {
        if (p.count > 0) {
            double tmp = p.item;
            p = p.next;
            return tmp;
        } else {
            return null;
        }
    }
    public void push(double item) {
        p = new StackItem(item, p.count + 1, p);
    }
    public long count() {
        return p.count;
    }
    public Double get() {
        return p.item;
    }
}
