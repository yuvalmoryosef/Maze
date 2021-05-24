package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator {
    public static int counter;

    @Override
    //*generate- init maze by given row and col, if they under 3 - changed to 3./*
    public Maze generate(int row, int col) {
        if(row<3)
            row =3;
        if(col<3)
            col=3;
        Maze maze = new Maze(row, col, new Position(0, 0), new Position(0, 0));
        maze = initMaze(row, col, maze);//build  walls
        primSol(row, col, maze);
        maze = updateStartAndEndPos(maze);
        return maze;
    }

    /**init the maze with walls*/
    private Maze initMaze(int row, int col, Maze maze) {

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                maze.updateMaze1(i, j);
            }
        }
        return maze;
    }

    /**primSol - get row, col and maze.
     * get random index in the posArr.
     *change the currPos to 0.
     *change the connection to random neighbor to 0.
     * get all the legal neighbors of this position.
     * run on all the neighbors of this position and
     * add the neighbor to the posArr*/
    private Maze primSol(int row, int col, Maze maze) {

        ArrayList<Position> posArr = new ArrayList<Position>();

        //get random position in the maze
        int randX = getRandomInRange(row);
        int randY = getRandomInRange(col);
        posArr.add(new Position(randX, randY));

        boolean isFirst = true;

        while (!posArr.isEmpty()) {

            int randIndex = getRandomInRange(posArr.size());//get random index in the posArr
            Position currPos = posArr.get(randIndex);
            maze.updateMaze0(currPos.getRowIndex(), currPos.getColumnIndex());//change the currPos to 0

            if (isFirst == false) {
                changeRandomNeighbor(currPos, maze);//change the connection to random neighbor to 0
            } else
                isFirst = false;

            Position[] neighbors = getNeighbors(currPos, maze);//get all the legal neighbors of this position
            posArr.remove(randIndex);//remove the position in this index
            for (int i = 0; i < 4; i++) {//run on all the neighbors of this position
                if (neighbors[i] != null) {
                    posArr.add(neighbors[i]);//add the neighbor to the posArr
                }
            }
        }

        return maze;
    }

    /**retutn number between 0 to range*/
    private int getRandomInRange(int range) {
        Random rn = new Random();
        int i = rn.nextInt(range);
        return i;
    }

    /**return neighbors in type position, akk position around the given position that contains 1 as value and in the maze range*/
    private Position[] getNeighbors(Position pos, Maze maze) { //run on all the neighbors of this position
        Position neighbors[] = new Position[4];
        for (int i = 0; i < 4; i++) {
            neighbors[i] = null;
        }

        int posRow = pos.getRowIndex();
        int posCol = pos.getColumnIndex();

        //up:
        if ((posRow - 2) >= 0) {//if the up neighbor is legal
            if ((maze.getValue(posRow - 2, posCol) == 1)) {//if this position is wall
                maze.updateMazeNum(posRow - 2, posCol, -1);//change this position to -1
                Position up = new Position(posRow - 2, posCol);//insert this position to the array
                neighbors[0] = up;
            }
        }
        //down:
        if ((posRow + 2) < maze.getrowSize()) {//if the down neighbor is legal
            if ((maze.getValue(posRow + 2, posCol) == 1)) {//if this position is wall
                maze.updateMazeNum(posRow + 2, posCol, -1);//change this position to -1
                Position down = new Position(posRow + 2, posCol);//insert this position to the array
                neighbors[1] = down;
            }
        }
        //left:
        if ((posCol - 2) >= 0) {//if the left neighbor is legal
            if ((maze.getValue(posRow, posCol - 2) == 1)) {//if this position is wall
                maze.updateMazeNum(posRow, posCol - 2, -1);//change this position to -1
                Position left = new Position(posRow, posCol - 2);//insert this position to the array
                neighbors[2] = left;
            }
        }
        //right:
        if ((posCol + 2) < maze.getcolSize()) {//if the right neighbor is legal
            if ((maze.getValue(posRow, posCol + 2) == 1)) {//if this position is wall
                maze.updateMazeNum(posRow, posCol + 2, -1);//change this position to -1
                Position right = new Position(posRow, posCol + 2);//insert this position to the array
                neighbors[3] = right;
            }
        }

        return neighbors;
    }

    /**change the connection to random neighbor to 0*/
    private void changeRandomNeighbor(Position pos, Maze maze) {

        int posRow = pos.getRowIndex();
        int posCol = pos.getColumnIndex();

        Position neighbors[] = new Position[4];
        for (int i = 0; i < 4; i++) {
            neighbors[i] = null;
        }

        //up:
        if ((posRow - 2) >= 0) {//if the up neighbor is legal
            if ((maze.getValue(posRow - 2, posCol) == 0)) {//if this position is open
                Position up = new Position(posRow - 2, posCol);
                neighbors[0] = up;
            }
        }
        //down:
        if ((posRow + 2) < maze.getrowSize()) {//if the down neighbor is legal
            if ((maze.getValue(posRow + 2, posCol) == 0)) {//if this position is open
                Position down = new Position(posRow + 2, posCol);
                neighbors[1] = down;
            }
        }
        //left:
        if ((posCol - 2) >= 0) {//if the left neighbor is legal
            if ((maze.getValue(posRow, posCol - 2) == 0)) {//if this position is open
                Position left = new Position(posRow, posCol - 2);
                neighbors[2] = left;
            }
        }
        //right:
        if ((posCol + 2) < maze.getcolSize()) {//if the right neighbor is legal
            if ((maze.getValue(posRow, posCol + 2) == 0)) {//if this position is open
                Position right = new Position(posRow, posCol + 2);
                neighbors[3] = right;
            }
        }
        boolean isChanged = false;
        while (isChanged == false) {
            int randIndex = getRandomInRange(4);//choose one of the legal neighbors
            if (neighbors[randIndex] != null) {

                //up:
                if (randIndex == 0)
                    maze.updateMaze0(posRow - 1, posCol);//change the connection to this neighbor to 0
                //down:
                if (randIndex == 1)
                    maze.updateMaze0(posRow + 1, posCol);//change the connection to this neighbor to 0
                //left:
                if (randIndex == 2)
                    maze.updateMaze0(posRow, posCol - 1);//change the connection to this neighbor to 0
                //right:
                if (randIndex == 3)
                    maze.updateMaze0(posRow, posCol + 1);//change the connection to this neighbor to 0

                isChanged = true;

            }
        }
    }

    /**update start position and end position by find the places in the four indexes around (0,0) and (rowSize,colSize)*/
    private Maze updateStartAndEndPos(Maze maze) {
        //start:
        int min = 2;
        Position s = new Position(0, 0);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (maze.getValue(i, j) == 0) {
                    if ((min >= (i + j))) {
                        min = (i + j);
                        s = new Position(i, j);

                    }
                }
            }
        }
        maze.setStartPosition(s);
        //end:
        int row = maze.getrowSize();
        int col = maze.getcolSize();
        int max = row - 2 + col - 2;
        Position e = new Position(row - 1, col - 1);
        for (int i = row - 2; i < row; i++) {
            for (int j = col - 2; j < col; j++) {
                if (maze.getValue(i, j) == 0) {
                    if ((max <= (i + j))) {
                        max = (i + j);
                        e = new Position(i, j);
                    }
                }
            }
        }
        maze.setGoalPosition(e);
        return maze;
    }


}