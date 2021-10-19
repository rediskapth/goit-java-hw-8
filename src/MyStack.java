import java.util.Arrays;
import java.util.EmptyStackException;

public class MyStack<T> {
    private T[] stack;
    private int maxSize;
    private int size;
    private int front;
    private int rear;

    public MyStack(int maxSize) {
        this.maxSize = maxSize;
        stack = (T[]) new Object[maxSize];
        rear = -1;
        front = 0;
        size = 0;
    }

    public T[] getStack() {
        return stack;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int getSize() {
        return size;
    }

    public int getFront() {
        return front;
    }

    public int getRear() {
        return rear;
    }

    public void push(T value) {
        try {
            stack[++rear] = (T) value;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Queue is full. " + "Unable to add " + value);
        }
        size++;
    }

    public void remove(int index) {
        try {
            int numMoved = size - index - 1;
            System.arraycopy(stack, index + 1, stack, index, numMoved);
            stack[--size] = null;
            rear--;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Unable to remove element at index - " + index +
                    ". Index out of bounds or queue is empty.");
        }
    }

    public void clear() {
        size = front = rear = 0;
        Arrays.fill(stack, null);
    }

    public int size() {
        return size;
    }

    public T peek() {
        if (size == 0) throw new EmptyStackException();
        return stack[getRear()];
    }

    public T pop() {
        T firstElement = peek();
        remove(getRear());
        return firstElement;
    }

    public T[] reverseElements(T[] stack) {
        T[] reverseStack = Arrays.copyOf(stack, stack.length);
        for (int i = 0; i < getSize() / 2; i++) {
            T temp = reverseStack[i];
            reverseStack[i] = reverseStack[getSize() - 1 - i];
            reverseStack[getSize() - 1 - i] = temp;
        }
        return reverseStack;
    }

    public String toString() {
        return "MyStack{" +
                "stack=" + Arrays.toString(reverseElements(stack)) +
                ", maxSize=" + maxSize +
                ", size=" + size +
                ", front=" + front +
                ", rear=" + rear +
                '}';
    }
}
