public class OffByN implements CharacterComparator{
    int offBy;

    OffByN(int N){
        offBy = N;
    }

    @Override
    public boolean equalChars(char x, char y){
        int diff = Math.abs(x - y);

        if (diff == offBy){
            return true;
        }
        return false;
    }
}
