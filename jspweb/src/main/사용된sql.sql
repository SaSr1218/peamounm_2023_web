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

-- 댓글 테이블 [ 댓글번호 , 내용 , 작성일 , 인덱스(계층구분) , 작성자 , 게시물번호 
drop table if exists reply;
create table reply(
	rno			int auto_increment primary key ,
    rcontent	longtext ,
    rdate		datetime default now() ,
    rindex		int default 0 , -- 0이면 1계층 , 1~ 해당 댓글의 하위 댓글
    mno			int ,
    bno			int ,
    foreign key ( mno ) references member ( mno ) on delete set null ,
    foreign key ( bno ) references board ( bno ) on delete set null 
);

/*
	3번 게시물
		1번 댓글			[ rno = 1 , rindex = 0 ]
			3번 댓글		[ rno = 3 , rindex = 1 ]
            4번 댓글		[ rno = 4 , rindex = 1 ]
				6번 댓글	[ rno = 6 , rindex = 4 ]
		2번 댓글			[ rno = 2 , rindex = 0 ]
        5번 댓글			[ rno = 5 , rindex = 0 ]
*/

drop table if exists jspweb_product;
create table jspweb_product(
	pno 		int auto_increment primary key , 	-- 제품 번호
    pname		varchar(500) not null ,				-- 제품 이름
    pcomment	text not null ,						-- 제품 설명
    pprice		bigint not null ,					-- 제품 가격
    pstate		int default 1 ,						-- 물품 상태 [ 1 : 판매중 / 2 : 거래중 / 3 : 판매완료 ]
    plat		varchar(100) not null ,				-- 위도
	plng		varchar(100) not null ,				-- 경도
    pview		int	default 0 ,						-- 조회수
    pdate		datetime default now()	,			-- 등록일
    mno 		int , 								-- 등록한 회원번호
	foreign key ( mno ) references  member ( mno ) on delete cascade 
);

-- 드래그 위치 안에 물품 표시하기 위해
select * from jspweb_product where ? >= plng and ? <= plng and ? <= plat and ? >= plat;

-- 제품 사진 테이블
drop table if exists pimg;
create table pimg( 
	pimgmno int auto_increment primary key , 	-- 사진 식별 번호
    pimgname longtext not null , 				-- 사진명
    pno int , 									-- 해당 사진의 연결된 제품번호
    foreign key (pno) references jspweb_product(pno) on delete cascade
);

-- 제품 찜하기 테이블
drop table if exists plike;
create table plike (
    plikeno bigint auto_increment primary key , 	-- 찜하기번호 pk
    mno int ,										-- 회원번호 fk
    pno int ,										-- 제품번호 fk
    foreign key ( mno ) references  member ( mno ) on delete cascade ,
    foreign key ( pno ) references jspweb_product ( pno ) on delete cascade
);
select * from plike;

-- 제품 쪽지 테이블
drop table if exists note;
create table note(
	nno bigint auto_increment primary key ,
    ncontent text not null ,
    ndate datetime default now() ,
    pno int ,
    frommno int ,
    tomno int ,
    foreign key (pno) references jspweb_product (pno) on delete cascade ,
    foreign key ( frommno ) references member (mno) on delete cascade ,
    foreign key ( tomno) references member (mno) on delete cascade
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

-- 1. 카테고리 임시 대입
insert into category (cname) values ( '공지사항' );
insert into category (cname) values ( '커뮤니티' );
insert into category (cname) values ( 'QnA' );
insert into category (cname) values ( '노하우' );

-- 자연조인 [ pk와 fk필드가 1개씩 존재하는 테이블에서 자주 사용 ]
select * from member m natural join board b; 
-- A테이블명 join B테이블명 on 조인조건 [ on 키워드를 사용해 교집합조건 사용해 조건들 구분 ]
select * from member m join board b on m.mno = b.mno;

-- 조건 없는 출력
select b.* , m.mid from member m natural join board b;
-- 개별 출력
select b.* , m.mid , m.mimg from member m natural join board b where b.bno = 2 ;

-- 특정 개수만 출력 [ 페이징 조건 ] limit 
select b.* , m.mid , m.mimg from member m natural join board b limit 0 , 3; -- 1페이지

-- 레코드 수 구하기 count(*)
select count(*) from member m natural join board b;

-- 조건식 [ 같다 ]
select * from board where btitle = '4';
-- 조건식 [ 포함 ]	필드명 like %데이터%
select * from board where btitle like '%0%'; -- 0이 포함된 제목 레코드 찾기
select * from board where bcontent like '%0%';
select * from board where btitle like '_1_'; -- X1X 가 제목인 레코드 찾기
select board , m.mid from board where mid like 'qwe';

-- 결론
	-- 1. 검색이 없을때 레코드수 구하기
    select count(*) from member m natural join board b;
    -- 2. 검색이 있을때 레코드수 구하기
    select count(*) from member m natural join board b where b.btitle like '%1%';
    -- 3. 자바에서 사용할 경우
--  "select count(*) from member m natural join board b where "+key+" like '%"+keyword+"%'";    

	-- 1. 검색이 없을때 모든 글 출력
    select b.* , m.mid from member m natural join board b limit ? , ?; 
    -- 2. 검색이 있을때 모든 글 출력
    select b.* , m.mid from member m natural join board b where b.btitle like '%1%' order by b.bdate desc limit 0 ,3  ;
    -- 3. 자바에서 사용할 경우
-- 	"select b.* , m.mid from member m natural join board b where "+ key +" like '%"+keyword+"%' order by b.bdate desc limit 0 ,3 " ;

-- 카테고리별 총 게시물 개수 구하기
    select count(*)  from board where cno = 1;

