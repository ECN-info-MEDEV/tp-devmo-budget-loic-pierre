# DEVMOney Application

## Vue Principale : MyBudget 

### Description 

La vue principale Mybudget issue de **MainActivity** affiche les dernières dépenses **Last Expenses** par catégorie. Ces dépenses sont enregistrées dans une base de donnée qui sera présentée plus bas.

### DATA Components

### UI Components

Le design intitial de cette vue était le suivant : 

!(/MISC/Figma_Vue_Principale)

Nous avons adapté le design pour le rendre plus simple, notamment en remplaçant le diagramme des dépenses par catégories par des barres.

De la même manière, nous avons préféré éliminer l\'affichage par periode pour des raisons de simplicité. Ce qui est affiché correspond donc à l\'ensemble des  

Autrement le reste des éléments d'interface de la vue ont pu être implémentés et correspondent à notre design originel

## Vue secondaire : Operations 

### Description 

Dans cette vue est recensé les différentes opérations de dépenses présentes dans la base de données. 

### DATA Components

### UI Components

Le design intitial de cette vue était le suivant : 

!(/MISC/Figma_Vue_Secondaire)

Tous les éléments ont pu être interprétes
## Base de données

La base de donnée est présente à l'adresse com.patigny_baudet.devmoney/models/database. Elle correspond au modèle suivant : 

 !(/MISC/base_de_données.png) 
 
 Cette base de donnée simple nous permet de stocker toutes les information nécessaires au bon fonctionnement des vues.
 
 
