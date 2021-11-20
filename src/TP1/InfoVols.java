package TP1;

import java.util.Scanner;
import utilitaires.TabFileReader;

public class InfoVols {

	// Display of all possible direct flights (all parameters) from a given departure specified by the user
	public static void volsDirects() {
		Scanner scan=new Scanner(System.in);
		
		String filename="departs.txt";
		TabFileReader.readTextFile(filename,'\t',"data");
		
		System.out.print("La ville de depart: ");
		String ville=scan.nextLine();
		
		System.out.println("Tous les vols directs possibles:");
		for(int i=0;i<TabFileReader.nrow();i++) {
			if(ville.equals(TabFileReader.wordAt(i,1))) {
				for (int j=0; j<TabFileReader.ncol();j++)
					System.out.print(TabFileReader.wordAt(i,j)+"\t");
				System.out.println();
			}
		}
		
		scan.close();
	}
	
	// If there is a stop over and the transit time greater than one hour
	public static void volsDeuxVilles() {
		Scanner scan=new Scanner(System.in);
		
		String filename="departs.txt";
		TabFileReader.readTextFile(filename,'\t',"data");
		
		System.out.print("La ville de depart: ");
		String villeDepart=scan.nextLine();
		
		System.out.print("La ville d'arrive: ");
		String villeArrive=scan.nextLine();
		
		System.out.println("Les vols directs:\n");
		for(int i=0;i<TabFileReader.nrow();i++) {
			if(villeDepart.equals(TabFileReader.wordAt(i,1)) && villeArrive.equals(TabFileReader.wordAt(i, 2))) {
				for (int j=0; j<TabFileReader.ncol();j++)
					System.out.print(TabFileReader.wordAt(i,j)+"\t");
				System.out.println();
			}
		}
		
		/*
			VolDepart: from villeDepart to villeEscale: the "m" row
	 		VolArrive: from villeEscale to villeArrive: the "n" row
		*/
		System.out.println("\n Les vols avec une escale: ");
		for(int m=0;m<TabFileReader.nrow();m++) {
			for(int n=0;n<TabFileReader.nrow();n++) {
				if(villeDepart.equals(TabFileReader.wordAt(m,1)) && villeArrive.equals(TabFileReader.wordAt(n,2))) {
					if (m != n){
						if((TabFileReader.wordAt(m,2)).equals(TabFileReader.wordAt(n,1))) {
							// Found a flight connecting two cities which has one stopover 
							
							int temps_VolDepart=toMinutes(TabFileReader.wordAt(m, 4));
							int temps_VolArrive=toMinutes(TabFileReader.wordAt(n, 3));
							
							if(temps_VolDepart+60<=temps_VolArrive) {
								// The transit time is greater than one hour
								
								System.out.println("\nL'escale: "+TabFileReader.wordAt(m,2));
								for(int j=0;j<TabFileReader.ncol();j++) 
									System.out.print(TabFileReader.wordAt(m, j)+"\t");
								System.out.println();
								for(int j=0;j<TabFileReader.ncol();j++) 
									System.out.print(TabFileReader.wordAt(n, j)+"\t");
								System.out.println();
						     }
					    }
				    }
			  }
		   }
		}
		
		scan.close();
		
	}
	
	// display of airports in alphabetic order; each airport can occur only once in the list
	public static void listeAeroports() {
		String filename="departs.txt";
		TabFileReader.readTextFile(filename,'\t',"data");
		
		String []aeroports=new String[TabFileReader.nrow()];
		int k=0;  	// pointer of table
		System.out.println("Tous les aéroports desservis dans la journée par ordre alphabétique:");

		for(int m=0;m<TabFileReader.nrow();m++) {
			boolean unefois=true;
			for(int n=m-1;n>=0;n--) {
				if(TabFileReader.wordAt(m, 2).equals(TabFileReader.wordAt(n, 2)))
					unefois=false;
			}
			if(unefois) {
				String aeroport=TabFileReader.wordAt(m, 2);
				int i=k;
				for(;i>0;i--) {
					if(aeroport.compareTo(aeroports[i-1]) >= 0) break;
					else {
						aeroports[i]=aeroports[i-1];
					}
				}
				k++;
				aeroports[i]=aeroport;
			}
		}
		
		for(int j=0;j<k;j++) {
			System.out.println(aeroports[j]);
		}
	}
	
	public static int toMinutes(String heure) {
		String []tokens=heure.split(":");
		int hour=Integer.parseInt(tokens[0]);
		int minute=Integer.parseInt(tokens[1]);
		return hour*60+minute;
	}
	
	
	public static void main(String []args) {
		System.out.println("\t\t Menu");
		System.out.println("---------------------------------------------");
		System.out.println("1.Tous les vols directs possibles (tous les paramètres) à partir d’une ville de départ donnée par l’utilisateur");
		System.out.println("2.Les reliant deux villes données par l’utilisateur,ayant une escale et respectant un temps de transit d’au moins d’une heure entre les deux vols");
		System.out.println("3.La liste de tous les aéroports desservis dans la journée par ordre alphabétique");
		System.out.println("---------------------------------------------");
		System.out.print("Vous choisissez: ");
		
		Scanner scan=new Scanner(System.in);
		int choix=scan.nextInt();
		switch(choix) {
			case 1:volsDirects();break;
			case 2:volsDeuxVilles();break;
			case 3:listeAeroports();break;
		}
		scan.close();
	}
	
}
