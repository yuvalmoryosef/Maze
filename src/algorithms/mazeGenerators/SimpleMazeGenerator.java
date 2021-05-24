package algorithms.mazeGenerators;

public class SimpleMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int row, int col) {
        if(row<3)
            row =3;
        if(col<3)
            col=3;
        Position s = new Position(0,0);
        Position e = new Position(row-1,col-1);
        Maze newMaze = new Maze(row,col,s,e);
        double rand=0;
        //spread random walls
        for(int i=0; i<row;i++){
            for(int j=0; j<col;j++){
                rand = Math.random();
                if(rand>0.5){
                    newMaze.updateMaze1(i,j);
                }
            }
        }
        boolean getOut=false;
        int i=0;
        int j=0;
        while (getOut==false) {
            rand = Math.random();
            if (rand > 0.5) {//turn right
                j++;
                if (newMaze.getValue(i, j) == 1)//break the wall
                    newMaze.updateMaze0(i, j);
                if (j == newMaze.getcolSize() - 1) {//the end
                    e.setPosition(i, j);
                    getOut = true;
                    break;
                }
            }
            else {
                i++;
                if (newMaze.getValue(i, j) == 1)//break the wall
                    newMaze.updateMaze0(i, j);
                if (i == newMaze.getrowSize() - 1) {//the end
                    e.setPosition(i, j);
                    getOut = true;
                    break;
                }
            }
        }

        return newMaze;
    }
}
