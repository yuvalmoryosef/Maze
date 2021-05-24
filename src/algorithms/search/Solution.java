package algorithms.search;

import java.util.ArrayList;

public class Solution {

    AState goalState;

    public Solution(AState goalState) {
        this.goalState = goalState;
    }

    public ArrayList<AState> getSolutionPath(){
        ArrayList<AState> path=new ArrayList<AState>();
        AState node=goalState;
        path.add(node);
        while (node.getCameFrom()!=null){
            node=node.getCameFrom();
            path.add(node);
        }
        goalState.setCameFrom(null);

        return reverseArrayList(path);
    }
    private ArrayList<AState> reverseArrayList(ArrayList<AState> alist){
        // Arraylist for storing reversed elements
        ArrayList<AState> revArrayList = new ArrayList<AState>();
        for (int i = alist.size() - 1; i >= 0; i--){
            // Append the elements in reverse order
            revArrayList.add(alist.get(i));
        }
        // Return the reversed arraylist
        return revArrayList;
    }

}
