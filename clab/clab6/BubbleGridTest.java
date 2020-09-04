import org.junit.Test;
import static org.junit.Assert.*;

public class BubbleGridTest {

    @Test
    public void testBasic() {

        int[][] grid = {{1, 0, 0, 0},
                        {1, 1, 1, 0}};
        int[][] darts = {{1, 0}};
        int[] expected = {2};

        validate(grid, darts, expected);


    }

    public void validate(int[][] grid, int[][] darts, int[] expected) {
        BubbleGrid sol = new BubbleGrid(grid);
        assertArrayEquals(expected, sol.popBubbles(darts));
    }

    @Test
    public void testAgain() {
        int[][] grid = {{1, 1, 0},
                {1, 0, 0},
                {1, 1, 0},
                {1, 1, 1}};
        int[][] darts = {{2, 2},{2, 0}};
        int[] expected = {0, 4};

        validate(grid, darts, expected);
    }

}
