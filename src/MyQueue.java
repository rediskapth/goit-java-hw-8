import java.util.Arrays;

public class MyQueue<T> {
    private T[] queue;
    private int maxSize;
    private int size;
    private int front;
    private int rear;

    public MyQueue(int maxSize) {
        this.maxSize = maxSize;
        queue = (T[]) new Object[maxSize];
        rear = -1;
        front = 0;
        size = 0;
    }

    public T[] getQueue() {
        return queue;
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

    public void add(T value) {
        try {
            queue[++rear] = (T) value;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Queue is full. " + "Unable to add " + value);
        }
        size++;
    }

    public void remove(int index) {
        try {
            int numMoved = size - index - 1;
            System.arraycopy(queue, index + 1, queue, index, numMoved);
            queue[--size] = null;
            rear--;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Unable to remove element at index - " + index +
                    ". Index out of bounds or queue is empty.");
        }
    }

    public void clear() {
        size = front = rear = 0;
        Arrays.fill(queue, null);
    }

    public int size() {
        return size;
    }

    public T peek() {
        return (T) queue[0];
    }

    public T poll() {
        T firstElement = queue[0];
        remove(0);
        return firstElement;
    }

    @Override
    public String toString() {
        return "MyQueue{" +
                "queue=" + Arrays.toString(queue) +
                ", maxSize=" + maxSize +
                ", size=" + size +
                ", front=" + front +
                ", rear=" + rear +
                '}';
    }
}
