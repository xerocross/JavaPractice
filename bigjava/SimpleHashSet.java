package bigjava;

import java.util.*;



public class SimpleHashSet<E> {

	class Node
	{
		public E data;
		public Node next;
		public Node(E data, Node node)
		{
			this.data = data;
			this.next = node;
		}
	}
	
	ArrayList<Node> buckets;
	
	public SimpleHashSet(int size)
	{
		buckets = new ArrayList<Node>(size);
		for (int i = 0; i < size; i++)
			buckets.add(i,null);
	}
	
	
	public void add(E key)
	{
		int bucketIndex = getBucketIndex(key);
		System.out.println("size :" + buckets.size() +" bucketIndex " + bucketIndex);
		
		Node<U,V> node = buckets.get(bucketIndex);
		Node<U,V> newNode = new Node<U,V>(arr, null);
		if (node == null) {
			System.out.println("creating the first element in bucket " + bucketIndex);
			buckets.set(bucketIndex, newNode);
		} else {
			System.out.println("something is already in bucket " + bucketIndex);
			Node<U,V> previous;
			System.out.println("about to run do loop inside add");
			do  
			{
				System.out.println("running do loop inside add");
				if (node.arrow.key.equals(key)) {
					System.out.println("key already in use");
					return;
				}
				previous = node;
				node=node.next;	
			} while (!(node == null));
			System.out.println("previous key: " + previous.arrow.key);
			System.out.println("newNode data: " + newNode.arrow.key + " : " + newNode.arrow.value);
			previous.next = newNode;
		}
		Node<U,V> testnode = buckets.get(bucketIndex);
		System.out.println("now testing add function");
		while (testnode != null) {
			System.out.println(testnode.arrow.key + " : " + testnode.arrow.value);
			testnode = testnode.next;
		}
		
		
		
	}
	
	
}
