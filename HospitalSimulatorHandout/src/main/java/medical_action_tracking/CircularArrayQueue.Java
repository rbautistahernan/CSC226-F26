package medical_action_tracking;

public class CircularArrayQueue<T>{
    private Object[] items;
    private int front;
    private int rear;
    private int size;

    public CircularArrayQueue(int capacity){
        if (capacity <= 0){
            throw new IllegalArgumentException("Capacity must be positive.");
        }

        items = new Object[capacity];
        front=0;
        rear=0;
        size=0;
    }

    public boolean enqueue(T item){
        if (item==null){
            throw new IllegalArgumentException("Item cannot be null.");
        }
        if (size==items.length){
            return false;
        }
        items[rear] = item;
        rear =(rear+1) % items.length;
        size++;
        return true;
    }

    @SuppressWarnings("unchecked")
    public T dequeue(){
        if (isEmpty()){
            return null;
        }

        T removedItem = (T) items[front];
        items[front]= null;
        front = (front+1) % items.length;
        size--;

        return removedItem;
    }

    @SuppressWarnings("unchecked")
    public T peelFront(){
        if(isEmpty()){
            return null;
        }
        return (T) items[front];
    }

    public boolean isEmpty(){
        return size ==0;
    }

    public int size(){
        return size;
    }

    @Override 
    public String toString(){
        StringBuilder result = new StringBuilder("[");

        for (int i =0; i<size; i++){
            int index = (front + i) % items.length;
            result.append(items(index));

            if (i< size -1){
                result.append(", ");
            }
        }

        result.append("]");
        return result.toString();
    }
}