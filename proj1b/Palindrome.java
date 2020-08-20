
public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        String str = word;
        Deque<Character> d = new ArrayDeque<>();
        for(int i=0; i<=str.length()-1; i++) {
            d.addLast(str.charAt(i));
        }
        return d;
    }

    public boolean isPalindromeHelper(Deque<Character> d){
        if (d.size() == 0 || d.size() == 1){
            return true;
        }
        char first = d.removeFirst();
        char last = d.removeLast();
        if (first != last){
            return false;
        }
        return isPalindromeHelper(d);
    }

    public boolean isPalindrome(String word){
        Deque<Character> d = wordToDeque(word);
        return isPalindromeHelper(d);
    }

    public boolean isPalindromeHelper2(Deque<Character> d, CharacterComparator cc){
        if (d.size() == 0 || d.size() == 1){
            return true;
        }
        char first = d.removeFirst();
        char last = d.removeLast();
        if (!cc.equalChars(first, last)){
            return false;
        }
        return isPalindromeHelper2(d, cc);
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> d = wordToDeque(word);
        return isPalindromeHelper2(d, cc);
    }

    public static void main(String[] args){
        String s2 = "flake";
        Boolean b;
        CharacterComparator offByOne = new OffByOne();
        Palindrome palindrome = new Palindrome();
        b = palindrome.isPalindrome(s2, offByOne);
    }
}
