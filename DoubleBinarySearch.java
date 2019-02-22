class DoubleBinarySearch {
	public static void main (String[] args){
	    double l = 0, h = 1e9 + 3;
	    for(int i = 0; i < 200; ++i){
	        double g = (l + h) / 2;
	        
	        //make sure to set bounds accordingly
	        if(go(g)) h = g;
	        else l = g;
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
