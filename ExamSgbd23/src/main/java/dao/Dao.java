package main.java.dao;

import main.java.singleton.ConnectionSingleton;

import java.sql.Connection;
import java.util.List;

public abstract class Dao<T> {

    protected Connection connection;

    public Dao() {
        this.connection = ConnectionSingleton.getConnection();
    }

    /**
     * Méthode de création
     *
     * @param t : l'objet à ajouter dans la base de données
     * @return :
     * - true : si l'objet a bien été créé
     * - false : si l'objet n'est pas créé
     */
    public abstract boolean create(T t);

    /**
     * Méthode de suppression
     *
     * @param t : l'objet à supprimer de la base de donnée
     * @return - true : si l'objet a bien été supprimé
     * - false : si l'objet n'est pas supprimé
     */
    public abstract boolean delete(T t);

    /**
     * Méthode de mise à jour de l'objet dans la base de donnée
     *
     * @param t : l'objet à mettre à jour
     * @return - true : si l'objet a bien été mis à jour
     * - false : si l'objet n'est pas mis à jour
     */
    public abstract boolean update(T t);

    /**
     * Méthode de recherche d'information en base de données
     *
     * @param id : l'identifiant de l'objet recherché
     * @return T : l'objet recherché
     */
    public abstract T find(int id);

    /**
     * Méthode de recherche d'information en base de données
     * Principalement utilisé pour vérifier si une entrée existe déjà en base de données
     *
     * @param t l'object sans id spécifié
     * @return T : l'object complet
     */
    public abstract T find(T t);

    /**
     * Méthode de rechecrche d'information en base de données
     *
     * @return List<T> : la liste de tous les objets du même type
     */
    public abstract List<T> all();
}
