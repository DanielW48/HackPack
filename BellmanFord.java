class BellmanFord {
	static final long max = Long.MAX_VALUE / 2;
	
	static int n, m;
	static ArrayDeque<edge>[] edges;
	static long[] bellmanFord(int start) {
		long[] dist = new long[n];
		Arrays.fill(dist, max);
		dist[start] = 0;
		
		for(int k = 0; k < n - 1; ++k) {
			for(int u = 0; u < n; ++u) {
				for(edge e : edges[u]) {
					if(dist[u] != max && dist[u] + e.w < dist[e.v]) {
						dist[e.v] = dist[u] + e.w;
					}
				}
			}
		}
		
		// check for negative cycles
		for(int u = 0; u < n; ++u) {
			for(edge e : edges[u]) {
				if(dist[u] != max && dist[u] + e.w < dist[e.v]) {
					// do BFS
					ArrayDeque<Integer> ad = new ArrayDeque<>();
					
					dist[u] = -max;
					ad.add(u);
					while(!ad.isEmpty()) {
						int curr = ad.poll();
						
						for(edge currE : edges[curr]) {
							if(dist[currE.v] != -max) {
								dist[currE.v] = -max;
								ad.add(currE.v);
							}
						}
					}
				}
			}
		}
		
		return dist;
	}
}
