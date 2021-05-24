package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {

    protected Stack<AState> stack;

    public DepthFirstSearch() {
        super("DFS");
        this.stack=new Stack<AState>();
        this.numberOfNodesEvaluated=0;
    }


    @Override
    //** get ISearchable and then init the given domain.
    // creating while loop until the stack is empty or we find the goal state by getting
    // the closet neighbors around the start goal and continue to other neighbor state */
    public Solution solve(ISearchable domain) {
        domain.getAllPossibleStates(null);//init the domain
        AState start = domain.getStartState();
        AState node = start;
        AState back = start;
        setVisited(node, null);
        Solution sol;
        stack.push(node);
        while (!stack.isEmpty()) {
            node = stack.peek();//get the next in the stack
            if (node.equals(domain.getGoalState())) {//we finish
                setVisited(node, back);
                this.numberOfNodesEvaluated++;
                sol = new Solution(node);
                return sol;
            }
            back = stack.pop();
            ArrayList<AState> neighbors = domain.getAllPossibleStates(back);//get all neighbors of the top in stack
            while (!neighbors.isEmpty()) {
                AState curr = neighbors.remove(0);
                if (isVisited(curr, domain) == false) {//if not visited
                    setVisited(curr,node);
                    this.numberOfNodesEvaluated++;
                    stack.push(curr);
                    if (curr.equals(domain.getGoalState())) {
                        setVisited(curr, node);
                        this.numberOfNodesEvaluated++;
                        sol = new Solution(curr);
                        return sol;
                    }
                }
            }
        }
        sol = new Solution(domain.getGoalState());
        return sol;
    }
}
