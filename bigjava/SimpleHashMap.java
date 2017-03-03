package bigjava;

import java.util.*;


interface Key
{
	public boolean equals(Object o);
}



class Arrow <U,V>
{
	public U key;
	public V value;
	
	public Arrow(U key, V value) 
	{
		this.key = key;
		this.value = value;
	}
}

class Node<U,V>
{
	public Arrow<U,V> arrow;
	public Node<U,V> next;
	public Node(Arrow<U,V> arr, Node<U,V> node)
	{
		this.arrow = arr;
		this.next = node;
	}
}

public class SimpleHashMap<U,V>
{

	
	private ArrayList<Node<U,V>> buckets;
	private int size;
	
	public SimpleHashMap(int size)
	{
		this.size = size;
		buckets = new ArrayList<Node<U,V>>(size);
		for (int i = 0; i < size; i++)
			buckets.add(null);
		
		
		System.out.println("size :" + size + " buckets has been created and the size is " + buckets.size());
	
	}
	
	private int hash (U key)
	{
		if (key == null)
			return 0;
		else
			return key.hashCode();
	}

	public void add(U key, V value)
	{
		int hashVal = hash(key);
		int bucketIndex = hashVal % size;
		System.out.println("size :" + buckets.size() +" bucketIndex " + bucketIndex);
		Arrow<U,V> arr = new Arrow<U,V>(key, value);
		Node<U,V> node = buckets.get(bucketIndex);
		Node<U,V> newNode = new Node<U,V>(arr, null);
		if (node == null)
			buckets.set(bucketIndex, newNode);
		else {
			while (node.next != null) 
			{
				if (node.arrow.key.equals(key)) {
					System.out.println("key already in use");
					return;
				}
				node=node.next;	
			}
			
			node.next = newNode;
		}
	}
	
	public V get(U key)
	{
		int hashValue = hash(key);
		int bucketIndex = hashValue % size;
		Node<U,V> node = buckets.get(bucketIndex);
		if (node == null)
			return null;
		else if (node.arrow.key.equals(key))
			return node.arrow.value;
		while (node.next != null) 
		{
			if (node.arrow.key.equals(key))
				return node.arrow.value;
			node = node.next;
		}
		return null;
	}
	
}
