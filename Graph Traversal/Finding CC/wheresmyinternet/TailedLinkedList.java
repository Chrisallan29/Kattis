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
        if (this.head == null) {
            this.head = n;
            this.tail = n;
            size++;
        } else if (this.head == this.tail) {
            this.tail.next = n;
            this.tail = n;
            size++;
        } else {
            this.tail.next = n;
            this.tail = n;
            size++;
        }
    }

    Node dequeue() {
        if (this.head != null) {
            Node oldHead = this.head;
            this.head = this.head.next;
            size--;
            return oldHead;
        }
        return null;
    }

    int peek() {
        return this.head.value;
    }

    boolean isEmpty() {
        return this.size == 0;
    }
}
