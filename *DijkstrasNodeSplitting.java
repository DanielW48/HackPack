class DijkstrasNodeSplitting {
    static int n, m, sta, max = Integer.MAX_VALUE / 2; // sta represents initial of your second variable
    static int[][] d;
    static ArrayDeque[] edges;
    public static void main (String[] args){
	    edges = new ArrayDeque[n];
	    for(int i = 0; i < n; ++i) edges[i] = new ArrayDeque<>();
	    
	    int out = max;
	    
	    d = new int[n][sta + 1];
	    for(int i = 0; i < n; ++i) Arrays.fill(d[i], max);
	    d[0][sta] = 0;
	    PriorityQueue<state> pq = new PriorityQueue<>();
	    pq.add(new state(0, sta, 0));
	    while(!pq.isEmpty()){
	        state curr = pq.poll();
	        if(curr.d > d[curr.i][curr.s]) continue;
	        
	        if(curr.i == n - 1){
	            out = curr.d;
	            break;
	        }
	        
	        for(edge e : (ArrayDeque<edge>) edges[curr.i]){
	            int newD = curr.d + e.w;
	            int newS = curr.s - e.s;
	            if(newS >= 0 && newD < d[e.v][newS]){
	                d[e.v][newS] = newD;
	                pq.add(new state(e.v, newS, newD));
	            }
	        }
	    }
	    
	}
	static class state implements Comparable<state> {
	    int i, s, d;
	    state(int ii, int ss, int dd){
	        i = ii;
	        s = ss;
	        d = dd;
	    }
	    public int compareTo(state in){
	        return Long.compare(d, in.d);
	    }
	}
	static class edge{
	    int v, s, w; // s represents second variable: stamina, fuel, etc
	    edge(int vv, int ss, int ww){
	        v = vv;
	        s = ss;
	        w = ww;
	    }
	}
}
