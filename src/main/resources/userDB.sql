DROP DATABASE IF EXISTS insignia;

CREATE DATABASE insignia;
USE insignia;


CREATE TABLE Player (
                        Id varchar(255) PRIMARY KEY,
                        PlayerName varchar(255) UNIQUE NOT NULL,
                        MaxDistance integer DEFAULT 0,
                        Rol varchar(255) DEFAULT 'ROOKIE',
                        TimeOfFlight integer DEFAULT 0,
                        Bitcoins integer DEFAULT 0


);

CREATE TABLE User (
                      Id varchar(255) PRIMARY KEY,
                      PlayerId varchar(255) NOT NULL,
                      UserName varchar(255) UNIQUE NOT NULL,
                      Password varchar(255) NOT NULL,
                      FullName varchar(255),
                      Email varchar(255),
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
                        FOREIGN KEY (Name) references InsigniaModel(Name),
                        Data varchar(255) DEFAULT 0 NOT NULL,
                        PlayerId varchar(255) NOT NULL,
                        FOREIGN KEY (PlayerId) references Player(Id)
);


CREATE TABLE PlaneModel (
                               Model varchar(255) NOT NULL UNIQUE PRIMARY KEY,
                               Fuel integer NOT NULL,
                               EnginesLife integer NOT NULL,
                               VelX integer NOT NULL,
                               VelY integer NOT NULL,
                               Gravity double default 9.81 NOT NULL
);

CREATE TABLE Plane (
                          Id varchar(255) PRIMARY KEY,
                          PlaneModelModel varchar(255) NOT NULL,
                          PlayerId varchar(255) NOT NULL,
                          FOREIGN KEY (PlayerId) references Player(Id),
                          FOREIGN KEY (PlaneModelModel) references PlaneModel(Model)
);









INSERT INTO Player(PlayerName, Id) VALUES ('Pau', 'IdPau');
INSERT INTO Player(PlayerName, Id) VALUES ('Julia', 'IdJulia');
INSERT INTO Player(PlayerName, Id) VALUES ('Arnau', 'IdArnau');
INSERT INTO Player(PlayerName, Id) VALUES ('Marc', 'IdMarc');
INSERT INTO User(UserName, Password, PlayerId, Id) VALUES ('Pau', MD5('Pau'),(SELECT id from Player WHERE PlayerName = 'Pau'), 'A');
INSERT INTO User(UserName, Password, PlayerId, Id) VALUES ('Arnau', MD5('Arnau'),(SELECT id from Player WHERE PlayerName = 'Arnau'),'B');
INSERT INTO User(UserName, Password, PlayerId, Id) VALUES ('Julia', MD5('Julia'),(SELECT id from Player WHERE PlayerName = 'Julia'), 'C');
INSERT INTO User(UserName, Password, PlayerId, Id) VALUES ('Marc', MD5('Marc'),(SELECT id from Player WHERE PlayerName = 'Marc'), 'D');

INSERT INTO InsigniaModel(Name, Type) VALUES ('Welcome', 'Tipo1');
INSERT INTO InsigniaModel(Name, Type) VALUES ('Diamond', 'Tipo2');
INSERT INTO InsigniaModel(Name, Type) VALUES ('First_purchase', 'Tipo3');
INSERT INTO InsigniaModel(Name, Type) VALUES ('Zombie', 'Tipo3');
INSERT INTO InsigniaModel(Name, Type) VALUES ('Scorched', 'Tipo3');


INSERT INTO Insignia(Id, Name, Data, PlayerId) VALUES ('welcome', 'Welcome',  '1/1/2000' , (SELECT id from Player WHERE PlayerName = 'Marc'));
INSERT INTO Insignia(Id, Name, Data, PlayerId) VALUES ('welcome', 'Welcome',  '1/1/2000' , (SELECT id from Player WHERE PlayerName = 'Pau'));
INSERT INTO Insignia(Id, Name, Data, PlayerId) VALUES ('welcome', 'Welcome',  '1/1/2000' , (SELECT id from Player WHERE PlayerName = 'Julia'));
INSERT INTO Insignia(Id, Name, Data, PlayerId) VALUES ('welcome', 'Welcome',  '1/1/2000' , (SELECT id from Player WHERE PlayerName = 'Arnau'));


INSERT INTO PlaneModel(Model, Fuel, EnginesLife, VelX, VelY, Gravity) VALUES ('Airbus', 60, 60, 70, 40, 100);
INSERT INTO PlaneModel(Model, Fuel, EnginesLife, VelX, VelY, Gravity) VALUES ('Fighter', 80, 20, 90, 60, 50);
INSERT INTO PlaneModel(Model, Fuel, EnginesLife, VelX, VelY, Gravity) VALUES ('Cessna', 20, 30, 40, 70, 20);
INSERT INTO PlaneModel(Model, Fuel, EnginesLife, VelX, VelY, Gravity) VALUES ('Helicopter', 50, 50, 50, 70, 60);
INSERT INTO PlaneModel(Model, Fuel, EnginesLife, VelX, VelY, Gravity) VALUES ('Acrobatic', 20, 20, 60, 80, 20);


INSERT INTO Plane(PlayerId, PlaneModelModel, Id) VALUES ((SELECT id from Player WHERE PlayerName = 'Arnau'), 'Cessna', '1');
INSERT INTO Plane(PlayerId, PlaneModelModel, Id) VALUES ((SELECT id from Player WHERE PlayerName = 'Pau'), 'Cessna', '2');
INSERT INTO Plane(PlayerId, PlaneModelModel, Id) VALUES ((SELECT id from Player WHERE PlayerName = 'Julia'), 'Cessna', '3');
INSERT INTO Plane(PlayerId, PlaneModelModel, Id) VALUES ((SELECT id from Player WHERE PlayerName = 'Marc'), 'Cessna', '4');
