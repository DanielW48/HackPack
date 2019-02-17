class LongestCommonSubsequence {
	public static void main (String[] args){
	    char[] a, b; //the two strings you scan in
	    int n = a.length, m = b.length;
	    int[][] dp = new int[n + 1][m + 1];
	    
	    for(int i = n - 1; i >= 0; --i){
	        for(int j = m - 1; j >= 0; --j){
	            if(a[i] == b[j]) dp[i][j] = 1 + dp[i + 1][j + 1];
	            else dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
	        }
	    }
	    
	    int out = dp[0][0];
	}
}
