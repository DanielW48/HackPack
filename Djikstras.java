public class Djikstras {
	static int n;
	static ArrayDeque[] edges;
	static long[] d;
	public static void main(String[] args) {
		
	}
	static void solve(int start){
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		ad.offer(start);
		d[start] = 0;
		while(!ad.isEmpty()){
			int curr = ad.poll();
			for(edge e : (ArrayDeque<edge>)edges[curr]){
				long currd = d[curr] + e.w;
				if(currd < d[e.v]){
					d[e.v] = currd;
					ad.offer(e.v);
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
}
