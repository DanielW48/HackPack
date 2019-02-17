class DSU {
	static int n;
	static int[] root, height;
	public static void main(String[] args){
	    root = new int[n];
	    height = new int[n];
	    for(int i = 0; i < n; ++i){
	        root[i] = i;
	        height[i] = 1;
	    }
	    
		PriorityQueue<edge> pq = new PriorityQueue<>();
	    long sum = 0;
	    int max = 0, numConnd = 1;
	    while(!pq.isEmpty() && numConnd < n){
	        edge curr = pq.poll();
	        if(union(curr.u, curr.v)){
	            sum += curr.w;
	            max = curr.w;
	            ++numConnd;
	        }
	    }
	}
	static int getPar(int idx) {
	    if(root[idx] != idx) return getPar(root[idx]);
	    return idx;
	}
	static boolean union(int x, int y) {
	    int parX = getPar(x);
	    int parY = getPar(y);
	    if(parX == parY) return false;
	    
	    if(height[parX] > height[parY]) parX = parX ^ parY ^ (parY = parX);
	    
	    root[parY] = parX;
	    height[parX] += height[parY];
	    return true;
	}
	static class edge implements Comparable<edge>{
	    int u, v, w;
	    edge(int uu, int vv, int ww){
	        u = uu;
	        v = vv;
	        w = ww;
	    }
	    public int compareTo(edge in){
	        return w - in.w;
	    }
	}
}
