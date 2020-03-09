# API Avis restaurants

## Fonctionalités

- Lister les restaurants
- Créer un client
- Créer un avis
- Supprimer un avis
- Lister les avis d'un restaurant

## Classes

- RestaurantType : Enum { BURGER, ITALIEN, ASIAT, FRANCAIS, INDIEN }
- Restaurant
    - Nom : String
    - Adresse : String
    - Nombre de couverts : Integer
    - Type : RestaurantType
- Client
    - Nom : String
    - Prénom : String
    - Email : String
- Avis
    - Note : Integer
    - Commentaire : String
