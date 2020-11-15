class SuffixArray {
	final char endChar = '\0';
	final char delim = (char)(endChar + 1);
	
	int n;
	String s;
	char[] arr;
	
	int[] suff, equiv, newVals, idxOf;
	int k;
	boolean sortChar;
	
	int[] lcp;
	
	int[] bitIdx;
	int[][] rmq;
	SuffixArray(String in){
		s = in + endChar;
		arr = s.toCharArray();
		n = arr.length;
		
		getSuff();
		getLCP();
		getRMQ();
	}
	void getSuff() {
		ArrayList<Integer> temp = new ArrayList<>();
		for(int i = 0; i < n; ++i) temp.add(i);
		Collections.sort(temp, (a, b) -> {
			return Character.compare(arr[a], arr[b]);
		});
		
		suff = new int[n];
		equiv = new int[n];
		newVals = new int[n];
		
		for(int i = 0; i < n; ++i) suff[i] = temp.get(i);
		
		sortChar = true;
		setEquiv();
		sortChar = false;
		
		int[] buckIdx = new int[n];
		for(k = 0; (1 << k) < n; ++k) {
			for(int i = 0; i < n; ++i) suff[i] = (suff[i] - (1 << k) + n) % n;
			 
			// radix sort
			int[] num = new int[n];
			for(int a : suff) ++num[equiv[a]];
			
			buckIdx[0] = 0;
			for(int i = 1; i < n; ++i) buckIdx[i] = buckIdx[i - 1] + num[i - 1];
			
			// place em in
			for(int a : suff) newVals[buckIdx[equiv[a]]++] = a;
			
			for(int i = 0; i < n; ++i) suff[i] = newVals[i];
			
			setEquiv();
		}
		
		idxOf = new int[n];
		for(int i = 0; i < n; ++i) idxOf[suff[i]] = i;
	}
	void setEquiv() {
		int eqIdx = 0;
		newVals[suff[0]] = 0;
		for(int i = 1; i < n; ++i) {
			if(!equal(suff[i], suff[i - 1])) ++eqIdx;
			newVals[suff[i]] = eqIdx;
		}
		for(int i = 0; i < n; ++i) equiv[i] = newVals[i];
	}
	boolean equal(int a, int b) {
		if(sortChar) return arr[a] == arr[b];
		return equiv[a] == equiv[b] && equiv[(a + (1 << k)) % n] == equiv[(b + (1 << k)) % n];
	}
	void getLCP() {
		int curr = 0;
		lcp = new int[n - 1];
		for(int a = 0; a < n - 1; ++a) {
			while(arr[a + curr] == arr[suff[idxOf[a] - 1] + curr]) ++curr;
			
			lcp[idxOf[a] - 1] = curr;
			
			if(curr > 0) --curr;
		}
	}
	void getRMQ() {
		bitIdx = new int[n + 1];
		int m = 0;
		for(int i = 1; i <= n; ++i) {
			if(i == 1 << (m + 1)) ++m;
			bitIdx[i] = m;
		}
		
		rmq = new int[m + 1][n - 1];
		for(int i = 0; i < n - 1; ++i) rmq[0][i] = lcp[i];
		
		for(int k = 1; k <= m; ++k) {
			for(int i = 0; i < n - 1; ++i) {
				if(i + (1 << k) < n) rmq[k][i] = min(rmq[k - 1][i], rmq[k - 1][i + (1 << (k - 1))]);
			}
		}
	}
	int rmq(int l, int r) {
		int k = bitIdx[r - l + 1];
		return min(rmq[k][l], rmq[k][r - (1 << k) + 1]);
	}
	int lcp(int i, int j) {
		if(i == j) return Integer.MAX_VALUE / 2;
		if(i > j) i = j ^ i ^ (j = i);
		return rmq(i, j - 1);
	}
	int min(int a, int b) {
		return a < b ? a : b;
	}
}
