package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int N;
    private int[][] grid;
    private int size;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf2;
    private int[] rowDirections = new int[] {0, 0, -1, 1};
    private int[] colDirections = new int[] {-1, 1, 0, 0};

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.N = N;
        this.grid = new int[N][N];
        this.size = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.grid[i][j] = 0;
            }
        }
        this.uf = new WeightedQuickUnionUF(N*N + 2);
        this.uf2 = new WeightedQuickUnionUF(N*N + 1);
    }

    public void checkBoundary(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    public int grid2ufid(int row, int col){
        int ans = row*N + col + 1;
        return ans;
    }

    public WeightedQuickUnionUF unionAround(int row, int col, WeightedQuickUnionUF uf){

        int rowNeighbor;
        int colNeighbor;
        for (int i = 0; i < 4; i ++){
            rowNeighbor = row + rowDirections[i];
            colNeighbor = col + colDirections[i];
            if (rowNeighbor >= 0 && rowNeighbor <  N && colNeighbor >= 0 && colNeighbor < N && grid[rowNeighbor][colNeighbor] == 1){
                uf.union(grid2ufid(row, col), grid2ufid(rowNeighbor,colNeighbor));
                //System.out.println("Around" + grid2ufid(row, col) + grid2ufid(rowNeighbor,colNeighbor));
            }
        }
        return uf;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkBoundary(row, col);
        grid[row][col] = 1;
        uf = unionAround(row, col, uf);
        uf2 = unionAround(row, col, uf2);
        if (row == 0) {
            uf.union(grid2ufid(row, col), 0);
            uf2.union(grid2ufid(row, col), 0);
            //System.out.println("to Ceil" + grid2ufid(i,j));
        }
        if (row == N - 1) {
            uf.union(grid2ufid(row, col), N*N + 1);
        }
        size += 1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkBoundary(row, col);
        if (grid[row][col] == 1) {
            return true;
        } else {
            return false;
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)  {
        checkBoundary(row, col);
        if (uf2.connected(grid2ufid(row,col), 0)) {
            return true;
        } else {
            return false;
        }
    }

    // number of open sites
    public int numberOfOpenSites() {
        return size;
    }

    // does the system percolate?
    public boolean percolates()  {
        if (uf.connected(0, N*N + 1)) {
            return true;
        } else {
            return false;
        }
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        System.out.println("Open (2,2)");
        p.open(2,2);
        System.out.println("Validate (2,2) is open: " + p.isOpen(2,2));
        System.out.println("Validate (2,3) is not open: " + p.isOpen(2,3));
        System.out.println("Validate (2,2) is not full: " + p.isFull(2,3));
        System.out.println("Validate Not percolate: " + p.percolates());
        System.out.println("Open (2,3)");
        p.open(2,3);
        System.out.println("Open (3,3)");
        p.open(3,3);
        System.out.println("Open (4,3)");
        p.open(4,3);
        System.out.println("Open (0,2)");
        p.open(0,2);
        System.out.println("Validate (4,3) is open: " + p.isOpen(4,3));
        System.out.println("Validate (4,3) is not full: " + p.isFull(4,3));
        System.out.println("Validate Not percolate: " + p.percolates());
        System.out.println("Open (1,2)");
        p.open(1,2);
        System.out.println("Validate (4,3) is full: " + p.isFull(4,3));
        System.out.println("Validate percolate: " + p.percolates());
    }
}
