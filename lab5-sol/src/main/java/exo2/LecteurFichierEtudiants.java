package exo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class LecteurFichierEtudiants {

	private ListEtudiantBuilder builder;

	public LecteurFichierEtudiants(ListEtudiantBuilder builder) {
		this.builder = builder;
	}

	public List<Etudiant> lireFichier(Reader reader) throws IOException {
		try (BufferedReader r = new BufferedReader(reader)) {
			String l;
			builder.reset();
			while ((l = r.readLine()) != null) {
				builder.traiterLigne(l);
			}
		}
		return builder.getResults();

	}

}
