package com.cda.camping.repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cda.camping.model.Client;

/**
 * Repository (DAO - Data Access Object) pour l'entité Client
 *
 * Cette classe gère l'accès aux données de la table "clients" dans la base de données.
 * Elle contient toutes les méthodes pour effectuer des opérations CRUD (Create, Read, Update, Delete).
 *
 * @Repository : Annotation Spring qui indique que cette classe est un composant de la couche DAO.
 *               Spring va automatiquement la détecter et la gérer comme un bean.
 */
@Repository
public class ClientRepository  {

    /**
     * JdbcTemplate : Outil Spring pour exécuter des requêtes SQL
     * @Autowired : Spring injecte automatiquement l'instance de JdbcTemplate
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Récupère tous les clients de la base de données
     *
     * @return Liste de tous les clients
     */
    public List<Client> findAll(){
        // Requête SQL pour sélectionner tous les clients
        String sql = "SELECT id_client, nom, prenom, adresse, telephone, id_user FROM clients";

        // jdbcTemplate.query() exécute la requête et transforme le ResultSet en objets Java
        return jdbcTemplate.query(sql, (ResultSet resultSet) -> {
            List<Client> clients = new ArrayList<>();

            // Parcourir chaque ligne du résultat
            while (resultSet.next()){
                Client c = new Client();
                // Remplir l'objet Client avec les données de la base
                c.setId(resultSet.getInt("id_client"));
                c.setNom(resultSet.getString("nom"));
                c.setPrenom(resultSet.getString("prenom"));
                c.setAdresse(resultSet.getString("adresse"));
                c.setTelephone(resultSet.getString("telephone"));
                c.setIdUser(resultSet.getInt("id_user"));
                clients.add(c);
            }
            return clients;
     });

    }

    /**
     * Récupère un client par son identifiant
     *
     * @param id L'identifiant du client à rechercher
     * @return Le client trouvé, ou null si aucun client ne correspond
     */
    public Client findById(Integer id){
        // Le "?" est un paramètre préparé (protection contre les injections SQL)
        String sql = "SELECT id_client, nom, prenom, adresse, telephone, id_user FROM clients WHERE id_client = ?";

        // Le paramètre "id" remplace le "?" dans la requête
        return jdbcTemplate.query(sql, (ResultSet resultSet) -> {
            // next() retourne true s'il y a un résultat
            if (resultSet.next()){
                Client c = new Client();
                c.setId(resultSet.getInt("id_client"));
                c.setNom(resultSet.getString("nom"));
                c.setPrenom(resultSet.getString("prenom"));
                c.setAdresse(resultSet.getString("adresse"));
                c.setTelephone(resultSet.getString("telephone"));
                c.setIdUser(resultSet.getInt("id_user"));
                return c;
            }
            return null; // Aucun client trouvé
        }, id);

    }

    /**
     * Insère un nouveau client dans la base de données
     *
     * @param c L'objet Client à insérer
     * @return Le nombre de lignes affectées (normalement 1 si succès)
     */
    public int save(Client c){
        // Insertion sans spécifier id_client (auto-incrémenté)
        String sql = "INSERT INTO clients (nom, prenom, adresse, telephone, id_user) VALUES (?, ?, ?, ?, ?)";

        // Les "?" sont remplacés par les valeurs du client dans l'ordre
        return jdbcTemplate.update(sql, c.getNom(), c.getPrenom(), c.getAdresse(), c.getTelephone(), c.getIdUser());
    }

    /**
     * Met à jour un client existant dans la base de données
     *
     * @param c L'objet Client avec les nouvelles valeurs
     * @return Le nombre de lignes affectées (normalement 1 si succès)
     */
    public int update(Client c){
        String sql = "UPDATE clients SET nom = ?, prenom = ?, adresse = ?, telephone = ?, id_user = ? WHERE id_client = ?";

        // L'id est passé en dernier car il correspond au dernier "?" (WHERE id_client = ?)
        return jdbcTemplate.update(sql, c.getNom(), c.getPrenom(), c.getAdresse(), c.getTelephone(), c.getIdUser(), c.getId());
    }

    /**
     * Supprime un client de la base de données
     *
     * @param id L'identifiant du client à supprimer
     * @return Le nombre de lignes affectées (normalement 1 si succès)
     */
    public int delete(Integer id){
        String sql = "DELETE FROM clients WHERE id_client = ?";
        return jdbcTemplate.update(sql, id);
    }

}
