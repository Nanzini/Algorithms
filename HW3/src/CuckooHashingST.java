
class CuckooHashingST<K,V> {

	private int N=0;
	private int M;
	private int M1;
	private int M2;
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
	
	public int put(K key, V value) {
		int count=0;	K tmp;
		for(int maxCnt=0; maxloop > maxCnt; maxCnt++) {
			if(keys1[hash1(key)] == null) {
				count++;
				keys1[hash1(key)] = key;
				vals1[hash1(key)] = value;
				N++;		
				return count;
			}
			else {
				if(keys1[hash1(key)].equals(key)) {	vals1[hash1(key)] = value;	return count;	}
				tmp = keys1[hash1(key)];
				count++;
				keys1[hash1(key)] = key;
				vals1[hash1(key)] = value;
				key=tmp;
			}
			
			
			if(keys2[hash2(key)] == null) {
				count++;
				keys2[hash2(key)] = key;
				vals2[hash2(key)] = value;
				N++;	
				return count;
			}
			else {
				if(keys2[hash2(key)].equals(key)) {	vals2[hash2(key)] = value;	return count;}
				tmp = keys2[hash2(key)];
				count++;
				keys2[hash2(key)] = key;
				vals2[hash2(key)] = value;
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
		System.out.println("테이블 M1 크기 : "+ M1 + ", 테이블 M2 크기 : "+M2);
		System.out.println("저장된 (Key, Value)쌍의 수 : "+N);
		System.out.println("평균 검색길이 : "+ avlength());
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
	
		System.out.println("최대검색길이 : " + mLeng+" (key :  "+ address +")"); 
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
