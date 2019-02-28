//calculating the nth fibonacci number (0-indexed) under mod 1e9+7
class MatrixExponentiation {
	static final int MOD=1000000007;
	static long[][] identity;
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		long n=scan.nextLong();
		long[][] matrix={
				{1,1},
				{1,0}
		};
		long[][] start={
				{1},
				{1}
		};
		identity=new long[2][2];
		identity[0][0]=identity[1][1]=1;
		if(n==0||n==1) {
			System.out.println(1);
			return;
		}
		matrix=exp(matrix,n-1);
		
		matrix=mult(matrix,start);
		System.out.println(matrix[0][0]);
	}
	public static long[][] exp(long[][] mat, long e) {
		if(e==0) return identity;
		if(e%2==0) {
			long[][] temp=mult(mat,mat);
			return exp(temp,e/2);
		}
		else {
			return mult(mat,exp(mat,e-1));
		}
	}
	public static long[][] mult(long[][] a, long[][] b) {
		long[][] res=new long[a.length][b[0].length];
		for(int i=0;i<res.length;i++) {
			for(int j=0;j<res[0].length;j++) {
				for(int k=0;k<a[0].length;k++) {
					res[i][j]=(res[i][j]+a[i][k]*b[k][j])%MOD;
				}
			}
		}
		return res;
	}
}
