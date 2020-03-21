package sort;

public class Integer implements Comparable<Integer> {
	private int a;
	
	@Override
	public int compareTo(Integer that) {
		if(this.a < that.a) return -1;
		if(this.a > that.a) return 1;
		return 0;
	}

}
