package search.binary;


public class BST<K extends Comparable<K>, V> {
	protected Node<K,V> root;
	
	public int size() {
		if(root!=null)
			return root.N;
		return -1;
	}
	
	public void rebalanceInsert(Node<K, V> newNode) {
		resetSize(newNode,1);
	}
	
	private void resetSize(Node<K, V> x, int val) {
		for(; x!= null; x=x.parent)
			x.N += val;
	}
	
	public void print() {
		inorder(root);
	}
	
	private void inorder(Node<K,V> x) {
		if(x != null) {
			inorder(x.left);
			System.out.println(x.key + " : " + x.value);
			inorder(x.right);
		}
	}
	
	
	public V get(K key) {
		if(root == null)return null;
		Node<K,V> x = treeSearch(key);
		if(x.key.equals(key))
			return x.value;
		else
			return null;
	}
	
	public Node<K,V> treeSearch(K key){
		Node<K,V> x = root;
		while(x != null) {
			int cmp = x.key.compareTo(key);
			if(cmp==0) return x;
			else if(cmp > 0) {
				if(x.left==null)	return x;
				else	x=x.left;
			}
			else {
				if(x.right == null)	return x;
				else	x=x.right;
			}
		}
		return null;
	}
	
	public void put(K key, V value) {
		if(root == null) {
			root = new Node<K,V>(key,value);	return ;
		}
		Node<K,V> newNode = new Node<K,V>(key,value);
		Node<K,V> x = treeSearch(key);
		int cmp = x.key.compareTo(key);
		
		if(cmp ==0)	x.value=value;
		else {
			if(cmp >0) x.left= newNode;
			else	x.right = newNode;
		newNode.parent = x;
		rebalanceInsert(newNode);
		}
			
		
		
		
	}
	public Iterable<K> keys(){
		return null;
		
	}
	public void delete(K key) {
		
	}
	
	
	
	
}
