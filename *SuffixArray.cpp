struct SuffixArray{
    const int endDelim = INT_MIN, delim = INT_MIN + 1;
    int n = 0;
    vector<int> arr, firstIdx, backMap;
    vector<int> suff, equiv, newVals, idxOf;
    int k = 0;
    bool sortVal = true;
    vector<int> lcp;
    RMQ rmq;
    SuffixArray(vector<vector<int>> vals){
        firstIdx = vector<int>(size(vals));
        for(int i = 0; i < size(vals); ++i){
            firstIdx[i] = n;
            n += size(vals[i]) + 1;
        }

        arr = vector<int>(n);
        backMap = vector<int>(n, -1);
        for(int i = 0; i < size(vals); ++i){
            for(int j = 0; j < size(vals[i]); ++j){
                arr[firstIdx[i] + j] = vals[i][j];
                backMap[firstIdx[i] + j] = i;
            }
            arr[firstIdx[i] + size(vals[i])] = delim;
        }
        arr[n - 1] = endDelim;

        genSuff();
        genLCP();
        genRMQ();
    }
    void genSuff(){
        vector<int> temp(n);
        iota(begin(temp), end(temp), 0);
        sort(begin(temp), end(temp), [&](int a, int b){
            return arr[a] < arr[b];
        });

        suff = temp;
        equiv = vector<int>(n);
        newVals = vector<int>(n);

        setEquiv();
        sortVal = false;

        vector<int> buckIdx(n);
        for(; (1 << k) < n; ++k){
            for(int i = 0; i < n; ++i) suff[i] = (suff[i] - (1 << k) + n) % n;

            // radix sort
            vector<int> num(n);
            for(int a : suff) ++num[equiv[a]];

            buckIdx[0] = 0;
            for(int i = 1; i < n; ++i) buckIdx[i] = buckIdx[i - 1] + num[i - 1];

            // place em in
            for(int a : suff) newVals[buckIdx[equiv[a]]++] = a;

            suff = newVals;

            setEquiv();
        }

        idxOf = vector<int>(n);
        for(int i = 0; i < n; ++i) idxOf[suff[i]] = i;
    }
    void setEquiv(){
        int eqIdx = 0;
        newVals[suff[0]] = 0;
        for(int i = 1; i < n; ++i){
            if(!eq(suff[i], suff[i - 1])) ++eqIdx;
            newVals[suff[i]] = eqIdx;
        }
        equiv = newVals;
    }
    bool eq(int a, int b){
        if(sortVal) return arr[a] == arr[b];
        return equiv[a] == equiv[b] && equiv[(a + (1 << k)) % n] == equiv[(b + (1 << k)) % n];
    }
    void genLCP(){
        int curr = 0;
        lcp = vector<int>(n - 1);
        for(int a = 0; a < n - 1; ++a){
            while(arr[a + curr] == arr[suff[idxOf[a] - 1] + curr]) ++curr;

            lcp[idxOf[a] - 1] = curr;

            if(curr > 0) --curr;
        }
    }
    void genRMQ(){
        rmq = RMQ(lcp);
    }
    // returns the LCP of suffix with index i and suffix with index j
    int getLCP(int i, int j){
        i = idxOf[i];
        j = idxOf[j];

        if(i == j) return INT_MAX;
        if(i > j) swap(i, j);
        return rmq.getMin(i, j - 1);
    }
    // returns the compareTo for two substrings
	int compare(int l1, int r1, int l2, int r2) {
		int len1 = r1 - l1 + 1, len2 = r2 - l2 + 1;
		int smallLen = min(len1, len2);

		if(getLCP(l1, l2) < smallLen) return idxOf[l1] - idxOf[l2];
		if(len1 != len2) return len1 - len2;
		// if they are the same substring, break ties on starting index:
		return l1 - l2;
	}
};
