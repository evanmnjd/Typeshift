
DROP DATABASE IF EXISTS Typeshift_DB;
CREATE DATABASE Typeshift_DB;

USE Typeshift_DB;

CREATE TABLE High_Scores
(
  player_name	VARCHAR(45)	NOT NULL	PRIMARY KEY,
  score			INT 		NOT NULL
);

CREATE TABLE Words_List
(
  words			VARCHAR(45)		NOT NULL	PRIMARY KEY
);

INSERT INTO Words_List VALUES 
('big'), 
('bog'), 
('bot'),
('bow'),
('cow'),
('cot'),
('cop'),
('cry'),
('doe'),
('dim'),
('dig'),
('dug'),
('dog'),
('eel'),
('eat'),
('ear'),
('for');

INSERT INTO High_Scores VALUES
('player1', '100');

GRANT ALL
ON Typeshift_DB.*
TO 'user'@'localhost'
IDENTIFIED BY 'user';