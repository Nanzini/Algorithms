package sort;

public class Count extends AbstractSort{
	
	public static void sort(Comparable[] a) {
		int L = a.length;
		Comparable max=a[0];
		
		//배열내 최대값 구하고 인자가 최대값인 배열만들기
		for(int i=1; i<L; i++)
			if(less(max,a[i]))
				max=a[i];
		int[] arr = new int[(int) max+1];
		for(int i=0; i<arr.length; i++)
			arr[i] =0;
		
		//배열 뭐가 몇개있는지 카운트
		for(int i=0; i<L; i++)
			arr[(int) a[i]]++;
		//누적배열
		int[] b = new int[arr.length];
		b[0] = arr[0];	b[1]=b[0]+arr[0];
		for(int i=1; i<arr.length; i++)
			b[i]=b[i-1]+arr[i];
		//원배열의 뒷값부터 추적해서
		Comparable[] complete = new Comparable[L];
		for(int i=L; i>0; i--) {
			complete [b[(int) a[i-1]] -1]=a[i-1];
			b[(int) a[i-1]]--;
		}
		show(complete);
		
	}

}
