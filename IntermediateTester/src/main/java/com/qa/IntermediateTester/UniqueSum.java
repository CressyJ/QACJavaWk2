package com.qa.IntermediateTester;
public class UniqueSum {
	public int check(int a, int b, int c) {
		//the numbers need to be unique, or they don't count
		if(a == b && b == c) return 0;
		if(a == b) return c;
		if(a == c) return b;
		if(b == c) return a;
		
		//now we've established that all numbers are equal
		return a+b+c;
	}
}