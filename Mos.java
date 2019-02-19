class Mos {
    static int n, q, size;
    static int[] arr, out;
    static query[] qs;
    public static void main (String[] args){
        size = (int)Math.sqrt(n);
        
        int[] freq = new int[1_000_001];
        freq[arr[0]] = 1;
        int l = 0, r = 0;
        int ans = 1; //currently algorithm will find the number of distinct integers in a range [l, r]
        for(int i = 0; i < q; ++i){
            query curr = qs[i];
            int cl = curr.l, cr = curr.r;
            
            while(l > cl){
                --l;
                ++freq[arr[l]];
                if(freq[arr[l]] == 1) ++ans;
            }
            while(r < cr){
                ++r;
                ++freq[arr[r]];
                if(freq[arr[r]] == 1) ++ans;
            }
            while(l < cl){
                --freq[arr[l]];
                if(freq[arr[l]] == 0) --ans;
                ++l;
            }
            while(r > cr){
                --freq[arr[r]];
                if(freq[arr[r]] == 0) --ans;
                --r;
            }
            
            out[curr.i] = ans;
        }
        
    }
    static class query implements Comparable<query> {
        int l, r, i, b;
        query(int ll, int rr, int ii){
            l = ll;
            r = rr;
            i = ii;
            b = l / size;
        }
        public int compareTo(query in){
            int d = b - in.b;
            if(d != 0) return d;
            if(b % 2 == 0) return r - in.r;
            return in.r - r;
        }
    }
}
