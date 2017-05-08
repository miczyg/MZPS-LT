# MZPS-LT

#db mock script


create database mzps;
use mzps;
create table TEAMS (
   id BIGINT NOT NULL AUTO_INCREMENT,
   name VARCHAR(50) NOT NULL,
   category  VARCHAR(5) NOT NULL,
   sex varchar(10) NOT NULL,
   PRIMARY KEY (id)
);
   
/* Populate USER Table */
INSERT INTO TEAMS(name,category,sex)
VALUES ('Proxima Kraków','U16','FEMALE');
   
INSERT INTO TEAMS(name,category,sex)
VALUES ('MKS MOS Wieliczka','U16','FEMALE');
 
commit;

