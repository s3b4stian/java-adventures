package it.seba.metodologie.ex07_multimappa;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class TestMultiMappa {

	private MultiMappa<String, Integer> mMap01;
	private MultiMappa<String, Integer> mMap02;

	@BeforeEach
	void resetObject() {
		// create new instance
		mMap01 = new MultiMappa<String, Integer>();
		mMap02 = new MultiMappa<String, Integer>();
	}

	@Test
	void newVoidInstanceTest() {

		MultiMappa<String, Integer> mMap = new MultiMappa<String, Integer>();

		assertTrue(mMap instanceof MultiMappa);
		assertTrue(mMap instanceof Iterable);
	}

	@Test
	void newElementInstanceTest() {

		MultiMappa<String, Integer> mMap = new MultiMappa<String, Integer>("key_1", 5);

		assertTrue(mMap instanceof MultiMappa);
		assertTrue(mMap instanceof Iterable);
		assertTrue(mMap.hasKey("key_1"));
		assertTrue(mMap.get("key_1") instanceof Collection);
	}

	@Test
	void newListInstanceTest() {

		List<Integer> list = new ArrayList<Integer>();
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);

		MultiMappa<String, Integer> mMap = new MultiMappa<String, Integer>("key_1", list);

		assertTrue(mMap instanceof MultiMappa);
		assertTrue(mMap instanceof Iterable);
		assertTrue(mMap.hasKey("key_1"));
		assertTrue(mMap.get("key_1") instanceof Collection);
	}

	@Test
	void newPutTest() {

		mMap01.put("key_1", 5);
		mMap01.put("key_1", 6);
		mMap01.put("key_1", 7);
		mMap01.put("key_1", 8);
		mMap01.put("key_1", 9);
		mMap01.put("key_1", 10);

		assertTrue(mMap01.hasKey("key_1"));

		List<Integer> list = (List<Integer>) mMap01.get("key_1");

		assertEquals(list.get(0), 5);
		assertEquals(list.get(1), 6);
		assertEquals(list.get(2), 7);
		assertEquals(list.get(3), 8);
		assertEquals(list.get(4), 9);
		assertEquals(list.get(5), 10);
	}
	
	@Test
	void newPutCollectionTest() {

		List<Integer> list = new ArrayList<Integer>();
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(10);
		
		mMap01.put("key_1", list);

		assertTrue(mMap01.hasKey("key_1"));

		List<Integer> listToTest = (List<Integer>) mMap01.get("key_1");

		assertEquals(listToTest.get(0), 5);
		assertEquals(listToTest.get(1), 6);
		assertEquals(listToTest.get(2), 7);
		assertEquals(listToTest.get(3), 8);
		assertEquals(listToTest.get(4), 9);
		assertEquals(listToTest.get(5), 10);
	}

}
