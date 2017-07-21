CREATE DATABASE chat;

USE chat;

CREATE TABLE adminOrg(
  adminId INT ,
  PRIMARY KEY(adminId)
 );

CREATE TABLE employees
(
 eid INT NOT NULL UNIQUE,
 ename varchar(50) NOT NULL,
 dob varchar(40),
 address varchar(100),
 phoneNo varchar(11)  NOT NULL  UNIQUE,
 email varchar(50)  NOT NULL UNIQUE,
 password longtext NOT NULL,
 loginStatus int(1) default 0 ,
 verifykey  INT auto_increment,
 depName VARCHAR(20),
 adminEid INT,
  foreign key (adminEid) references adminOrg(adminId),
  PRIMARY KEY (verifykey)
);

 CREATE TABLE forumQues
 ( qid INT auto_increment,
   question longtext NOT NULL,
   datePost date NOT NULL,
   postEid INT,
   foreign key(postEid) references employees(eid),
   PRIMARY KEY(qid)
 );
 
 CREATE TABLE forumAnswer
 ( ansid INT auto_increment,
   answer longtext NOT NULL,
   datePost date NOT NULL,
   postEid INT,
   rateEid INT,
   rate INT default 0,
   qid int,
   foreign key(qid) references forumQues(qid),
   foreign key(postEid) references employees(eid),
   foreign key(rateEid) references employees(eid),
   PRIMARY KEY(ansid)
 );
 
  CREATE TABLE messages
 ( mid INT auto_increment,
   receiveEid int NOT NULL, 
   sendEids longtext, 
   foreign key(receiveEid) references employees(eid),
   PRIMARY KEY(mid)
 );
 
   CREATE TABLE messagesFile
 ( fileName longtext NOT NULL,
   countLines int NOT NULL
 );
 
 CREATE TABLE broadcast(
  bcount INT 
 );
 
 
 insert into broadcast values(0);
 alter table adminorg add jdoDetachedState VARCHAR(11);
 alter table employees add jdoDetachedState VARCHAR(11);
 alter table messages add jdoDetachedState VARCHAR(11);
 alter table forumQues add jdoDetachedState VARCHAR(11);
 alter table forumAnswer add jdoDetachedState VARCHAR(11);