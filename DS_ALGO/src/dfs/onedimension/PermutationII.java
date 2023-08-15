package dfs.onedimension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//Permuation with duplicates
//https://www.lintcode.com/problem/10/

public class PermutationII {
	//Using DFS, time out at 78% of test cases in lintcode
	public List<String> stringPermutation2(String str) {
        // write your code here
        //Set<String> res = new HashSet<>();
        List<String> res = new ArrayList<>();
        if (str == null) return res;//new ArrayList(res);
        int n = str.length();
        char[] arr = str.toCharArray();
        Arrays.sort(arr);  //must
        System.out.println(Arrays.toString(arr));
        dfs(arr, new boolean[n], new StringBuilder(), res);
        //return new ArrayList(res);
        return res;
    }

    private void dfs(char[] arr, boolean[] visited, StringBuilder sb, List<String> res ) {
        if (sb.length() == arr.length) {
            res.add(sb.toString());
            return; //must
        }

        //char[] arr = str.toCharArray();

        for (int i=0; i<arr.length; i++) {
            if (visited[i]) continue;
            //int k = sb.length()-1;
            if (i>0 && !visited[i-1] && arr[i] == arr[i-1]) continue; //**** key point
            sb.append(arr[i]);   
            visited[i] = true;   
            dfs(arr, visited, sb, res);
            visited[i] = false;
            sb.deleteCharAt(sb.length()-1);  
        }

    }
    
    //DFS pass 100% test cases in lintcode:
    public List<String> stringPermutation2DFS(String str) {
        // write your code here
        List<String> res = new ArrayList<>();
        if (str.length()==0 || str==null) {
            res.add("");
            return res;
        }

        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        getList(chars, "", new boolean[str.length()], res);
        return res;
    }

    private void getList(char[] chars, String cur, boolean[] visited, List<String> res) {
        if (cur.length()==chars.length) {
            res.add(cur);
            return;
        }

        for (int i=0; i<chars.length; i++) {
            if (visited[i] || i>0 && chars[i]==chars[i-1] && !visited[i-1]) continue;
            
            visited[i] = true;
            String nxt = cur + chars[i];
            getList(chars, nxt, visited, res);
            visited[i] = false;
            //cur = cur.substring(0, cur.length()-1);
        }

    }
    
    //Using Swap only, pass all test cases
    
    public List<String> stringPermutation2Swap(String str) {
        // write your code here
        List<String> res = new ArrayList<>();
        if (str == null) return res;

        //res.add(str);
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        res.add(new String(arr));
        String next = nextPermutation(arr);
        while( next != null) {
            res.add(next);
            next = nextPermutation(arr);
        }

        return res;
    }

    private String nextPermutation(char[] arr) {
        int index = -1;

        for (int i=arr.length-1; i>0; i--) {
            if (arr[i] > arr[i-1]) {
                index = i-1;
                break;
            }
        }

        if (index == -1) return null;

        for (int i=arr.length-1; i>=index; i--) {
            if (arr[i] > arr[index]) {
                swap(arr, index, i);
                break;
            }
        }

        reverse(arr, index+1, arr.length-1);
        return new String(arr);
    }

    private void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private void reverse(char[] arr, int i, int j) {
        while(i<j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }
}
