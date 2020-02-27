class LCA {
    static final int m = 20; // works for up to 10^6 nodes
    static int n;
    static ArrayDeque<Integer>[] edges;
    static int[] height;
    static int[][] jmp;
    public static void main(String[] args) {
        jmp = new int[m][n];
        for(int[] z : jmp) Arrays.fill(z, -1);
        height = new int[n];
        
        dfs(0, -1);
        
        for(int j = 1; j < m; ++j) {
            for(int i = 0; i < n; ++i) {
                int idx = jmp[j - 1][i];
                if(idx != -1) jmp[j][i] = jmp[j - 1][idx];
            }
        }
        
        int a = 0, b = 0, lca = lca(a, b);
        int pathLength = height[a] + height[b] - 2*height[lca];
    }
    static int lca(int a, int b) {
        if(height[a] > height[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        
        for(int j = m - 1; j >= 0; --j) {
            int newb = jmp[j][b];
            if(newb != -1 && height[newb] >= height[a]) b = newb;
        }
        
        if(a == b) return a;
        
        for(int j = m - 1; j >= 0; --j) {
            int newa = jmp[j][a], newb = jmp[j][b];
            if(newa != newb) {
                a = newa;
                b = newb;
            }
        }
        
        return jmp[0][a];
    }
    static void dfs(int idx, int par) {
        for(int next : edges[idx]) {
            if(next != par) {
                height[next] = height[idx] + 1;
                jmp[0][next] = idx;
                dfs(next, idx);
            }
        }
    }
}
