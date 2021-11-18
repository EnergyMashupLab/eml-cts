FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD capstone@123
ENV MYSQL_DATABASE nist_cts_eml
ENV MYSQL_USER eml
ENV MYSQL_PASSWORD capstone@123
ADD create-database.sql /docker-entrypoint-initdb.d/create-database.sql
RUN chmod -R 775 /docker-entrypoint-initdb.d
