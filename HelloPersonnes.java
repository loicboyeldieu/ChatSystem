import perso.*;

public class HelloPersonnes{


	public static void main(String[] args){
	
		Personne aurel = new Personne("Leloup", 20);
		Personne julien = new Etudiant("Baladier", 21, 4);
		Personne ernesto = new Enseignant("Ernesto", 37, 30);
		
		
		Personne tab[]=new Personne[3];
		
		tab[0]=aurel;
		tab[1]=julien;
		tab[2]=ernesto;
		
		for (int i = 0; i < tab.length; i++) {
			System.out.println(tab[i].toString());
		}
		
		// Polymorphisme : oui Personne/Etudiant/Enseignant
		// Liaison dynamique ? Oui
		// Surcharge? Oui
	    // Redéfinition partielle ou totale? Totale car on a pas utilisé super()
		
	
	}



}