package Perso;

public class Enseignant extends Personne{

	private int heures;
	
	public Enseignant(String nom, int age, int heures){
		super(nom,age);
		this.heures = heures;	
	}
	
	
	
	public int getHeures(){
		return this.heures;
	}
	
	public void setHeures(int val){
		this.heures = val; 
	}
	
	
	public String toString(){
	
		return ("L'enseignant : "+this.getNom()+" a "+this.getAge()+"ans et travaille "+this.getHeures()+" heures.");
	
	}

}