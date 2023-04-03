package ma.fstt.trackingl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class HelloController {
    @FXML private Button livreurButton;
    @FXML private Button produitButton;
    @FXML private Button commandeButton;

    @FXML private Button logout;

    @FXML private AnchorPane root;

    //Basculer vers L'interface de Gestion des livreurs
    public void switchToLivreur(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("liv-view.fxml"));
        Parent livreurParent = loader.load();
        Scene livreurScene = new Scene(livreurParent);
        Stage window = (Stage) root.getScene().getWindow();
        window.setTitle("Gestion des livreurs");
        window.setScene(livreurScene);
        window.show();
    }

    //Basculer vers L'interface de Gestion des produits
    public void switchToProduit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("prod-view.fxml"));
        Parent produitParent = loader.load();
        Scene produitScene = new Scene(produitParent);
        Stage window = (Stage) root.getScene().getWindow();
        window.setTitle("Gestion des produits");
        window.setScene(produitScene);
        window.show();
    }

    //Basculer vers L'interface de Gestion des commandes
    public void switchToCommande(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cmd-view.fxml"));
        Parent commandeParent = loader.load();
        Scene commandeScene = new Scene(commandeParent);
        Stage window = (Stage) root.getScene().getWindow();
        window.setTitle("Gestion des commandes");
        window.setScene(commandeScene);
        window.show();
    }

    //Basculer vers L'interface de Produits - Commandes
    public void switchToCmdProd(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cmd-prod.fxml"));
        Parent cmdProdParent = loader.load();
        Scene cmdProdScene = new Scene(cmdProdParent);
        Stage window = (Stage) root.getScene().getWindow();
        window.setTitle("Ajout des produits aux commandes");
        window.setScene(cmdProdScene);
        window.show();
    }

    //Déconnexion
    public void switchToLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Parent commandeParent = loader.load();
        Scene commandeScene = new Scene(commandeParent);
        Stage window = (Stage) root.getScene().getWindow();
        window.setTitle("Connexion");
        window.setScene(commandeScene);
        window.show();
    }
}