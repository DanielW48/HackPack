public class segmentTree {
	public static void main (String[] args) {
	    int n;
	    node seg = new node(1, n);
	    
	}
	static class node{
	    long li, ri, m;
	    long sum, min, lazy = 0;
	    node l = null, r = null;
	    node(long liIn, long riIn){
	        li = liIn;
	        ri = riIn;
	        m = (li + ri) / 2;
	        if(li != ri){
	            l = new node(li, m);
	            r = new node(m + 1, ri);
	        }
	    }
	    void add(int s, int e, int v){
	        if(s <= li && ri <= e){
	            sum += (ri - li + 1) * v;
	            min += v;
	            lazy += v;
	            return;
	        }
	        
	        push();
	        if(s <= m) l.add(s, e, v);
	        if(e > m) r.add(s, e, v);
	        sum = l.sum + r.sum;
	        min = Math.min(l.min, r.min);
	    }
	    long getSum(int s, int e){
	        if(s <= li && ri <= e) return sum;
	        
	        push();
	        long out = 0;
	        if(s <= m) out += l.getSum(s, e);
	        if(e > m) out += r.getSum(s, e);
	        return out;
	    }
	    long getMin(int s, int e){
	        if(s <= li && ri <= e) return min;
	        
	        push();
	        long out = Long.MAX_VALUE;
	        if(s <= m) out = Math.min(out, l.getMin(s, e));
	        if(e > m) out = Math.min(out, r.getMin(s, e));
	        return out;
	    }
	    void push(){
	        l.lazy += lazy;
	        l.sum += (l.ri - l.li + 1) * lazy;
	        l.min += lazy;
	            
	        r.lazy += lazy;
	        r.sum += (r.ri - r.li + 1) * lazy;
	        r.min += lazy;
	        
	        lazy = 0;
	    }
	}
}
