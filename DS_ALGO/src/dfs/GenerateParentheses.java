package dfs;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

	/**
     * @param n: n pairs
     * @return: All combinations of well-formed parentheses
     *          we will sort your return value in output
     */
    public List<String> generateParenthesis(int n) {
        // write your code here
        List<String> res = new ArrayList<>();
        List<String> seq = new ArrayList<>();
        dfs(n, seq, res, 0);
        return res;
    }

    private void dfs(int nPair, List<String> sequence, List<String> result, int leftCount) {
        if ( sequence.size() == nPair*2 ) {
        //if (leftCount == nPair) { //this is wrong
            result.add(String.join("", sequence));
            return;
        }

        if (leftCount < nPair) {
            sequence.add("(");
            dfs(nPair, sequence, result, leftCount+1);
            sequence.remove(sequence.size()-1);
        }

        if (leftCount>sequence.size()-leftCount) {
            sequence.add(")");
            dfs(nPair, sequence, result, leftCount);
            sequence.remove(sequence.size()-1);
        }

    }
}
