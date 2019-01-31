public class DoubleBinarySearch {
	static double binSearch(){
	    double l = 0, h = 1e9;
	    double prev = -1;
	    int count = 0;
	    while(h - l >= 1e-6){
	        double g = (l + h) / 2;
	        
	        if(g == prev){
	            if(++count == 10) break;
	        }
	        
	        if(go(g)) l = g;
	        else h = g;
	        
	        prev = g;
	    }
	    
	    prev = l;
	    double add = 1e-6;
	    for(double g = l; g <= h; g += add){
	        if(prev == g) add *= 10;
	        else add = 1e-6;
	        if(go(g)) l = g;
	    }
	    
	    return l;
	}
	static boolean go(double g){
      //put boolean statement here
      return true;
	}
}
