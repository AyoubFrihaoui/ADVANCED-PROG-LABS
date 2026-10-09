package exo1.q2.v1;

public class Main {
    
    public static void main(String[] args) {
        Catalogue cat = new Catalogue();
        cat.ajouterProduit(new Produit("ordi", 600));
        cat.ajouterProduit(new Produit("souris", 30));
        System.out.println(cat.xmlDumper());
    }
}
