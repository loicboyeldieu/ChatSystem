import perso.*;

public class EnseignantChercheur extends Enseignant implements IChercheur  {

	private Publication tabPublication[] = new Publication[10];
	
	public EnseignantChercheur(String nom, int age, int heures) {
		super(nom, age, heures);
		// TODO Auto-generated constructor stub
	}
	
	public void ajouterPublication(Publication p) {
		
		int i = 0;
		while ((tabPublication[i]!=null)&&(i<10)){
			i++;
		}
		if (i<10){
			tabPublication[i]=p;
		}
		else{
			System.out.println("Tableau de publication déjà rempli");
		}
		
	}


	public String listerPublications() {
		
		String listePublication = new String();
		
		for (int i = 0; i<10; i++){
			listePublication.concat(" "+tabPublication[i].toString()+" ");
		}
		
		return listePublication;
	}
	
	
	
}
