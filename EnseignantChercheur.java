import perso.*;

import java.util.ArrayList;
import java.util.List;

public class EnseignantChercheur extends Enseignant implements IChercheur  {

	private ArrayList<Publication> publicationList;
	
	public EnseignantChercheur(String nom, int age, int heures) {
		super(nom, age, heures);
		publicationList = new ArrayList<Publication>();
	}
	
	public void ajouterPublication(Publication p) {
		
		publicationList.add(p);
		
	}


	public String listerPublications() {
		
		String listePublication = new String();
		
		for (Publication p: publicationList){
			listePublication.concat(" "+p.toString()+" ");
		}
		
		return listePublication;
	}
	
	
	
}
