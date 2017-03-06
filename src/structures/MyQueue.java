package structures;

public class MyQueue<AnyType> implements Queue<AnyType> {

    private MyDoublyLinkedList<AnyType> list;

    public MyQueue() {
        list = new MyDoublyLinkedList<AnyType>();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void enqueue(AnyType x) {
        list.insert(x);
    }

    @Override
    public AnyType dequeue() {
        if (isEmpty()) return null;

        AnyType temp = peek();
        list.delete(0);
        return temp;
    }

    @Override
    public AnyType peek() {
        if (isEmpty()) return null;
        return list.get(0);
    }

    public void print() {
        list.printList("Queue");
    }
}
