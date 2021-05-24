package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {
    /**generate - get col and row and return a maze.*/
    public abstract Maze generate(int row, int col);

    /**measureAlgorithmTimeMillis - get col and row and return long.*/
    public long measureAlgorithmTimeMillis(int row, int col){
        long timeBefore = System.currentTimeMillis();
        generate(row, col);
        long timeAfter = System.currentTimeMillis();
        return (timeAfter-timeBefore);
    }
}
