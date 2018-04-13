package com.qa.IntermediateTester;

import static org.junit.Assert.*;

import org.junit.Test;

public class IntermediateTester {
	@Test
	public void blackjackTest() {
		Scorer s = new Scorer();
		
		assertEquals(0, s.blackjack(22, 22));
		assertEquals(21, s.blackjack(21, 22));
		assertEquals(21, s.blackjack(22, 21));
		assertEquals(21, s.blackjack(21, 21));
		assertEquals(21, s.blackjack(21, 20));
		assertEquals(20, s.blackjack(19, 20));
	}
	
	@Test
	public void uniqueSumTest() {
		UniqueSum us = new UniqueSum();
		
		assertEquals(0, us.check(1, 1, 1));
		assertEquals(2, us.check(1, 1, 2));
		assertEquals(2, us.check(1, 2, 1));
		assertEquals(2, us.check(2, 1, 1));
		assertEquals(6, us.check(1, 2, 3));
	}
	
	@Test
	public void tempReaderTest() {
		TempReader tr = new TempReader();
		
		assertFalse(tr.idealTemp(59, true));
		assertTrue(tr.idealTemp(60, true));
		assertTrue(tr.idealTemp(100, true));
		assertFalse(tr.idealTemp(100, false));
	}
}