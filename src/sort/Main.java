package sort;

public class Main {

	public static void main(String[] args) {
		Selection abs = new Selection();
		Insertion ins = new Insertion();
		Comparable[] a = {'C','B','D','A','X','F'};
		Comparable[] b = {5,2,3,1,5,6,2,3,1,5,4,2,3,10};
		
		
		//Selection Sort
		/*
		double selectStart = System.currentTimeMillis();
		abs.sort(a);
		abs.show(a);
		double selectEnd = System.currentTimeMillis();
		double tookTime = (selectEnd-selectStart)/1000;
		System.out.println(tookTime);
		*/
		
		//Insertion Sort
		double selectStart = System.currentTimeMillis();
		ins.sort(a);                                    
		ins.show(a);                                    
		double selectEnd = System.currentTimeMillis();  
        double tookTime = (selectEnd-selectStart)/1000; 
        System.out.println(tookTime);

	}
}
