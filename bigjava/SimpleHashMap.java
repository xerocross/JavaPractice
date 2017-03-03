package bigjava;

import java.util.*;

/*
 * This is a simplified, amateur version of a
 * hash map implemented for practice.  It is
 * not intended for production use.
 */

public class SimpleHashMap<U,V>
{
	private ArrayList<Node> buckets;
	private int size;
	
	private class Arrow
	{
		public U key;
		public V value;
		
		public Arrow(U key, V value) 
		{
			this.key = key;
			this.value = value;
		}
	}
	
	private class Node
	{
		public Arrow arrow;
		public Node next;
		public Node previous;
		public Node(Arrow arr, Node next, Node previous)
		{
			this.arrow = arr;
			this.next = next;
			this.previous = previous;
		}
	}
	
	public SimpleHashMap(int size)
	{
		this.size = size;
		buckets = new ArrayList<Node>(size);
		for (int i = 0; i < size; i++)
			buckets.add(null);
	
	}
	
	private int hash (U key)
	{
		if (key == null)
			return 0;
		else 
		{
			return key.hashCode();
		}
	}

	public int getBucketIndex(U key) {
		int bucketIndex = hash(key) % size;
		if (bucketIndex < 0)
			bucketIndex+=size;
		return bucketIndex;
	}
	
	private Node getNode(U key) 
	{
		int bucketIndex = getBucketIndex(key);
		Node node = buckets.get(bucketIndex);
		while (node != null) {
			if (node.arrow.key.equals(key))
				return node;
			node = node.next;
		}
		return null;
	}
	
	public void remove (U key)
	{
		int bucketIndex = getBucketIndex(key);
		Node node = getNode(key);
		Node previous = node.previous;
		Node next = node.next;
		if (previous == null)
			buckets.set(bucketIndex, next);
		else {
			previous.next = next;
		}
	}
	
	public void put(U key, V value)
	{
		int bucketIndex = getBucketIndex(key);
		Arrow arr = new Arrow(key, value);
		System.out.println("Adding " + key + " : " + value + " to bucket " + bucketIndex);
		Node node = buckets.get(bucketIndex);
		Node newNode = new Node(arr, null,null);
		if (node == null) {
			buckets.set(bucketIndex, newNode);
		} else {
			Node previous;
			do  
			{
				if (node.arrow.key.equals(key)) {
					System.out.println("key already in use");
					return;
				}
				previous = node;
				node=node.next;	
			} while (node != null);
			previous.next = newNode;
			newNode.previous = previous;
		}
	}
	

	public V get(U key)
	{
		Node node = getNode(key);
		if (node == null)
				return null;
		else
			return node.arrow.value;
	}
	
	public Set<U> keySet()
	{
		int bucketIndex = 0;
		Set<U> hs = new HashSet<U>();
		Node node;
		for (; bucketIndex < size; bucketIndex++)
		{
			node = buckets.get(bucketIndex);
			while (node != null) {
				hs.add(node.arrow.key);
				node = node.next;
			}
		}
		return hs;
	}
	
	
}
