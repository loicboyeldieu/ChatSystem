import Perso.*;

public class HelloPersonnes{


	public static void main(String[] args){
	
		Personne aurel = new Personne("Leloup", 20);
		Etudiant julien = new Etudiant("Baladier", 21, 4);
		Enseignant ernesto = new Enseignant("Ernesto", 37, 30);
		
		System.out.println(aurel);
		System.out.println(julien);
		System.out.println(ernesto);
		
		
	
	}



}