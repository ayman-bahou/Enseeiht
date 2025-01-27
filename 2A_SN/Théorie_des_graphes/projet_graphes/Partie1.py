import pandas as pd 
from scipy.spatial.distance import pdist, squareform
import networkx as nx
import matplotlib.pyplot as plt
import numpy as np

# extraire les trois tableaux '.csv'
tableau_high = pd.read_csv('topology_high.csv')
tableau_avg = pd.read_csv('topology_avg.csv')
tableau_low = pd.read_csv('topology_low.csv')

def plot_3d_graph (tableau, portee, ax, titre):
    """
    Cette methode trace un graphe en 3D à partir des éléments suivants: un tableau de données, la portée,
    le repere et le titre.

    Args:
        tableau (pd.DataFrame): Tableau contenant les coordonnées des points ainsi que leurs identifiants.
        portee (int): La distance maximale au-delà de laquelle les points ne sont pas connectés.
        ax (matplotlib.axes._subplots.Axes3DSubplot): Instance de l’axe 3D où le graphe sera tracé.
        titre (str): Titre du graphe 3D.
          
    """   
    # Convertir le tableau en DataFrame pour une manipulation plus aisée
    frame = pd.DataFrame(tableau)
    # Extraire les coordonnées des points en 3D
    coordonnee = frame[['x' , 'y' , 'z']]
    # Calculer les distances entre tous les points sous forme d'un vecteur
    vecteurDistances= pdist(coordonnee)
    # Convertir le vecteur des distances en matrice d’adjacence
    matriceDistances = squareform(vecteurDistances)
    G = (matriceDistances<= portee) # Graphique basé sur la portée maximale
    
    # Extraire les coordonnées dans les vecteurs
    x_array = coordonnee['x'].to_numpy()
    y_array = coordonnee['y'].to_numpy()
    z_array = coordonnee['z'].to_numpy()

    # Ajouter les noeuds au graphe
    for i in range(len(x_array)):
        ax.scatter(x_array[i], y_array[i], z_array[i], s=10, label=str(i)) # Noeuds en 3d 
        ax.text(x_array[i], y_array[i], z_array[i], str(frame.loc[i, 'sat_id']), color='red', fontsize=8) # Labels des noeuds 

    # Ajout les arêtes entre les noeuds connectés
    for i in range(len(G)):
        for j in range(i+1, len(G[i])):
            if G[i, j] == 1:  # Si les points sont connectés
                ax.plot([x_array[i], x_array[j]], 
                        [y_array[i], y_array[j]],
                        [z_array[i], z_array[j]], 
                        'k-' ,linewidth=0.5
                )
                
    # Titre du graphe            
    ax.set_title(titre)


# Création d'une figure 3D Pour toplogy_high pour les trois portées
fig = plt.figure()
ax1 = fig.add_subplot(131, projection='3d')
plot_3d_graph(tableau_high, 20000,ax1, 'densité forte à portée 20 km')
ax2 = fig.add_subplot(132, projection='3d')
plot_3d_graph(tableau_high, 40000,ax2, 'densité forte à portée 40 km')
ax3= fig.add_subplot(133, projection='3d')
plot_3d_graph(tableau_high, 60000,ax3, 'densité forte à portée 60 km')
plt.tight_layout()



# Création d'une figure 3D Pour toplogy_avg pour les trois portées
fig = plt.figure()
ax1 = fig.add_subplot(131, projection='3d')
plot_3d_graph(tableau_avg, 20000,ax1, 'densité moyenne à portée 20 km')
ax2 = fig.add_subplot(132, projection='3d')
plot_3d_graph(tableau_avg, 40000,ax2, 'densité moyenne à portée 40 km')
ax3= fig.add_subplot(133, projection='3d')
plot_3d_graph(tableau_avg, 60000,ax3, 'densité moyenne à portée 60 km')
plt.tight_layout()


# Création d'une figure 3D Pour toplogy_low pour les trois portées
fig = plt.figure()
ax1 = fig.add_subplot(131, projection='3d')
plot_3d_graph(tableau_low, 20000,ax1, 'densité faible à portée 20 km')
ax2 = fig.add_subplot(132, projection='3d')
plot_3d_graph(tableau_low, 40000,ax2, 'densité faible à portée 40 km')
ax3= fig.add_subplot(133, projection='3d')
plot_3d_graph(tableau_low, 60000,ax3, 'densité faible à portée 60 km')
plt.tight_layout()


# Affichage des figures
plt.show()