class AnagramCounting {
	public static void main(String[] args) {
	    char[] arr;
	    int[] cnt = new int[26*2]; //lowercase and uppercase latin letters
	    for(char curr : arr) {
	        if(curr >='a' && curr <='z') cnt[curr - 'a']++;
	        if(curr >='A' && curr <='Z') cnt[curr - 'A' + 26]++;
	    }
	    
	    BigInteger out = BigInteger.ONE;
	    int stack = 1;
	    for(int i =0 ; i < cnt.length; i++) {
	        if(cnt[i] != 0) {
	            BigInteger f = BigInteger.ONE;
	            for(int j = 1; j <= cnt[i]; j++) {
	                out = out.multiply(new BigInteger(stack + ""));
	                stack++;
	                f = f.multiply(new BigInteger(j + ""));
	            }
	            out = out.divide(f);
	        }
	    }
	}
}
