struct RMQ{
    vector<vector<int>> rmq;
    RMQ(){}
    RMQ(vector<int> arr){
        int n = size(arr);
        int m = __lg(n);
        rmq = vector<vector<int>>(m + 1, vector<int>(n));
        for(int i = 0; i < n; ++i) rmq[0][i] = arr[i];
        for(int k = 1; k <= m; ++k){
            for(int i = 0; i < n; ++i){
                if(i + (1 << k) - 1 < n){
                    rmq[k][i] = min(rmq[k - 1][i], rmq[k - 1][i + (1 << (k - 1))]);
                }
            }
        }
    }
    int getMin(int l, int r){
        int k = __lg(r - l + 1);
        return min(rmq[k][l], rmq[k][r - (1 << k) + 1]);
    }
};
