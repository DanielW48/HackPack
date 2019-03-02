class DSU2 {
    static int n, m; //vertices, edges
    static int[] par, height;
    static ArrayDeque[] set;
    public static void main(String[] args){
        par = new int[n];
        height = new int[n];
        set = new ArrayDeque[n];
        
        for(int i = 0; i < n; ++i){
            par[i] = i;
            height[i] = 1;
            set[i] = new ArrayDeque<>();
            set[i].push(i);
        }
        
        PriorityQueue<edge> edges = new PriorityQueue<>();
        long sum = 0;
        int max = 0, numConnd = 1;
        while(!edges.isEmpty() && numConnd < n){
            edge curr = edges.poll();
            if(union(curr.u, curr.v)){
                sum += curr.w;
                max = curr.w;
                ++numConnd;
            }
        }
    }
    static boolean union(int a, int b){
        int parA = par[a], parB = par[b];
        if(parA == parB) return false;
        
        if(height[parA] > height[parB]) parA = parA ^ parB ^ (parB = parA);
        
        height[parB] += height[parA];
        for(int curr : (ArrayDeque<Integer>) set[parA]){
            par[curr] = parB;
            set[parB].push(curr);
        }
        set[parA].clear();
        
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
