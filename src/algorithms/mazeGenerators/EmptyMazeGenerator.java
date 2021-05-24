package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {


    @Override
    public Maze generate(int row, int col) {
        if(row<3)
            row =3;
        if(col<3)
            col=3;
        Position s = new Position(0,0);
        Position e = new Position(row-1,col-1);
        Maze newMaze = new Maze(row, col, s, e) ;
        return newMaze;
    }
}
