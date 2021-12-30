DROP DATABASE IF EXISTS insignia;

CREATE DATABASE insignia;
USE insignia;


CREATE TABLE Player (
                        Id varchar(255) DEFAULT UUID() PRIMARY KEY,
    #    UserId INT NOT NULL,
                        PlayerName varchar(255) UNIQUE NOT NULL,
                        MaxDistance integer DEFAULT 0,
                        Rol varchar(255) DEFAULT 'ROOKIE',
                        TimeOfFlight integer DEFAULT 0,
                        Bitcoins integer DEFAULT 0


);

CREATE TABLE User (
                      Id varchar(255) DEFAULT UUID() PRIMARY KEY,
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
                        Id varchar(255) DEFAULT UUID() PRIMARY KEY,
                        Date varchar(255) DEFAULT 0 NOT NULL,
                        InsigniaModelName varchar(255) NOT NULL,
                        PlayerId varchar(255) NOT NULL,
                        FOREIGN KEY (PlayerId) references Player(Id),
                        FOREIGN KEY (InsigniaModelName) references InsigniaModel(Name)
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
                          Id varchar(255) DEFAULT UUID() PRIMARY KEY,
                          PlaneModelModel varchar(255) NOT NULL,
                          PlayerId varchar(255) NOT NULL,
                          FOREIGN KEY (PlayerId) references Player(Id),
                          FOREIGN KEY (PlaneModelModel) references PlaneModel(Model)
);









INSERT INTO Player(PlayerName) VALUES ('pau player');
INSERT INTO Player(PlayerName) VALUES ('julia player');
INSERT INTO Player(PlayerName) VALUES ('arnau player')
INSERT INTO User(UserName, Password, PlayerId) VALUES ('Pau', MD5('Pau'),(SELECT id from Player WHERE PlayerName = 'pau player'));
INSERT INTO User(UserName, Password, PlayerId) VALUES ('Arnau', MD5('Arnau'),(SELECT id from Player WHERE PlayerName = 'arnau player'));
INSERT INTO User(UserName, Password, PlayerId) VALUES ('Julia', MD5('Julia'),(SELECT id from Player WHERE PlayerName = 'julia player'));

INSERT INTO InsigniaModel(Name, Type) VALUES ('Insignia 1', 'Tipo1');
INSERT INTO InsigniaModel(Name, Type) VALUES ('Insignia 2', 'Tipo2');
INSERT INTO InsigniaModel(Name, Type) VALUES ('Insignia 3', 'Tipo3');

INSERT INTO PlaneModel(Model, Fuel, EnginesLife, VelX, VelY, Gravity) VALUES ('Airbus', 10, 3, 3, 1, 9.81);
INSERT INTO PlaneModel(Model, Fuel, EnginesLife, VelX, VelY, Gravity) VALUES ('Fighter', 5, 1, 5, 5, 9.81);
INSERT INTO PlaneModel(Model, Fuel, EnginesLife, VelX, VelY, Gravity) VALUES ('Cessna', 6, 2, 1, 2, 9.81);

INSERT INTO Plane(PlayerId, PlaneModelModel) VALUES ((SELECT id from Player WHERE PlayerName = 'arnau player'), 'Airbus');
INSERT INTO Plane(PlayerId, PlaneModelModel) VALUES ((SELECT id from Player WHERE PlayerName = 'arnau player'), 'Cessna');