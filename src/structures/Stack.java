package structures;

public interface Stack<AnyType> {
    boolean isEmpty();
    void push(AnyType x);
    AnyType pop();
    AnyType peek();
}
