package ma.fstt.trackingl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ma.fstt.model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class CmdProdController implements Initializable {

    @FXML
    private VBox cmdProd;

    @FXML
    private ComboBox<Long> commandes;

    @FXML
    private Button btnLoad;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClear;

    @FXML
    private TextField nom;
    @FXML
    private TextField prix;
    @FXML
    private TextField quant;
    @FXML
    private TextField desc;

    @FXML
    private TableView<Produit> mytable;
    Long id;

    @FXML
    private TableColumn<Produit, Long> col_id;
    @FXML
    private TableColumn<Produit, String> col_nom;
    @FXML
    private TableColumn<Produit, Float> col_prix;
    @FXML
    private TableColumn<Produit, String> col_desc;


    @FXML
    protected void onLoadButtonClick() {
        // access a la bdd
        try {
            CmdProdDAO cmdProdDAODAO = new CmdProdDAO();

            cmdProdDAODAO.load(commandes);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onSaveButtonClick() {
        // access a la bdd
        try {
            CmdProdDAO cmdProdDAODAO = new CmdProdDAO();

            CmdProd cmdProd = new CmdProd(0l , id, Integer.parseInt(quant.getText()));

            cmdProdDAODAO.saveCmdProd(cmdProd, commandes, quant);

            UpdateTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onClearButtonClick() {
        commandes.setValue(null);
        nom.setText(null);
        prix.setText(null);
        quant.setText(null);
        desc.setText(null);
    }

    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Produit,Long>("id_produit"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom_produit"));
        col_prix.setCellValueFactory(new PropertyValueFactory<Produit,Float>("prix"));
        col_desc.setCellValueFactory(new PropertyValueFactory<Produit,String>("description"));

        mytable.setItems(ProdController.getDataProduits());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
    }

    public void getData(javafx.scene.input.MouseEvent mouseEvent) {
        Produit produit = mytable.getSelectionModel().getSelectedItem();
        id = produit.getId_produit();
        nom.setText(produit.getNom_produit());
        prix.setText(Float.toString(produit.getPrix()));
        desc.setText(produit.getDescription());
    }

    @FXML
    void switchToHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent homeParent = loader.load();
        Scene homeScene = new Scene(homeParent);
        Stage window = (Stage) cmdProd.getScene().getWindow();
        window.setScene(homeScene);
        window.show();
    }
}
