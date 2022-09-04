DROP TABLE Contract
DROP TABLE Course
DROP TABLE Tchr
DROP TABLE Student

CREATE TABLE Student(
Id_Stud int NOT NULL PRIMARY KEY,
Name_stud varchar(20) NOT NULL,
Org varchar(20) NOT NULL,
Cont varchar(20) NOT NULL,
Cty varchar(20) NOT NULL,
Cntr varchar(20) NOT NULL,
disCnt int NOT NULL,
Sx char NOT NULL);

CREATE TABLE Tchr(
Id_Tch int NOT NULL PRIMARY KEY,
NameTch varchar(18) NOT NULL,
ContTch varchar(20) NOT NULL,
Sal int NOT NULL,
Dol varchar(20) NOT NULL,
Sx_Tc char NOT NULL);

CREATE TABLE Course(
Id_Crs int NOT NULL PRIMARY KEY,
Title varchar(20) NOT NULL,
DefCrs varchar(40),
Cost int NOT NULL,
Id_Tch int NOT NULL FOREIGN KEY REFERENCES Tchr(Id_Tch),
Hur int NOT NULL,
TypCrs varchar(20) NOT NULL);

CREATE TABLE Contract(
Id_Cont int NOT NULL PRIMARY KEY,
Id_Stud int NOT NULL FOREIGN KEY REFERENCES Student(Id_Stud),
Dte datetime NOT NULL, 
Id_Crs int NOT NULL FOREIGN KEY REFERENCES Course(Id_Crs));
