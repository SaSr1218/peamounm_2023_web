drop database if exists jspweb;
create database jspweb;
use jspweb;

-- 회원테이블
drop table if exists member;
create table member(
	mno		int auto_increment primary key,			-- 식별 번호
    mid		varchar(30)  not null unique,			-- 회원 ID [ 공백, 중복 불가 ]
    mpwd	varchar(20)  not null,					-- 회원 PW [ 공백 불가 ]
    mimg	longtext 	,							-- 웹서버에 저장된 사진 경로
    memail	varchar(100) not null unique			-- 회원 email [ 공백, 중복 불가 ]
);

-- 친추 테이블
drop table if exists friend;
create table friend(
	fno		int auto_increment primary key , -- 식별 번호
	ffrom	int , 						 	 -- 친구 신청한 회원 fk
	fto		int  ,							 -- 친구 신청 받은 회원 fk
	foreign key ( ffrom ) 	references member ( mno ) on delete set null,	-- 친구가 탈퇴하면 null로 변환
    foreign key ( fto ) 	references member ( mno ) on delete set null	-- 친구가 탈퇴하면 null로 변환
);

-- 포인트 테이블
drop table if exists mpoint;
create table mpoint(
	mpno		int auto_increment primary key,
    mpcomment	varchar(1000) not null,				-- 포인트내역 내용
    mpamount 	int ,								-- 포인트 수량
    mpdate		datetime default now() ,			-- 포인트 내역 날짜
    mno			int ,								
    foreign key ( mno ) references member ( mno ) on delete set null		-- 회원이 탈퇴하면 null 값 
);

-- 카테고리 테이블 [ 카테고리번호 , 카테고리 이름 ( 공지사항 , 커뮤니티 , QnA , 노하우 등등 ) ]
drop table if exists category;
create table category(
	cno		int auto_increment primary key ,
    cname 	varchar(100)
);

-- 게시물 테이블 [ 번호 , 제목 , 내용 , 첨부파일 , 작성일 , 조회수 , 좋아요수 , 싫어요수 , 작성자  , 카테고리번호  ]
drop table if exists board;
create table board(
	bno			int auto_increment primary key ,	-- 번호
    btitle		varchar(100) not null,				-- 제목
    bcontent	longtext  not null,					-- 내용
    bfile		longtext ,							-- 첨부파일
    bdate		datetime default now() ,			-- 작성일
    bview		int default 0 ,						-- 조회수
    bgood		int default 0 ,						-- 좋아요수
    bbad		int default 0 ,						-- 싫어요수
    mno			int ,								-- 작성자(회원번호 fk)
    cno			int ,								-- 카테고리번호 fk
    foreign key ( mno ) references member ( mno ) on delete set null , -- [회원] pk가 삭제되면 게시물도 삭제
	foreign key ( cno ) references category ( cno ) on delete cascade
);

-- on delete cascade	: pk가 삭제되면 fk 같이 삭제
-- on delete set null	: pk가 삭제되면 fk는 null로 변경
-- 생략					: fk에 존재하는 식별키[pk]는 삭제 불가능

-- join 후 필요한 필드와 통계 [ 두개이상 필드 출력시 그룹필수! ] --> 회원별로 보유포인트 출력!
select m.mno , m.mid , m.mimg , m.memail , sum(p.mpamount) as mpoint  
from member m , mpoint p 
where m.mno = p.mno
group by mno; -- 집계할 기준 [ 회원별이니까 mno로 그룹화 ]

-- 특정1명 회원정보 + 보유포인트
select m.mno , m.mid , m.mimg , m.memail , sum(p.mpamount) as mpoint  
from member m natural join mpoint p 
where  m.mid = 'qwe123';

select * from member;
select * from friend;
select * from mpoint;
select * from category;
select * from board;

-- 1. 카테고리 임시 대입
insert into category (cname) values ( '공지사항' );
insert into category (cname) values ( '커뮤니티' );
insert into category (cname) values ( 'QnA' );
insert into category (cname) values ( '노하우' );

