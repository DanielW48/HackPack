class RMQ {
	int n, m;
	int[] bitIdx;
	int[][] rmq;
	RMQ(int[] arr){
		n = arr.length;
		bitIdx = new int[n + 1];
		
		m = 0;
		for(int i = 1; i <= n; ++i) {
			if(i == 1 << (m + 1)) ++m;
			bitIdx[i] = m;
		}
		
		rmq = new int[m + 1][n];
		for(int i = 0; i < n; ++i) rmq[0][i] = arr[i];
		
		for(int k = 1; k <= m; ++k) {
			for(int i = 0; i < n; ++i) {
				if(i + (1 << k) - 1 < n) rmq[k][i] = min(rmq[k - 1][i], rmq[k - 1][i + (1 << (k - 1))]);
			}
		}
	}
	int getMin(int l, int r) {
		int k = bitIdx[r - l + 1];
		return min(rmq[k][l], rmq[k][r - (1 << k) + 1]);
	}
	int min(int a, int b) {
		return a < b ? a : b;
	}
}
