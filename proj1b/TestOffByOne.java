import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testOffByOne() {
        assertTrue(offByOne.equalChars('a', 'b'));  // true
        assertTrue(offByOne.equalChars('l', 'k'));  // true
        assertTrue(offByOne.equalChars('&', '%'));
        assertFalse(offByOne.equalChars('a', 'e'));  // false
        assertFalse(offByOne.equalChars('z', 'a'));  // false
        assertFalse(offByOne.equalChars('a', 'a'));  // false
        assertFalse(offByOne.equalChars('a', 'B'));  // false
    }
}