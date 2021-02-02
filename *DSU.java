static class DSU {
	int n;
	int[] par, height;
	DSU(int nn){
		n = nn;
		par = new int[n];
		Arrays.fill(par, -1);
		height = new int[n];
	}
	int getPar(int idx) {
		return par[idx] == -1 ? idx : (par[idx] = getPar(par[idx]));
	}
	boolean union(int a, int b) {
		a = getPar(a);
		b = getPar(b);
		
		if(a == b) return false;
		
		if(height[a] == height[b]) ++height[a];
		
		if(height[a] >= height[b]) par[b] = a;
		else par[a] = b;
		
		return true;
	}
	boolean sameComp(int a, int b) {
		return getPar(a) == getPar(b);
	}
}
