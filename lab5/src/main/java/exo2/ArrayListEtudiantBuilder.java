package exo2;

import java.util.ArrayList;
import java.util.List;

public class ArrayListEtudiantBuilder implements ListEtudiantBuilder {
	List<Etudiant> etudiants = new ArrayList<>();

	@Override
	public ListEtudiantBuilder reset() {
		// TODO Auto-generated method stub
		
		return this;
	}

	@Override
	public ListEtudiantBuilder traiterLigne(String l) {
		// TODO Auto-generated method stub
		
		return this;
	}

	@Override
	public List<Etudiant> getResults() {
		// TODO Auto-generated method stub
		return etudiants;
	}


	public List<String> getNomsEtudiants() {
		
		return null;

	}

}
