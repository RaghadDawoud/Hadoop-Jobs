import csv
import random
from faker import Faker


fake = Faker()

NUM_BUYERS = 10000
PURCHASES_PER_BUYER = 100
BUYERS_FILE = 'Buyers.csv'
PURCHASES_FILE = 'Purchases.csv'

def generate_valid_name():
    while True:
        name = fake.name()  # Generate a name
        if 10 <= len(name) <= 15:  # Check if the length is between 10 and 15
            return name

def generate_buyers_dataset():
    with open(BUYERS_FILE, mode='w', newline='') as file:
        writer = csv.writer(file)
        writer.writerow(['BuyerID', 'BuyerName', 'BuyerAge', 'BuyerGender', 'BuyerSalary'])

        # Generating 10,000 buyers
        for buyer_id in range(1, NUM_BUYERS + 1):
            buyer_name = generate_valid_name()  # Get a name
            buyer_age = random.randint(12, 75)
            buyer_gender = random.choice(['male', 'female'])
            buyer_salary = round(random.uniform(3500, 11000), 2)

            writer.writerow([buyer_id, buyer_name, buyer_age, buyer_gender, buyer_salary]) # Writing each buyer's data
def generate_purchases_dataset():
    with open(PURCHASES_FILE, mode='w', newline='') as file:
        writer = csv.writer(file)
        writer.writerow(['purchID', 'BuyerID', 'purchPrice', 'purchNumItems'])  # the header
        purch_id = 1
        # Generating exactly 100 purchases for each buyer
        for buyer_id in range(1, NUM_BUYERS + 1):
            for _ in range(PURCHASES_PER_BUYER):
                purch_price = round(random.uniform(10, 100), 2)  # Random number between 10 and 100
                purch_num_items = random.randint(1, 10)  # Random number between 1 and 10

                writer.writerow([purch_id, buyer_id, purch_price, purch_num_items]) # Writing each purchase's data
                # Increment the purchase ID
                purch_id += 1

# Generate the datasets
generate_buyers_dataset()
print("Buyers dataset created successfully.")

generate_purchases_dataset()
print("Purchases dataset created successfully.")
