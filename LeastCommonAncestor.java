class LeastCommonAncestor {
	static int n;
	static int[] par, height;
	static int[][] jmp;
	public static void main(String[] args) {
		par = new int[n];
		height = new int[n];
		jmp = new int[31][n];
		
		for(int i = 0; i < n; ++i) jmp[0][i] = par[i];
		for(int j = 1; j < 31; ++j){
		    for(int i = 0; i < n; ++i){
		        if(jmp[j - 1][i] < 0) jmp[j][i] = -1;
		        else jmp[j][i] = jmp[j - 1][jmp[j - 1][i]];
		    }
		}
		
	}
	static int lca(int a, int b) {
		if(height[a] > height[b]){
		    int temp = a;
		    a = b;
		    b = temp;
		}
		
		for(int j = 30; j >= 0; --j) {
			int to = jmp[j][b];
			if(to >= 0 && height[to] >= height[a]) b = to;
		}
		
		if(a == b) return a;
		
		for(int j = 30; j >= 0; --j) {
			int toA = jmp[j][a];
			int toB = jmp[j][b];
			if(toA < 0 || toB < 0) continue;
			if(toA != toB) {
				a = toA;
				b = toB;
			}
		}
		
		return par[a];
	}
}
