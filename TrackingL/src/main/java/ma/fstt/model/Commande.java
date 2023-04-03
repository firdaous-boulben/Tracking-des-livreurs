package ma.fstt.model;


import java.util.Date;

// java bean
public class Commande {
        private Long id_commande ;
        private String date_debut ;
        private String date_fin ;
        private Float distance ;
        private String client ;
        private String livreur;
        private String etat ;

    public Commande() {
    }

    public Commande(Long id_commande, String date_debut, String date_fin, Float distance, String client, String livreur, String etat) {
        this.id_commande = id_commande;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.distance = distance;
        this.client = client;
        this.livreur = livreur;
        this.etat = etat;
    }

    public Long getId_commande() {
        return id_commande;
    }

    public void setId_commande(Long id_commande) {
        this.id_commande = id_commande;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getLivreur() {
        return livreur;
    }

    public void setLivreur(String livreur) {
        this.livreur = livreur;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id_commande=" + id_commande +
                ", date_debut='" + date_debut + '\'' +
                ", date_fin='" + date_fin + '\'' +
                ", distance='" + distance + '\'' +
                ", client='" + client + '\'' +
                ", livreur='" + livreur + '\'' +
                ", etat='" + etat + '\'' +
                '}';
    }

}