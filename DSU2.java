public class DSU2 {
    static int[] par, height;
    static ArrayDeque[] set;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        par = new int[n];
        height = new int[n];
        set = new ArrayDeque[n];
        
        for(int i = 0; i < n; ++i){
            par[i] = i;
            height[i] = 1;
            set[i] = new ArrayDeque<edge>();
            set[i].push(i);
        }
        
        PriorityQueue<edge> edges = new PriorityQueue<>();
        //scan in edges
        
        int numConnd = 1;
        int out = 0;
        while(!edges.isEmpty()){
            edge curr = edges.poll();
            if(!union(curr.u, curr.v)) continue;
            
            out += curr.w;
            if(++numConnd == n) break;
        }
    }
    static boolean union(int a, int b){
        int parA = par[a], parB = par[b];
        if(parA == parB) return false;
        
        if(height[parA] > height[parB]) parA = parA ^ parB ^ (parB = parA);
        
        height[parB] += height[parA];
        for(Object temp : set[parA]){
            int curr = (int)temp;
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
