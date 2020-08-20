import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    StudentArrayDeque<Integer> student = new StudentArrayDeque();
    ArrayDequeSolution<Integer> solution = new ArrayDequeSolution();

    @Test
    public void testAddFirstRemoveFirst() {

        /** @Source StudentArrayDequeLaucher.java */
        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                student.addLast(i);
                solution.addLast(i);
            } else {
                student.addFirst(i);
                solution.addFirst(i);
            }
        }

        Integer sol;
        Integer stu;
        for (int i = 0; i < 5; i += 1) {
            sol = solution.removeFirst();
            stu = student.removeFirst();
            assertEquals("Oh noooo!\nThis is bad:\n  Broke when size is " + solution.size(), sol, stu);
        }
        for (int i = 0; i < 5; i += 1) {
            sol = solution.removeLast();
            stu = student.removeLast();
            assertEquals("Oh noooo!\nThis is bad:\n  Broke when size is " + solution.size(), sol, stu);
        }

    }

}
