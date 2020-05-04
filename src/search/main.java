package search;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class main {

	public static void main(String[] args) {
		int total=0;
		int maxValue1=3, maxValue2=2, maxValue3=1;
		String maxKey1 = null,maxKey2 = null,maxKey3 = null;
		
		Search<String,Integer> sq = new Search<String,Integer>();
		
		File file;
		final JFileChooser fc = new JFileChooser();
		if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			file=fc.getSelectedFile();
		else {
			JOptionPane.showMessageDialog(null, "파일을 선태가세요","오류",JOptionPane.ERROR_MESSAGE);
			return ;
		}
		
		Scanner scan = null;
		try {
			scan = new Scanner(file);
			
			
			for(int i=0; scan.hasNext(); i++) {
				String key = scan.next();
				if(!sq.contains(key))
					sq.put(key, 1);
				else
					sq.put(key,sq.get(key)+1);	//key값에 단어 수 넣기
				total++;
			}
			//출력
			for(String key : sq.keys()) {
				if(maxValue3 < sq.get(key) && sq.get(key)<maxValue2) {
					maxValue3 = sq.get(key);	maxKey3 = key;
				}
				else if(maxValue2 < sq.get(key) && sq.get(key)<maxValue1) { 
					maxValue2 = sq.get(key);	maxKey2 = key;
				}
				else if(maxValue1 < sq.get(key)) {
					maxValue1 = sq.get(key); maxKey1 = key;
				}
			}
			System.out.println("1등 : "+maxKey1 + " - - - - " + maxValue1);
			System.out.println("2등: "+maxKey2 + " - - - - " + maxValue2);
			System.out.println("3등: "+maxKey3 + " - - - - " + maxValue3);
			System.out.println("전체중의 "+((double)maxValue1/(double)total)*100 + " " + total);
			System.out.println("전체중의 "+((double)maxValue2/(double)total)*100 + " " + total);
			System.out.println("전체중의 "+((double)maxValue3/(double)total)*100 + " " + total);
		} catch (Exception e) {

		}
		if(scan!=null)
			scan.close();
		
	}

}
