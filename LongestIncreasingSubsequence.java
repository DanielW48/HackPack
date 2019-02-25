class LongestIncreasingSubsequence {
    static int n, out;
    static int[] arr, end;
    public static void main (String[] args){
        arr = new int[n];
        end = new int[n + 1];
        out = 0;
        for(int i = 0; i < n; ++i){
            int curr = binSearch(arr[i]);
            end[curr] = in;
            out = Math.max(out, curr);
        }
    }
    static int binSearch(int in){
        int l = 1, h = out + 1;
        while(l + 2 <= h){
            int g = (l + h) / 2;
            
            if(end[g - 1] < in) l = g;
            else h = g - 1;
        }
        for(int g = l; g <= h; ++g){
            if(end[g - 1] < in) l = g;
        }
        return l;
    }
}
