class AreaOfPolygon {
	public static void main(String[] args) {
		int n = 5;
		
		double[] x = new double[n], y = new double[n];
		
		double area = 0;
		
		for (int i = 0; i < n; i++) {
			area += x[i] * y[(i + 1) % n];
			area += y[i] * x[(i + 1) % n];
		}
		
		area /= 2;
	}
}
