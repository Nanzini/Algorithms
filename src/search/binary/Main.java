package search.binary;

public class Main {

	public static void main(String[] args) {
		BST bst = new BST();
		bst.put("3", "����");
		bst.put("5", "����");
		bst.put("1", "����");
		bst.put("2", "��ũ��");
		bst.put("0", "����");
		
		
		int a = bst.size();
		System.out.println(a);
		bst.print();
		
		
	}

}
