package search.binary;

public class Node<K,V> {
	int N;
	int aux;
	Node<K,V> parent;
	K key; V value;
	Node<K,V> left,right;
	
	public Node(K key,V value) {
		this.key=key;	this.value=value;
		this.N=1;	left = right= null;
	}


	
}
