package sort;

import java.util.ArrayList;

public class Radix extends AbstractSort{

	public static void sort(int[] a) {
		int d=0;	//천자리수인지 백의자리인지.
		int L = a.length;
		int[] cntArr = new int[10];
		int[] b = new int[10];	//누적배열
		int[] c = new int[L];
		int[] tmp = new int[L];
		
		
		//최대값찾기
		int max=getMax(a);
		initArr(tmp,a);
		
		//d 최대자리수 구하기 100자리면 3, 1000자리면 4 나옴.
		for(int n=1; max>=n; n*=10)
			d++;
		
		//누적b초기화
		initArr(b);
		
		//1의자리일때 + 10의자리일때 + 100의자리일때
		for(int i=0; i<d; i++) {
			//cntArr카운팅
			initArr(cntArr);
			for(int j=L-1; j>= 0; j--)
				cntArr[ (tmp[j]/(int)Math.pow(10,i)%10)]++;
			//누적 b 카운팅
			b[0] = cntArr[0];
			for(int k=1; k<b.length; k++)
				b[k]=cntArr[k]+b[k-1];
			//새로운 배열에 뒤애서 부터 넣기
			for(int l=L-1; l>=0; l--) {
				int a1 = tmp[l];
				c[b[(tmp[l]/(int)Math.pow(10,i)%10)]-1] =a1;
				b[(tmp[l]/(int)Math.pow(10,i)%10)]--;
			}
			initArr(tmp,c);
		}
		initArr(a,c);
	}
	
	
	protected static void show(int[] a) {
		for(int i=0; i<a.length; i++)
			System.out.println(a[i]+" ");
		System.out.println();
	}
	protected static int[] initArr(int[] arr1,int[] arr2) {
		if(arr1.length == arr2.length)
			for(int i=0; i<arr1.length; i++)
				arr1[i] = arr2[i];
		return arr2;
	}
	
	protected static int[] initArr(int[] arr) {
		for(int i=0; i<arr.length; i++)
			arr[i] =0;
		return arr;
	}
	protected static int getMax(int[] a) {		
		int max = a[0];
		for(int i=1; i<a.length; i++)
			if(less(max,a[i]))
				max=a[i];
		return max;

	}
}