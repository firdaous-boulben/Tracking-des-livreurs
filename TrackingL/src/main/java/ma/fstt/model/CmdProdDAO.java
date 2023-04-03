package ma.fstt.model;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CmdProdDAO extends BaseDAO<Commande>{
    public CmdProdDAO() throws SQLException {
        super();
    }

    public void load(ComboBox commandes) throws SQLException {

        String request = "select id_commande from commande ";

        this.statement = this.connection.createStatement();

        this.resultSet = this.statement.executeQuery(request);

        while (this.resultSet.next()) {
            commandes.getItems().add(resultSet.getString("id_commande"));
        }
    }

    public void saveCmdProd(CmdProd object, ComboBox commandes, TextField quant) throws SQLException {
        //Récupérer la valeur sélectionnée
        Long selectedValue = Long.parseLong(commandes.getValue().toString());
        object.setId_commande(selectedValue);
        object.setQuantite(Integer.parseInt(quant.getText()));

        String request = "INSERT INTO commande_produit (id_commande, id_produit, quantite) VALUES (?, ?, ?);";

        this.preparedStatement = this.connection.prepareStatement(request);

        // mapping
        this.preparedStatement.setString(1 , String.valueOf(object.getId_commande()));
        this.preparedStatement.setString(2 , String.valueOf(object.getId_produit()));
        this.preparedStatement.setString(3 , String.valueOf(object.getQuantite()));

        this.preparedStatement.execute();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Commande créée avec succès!");
        alert.setContentText("Vous avez ajouté "+ object.getQuantite()+ " de produit id="+ object.getId_produit()+ " à la commande id="+ object.getId_commande());
        alert.show();
    }

    @Override
    public void save(Commande object) throws SQLException{}

    @Override
    public void update(Commande object, Long id) throws SQLException {}

    @Override
    public void delete(Commande object, Long id) throws SQLException {}

    @Override
    public List<Commande> getAll()  throws SQLException {
        return null;
    }

}