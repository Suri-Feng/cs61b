package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        arb.enqueue(5);
        assertEquals(10, arb.capacity());
        assertEquals(5, arb.peek());
        arb.dequeue();
        assertTrue(arb.isEmpty());
    }
}
