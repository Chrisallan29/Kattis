public class TailedLinkedList {
    Node head;
    Node tail;
    int size;

    TailedLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    void enqueue(Node n) {
        if (this.head == null && this.tail == null) {
            this.head = n;
            this.tail = n;
        } else if (this.head == this.tail) {
            this.head.next = n;
            this.tail = n;
        } else {
            this.tail.next = n;
            this.tail = n;
        }
        size++;
    }

    Node dequeue() {
        if (this.size == 0) {
            return null;
        } else if (this.head == this.tail) {
            Node cur = this.head;
            this.head = null;
            this.tail = null;
            size--;
            return cur;
        } else {
            Node cur = this.head;
            this.head = this.head.next;
            size--;
            return cur;
        }
    }

    boolean isEmpty() {
        return this.size == 0;
    }
}
