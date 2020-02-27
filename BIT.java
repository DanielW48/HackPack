class BIT{
    int n;
    long[] arr;
    BIT(int nn){
        n = nn;
        arr = new long[n + 1];
    }
    void update(int i, long v){
        if(i > n) return;
        arr[i] += v;
        update(i + (i & -i), v);
    }
    long sum(int i){
        if(i < 1) return 0;
        return arr[i] + sum(i - (i & -i));
    }
    int getKth(long k) {
        int pow = Integer.highestOneBit(n), idx = 0;
        while(pow > 0){
            if(idx + pow <= n && arr[idx + pow] <= k){
                idx += pow;
                k -= arr[idx];
            }
            pow /= 2;
        }
        return idx;
    }
}
