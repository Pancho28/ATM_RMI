drop table transaction;
drop table account;
drop table user;

create table User (
document_id int primary key not null,
name_ varchar(30) not null,
username varchar(30) not null,
password_ varchar(30) not null
);

Create table Account (
number_ int primary key not null AUTO_INCREMENT,
current_balace float not null,
fk_user int not null,
foreign key (fk_user) references User(Document_id),
check (current_balace>-1)
);

Create table Transaction (
id int primary key not null AUTO_INCREMENT,
amount float not null,
date_ date not null,
description_ varchar(100) not null,
tipe varchar(2) not null,
fk_d int not null,
fk_f int,
foreign key (fk_d) references Account(number_),
foreign key (fk_f) references Account(number_),
check (tipe = "d" or tipe = "t" or tipe = "w")
);


insert into User values (100,'Pedro','pedro13','qwert123');
insert into User values (210,'Luis','L14','qwert123');
insert into User values (340,'Laura','lala06','qwert123');

insert into Account(current_balace,fk_user) values (1500,100);
insert into Account(current_balace,fk_user) values (1000,100);
insert into Account(current_balace,fk_user) values (2000,210);
insert into Account(current_balace,fk_user) values (3500,340);
insert into Account(current_balace,fk_user) values (500,340);
insert into Account(current_balace,fk_user) values (1000,340);

insert into Transaction(amount,date_,description_,tipe,fk_d,fk_f) values(1500,curdate(),'Deposito','d',1,null);
insert into Transaction(amount,date_,description_,tipe,fk_d,fk_f) values(1000,curdate(),'Deposito','d',2,null);
insert into Transaction(amount,date_,description_,tipe,fk_d,fk_f) values(2000,curdate(),'Deposito','d',3,null);
insert into Transaction(amount,date_,description_,tipe,fk_d,fk_f) values(3500,curdate(),'Deposito','d',4,null);
insert into Transaction(amount,date_,description_,tipe,fk_d,fk_f) values(500,curdate(),'Deposito','d',5,null);
insert into Transaction(amount,date_,description_,tipe,fk_d,fk_f) values(1000,curdate(),'Deposito','d',6,null);








