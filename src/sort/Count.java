package sort;

public class Count extends AbstractSort{
	
	public static void sort(Comparable[] a) {
		int L = a.length;
		int[] cntArr;
		
		//�迭�� �ִ밪 ���ϰ� ���ڰ� �ִ밪�� �迭�����
		Comparable max=getMax(a);
		cntArr= cntArr(max);
		
		//�迭 ���� ��ִ��� ī��Ʈ
		for(int i=0; i<L; i++)
			cntArr[(int) a[i]]++;
		//�����迭
		int[] b = new int[cntArr.length];
		b[0] = cntArr[0];	b[1]=b[0]+cntArr[0];
		for(int i=1; i<cntArr.length; i++)
			b[i]=b[i-1]+cntArr[i];
		
		//���迭�� �ް����� �����ؼ�
		Comparable[] complete = new Comparable[L];
		for(int i=L-1; i>=0; i--) {
			complete [b[(int) a[i]] -1]=a[i];
			b[(int) a[i]]--;
		}
		show(complete);
		
	}

	protected static int[] cntArr(Comparable max) {
		int[] arr = new int[(int) max+1];
		for(int i=0; i<arr.length; i++)
			arr[i] =0;
		return arr;
	}
}
