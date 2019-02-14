class Hashing {
    static long mod = 1_000_000_007, modi = 370_370_373;
    static long mod2 = 1_000_000_009, modi2 = 296_296_299; //don't use
    static long[] pow = new long[200_000];
	public static void main (String[] args){
	    pow[0] = 1;
	    for(int i = 1; i < 200_000; ++i) pow[i] = (pow[i - 1] * 27) % mod;
	    
	}
	static long init(int s, int l, int[] arr){
	    long out = 0;
	    for(int i = s; i < s + l; ++i){
	        out = (out + arr[i] * pow[i - s]) % mod;
	    }
	    return out;
	}
	static long rollRight(long hash, int i, int l, int[] arr){
	    hash = (hash - arr[i] + mod) % mod;                     //pop first
	    hash = (hash * modi) % mod;                             //shift left
	    hash = (hash + arr[i + l] * pow[l - 1]) % mod;          //add last
	    return hash;
	}
	static long rollLeft(long hash, int i, int l, int[] arr){
	    hash = (hash - arr[i + l] * pow[l - 1] + mod) % mod;    //pop last
	    hash = (hash * 27) % mod;                               // shift right
	    hash = (hash + arr[i]) % mod;                           //add first
	    return hash;
	}
	static long addHash(long hash1, int l1, long hash2){
	    return (hash1 + pow[l1] * hash2) % mod;
	}
}
