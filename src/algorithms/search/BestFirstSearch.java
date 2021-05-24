package algorithms.search;

import java.util.Comparator;
import java.util.PriorityQueue;


public class BestFirstSearch extends BreadthFirstSearch{

    //** Doing super to BFs */
    public BestFirstSearch() {
        super("BestFirstSearch",new PriorityQueue<AState>((AState a1, AState a2) -> Double.compare(a2.getCost(), a1.getCost())),0);
    }
}
