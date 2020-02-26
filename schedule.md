# Déroulement de l'Atelier Docker

## Préparation

Avant la séance, envoyer la [doc d’installation](docs/installation/docker_install.html) pour chaque OS (comme cela on gagne du temps).

## Le jour J

- [Un petit rappel (~ 15min) de la techno Docker](docs/presentation/docker.html) avec :
  - ~~une explication sur le système de « couche »~~
  - le lexique :
    - docker-hub,
    - docker-engine,
    - la registry locale,
    - les images
    - et les containers
- Finalisation des installations (~15 min)
- Lancement du Docker Hello-World à faire ensemble
- Présentation du DockerHub (~10 min)
- [Explication du DockerFile (~30 min)](docs/presentation/dockerfile.html)
- [Explication du Docker-Compose (~30 min)](docs/presentation/dockercompose.html)
- ~~Application sur une API de Jules (~2 h) par ex :~~
  - ~~API Star Wars, pour montrer les tests dans le conteneur~~
  - ~~ou mieux Avis Restaurants avec :~~
    - ~~un Docker compose comprenant :~~
      - ~~un conteneur pour l’API~~
      - ~~un conteneur pour le front~~
      - ~~un conteneur pour la Base de données~~

- Application sur :
  - Dockerfile :
    - [static-site](code/docker-files/static-site/README.md)
  - Docker Compose
    - [docker-compose simple](code/docker-compose/1-static-site/README.md)
    - [docker-compose multiple](code/docker-compose/2-php-site/README.md)
    - [docker-compose spring](code/docker-compose/3-spring-framework/README.md)
    - docker-compose API-Front
