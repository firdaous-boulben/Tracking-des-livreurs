package ma.fstt.trackingl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ma.fstt.model.Livreur;
import ma.fstt.model.LivreurDAO;
import ma.fstt.model.Produit;
import ma.fstt.model.ProduitDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProdController implements Initializable {

    @FXML
    private Button btnSave;

    @FXML
    private VBox prod;

    @FXML
    private TextField nom;
    @FXML
    private TextField prix;
    @FXML
    private TextField desc;

    @FXML
    private TableView<Produit> mytable ;
    Long id;

    @FXML
    private TableColumn<Produit ,Long> col_id ;
    @FXML
    private TableColumn <Produit ,String> col_nom ;
    @FXML
    private TableColumn <Produit ,Float> col_prix ;
    @FXML
    private TableColumn<Produit, String> col_desc;

    @FXML
    protected void onSaveButtonClick() {
        // access a la bdd
        try {
            ProduitDAO produitDAO = new ProduitDAO();

            float prixValue = Float.parseFloat(prix.getText());
            Produit prod = new Produit(0l , nom.getText() , prixValue, desc.getText());

            produitDAO.save(prod);

            UpdateTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onUpdateButtonClick() {
        // access a la bdd
        try {
            ProduitDAO produitDAO = new ProduitDAO();

            float prixValue = Float.parseFloat(prix.getText());
            Produit prod = new Produit(0l , nom.getText() , prixValue, desc.getText());

            produitDAO.update(prod, id);

            UpdateTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onDeleteButtonClick() {
        // access a la bdd
        try {
            ProduitDAO produitDAO = new ProduitDAO();

            float prixValue = Float.parseFloat(prix.getText());
            Produit prod = new Produit(0l , nom.getText() , prixValue, desc.getText());

            produitDAO.delete(prod, id);

            UpdateTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onClearButtonClick() {
        nom.setText(null);
        prix.setText(null);
        desc.setText(null);
        btnSave.setDisable(false);
    }

    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Produit,Long>("id_produit"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom_produit"));
        col_prix.setCellValueFactory(new PropertyValueFactory<Produit,Float>("prix"));
        col_desc.setCellValueFactory(new PropertyValueFactory<Produit,String>("description"));

        mytable.setItems(this.getDataProduits());
    }

    public static ObservableList<Produit> getDataProduits(){
        ProduitDAO produitDAO = null;

        ObservableList<Produit> listfx = FXCollections.observableArrayList();

        try {
            produitDAO = new ProduitDAO();
            for (Produit ettemp : produitDAO.getAll())
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
        Produit produit = mytable.getSelectionModel().getSelectedItem();
        id = produit.getId_produit();
        nom.setText(produit.getNom_produit());
        prix.setText(Float.toString(produit.getPrix()));
        desc.setText(produit.getDescription());
        btnSave.setDisable(true);
    }

    @FXML
    void switchToHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent homeParent = loader.load();
        Scene homeScene = new Scene(homeParent);
        Stage window = (Stage) prod.getScene().getWindow();
        window.setScene(homeScene);
        window.show();
    }
}