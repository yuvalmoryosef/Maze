package algorithms.mazeGenerators;

public class Position {
   private int row;
   private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }


    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRowIndex() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumnIndex() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return "{" + row +
                "," + col +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position))
            return false;
        Position other = (Position) obj;
        if(other.getColumnIndex()==this.getColumnIndex()&&other.getRowIndex()==this.getRowIndex())
            return true;
        return false;
    }
}
