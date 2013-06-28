package net.rusf.czujmao.calc;

/**
 * It's my stack class
 */
public class Stack {
    class StackItem {
        final Double item;
        final StackItem next;
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
    public Double pop() {
        if (count > 0) {
            double tmp = p.item;
            p = p.next;
            count--;
            return tmp;
        } else {
            return null;
        }
    }
    public void push(double item) {
        p = new StackItem(item, p);
        count++;
    }
    public long count() {
        return count;
    }
    public Double get() {
        return p.item;
    }
}
