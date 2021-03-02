# Projet H2021 - Énoncé

## Description

Dans le cadre du projet de session d'hiver 2021 du cours GLO-2003, vous aurez l'occasion de développer un **service de vente aux enchères** nommé **eBaie** (version québécoise cheap de *eBay*). 

## Technologies

Le projet utilise un serveur **Jetty** et le framework d'API **Jersey**.

:warning: **Aucune autre librarie ne sera permise sans l'approbation du responsable.**

## Requis

- Java (JDK) version **8**
- Maven
- Votre IDE / éditeur de texte préféré (**IntelliJ** hautement recommandé)

## Préparation dans IntelliJ

1. Ouvrir le projet à l'aide de l'option `Open or import`.
2. Dans `File > Project Structure > Project`, sélectionner la version de java que vous venez d'installer (8) pour les section `Project SDK` et `Project language level`.
3. Ouvrir le fichier `EBaieMain.java` (peut être trouvé avec le raccourci clavier `Ctrl+Shift+N`), cliquer sur la flèche à côté de la fonction `main`, puis sur `Run 'EBaieMain.main()'`. Ceci démarrera l'application. Si tout se passe bien, un carré rouge d'arrêt devrait apparaître en haut à droite. Il est normal que du texte rouge de logging apparaisse dans le terminal.
4. Cliquer sur le bouton d'arrêt afin d'arrêter l'application. Puis, juste à côté, cliquer sur le menu déroulant `EBAIEMAIN`, puis cliquer `Save 'EBAIEMAIN' configuration`.

Et voilà! Tout est prêt pour l'utilisation!

## Structure

La structure des packages suit l'architecture Clean avec séparation verticale des modules.

- **`src.main.java.ca.ulaval.glo2003.ebaie`**: Racine du code source. Est ensuite séparé en *modules*, c'est-à-dire des packages qui contiennent les différentes couches pour chaque *parties* ou *features* du projet.
  - **`app`**: Module technologique contenant le setup Jetty+Jersey de l'app. On pourrait facilement y ajouter un setup utilisant d'autres technologies, mais le tout est interfacé à l'aide de l'interface `EBaieApplication`. Donc le fichier `Main` reste vide et propre.
  - **`ping`**: Module logique contenant tout ce qui est relié à la feature de *ping*.
    - **`api`**: Contient les fichiers reliés à Jersey, la descriptions des *endpoints*, les objets de requêtes et de réponses ainsi que les assembleurs de la couche.
      - **`dtos`**: Contient les objets de requêtes et de réponses
      - **`assemblers`**: Contient les classes qui transforment les requêtes simples en dto applicatifs, et vice-versa en réponses simples.
      - **`PingResource.java`**: Fichier de description des *endpoints* du module `ping`. Ce qu'on appelle aussi un *contrôlleur*. 
    - **`application`**: Contient ce qu'on appelle les *use cases*, c'est-à-dire les cas d'utilisation. Ceux-ci vont *chercher* les informations, *ordonnent* les actions et appelent les *services externes*.
      - **`dtos`**: Contient les objets *input* et *output* des *use cases*.
      - **`assemblers`**: Contient les classes qui transforments les *entities* en *ouput* de *use cases*, et vice-versa avec les *input*.
      - **`PingUseCase`**: Contient les cas d'utilisation du module `ping`. Aussi appelé *service* (à lire ***application** service*).
    - **`entities`**: Contient le domaine du projet. Contient les objets pures qui représentent la hiérarchie du domaine. On y retrouve également les interfaces principales.
    - **`infrastructure`**: Contient les différentes classes et implémentations propres à une technologie.
      - **`persistence`**: Contient les implémentations propres à la persistance des données. 
  - **`EBaieMain.java`**: Fichier racine pour le démarrage de l'application
- **`src.test.java.ca.ulaval.glo2003.ebaie`**: Racine des tests. Le reste de la structure doit suivre celle de la racine source.
