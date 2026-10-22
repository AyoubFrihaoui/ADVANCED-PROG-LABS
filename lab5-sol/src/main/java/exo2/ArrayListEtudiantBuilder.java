package exo2;

import java.util.ArrayList;
import java.util.List;

/**
 * SOLUTION — parses "numero:nom:prenom:note1:note2:..." lines.
 * Blank lines are skipped; fields are trimmed (names keep inner spaces,
 * e.g. "De La Vega", since the separator is ":").
 */
public class ArrayListEtudiantBuilder implements ListEtudiantBuilder {
	List<Etudiant> etudiants = new ArrayList<>();

	@Override
	public ListEtudiantBuilder reset() {
		etudiants.clear();
		return this;
	}

	@Override
	public ListEtudiantBuilder traiterLigne(String l) {
		if (l == null || l.trim().isEmpty()) {
			return this;
		}
		String[] parts = l.split(":");
		if (parts.length < 3) {
			throw new IllegalArgumentException("bad line: " + l);
		}
		Etudiant e = new Etudiant(Integer.parseInt(parts[0].trim()),
				parts[1].trim(), parts[2].trim());
		for (int i = 3; i < parts.length; i++) {
			if (!parts[i].trim().isEmpty()) {
				e.addNote(Double.parseDouble(parts[i].trim()));
			}
		}
		etudiants.add(e);
		return this;
	}

	@Override
	public List<Etudiant> getResults() {
		return etudiants;
	}


	public List<String> getNomsEtudiants() {
		List<String> noms = new ArrayList<>();
		for (Etudiant e : etudiants) {
			noms.add(e.getNom());
		}
		return noms;
	}
}
