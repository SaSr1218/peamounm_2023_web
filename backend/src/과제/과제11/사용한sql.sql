create database 과제11;
use 과제11;
create table product(
	pno int auto_increment primary key , -- 제품번호
    pname varchar(20) , 				 -- 제품이름
    pprice int ,						 -- 제품가격
    pbox int							 -- 제품재고
);
-- 제품 등록 (이름, 가격, 초기재고)
insert into product ( pname , pprice , pbox ) values ( ? , ? , ? );
-- 모든 제품 출력
select * from product;					
-- 제품 수정 (이름 , 가격)
update product set pname = ? , pprice =? where pno =?;
-- 제품 재고수정(재고)
update product set pbox = ? where pno = ?;
-- 제품 삭제
delete from product where pno = ?;