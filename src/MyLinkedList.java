public class MyLinkedList<T> {
    private int size;
    private Node<T> first;
    private Node<T> last;


    private static class Node<T> {
        T element;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    '}';
        }
    }

    public void add(Object value) {
        Node<T> lastNode = last;
        Node<T> newNode = new Node<T>(lastNode, (T) value, null);
        last = newNode;
        if (lastNode == null) {
            first = newNode;
        } else {
            lastNode.next = newNode;
        }
        size++;
    }

    public void remove(int index) {
        if(index >= 0 && index < size) {
        Node<T> removeNode = get(index);
        Node<T> prev = removeNode.prev;
        Node<T> next = removeNode.next;
            if (next == null) {
                last = prev;
                prev.next = null;
                removeNode.element = null;
            } else if (prev == null) {
                first = next;
                next.prev = null;
                removeNode.element = null;
            } else {
                next.prev = prev;
                prev.next = next;
                removeNode.element = null;
            }
            size--;
        }
    }

    public void clear() {
        Node<T> someNode = first;
        for (int i = 0; i < size; i++) {
            someNode.prev = null;
            someNode.element = null;
            someNode.next = null;
            last = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    public Node<T> get(int index) {
        Node<T> searchNode = first;
        if (index < 0 || index > size) throw new java.lang.ArrayIndexOutOfBoundsException("No such Index");
        for (int i = 0; i < index; i++) {
            searchNode = searchNode.next;
        }
        return searchNode;
    }

    @Override
    public String toString() {
        return "MyLinkedList{" +
                "size=" + size +
                ", first=" + first +
                ", last=" + last +
                '}';
    }
}
