DROP DATABASE IF EXISTS insignia;

CREATE DATABASE insignia;
USE insignia;


CREATE TABLE Player (
                        Id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    #    UserId INT NOT NULL,
                        PlayerName varchar(255) NOT NULL,
                        MaxDistance integer DEFAULT 0,
                        Rol varchar(255) DEFAULT 'ROOKIE',
                        TimeOfFlight integer DEFAULT 0,
                        Bitcoins integer DEFAULT 0


);

CREATE TABLE User (
                      Id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                      PlayerId INT NOT NULL,
                      UserName varchar(255) NOT NULL unique,
                      Password varchar(255) NOT NULL,
                      FullName varchar(255),
                      Email varchar(255),
                      Status BOOLEAN DEFAULT FALSE NOT NULL,
                      FOREIGN KEY (PlayerId) references Player(Id)
);




INSERT INTO Player(PlayerName) VALUES ('pau player');
INSERT INTO Player(PlayerName) VALUES ('julia player');
INSERT INTO User(UserName, Password, PlayerId) VALUES ('Pau', MD5('Pau'),
                                                       (SELECT id from Player WHERE PlayerName = 'pau player'));
INSERT INTO User(UserName, Password, PlayerId) VALUES ('Julia', MD5('Julia'),
                                             (SELECT id from Player WHERE PlayerName = 'julia player'));
