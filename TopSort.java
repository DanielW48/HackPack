class TopSort {
    static int n;
    static int[] inEdges;
    static ArrayDeque[] edges;
	public static void main (String[] args){
	    inEdges = new int[n];
	    //while scanning in edges, add 1 to inEdges at connecting node
	    
	    ArrayDeque<Integer> starts = new ArrayDeque<Integer>();
	    for(int i = 0; i < n; ++i){
	        if(inEdges[i] == 0) starts.offer(i);
	    }
	    
	    while(!starts.isEmpty()){
	        ArrayDeque<Integer> newStarts = new ArrayDeque<Integer>();
	        
	        for(int curr : starts){
	            for(int next : (ArrayDeque<Integer>) edges[curr]){
	                --inEdges[next];
	                if(inEdges[next] == 0) newStarts.offer(next);
	            }
	        }
	        
	        starts.clear();
	        starts.addAll(newStarts);
	    }
	    
	}
}
