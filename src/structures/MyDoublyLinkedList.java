package structures;

public class MyDoublyLinkedList<AnyType> implements SimpleLinkedList<AnyType> {

    private MyDoubleNode<AnyType> first = null;
    private MyDoubleNode<AnyType> last = null;

    @Override
    public void insert(AnyType x) {
        // Runtime is O(1): constant time
        if (isEmpty()) {
            first = new MyDoubleNode<AnyType>(x);
            last  = first;
        } else {
            last.next = new MyDoubleNode<AnyType>(x);
            last.next.prev = last;
            last = last.next;
        }
    }

    @Override
    public AnyType delete(AnyType x) {
        // Runtime is O(n)
        MyDoubleNode<AnyType> node = first;
        AnyType data = null;
        while (node != null) {
            if (node.data.equals(x)) {
                if (node.next == null) {
                    last = node.prev;
                } else {
                    node.next.prev = node.prev;
                }

                if (node.prev == null) {
                    first = node.next;
                } else {
                    node.prev.next = node.next;
                }
                data = node.data;
            }
            node = node.next;
        }

        return data;
    }

    @Override
    public boolean contains(AnyType x) {
        // Runtime is O(n)
        MyDoubleNode<AnyType> node = first;
        while (node != null) {
            if (node.data.equals(x)) return true;
            node = node.next;
        }
        return false;
    }

    @Override
    public AnyType get(int index) {
        // Runtime is O(n)
        // if index is negative, start at the end
        MyDoubleNode<AnyType> node = (index < 0) ? last : first;
        if (index < 0) index++; // make -1 = 0 when in reverse

        for (int i = 0; i < Math.abs(index); i++) {
            node = (index < 0) ? node.prev : node.next;
        }

        return node.data;
    }

    @Override
    public AnyType delete(int index) {
        // Runtime is O(n)
        // if index is negative, start at the end
        MyDoubleNode<AnyType> node = (index < 0) ? last : first;
        if (index < 0) index++; // make -1 = 0 when in reverse

        for (int i = 0; i < Math.abs(index); i++) {
            node = (index < 0) ? node.prev : node.next;
        }

        AnyType data = node.data;

        if (node.next == null) {
            last = node.prev;
        } else {
            node.next.prev = node.prev;
        }

        if (node.prev == null) {
            first = node.next;
        } else {
            node.prev.next = node.next;
        }

        return data;
    }

    @Override
    public boolean isEmpty() {
        return first == null && last == null;
    }

    @Override
    public void printList(String title) {
        // Runtime is O(n)
        System.out.print(title + " [ ");
        MyDoubleNode<AnyType> node = first;
        while (node != null) {
            System.out.print(node.data + (node.next == null ? " " : ", "));
            node = node.next;
        }
        System.out.println("]");
    }

    public void printListRev(String title) {
        // Runtime is O(n)
        System.out.print(title + " [ ");
        MyDoubleNode<AnyType> node = last;
        while (node != null) {
            System.out.print(node.data + (node.prev == null ? " " : ", "));
            node = node.prev;
        }
        System.out.println("] (reverse)");
    }
}
