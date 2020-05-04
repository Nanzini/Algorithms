package search.binary;

public class Main {

	public static void main(String[] args) {
		BST bst = new BST();
		bst.put("3", "헤헤");
		bst.put("5", "헤헤");
		bst.put("1", "헤헤");
		bst.put("2", "세크스");
		bst.put("0", "헤헤");
		
		
		int a = bst.size();
		System.out.println(a);
		bst.print();
		
		
	}

}
