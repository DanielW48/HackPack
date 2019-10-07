class FordFulkerson {
	int n, s, t;
	int[][] cap;
	boolean[] seen;
	FordFulkerson(int nn){
		n = nn + 2;
		s = n - 2;
		t = n - 1;
		cap = new int[n][n];
	}
	void addEdge(int u, int v, int c) {
		cap[u][v] = c;
	}
	int ff() {
		int out = 0;
		while(true) {
			seen = new boolean[n];
			int add = dfs(s, Integer.MAX_VALUE);
			if(add == 0) break;
			out += add;
		}
		return out;
	}
	int dfs(int idx, int min) {
		seen[idx] = true;
		if(idx == t) return min;
		for(int i = 0; i < n; ++i) {
			if(!seen[i] && cap[idx][i] > 0) {
				int a = dfs(i, Math.min(min, cap[idx][i]));
				if(a > 0){
					cap[idx][i] -= a;
					cap[i][idx] += a;
					return a;
				}
			}
		}
		return 0;
	}
}
