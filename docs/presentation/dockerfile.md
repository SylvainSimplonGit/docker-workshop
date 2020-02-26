# Dockerfile détails

Les Dockerfiles sont des fichiers qui permettent de construire une image Docker adaptée à nos besoins, étape par étape.

Docker exécute les instructions d'un Dockerfile séquentiellement. Chaque instructions sera lue dans l'ordre du Dockerfile.

Le fichier Dockerfile n'a pas d'extension et se situe en général à la racine de votre projet.

## Sommaire <!-- omit in toc -->

- [Dockerfile exemple](#dockerfile-exemple)
- [Le mot clé **FROM**](#le-mot-clé-from)
- [Le mot clé **RUN**](#le-mot-clé-run)
- [Le mot clé **COPY**](#le-mot-clé-copy)
- [Le mot clé **ADD**](#le-mot-clé-add)
- [Le mot clé **WORKDIR**](#le-mot-clé-workdir)
- [Le mot clé **EXPOSE**](#le-mot-clé-expose)
- [Le mot clé **VOLUME**](#le-mot-clé-volume)
- [Le mot clé **ENV**](#le-mot-clé-env)
- [Le mot clé **LABEL**](#le-mot-clé-label)
- [Le mot clé **USER**](#le-mot-clé-user)
- [Le mot clé **CMD**](#le-mot-clé-cmd)
- [Le mot clé **ENTRYPOINT**](#le-mot-clé-entrypoint)
- [Effets **ENTRYPOINT** & **CMD**](#effets-entrypoint--cmd)
- [Construction d'une image](#construction-dune-image)
- [Lancement du image](#lancement-du-image)
- [Arrêt du container](#arrêt-du-container)
- [Annexe](#annexe)

## Dockerfile exemple

```dockerfile
FROM debian:jessie
RUN apt-get update \
&& apt-get install -y curl \
&& rm -rf /var/lib/apt/lists/*
RUN curl -LO "https://nodejs.org/dist/v0.12.5/node-v0.12.5-linux-x64.tar.gz" \
&& tar -xzf node-v0.12.5-linux-x64.tar.gz -C /usr/local --strip-components=1 \
&& rm node-v0.12.5-linux-x64.tar.gz
ADD package.json /app/
WORKDIR /app
RUN npm install
ADD . /app/
EXPOSE 3000
VOLUME /app/log
CMD node server.js
```

## Le mot clé **FROM**

> Il définit notre image de base, celle dont votre image héritera.

```dockerfile
FROM debian:jessie
...
```

Avant la version v17.05 de Docker, un Dockerfile **ne devait** avoir **une et une seule** instruction **FROM**. Depuis la v17.05 de Docker, il est possible de créer un Dockerfile "multi-stage".

## Le mot clé **RUN**

> Il permet d'exécuter une commande à l'intérieur de votre image comme si vous étiez devant un shell unix.

```dockerfile
...
RUN apt-get update \
&& apt-get install -y curl \
&& rm -rf /var/lib/apt/lists/*
...
```

cette commande nous permet :

- de mettre à jour la base de paquets
- d'installer curl sans validation
- de nettoyer le gestionnaire de paquets

Dans le Dockerfile, chaque commande (ADD, COPY, ENTRYPOINT, CMD, …) fait l’objet d’ajout d’une nouvelle couche à l’image. Et les couches, moins on en a, mieux c’est pour la taille des images.

Sachant qu’en plus, le nombre de couches a une limite (42 auparavant et 127 depuis peu), même si la contrainte du nombre de lignes (ou couches) est quasiment écartée (*si vous avez un Dockerfile avec 127 lignes, c’est qu’il y a un vrai problème !*), regrouper les commandes facilite la compréhension du Dockerfile.

### Un exemple à ne pas faire

```dockerfile
...
RUN apt-get update
RUN apt-get install -y curl
RUN rm -rf /var/lib/apt/lists/*
RUN curl -LO "https://nodejs.org/dist/v0.12.5/node-v0.12.5-linux-x64.tar.gz"
RUN tar -xzf node-v0.12.5-linux-x64.tar.gz -C /usr/local --strip-components=1
RUN rm node-v0.12.5-linux-x64.tar.gz
...
```

Dans cet exemple chaque commande RUN ajoutera une couche à notre image finale.

### Correction

```dockerfile
...
RUN apt-get update \
&& apt-get install -y curl \
&& rm -rf /var/lib/apt/lists/*
RUN curl -LO "https://nodejs.org/dist/v0.12.5/node-v0.12.5-linux-x64.tar.gz" \
&& tar -xzf node-v0.12.5-linux-x64.tar.gz -C /usr/local --strip-components=1 \
&& rm node-v0.12.5-linux-x64.tar.gz
...
```

Dans la correction, toutes les conmmandes permettant l'installation de CURL ont été regroupées, et celles qui executent la commande CURL aussi.

## Le mot clé **COPY**

> Il ne prend en charge que la copie de base des fichiers locaux dans le conteneur.

```dockerfile
...
COPY config.txt /tmp/
...
```

Cette instruction copie simplement le fichier config.txt dans le répertoire /tmp/ de l'image.

Cette instruction se contente tout simplement de prendre un fichier (ou répertoire) du host et de le mettre dans le conteneur à l'emplacement indiqué.

Cette instruction ne prend en charge **que** la copie de base des fichiers locaux dans le conteneur, tandis que **ADD** possède certaines fonctionnalités (comme l'extraction de tar local uniquement et la prise en charge d'URL à distance) qui ne sont pas immédiatement évidentes.

## Le mot clé **ADD**

> Il permet d'ajouter des fichiers locaux ou distants à l'intérieur de votre image.

```dockerfile
...
ADD package.json /app/
...
```

cette commande **ADD** copie le fichier package.json dans le répertoire /app/ de notre future image.

L'instruction **ADD** fait donc la même chose que l'instruction **COPY** sur un simple fichier ou répertoire.

La différence avec l'instruction **COPY** est qu'il peut aussi copier depuis une URL. Dans ce cas, docker se charge du téléchargement et de placer le fichier téléchargé dans le répertoire destination.

Si la source est un fichier zippé et la destination un répertoire, docker dezzipe la source à la volée, exemple :

```dockerfile
ADD file.zip /var/opt/
```

Un peu confus tout ça …

## Le mot clé **WORKDIR**

> Il permet de changer le répertoire courant de votre image.

```dockerfile
...
WORKDIR /app
...
```

Toutes les commandes qui suivront seront exécutées à partir de ce répertoire /app.

## Le mot clé **EXPOSE**

> Il permet d'indiquer quel port nous souhaitons ouvrir depuis l'extérieur de l'image.

```dockerfile
...
EXPOSE 8080
...
```

Cette commande ouvrira le port 8080 depuis l'extérieur.

Par défaut, dans une image, tous les ports sont fermés. Le port 8080 sera donc le seul port d'accès à notre application.

Il est possible d'ouvrir plusieurs ports.

```dockerfile
...
EXPOSE 8080 443
...
```

## Le mot clé **VOLUME**

> Il permet d'indiquer quel dossier nous souhaitons partager.

```dockerfile
...
VOLUME /app/log
...
```

L'instruction VOLUME crée un point de montage avec le nom spécifié.
Cette instruction permet la persistence des données en créant un volume sur le disque.
Ce volume peut être partagé entre container.

> A noter que Docker ne supprimera jamais automatiquement un volume même si plus aucun conteneur n’y fait référence. Ce sera à vous de faire le ménage !

## Le mot clé **ENV**

> Il permet de dénir une variable d'environnement à l'intérieur de l'image.

```dockerfile
...
ENV LOGIN_POSTGRES postgres
ENV PASS_POSTGRES="frozenCat"
...
```

Dans cet exemple, 2 variables d'environnements sont créées :

- *LOGIN_POSTGRES* qui aura pour valeur *postgres*
- *PASS_POSTGRES* qui aura pour valeur *frozenCat*

Cette instruction permet de transmettre efficacement des paramètres de configuration personnalisés au code executé au lancement de l'image.

## Le mot clé **LABEL**

> Il permet d'ajouter des métadonnées à la création de l'image.

```dockerfile
...
LABEL maintainer "Sylvain GRIPPON"
LABEL email "sylvain.grippon.dev@gamil.com"
LABEL Version "1.1.0"
...
```

Cette instruction est utilisée pour tracer l'image :

- A qui appartient cette image ?
- Comment joindre le propriétaire ?
- Quelle est la version de l'application ?
- ...

## Le mot clé **USER**

> Il définit le nom d'utilisateur qui executera les instructions RUN, CMD et ENTRYPOINT qui le suivent dans le Dockerfile.

```dockerfile
...
USER postgres
...
```

Cette instruction définira le USER postgres comme étant celui qui executera les prochaines instructions.
C'est l'équivalent de la commande shell

```shell
su - postgres
```

L'instruction USER définit le nom d'utilisateur (ou UID) et éventuellement le groupe d'utilisateurs (ou GID) à utiliser lors de l'exécution de l'image et pour toutes les instructions RUN, CMD et ENTRYPOINT qui le suivent dans le Dockerfile.

## Le mot clé **CMD**

> Il est utilisé pour définir la commande executée au démarrage du container.

```dockerfile

```

## Le mot clé **ENTRYPOINT**

> Il est utilisé pour surcharger l'instruction CMD.

```dockerfile

```

## Effets **ENTRYPOINT** & **CMD**

Voici les différents effets en fonction de l'écriture du couple **ENTRYPOINT** & **CMD**.

|                            |    No ENTRYPOINT           | ENTRYPOINT exec_entry p1_entry | ENTRYPOINT [“exec_entry”, “p1_entry”]          |
|----------------------------|:--------------------------:|:------------------------------:|:----------------------------------------------:|
| No CMD                     | error, not allowed         | /bin/sh -c exec_entry p1_entry | exec_entry p1_entry                            |
| CMD [“exec_cmd”, “p1_cmd”] | exec_cmd p1_cmd            | /bin/sh -c exec_entry p1_entry | exec_entry p1_entry exec_cmd p1_cmd            |
| CMD [“p1_cmd”, “p2_cmd”]   | p1_cmd p2_cmd              | /bin/sh -c exec_entry p1_entry | exec_entry p1_entry p1_cmd p2_cmd              |
| CMD exec_cmd p1_cmd        | /bin/sh -c exec_cmd p1_cmd | /bin/sh -c exec_entry p1_entry | exec_entry p1_entry /bin/sh -c exec_cmd p1_cmd |

## Construction d'une image

Pour construire l'image depuis le fichier Dockerfile, executez la commande suivante :

```shell
# Construction de l'image
$ docker build -t <nom_image>:latest .
```

Cette commande construira une image nommée `<nom_image>` depuis le fichier Dockerfile situé dans le répertoire `.` (ici le répertoire courant) en lui affectant le tag `latest`.

## Lancement du image

Pour démarrer le container depuis l'image créée, executer la commande suivante dans un terminal :

```shell
docker run --name <nom_container> -d <nom_image>
```

Cette commande démarrera un container nommé `<nom_container>` depuis l'image nommée `<nom_image>` ayant le tag `latest` (par défaut).

## Arrêt du container

Pour arrêter le container précédemment lancé, exécuter la commande suivante dans un terminal :

```shell
docker stop <nom_container>
```

## Annexe

### Liens

- [Dockerfile reference](https://docs.docker.com/engine/reference/builder/)
- [Dockerfile Best Practices](https://docs.docker.com/develop/develop-images/dockerfile_best-practices/)
- [Dockerfile precisions](https://www.arolla.fr/blog/2016/09/quelques-precisions-dockerfile/)
- [Dockerfile & Volumes](https://blog.moncoindunet.fr/docker/docker-volumes-donnees-12/)
