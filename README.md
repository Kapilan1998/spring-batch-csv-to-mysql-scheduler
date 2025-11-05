🗂️ spring-batch-csv-to-mysql-scheduler

A Spring Boot project that uses Spring Batch and Spring Scheduler to automatically read data from a CSV file every 2 minutes and store it into a MySQL database.🗂️ spring-batch-csv-to-mysql-scheduler


🚀 Features

Reads employee data from a CSV file (employees.csv)

Processes and saves data into MySQL using Spring Batch

Automatically runs every 2 minutes using Spring Scheduler

Tracks job metadata using Spring Batch system tables


🧰 Tech Stack

Java 21

Spring Boot 3.5.7

Spring Batch

Spring Scheduler

Spring Data JPA

MySQL

Lombok



⚙️ Setup Instructions
1️⃣ Clone the Repository
git clone https://github.com/Kapilan1998/spring-batch-csv-to-mysql-scheduler.git
cd spring-batch-csv-to-mysql-scheduler

2️⃣ Configure Database

Create a MySQL database for Spring Batch:

CREATE DATABASE spring-batch-scheduler;

Update your application.properties file as needed:

spring.datasource.url=jdbc:mysql://localhost:3306/spring-batch-scheduler
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.batch.jdbc.initialize-schema=always


3️⃣ (Optional) Manually Initialize Spring Batch Metadata Tables

If you prefer to initialize Spring Batch tables manually instead of using
spring.batch.jdbc.initialize-schema=always, follow these steps 👇

a. Open WSL terminal and navigate to your Maven repository:
cd /mnt/c/Users/Kabilan/.m2/repository/org/springframework/batch/spring-batch-core/5.2.4
b. Extract the MySQL schema file:
jar tf spring-batch-core-5.2.4.jar | grep schema-mysql.sql
jar xf spring-batch-core-5.2.4.jar org/springframework/batch/core/schema-mysql.sql
c. Copy it to your Desktop for easy access:
cp org/springframework/batch/core/schema-mysql.sql /mnt/c/Users/Kabilan/Desktop/
![WSL Commands](assets/wsl-commands.png)

d. Run the schema file in MySQL CLI on Windows:
USE spring-batch-scheduler;
SOURCE C:/Users/Kabilan/Desktop/schema-mysql.sql;
SHOW TABLES;
You should now see tables like BATCH_JOB_INSTANCE, BATCH_JOB_EXECUTION, etc.
![MYSQL CLI Commands](assets/mysql-cli-commands.png)

4️⃣ Prepare Your CSV File

Place your CSV file under:

src/main/resources/data/employees.csv

Example content:

name,email,salary
John Doe,john.doe@example.com,50000
Jane Smith,jane.smith@example.com,60000

5️⃣ Run the Application

You can run it from your IDE or use Maven:

mvn spring-boot:run

6️⃣ Verify

Every 2 minutes, the scheduler triggers the batch job.

Processed employee data will be saved in your MySQL table.


