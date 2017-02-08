FROM mysql:5.7

# docker build . -t purplesql
# docker run --name isepapp -p 3306:3306 -d purplesql 

ADD my.cnf /docker-entrypoint-initdb.d/my.cnf.template

ADD isepapp.sql /docker-entrypoint-initdb.d/isepapp.sql

ADD appdb_init.sql /docker-entrypoint-initdb.d/appdb_init.sql

RUN chmod -R 755 /docker-entrypoint-initdb.d/

ENV MYSQL_USER mysql
ENV MYSQL_DATABASE APPDB
ENV MYSQL_ROOT_PASSWORD paris06

EXPOSE 3306 3306
