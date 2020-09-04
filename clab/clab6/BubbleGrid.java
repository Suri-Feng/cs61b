import java.util.Arrays;

/**@Source: Inspired by LeetCode 803 Discussion
 * Milestone: my first leetcode hard haha
 * Two tricks:
 *
 * 1. Adding the bubbles backward:
 * Set ALL popped bubbles as 2
 * Union around (left, right, up , down) all bubbles that are 1 (final status of remained bubbles)
 * Count the size of union (number of the bubbles left)
 * Restore the popped bubbles from 2 to 1 from the last pop to the first pop
 * The gained number of bubbles in each time of restoration can represent the number of popped bubbles
 *
 * 2. Set a virtual node 0 as the parent node of all nodes in the disjoint sets:
 * Only top bubbles can connect to the virtual node 0, and all other bubbles connected with the top bubbles
 * can connect to this virtual node simultaneously;
 * With this virtual node 0, the size of the disjoint set can be easily found
 */

public class BubbleGrid {
    private int[][] grid;
    private int noRow;
    private int noCol;
    private int[] rowDirections = new int[] {0, 0, -1, 1};
    private int[] colDirections = new int[] {-1, 1, 0, 0};

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
        this.noRow = grid.length;
        this.noCol = grid[0].length;
    }

    //Set uf[0] as the virtual parent of all connected nodes
    public int grid2ufid(int row, int col){
        int ans = row*noCol + col + 1;
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 1, 0},
                {1, 0, 0},
                {1, 1, 0},
                {1, 1, 1}};
        int[][] darts = {{2, 2},{2, 0}};
        BubbleGrid bg = new BubbleGrid(grid);
        int[] ans = bg.popBubbles(darts);
        //System.out.println(Arrays.toString(ans));
    }

    public UnionFind unionAround(int row, int col, UnionFind uf){

        int rowNeighbor;
        int colNeighbor;
        for (int i = 0; i < 4; i ++){
            rowNeighbor = row + rowDirections[i];
            colNeighbor = col + colDirections[i];
            if (rowNeighbor >= 0 && rowNeighbor < noRow && colNeighbor >= 0 && colNeighbor < noCol && grid[rowNeighbor][colNeighbor] == 1){
                uf.union(grid2ufid(row, col), grid2ufid(rowNeighbor,colNeighbor));
                //System.out.println("Around" + grid2ufid(row, col) + grid2ufid(rowNeighbor,colNeighbor));
            }
        }
        return uf;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        int times = darts.length;
        int[] ans = new int[times];
        UnionFind uf = new UnionFind(noCol*noRow + 1);

        //Set the grid to the final status where all locations of popped bubbles are 2
        int row;
        int col;
        for (int i = 0; i < times; i++) {
            row = darts[i][0];
            col = darts[i][1];
            if (grid[row][col] == 1) {
                grid[row][col] = 2;
            }
        }

        //Last status fo the union find instance
        for (int i = 0; i < noRow; i++) {
            for (int j = 0; j < noCol; j++) {
                if (grid[i][j] == 1) {
                    uf = unionAround(i, j, uf);
                    if (i == 0) {
                        uf.union(grid2ufid(i,j), 0);
                        //System.out.println("to Ceil" + grid2ufid(i,j));
                    }
                }
            }
        }

        int sizeNow;
        int sizePrevious;
        int numFall;
        for (int i = times - 1; i >= 0; i--) {
            row = darts[i][0];
            col = darts[i][1];
            if (grid[row][col] == 2) {
                //System.out.println(Arrays.toString(uf.parent));
                sizeNow = uf.sizeOf(0);
                grid[row][col] = 1;
                uf = unionAround(row, col, uf);
                //System.out.println(Arrays.toString(uf.parent));
                sizePrevious = uf.sizeOf(0);
                numFall = sizePrevious - sizeNow - 1;// Exclude the popped one
            } else {
                numFall = 0;
            }
            ans[i] = numFall;
        }

        return ans;
    }
}
