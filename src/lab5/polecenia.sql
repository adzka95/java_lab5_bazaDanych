
CREATE Table KATEDRA
(
ID_KATEDRY INTEGER PRIMARY KEY,
SKROT VARCHAR (6),
NAZWA VARCHAR (60) 

);


CREATE TABLE STUDENT
(
ID_STUDENTA INTEGER PRIMARY KEY,
IMIE VARCHAR (15) ,
NAZWISKO VARCHAR (30) ,
ID_KATEDRY INTEGER,
FOREIGN KEY (ID_KATEDRY) REFERENCES KATEDRA(ID_KATEDRY)
);
INSERT INTO KATEDRA (ID_KATEDRY, SKROT, NAZWA) VALUES(1, 'KIO','Katedra Inzynierii Oprogramowania');
insert into KATEDRA (ID_KATEDRY, SKROT, NAZWA) VALUES(2, 'KASK', 'Katedra Architektury Systemow Komputerowych');
insert into KATEDRA (ID_KATEDRY, SKROT, NAZWA) VALUES(3, 'KAiMS', 'Katedra Algorytmow i Modelowania Systemow');

insert into STUDENT VALUES(1, 'Jan', 'Kowalski', 1);
insert into STUDENT VALUES(2, 'Marek', 'Adamowicz', 2);
insert into STUDENT VALUES(3, 'Julia', 'Liszka', 3);
insert into STUDENT VALUES(4, 'Jakub', 'Stolak', 1);
insert into STUDENT VALUES(5, 'Konrad', 'Kwiat', 3);
insert into STUDENT VALUES(6, 'Aleksandra', 'Lipuska', 2);
insert into STUDENT VALUES(7, 'Kasia', 'Kot', 2);

