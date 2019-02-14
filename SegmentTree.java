class SegmentTree {
	public static void main (String[] args){
	    int n = 10;
	    node segTree = new node(1, n);
	    
	}
	static class node{
	    int lr, rr, m;
	    long sum = 0;
	    node l = null, r = null;
	    boolean leaf;
	    node(int lrIn, int rrIn){
		    lr = lrIn;
		    rr = rrIn;
		    m = (lr + rr) / 2;
		    leaf = lr == rr;
		    if(!leaf){
			    l = new node(lr, m);
			    r = new node(m + 1, rr);
		    }
	    }
	    void add(int idx, int v){
		    sum += v;
		    
		    if(idx <= m) l.add(idx, v);
		    else r.add(idx, v);
	    }
	    long getSum(int li, int ri){
		    if(li <= lr && rr <= ri) return sum;
            
		    long out = 0;
		    if(li <= m) out += l.getSum(li, ri);
		    if(m + 1 <= ri) out += r.getSum(li, ri);
		    return out;
	    }
	}
}
