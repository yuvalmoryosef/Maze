package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {

    Maze maze;
    MazeState[][] matrix;

    //*init maze*/
    public SearchableMaze(Maze maze) {
        this.maze = maze;
        this.matrix = initMatrix();
    }

    //*create matrix that contains states*/
    private MazeState[][] initMatrix() {
        MazeState[][] matrix = new MazeState[maze.getrowSize()][maze.getcolSize()];
        for (int i = 0; i < maze.getrowSize(); i++) {
            for (int j = 0; j < maze.getcolSize(); j++) {
                matrix[i][j] = new MazeState(1, null, new Position(i, j));
            }
        }
        return matrix;
    }

    @Override
    public AState getStartState() {
        MazeState mazeState = new MazeState(1, null, maze.getStartPosition());
        return mazeState;
    }

    @Override
    public AState getGoalState() {
        MazeState mazeState = new MazeState(-1, null, maze.getGoalPosition());
        return mazeState;
    }

    @Override
    //*get state and return arrayList that contains all neighbors that their value is zero*/
    public ArrayList<AState> getAllPossibleStates(AState state) {
        if (state == null) {
            matrix = initMatrix();
            return null;
        } else {
            ArrayList<AState> neighbors = new ArrayList<AState>();
            Position currPos = ((MazeState) state).getPosition();
            int posRow = currPos.getRowIndex();
            int posCol = currPos.getColumnIndex();
            boolean isUpRight = false;//right up/up right
            boolean isUpLeft = false;//left up/up left
            boolean isDownRight = false;//left down/down left
            boolean isDownLeft = false;//left down/up down
            double stateCost = state.getCost();
            //up:
            if ((posRow - 1) >= 0) {//if the up neighbor is legal
                if ((maze.getValue(posRow - 1, posCol) == 0)) {//if this position is open
                    MazeState up = matrix[posRow - 1][posCol];
                    matrix[posRow - 1][posCol].setCost(1);
                    neighbors.add(up);
                    //right:
                    if ((posCol + 1) < maze.getcolSize()) {
                        if ((maze.getValue(posRow - 1, posCol + 1) == 0)) {
                            MazeState upR = matrix[posRow - 1][posCol + 1];
                            matrix[posRow - 1][posCol+1].setCost(1.5);
                            neighbors.add(upR);
                            isUpRight = true;
                        }
                    }
                    //left:
                    if ((posCol - 1) >= 0) {
                        if ((maze.getValue(posRow - 1, posCol - 1) == 0)) {
                            MazeState upL = matrix[posRow - 1][posCol - 1];
                            matrix[posRow - 1][posCol-1].setCost(1.5);
                            neighbors.add(upL);
                            isUpLeft = true;
                        }
                    }
                }
            }
            //down:
            if ((posRow + 1) < maze.getrowSize()) {//if the down neighbor is legal
                if ((maze.getValue(posRow + 1, posCol) == 0)) {//if this position is open
                    MazeState down = matrix[posRow + 1][posCol];
                    matrix[posRow + 1][posCol].setCost(1);
                    neighbors.add(down);
                    //right:
                    if ((posCol + 1) < maze.getcolSize()) {
                        if ((maze.getValue(posRow + 1, posCol + 1) == 0)) {
                            MazeState downR = matrix[posRow + 1][posCol + 1];
                            matrix[posRow + 1][posCol+1].setCost(1.5);
                            neighbors.add(downR);
                            isDownRight = true;
                        }
                    }
                    //left:
                    if ((posCol - 1) >= 0) {
                        if ((maze.getValue(posRow + 1, posCol - 1) == 0)) {
                            MazeState downL = matrix[posRow + 1][posCol - 1];
                            matrix[posRow + 1][posCol-1].setCost(1.5);
                            neighbors.add(downL);
                            isDownLeft = true;
                        }
                    }
                }
            }
            //left:
            if ((posCol - 1) >= 0) {//if the left neighbor is legal
                if ((maze.getValue(posRow, posCol - 1) == 0)) {//if this position is open
                    MazeState left = matrix[posRow][posCol - 1];
                    matrix[posRow][posCol - 1].setCost(1);
                    neighbors.add(left);
                    //up
                    if (isUpLeft == false) {
                        if ((posRow - 1) >= 0) {
                            if ((maze.getValue(posRow - 1, posCol - 1) == 0)) {
                                MazeState leftUp = matrix[posRow - 1][posCol - 1];
                                matrix[posRow-1][posCol - 1].setCost(1.5);
                                neighbors.add(leftUp);
                            }
                        }
                    }
                    //down
                    if (isDownLeft == false) {
                        if ((posRow + 1) < maze.getrowSize()) {
                            if ((maze.getValue(posRow + 1, posCol - 1) == 0)) {
                                MazeState leftD = matrix[posRow + 1][posCol - 1];
                                matrix[posRow+1][posCol - 1].setCost(1.5);
                                neighbors.add(leftD);
                            }
                        }
                    }
                }
            }
            //right:
            if ((posCol + 1) < maze.getcolSize()) {//if the right neighbor is legal
                if ((maze.getValue(posRow, posCol + 1) == 0)) {//if this position is open
                    MazeState right = matrix[posRow][posCol + 1];
                    matrix[posRow][posCol + 1].setCost(1);
                    neighbors.add(right);
                    //up
                    if (isUpRight == false) {
                        if ((posRow - 1) >= 0) {
                            if ((maze.getValue(posRow - 1, posCol + 1) == 0)) {
                                MazeState rightUp = matrix[posRow - 1][posCol + 1];
                                matrix[posRow-1][posCol + 1].setCost(1.5);
                                neighbors.add(rightUp);
                            }
                        }
                    }
                    //down
                    if (isDownRight == false) {
                        if ((posRow + 1) < maze.getrowSize()) {
                            if ((maze.getValue(posRow + 1, posCol + 1) == 0)) {
                                MazeState rightD = matrix[posRow + 1][posCol + 1];
                                matrix[posRow+1][posCol + 1].setCost(1.5);
                                neighbors.add(rightD);
                            }
                        }
                    }
                }
            }
            return neighbors;

        }
    }

}





