package Perso;

public class Etudiant extends Personne{

	private float note;
	
	public Etudiant(String nom, int age, float note){
		super(nom,age);
		this.note = note;	
	}
	
	//commentaire
	public float getNote(){
		return this.note;
	}
	
	public void setNote(float val){
		this.note = val; 
	}
	
	
	public String toString(){
	
		return ("L'etudiant : "+this.getNom()+" a "+this.getAge()+"ans et une note de "+this.getNote()+"/20.");
	
	}
	
	

}
