# Algorithms

## 코드의 구성

먼저, `추상클래스`를 상속받아 `sort메서드`를 공용으로 사용할 수 있도록 설계되어 있고<br>
다운로드 후 각각의 정렬파일들을 호출해 사용할 수 있다.<br>
정렬 시 자주 쓰이는 기능(모듈)들 또한 자식클래스에서 사용할 수 있도록 설계하였다.<br>

- 비교모듈 : boolean less(Comparable a, Comparable b)
  - 왼쪽 인자 a가 작으면 true (오름차순)
- 출력모듈 : void show(Comparable[] a)
- 교환모듈 : void exch(Comparable[] a, int i,int j)
  - a[i] 와 a[j]를 교환
- 정렬확인모듈 : boolean isSorted(Comparable[] a)
  - 오름차순으로 모든 인자가 배열 되어있는 지 확인

<br>

    package sort;

      public abstract class AbstractSort {

      public static void sort(Comparable[] a) {};


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
    }

## 파일 사용방법

sort패키지를 설치 후 Eclipse로 실행한 후 사용한다
