FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD capstone@123
ENV MYSQL_DATABASE nist_cts_eml
ENV MYSQL_USER eml
ENV MYSQL_PASSWORD capstone@123

# Add SQL file in entry point of mysql container
# create required database and tables from sql file 
ADD eml-mysql-create.sql /docker-entrypoint-initdb.d/eml-mysql-create.sql
RUN chmod -R 775 /docker-entrypoint-initdb.d

# reference: https://hub.docker.com/_/mysql
# Initializing a fresh instance
# When a container is started for the first time, a new database with the specified name will be created
# and initialized with the provided configuration variables. Furthermore, it will execute files
# with extensions .sh, .sql and .sql.gz that are found in /docker-entrypoint-initdb.d. Files will be executed in alphabetical order.
# You can easily populate your mysql services by mounting a SQL dump into that directory and provide custom images with contributed data.
# SQL files will be imported by default to the database specified by the MYSQL_DATABASE variable.
