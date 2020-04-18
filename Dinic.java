class Dinic {
    final int max = 1_000_000_000;
    int n, s, t;
    ArrayDeque<fedge>[] fedges;
    boolean[] blocked;
    int[] d;
    Dinic(int nn){
        n = nn;
        s = n++;
        t = n++;
        fedges = new ArrayDeque[n];
        for(int i = 0; i < n; ++i) fedges[i] = new ArrayDeque<>();
        blocked = new boolean[n];
        d = new int[n];
    }
    void add(int u, int v, int cap, int flow) {
        fedge ue = new fedge(v, cap, flow);
        fedge ve = new fedge(u, 0, 0);
        ue.rev = ve;
        ve.rev = ue;
        fedges[u].add(ue);
        fedges[v].add(ve);
    }
    int flow() {
        int out = 0;
        while(bfs()) {
            Arrays.fill(blocked, false);
            out += dfs(s, max);
        }
        return out;
    }
    boolean bfs() {
		Arrays.fill(d, -1);
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		d[t] = 0;
		ad.add(t);
		while(!ad.isEmpty()) {
			int curr = ad.poll();
			if(curr == s) return true;
			for(fedge e : fedges[curr]) {
				if(e.rev.cap > e.rev.flow && d[e.v] == -1) {
					d[e.v] = d[curr] + 1;
					ad.add(e.v);
				}
			}
		}
		return false;
	}
	int dfs(int idx, int min) {
		if(idx == t) return min;
		int out = 0;
		for(fedge e : fedges[idx]) {
			if(!blocked[e.v] && d[idx] - 1 == d[e.v] && e.cap - e.flow > 0) {
				int get = dfs(e.v, Math.min(min - out, e.cap - e.flow));
				e.flow += get;
				e.rev.flow = -e.flow;
				out += get;
			}
			if(out == min) return out;
		}
		blocked[idx] = out != min;
		return out;
	}
	static class fedge{
		int v, cap, flow;
		fedge rev;
		fedge(int vv, int cc, int ff){
			v = vv;
			cap = cc;
			flow = ff;
		}
	}
}
