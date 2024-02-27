
FROM mysql:8.0

ENV MYSQL_DATABASE=accwe-hospital
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_ROOT_PASSWORD=root

COPY init.sql /docker-entrypoint-initdb.d/

EXPOSE 3306

