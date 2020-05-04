package search;

import java.util.ArrayList;
import java.util.Iterator;

public class Search<K,V> {
	private Node<K,V> first;
	int N=0;	//Node¼ö
	
	
	
	
	public V get(K key) {
		for(Node<K,V> x = first; x != null; x = x.next)
			if(key.equals(x.key))
				return x.value;
		return null;
	}
	
	public void put(K key, V value) {
		Node<K,V> newNode = new Node<K,V>(key,value,first);
		first = newNode;	
		N++;
	}
	
	public void delete(K key) {
		if(first.key.equals(key)) {
			first = first.next; N--;
			return;
		}

		for(Node<K,V> x = first; x.next != null; x = x.next) {
			if(key.equals(x.next.key)) {
				x.next = x.next.next; N--;
				return;
			}
			
		}
	}
	
	public Iterable<K> keys(){
		ArrayList<K> tmp = new ArrayList<K>(N);
		for(Node<K,V> x = first; x!=null; x=x.next)
			tmp.add(x.key);
	return tmp;
	}
	
	public boolean contains(K key) {return get(key) != null;}
	
	public boolean isEmpty() {return false;}
	public int size() {return N;}
	
	public void print() {
		for(Node<K,V> x = first; x!=null; x=x.next)
			System.out.println( x.key +" "+get(x.key));
	}
}
