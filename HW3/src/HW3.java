import java.util.Random;
import java.util.Scanner;

public class HW3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("������ seed���� �ɺ� ���̺� ũ�⸦ �Է�: ");
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


//���⼭ ���� �ؽ� ���̺� Ŭ������ �����ϴ� ���α׷� �߰��� ��!