package exam.sgbd.construction.dao.implement;

import exam.sgbd.construction.dao.DAO;
import exam.sgbd.construction.models.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAO extends DAO<Client> {

    public ClientDAO(Connection conn){
        super(conn);
    }
    @Override
    public boolean create(Client obj) {
        try {
            PreparedStatement state = conn.prepareStatement("INSERT INTO client (nom,NISS,email,adresse) VALUES (?,?,?,?)");
            state.setString(1, obj.get_nom());
            state.setString(2, obj.get_NISS());
            state.setString(3, obj.get_email());
            state.setString(4, obj.get_adresse());
            state.executeUpdate();
            state.close();
            return true;

        }
        catch (SQLException e){
            System.out.println("Vous ne pouvez pas créer le client suivant:" + obj.get_nom());
            return false;
        }
    }

    @Override
    public boolean delete(Client obj) {
        return false;
    }

    @Override
    public boolean update(Client obj) {
        return false;
    }

    /**
     *
     * @param id : index de l'élément à chercher
     * @return
     */
    @Override
    public Client find(int id) {
    Client client = null;
    try{
        PreparedStatement state = conn.prepareStatement("SELECT * FROM Client c WHERE c.id=?");
        state.setInt(1, id);
        ResultSet result =state.executeQuery();
        if(result.first() ) {
            client = new Client(id, result.getString("nom"), result.getString( "NISS"), result.getString("email"),result.getString("adresse"));
        }
    }
    catch (SQLException e){
        System.out.println("Probleme de récupération du client avec l'id:"+id);
    }
    return client;
    }

    public Client findbyname(String name) {
        Client client = null;
        try{
            PreparedStatement state = conn.prepareStatement("SELECT * FROM Client c WHERE c.nom=?");
            state.setString(1, name);
            ResultSet result =state.executeQuery();
            if(result.first() ) {
                client = new Client(result.getInt("id"), result.getString("nom"), result.getString( "NISS"), result.getString("email"),result.getString("adresse"));
            }
        }
        catch (SQLException e){
            System.out.println("Probleme de récupération du client avec le nom:"+name);
        }
        return client;
    }
}
