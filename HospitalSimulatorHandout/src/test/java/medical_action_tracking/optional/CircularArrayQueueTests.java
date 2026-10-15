package medical_action_tracking.optional;

import medical_action_tracking.CircularArrayQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CircularArrayQueueTests {

    @Test
    void emptyQueueReturnNull(){
        CircularArrayQueue<String> queue = new CircularArrayQueue<>(3);

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertNull(queue.peekFront());
        assertNull(queue.dequeue());
        assertEquals("[]", queue.toString());
    }

    @Test
    void queueWrapsAroundAndCanBeReused(){
        CircularArrayQueue<String> queue = new CircularArrayQueue<>(3);

        queue.enqueue("Bang Chan");
        queue.enqueue("Felix");
        queue.enqueue("Han");

        assertEquals("Bang Chan", queue.dequeue());
        assertEquals("Felix", queue.dequeue());

        assertTrue(queue.enqueue("Jeongin"));
        assertTrue(queue.enqueue("Seungmin"));

        assertEquals("[Han, Jeongin, Seungmin]", queue.toString());
        assertEquals(3, queue.size());
        assertEquals("Han", queue.peekFront());

        assertEquals("Han", queue.dequeue());
        assertEquals("Jeongin", queue.dequeue());
        assertEquals("Seungmin", queue.dequeue());
        assertNull(queue.dequeue());

        assertTrue(queue.enqueue("Lee Know"));
        assertEquals("Lee Know", queue.dequeue());
        assertTrue(queue.isEmpty());
   }

   @Test
   void nullItemIsRejectedWithoutChangingContents(){
    CircularArrayQueue<String> queue = new CircularArrayQueue<>(2);
    queue.enqueue("Felix");

    assertThrows(IllegalArgumentException.class,()-> queue.enqueue(null));

    assertEquals(1, queue.size());
    assertEquals("Felix", queue.peekFront());
    assertEquals("[Felix]", queue.toString());
   }

   @Test
   void nonpositiveCapacityIsRejected(){
    assertThrows(IllegalArgumentException.class, ()-> new CircularArrayQueue<String>(0));

    assertThrows(IllegalArgumentException.class, ()-> new CircularArrayQueue<String>(-1));
   }
}