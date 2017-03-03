package bigjavatest;


import static org.junit.Assert.*;

import java.util.Set;

import bigjava.SimpleHashMap;
import bigjava.RandomString;
import org.junit.Test;



public class SimpleHashMapTest {

	@Test
	public void AddedDataShouldBeFoundbyGet() {
		System.out.println("%%%%%%%%%%%%%");
		SimpleHashMap<String,Integer> hm = new SimpleHashMap<String,Integer>(5);
		hm.add("Adam", 31);
		hm.add("Suhail", 18);
		hm.add("Mahdi",21);
		assertTrue(hm.get("Adam") == 31);
		assertTrue(hm.get("Mahdi")==21);
		assertTrue(hm.get("Suhail")==18);
	}
	
	@Test
	public void RemoveMethodShouldRemoveEntry() {
		System.out.println("%%%%%%%%%%%%%");
		SimpleHashMap<String,Integer> hm = new SimpleHashMap<String,Integer>(5);
		
		//case one
		//bucket contains only one item
		hm.add("Adam", 31);
		assertTrue(hm.get("Adam") == 31); //check that "Adam" was added
		hm.remove("Adam");
		assertTrue(hm.get("Adam") == null); //check that "Adam" was removed
		
		
		//case two
		//bucket contains more than one item, removing the first one
		hm.add("Mahdi", 31);
		hm.add("Suhail", 18);
		hm.add("Frank", 12);
		//these three should all be assigned to bucket 3
		hm.remove("Mahdi");
		assertTrue(hm.get("Mahdi")==null);
		assertTrue(hm.get("Suhail")==18);
		assertTrue(hm.get("Frank")==12);
		
		//case three
		//bucket contains three items, removing an interior item
		hm = new SimpleHashMap<String,Integer>(5);
		hm.add("Mahdi", 31);
		hm.add("Suhail", 18);
		hm.add("Frank", 12);
		hm.remove("Suhail");
		assertTrue(hm.get("Suhail")==null);
		assertTrue(hm.get("Mahdi")==31);
		assertTrue(hm.get("Frank")==12);
		
		//case four
		//bucket contains three items, removing the last one
		hm = new SimpleHashMap<String,Integer>(5);
		hm.add("Mahdi", 31);
		hm.add("Suhail", 18);
		hm.add("Frank", 12);
		hm.remove("Frank");
		assertTrue(hm.get("Suhail")==18);
		assertTrue(hm.get("Mahdi")==31);
		assertTrue(hm.get("Frank")==null);
		hm.add("Frank", 12);
		assertTrue(hm.get("Frank")==12);
	}
	
	@Test
	public void keySetTest()
	{
		SimpleHashMap<String,Integer> hm =  new SimpleHashMap<String,Integer>(5);
		hm.add("Mahdi", 31);
		hm.add("Suhail", 18);
		hm.add("Frank", 12);
		hm.add("Adam",12);
		hm.add("Lotfi",15);
		hm.add("Felicia",39);
		hm.add("Geordi",39);
		hm.add("Carly",39);
		hm.add("D",39);
		Set<String> keys = hm.keySet();
		assertTrue(keys.contains("Adam"));
		assertTrue(keys.contains("Mahdi"));
		assertTrue(keys.contains("Suhail"));
		assertTrue(keys.contains("Frank"));
		assertTrue(keys.contains("Felicia"));
		assertTrue(keys.contains("Geordi"));
		assertTrue(keys.contains("Carly"));
		assertTrue(keys.contains("D"));
		
	}
	
	
	@Test
	public void shouldUseAllBuckets()
	{
		//this test is not logically required to
		//pass, but it should usually pass.
		int size = 20;
		int numEntries = 5*size;
		SimpleHashMap<String,Integer> hm =  new SimpleHashMap<String,Integer>(size);
		RandomString rs = new RandomString(20);
		String string;
		int[] count = new int[size];
		
		for(int i = 0; i < numEntries; i++)
		{
			string = rs.nextString();
			int index = hm.getBucketIndex(string);
			System.out.println(string + " : " + index);
			count[index]++;
		}
		for (int i = 0; i < size; i++)
		{
			assertTrue(count[i] > 0);
		}
	}

}
