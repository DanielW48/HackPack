static class DSUSmallToLarge {
	int n;
	int[] par;
	ArrayDeque<Integer>[] comps;
	DSUSmallToLarge(int nn){
		n = nn;
		par = new int[n];
		comps = new ArrayDeque[n];
		for(int i = 0; i < n; ++i) {
			par[i] = i;
			comps[i] = new ArrayDeque<>();
			comps[i].add(i);
		}
	}
	boolean union(int a, int b) {
		a = par[a];
		b = par[b];
		
		if(a == b) return false;
		
		// make a smaller
		if(comps[a].size() > comps[b].size()) a = b ^ a ^ (b = a);
		
		// merge a into b
		for(int x : comps[a]) {
			comps[b].add(x);
			par[x] = b;
		}
		comps[a].clear();
		
		return true;
	}
	boolean sameComp(int a, int b) {
		return par[a] == par[b];
	}
}
