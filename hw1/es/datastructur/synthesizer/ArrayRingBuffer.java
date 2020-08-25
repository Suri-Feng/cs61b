package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public

public class ArrayRingBuffer<T> implements BoundedQueue<T> {

    /* Index for the next dequeue or peek. */
    private int first;

    /* Index for the next enqueue. */
    private int last;

    /* Variable for the fillCount. */
    private int fillCount;

    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount(){
        return fillCount;
    }
    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (this.isFull()){
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        fillCount += 1;
        last += 1;
        if (last == capacity()){
            last = 0;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (this.isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        T item = rb[first];
        rb[first] = null;
        fillCount -= 1;
        first += 1;
        if (first == capacity()){
            first = 0;
        }
        return item;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (this.isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    private class ArrayRingBufferIterator implements Iterator<T> {

        private int curr;

        public ArrayRingBufferIterator(){
            curr = first;
        }

        @Override
        public boolean hasNext(){
            return curr != last;
        }

        @Override
        public T next(){
            T returnItem = rb[curr];
            curr += 1;
            if (curr == capacity()){
                curr = 0;
            }
            return returnItem;
        }

    }

    public Iterator<T> iterator(){
        return new ArrayRingBufferIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) { return false; }
        if (this == o) { return true; }
        if (this.getClass() != o.getClass()) {return false;}

        ArrayRingBuffer<T> otherrb = (ArrayRingBuffer<T>) o;
        if (first != otherrb.first) { return false; }
        if (last != otherrb.last) { return false; }
        if (fillCount != otherrb.fillCount) { return false; }
        if (this.capacity() != otherrb.capacity()) { return false; }
        Iterator<T> rbIterator = this.iterator();
        Iterator<T> otherrbIterator = otherrb.iterator();
        while(rbIterator.hasNext()) {
            if (rbIterator.next() != otherrbIterator.next()) {
                return false;
            }
        }
        return true;
    }

}

