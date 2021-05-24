package algorithms.mazeGenerators;

public interface IMazeGenerator {
    /**generate - get col and row and return a maze.*/
    public Maze generate(int row, int col);

    /**measureAlgorithmTimeMillis - get col and row and return long.*/
    public long measureAlgorithmTimeMillis(int row, int col);

}
