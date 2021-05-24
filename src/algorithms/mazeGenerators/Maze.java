package algorithms.mazeGenerators;

/**
 *
 */
public class Maze {
    private int rowSize;
    private int colSize;
    private int [][]maze;
    private Position startPosition;
    private Position GoalPosition;


    public Maze(int rowSize, int colSize, Position startPosition, Position goalPosition) {
        this.rowSize = rowSize;
        this.colSize = colSize;
        this.maze = new int[rowSize][colSize];
        this.startPosition = startPosition;
        GoalPosition = goalPosition;
    }

    public int getcolSize() {
        return colSize;
    }

    public void setcolSize(int col) {
        this.colSize = col;
    }

    public int getrowSize() {
        return rowSize;
    }

    public void setrowSize(int row) {
        this.rowSize = row;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    public Position getGoalPosition() {
        return GoalPosition;
    }

    public void setGoalPosition(Position goalPosition) {
        GoalPosition = goalPosition;
    }



    public int getValue(int x, int y) {
        return this.maze[x][y];
    }

    //updateMaze - change location in (x,y) to 1.
    public void updateMaze1( int x, int y ){
        this.maze[x][y]=1;
    }

    //updateMaze - change location in (x,y) to 0.
    public void updateMaze0( int x, int y ){
        this.maze[x][y]=0;
    }

    //updateMaze - change location in (x,y) to number.
    public void updateMazeNum( int x, int y, int num ){
        this.maze[x][y]=num;
    }

    public void print(){
        for(int i = 0; i< rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if ((i==startPosition.getRowIndex()  &&    j==startPosition.getColumnIndex())){
                    System.out.print("S");
                }
                else if(i==GoalPosition.getRowIndex()  &&    j==GoalPosition.getColumnIndex()) {
                    System.out.print("E");
                }
                else
                    System.out.print(maze[i][j]);
                }

            System.out.println();
            }

        }

    }


