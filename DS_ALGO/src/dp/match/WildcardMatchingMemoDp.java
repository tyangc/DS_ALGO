package dp.match;

public class WildcardMatchingMemoDp {
	 public boolean isMatch(String s, String p) {
	        // write your code here
	      if (s==null || p==null) return false;

	      int m = s.length(), n = p.length();

	      return matchHelper(s, 0, p, 0, new boolean[m][n], new boolean[m][n]);
	        
	    }

	    private boolean matchHelper(String s, int sIndex, String p, int pIndex, boolean[][] visited, boolean[][] memo) {
	      int m = s.length(), n = p.length();
	      if (pIndex == n) {
	        return sIndex == m;

	      }

	      if (sIndex == m) {
	        return isAllStar(p, pIndex);
	     
	      }

	      if (visited[sIndex][pIndex]) {
	        return memo[sIndex][pIndex];
	      }

	      boolean match = false;
	      //visited[sIndex][pIndex] = true;
	      if (p.charAt(pIndex) == '*') {
	        match = matchHelper(s, sIndex, p, pIndex+1, visited, memo) || matchHelper(s, sIndex+1, p, pIndex, visited, memo);
	      } else {
	        match = matchOne(s, sIndex, p, pIndex) &&  matchHelper(s, sIndex+1, p, pIndex+1, visited, memo);
	      }

	      visited[sIndex][pIndex] = true;
	      memo[sIndex][pIndex] = match;
	      return match;
	    }

	    private boolean isAllStar(String p, int pIndex){
	      for (int i=pIndex; i<p.length(); i++) {
	        if (p.charAt(i) != '*') return false;
	      }

	      return true;
	    }
	   
	    private boolean matchOne(String s, int sIndex, String p, int pIndex) {
	      return s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '?';
	    }
}
