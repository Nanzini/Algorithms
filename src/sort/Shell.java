package sort;

import java.util.ArrayList;

public class Shell extends AbstractSort{
	public static void sort(Comparable[] a) {
		int L = a.length;
		int count=0;
		int lastIndex;
		//h구하기 1 4 13 53...
		for(int tmp=1; tmp< L; tmp=3*tmp+1)
			count++;
		int[] h = new int[count];
		count=0;
		for(int tmp=1; tmp< L; tmp=3*tmp+1)
			h[count]=tmp;	count++;
		
		//i:sort수, j:배열인자
		//(L/(h[i])*h[i]+j) 배열 내 h만큼 최대 떨어진 거리. 즉, 부분배열 내 마지막 인자
		for(int i=h.length-1; i >= 0; i--){
			for(int j=0; j < L/h[i]; j++) {
				if((L/(h[i])*h[i]+j) < L)
					lastIndex = (L/(h[i])*h[i]+j);
				else {
					if(h[i] ==1)
						lastIndex=L-1;
					else
						lastIndex = (L/(h[i])*h[i]+j)-h[i];
				}
				
				//부분배열의 처음인자 지정.
				for(int current=h[i]+j; current<=lastIndex; current+=h[i]) {
					int currentLast = current;
					
					//m값(부분배열의 크기)으로 부분배열연쇄비교
					for(int m=0; m<=currentLast/h[i]; m++) {
						//비교는 마지막 인자~처음인자까지 모두
						if(less(a[currentLast],a[currentLast-h[i]]))
							exch(a,currentLast,currentLast-h[i]);
						currentLast-=h[i];
					}
				}
			}
		}
	}
	
}
