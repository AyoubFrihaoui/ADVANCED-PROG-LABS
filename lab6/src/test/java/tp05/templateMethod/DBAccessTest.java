package tp05.templateMethod;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

// Note: the database is destroyed and recreated before each test,
// so tests are independent.
//
// Alternatives could be:
//
// - two databases, one in-memory for tests, one persistent for the app (more realistic)
// - work on the persistent database, but run each test in a transaction, with
//   rollback at the end.
public class DBAccessTest {

    @Before
    public void init() throws SQLException {
        DbDefinition db = new DbDefinition();
        db.initialiserDB();
    }

    @Test
    public void retourneContenuDB() throws SQLException {
        DbDefinition db = new DbDefinition();
        String attendu = "1 Alan Turing\n2 Ada Lovelace\n3 Niklaus Wirth\n4 Charles Babbage\n5 John von Neumann\n";
        String contenu = db.retourneContenuDB();
        assertEquals(attendu, contenu);
    }


    @Test
    public void testGetById() throws SQLException {
        EtudiantDAO dao = new EtudiantDAO();
        String attendu = "Ada Lovelace";
        Etudiant e = dao.findById(2l);
        assertEquals(attendu, e.getNom());
    }

    @Test
    public void testGetByIdNull() throws SQLException {
        EtudiantDAO dao = new EtudiantDAO();
        Etudiant e = dao.findById(2000l);
        assertNull(e);
    }
    
    @Test
    public void testInsererEtudiant() throws SQLException {
        EtudiantDAO dao = new EtudiantDAO();
        Etudiant e = new Etudiant(10l, "test");
        dao.save(e);
        String attendu = "test";
        Etudiant res = dao.findById(10l);
        assertEquals(attendu, res.getNom());
    }

    @Test
    public void testInsererEtudiantBis() throws SQLException {
        EtudiantDAO dao = new EtudiantDAO();
        Etudiant e = new Etudiant(10l, "test");
        dao.save(e);
        String attendu = "test";
        Etudiant res = dao.findById(10l);
        assertEquals(attendu, res.getNom());
    }


    @Test
    public void testEnleverEtudiant() throws SQLException {
        EtudiantDAO dao = new EtudiantDAO();
        dao.delete(1l);
        Etudiant res = dao.findById(1l);
        assertNull(res);
    }

    @Test
    public void testUpdateEtudiant() throws SQLException {
        EtudiantDAO dao = new EtudiantDAO();
        Etudiant e = dao.findById(2l);    
        assertEquals("Ada Lovelace", e.getNom());
        e.setNom("A. Lovelace");
        dao.update(e);
        Etudiant res = dao.findById(2l);
        assertEquals("A. Lovelace", res.getNom());
    }
}
