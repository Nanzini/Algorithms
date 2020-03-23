package sort;

import java.util.ArrayList;

public class Shell extends AbstractSort{
	public static void sort(Comparable[] a) {
		int L = a.length;
		int count=0;
		int lastIndex;
		//h���ϱ� 1 4 13 53...
		for(int tmp=1; tmp< L; tmp=3*tmp+1)
			count++;
		int[] h = new int[count];
		count=0;
		for(int tmp=1; tmp< L; tmp=3*tmp+1)
			h[count]=tmp;	count++;
		
		//i:sort��, j:�迭����
		//(L/(h[i])*h[i]+j) �迭 �� h��ŭ �ִ� ������ �Ÿ�. ��, �κй迭 �� ������ ����
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
				
				//�κй迭�� ó������ ����.
				for(int current=h[i]+j; current<=lastIndex; current+=h[i]) {
					int currentLast = current;
					
					//m��(�κй迭�� ũ��)���� �κй迭�����
					for(int m=0; m<=currentLast/h[i]; m++) {
						//�񱳴� ������ ����~ó�����ڱ��� ���
						if(less(a[currentLast],a[currentLast-h[i]]))
							exch(a,currentLast,currentLast-h[i]);
						currentLast-=h[i];
					}
				}
			}
		}
	}
	
}
