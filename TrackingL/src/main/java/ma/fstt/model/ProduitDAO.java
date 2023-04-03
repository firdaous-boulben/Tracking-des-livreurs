package ma.fstt.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO extends BaseDAO<Produit>{
    public ProduitDAO() throws SQLException {
        super();
    }

    @Override
    public void save(Produit object) throws SQLException {
        String request = "insert into produit (nom_produit, prix , description) values (? , ? , ?)";

        // mapping objet table
        this.preparedStatement = this.connection.prepareStatement(request);

        // mapping
        this.preparedStatement.setString(1 , object.getNom_produit());
        this.preparedStatement.setFloat(2 , object.getPrix());
        this.preparedStatement.setString(3 , object.getDescription());

        this.preparedStatement.execute();
    }

    @Override
    public void update(Produit object, Long id) throws SQLException {
        String request = "update produit set nom_produit = ? , prix = ?, description = ? where id_produit = ?";

        // mapping objet table
        this.preparedStatement = this.connection.prepareStatement(request);

        // mapping
        this.preparedStatement.setString(1 , object.getNom_produit());
        this.preparedStatement.setFloat(2 , object.getPrix());
        this.preparedStatement.setString(3 , object.getDescription());
        this.preparedStatement.setLong(4 , id);

        this.preparedStatement.execute();
    }

    @Override
    public void delete(Produit object, Long id) throws SQLException {
        String request = "delete from produit where id_produit = ?";

        // mapping objet table
        this.preparedStatement = this.connection.prepareStatement(request);

        // mapping
        this.preparedStatement.setLong(1 , id);

        this.preparedStatement.execute();
    }

    @Override
    public List<Produit> getAll()  throws SQLException {
        List<Produit> mylist = new ArrayList<Produit>();

        String request = "select * from produit ";

        this.statement = this.connection.createStatement();

        this.resultSet =   this.statement.executeQuery(request);

        // parcours de la table
        while ( this.resultSet.next()){

            // mapping table objet
            mylist.add(new Produit(this.resultSet.getLong(1) ,
                    this.resultSet.getString(2) ,
                    this.resultSet.getFloat(3) ,
                    this.resultSet.getString(4)));

        }

        return mylist;
    }
}