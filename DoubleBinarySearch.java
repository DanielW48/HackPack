class DoubleBinarySearch {
	public static void main (String[] args){
	    double l = 0, h = 1e9 + 3, prev = -1;
	    int count = 0;
	    while(h - l >= 1e-8){
	        double g = (l + h) / 2;
	        
	        if(g == prev){
	            if(++count == 70) break;
	        }
	        
	        //make sure to set bounds accordingly
	        if(go(g)) h = g;
	        else l = g;
	        
	        prev = g;
	    }
	    
	    if(l > 1e9 + 1){
	        //impossible
	    }
	    
	    double out = l;
	}
	static boolean go(double in){
	    //put check function here
	    return true;
	}
}
