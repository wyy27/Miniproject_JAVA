package TP2;

import java.util.Arrays;

import TP2.Step;
import utilitaires.TabFileReader;
import utilitaires.TermList;

public class Network {
	
	private Step[] tab;
	private String[] list;
	
	public Network() {
		String filename="steps.txt";
		TabFileReader.readTextFile(filename,'\t',"data");
		tab= new Step[TabFileReader.nrow()];
		for(int n = 0;n<TabFileReader.nrow();n++) {
			String A = TabFileReader.wordAt(n,0);
			String L = TabFileReader.wordAt(n,1);
			String B = TabFileReader.wordAt(n,2);
			
			tab[n]= new Step(A,L,B);
			
		}		
		
	}
	


	
	public Step stepAt(int num_ligne) {
		return tab[num_ligne];
		
	}
	
	public void ListStation() {
		TermList list = new TermList();
		for(int n=0;n<numberOfline();n++) {
			list.add(stepAt(n));
		}
	
	}
	
	@Override
	public String toString() {
		return "Network [list=" + Arrays.toString(list) + "]";
	}

	public static void main(String[] args) {
		Network Net = new Network();
		
		for(int n = 0;n<numberOfline();n++) {
			Net.stepAt(n);
			
		}
		
		/*for(int n = 0;n<numberOfline();n++) {
			Net.tab[n].afficheStep();
			//System.out.println(Net.tab[n]);
		}*/
		
		TermList list = new TermList();
		for(int n=0;n<numberOfline();n++) {
			if(list.exists(Net.tab[n].extractStation1())) {}
			else {
				list.add(Net.tab[n].extractStation1());
			}
			
			if(list.exists(Net.tab[n].extractStation2())) {}
			else {
				list.add(Net.tab[n].extractStation2());
			}
		}
		
		for(int n = 0;n<numberOfStations();n++) {
			System.out.println(list[n]);
		}

	}

	

	
	

}
