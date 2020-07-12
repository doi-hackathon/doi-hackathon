CREATE DATABASE IF NOT EXISTS scan_db;

CREATE USER scan_user@localhost IDENTIFIED BY 'codeup';
GRANT ALL ON scan_db.* TO scan_user@localhost;