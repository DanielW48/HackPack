static class SCCs {
	int n;
	graph g;
	int preCount, metaN, stkSize;
	int[] pre, low, map, stk;

	// returns DAG meta graph
	graph getMeta(graph gg) {
		g = gg;
		n = g.n;

		// do lowlink
		preCount = -1;
		metaN = 0;
		pre = new int[n];
		Arrays.fill(pre, -1);
		low = new int[n];
		map = new int[n]; // maps each node in original graph g to new node index in meta
		stk = new int[n];
		stkSize = 0;
		for(int i = 0; i < n; ++i) {
			if(pre[i] == -1) dfs(i);
		}

		graph meta = new graph(metaN);
		for(int u = 0; u < n; ++u) {
			for(edge e : g.edges[u]) {
				if(map[u] != map[e.v]) meta.addEdge(map[u], map[e.v], e.w);
			}
		}

		return meta;
	}
	void dfs(int idx) {
		low[idx] = pre[idx] = ++preCount;
		stk[stkSize++] = idx;

		for(edge e : g.edges[idx]) {
			if(pre[e.v] == -1) dfs(e.v);

			if(low[e.v] < low[idx]) low[idx] = low[e.v];
		}
		
		if(low[idx] == pre[idx]) pop(idx);
	}
	void pop(int stop) {
		while(stkSize > 0) {
			int curr = stk[--stkSize];
			map[curr] = metaN;
			low[curr] = n;
			if(curr == stop) break;
		}
		++metaN;
	}
}
static class graph {
	int n;
	ArrayDeque<edge>[] edges;
	graph(int nn){
		edges = new ArrayDeque[n = nn];
		for(int i = 0; i < n; ++i) edges[i] = new ArrayDeque<>();
	}
	void addEdge(int u, int v) {
		addEdge(u, v, 1);
	}
	void addEdge(int u, int v, int w) {
		edges[u].add(new edge(v, w));
	}
}
static class edge{
	int v, w;
	edge(int vv, int ww){
		v = vv;
		w = ww;
	}
}
