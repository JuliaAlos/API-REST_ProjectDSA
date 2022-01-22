DROP DATABASE IF EXISTS insignia;

CREATE DATABASE insignia;
USE insignia;


CREATE TABLE Player (
                        Id varchar(255) PRIMARY KEY,
                        PlayerName varchar(255) UNIQUE NOT NULL,
                        MaxDistance integer DEFAULT 0,
                        Rol varchar(255) DEFAULT 'Cadet',
                        TimeOfFlight float DEFAULT 0.0,
                        Bitcoins integer DEFAULT 0
);

CREATE TABLE User (
                      Id varchar(255) PRIMARY KEY,
                      PlayerId varchar(255) NOT NULL,
                      UserName varchar(255) UNIQUE NOT NULL,
                      Password varchar(255) NOT NULL,
                      FullName varchar(255),
                      Email varchar(255),
                      Image_url varchar(255) DEFAULT 'http://147.83.7.203:8080/profileImages/bart.png',
                      Status BOOLEAN DEFAULT FALSE NOT NULL,
                      FOREIGN KEY (PlayerId) references Player(Id)
);

CREATE TABLE InsigniaModel (
                               Name varchar(255) NOT NULL UNIQUE PRIMARY KEY,
                               Type varchar(255) NOT NULL
);

CREATE TABLE Insignia (
                        Id varchar(255) PRIMARY KEY,
                        Name varchar(255) NOT NULL,
                        Data varchar(255) DEFAULT 0 NOT NULL,
                        PlayerId varchar(255) NOT NULL,
                        FOREIGN KEY (Name) references InsigniaModel(Name),
                        FOREIGN KEY (PlayerId) references Player(Id)
);


CREATE TABLE PlaneModel (
                               Model varchar(255) NOT NULL UNIQUE PRIMARY KEY,
                               Fuel integer NOT NULL,
                               MinFuel integer NOT NULL,
                               EnginesLife integer NOT NULL,
                               MaxEnginesLife integer NOT NULL,
                               VelX integer NOT NULL,
                               MaxSpeed integer NOT NULL,
                               VelY integer NOT NULL,
                               MaxManoeuvrability integer NOT NULL,
                               Gravity integer NOT NULL,
                               MinWeight integer NOT NULL,
                               Price integer NOT NULL
);

CREATE TABLE Plane (
                          Id varchar(255) PRIMARY KEY,
                          PlaneModelModel varchar(255) NOT NULL,
                          PlayerId varchar(255) NOT NULL,
                          FOREIGN KEY (PlayerId) references Player(Id),
                          FOREIGN KEY (PlaneModelModel) references PlaneModel(Model)
);

CREATE TABLE Upgrade (
                          Id varchar(255) PRIMARY KEY,
                          ModificationCode varchar(255) NOT NULL,
                          PlaneModelModel varchar(255) NOT NULL,
                          PlayerName varchar(255) NOT NULL
);

CREATE TABLE ForumEntry (
                          Id varchar(255) PRIMARY KEY,
                          UserName varchar(255) NOT NULL,
                          Message varchar(255) NOT NULL,
                          NumSeq integer NOT NULL
);



INSERT INTO Player(PlayerName, Id) VALUES ('Pau', 'IdPau');
INSERT INTO Player(PlayerName, Id) VALUES ('Julia', 'IdJulia');
INSERT INTO Player(PlayerName, Id, Bitcoins) VALUES ('Arnau', 'IdArnau', 200);
INSERT INTO Player(PlayerName, Id) VALUES ('Marc', 'IdMarc');
INSERT INTO User(UserName, Password, PlayerId, Id) VALUES ('Pau', MD5('Pau'),(SELECT id from Player WHERE PlayerName = 'Pau'), 'A');
INSERT INTO User(UserName, Password, PlayerId, Id) VALUES ('Arnau', MD5('Arnau'),(SELECT id from Player WHERE PlayerName = 'Arnau'),'B');
INSERT INTO User(UserName, Password, PlayerId, Id) VALUES ('Julia', MD5('Julia'),(SELECT id from Player WHERE PlayerName = 'Julia'), 'C');
INSERT INTO User(UserName, Password, PlayerId, Id) VALUES ('Marc', MD5('Marc'),(SELECT id from Player WHERE PlayerName = 'Marc'), 'D');

INSERT INTO InsigniaModel(Name, Type) VALUES ('Welcome', '1');
INSERT INTO InsigniaModel(Name, Type) VALUES ('Diamond', '2');
INSERT INTO InsigniaModel(Name, Type) VALUES ('First_purchase', '3');
INSERT INTO InsigniaModel(Name, Type) VALUES ('5min', '4');
INSERT INTO InsigniaModel(Name, Type) VALUES ('1hour', '5');
INSERT INTO InsigniaModel(Name, Type) VALUES ('24/7playing', '6');
INSERT INTO InsigniaModel(Name, Type) VALUES ('Centimetre', '7');
INSERT INTO InsigniaModel(Name, Type) VALUES ('World', '8');
INSERT INTO InsigniaModel(Name, Type) VALUES ('Wealth', '9');

INSERT INTO Insignia(Id, Name, Data, PlayerId) VALUES ('1', 'Welcome',  '1/2/2021' , (SELECT id from Player WHERE PlayerName = 'Marc'));
INSERT INTO Insignia(Id, Name, Data, PlayerId) VALUES ('2', 'Welcome',  '1/3/2021' , (SELECT id from Player WHERE PlayerName = 'Pau'));
INSERT INTO Insignia(Id, Name, Data, PlayerId) VALUES ('3', 'Welcome',  '1/1/2021' , (SELECT id from Player WHERE PlayerName = 'Julia'));
INSERT INTO Insignia(Id, Name, Data, PlayerId) VALUES ('4', 'Welcome',  '1/4/2021' , (SELECT id from Player WHERE PlayerName = 'Arnau'));

INSERT INTO PlaneModel(Model, Fuel, MinFuel, EnginesLife, MaxEnginesLife, VelX, MaxSpeed, VelY, MaxManoeuvrability, Gravity, MinWeight, Price) VALUES ('Airbus', 60, 40, 60, 100, 70, 90, 40, 60, 100, 80, 40);
INSERT INTO PlaneModel(Model, Fuel, MinFuel, EnginesLife, MaxEnginesLife, VelX, MaxSpeed, VelY, MaxManoeuvrability, Gravity, MinWeight, Price) VALUES ('Fighter', 80, 50, 20, 50, 90, 100, 60, 90, 50, 40, 60);
INSERT INTO PlaneModel(Model, Fuel, MinFuel, EnginesLife, MaxEnginesLife, VelX, MaxSpeed, VelY, MaxManoeuvrability, Gravity, MinWeight, Price) VALUES ('Cessna', 20, 10, 30, 50, 40, 60, 70, 90, 20, 10, 0);
INSERT INTO PlaneModel(Model, Fuel, MinFuel, EnginesLife, MaxEnginesLife, VelX, MaxSpeed, VelY, MaxManoeuvrability, Gravity, MinWeight, Price) VALUES ('Helicopter', 50, 30, 50, 80, 50, 80, 70, 90, 60, 40, 50);
INSERT INTO PlaneModel(Model, Fuel, MinFuel, EnginesLife, MaxEnginesLife, VelX, MaxSpeed, VelY, MaxManoeuvrability, Gravity, MinWeight, Price) VALUES ('Acrobatic', 20, 10, 20, 40, 60, 80, 80, 100, 20, 10, 20);

INSERT INTO Plane(PlayerId, PlaneModelModel, Id) VALUES ((SELECT id from Player WHERE PlayerName = 'Arnau'), 'Cessna', '1');
INSERT INTO Plane(PlayerId, PlaneModelModel, Id) VALUES ((SELECT id from Player WHERE PlayerName = 'Pau'), 'Cessna', '2');
INSERT INTO Plane(PlayerId, PlaneModelModel, Id) VALUES ((SELECT id from Player WHERE PlayerName = 'Julia'), 'Cessna', '3');
