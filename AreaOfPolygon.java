class AreaOfPolygon {
	public static void main(String[] args) {
		int n = 5;
		
		double[] x = new double[n], y = new double[n];
		
		double area = 0;
		
		for (int i = 0; i < n; i++) {
			if (i == n-1) {
				area += x[i] * y[0];
				area += y[i] * x[0];
			}
      		else {
				area += x[i] * y[i+1];
				area += y[i] * x[i+1];
			}
		}
		
		area /= 2.0;
	}
}
