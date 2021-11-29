package TP2;

import java.util.Scanner;

import utilitaires.TabFileReader;

public class Step {
	private String station1,ligne,station2;
	
	public Step(String s1, String lg, String s2) {
		station1=s1;
		ligne=lg;
		station2=s2;
	}
	
	public void getNext(String arg) {
		
		if(station1.equals(arg)) {
			System.out.println(station2);
		}
		if(station2.equals(arg)) {
			System.out.println(station1);
		}
		if(station2.equals("NULL")||station1.equals("NULL")) {
			System.out.println("NULL!");
		}
	
	}
	
	public void afficheStep() {
		System.out.println("Step [station1=" + station1 + ", ligne=" + ligne + ", station2=" + station2 + "]");
	}
	
	public String extractStation1() {
		return station1;
	}
	
	public String extractStation2() {
		return station2;
	}
	
	



	public static void main(String []args) {
		
		Scanner scan=new Scanner(System.in);
		
		String filename="steps.txt";
		TabFileReader.readTextFile(filename,'\t',"data");
		
		System.out.print("La station 1: ");
		String station1=scan.nextLine();
		
		System.out.print("Ligne : ");
		String ligne=scan.nextLine();
		
		System.out.print("Station2 : ");
		String station2=scan.nextLine();
		
		Step st=new Step(station1,ligne,station2); 
		System.out.println(st);
		
		System.out.print("get next ? : ");
		String var=scan.nextLine();
		
		st.getNext(var);
	}

}



/*
int num_ligne = 0;
boolean err = false;

for(int i=0;i<TabFileReader.nrow();i++) {
	if(station1.equals(TabFileReader.wordAt(i,0)) && ligne.equals(TabFileReader.wordAt(i,1))  && station2.equals(TabFileReader.wordAt(i,2))) {
		num_ligne=i;
	}else {
		err = true;
	}
}

if(station1.equals(arg)) {
	System.out.print(TabFileReader.wordAt(num_ligne, 2));
	System.out.println();
}

if(station2.equals(arg)) {
	System.out.print(TabFileReader.wordAt(num_ligne, 0));
	System.out.println();
}
if(err) {
	System.out.println("Null!");
}
*/
