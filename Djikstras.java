import java.util.*;
public class Djikstras {
    static int n, s;
    static ArrayDeque[] edges;
    static long[] d;
	public static void main (String[] args){
	    PriorityQueue<state> pq = new PriorityQueue<>();
	    d[s] = 0;
	    pq.add(new state(s, 0));
	    while(!pq.isEmpty()){
	        state curr = pq.poll();
	        if(curr.d > d[curr.i]) continue;
	        
	        for(edge e : (ArrayDeque<edge>) edges[curr.i]){
	            long currd = d[curr.i] + e.w;
	            if(currd < d[e.v]){
	                d[e.v] = currd;
	                pq.add(new state(e.v, currd));
	            }
	        }
	    }
	}
	static class edge{
	    int v, w;
	    edge(int vv, int ww){
	        v = vv;
	        w = ww;
	    }
	}
	static class state implements Comparable<state>{
	    int i;
	    long d;
	    state(int ii, long dd){
	        i = ii;
	        d = dd;
	    }
	    public int compareTo(state in){
	        return Long.compare(d, in.d);
	    }
	}
}
