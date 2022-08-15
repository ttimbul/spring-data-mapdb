package org.springframework.data.mapdb.query.predicates;

import org.junit.Test;

import java.time.LocalDate;
import java.util.function.Predicate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GreaterLessPredicateTests {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testLess() {
		Predicate predicate = GreaterLessPredicate.ls(10L);

		assertTrue(predicate.test(5));
		assertFalse(predicate.test(10));
		assertFalse(predicate.test(15));

		assertTrue(predicate.test(8));
		assertFalse(predicate.test(12));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testGreater() {
		Predicate predicate = GreaterLessPredicate.gr(10);

		assertTrue(predicate.test(15));
		assertFalse(predicate.test(10));
		assertFalse(predicate.test(5));

		assertTrue(predicate.test(18));
		assertFalse(predicate.test(2));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testLessOrEqual() {
		Predicate predicate = GreaterLessPredicate.le(10L);

		assertTrue(predicate.test(5L));
		assertTrue(predicate.test(10L));
		assertFalse(predicate.test(15L));

		assertTrue(predicate.test(8L));
		assertTrue(predicate.test(10L));
		assertFalse(predicate.test(12L));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testGreaterOrEqual() {
		Predicate predicate = GreaterLessPredicate.ge(10);

		assertTrue(predicate.test(15));
		assertTrue(predicate.test(10));
		assertFalse(predicate.test(5));

		assertTrue(predicate.test(18));
		assertTrue(predicate.test(10));
		assertFalse(predicate.test(2));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testGreaterOrEqualDate() {
		Predicate predicate = GreaterLessPredicate.ge(LocalDate.now());

		assertTrue(predicate.test(LocalDate.now().plusYears(10)));
		assertFalse(predicate.test(LocalDate.now().minusYears(10)));

	}

}
