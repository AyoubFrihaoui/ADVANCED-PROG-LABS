package tp05.templateMethod;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Reminder (or not): in JDBC, indices start at 1 #!*!

public class EtudiantDAO {

    public void save(Etudiant etudiant) throws SQLException {
        String sql = "insert into Etudiant values (?,?)";
        try (
                // Automatic closing with try-with-resources
                Connection connection = ConnectionFactory.getInstance().getConnection();
                PreparedStatement pst = connection.prepareStatement(sql);) {
            pst.setLong(1, etudiant.getId());
            pst.setString(2, etudiant.getNom());
            pst.executeUpdate();         
        } 
    }

    public void update(Etudiant etudiant) throws SQLException {
        String sql = "update etudiant set nom = ? where etudiant_id = ?";
        try (
                // Automatic closing with try-with-resources
                Connection connection = ConnectionFactory.getInstance().getConnection();
                PreparedStatement pst = connection.prepareStatement(sql);) {
            pst.setString(1, etudiant.getNom());
            pst.setLong(2, etudiant.getId());
            pst.executeUpdate();
        }
    }

    public void delete(Long id) throws SQLException {
        String sql = "delete from Etudiant where etudiant_id = ?";
        try (
                // Automatic closing with try-with-resources
                Connection connection = ConnectionFactory.getInstance().getConnection();
                PreparedStatement pst = connection.prepareStatement(sql);) {

            pst.setLong(1, id);
            pst.executeUpdate();
        } 
    }

    public Etudiant findById(Long id) throws SQLException {
        Etudiant etudiant = null;
        String sql = "select * from Etudiant where etudiant_id = ?";
        try (
                // Automatic closing with try-with-resources
                Connection connection = ConnectionFactory.getInstance().getConnection();
                PreparedStatement pst = connection.prepareStatement(sql);) {
            pst.setLong(1, id);
            try (ResultSet res = pst.executeQuery()) { // try-with-resources to close res for sure.
                if (res.next()) {
                    Long idRes = res.getLong("ETUDIANT_ID");
                    String nomRes = res.getString("NOM");
                    etudiant = new Etudiant(idRes, nomRes);
                }
                // otherwise: null result.
            }
        } 
        return etudiant;
    }

    public List<Etudiant> findAll() throws SQLException {
        ArrayList<Etudiant> liste = new ArrayList<>();
        String sql = "select * from Etudiant order by etudiant_id";
        try (
                // Automatic closing with try-with-resources
                Connection connection = ConnectionFactory.getInstance().getConnection();
                Statement pst = connection.createStatement();) {

            try (ResultSet res = pst.executeQuery(sql)) { // try-with-resources to close res for sure.
                while (res.next()) {
                    Long idRes = res.getLong("ETUDIANT_ID");
                    String nomRes = res.getString("NOM");
                    liste.add(new Etudiant(idRes, nomRes));
                }
            }
        } 
        return liste;
    }

}
