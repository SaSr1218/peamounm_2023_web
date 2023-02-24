drop database if exists project;
create database project;

use project;
drop table if exists member;

create table member (
	mNo int auto_increment primary key, 
    mid varchar(20),
    mpw varchar(20),
    mphone varchar(20),
    memail varchar(20),
    mnaccount varchar(20) default null
);

drop table if exists buy;
create table buy (
	bNo int auto_increment primary key,
    bPrice int not null,
    bAmount int not null,
    bDate datetime default now(),
    mNo int,
    foreign key ( mNo ) references member ( mNo )
);

drop table if exists sell;
create table sell(
	sNo int auto_increment primary key,
    sPrice int not null,
    sAmount int not null,
    sDate datetime default now(),
    bNo int,
    foreign key ( bNo ) references buy ( bNo )
);

drop table if exists account;
create table account(
	aNo int auto_increment primary key,
    aBalance int not null,
    aAmount int not null,
    mNo int,
    foreign key ( mNo ) references member ( mNo ) 
);

select * from member;
select * from buy;
select * from sell;
select * from account ;
