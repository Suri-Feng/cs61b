public class UnionFind {

    public int[] parent;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i ++) {
            parent[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) throws Exception {
        if (parent[vertex] >= parent.length){
            throw new Exception();
        } else if (Math.abs(parent[find(vertex)]) != sizeOf(vertex)){
            throw new Exception();
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        int i = find(v1);
        int size = Math.abs(parent[i]);
        return size;
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int size1 = sizeOf(v1);
        int size2 = sizeOf(v2);
        int root1 = find(v1);
        int root2 = find(v2);
        if (root1 != root2 || (root1 == -1 && root2 == -1)) {
            if (size1 <= size2) {
                parent[root1] = root2;
                parent[root2] -= size1;
            } else {
                parent[root2] = root1;
                parent[root1] -= size2;
            }
        }
        }


    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        int r = vertex;
        while(parent(r) >= 0){
            r = parent(r);
        }
        int q = vertex;
        while(parent(q) >= 0){
            int temp = q;
            q = parent(q);
            parent[temp] = r;
        }
        return r;
    }

}
