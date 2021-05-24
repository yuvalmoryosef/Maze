package algorithms.search;

public abstract class ASearchingAlgorithm  implements ISearchingAlgorithm{

    protected String name;
    protected int numberOfNodesEvaluated;

    public ASearchingAlgorithm(String name) {
        this.name = name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setNumberOfNodesEvaluated(int numberOfNodesEvaluated) {
        this.numberOfNodesEvaluated = numberOfNodesEvaluated;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return this.numberOfNodesEvaluated;
    }


    protected void setVisited(AState curr, AState cameFrom) {
        if (curr.getCameFrom() == null)
            curr.setCameFrom(cameFrom);
    }

    //*get AState and ISearchable and return true/false if the value of came from is null and is not start.
    protected boolean isVisited(AState curr, ISearchable domain) {
        if (curr.getCameFrom() == null && !curr.equals(domain.getStartState())) {
            return false;
        }
        return true;

    }
}
