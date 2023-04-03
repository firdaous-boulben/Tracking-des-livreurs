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
import ma.fstt.model.Commande;
import ma.fstt.model.CommandeDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class CmdController implements Initializable {

    @FXML
    private Button btnSave;

    @FXML
    private VBox cmd;

    @FXML
    private TextField ddebut;
    @FXML
    private TextField dfin;
    @FXML
    private TextField dist;
    @FXML
    private TextField client;
    @FXML
    private TextField etat;

    @FXML
    private ComboBox<String> livreurs;

    @FXML
    private TableView<Commande> mytable ;
    Long id;

    @FXML
    private TableColumn<Commande, Long> col_id;
    @FXML
    private TableColumn<Commande, Date> col_ddebut;
    @FXML
    private TableColumn<Commande, Date> col_dfin;
    @FXML
    private TableColumn<Commande, Float> col_dist;
    @FXML
    private TableColumn<Commande, String> col_client;
    @FXML
    private TableColumn<Commande, String> col_livreur;
    @FXML
    private TableColumn<Commande, String> col_etat;

    @FXML
    protected void onLoadButtonClick() {
        // access a la bdd
        try {
            CommandeDAO commandeDAO = new CommandeDAO();

            commandeDAO.load(livreurs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onSaveButtonClick() {
        // access a la bdd
        try {
            CommandeDAO commandeDAO = new CommandeDAO();

            float distValue = Float.parseFloat(dist.getText());
            Commande cmd = new Commande(0l , ddebut.getText(), dfin.getText() , distValue, client.getText(), livreurs.getValue() ,etat.getText());

            commandeDAO.saveCmd(cmd, livreurs);

            UpdateTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onUpdateButtonClick() {
        // access a la bdd
        try {
            CommandeDAO commandeDAO = new CommandeDAO();

            float distValue = Float.parseFloat(dist.getText());
            Commande cmd = new Commande(0l , ddebut.getText(), dfin.getText() , distValue, client.getText(), livreurs.getValue(), etat.getText());

            commandeDAO.updateCmd(cmd, id, livreurs);

            UpdateTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onDeleteButtonClick() {
        // access a la bdd
        try {
            CommandeDAO commandeDAO = new CommandeDAO();

            float distValue = Float.parseFloat(dist.getText());
            Commande cmd = new Commande(0l , ddebut.getText(), dfin.getText() , distValue, client.getText(), livreurs.getValue(), etat.getText());

            commandeDAO.delete(cmd, id);

            UpdateTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onClearButtonClick() {
        ddebut.setText(null);
        dfin.setText(null);
        dist.setText(null);
        client.setText(null);
        etat.setText(null);
        btnSave.setDisable(false);
    }

    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Commande,Long>("id_commande"));
        col_ddebut.setCellValueFactory(new PropertyValueFactory<Commande,Date>("date_debut"));
        col_dfin.setCellValueFactory(new PropertyValueFactory<Commande,Date>("date_fin"));
        col_dist.setCellValueFactory(new PropertyValueFactory<Commande,Float>("distance"));
        col_client.setCellValueFactory(new PropertyValueFactory<Commande,String>("client"));
        col_livreur.setCellValueFactory(new PropertyValueFactory<Commande,String>("livreur"));
        col_etat.setCellValueFactory(new PropertyValueFactory<Commande,String>("etat"));

        mytable.setItems(this.getDataCommandes());
    }

    public static ObservableList<Commande> getDataCommandes(){
        CommandeDAO commandeDAO = null;

        ObservableList<Commande> listfx = FXCollections.observableArrayList();

        try {
            commandeDAO = new CommandeDAO();
            for (Commande ettemp : commandeDAO.getAll())
                listfx.add(ettemp);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
    }

    public void getData(javafx.scene.input.MouseEvent mouseEvent) {
        Commande commande = mytable.getSelectionModel().getSelectedItem();
        id = commande.getId_commande();
        ddebut.setText(String.valueOf(commande.getDate_debut()));
        dfin.setText(String.valueOf(commande.getDate_fin()));
        dist.setText(Float.toString(commande.getDistance()));
        client.setText(commande.getClient());
        livreurs.setValue(commande.getLivreur());
        etat.setText(commande.getEtat());
        btnSave.setDisable(true);
    }

    @FXML
    void switchToHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent homeParent = loader.load();
        Scene homeScene = new Scene(homeParent);
        Stage window = (Stage) cmd.getScene().getWindow();
        window.setScene(homeScene);
        window.show();
    }
}
