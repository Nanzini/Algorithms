package sort;

import java.util.ArrayList;

public abstract class AbstractSort {

	
	public static void sort(Comparable[] a) {}
		

	protected static void show(ArrayList t1) {
		for(int i=0; i<t1.size(); i++)
			System.out.print(t1.get(i)+" ");
		System.out.println();
	}
	
	protected static void show(Comparable[] a) {
		for(int i=0; i<a.length; i++)
			System.out.println(a[i]+" ");
		System.out.println();
	}
	
	protected static void exch(Comparable[] a, int i,int j) {
		Comparable tmp=a[i];	a[i]=a[j];	a[j]=tmp;
		System.out.println(a[i]+"와"+a[j]+"바뀐다.");
	}
	
	protected static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b)<0;	//a가 작으면 true반환
	}
	

	protected static boolean isSorted(Comparable[] a) {
		for(int i=0; i<a.length; i++) 
			if(!less(a[i],a[i+1])) return false;
		return true;
	}
	
	protected static Comparable getMax(Comparable[] a) {		
		Comparable max = a[0];
		for(int i=1; i<a.length; i++)
			if(less(max,a[i]))
				max=a[i];
		return max;
	}
}
