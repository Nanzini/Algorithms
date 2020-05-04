
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
				sum+= getCost(keys[i]);
		return sum/N;
	}
	
	
	private void getMaxCost() {
		int mLeng=1, current; 
		K address = null;
		for(int i=0; i<keys.length; i++)
			if(keys[i] != null) {
				current = getCost(keys[i]);
				if(current > mLeng) {
					mLeng = current;
					address = keys[i];
				}
			}
		
		System.out.println("	최대검색길이 : " + mLeng+" (key = "+ address+")"); 
	}
	
}
