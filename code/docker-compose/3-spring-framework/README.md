# Exemple de construction d'image Spring

## Prérequis

- [Installation de Docker](../../../docs/installation/docker_install.html)
- Vérifier que Docker est lancé :

  ```shell
  # La commande suivante :
  $ docker -v

  # doit vous retourner :
  Docker version 19.03.5, build 633a0ea
  ```

## Construire l'image

Pour construire l'image depuis le fichier Dockerfile, executez la commande suivante :

> **ATTENTION**: Bien ajouté l'espace point à la fin de la ligne de commande.

```shell
# Construction de l'image
$ docker build -t test .
```

Cette commande construira une image nommée `test` depuis le fichier Dockerfile situé dans le répertoire `.` (ici le répertoire courant).

## Vérifier l'image créée

Dans un terminal, executer la commande suivante :

```shell
# Lister les images locales
$ docker image ls
```

Vous deriez voir l'image `dockerfiles/static-site` :

```shell
$ docker image ls
REPOSITORY                 TAG                 IMAGE ID            CREATED             SIZE
...
test                       latest              753e29b9fdd0        11 seconds ago      97.3MB
...
```

## Lancer l'image

Pour démarrer le container depuis l'image créée, executer la commande suivante dans un terminal :

```shell
docker run -d -p 8080:8080 test
```

Pour valider que votre image fonctionne bien, vous pouvez exécuter ce script dans un teerminal :

```shell
# Affiche le contenu de la requête http://localhost:8080 (10 fois)
$ for i in {0..9}; do curl -s localhost:8080 ; echo "" ; sleep 1 ; done
```

## Arrêter le container

Pour arrêter le container précédemment lancé, vous pouvez exécuter la commande suivante :

```shell
$ docker stop 753e29b9fdd0

# ou en précisant que l'on travaille sur un container
$ docker container stop 753e29b9fdd0

# ou en précisant le nom du container
$ docker stop test
```

## Sources

Remerciements à Jean-Marc pour ces [sources](https://github.com/jm-devweb/docker-springframework)
