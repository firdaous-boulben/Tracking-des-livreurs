package ma.fstt.model;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAO extends BaseDAO<Commande>{
    public CommandeDAO() throws SQLException {
        super();
    }

    public void load(ComboBox livreurs) throws SQLException {

        String request = "select nom from livreur ";

        this.statement = this.connection.createStatement();

        this.resultSet = this.statement.executeQuery(request);

        while (this.resultSet.next()) {
            livreurs.getItems().add(resultSet.getString("nom"));
        }
    }

    @Override
    public void save(Commande object) throws SQLException{}

    public void saveCmd(Commande object, ComboBox livreurs) throws SQLException {
        //Récupérer la valeur sélectionnée
        String selectedValue = (String) livreurs.getValue();
        object.setLivreur(selectedValue);

        PreparedStatement insertStmt = null;
        PreparedStatement updateStmt = null;

        try {
            this.connection.setAutoCommit(false);

            //Ajouter une nouvelle commande
            insertStmt = this.connection.prepareStatement("insert into commande (date_debut, date_fin , distance, client, livreur, etat) values (? , ? , ? , ? , ? , ?)");
            insertStmt.setString(1 , object.getDate_debut());
            insertStmt.setString(2 , object.getDate_fin());
            insertStmt.setFloat(3 , object.getDistance());
            insertStmt.setString(4 , object.getClient());
            insertStmt.setString(5 , object.getLivreur());
            insertStmt.setString(6 , object.getEtat());
            insertStmt.executeUpdate();

            //Mettre à jour le nombre de commandes du livreur sélectionné
            updateStmt = this.connection.prepareStatement("UPDATE livreur SET nombre_commande = nombre_commande + 1 WHERE nom = ?");
            updateStmt.setString(1, selectedValue);
            updateStmt.executeUpdate();

            this.connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Commande object, Long id) throws SQLException {}

    public void updateCmd(Commande object, Long id, ComboBox livreurs) throws SQLException {
        //Récupérer la valeur sélectionnée
        String selectedValue = (String) livreurs.getValue();
        object.setLivreur(selectedValue);

        PreparedStatement updateCmdStmt = null;
        PreparedStatement updateLivStmt = null;

        //Modifier une commande
        try {
            updateCmdStmt = this.connection.prepareStatement("update commande set date_debut = ? , date_fin = ?, distance = ?, client = ?, livreur = ?, etat = ? where id_commande = ?");
            updateCmdStmt.setString(1 , object.getDate_debut());
            updateCmdStmt.setString(2 , object.getDate_fin());
            updateCmdStmt.setFloat(3 , object.getDistance());
            updateCmdStmt.setString(4 , object.getClient());
            updateCmdStmt.setString(5 , object.getLivreur());
            updateCmdStmt.setString(6 , object.getEtat());
            updateCmdStmt.setLong(7 , id);
            updateCmdStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Mettre à jour le nombre de commande associé à chaque livreur
        updateLivStmt = this.connection.prepareStatement("UPDATE livreur l SET l.nombre_commande = (SELECT COUNT(*) FROM commande c WHERE c.livreur = l.nom )");
        updateLivStmt.executeUpdate();
    }

    @Override
    public void delete(Commande object, Long id) throws SQLException {
        String request = "delete from commande where id_commande = ?";

        // mapping objet table
        this.preparedStatement = this.connection.prepareStatement(request);

        // mapping
        this.preparedStatement.setLong(1 , id);

        this.preparedStatement.execute();
    }

    @Override
    public List<Commande> getAll()  throws SQLException {

        List<Commande> mylist = new ArrayList<Commande>();

        String request = "select * from commande ";

        this.statement = this.connection.createStatement();

        this.resultSet =   this.statement.executeQuery(request);

        // parcours de la table
        while ( this.resultSet.next()){

            // mapping table objet
            mylist.add(new Commande(this.resultSet.getLong(1) ,
                    this.resultSet.getString(2),
                    this.resultSet.getString(3) ,
                    this.resultSet.getFloat(4),
                    this.resultSet.getString(5),
                    this.resultSet.getString(6),
                    this.resultSet.getString(7)));

        }

        return mylist;
    }

}