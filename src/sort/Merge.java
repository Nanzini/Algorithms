package sort;

public class Merge extends AbstractSort{

	public static void sort(Comparable[] a) {
		//�迭�� 2���� ������
		int N = a.length;
		int mid= N/2;
		int i=0, j=mid;
		Comparable[] arr = new Comparable[N];
		
		//�� �迭�� ���Ѵ�
		for(int k=0; k<N; k++) {
			if(i >= mid) {	arr[k]=a[j];	j++;}
			else if(j >= N) {	arr[k]=a[i];	i++;}
			else if(less(a[i],a[j])) {	arr[k]=a[i];	i++;}	
			else {	arr[k] = (int) a[j];	j++;}
		}
		show(arr);
	}
	
}
