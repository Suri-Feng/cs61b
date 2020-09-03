import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {

    @Test
    public void test(){
        UnionFind uf = new UnionFind(10);
        uf.union(0,1);
        uf.union(1,2);
        uf.union(2,3);
        uf.union(3,4);
        uf.union(5,6);
        uf.union(7,8);
        uf.union(6,7);
        assertTrue(uf.connected(1,3));
        assertTrue(uf.connected(5,8));
        assertFalse(uf.connected(1,8));
        assertFalse(uf.connected(1,9));
        assertFalse(uf.connected(8,9));
        assertEquals(uf.sizeOf(3), 5);
        uf.union(1,8);
        assertEquals(uf.sizeOf(3), 9);
        assertTrue(uf.connected(1,8));
        uf.union(2,9);
        assertTrue(uf.connected(1,9));
        assertTrue(uf.connected(8,9));
    }

}
