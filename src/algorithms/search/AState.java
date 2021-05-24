package algorithms.search;

public abstract class AState {
    private double cost;
    private AState cameFrom;

    public AState(int cost, AState cameFrom) {
        this.cost = cost;
        this.cameFrom = cameFrom;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public AState getCameFrom() {
        return this.cameFrom;
    }


    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract String toString();

}
