DROP DATABASE IF EXISTS insignia;

CREATE DATABASE insignia;
USE insignia;

CREATE TABLE User (
                      id varchar(255) DEFAULT UUID() PRIMARY KEY,
                      userName varchar(255) NOT NULL unique,
                      password varchar(255) NOT NULL,
                      fullName varchar(255),
                      email varchar(255),
                      status BOOLEAN DEFAULT FALSE NOT NULL
);

CREATE TABLE Player (
                        id varchar(255) DEFAULT UUID() PRIMARY KEY,
                        userId varchar(255) NOT NULL,
                        playerName varchar(255) NOT NULL,
                        maxDistance integer DEFAULT 0,
                        rol varchar(255) DEFAULT 'ROOKIE',
                        timeOfFlight integer DEFAULT 0,
                        bitcoins integer DEFAULT 0,

                        FOREIGN KEY (userId) references User(id)

);

INSERT INTO User(userName, password) VALUES ('Pau', MD5('Pau'));
INSERT INTO Player(userId, playerName) VALUES ((
    SELECT id from User WHERE userName = 'Pau'), 'pau player');