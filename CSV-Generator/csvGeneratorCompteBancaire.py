import csv
import random
import string

# Définir les en-têtes du fichier CSV
header = ['idCb', 'cinClient',  'date_creation','idClient','rib', 'solde', 'type_de_compte','code']

# Générer des données aléatoires pour chaque ligne du fichier CSV
data = []
for i in range(1000):
    idCb = i + 1
    cinClient = ''.join(random.choice(string.digits) for _ in range(8))
    date_creation = f"2023-{random.randint(1, 12):02d}-{random.randint(1, 28):02d}"
    idClient=idCb
    rib = ''.join(random.choice(string.digits + string.ascii_uppercase) for _ in range(24))
    
    solde = round(random.uniform(1000, 10000), 2)
    type_de_compte = random.choice(['COURANT'])
    


    # Ajouter les valeurs dans une liste de données
    data.append([idCb, cinClient, date_creation,idClient, rib, solde, type_de_compte,idClient])

# Écrire les données dans un fichier CSV
with open('comptecourant.csv', mode='w', newline='') as csvfile:
    writer = csv.writer(csvfile, delimiter=',')
    writer.writerow(header)
    for row in data:
        writer.writerow(row)
