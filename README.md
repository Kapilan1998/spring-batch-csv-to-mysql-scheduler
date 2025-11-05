🗂️ spring-batch-csv-to-mysql-scheduler

This is a Spring Boot project that uses Spring Batch and Spring Scheduler to automatically read employee data from a CSV file every 2 minutes and store it into a MySQL database.


🚀 Features

📄 Reads employee data from a CSV file (employees.csv)

⚙️ Processes and saves records into MySQL using Spring Batch

⏰ Automatically runs every 2 minutes using Spring Scheduler

🧾 Tracks job execution metadata using built-in Spring Batch tables

⚙️ How to Set Up and Run

### 1️⃣ Clone the Repository

Open your terminal and run:

```bash
git clone https://github.com/Kapilan1998/spring-batch-csv-to-mysql-scheduler.git
```

```bash
cd spring-batch-csv-to-mysql-scheduler
```

###  2️⃣ Set Up MySQL Database

Create a new database in MySQL and add it in the application.properties file:

###  3️⃣ Initialize Spring Batch Metadata Tables Manually

If you prefer to create the Spring Batch metadata tables yourself:

🧭 Using WSL Terminal:

```bash
cd /mnt/c/Users/Kabilan/.m2/repository/org/springframework/batch/spring-batch-core/5.2.4
```

```bash
jar tf spring-batch-core-5.2.4.jar | grep schema-mysql.sql
```

```bash
jar xf spring-batch-core-5.2.4.jar org/springframework/batch/core/schema-mysql.sql
```

```bash
cp org/springframework/batch/core/schema-mysql.sql /mnt/c/Users/Kabilan/Desktop/
```

🖥️ Then in MySQL CLI:

```bash
USE spring-batch-scheduler;
```

```bash
SOURCE C:/Users/Kabilan/Desktop/schema-mysql.sql;
```

```bash
SHOW TABLES;
```

You should see system tables such as:

BATCH_JOB_INSTANCE, BATCH_JOB_EXECUTION, BATCH_STEP_EXECUTION, etc.

###  4️⃣ Prepare the CSV File

Create your CSV file here: -    src/main/resources/data/employees.csv


###  5️⃣ Run the Application

Use your IDE or run via Maven:

```bash
mvn spring-boot:run
```

###  6️⃣ Verify the Output

Every 2 minutes, the scheduler will:

Read the CSV file

Process the records

Insert data into your MySQL table

You can also verify job logs in the Spring Batch metadata tables (BATCH_JOB_EXECUTION, BATCH_STEP_EXECUTION, etc.).
