package exo2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/** SOLUTION tests (Ex2) — the four extra cases the handout asks for. */
public class TestEtudiantsBuilderCases {

	private List<Etudiant> lire(String data) throws IOException {
		ArrayListEtudiantBuilder builder = new ArrayListEtudiantBuilder();
		new LecteurFichierEtudiants(builder).lireFichier(new StringReader(data));
		return builder.getResults();
	}

	@Test
	public void testTroisEtudiantsChamps() throws IOException {
		List<Etudiant> etudiants = lire(FichierUtil.dataEtudiants);
		assertEquals(3, etudiants.size());
		Etudiant turing = etudiants.get(0);
		assertEquals(1, turing.getNumero());
		assertEquals("Turing", turing.getNom());
		assertEquals("Alan", turing.getPrenom());
		assertEquals(Arrays.asList(10.0, 12.0, 13.0, 7.0, 15.0), turing.getNotes());
	}

	@Test
	public void testEtudiantSansNotes() throws IOException {
		List<Etudiant> etudiants = lire("4:Dupont:Marie\n");
		assertEquals(1, etudiants.size());
		assertEquals("Dupont", etudiants.get(0).getNom());
		assertTrue(etudiants.get(0).getNotes().isEmpty());
	}

	@Test
	public void testEspacesDansLeNom() throws IOException {
		List<Etudiant> etudiants = lire("5:De La Vega:Maria:15:16\n");
		assertEquals(1, etudiants.size());
		assertEquals("De La Vega", etudiants.get(0).getNom());
		assertEquals(Arrays.asList(15.0, 16.0), etudiants.get(0).getNotes());
	}

	@Test
	public void testBeaucoupDeNotes() throws IOException {
		List<Etudiant> etudiants = lire("6:Martin:Paul:1:2:3:4:5:6:7:8:9:10:11:12\n");
		assertEquals(1, etudiants.size());
		assertEquals(12, etudiants.get(0).getNotes().size());
		assertEquals(Double.valueOf(12.0), etudiants.get(0).getNotes().get(11));
	}
}
