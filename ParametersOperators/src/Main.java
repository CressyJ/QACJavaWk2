public class Main {
	public static void main(String[] args) {
		SimpleMaths sm = new SimpleMaths();
		
		//System.out.println(sm.add(1, 2));
		//System.out.println(sm.add2(1, 2, true));
		//System.out.println(sm.add2(1, 2, false));
		
		//for (int i = 0; i < 10; i++) System.out.println(sm.add3(i, 5, false));
		
		//int[] iterator = {0,1,2,3,4,5,6,7,8,9};
		//for(int i : iterator) System.out.println(i);
		//for(int i : iterator) System.out.println(sm.add3(i, 5, false));
		
		int[] numArray = new int[10];
		for(int i = 0; i < 10; i++) {
			int j = i+1;
			numArray[i] = j;
			System.out.print(numArray[i]);
			if(i < 9) System.out.print(", ");
			else System.out.print("\n");
		}
		for(int i : numArray) {
			System.out.print(sm.add3(i, 10, false));
			if(i < 10) System.out.print(", ");
			else System.out.print("\n");
		}
	}
}