import pandas as pd 
from scipy.spatial.distance import pdist, squareform
import networkx as nx
import matplotlib.pyplot as plt
import numpy as np

tableau_high = pd.read_csv('topology_high.csv')
tableau_avg = pd.read_csv('topology_avg.csv')
tableau_low = pd.read_csv('topology_low.csv')




def caracteristiques(df, portee, topologie):

    G = nx.Graph()
    # ajout des nœuds
    for i, row in df.iterrows():
        G.add_node(i, pos=(row['x'], row['y'], row['z']))
    
    # ajout des arêtes
    for i in range(len(df)):
        for j in range(i+1, len(df)):
            n_i = df.loc[i, ['x', 'y', 'z']]
            n_j = df.loc[j, ['x', 'y', 'z']]
            distance = np.linalg.norm(n_i - n_j)
            if distance <= portee:
                    G.add_edge(i, j,weight = int((distance)**2))

    # figure pour rassembler toutes les subfigures
    plt.figure(figsize=(16, 16))

    
  
    # Determiner le degré moyen
    degreMoyen = np.mean(list(dict(G.degree()).values()))
    print("Degré moyen:", degreMoyen)

    # Distribution du degré
    plt.subplot(3, 2, 1)
    distributionDegre = list(dict(G.degree()).values())
    plt.plot(distributionDegre)
    plt.title("distribution du degré")
    plt.xlabel("degré du noeud")
    plt.ylabel("nombre de noeuds ayant ce degré")

 
    # Moyenne du coefficient de clustering
    moyenne_clustering = nx.average_clustering(G)
    print("Moyenne du degré de clustering:", moyenne_clustering)

    # Distribution du coefficient de clustering
    plt.subplot(3, 2, 2)
    distributionClustering = list(nx.clustering(G).values())
    plt.scatter(range(len(distributionClustering)), distributionClustering, marker='o', color='b', s=15)
    plt.xlabel('noeud')
    plt.ylabel('Degré de Clustering')
    plt.title('distribution du degré de clustering')


    # Calcul du nombre de cliques et leurs ordres
    cliques = list(nx.find_cliques(G))
    nombreCliques = len(cliques)

    # Extraire les tailles des cliques
    tailles_cliques = [len(clique) for clique in cliques]

    # Création de l'histogramme
    plt.subplot(3, 2, 3)
    plt.hist(tailles_cliques, bins=range(min(tailles_cliques), max(tailles_cliques) + 2), align='left', rwidth=0.8)
    plt.xlabel('Taille des cliques')
    plt.ylabel('Nombre de cliques')
    plt.title('Histogramme de la taille des cliques')


    print("Nombre de cliques:", nombreCliques)

    # Calcul du nombre de composantes connexes
    composantes_connexes = list(nx.connected_components(G))
    nombreCC = len(composantes_connexes)
    print("Nombre de composantes connexes dans le graphe:", nombreCC)


    # Extraire les tailles des composantes connexes
    tailleCC = [len(comp) for comp in composantes_connexes]

    # Création de l'histogramme
    plt.subplot(3, 2, 4)
    plt.hist(tailleCC, bins=range(min(tailleCC), max(tailleCC) + 2), align='left', rwidth=0.8)
    plt.xlabel('Taille des composantes connexes')
    plt.ylabel('Nombre de composantes connexes')
    plt.title('Histogramme de la taille des composantes connexes')

    
    i = 0
    j = 0
    longueurs = []
    all_shortest_paths = []
    for source in G.nodes():
        j = 0
        for target in G.nodes():
            if source != target:
                try:
                    # Calcul de la longueur du plus court chemin entre la source et la cible
                    shortest_path_length = nx.shortest_path_length(G, source=source, target=target, weight='weight')
                    # Vérifier si le chemin inverse existe déjà
                    inverse_exists = any([item[0] == str(j) + "->" + str(i) for item in longueurs])

                    if not inverse_exists:  
                        longueurs.append([str(i) + "->" + str(j), shortest_path_length])

                        paths = nx.all_shortest_paths(G, source=source, target=target, weight='weight')
                        all_shortest_paths.extend(paths)
                
                except nx.NetworkXNoPath:
                    pass  # Ignorer les paires sans chemin
            j += 1
        i += 1

 
    print("\nNombre total de plus courts chemins en considérant un seul chemin entre deux noeuds:", len(longueurs))
    print("\nNombre total de plus courts chemins en considérant tous les chemins possibles entre deux noeuds:", len(all_shortest_paths))

    # Extraction des longueurs des chemins
    longueurs_chemins = [item[1] for item in longueurs]

    # Création de l'histogramme
    plt.subplot(3, 2, 5)
    plt.hist(longueurs_chemins, align='left')
    plt.xlabel('Longueur des plus courts chemins')
    plt.ylabel('Nombre de chemins')
    plt.title('Histogramme des longueurs des plus courts chemins')


    plt.suptitle('Analyse de la topologie du graphe pour une topologie '+ topologie + ' et une portée ' + str(portee) + ' m', color='blue')

    plt.tight_layout()
    plt.subplots_adjust(top=0.916, bottom=0.099, hspace=0.390, wspace=0.119)

    plt.show()
        
    return G







caracteristiques(tableau_high, 60000, 'high')
caracteristiques(tableau_avg, 60000, 'average')
caracteristiques(tableau_low, 60000, 'low')


