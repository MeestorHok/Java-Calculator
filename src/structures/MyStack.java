package structures;

public class MyStack<AnyType> implements Stack<AnyType> {

    private MyLinkedList<AnyType> list;

    public MyStack() {
        list = new MyLinkedList<AnyType>();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(AnyType x) {
        list.insert(x);
    }

    @Override
    public AnyType pop() {
        if (isEmpty()) return null;
        return list.delete(0);
    }

    @Override
    public AnyType peek() {
        if (isEmpty()) return null;
        return list.get(0);
    }

    public int getSize() {
        return list.getSize();
    }

    public void print() {
        list.printList("Stack");
    }
}
