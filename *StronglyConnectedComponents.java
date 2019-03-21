class StronglyConnectedComponents {
    static int n, m, preCt = 0, numSccs = 0;
    static graph g, meta;
    static int[] pre, low, id;
    static ArrayDeque<Integer> stk = new ArrayDeque<>();
    public static void main (String[] args){
        g = new graph(n);
        for(int i = 0; i < m; ++i){
            int a, b; //edge from a to b
            g.edges[a].offer(b);
        }
        
        pre = new int[n];
        low = new int[n];
        id = new int[n];
        Arrays.fill(pre, -1);
        
        for(int i = n - 1; i >= 0; --i){
            if(pre[i] == -1) dfs(i);
        }
        
        meta = new graph(numSccs);
        for(int i = 0; i < n; ++i){
            for(int j : (ArrayDeque<Integer>) g.edges[i]){
                if(id[i] != id[j]) meta.edges[id[i]].offer(id[j]);
            }
        }
        
    }
    static void dfs(int idx){
        low[idx] = pre[idx] = preCt++;
        stk.push(idx);
        
        for(int next : (ArrayDeque<Integer>) g.edges[idx]){
            if(pre[next] == -1) dfs(next);
            
            low[idx] = Math.min(low[idx], low[next]);
        }
        
        if(low[idx] == pre[idx]){
            while(true){
                int curr = stk.pop();
                id[curr] = numSccs;
                low[curr] = n;
                
                if(curr == idx) break;
            }
            ++numSccs;
        }
    }
    static class graph{
        int n;
        ArrayDeque[] edges;
        graph(int nn){
            edges = new ArrayDeque[n = nn];
            for(int i = 0; i < n; ++i) edges[i] = new ArrayDeque<>();
        }
    }
}
