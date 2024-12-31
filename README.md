# Hadoop MapReduce Jobs 🐘

This repository contains Java code for implementing two MapReduce jobs in Hadoop. These jobs demonstrate how to process and analyze datasets using the MapReduce framework. The focus is on filtering buyers by age and summarizing purchase data.


## Dataset Creation 🛠️

The datasets (Buyers and Purchases) were generated using Python in the **`DataSet.py`** file. This Python code creates realistic and varied synthetic data, specifically tailored for processing with Hadoop MapReduce jobs. The diverse datasets enhance the effectiveness of testing and analysis in data-intensive applications.


## Dataset Description 📈

The datasets used are structured as follows:

#### **Buyers Dataset**
Includes details about individual buyers:
- **BuyerID:** Unique sequential number from 1 to 10,000 (the file contains 10,000 buyers).
- **BuyerName:** Random sequence of characters with a length between 10 and 15 (excluding commas from the possible characters).
- **BuyerAge:** Random integer between 12 and 75.
- **BuyerGender:** Randomly generated string, either “male” or “female”.
- **BuyerSalary:** Random float number between 3,500 and 11,000.

#### **Purchases Dataset**
Contains records of purchases, including Buyer IDs and the prices of the purchases:
- **purchID:** Unique sequential integer from 1 to 1,000,000 (the file contains 1M purchases).
- **BuyerID:** References one of the Buyer IDs, i.e., from 1 to 10,000 (on average, a buyer has 100 purchases).
- **purchPrice:** Random float number between 10 and 100.
- **purchNumItems:** Random integer between 1 and 10.


## MapReduce Jobs 📊

#### 1️. Buyers Job (Map-only)
- **Goal:** Identify buyers whose age falls between 20 and 50.
- **Input:** A CSV file containing buyer data.
- **Output:** Filtered records with buyer details.
- **Code File:** `BuyersFirstJob.java`

#### 2️. Purchases Job (MapReduce)
- **Goal:** Compute for every buyer, the total number of purchases and the total sum of purchase prices.
- **Input:** A CSV file containing purchase data.
- **Output:** Aggregated results for each buyer.
- **Code File:** `PurchasesJob2.java`


## Files in the Repository 📁

### **Dataset Files**
- **`DataSet.py`**: Python script for dataset generation.
- **`Buyers.csv`**: CSV file containing buyer data.
- **`Purchases.csv`**: CSV file containing purchase data.


### **Code Files**
- **`BuyersFirstJob.java`**: Java code for the first Map-Only job.
- **`BuyersFirstJob$Test.java`**: Test file for the first Map-Only job.
- **`PurchasesJob2.java`**: Java code for the second MapReduce job.
- **`PurchasesJob2$Test.java`**: Test file for the second MapReduce job.


### **Build Files**
- **`pom.xml`**: Maven configuration file.
- **`job1.jar`**: Compiled JAR file for the first Map-Only job.
- **`job2.jar`**: Compiled JAR file for the second MapReduce job.


### **Output Files**
- **`part-r-00000`**: Result file of the job.
- **`_SUCCESS`**: Marker file indicating successful completion of the MapReduce job. It is generated automatically by Hadoop in the output directories.

