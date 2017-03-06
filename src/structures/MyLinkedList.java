package structures;

public class MyLinkedList<AnyType> implements SimpleLinkedList<AnyType> {

    // Sub-class for the node
    private class MyNode<T> {
        T data;
        MyNode<T> next = null;
        MyNode(T data) { this.data = data; }
    }

    private MyNode<AnyType> first = null;
    private int size = 0;

    @Override
    public void insert(AnyType x) {
        // Adds to beginning, O(1)
        if (isEmpty()) {
            first = new MyNode<>(x);
            size = 1;
        } else {
            MyNode<AnyType> temp = new MyNode<>(x);
            temp.next = first;

            first = temp;
            size++;
        }
    }

    @Override
    public AnyType get(int index) {
        // Will run O(n) in worst case
        MyNode<AnyType> node = first;

        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node.data;
    }

    @Override
    public AnyType delete(int index) {
        // Will run O(n) in worst case
        MyNode<AnyType> prev = null;
        MyNode<AnyType> node = first;

        for (int i = 0; i < index; i++) {
            prev = node;
            node = node.next;
        }

        AnyType data = node.data;

        if (prev == null) { // this is first,
            first = node.next;
        } else { // this is not first,
            prev.next = node.next;
        }

        size--;

        return data;
    }

    @Override
    public AnyType delete(AnyType x) {
        // Will run O(n) in worst case
        MyNode<AnyType> prev = null;
        MyNode<AnyType> node = first;

        do {
            if (node.data.equals(x)) {
                if (prev == null) { // this is first,
                    first = node.next;
                } else { // this is not first,
                    prev.next = node.next;
                }
                break;
            }

            prev = node;
            node = node.next;
        } while (node != null);

        size--;

        return x;
    }

    @Override
    public boolean contains(AnyType x) {
        // Will run O(n) in worst case
        MyNode<AnyType> node = first;
        while (node != null) {
            if (node.data.equals(x)) return true;
            node = node.next;
        }

        return false;
    }

    public AnyType lookup(AnyType x) {
        if (contains(x)) return x;
        return null;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    public int getSize() { return size; }

    @Override
    public void printList(String title) {
        // runtime will be O(n), where n is the size of the list
        System.out.print(title + " (" + size + ") [ ");

        if (!isEmpty()) {
            MyNode<AnyType> node = first;
            while (node != null) {
                System.out.print(node.data.toString() + (node.next == null ? " " : ", "));
                node = node.next;
            }
        }

        System.out.println("]");
    }
}
