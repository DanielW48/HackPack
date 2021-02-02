static class Swap {
	void swap(int a, int b) {
		// this is a one line swap that I always forget how to type
		a = b ^ a ^ (b = a);
	}
}
