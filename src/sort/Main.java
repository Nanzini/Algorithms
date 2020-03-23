package sort;

public class Main {

	public static void main(String[] args) {
		Selection abs = new Selection();
		Insertion ins = new Insertion();
		Shell sh = new Shell();
		Merge mrg = new Merge();
		Count cnt = new Count();
		
		Comparable[] a = {'C','B','D','A','X','F'};
		Comparable[] b = {4,2,6,3,1,3,2,5,2,1,5,6,1};
		Comparable[] c = {2,4,1,1,2,3};
		
		
		/*	Selection Sort
		double selectStart = System.currentTimeMillis();
		abs.sort(a);
		abs.show(a);
		double selectEnd = System.currentTimeMillis();
		double tookTime = (selectEnd-selectStart)/1000;
		System.out.println(tookTime);
		
		

		/*	Insertion Sort
		double selectStart = System.currentTimeMillis();
		ins.sort(a);                                    
		ins.show(a);                                    
		double selectEnd = System.currentTimeMillis();  
        double tookTime = (selectEnd-selectStart)/1000; 
        System.out.println(tookTime);
		*/
		
		
		/*	Shell Sort
		double selectStart = System.currentTimeMillis();
		sh.sort(b);                                    
		sh.show(b);                                    
		double selectEnd = System.currentTimeMillis();  
        double tookTime = (selectEnd-selectStart)/1000; 
        System.out.println(tookTime);
		*/
		
		/*	Merge
		double selectStart = System.currentTimeMillis();
		mrg.sort(c);                                    
		double selectEnd = System.currentTimeMillis();  
        double tookTime = (selectEnd-selectStart)/1000; 
        System.out.println(tookTime);
		*/
		
		double selectStart = System.currentTimeMillis();
		cnt.sort(b);
		double selectEnd = System.currentTimeMillis();  
        double tookTime = (selectEnd-selectStart)/1000; 
        System.out.println(tookTime);

	}
}
