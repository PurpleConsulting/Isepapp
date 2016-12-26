FROM mysql:5.7
# docker build . -t purplesql

ADD my.cnf /docker-entrypoint-initdb.d/my.cnf.template

ADD isepapp.sql /docker-entrypoint-initdb.d/isepapp.sql

RUN chmod -R 755 /docker-entrypoint-initdb.d/
