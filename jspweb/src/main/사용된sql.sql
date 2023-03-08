drop database if exists jspweb;
create database jspweb;
use jspweb;

-- 회원테이블
drop table if exists member;
create table member(
	mno		int auto_increment primary key,	-- 식별 번호
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
    foreign key ( mno ) references member ( mno ) on delete set null		-- 탈퇴한
);

select * from member;
select * from friend;
select * from mpoint;
