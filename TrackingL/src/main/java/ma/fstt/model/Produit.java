package ma.fstt.model;

public class Produit {
    //Attributs
    private Long id_produit ;
    private String nom_produit ;
    private Float prix ;
    private String description ;

    //Constructeurs
    public Produit() {}
    public Produit(Long id_produit, String nom_produit, Float prix, String description) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.prix = prix;
        this.description = description;
    }

    //Getters & Setters
    public Long getId_produit() {
        return id_produit;
    }
    public void setId_produit(Long id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom_produit() {
        return nom_produit;
    }
    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public Float getPrix() {
        return prix;
    }
    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String telephone) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id_produit=" + id_produit +
                ", nom_produit='" + nom_produit + '\'' +
                ", prix='" + prix + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}