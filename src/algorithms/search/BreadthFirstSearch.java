package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    protected Queue<AState> q;


    public BreadthFirstSearch() {
        super("BFS");
        this.q =  new LinkedList<>();
        this.numberOfNodesEvaluated=0;
    }

    public BreadthFirstSearch(String name, Queue<AState> q,int numberOfNodesEvaluated ) {
        super(name);
        this.q = q;
        this.numberOfNodesEvaluated=0;
    }

    @Override
    //** get ISearchable and then init the given domain.
    // creating while loop until the queue is empty or we find the goal state by getting
    // the closet neighbors around the start goal and continue to other neighbor state */
    public Solution solve(ISearchable domain) {
        domain.getAllPossibleStates(null);//init the domain
        AState start = domain.getStartState();
        AState node = start;
        AState back = start;
        setVisited(node, null);
        q.add(node);
        Solution sol;
        while (!q.isEmpty()) {
            node = q.peek();//get the next in the q
            if (node.equals(domain.getGoalState())) {
                setVisited(node, back);//////???this is the father?
                this.numberOfNodesEvaluated++;
                sol = new Solution(node);
                return sol;
            }
            back = q.remove();
            ArrayList<AState> neighbors = domain.getAllPossibleStates(back);
            int size = neighbors.size();
            for (int i = 0; i < size; i++) {//add every neighbor to the q that not visited
                AState curr = neighbors.get(i);
                if (isVisited(curr, domain) == false) {//if not visited
                    setVisited(curr, node);
                    if (curr.equals(domain.getGoalState())) {
                        setVisited(curr, node);
                        this.numberOfNodesEvaluated++;
                        sol = new Solution(curr);
                        return sol;
                    }
                    q.add(neighbors.get(i));

                }
            }
            this.numberOfNodesEvaluated++;
        }
        sol = new Solution(node);
        return sol;
    }
}
