package medical_action_tracking.optional;

import medical_action_tracking.LinkedQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecursiveCountTests{
    @Test 
    void recursiveCountMatchesSizeForEmptyQueue(){
        LinkedQueue<String> queue = new LinkedQueue<>();

        assertEquals(0, queue.recursiveCount());
        assertEquals(queue.size(), queue.recursiveCount());
        assertTrue(queue.isEmpty());
    }

    @Test 
    void recursiveCountMatchesSizeWithoutChangingQueue(){
        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("Bang Chan");
        queue.enqueue("Felix");
        queue.enqueue("Han");

        assertEquals(3, queue.recursiveCount());
        assertEquals(queue.size(), queue.recursiveCount());

        assertEquals("Bang Chan", queue.dequeue());
        assertEquals("Felix", queue.dequeue());
        assertEquals("Han", queue.dequeue());

        assertEquals(0, queue.recursiveCount());
        assertEquals(queue.size(), queue.recursiveCount());
    }
}