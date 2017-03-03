package bigjavatest;

import static org.junit.Assert.*;
import bigjava.SimpleHashMap;
import org.junit.Test;



public class SimpleHashMapTest {

	@Test
	public void createNewMaptest() {
		
		SimpleHashMap<String,Integer> hm = new SimpleHashMap<String,Integer>(5);
		hm.add("Adam", 21);
		int result = hm.get("Adam");
		assertTrue(result == 21);
	}

}
