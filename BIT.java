public class BIT{
  int n;
  long[] arr;
  BIT(int nn){
    n = nn;
    arr = new long[n + 1];
  }
  void update(int idx, long v){
    while(idx < arr.length){
      arr[idx] += v;
      idx += (idx & -idx);
    }
  }
  long sum(int idx){
    long out = 0;
    while(idx > 0){
      out += arr[idx];
      idx -= (idx & -idx);
    }
    return out;
  }
  long getKth(int k) {
		int lastK = n;
		int pos = 0;
		int pow2 = Integer.highestOneBit(lastK);
		while(pow2 > 0){
			if(pos + pow2 <= n){
				if(arr[pos + pow2] >= k){
					lastK = Math.min(lastK, pos + pow2);
				}
				else{
					pos += pow2;
					k -= arr[pos];
				}
			}
			pow2 >>= 1;
		}
		return lastK;
}
