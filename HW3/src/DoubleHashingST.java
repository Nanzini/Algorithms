
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
	
	public boolean contains(K key) { return get(key) != null;}
	public boolean isEmpty() {	return N ==0;}
	public int size() {return N;}
	
	public V get(K key) {
		int count=1;
		for(int i=hash1(key); keys[i]!=null; i=hash3(key,count-1))
			if(keys[i].equals(key))
				return vals[i];
		return null;	
	}
	
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
				current = getCost(keys[i]);
				if(current > mLeng) {
					mLeng = current;
					address = keys[i];
				}
			}
		}
		System.out.println("	최대검색길이 : " + mLeng+" (key : "+ address +")"); 
	}
	
	
}

