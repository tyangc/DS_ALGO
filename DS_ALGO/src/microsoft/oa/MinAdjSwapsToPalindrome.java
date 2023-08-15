package microsoft.oa;

public class MinAdjSwapsToPalindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(minSwap("mamad"));
		//System.out.println(minSwap(""));
		System.out.println(minSwap("dfdas"));
		System.out.println(minSwap("aabb"));
		System.out.println(minSwap("ntiin"));
		System.out.println(minSwap("baamm"));
	}
	
	public static int minSwap(String s) {
		
		if (s==null || s.length()<2) return 0;
		char[] chars = s.toCharArray();
		
		if (!isShuffledPalindrome(chars)) {
			return -1;
		}
		
		int n = chars.length;
		int left = 0, right = n-1;
		int cnt = 0;
		while(left < right) {
			
			if (chars[left] != chars[right]) {
				int p1=left, p2 = right;
				
				while(p1<=p2 && chars[p1]!=chars[p2]) {
					p2--;
				}
				
				if (p1==p2) {
					cnt += swap(chars, p1, p1+1);
				} else {
					cnt += swap(chars, p2, right);
					left++;
					right--;
				}
				
				//System.out.println("cnt:" + cnt);
				
			} else {
				left++;
				right--;
			}
		}
		
		return cnt;
		
	}
	
	private static int swap(char[] chars, int s, int t) {
		//System.out.println(s + "|" + t);
		
		int left = s, right = t;
		int cnt = 0;
		while(left<right) {
			char tmp = chars[left];
			chars[left] = chars[left+1];
			chars[left+1] = tmp;
			left++;
			cnt++;
			
		}
		
		return cnt;
	}

	private static boolean isShuffledPalindrome(char[] chars) {
		
		int[] cnt = new int[26];
		
		for (char c : chars) {
			
			cnt[c-'a']++;
			
		}
		
		int odd = 0;
		for (int i : cnt) {
			if (i%2 == 1) odd++;
		}
		
		return odd<=1;
	}
}
