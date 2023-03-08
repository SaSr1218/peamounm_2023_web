use jspweb;

create table ex3(
	bno			int 	auto_increment primary key, 
    bcontent 	varchar(1000) ,
    bwriter		varchar(100 ) ,
	bdate		datetime	default now()
);

select * from ex3;

delete from ex3 where bno = 1;
update ex3 set bcontent = "새로운내용" where bno ="2";

create table q3(
	pno	int	auto_increment primary key ,	-- 제품번호
    pname varchar(20) ,						-- 제품명
    pprice int								-- 제품가격
);

insert into q3 ( pname , pprice ) values ( "아아" , "1500" );
delete from q3 where pno =3;
update q3 set pname = "아아"  , pprice = 2000  where pno = "2";

select * from q3;

