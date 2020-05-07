package hash;

import java.util.Random;
import java.util.Scanner;

public class HW3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("난수의 seed값과 심볼 테이블 크기를 입력: ");
		long seed = sc.nextLong();
		int size = sc.nextInt();
		
		LinearProbingHashST<Integer,Integer> lht = new LinearProbingHashST<>(size);
		DoubleHashingST<Integer,Integer> dht = new DoubleHashingST<>(size);
		CuckooHashingST<Integer,Integer> cht = new CuckooHashingST<>(size, 0.45);
		Random rand = new Random(seed);
		
		int lcount = 0, dcount = 0, ccount = 0;
		
		for (int i = 0; i < size * 0.45; i++) {
			int key = rand.nextInt(), value;
			if (lht.contains(key))
				value = lht.get(key) + 1;
			else value = 1;
			
			lcount += lht.put(key, value);
			dcount += dht.put(key, value);
			ccount += cht.put(key, value);
			
		}
			
		System.out.println("Linear Probing: put count = " + lcount);
		lht.print();
		
		System.out.println("\nDouble Hashing: put count = " + dcount);
		dht.print();
		
		System.out.println("\nCuckoo Hashing: put count = " + ccount);
		cht.print();

		sc.close();
	}
	
	
}


class LinearProbingHashST<K, V>{

	private int N;
	private int M;
	private K[] keys;
	private V[] vals;
	
	public LinearProbingHashST() {this(31);}
	public LinearProbingHashST(int M) {
		int prime=getPrime(M);
		keys=(K[]) new Object[prime];
		vals=(V[]) new Object[prime];
		this.M=prime;
	}

	private int getPrime(int M) {
		  int count=0;

          for(int i=M+1; true; i++){
             for(int j=2; j<=i; j++)
                if(i%j ==0)	count ++;
              	if(count==1)
           	   		return i;
              
             count=0;
          }
	}
	
	public boolean contains(K key) { return get(key) != null;}
	public boolean isEmpty() {	return N ==0;}
	public int size() {return N;}
	
	private int hash(K key) {	return (key.hashCode() & 0x7fffffff)%M;}
	
	
	public V get(K key) {
		for(int i=hash(key); keys[i]!=null; i=(i+1)%M)
			if(keys[i].equals(key))	
				return vals[i];
		return null;	
	}
	
	public int put(K key,V value) {
		if(N >= M/2)	resize(2*M+1);
		int i, count=1;
		for(i=hash(key); keys[i]!=null; i=(i+1)%M) {	
			count++;
			if(keys[i].equals(key)) {	
				vals[i] = value;
				return count;
			}	
		}
		keys[i] = key;
		vals[i] = value;
		N++;
		return count;
		
	}
	
	private void resize(int cap) {
		LinearProbingHashST<K, V> t;
		t=new LinearProbingHashST<>(cap);
		for(int i=0; i<M; i++)
			if(keys[i] != null)
				t.put(keys[i], vals[i]);
		keys = t.keys;
		vals = t.vals;
		M=t.M;
	}
	
	public void print() {
		System.out.println("	테이블 크기 : "+M);
		System.out.println("	저장된 (Key, Value)쌍의 수 : "+N);
		System.out.println("	평균 검색길이 : "+ avlength());
		getMaxCost();
		
	}
	
	private int getCost(K key) {
		int cost=0;
		for(int home=hash(key); keys[home]!=null; home=(home+1)%M) {	
			cost++;
			if(keys[home].equals(key))
				return cost;
		}
		return -1;
	}
	
	private double avlength() {
		double sum=0;
		for(int i=0; i<keys.length; i++)
			if(keys[i] != null)
				if(getCost(keys[i]) != -1)
					sum+= getCost(keys[i]);
		return sum/N;
	}
	
	
	private void getMaxCost() {
		int mLeng=1, current; 
		K address = null;
		for(int i=0; i<keys.length; i++)
			if(keys[i] != null) {
				if( getCost(keys[i]) != -1) {
					current = getCost(keys[i]);
					if(current > mLeng) {
						mLeng = current;
						address = keys[i];
					}
				}
			}
		
		System.out.println("	최대검색길이 : " + mLeng+" (key = "+ address+")"); 
	}
	
}


class DoubleHashingST<K,V> {

	private int N;
	private int M;
	private int M1;
	private K[] keys;
	private V[] vals;
	
	public DoubleHashingST() {	this(31);	}
	public DoubleHashingST(int size) {
		int prime=nextPrime(size);
		keys=(K[]) new Object[prime];
		vals=(V[]) new Object[prime];
		M=prime;
		prime = beforePrime(M);
		M1 = prime;
	}
	

	public boolean isEmpty() {	return N ==0;}
	public int size() {return N;}
	

	
	public int put(K key, V value) {
		
		int i, count=1;
		for(i=hash1(key); keys[i]!=null; i=hash3(key,count-1)) {
			count++;
			if(keys[i].equals(key)) {	
				vals[i] = value;
				return count;
			}	
		}
		keys[i] = key;
		vals[i] = value;
		N++;
		return count;
	}
	
	private int hash1(K key) {	return (key.hashCode() & 0x7fffffff)%M;}
	private int hash2(K key) { 	return  1 + ((key.hashCode() & 0x7fffffff) % M1);	}
	private int hash3(K key, int i) { 	return   (hash1(key)+i*(hash2(key)) & 0x7fffffff) % M;}
	
	

	
	private int nextPrime(int M) {
		int count=0;
        for(int i=M+1; true; i++){
           for(int j=2; j<=i; j++)
              if(i%j ==0)	count ++;
            	if(count==1){
         	   		return i;
            }
           count=0;
        }
	}
	
	private int beforePrime(int M) {
		int count=0;
		for(int i=M-1; true; i--){
			for(int j=2; j<=i; j++)
				if(i%j ==0)	count ++;
          			if(count==1){
          				return i;
          			}
          	count=0;
      }
	}
	
	private int getCost(K key) {
		int cost=0;
		for(int home=hash1(key); keys[home]!=null; home=hash3(key,cost)) {
			cost++;
			if(keys[home].equals(key))
				return cost;
		}
		return -1;
	}
		
	
	public void print() {
		System.out.println("	테이블 크기 : "+M);
		System.out.println("	저장된 (Key, Value)쌍의 수 : "+N);
		System.out.println("	평균 검색길이 : "+ avlength());
		getMaxCost();
	}
	
	private double avlength() {
		double sum=0;
		for(int i=0; i<keys.length; i++)
			if(keys[i] != null)
				sum+= getCost(keys[i]);
		return sum/N;
	}

	
	private void getMaxCost() {
		int mLeng=1, current; 
		K address = null;
		
		for(int i=0; i<keys.length; i++) {
			if(keys[i] != null) {
				if(getCost(keys[i]) != -1) {
					current = getCost(keys[i]);
					if(current > mLeng) {
						mLeng = current;
						address = keys[i];
					}
				}
			}
		}
		System.out.println("	최대검색길이 : " + mLeng+" (key : "+ address +")"); 
	}
	
	
}


class CuckooHashingST<K,V> {

	private int N=0;
	private int M, M1, M2;
	private int count=0;
	private double fillFactor;
	private double e;
	private double maxloop;
	
	private K[] keys1;
	private V[] vals1;
	private K[] keys2;
	private V[] vals2;
	
	
	
	public CuckooHashingST(){}
	public CuckooHashingST(int size, double fillFactor){
		int prime=getPrime(size);
		M=prime;
		M1 = getPrime(M/2+1);
		M2 = M-M1;
		
		fillFactor=fillFactor;
		e = 0.5-fillFactor;
		maxloop=(3*baseLog(fillFactor*size,1+e));
		
		keys1=(K[]) new Object[M1];
		vals1=(V[]) new Object[M1];
		keys2=(K[]) new Object[M2];
		vals2=(V[]) new Object[M2];
	}
	
	static double baseLog(double x, double base) {
		return Math.log10(x) / Math.log10(base);
	}

	
	private int getPrime(int M) {
		int count=0;
        for(int i=M; true; i++){
           for(int j=2; j<=i; j++)
              if(i%j ==0)	count ++;
            	if(count==1)
         	   		return i;
           count=0;
        }
	}
	
	public boolean isEmpty() {	return N ==0;}
	public int size() {return N;}

	private int hash1(K key) {	return (key.hashCode() & 0x7fffffff)%M1;}
	private int hash2(K key) {	return (key.hashCode() & 0x7fffffff)%M2;}
	
	private void insert1(K key, V value) {
		keys1[hash1(key)] = key;
		vals1[hash1(key)] = value;
		count++;
	}
	
	private void insert2(K key, V value) {
		keys2[hash2(key)] = key;
		vals2[hash2(key)] = value;
		count++;
	}
	
	
	public int put(K key, V value) {
		count=0;	K tmp;
		for(int maxCnt=0; maxloop > maxCnt; maxCnt++) {
			if(keys1[hash1(key)] == null) {
				insert1(key, value);
				N++;
				return count;
			}
			
			else {
				if(keys1[hash1(key)].equals(key)) {	vals1[hash1(key)] = value;	return count;	}
				tmp = keys1[hash1(key)];
				insert1(key,value);				
				key=tmp;
			}
			
			if(keys2[hash2(key)] == null) {
				insert2(key, value);	
				N++;
				return count;
			}
			
			else {
				if(keys2[hash2(key)].equals(key)) {	vals2[hash2(key)] = value;	return count;}
				tmp = keys2[hash2(key)];
				insert2(key, value);
				key = tmp;					
			}
		}
		count+=resize((2*M+1));
		count+=put(key,value);
		return count;
	}

	
	private int resize(int cap) {
		int oldM1=M1,oldM2=M2, count=0;
		CuckooHashingST<K,V> c;
		c=new CuckooHashingST<>(cap,0.45);
		
		M1 = c.M1;
		M2 = c.M2;	
		
		for(int i=0; i<oldM1; i++)
			if(keys1[i] != null)
				count+=c.put(keys1[i], vals1[i]);
		for(int i=0; i<oldM2; i++)
			if(keys2[i] != null)
				count+=c.put(keys2[i], vals2[i]);
		
		keys1 = c.keys1;
		vals1 = c.vals1;
		keys2 = c.keys2;
		vals2 = c.vals2;
		return count;
	}
	
	
	
	public void print() {
		System.out.println("	테이블 M1 크기 : "+ M1 + ", 테이블 M2 크기 : "+M2);
		System.out.println("	저장된 (Key, Value)쌍의 수 : "+N);
		System.out.println("	평균 검색길이 : "+ avlength());
		getMaxCost();
		
	}

	private void getMaxCost() {
		int mLeng=1, current; 
		K address = null;
		
		for(int i=0; i<keys2.length; i++)
			if(keys2[i] != null) {
				address=keys2[i];
				mLeng++;
				break;
			}
	
		System.out.println("	최대검색길이 : " + mLeng+" (key :  "+ address +")"); 
	}
	
	
	private double avlength() {
		double sum1 = 0,sum2=0;
		for(int i=0; i<keys1.length; i++)
			if(keys1[i] != null)
				sum1++;
		for(int i=0; i<keys2.length; i++)
			if(keys2[i] != null)
				sum2++;
		return (sum1+2*sum2)/(sum1+sum2);
	}
	
	
}


