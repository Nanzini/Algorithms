package sort;

public abstract class AbstractSort {

	
	public static void sort(Comparable[] a) {}
		
	
	
	protected static void show(Comparable[] a) {
		for(int i=0; i<a.length; i++)
			System.out.println(a[i]+" ");
		System.out.println();
	}
	
	protected static void exch(Comparable[] a, int i,int j) {
		Comparable tmp=a[i];	a[i]=a[j];	a[j]=tmp;
		System.out.println(a[i]+"��"+a[j]+"�ٲ��.");
	}
	
	protected static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b)<0;	//a�� ������ true��ȯ
	}
	

	protected static boolean isSorted(Comparable[] a) {
		for(int i=0; i<a.length; i++) 
			if(!less(a[i],a[i+1])) return false;
		return true;
	}
	
}
