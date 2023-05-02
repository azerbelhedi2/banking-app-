import csv
import random
import string
from datetime import datetime, timedelta

# Définition des valeurs pour les champs
id_client = 1
id_client_max = 10000
cin_set = set()
cin_max = 10000
adresse_set = ['5 Rue de la Paix', '10 Avenue des Champs-Elysées', '15 Rue du Commerce']
email_domains = ['gmail.com', 'yahoo.com', 'hotmail.com', 'outlook.com']
tel_prefixes = ['06', '07']
firstname_set = ['Emma', 'Liam', 'Olivia', 'Noah', 'Ava', 'Lucas', 'Mia', 'Ethan', 'Sophia', 'Jackson']
lastname_set = ['Smith', 'Johnson', 'Brown', 'Lee', 'Garcia', 'Martinez', 'Davis', 'Wilson', 'Anderson']

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

# Écriture des données dans un fichier CSV
with open('clients.csv', 'w', newline='') as f:
    writer = csv.writer(f, delimiter=';')
    writer.writerow(['idClient', 'Adresse', 'cin', 'date_inscription', 'date_naissance', 'email', 'firstname', 'lastname', 'tel'])
    writer.writerows(data)
