package sort;

public class Selection extends AbstractSort{

	
	public static void sort(Comparable[] a) {
		for(int i=0; i<a.length; i++) {
			int now = i;
			for(int j=i+1; j<a.length; j++) {
				if(less(a[i],a[j]) == false) {
					i=j;j=i;
				}
			}
			exch(a,now,i);
			i=now;
		}
	}	
}
