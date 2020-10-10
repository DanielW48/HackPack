class Hashing {
	// don't forget to change the bases!
	static final long[] base = {27, 31}, mod = {1_000_000_007, 1_000_000_009};
	static final long[] basei = {370_370_373, 838_709_685};
	static final int max = 500_000; // max string length
	static final long[][] pow = new long[2][max + 1], powi = new long[2][max + 1];
	
	static void setupM(int mi) {
		pow[mi][0] = powi[mi][0] = 1;
		for(int i = 1; i <= max; ++i) {
			pow[mi][i] = (pow[mi][i - 1] * base[mi]) % mod[mi];
			powi[mi][i] = (powi[mi][i - 1] * basei[mi]) % mod[mi];
		}
	}
	static long[] getPreM(int[] arr, int mi) {
		long[] pre = new long[arr.length];
		pre[0] = arr[0];
		for(int i = 1; i < arr.length; ++i) pre[i] = (pre[i - 1] + arr[i] * pow[mi][i]) % mod[mi];
		return pre;
	}
	static long[][] getPre(int[] arr){
		return new long[][]{getPreM(arr, 0), getPreM(arr, 1)};
	}
	static long subM(long[][] pre, int l, int r, int mi) {
		if(l > r) return 0;
		long out = (pre[mi][r] - (l == 0 ? 0 : pre[mi][l - 1]) + mod[mi]) % mod[mi];
		return (out * powi[mi][l]) % mod[mi];
	}
	static long[] sub(long[][] pre, int l, int r) {
		return new long[]{subM(pre, l, r, 0), subM(pre, l, r, 1)};
	}
	static boolean eq(long[] a, long[] b) {
		return a[0] == b[0] && a[1] == b[1];
	}
	// if needed
	static long catM(long a, int alen, long b, int mi) {
		return (a + pow[mi][alen] * b) % mod[mi];
	}
	static long[] cat(long[] a, int alen, long[] b) {
		return new long[]{catM(a[0], alen, b[0], 0), catM(a[1], alen, b[1], 1)};
	}
	
	public static void main(String[] args) {
		setupM(0);
		setupM(1);
		
		char[] temp = "nahdawg.".toCharArray();
		int n = temp.length;
		int[] arr = new int[n];
		for(int i = 0; i < n; ++i) arr[i] = temp[i] - 'a' + 1;
		
		long[][] pre = getPre(arr);
		// use pre for all functions
	}
}
