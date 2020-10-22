class ZValues {
	// arr can be any type that uses ==
	static int[] getZVals(char[] arr) {
		int n = arr.length;
		int[] z = new int[n];
		z[0] = n;
		int l = 0, r = 0;
		for(int i = 1; i < n; ++i){
			if(i > r){
				l = r = i;
				while(r < n && arr[r - l] == arr[r]) ++r;
				z[i] = r - l;
				--r;
			}
			else{
				int k = i - l;
				if(z[k] < r - i + 1) z[i] = z[k];
				else{
					l = i;
					while(r < n && arr[r - l] == arr[r]) ++r;
					z[i] = r - l;
					--r;
				}
			}
		}
		return z;
	}
}
