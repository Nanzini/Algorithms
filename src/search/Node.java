package search;

//Map타입으로, K:V의 형태. 자료형은 Object Node< , >형태이다
class Node<K,V>{
	K key; V value;	Node<K,V> next;
	
	public Node(K key, V value, Node<K,V> next){
		this.key = key; this.value=value; 
		this.next= next; 
	}
}

