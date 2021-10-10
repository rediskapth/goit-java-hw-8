import java.util.Arrays;

public class MyArrayList<T> {
    private T[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList(int capacity) {
        this.array = (T[]) new Object[capacity];
    }

    public MyArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public T[] add(Object value) {
        if (size == array.length) {
            ensureCapacity();
        }
        array[size++] = (T) value;
        return array;
    }

    public void ensureCapacity() {
        int newCapacity = (size * 3) / 2 + 1;
        array = Arrays.copyOf(array, newCapacity);
    }

    public T[] remove(int index) {
        try {
            int numMoved = size - index - 1;
            System.arraycopy(array, index + 1, array, index, numMoved);
            array[--size] = null;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Unable to remove element at index - " + index +
                    ". Index out of bounds or array is empty.");
        }
        return array;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        if (index < 0 || index >= array.length) throw new java.lang.ArrayIndexOutOfBoundsException("No such Index");
            return array[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
