# Exemple de site statique avec un Dockerfile

## Construire l'image

Pour construire l'image depuis le fichier Dockerfile, executez la commande suivante :

```shell
$ docker build -t dockerfiles/static-site:latest .
```

Cette commande construira une image nommée `dockerfiles/static-site` depuis le fichier Dockerfile situé dans le répertoire `.` (ici le répertoire courant) en lui affectant le tag `latest`.

## Vérifier l'image créée

Dans un terminal, executer la commande suivante :

```shell
$ docker ls -a
```

Vous deriez voir l'image `dockerfiles/static-site` :

```shell
$ docker ps -a
CONTAINER ID        IMAGE                     COMMAND                  CREATED              STATUS                       PORTS               NAMES
...
c167e9c97e7d        dockerfiles/static-site   "/bin/sh -c 'cd /usr…"   About a minute ago   Up About a minute            80/tcp              cool_pare
...
```

## Lancer l'image

Pour démarrer le container depuis l'image créée, executer la commande suivante dans un terminal :

```shell
docker run --name static-site -e AUTHOR="Your Name" -d -p 8888:80 dockerfiles/static-site
```

Puis dans votre navigateur, entrer l'URL suivante : <http://localhost:8888/>

Vous devriez voir :

![Resultat](resources/result-html.png)