import csv
import random
import string
from datetime import datetime, timedelta

# Définition des valeurs pour les champs
id_client = 1
id_client_max = 100000
cin_set = set()
cin_max = 100000
adresse_set = ['5 Rue de la Paix', '10 Avenue des Champs-Elysées', '15 Rue du Commerce']
email_domains = ['gmail.com', 'yahoo.com', 'hotmail.com', 'outlook.com']
tel_prefixes = ['06', '07']
firstname_set = ['Emma', 'Liam', 'Olivia', 'Noah', 'Ava', 'Lucas', 'Mia', 'Ethan', 'Sophia', 'Jackson']
lastname_set = ['Smith', 'Johnson', 'Brown', 'Lee', 'Garcia', 'Martinez', 'Davis', 'Wilson', 'Anderson']


header = ['idCb', 'cinClient', 'rib', 'date_creation', 'solde', 'type_de_compte']

dataCompte = []
# Génération des données aléatoires
data = []
while id_client <= id_client_max:
    cin = ''.join(random.choice(string.digits) for _ in range(8))
    if cin not in cin_set:
        cin_set.add(cin)
        adresse = random.choice(adresse_set)
        email = f"{random.choice(firstname_set)}.{random.choice(lastname_set)}@{random.choice(email_domains)}"
        firstname = random.choice(firstname_set)
        lastname = random.choice(lastname_set)
        tel = f"{random.choice(tel_prefixes)}{''.join(random.choice(string.digits) for _ in range(8))}"
        date_naissance = datetime.now() - timedelta(days=random.randint(5000, 15000))
        date_inscription = datetime.now() - timedelta(days=random.randint(0, 365))
        data.append([id_client, adresse, cin, date_inscription.strftime('%Y-%m-%d'), date_naissance.strftime('%Y-%m-%d'), email, firstname, lastname, tel])
        id_client += 1
for i in range(100000):
    idCb = i + 1
    cinClient = random.choice(list(cin_set)) # On choisit un cin existant pour un client existant
    rib = ''.join(random.choice(string.digits + string.ascii_uppercase) for _ in range(24))
    date_creation = f"2023-{random.randint(1, 12):02d}-{random.randint(1, 28):02d}"
    solde = round(random.uniform(1000, 10000), 2)
    type_de_compte = random.choice(['Epargne', 'Courant', 'Joint'])
    dataCompte.append([idCb, cinClient, rib, date_creation, solde, type_de_compte])
    
    # Ajouter les valeurs dans une liste de données
    
# Écriture des données dans un fichier CSV
with open('clients_database.csv', 'w', newline='') as f:
    writer = csv.writer(f, delimiter=';')
    writer.writerow(['idClient', 'Adresse', 'cin', 'date_inscription', 'date_naissance', 'email', 'firstname', 'lastname', 'tel'])
    writer.writerows(data)
with open('comptebancaires_clients_database.csv', mode='w', newline='') as csvfile:
    writer = csv.writer(csvfile, delimiter=';')
    writer.writerow(header)
    writer.writerows(dataCompte)