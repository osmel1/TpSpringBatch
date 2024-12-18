# Traitement des Commandes avec Spring Batch

## Description

Ce projet démontre un job Spring Batch qui traite des commandes à partir d'un fichier CSV. Le job lit les commandes, applique une remise de 10 % sur le montant, et écrit les commandes mises à jour dans une base de données HSQLDB. À la fin du job, il affiche les commandes insérées.

## Fonctionnalités

- **Entrée** : Lecture des commandes depuis un fichier CSV (`orders.csv`).
- **Traitement** : Application d'une remise de 10 % sur le champ `amount` de chaque commande.
- **Sortie** : Écriture des commandes mises à jour dans une base de données HSQLDB.
- **Listener de fin** : Affiche les enregistrements insérés après la fin du job.

## Prérequis

- Java 17 ou supérieur
- Maven
- Base de données HSQLDB (intégrée)

## Structure du Projet

```
├── src/main/java
│   ├── ma/elhachimi/tpspringbatch
│   │   ├── BatchConfig.java                  # Configuration de Spring Batch
│   │   ├── Order.java                       # Définition de l'enregistrement Order
│   │   ├── OrderItemProcessor.java          # Processeur pour appliquer la remise
│   │   ├── JobCompletionNotificationListener.java # Listener de fin de job
│   └── resources
│       └── orders.csv                       # Fichier d'entrée avec des exemples de commandes
└── pom.xml                                  # Dépendances et plugins Maven
```

## Schéma de la Base de Données

La table pour stocker les commandes est définie comme suit :

```sql
DROP TABLE orders IF EXISTS;

CREATE TABLE orders (
    order_id BIGINT IDENTITY PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    amount DOUBLE NOT NULL
);
```

## Format du Fichier CSV

Le fichier d'entrée `orders.csv` doit avoir le format suivant :

```csv
orderId,customerName,amount
1,John Doe,100.0
2,Jane Smith,200.0
3,Robert Johnson,150.5
4,Emily Davis,300.75
5,Michael Brown,80.0
```

## Instructions d'Exécution

1. Clonez le dépôt et accédez au répertoire du projet.

   ```bash
   git clone <repository-url>
   cd spring-batch-orders
   ```

2. Créez le fichier `orders.csv` dans le répertoire `src/main/resources`.


3. Consultez la sortie de la console pour voir les commandes insérées.

## Personnalisation

### Modifier le Taux de Remise

Pour changer le taux de remise, modifiez la classe `OrderItemProcessor` :

```java
@Override
public Order process(Order order) throws Exception {
        Order transformedOrder = new Order(order.orderId(), order.customerName(), order.amount() * 0.9);
        logger.info("Processing order "+order.orderId() +" for customer "+ order.customerName());
        return transformedOrder;
}
```

### Ajuster la Taille des Chunks

Pour modifier le nombre d'enregistrements traités par chunk, mettez à jour la méthode `chunk` dans la classe `BatchConfig` :

```java
.chunk(10, transactionManager)
```

## Exemple de Sortie

Après une exécution réussie, les éléments suivants seront affichés dans la console :

![image](https://github.com/user-attachments/assets/b0ce9147-713e-40b9-8502-d2cc302e6c7a)


## Dépendances

Le projet utilise les dépendances suivantes :

- Spring Boot Starter Batch
- Spring Boot Starter JDBC
- HSQLDB

Ces dépendances sont définies dans le fichier `pom.xml`.



