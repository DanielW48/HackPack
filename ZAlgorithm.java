class ZAlgorithm {
    static int n;
    static char[] arr;
    static int[] z;
	public static void main (String[] args){
	    char[] arr;
	    n = arr.length;
	    z = new int[n];
	    int l = 0, r = 0;
	    for(int i = 1; i < n; ++i){
	        if(i > r){
	            l = r = i;
	            while(r < n && arr[r - l] == arr[r]) ++r;
	            z[i] = r - l;
	            --r;
	        }
	        else{
	            int k = i - l;
	            if(z[k] < r - i + 1) z[i] = z[k];
	            else{
	                l = i;
	                while(r < n && arr[r - l] == arr[r]) ++r;
	                z[i] = r - l;
	                --r;
	            }
	        }
	    }
	    
	}
}
