class Lowlink {
    static int n, m, count = 0;
    static ArrayDeque[] edges;
    static int[] pre, low;
    static boolean[] art, used;
    static ArrayDeque<edge> ad = new ArrayDeque<>();
    static ArrayDeque<comp> comps = new ArrayDeque<>();
	public static void main (String[] args){
	    Scanner sc = new Scanner(System.in);
	    n = sc.nextInt();
	    m = sc.nextInt();
	    pre = new int[n];
	    Arrays.fill(pre, -1);
	    low = new int[n];
	    edges = new ArrayDeque[n];
	    for(int i = 0; i < n; ++i) edges[i] = new ArrayDeque<>();
	    art = new boolean[n];
	    used = new boolean[m];
	    
	    for(int i = 0; i < m; ++i){
	        int a = sc.nextInt(), b = sc.nextInt();
	        edges[a].add(new edge(a, b, i));
	        edges[b].add(new edge(b, a, i));
	    }
	    
	    for(int i = 0; i < n; ++i){
	        if(pre[i] == -1) dfs(i, -1, -1);
	    }
	    
	    for(comp c : comps){
	       // System.out.println(c.edgesString());
	        System.out.println(c.nodesString());
	       // System.out.println();
	    }
	    
	}
	static void dfs(int idx, int par, int id){
	    low[idx] = pre[idx] = count++;
	    
	    int numFwd = 0;
	    for(edge e : (ArrayDeque<edge>) edges[idx]){
	        if(e.v != par && !used[e.id]){
	            
	            used[e.id] = true;
	            ad.push(e);
	            
	            //check if back edge...
	            if(pre[e.v] != -1){
	                low[idx] = Math.min(low[idx], pre[e.v]);
	                continue;
	            }
	            
	            numFwd++;
	            dfs(e.v, idx, e.id);
	            low[idx] = Math.min(low[idx], low[e.v]);
	            
	            if(par == -1 ? numFwd > 1 : low[e.v] >= pre[idx]){
	                art[idx] = true;
	                pop(e.id);
	                
	            }
	        }
	    }
	    
	    if(par == -1) pop(-1);
	}
	static void pop(int stopId){
	    if(ad.isEmpty()) return;
	    comp out = new comp();
	    
	    while(true){
	        edge e = ad.pop();
	        out.add(e);
	        if(e.id == stopId || ad.isEmpty()) break;
	    }
	    
	    comps.add(out);
	}
	static class comp{
	    ArrayDeque<edge> edges = new ArrayDeque<>();
	    HashSet<Integer> nodes = new HashSet<>();
	    comp(){}
	    void add(edge in){
	        edges.add(in);
	        nodes.add(in.u);
	        nodes.add(in.v);
	    }
	    public String edgesString(){
	        String out = "{";
	        for(edge e : edges) out += e.id + " ";
	        out = out.substring(0, out.length() - 1) + "}";
	        return out;
	    }
	    public String nodesString(){
	        String out = "[";
	        for(int i : nodes) out += i + " ";
	        out = out.substring(0, out.length() - 1) + "]";
	        return out;
	    }
	}
	static class edge{
	    int u, v, id;
	    edge(int uu, int vv, int ii){
	        u = uu;
	        v = vv;
	        id = ii;
	    }
	}
}
