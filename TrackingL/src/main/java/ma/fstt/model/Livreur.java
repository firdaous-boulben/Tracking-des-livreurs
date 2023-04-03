package ma.fstt.model;

public class Livreur {
    //Attributs
    private Long id_livreur ;
    private String nom ;
    private String telephone ;
    private Integer nb_commande;

    //Constructeurs
    public Livreur() {}

    public Livreur(Long id_livreur, String nom, String telephone, Integer nb_commande) {
        this.id_livreur = id_livreur;
        this.nom = nom;
        this.telephone = telephone;
        this.nb_commande = nb_commande;
    }

    //Getters & Setters
    public Long getId_livreur() {
        return id_livreur;
    }
    public void setId_livreur(Long id_livreur) {
        this.id_livreur = id_livreur;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getNb_commande() { return nb_commande; }
    public void setNb_commande(Integer nb_commande) {
        this.nb_commande = nb_commande;
    }


    @Override
    public String toString() {
        return "Livreur{" +
                "id_livreur=" + id_livreur +
                ", nom='" + nom + '\'' +
                ", telephone='" + telephone + '\'' +
                ", nb_commande='" + nb_commande + '\'' +
                '}';
    }
}