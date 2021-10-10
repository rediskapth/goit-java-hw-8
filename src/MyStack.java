import java.util.Arrays;

public class MyStack<T> extends MyQueue<T> {
    private T[] stack = super.getQueue();

    public MyStack(int maxSize) {
        super(maxSize);
    }

    public void push(Object value) {
        super.add(value);
    }

    @Override
    public T peek() {
        return stack[getRear()];
    }

    public T pop() {
        T firstElement = stack[getRear()];
        remove(getRear());
        return firstElement;
    }

    public T[] reverseElements(T[] stack) {
        T[] reverseStack = Arrays.copyOf(stack, stack.length);
        for (int i = 0; i < getSize()/2; i++) {
            T temp = reverseStack[i];
            reverseStack[i] = reverseStack[getSize() - 1 - i];
            reverseStack[getSize() - 1 - i] = temp;
        }
        return reverseStack;
    }

    @Override
    public String toString() {
        return "MyStack{" +
                "stack=" + Arrays.toString(reverseElements(stack)) +
                ", maxSize=" + getMaxSize() +
                ", size=" + getSize() +
                ", front=" + getFront() +
                ", rear=" + getRear() +
                '}';
    }
}
