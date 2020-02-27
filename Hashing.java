class Hashing {
	static final long b1 = 27, b2 = 31, mod1 = 1_000_000_007, mod2 = 1_000_000_009, modi1 = 370_370_373, modi2 = 838_709_685;
	static final int max = 500_000; // max string length
	static final long[] pow1 = new long[max], pow2 = new long[max], powi1 = new long[max], powi2 = new long[max];
	
	static int n;
	static int[] arr;
	static long[] pre1, pre2;
	public static void main(String[] args) {
		pow1[0] = pow2[0] = powi1[0] = powi2[0] = 1;
		for(int i = 1; i < max; ++i) {
			pow1[i] = (pow1[i - 1] * b1) % mod1;
			pow2[i] = (pow2[i - 1] * b2) % mod2;
			powi1[i] = (powi1[i - 1] * modi1) % mod1;
			powi2[i] = (powi2[i - 1] * modi2) % mod2;
		}
		
		char[] in;
		n = in.length;
		arr = new int[n];
		for(int i = 0; i < n; ++i) arr[i] = in[i] - 'a';
		
		pre1 = new long[n];
		pre2 = new long[n];
		pre1[0] = pre2[0] = arr[0];
		for(int i = 1; i < n; ++i) {
			pre1[i] = (pre1[i - 1] + arr[i] * pow1[i]) % mod1;
			pre2[i] = (pre2[i - 1] + arr[i] * pow2[i]) % mod2;
		}
		
	}
	static long sub1(int l, int r) {
		long out = (pre1[r] - (l == 0 ? 0 : pre1[l - 1]) + mod1) % mod1;
		return (out * powi1[l]) % mod1;
	}
	static long sub2(int l, int r) {
		long out = (pre2[r] - (l == 0 ? 0 : pre2[l - 1]) + mod2) % mod2;
		return (out * powi2[l]) % mod2;
	}
}
