public class SimpleMaths {
	public int add(int a, int b) {
		return a+b;
	}
	
	public int add2(int a, int b, boolean multiply) {
		if(multiply) return a+b;
		else return a*b;
	}
	
	public int add3(int a, int b, boolean multiply) {
		if(a == 0) return b;
		if(b == 0) return a;
		
		if(multiply) return a+b;
		else return a*b;
	}
}