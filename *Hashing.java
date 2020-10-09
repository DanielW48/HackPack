class Hashing {
	// don't forget to change the bases!
	static final long[] base = {27, 31}, mod = {1_000_000_007, 1_000_000_009};
	static final long[] modi = {370_370_373, 838_709_685};
	static final int max = 500_000; // max string length
	static final long[][] pow = new long[2][max + 1], powi = new long[2][max + 1];
	
	static int n;
	static int[] arr;
	static long[][] pre;
	public static void main(String[] args) {
		setup(0);
		setup(1);
		
		char[] in = "nah boach".toCharArray();
		n = in.length;
		arr = new int[n];
		for(int i = 0; i < n; ++i) arr[i] = in[i] - 'a';
		
		pre = new long[2][n];
		init(0);
		init(1);
		
	}
	static void setup(int mi) {
		pow[mi][0] = powi[mi][0] = 1;
		for(int i = 1; i <= max; ++i) {
			pow[mi][i] = (pow[mi][i - 1] * base[mi]) % mod[mi];
			powi[mi][i] = (powi[mi][i - 1] * modi[mi]) % modi[mi];
		}
	}
	static void init(int mi) {
		pre[mi][0] = arr[0];
		for(int i = 1; i < n; ++i) pre[mi][i] = (pre[mi][i] + arr[i] * pow[mi][i]) % mod[mi];
	}
	static long sub(int l, int r, int mi) {
		long out = (pre[mi][r] - (l == 0 ? 0 : pre[mi][l - 1]) + mod[mi]) % mod[mi];
		return (out * powi[mi][l]) % mod[mi];
	}
	static long cat(long a, long b, int alen, int mi) {
		return (a + pow[mi][alen] * b) % mod[mi];
	}
}
