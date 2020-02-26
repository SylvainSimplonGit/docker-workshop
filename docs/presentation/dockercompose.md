# Docker Compose détails

Compose est un outil pour définir et executer des applications Docker multi container.

Avec Compose, vous utilisez un fichier YAML pour configurer les services de votre application. Il est donc très important de faire attention à l'indentation du fichier.

Ensuite, avec une seule commande, vous créez et démarrez tous les services à partir de votre configuration.

Pour en savoir plus sur toutes les fonctionnalités de Compose, consultez la liste des fonctionnalités.

## Table of Contents

<details>
  <summary>Cliquer pour afficher</summary>

1. [Docker Compose exemple](#docker-Compose-exemple)
1. [Le mot clé **version**](#le-mot-clé-version)
1. [Le mot clé **services**](#le-mot-clé-services)
1. [Le mot clé **build**](#le-mot-clé-build)
1. [Le mot clé **image**](#le-mot-clé-image)
1. [Le mot clé **container_name**](#le-mot-clé-container_name)
1. [Le mot clé **restart**](#le-mot-clé-restart)
1. [Le mot clé **ports**](#le-mot-clé-ports)
1. [Le mot clé **environment**](#le-mot-clé-environment)
1. [Le mot clé **volumes**](#le-mot-clé-volumes)
1. [Le mot clé **networks**](#le-mot-clé-networks)
1. [Annexe](#annexe)

</details>

## Docker Compose exemple

```yaml
version: '3'
services:

  #PHP Service
  web-php:
    build:
      context: .
      dockerfile: Dockerfile
    image: php:fpm-alpine
    container_name: web-php
    restart: unless-stopped
    volumes:
        - "./docker-web/www:/script:ro"
    networks:
      - web-network

  #Nginx Service
  web-nginx:
    image: nginx:stable-alpine
    container_name: web-nginx
    restart: unless-stopped
    ports:
      - "80:80"
      - "443:443"
    networks:
      - web-network

  #MySQL Service
  web-db:
    image: mysql:5.7.22
    container_name: db
    restart: unless-stopped
    ports:
      - "3306:3306"
    environment:
      MYSQL_DATABASE: your_database_name
      MYSQL_ROOT_PASSWORD: your_mysql_root_password
    volumes:
      - dbdata:/var/lib/mysql
    networks:
      - web-network

#Docker Networks
networks:
  web-network:
    driver: bridge

#Volumes
volumes:
  dbdata:
    driver: local
```

## Le mot clé **version**

Il définit la version de la syntaxe utilisé du fichier docker-compose

## Le mot clé **services**

Il définit une section décrivant les services à executer. C'est ce mot clé qui répété pourra vous permettre de lancer plusieurs container liés entre eux.

Le niveau inférieur correspond aux noms des différentes services lancés.

```yaml
...
services:
  web-php:
...
  web-nginx:
...
  web-db:
...
```

Dans cette exemple, 3 services seront lancés lors de l'excution du fichier docker-compose.yml. Pour lancer ces services, Docker lancera 3 containers qui proteront les noms suivants :

- web-php
- web-nginx
- web-db

## Le mot clé **build**

[Documentation officielle](https://docs.docker.com/compose/compose-file/#build)

Il permet de construire son image depuis un fichier Dockerfile. Il peut avoir comme propriété :

- context
- dockerfile
- args

### Le mot clé **context**

Il permet de définir l'emplacement du fichier Dockerfile à utiliser pour la commande **build**.

### Le mot clé **dockerfile**

Il permet de définir le nom du fichier Dockerfile à utiliser pour la commande **build**.

### Le mot clé **args**

Il permet de surcharger le mot clé **build** avec des arguments.

## Le mot clé **image**

[Documentation officielle](https://docs.docker.com/compose/compose-file/#image)

Il permet de définir l'image dont héritera le service.

Situé juste après le mot clé **build**, il définit le nom de l'image construite.

## Le mot clé **container_name**

Il permet de définir le nom du container qui sera lancé pour le service. Cette propriété est particulièrement utilise pour préfixer tous les services d'une application (App, Server Web, DB, ...).

## Le mot clé **restart**

[Documentation officielle](https://docs.docker.com/compose/compose-file/#restart)

Il permet de gérer le redémarrage du service en fonction des états de sortie du container.

Par défaut, sa valeur est à `no`, c'est à dire que le service ne sera pas relancé si le container s'arrête pour n'importe quel raisons (plantage de l'application, arrêt volontaire du container, arrêt de Docker, ...).

Il peut avoir les valeurs suivantes :

- 'no'
  - Pas de relance du service.
- always
  - Le service sera relancé quelque soit la sortie du container. Utile pour des outils de supervision (Portainer) ou de routage (Traefik)
- on-failure
  - le service sera redémarré uniquement lors d'une sortie du code sur erreur.
- unless-stopped
  - Le service sera redémarré uniquement si le service n'a pas été arrêté manuellement.

## Le mot clé **ports**

[Documentation officielle](https://docs.docker.com/compose/compose-file/#ports)

Il permet d'exposer des ports internes au container vers l'extérieur. Il est possible de rediriger un port interne vers un autre port.

### la syntaxe courte

```yaml
ports:
  # Expose le port 3000 externe vers le port 3000 interne
 - "3000"
 # Expose la plage de port 3000 à 3005 externe vers la même plage de port interne
 - "3000-3005"
 # Expose le port 8000 externe vers le port 8000 interne
 - "8000:8000"
 # Expose la plage de port 9090 à 9091 externe vers la même plage de port interne 8080-8081
 - "9090-9091:8080-8081"
 # Expose le port 49100 externe vers le port 22 interne
 - "49100:22"
 - "127.0.0.1:8001:8001"
 - "127.0.0.1:5000-5010:5000-5010"
 - "6060:6060/udp"
```

### La syntaxe longue

```yaml
ports:
  - target: 80        # Le port interne au container exposé
    published: 8080   # Le port externe au container exposé
    protocol: tcp
    mode: host
```

## Le mot clé **environment**

[Documentation officielle](https://docs.docker.com/compose/compose-file/#environment)

Il permet d'ajouter des variables d'environnement dans le container utilisé par le service.

La syntaxe est la suivante :

```yaml
environment:
  MYSQL_DATABASE: your_database_name
  MYSQL_ROOT_PASSWORD: your_mysql_root_password
```

La variable d'environnement MYSQL_DATABASE aura pour valeur `your_database_name`

## Le mot clé **volumes**

[Documentation officielle](https://docs.docker.com/compose/compose-file/#volumes)

Il permet de monter des répertoire ou des volumes nommés pour le partage de données entre services ou pour la persistence des données.

```yaml
volumes:
  # Just specify a path and let the Engine create a volume
  - /var/lib/mysql

  # Specify an absolute path mapping
  - /opt/data:/var/lib/mysql

  # Path on the host, relative to the Compose file
  - ./cache:/tmp/cache

  # User-relative path
  - ~/configs:/etc/configs/:ro
```

Lorsque l'on souhaite pouvoir partager des données via un volume entre les services, il faudra nommé le volume au niveau le plus haut.

```yaml
version: '3.7'

services:
  db:
    image: db
    volumes:
      - data-volume:/var/lib/db
  backup:
    image: backup-service
    volumes:
      - data-volume:/var/lib/backup/data

volumes:
  data-volume:
```

## Le mot clé **networks**

[Documentation officielle](https://docs.docker.com/compose/compose-file/#networks)

Il permet de créé un réseau interne aux différents service de l'application.

```yaml
version: '3'
services:

  #PHP Service
  web-php:
    image: php:fpm-alpine
    networks:
      - web-network

  #Nginx Service
  web-nginx:
    image: nginx:stable-alpine
    ports:
      - "80:80"
      - "443:443"
    networks:
      - web-network

  #MySQL Service
  web-db:
    image: mysql:5.7.22
    ports:
      - "3306:3306"
    networks:
      - web-network

#Docker Networks
networks:
  web-network:
    driver: bridge
```

Dans cet exemple, les services web-php, web-nginx et web-db seront tous démarré dans un réseau interne facilitant ainsi les flux entre eux.

## Annexe

### Liens

- [Docker Compose Overview](https://docs.docker.com/compose/)
- [Compose file v3](https://docs.docker.com/compose/compose-file/)
- [Restart Policy](https://blog.codeship.com/ensuring-containers-are-always-running-with-dockers-restart-policy/)