-- init.sql
--drop database reactive_db;
CREATE DATABASE reactive_db
  WITH ENCODING='UTF8'
       LC_COLLATE='en_US.UTF-8'
       LC_CTYPE='en_US.UTF-8'
       TEMPLATE=template0
       CONNECTION LIMIT = -1; -- (-1 means unlimited connections)
CREATE USER admin WITH PASSWORD Adm!n123$;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO admin;


