# Projet Camping - Spring MVC (Premier Cours)

Bienvenue Loreileï ! Ce projet est une application Spring MVC développée dans le cadre de notre premier cours sur le framework Spring. Il s'agit d'une API REST complète pour gérer des clients de camping.

## Table des matières

- [À propos](#à-propos)
- [Technologies utilisées](#technologies-utilisées)
- [Architecture du projet](#architecture-du-projet)
- [Prérequis](#prérequis)
- [Installation](#installation)
- [Configuration de la base de données](#configuration-de-la-base-de-données)
- [Démarrage de l'application](#démarrage-de-lapplication)
- [Utilisation de l'API](#utilisation-de-lapi)
- [Structure du code](#structure-du-code)
- [Concepts Spring abordés](#concepts-spring-abordés)

## À propos

Cette application est une API REST qui permet de gérer une liste de clients pour un camping. Elle implémente les opérations CRUD (Create, Read, Update, Delete) et utilise une architecture en couches typique des applications Spring.

## Technologies utilisées

- **Java 17** - Langage de programmation
- **Spring Framework 6.1.13** - Framework principal
  - Spring Core - Conteneur IoC et injection de dépendances
  - Spring MVC - Framework web pour créer des API REST
  - Spring JDBC - Accès simplifié à la base de données
- **MySQL 8.0** - Base de données relationnelle
- **Maven** - Gestion des dépendances et build
- **Lombok** - Réduction du code boilerplate (getters/setters)
- **Jackson** - Sérialisation/désérialisation JSON
- **Jakarta Servlet API** - API pour les servlets
- **Jakarta Persistence API (JPA)** - Annotations pour les entités

## Architecture du projet

Le projet suit une architecture en couches (Layered Architecture), un pattern très courant dans les applications Spring :

```
┌─────────────────────────────────────────┐
│         Controller Layer                │  ← Reçoit les requêtes HTTP
│   (ClientController, DemoController)    │     et retourne des réponses JSON
└──────────────┬──────────────────────────┘
               │
               ▼
┌─────────────────────────────────────────┐
│          Service Layer                  │  ← Contient la logique métier
│         (ClientService)                 │
└──────────────┬──────────────────────────┘
               │
               ▼
┌─────────────────────────────────────────┐
│        Repository Layer (DAO)           │  ← Accède à la base de données
│       (ClientRepository)                │
└──────────────┬──────────────────────────┘
               │
               ▼
┌─────────────────────────────────────────┐
│          MySQL Database                 │  ← Stocke les données
│        (camping_spring)                 │
└─────────────────────────────────────────┘
```

## Prérequis

Avant de commencer, assure-toi d'avoir installé :

- **Java JDK 17** ou supérieur
- **MySQL 8.0** ou supérieur
- **Maven 3.6+** (ou utiliser le wrapper Maven inclus)
- **Un serveur d'applications** compatible Jakarta EE (Tomcat 10+, etc.)
- **Un client HTTP** pour tester l'API (Postman, Insomnia, Thunder Client, etc.)

## Installation

1. Clone ce repository :
   ```bash
   git clone git@github.com:Jeeiib/coursSping.git
   cd camping
   ```

2. Installe les dépendances Maven :
   ```bash
   mvn clean install
   ```

## Configuration de la base de données

### 1. Créer la base de données

Connecte-toi à MySQL et exécute :

```sql
CREATE DATABASE camping_spring;
USE camping_spring;
```

### 2. Créer la table clients

```sql
CREATE TABLE clients (
    id_client INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    adresse VARCHAR(255),
    telephone VARCHAR(15),
    id_user INT
);
```

### 3. Insérer des données de test (optionnel)

```sql
INSERT INTO clients (nom, prenom, adresse, telephone, id_user) VALUES
('Dupont', 'Jean', '123 rue de Paris', '0612345678', 1),
('Martin', 'Sophie', '456 avenue de Lyon', '0698765432', 2),
('Durand', 'Pierre', '789 boulevard de Marseille', '0623456789', 3);
```

### 4. Vérifier la configuration

La configuration de la base de données se trouve dans `AppConfig.java` :

```java
dataSource.setUrl("jdbc:mysql://localhost:3306/camping_spring");
dataSource.setUsername("root");
dataSource.setPassword("root");
```

Si tes identifiants MySQL sont différents, modifie ces valeurs dans :
`src/main/java/com/cda/camping/config/AppConfig.java`

## Démarrage de l'application

### Avec Maven et un serveur Tomcat

1. Build le projet :
   ```bash
   mvn clean package
   ```

2. Le fichier WAR sera généré dans `target/camping.war`

3. Déploie ce fichier WAR sur ton serveur Tomcat (copie-le dans le dossier `webapps` de Tomcat)

4. Démarre Tomcat

5. L'application sera accessible à : `http://localhost:8080/camping/`

## Utilisation de l'API

Voici les endpoints disponibles :

### 1. Route de test (Demo)

**GET** `/api/demo`

Retourne simplement "Hello World" pour vérifier que l'application fonctionne.

```bash
curl http://localhost:8080/camping/api/demo
```

**Réponse :**
```json
"Hello World"
```

---

### 2. Récupérer tous les clients

**GET** `/api/clients`

```bash
curl http://localhost:8080/camping/api/clients
```

**Réponse :**
```json
[
  {
    "id": 1,
    "nom": "Dupont",
    "prenom": "Jean",
    "adresse": "123 rue de Paris",
    "telephone": "0612345678",
    "idUser": 1
  },
  {
    "id": 2,
    "nom": "Martin",
    "prenom": "Sophie",
    "adresse": "456 avenue de Lyon",
    "telephone": "0698765432",
    "idUser": 2
  }
]
```

---

### 3. Récupérer un client par son ID

**GET** `/api/clients/{id}`

```bash
curl http://localhost:8080/camping/api/clients/1
```

**Réponse :**
```json
{
  "id": 1,
  "nom": "Dupont",
  "prenom": "Jean",
  "adresse": "123 rue de Paris",
  "telephone": "0612345678",
  "idUser": 1
}
```

---

### 4. Créer un nouveau client

**POST** `/api/clients`

```bash
curl -X POST http://localhost:8080/camping/api/clients \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Durand",
    "prenom": "Marie",
    "adresse": "789 rue de Bordeaux",
    "telephone": "0656781234",
    "idUser": 3
  }'
```

**Réponse :**
```json
{
  "message": "Client ajouté avec succès"
}
```

---

### 5. Modifier un client existant

**PUT** `/api/clients`

```bash
curl -X PUT http://localhost:8080/camping/api/clients \
  -H "Content-Type: application/json" \
  -d '{
    "id": 1,
    "nom": "Dupont",
    "prenom": "Jean-Michel",
    "adresse": "123 rue de Paris",
    "telephone": "0612345678",
    "idUser": 1
  }'
```

**Réponse :**
```json
{
  "message": "Client modifié avec succès"
}
```

---

### 6. Supprimer un client

**DELETE** `/api/clients/{id}`

```bash
curl -X DELETE http://localhost:8080/camping/api/clients/1
```

**Réponse :**
```json
{
  "message": "Client supprimé avec succès"
}
```

## Structure du code

Voici l'organisation du projet :

```
camping/
├── src/main/java/com/cda/camping/
│   ├── App.java                          # Point d'entrée de l'application
│   ├── config/
│   │   └── AppConfig.java                # Configuration Spring (remplace web.xml)
│   ├── controller/
│   │   ├── ClientController.java         # Contrôleur REST pour les clients
│   │   └── DemoController.java           # Contrôleur de démonstration
│   ├── service/
│   │   └── ClientService.java            # Logique métier
│   ├── repository/
│   │   └── ClientRepository.java         # Accès aux données (DAO)
│   └── model/
│       └── Client.java                   # Entité JPA représentant un client
├── pom.xml                                # Configuration Maven
└── README.md                              # Ce fichier
```

### Explication de chaque couche :

#### **Model (Entité)**
- `Client.java` : Représente la structure d'un client (équivalent à une table en base de données)
- Utilise les annotations JPA (`@Entity`, `@Table`, `@Id`, etc.)
- Utilise Lombok pour générer automatiquement les getters/setters

#### **Repository (DAO - Data Access Object)**
- `ClientRepository.java` : Gère toutes les opérations en base de données
- Utilise `JdbcTemplate` pour exécuter des requêtes SQL
- Méthodes : `findAll()`, `findById()`, `save()`, `update()`, `delete()`

#### **Service**
- `ClientService.java` : Contient la logique métier de l'application
- Fait le lien entre les controllers et les repositories
- C'est ici qu'on pourrait ajouter des validations, des calculs, etc.

#### **Controller**
- `ClientController.java` : Expose les endpoints de l'API REST
- Reçoit les requêtes HTTP, appelle les services, et retourne des réponses JSON
- `DemoController.java` : Contrôleur simple pour tester l'application

#### **Config**
- `AppConfig.java` : Configuration de l'application (base de données, beans, etc.)
- Remplace les fichiers XML traditionnels grâce aux annotations

#### **App**
- `App.java` : Classe principale qui initialise le DispatcherServlet de Spring
- Remplace le fichier `web.xml` grâce à la configuration Java

## Concepts Spring abordés

Voici les concepts Spring que nous avons utilisés dans ce projet :

### 1. Injection de Dépendances (Dependency Injection)
- Utilisation de `@Autowired` pour injecter automatiquement les beans
- Spring gère la création et le cycle de vie des objets

### 2. Inversion de Contrôle (IoC - Inversion of Control)
- Spring crée et gère nos objets (controllers, services, repositories)
- On n'a pas besoin de faire `new ClientService()`, Spring s'en charge

### 3. Annotations Spring
- `@Configuration` : Classe de configuration
- `@Bean` : Déclare un bean géré par Spring
- `@ComponentScan` : Scanne automatiquement les composants
- `@Controller` : Déclare un contrôleur web
- `@Service` : Déclare un service métier
- `@Repository` : Déclare un repository (DAO)
- `@Autowired` : Injection automatique de dépendances

### 4. Spring MVC
- `@RequestMapping` : Définit le préfixe d'URL d'un contrôleur
- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping` : Mapping des méthodes HTTP
- `@PathVariable` : Récupère une variable depuis l'URL
- `@RequestBody` : Convertit le JSON en objet Java

### 5. Spring JDBC
- `JdbcTemplate` : Simplifie l'exécution de requêtes SQL
- Gestion automatique des connexions et des exceptions

### 6. Architecture REST
- Utilisation des verbes HTTP (GET, POST, PUT, DELETE)
- Retour de données en JSON
- Codes de statut HTTP appropriés (200, 201, 500)

### 7. Configuration Java (sans XML)
- Utilisation d'annotations au lieu de fichiers XML
- `AbstractAnnotationConfigDispatcherServletInitializer` remplace `web.xml`
- `@Configuration` + `@Bean` remplacent les fichiers de configuration XML

---

## Notes importantes pour réviser

### Flux d'une requête HTTP dans l'application

```
1. Le client envoie une requête HTTP
   ↓
2. Le DispatcherServlet (configuré dans App.java) reçoit la requête
   ↓
3. Le Controller approprié traite la requête (ex: ClientController)
   ↓
4. Le Controller appelle le Service (ex: ClientService)
   ↓
5. Le Service appelle le Repository (ex: ClientRepository)
   ↓
6. Le Repository exécute une requête SQL via JdbcTemplate
   ↓
7. Les résultats remontent la chaîne (Repository → Service → Controller)
   ↓
8. Le Controller retourne une ResponseEntity avec du JSON
   ↓
9. Spring convertit automatiquement l'objet Java en JSON (grâce à Jackson)
   ↓
10. Le client reçoit la réponse JSON
```

### Pourquoi cette architecture ?

- **Séparation des responsabilités** : Chaque couche a un rôle précis
- **Testabilité** : On peut tester chaque couche indépendamment
- **Maintenabilité** : Le code est organisé et facile à modifier
- **Réutilisabilité** : Les services peuvent être appelés par plusieurs controllers
- **Évolutivité** : Facile d'ajouter de nouvelles fonctionnalités

---

Bon courage pour tes révisions Loreileï ! N'hésite pas si tu as des questions sur le projet.

Projet partagé avec bienveillance par ton camarade de classe.
