package sort;

public class Insertion extends AbstractSort{

	public static void sort(Comparable[] a) {
		for(int p=1; p<a.length; p++)
			for(int j=p; j>0; j--)
				if(less(a[j-1],a[j]) == false)
					exch(a,j-1,j);
	}
}
