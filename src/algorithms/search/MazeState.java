package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{
    private Position position;


    public MazeState(int cost, AState cameFrom,Position position) {
        super(cost,cameFrom);
        this.position = position;
    }


    @Override
    public boolean equals(Object obj) {
    if(!(obj instanceof MazeState))
        return false;
    MazeState other = (MazeState)obj;
    return this.position.equals(other.position);
    }

    @Override
    public String toString() {
        return position.toString();
    }

    public Position getPosition() {
        return position;
    }
}
