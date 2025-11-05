package com.cda.camping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.camping.model.Client;
import com.cda.camping.repository.ClientRepository;

/**
 * Service métier pour la gestion des clients
 *
 * Cette classe contient la logique métier de l'application.
 * Elle fait le lien entre les controllers (couche web) et les repositories (couche données).
 *
 * @Service : Annotation Spring qui indique que cette classe est un service métier.
 *            Spring va automatiquement la détecter et la gérer comme un bean.
 *
 * ARCHITECTURE EN COUCHES :
 * Controller -> Service -> Repository -> Base de données
 *
 * Le Service permet de :
 * - Centraliser la logique métier
 * - Réutiliser la même logique dans plusieurs controllers
 * - Gérer les transactions
 * - Ajouter des validations métier
 */
@Service
public class ClientService {

    /**
     * Injection automatique du repository
     * @Autowired : Spring injecte automatiquement l'instance de ClientRepository
     */
    @Autowired
    private ClientRepository clientRepository;

    /**
     * Récupère tous les clients
     *
     * @return Liste de tous les clients
     */
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    /**
     * Récupère un client par son identifiant
     *
     * @param id L'identifiant du client
     * @return Le client correspondant ou null
     */
    public Client getClient(Integer id) {
        return clientRepository.findById(id);
    }

    /**
     * Crée un nouveau client
     *
     * Ici on pourrait ajouter des validations métier avant de sauvegarder
     * (ex: vérifier que le téléphone est au bon format, que le nom n'est pas vide, etc.)
     *
     * @param client Le client à créer
     */
    public void createClient(Client client){
        clientRepository.save(client);
    }

    /**
     * Met à jour un client existant
     *
     * @param client Le client avec les nouvelles données
     */
    public void updateClient(Client client){
        clientRepository.update(client);
    }

    /**
     * Supprime un client
     *
     * @param id L'identifiant du client à supprimer
     */
    public void deleteClient(Integer id){
        clientRepository.delete(id);
    }
}

    


