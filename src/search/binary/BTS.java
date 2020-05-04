package search.binary;


public class BTS implements Comparable<BTS> {
	private int key;
	
	public BTS(BTS key){
		this.key =key.key;
	}
	
	public int compareTo(BTS key) {
		if(this.key > key.key) return 1;
		if(this.key < key.key) return -1;
		else
			return 0;
	}

	

}
