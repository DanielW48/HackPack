class AreaOfPolygon {
	public static void main(String[] args) {
	    int n;
	    double[] x = new double[n], y = new double[n];
	    
	    double area = 0;
	    for (int i = 0; i < n; i++) {
	        double x1 = x[i], y1 = y[i];
	        double x2 = x[(i + 1) % n], y2 = y[(i + 1) % n];
	        area += (x2 - x1) * (y1 + y2);
	    }
	    area /= 2;
	    
	    //if counterclockwise, then
	    area = -area;
	}
}
