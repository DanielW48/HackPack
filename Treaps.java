class Treaps {
	static final Random rand = new Random();
	
	static node root;
	
	static long getMin(int l, int r) {
		node[] b = split(root, r + 1);
		node[] a = split(b[0], l);
		
		long out = a[1].min;
		
		b[0] = merge(a[0], a[1]);
		root = merge(b[0], b[1]);
		
		return out;
	}
	static long getSum(int l, int r) {
		node[] b = split(root, r + 1);
		node[] a = split(b[0], l);
		
		long out = a[1].sum;
		
		b[0] = merge(a[0], a[1]);
		root = merge(b[0], b[1]);
		
		return out;
	}
	static void add(int l, int r, long add) {
		node[] b = split(root, r + 1);
		node[] a = split(b[0], l);
		
		addNode(a[1], add);
		
		b[0] = merge(a[0], a[1]);
		root = merge(b[0], b[1]);
	}
	static node merge(node a, node b) {
		if(a == null) return b;
		if(b == null) return a;
		
		push(a);
		push(b);

		if(a.prior < b.prior) {
			a.r = merge(a.r, b);
			update(a);
			return a;
		}
		else{
			b.l = merge(a, b.l);
			update(b);
			return b;
		}
	}
	static node[] split(node a, int numLeft) {
		if(a == null) return new node[2];
		
		push(a);
		
		if(numLeft <= size(a.l)) {
			node[] get = split(a.l, numLeft);

			a.l = get[1];
			
			update(a);
			
			return new node[] {get[0], a};
		}
		else {
			node[] get = split(a.r, numLeft - (size(a.l) + 1));

			a.r = get[0];

			update(a);
			
			return new node[] {a, get[1]};
		}
	}
	static void update(node in) {
		in.size = 1 + size(in.l) + size(in.r);
		in.sum = in.val + sum(in.l) + sum(in.r);
		in.min = min(in.val, min(min(in.l), min(in.r)));
	}
	static void push(node in) {
		if(in.l != null) addNode(in.l, in.lazy);
		if(in.r != null) addNode(in.r, in.lazy);
		
		in.lazy = 0;
	}
	static void addNode(node in, long add) {
		in.val += add;
		in.sum += add * in.size;
		in.min += add;
		in.lazy += add;
	}
	static long min(node in) {
		return in == null ? Long.MAX_VALUE : in.min;
	}
	static long sum(node in) {
		return in == null ? 0 : in.sum;
	}
	static int size(node in) {
		return in == null ? 0 : in.size;
	}
	static class node{
		int prior, size;
		long val, sum, min, lazy;
		node l, r;
		node(long vv){
			min = sum = val = vv;
			prior = rand.nextInt();
			size = 1;
		}
	}
	static long min(long a, long b) {
		return a < b ? a : b;
	}
}
