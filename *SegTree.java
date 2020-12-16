class SegTree {
	int n;
	long[] li, ri, sum, min, lazy;
	SegTree(int nn){
		n = nn;
		
		li = new long[n * 4 + 1];
		ri = new long[n * 4 + 1];
		sum = new long[n * 4 + 1];
		min = new long[n * 4 + 1];
		lazy = new long[n * 4 + 1];
		
		build(1, 0, n - 1);
	}
	void build(int i, int l, int r) {
		li[i] = l;
		ri[i] = r;
		
		if(l != r) {
			int m = (l + r) / 2;
			build(2 * i, l, m);
			build(2 * i + 1, m + 1, r);
		}
	}
	void add(int s, int e, long v) {
		add(1, s, e, v);
	}
	void add(int i, int s, int e, long v) {
		if(e < li[i] || s > ri[i]) return;
		
		if(s <= li[i] && ri[i] <= e) {
			sum[i] += (ri[i] - li[i] + 1) * v;
			min[i] += v;
			lazy[i] += v;
			return;
		}
		
		push(i);
		
		add(2 * i, s, e, v);
		add(2 * i + 1, s, e, v);
		
		sum[i] = sum[2 * i] + sum[2 * i + 1];
		min[i] = min(min[2 * i], min[2 * i + 1]);
	}
	long getSum(int s, int e) {
		return getSum(1, s, e);
	}
	long getSum(int i, int s, int e) {
		if(e < li[i] || s > ri[i]) return 0;
		
		if(s <= li[i] && ri[i] <= e) return sum[i];
		
		push(i);
		
		return getSum(2 * i, s, e) + getSum(2 * i + 1, s, e);
	}
	long getMin(int s, int e) {
		return getMin(1, s, e);
	}
	long getMin(int i, int s, int e) {
		if(e < li[i] || s > ri[i]) return Long.MAX_VALUE;
		
		if(s <= li[i] && ri[i] <= e) return min[i];
		
		push(i);
		
		return min(getMin(2 * i, s, e), getMin(2 * i + 1, s, e));
	}
	void push(int i) {
		sum[2 * i] += (ri[2 * i] - li[2 * i] + 1) * lazy[i];
		min[2 * i] += lazy[i];
		lazy[2 * i] += lazy[i];
		
		sum[2 * i + 1] += (ri[2 * i + 1] - li[2 * i + 1] + 1) * lazy[i];
		min[2 * i + 1] += lazy[i];
		lazy[2 * i + 1] += lazy[i];
		
		lazy[i] = 0;
	}
	
	long min(long a, long b) {
		return a < b ? a : b;
	}
}
