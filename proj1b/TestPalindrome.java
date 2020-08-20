import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome(){
        String s1 = "racecar";
        String s2 = "noon";
        String s3 = "haha";
        String s4 = "s";
        String s5 = "";
        assertTrue(palindrome.isPalindrome(s1));
        assertTrue(palindrome.isPalindrome(s2));
        assertFalse(palindrome.isPalindrome(s3));
        assertTrue(palindrome.isPalindrome(s4));
        assertTrue(palindrome.isPalindrome(s5));
    }

    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testIsPalindrome2(){
        String s1 = "ab";
        String s2 = "flake";
        String s3 = "ssc";
        assertTrue(palindrome.isPalindrome(s1, offByOne));
        assertTrue(palindrome.isPalindrome(s2, offByOne));
        assertFalse(palindrome.isPalindrome(s3, offByOne));
    }
}