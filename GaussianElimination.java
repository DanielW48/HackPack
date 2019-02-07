public class GaussianElimination {
	static int n;
	static double[][] mat;
	static double[] ans;
	public static void main(String[] args) {
		
	}
	static void solve(){
		for(int j = 0; j < n - 1; ++j){
			for(int i = n - 1; i > j; --i){

				if(equals(mat[i - 1][j], 0)){
					swap(i, i - 1);
					continue;
				}

				double ratio = -(mat[i][j] / mat[i - 1][j]);
				for(int k = 0; k < n + 1; ++k){
					mat[i][k] += mat[i - 1][k] * ratio;
				}
			}
		}

		for(int i = n - 1; i >= 0; --i){
			double add = 0;
			for(int j = i + 1; j < n; ++j){
				add += ans[j] * mat[i][j];
			}
			double curr = (mat[i][n] - add) / mat[i][i];
			ans[i] = curr;
		}
	}
	static void swap(int r1, int r2){
		for(int j = 0; j < n + 1; ++j){
			double temp = mat[r1][j];
			mat[r1][j] = mat[r2][j];
			mat[r2][j] = temp;
		}
	}
	static boolean equals(double a, double b){
		return Math.abs(a - b) <= 1e-6;
	}
}
