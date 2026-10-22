package exo2;

import java.util.List;

public interface ListEtudiantBuilder {
	ListEtudiantBuilder reset();
	ListEtudiantBuilder traiterLigne(String l);
	List<String>  getNomsEtudiants();
	List<Etudiant> getResults();
}
