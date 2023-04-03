package ma.fstt.model;


// java bean
public class CmdProd {
        private Long id_commande ;
        private Long id_produit ;
        private int quantite ;


    public CmdProd() {
    }

    public CmdProd(Long id_commande, Long id_produit, int quantite) {
        this.id_commande = id_commande;
        this.id_produit = id_produit;
        this.quantite = quantite;
    }

    public Long getId_commande() {
        return id_commande;
    }

    public void setId_commande(Long id_commande) {
        this.id_commande = id_commande;
    }

    public Long getId_produit() {
        return id_produit;
    }

    public void setId_produit(Long id_produit) {
        this.id_produit = id_produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "CmdProd{" +
                "id_commande=" + id_commande +
                ", id_produit=" + id_produit +
                ", quantite=" + quantite +
                '}';
    }
}