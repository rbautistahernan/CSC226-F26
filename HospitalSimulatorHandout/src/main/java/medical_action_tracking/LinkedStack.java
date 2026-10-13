package medical_action_tracking;

public class LinkedStack<T> {
    private class Node {
        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
        }
    }

    private Node top;
    private int size;

    public void push(T item) {
        // TODO: Reject null items, then link a new node at the top and update size.
        if (item==null){
            throw new IllegalArgumentException("Item cannot be null");
        }
        Node newNode = new Node(item);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public T pop() {
        // TODO: Remove and return the top item, or return null when empty.
        if (top==null){
        return null;
        }
        T removedItem = top.data;
        top = top.next;
        size--;
        return removedItem;
    }


    public T peek() {
        // TODO: Return the top item without removing it, or null when empty.
        if (top==null){
        return null;
        }
        return top.data;
    }

    public boolean isEmpty() {
        // TODO: Determine whether the stack contains any items.
        return top==null;
    }

    public int size() {
        // TODO: Return the number of stacked items.
        return size;
    }

    @Override
    public String toString() {
        // TODO: Build [top, next, ...] by traversing the stack without changing it.
        StringBuilder result = new StringBuilder("[");
        Node current = top;
        while (current != null){
            result.append(current.data);
            if (current.next != null){
                result.append(", ");
            }
            current = current.next;
         }
         result.append("]");
         return result.toString();
        }
    }
}