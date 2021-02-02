static class EditDistance {
	// finds edit distance between two arrays a and b in O(k^2 * log(max(n, m))),
	// where k is the maximum distance you need to check for and n and m
	// are the lengths of the two arrays, or -1 if no distance <= k is found;
	// assumes you can only make edits on the first array
	// the build-back is included and makes edits from right to left;
	// use the constructor once to setup the hashing stuff given the max length
	
	long[] base = {999_999_001, 999_999_005}, mod = {999_999_929, 999_999_937};
	long[] basei = {975_215_448, 129_828_318};
	int max; // max string length
	long[][] pow, powi;

	EditDistance(int maxLen){
		max = maxLen;
		pow = new long[2][max + 1];
		powi = new long[2][max + 1];
		setup();
	}
	
	int n, m, k;
	int[] a, b;
	long[][] preA, preB;
	int[][] dp, best;
	int getEditDistance(char[] tempA, char[] tempB, int kk) {
		// if using only arrays, replace parameters with a and b and remove this
		a = new int[tempA.length];
		for(int i = 0; i < tempA.length; ++i) a[i] = tempA[i] - 'a' + 1;
		b = new int[tempB.length];
		for(int i = 0; i < tempB.length; ++i) b[i] = tempB[i] - 'a' + 1;
		
		n = a.length;
		m = b.length;
		k = kk;
		
		preA = getPre(a);
		preB = getPre(b);
		
		// diff = bi - ai

		dp = new int[k + 1][k + 1 + k];
		for(int[] a : dp) Arrays.fill(a, -1);
		best = new int[k + 1][k + 1 + k];
		
		dp[0][idx(0)] = 0;
		
		int outDist = k + 1, outDiff = -1, outInserts = -1, outDeletes = -1;
		for(int dist = 0; dist <= k && dist < outDist; ++dist) {
			for(int diff = -k; diff <= k; ++diff) {
				int ai = dp[dist][idx(diff)];
				int bi = ai + diff;
				
				if(ai < 0 || bi < 0) continue;
				
				if(ai < n && bi < m) {
					ai = extendAi(ai, bi);
					bi = ai + diff;
				}
				
				if(ai == n || bi == m) {
					int currInserts = m - bi;
					int currDeletes = n - ai;
					int currDist = dist + currInserts + currDeletes;
					if(currDist < outDist) {
						outDist = currDist;
						outInserts = currInserts;
						outDeletes = currDeletes;
						outDiff = diff;
					}
					continue;
				}
				
				if(dist == k) continue;

				// try inserting
				if(diff + 1 <= k && ai > dp[dist + 1][idx(diff + 1)]) {
					dp[dist + 1][idx(diff + 1)] = ai;
					best[dist + 1][idx(diff + 1)] = 0;
				}

				// try deleting
				if(diff - 1 >= -k && ai + 1 > dp[dist + 1][idx(diff - 1)]) {
					dp[dist + 1][idx(diff - 1)] = ai + 1;
					best[dist + 1][idx(diff - 1)] = 1;
				}

				// try substituting
				if(ai + 1 > dp[dist + 1][idx(diff)]) {
					dp[dist + 1][idx(diff)] = ai + 1;
					best[dist + 1][idx(diff)] = 2;
				}
			}
		}

		if(outDist > k) {
			// the edit distance is more than k
			return -1;
		}
		
		// if only in need of distance, return outDist here
		
		// do insertions
		for(int bi = m - 1; bi >= m - outInserts; --bi) {
			// INSERT (n + 1) tempB[bi]
		}
		
		// do deletions
		for(int ai = n - 1; ai >= n - outDeletes; --ai) {
			// DELETE (ai + 1)
		}
		
		for(int dist = outDist - (outInserts + outDeletes), diff = outDiff; dist >= 1; --dist) {
			int ai = dp[dist][idx(diff)];
			int trans = best[dist][idx(diff)];

			if(trans == 0) {
				--diff;
				// INSERT (ai + 1) tempB[ai + diff]
			}
			else if(trans == 1) {
				++diff;
				// DELETE (ai - 1 + 1)
			}
			else {
				// REPLACE (ai - 1 + 1) tempB[ai - 1 + diff]
			}
		}
		
		return outDist;
	}
	int idx(int diff) {
		return k + diff;
	}
	int extendAi(int ai, int bi) {
		int lo = -1, hi = min(n - 1 - ai, m - 1 - bi);
		while(lo < hi) {
			int g = (lo + hi + 1) / 2;

			if(eq(sub(preA, ai, ai + g), sub(preB, bi, bi + g))) lo = g;
			else hi = g - 1;
		}

		return ai + lo + 1;
	}
	int min(int a, int b) {
		return a < b ? a : b;
	}
	
	// all hashing stuff
	void setupM(int mi) {
		pow[mi][0] = powi[mi][0] = 1;
		for(int i = 1; i <= max; ++i) {
			pow[mi][i] = (pow[mi][i - 1] * base[mi]) % mod[mi];
			powi[mi][i] = (powi[mi][i - 1] * basei[mi]) % mod[mi];
		}
	}
	void setup() {
		setupM(0);
		setupM(1);
	}
	long[] getPreM(int[] arr, int mi) {
		long[] pre = new long[arr.length];
		pre[0] = arr[0];
		for(int i = 1; i < arr.length; ++i) pre[i] = (pre[i - 1] + arr[i] * pow[mi][i]) % mod[mi];
		return pre;
	}
	long[][] getPre(int[] arr){
		return new long[][]{getPreM(arr, 0), getPreM(arr, 1)};
	}
	long subM(long[][] pre, int l, int r, int mi) {
		if(l > r) return 0;
		long out = (pre[mi][r] - (l == 0 ? 0 : pre[mi][l - 1]) + mod[mi]) % mod[mi];
		return (out * powi[mi][l]) % mod[mi];
	}
	long[] sub(long[][] pre, int l, int r) {
		return new long[]{subM(pre, l, r, 0), subM(pre, l, r, 1)};
	}
	boolean eq(long[] a, long[] b) {
		return a[0] == b[0] && a[1] == b[1];
	}
}
