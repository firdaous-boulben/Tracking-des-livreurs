package ma.fstt.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivreurDAO extends BaseDAO<Livreur>{
    public LivreurDAO() throws SQLException {
        super();
    }

    @Override
    public void save(Livreur object) throws SQLException {
        String request = "insert into livreur (nom , telephone) values (? , ?)";

        // mapping objet table
        this.preparedStatement = this.connection.prepareStatement(request);

        // mapping
        this.preparedStatement.setString(1 , object.getNom());
        this.preparedStatement.setString(2 , object.getTelephone());

        this.preparedStatement.execute();
    }

    @Override
    public void update(Livreur object, Long id) throws SQLException {
        String request = "update livreur set nom = ? , telephone = ? where id_livreur = ?";

        // mapping objet table
        this.preparedStatement = this.connection.prepareStatement(request);

        // mapping
        this.preparedStatement.setString(1 , object.getNom());
        this.preparedStatement.setString(2 , object.getTelephone());
        this.preparedStatement.setLong(3 , id);

        this.preparedStatement.execute();
    }

    @Override
    public void delete(Livreur object, Long id) throws SQLException {
        String request = "delete from livreur where id_livreur = ?";

        // mapping objet table
        this.preparedStatement = this.connection.prepareStatement(request);

        // mapping
        this.preparedStatement.setLong(1 , id);

        this.preparedStatement.execute();
    }

    @Override
    public List<Livreur> getAll()  throws SQLException {
        List<Livreur> mylist = new ArrayList<Livreur>();

        String request = "select * from livreur ";

        this.statement = this.connection.createStatement();

        this.resultSet =   this.statement.executeQuery(request);

        // parcours de la table
        while ( this.resultSet.next()){
            // mapping table objet
            mylist.add(new Livreur(this.resultSet.getLong(1) ,
                    this.resultSet.getString(2) ,
                    this.resultSet.getString(3),
                    this.resultSet.getInt(4)));
        }

        return mylist;
    }
}