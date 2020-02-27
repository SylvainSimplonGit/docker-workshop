# Exemple de Poject Apring / Angular / Postgresql

## Prérequis

- [Installation de Docker](../../../docs/installation/docker_install.html)
- Vérifier que Docker est lancé :

  ```shell
  # La commande suivante :
  $ docker -v

  # doit vous retourner :
  Docker version 19.03.5, build 633a0ea
  ```

## Lancer le service

Pour démarrer les containers depuis l'image créée, executer la commande suivante dans un terminal :

```shell
docker-compose up -d
```

Puis dans votre navigateur, entrer l'URL suivante : <http://localhost:8080/>

Vous devriez voir :

![Resultat](resources/result-dockercompose-4.png)

## Arrêter votre service

Pour arrêter le service crée, exécuter la commande suivante dans un terminal :

```shell
docker-compose stop
```

Cette commande arrêtera les services défini dans le fichier docker-compose.yml du répertoire courant.

## Arrêter et nettoyer votre service

Pour arrêter le service crée, exécuter la commande suivante dans un terminal :

```shell
docker-compose down
```

Cette commande arrêtera les services défini dans le fichier docker-compose.yml du répertoire courant puis supprimera les containers et network associés.

## Supprimer les containers liés aux services lancés

Une fois les services arrêtés, vous pouvez supprimer les containers associés via la commande suivante :

```shell
docker-compose rm
```

## Arrêter brutalement les services

Il peut arriver que la commande Stop ne fonctionne pas. Pour forcer l'arrêt des services, exécuter la commande suivante :

```shell
docker-compose kill
```

## Sources

Remerciements à Jules pour ces sources :

- [API](https://github.com/jlsgrand/restaurant-api)
- [Front](https://stackblitz.com/edit/jlsgrand-restaurant-review)

### Liens

- [Exemple de Dockerisation d'un projet Angular](https://malcoded.com/posts/angular-docker/)
- [Exemple de Dockerisation d'un projet Spring boot](https://dzone.com/articles/build-package-and-run-spring-boot-apps-with-docker)