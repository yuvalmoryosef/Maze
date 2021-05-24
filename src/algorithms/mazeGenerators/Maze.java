package algorithms.mazeGenerators;

import java.io.Serializable;

/**
 *
 */
public class Maze implements Serializable {
    private int rowSize;
    private int colSize;
    private int[][] maze;
    private Position startPosition;
    private Position GoalPosition;


    public Maze(int rowSize, int colSize, Position startPosition, Position goalPosition) {
        this.rowSize = rowSize;
        this.colSize = colSize;
        this.maze = new int[rowSize][colSize];
        this.startPosition = startPosition;
        this.GoalPosition = goalPosition;
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
    public void updateMaze1(int x, int y) {
        this.maze[x][y] = 1;
    }

    //updateMaze - change location in (x,y) to 0.
    public void updateMaze0(int x, int y) {
        this.maze[x][y] = 0;
    }

    //updateMaze - change location in (x,y) to number.
    public void updateMazeNum(int x, int y, int num) {
        this.maze[x][y] = num;
    }

    public void print() {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if ((i == startPosition.getRowIndex() && j == startPosition.getColumnIndex())) {
                    System.out.print("S");
                } else if (i == GoalPosition.getRowIndex() && j == GoalPosition.getColumnIndex()) {
                    System.out.print("E");
                } else
                    System.out.print(maze[i][j]);
            }

            System.out.println();
        }

    }


    //part B:

    //return new location in b
    public int enterToByte(byte[] b, int locationInB, int counter) {

        if (counter <= 255)
            b[locationInB + 1] = (byte) counter;
        else {
            int multiply = counter / 255;//the full
            b[locationInB] = (byte) multiply;
            b[locationInB + 1] = (byte) (counter - multiply * 255);//the rest

        }
        return locationInB + 2;
    }

    public byte[] toByteArray() {
        //index 0-*: rowSize
        //index *+1-*: colSize
        //index *+1-*: startPosition row
        //index *+1-*: startPosition col
        //index *+1-*: goalPosition row
        //index *+1-*: goalPosition col
        //indext *+1-*-end: maza, start with count of 0, and then 1
        byte[] b = new byte[12 + this.rowSize * this.colSize];
        int locationInB = 0;
        locationInB = enterToByte(b, locationInB, this.rowSize);
        locationInB = enterToByte(b, locationInB, this.colSize);
        locationInB = enterToByte(b, locationInB, this.startPosition.getRowIndex());
        locationInB = enterToByte(b, locationInB, this.startPosition.getColumnIndex());
        locationInB = enterToByte(b, locationInB, this.GoalPosition.getRowIndex());
        locationInB = enterToByte(b, locationInB, this.GoalPosition.getColumnIndex());

        int counter = 0;
        boolean isCountZero = true;
        for (int i = 0; i < this.rowSize; i++) {
            for (int j = 0; j < this.colSize; j++) {
                b[locationInB] = (byte) this.maze[i][j];
                locationInB++;
            }
        }
        return b;
    }


    public Maze(byte[] b) {
        int locationInB = 0;
        this.rowSize = convertByteToInt(b, locationInB);
        locationInB += 2;
        this.colSize = convertByteToInt(b, locationInB);
            this.maze = new int[rowSize][colSize];
        locationInB += 2;
        int x = convertByteToInt(b, locationInB);
        locationInB += 2;
        int y = convertByteToInt(b, locationInB);
        this.setStartPosition(new Position(x,y));
        locationInB += 2;
        x = convertByteToInt(b, locationInB);
        locationInB += 2;
        y = convertByteToInt(b, locationInB);
        this.setGoalPosition(new Position(x,y));
        locationInB += 2;
        for (int i = 0; i < this.rowSize; i++) {
            for (int j = 0; j < this.colSize; j++) {
                this.maze[i][j] = (b[locationInB] & 0xff);
                locationInB++;
            }
        }
        int i=9;
    }

    public int convertByteToInt(byte[] b, int locationInB) {
        int i= (b[locationInB]& 0xff) * 255 + (b[locationInB + 1]& 0xff);
        return i;
    }
}