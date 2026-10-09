package exo1.q2.v1;

public class Produit {
    private String designation;
    private double prix;

    public Produit(String designation, double prix) {
        this.designation = designation;
        this.prix = prix;
    }

    public String getDesignation() {
        return this.designation;
    }

    public double getPrix() {
        return this.prix;
    }

    @Override
    public String toString() {
        return "{" +
            " designation='" + getDesignation() + "'" +
            ", prix='" + getPrix() + "'" +
            "}";
    }

}
