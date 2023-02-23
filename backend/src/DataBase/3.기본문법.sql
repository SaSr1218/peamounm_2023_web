/*
	- SQL
		DDL
			create
				create database DB명;
                create table 테이블명( 필드명1 타입 제약조건 );
            drop
				drop database DB명;
                drop database if exists DB명;
                drop table 테이블명;
                drop table if exists 테이블명;
        DML
        DCL
	
    - 타입 [ DBMS 회사마다 다름 ]
		1. 정수형
			tinyint 
            smallint
            int 
            bigint
		2. 문자형 
			char(길이)		: 고정길이
            varchar(길이) 	: 가변길이
		3. 대용량[ 긴글 , 첨부파일]
			text			
            longtext
		4. 실수형
			float
            double
		5. 날짜형
			date
            time
            datetime
    - 제약조건 
    
*/
-- 1. DB 생성
drop database if exists market;
create database market;
use market;
-- 2. 테이블 생성
drop table if exists member;
create table member(
	-- 필드 선언
		-- 필드명은 테이블앞글자_필드명
	mid char(8) not null primary key ,
		-- char(길이) : 길이만큼 문자 저장[ 최대 길이 글자(8) ]
        -- not null : 공백 저장 불가능 [ 공백이면 저장 실패 ]
		-- primary key : (기본키) 식별키 [ 필드내 중복 불가능, 공백 불가능 ] -> not null 포함(빼도 됨)
			-- 주민등록번호, 학번, 제품번호 등
	mname varchar(10) not null , 
		-- varchar(길이) : 가변길이 [ 데이터의 길이가 일정하지 않을때 ]
        -- 가변길이 : 저장된 데이터만큼 메모리 할당
			-- varchar(8) 에서 'ABC' 저장시 5칸 메모리 자동 제거
		-- 고정길이 : 선언된 길이만큼 메모리 할당
			-- char(8)에서 'ABC' 저장시 8칸 메모리 고정 [ 3칸:ABC , 5칸:빈공간 ]
	mnumber int not null , 
		-- int : 정수 +-20억 정도 저장가능
	maddr char(2) not null , 
    mphone1 char(3) ,	-- 국번 최대 3글자
    mphone2 char(8) , 	-- 전화번호 최대 8글자
	mheight smallint ,
		-- smallint : +-3만정도 저장가능
	mdebut date
		-- date : 날짜
);
INSERT INTO member VALUES('TWC', '트와이스', 9, '서울', '02', '11111111', 167, '2015.10.19');
INSERT INTO member VALUES('BLK', '블랙핑크', 4, '경남', '055', '22222222', 163, '2016.08.08');
INSERT INTO member VALUES('WMN', '여자친구', 6, '경기', '031', '33333333', 166, '2015.01.15');
INSERT INTO member VALUES('OMY', '오마이걸', 7, '서울', NULL, NULL, 160, '2015.04.21');
INSERT INTO member VALUES('GRL', '소녀시대', 8, '서울', '02', '44444444', 168, '2007.08.02');
INSERT INTO member VALUES('ITZ', '잇지', 5, '경남', NULL, NULL, 167, '2019.02.12');
INSERT INTO member VALUES('RED', '레드벨벳', 4, '경북', '054', '55555555', 161, '2014.08.01');
INSERT INTO member VALUES('APN', '에이핑크', 6, '경기', '031', '77777777', 164, '2011.02.10');
INSERT INTO member VALUES('SPC', '우주소녀', 13, '서울', '02', '88888888', 162, '2016.02.25');
INSERT INTO member VALUES('MMU', '마마무', 4, '전남', '061', '99999999', 165, '2014.06.19');
select * from member;
-- ----------------------------------------------
drop table if exists buy;
create table buy(
	bnum int auto_increment primary key ,	-- 1. 구매 번호
    mid char(8) ,							-- 2. 구매한 회원 [ 외래키 ]
	bpname char(6) not null ,				-- 3. 제품 이름
    bgname char(4) ,						-- 4. 분류명
    bprice int ,							-- 5. 가격
    bamout smallint	,						-- 6. 수량 
	foreign key( mid ) references member( mid ) -- 관계
	-- 외래키설정필드 ( 현재테이블의 필드명 ) 참조하다 PK위한테이블명 ( PK 필드명)
    -- foreign key ( 외래키 ) : 외래키 설정
    -- references : 다른 곳에 참조하다
);

-- 예제 레코드 추가
INSERT INTO buy VALUES(NULL, 'BLK', '지갑', NULL, 30, 2);
INSERT INTO buy VALUES(NULL, 'BLK', '맥북프로', '디지털', 1000, 1);
INSERT INTO buy VALUES(NULL, 'APN', '아이폰', '디지털', 200, 1);
INSERT INTO buy VALUES(NULL, 'MMU', '아이폰', '디지털', 200, 5);
INSERT INTO buy VALUES(NULL, 'BLK', '청바지', '패션', 50, 3);
INSERT INTO buy VALUES(NULL, 'MMU', '에어팟', '디지털', 80, 10);
INSERT INTO buy VALUES(NULL, 'GRL', '혼공SQL', '서적', 15, 5);
INSERT INTO buy VALUES(NULL, 'APN', '혼공SQL', '서적', 15, 2);
INSERT INTO buy VALUES(NULL, 'APN', '청바지', '패션', 50, 1);
INSERT INTO buy VALUES(NULL, 'MMU', '지갑', NULL, 30, 1);
INSERT INTO buy VALUES(NULL, 'APN', '혼공SQL', '서적', 15, 1);
INSERT INTO buy VALUES(NULL, 'MMU', '지갑', NULL, 30, 4);
select * from buy;

-- ER 다이어그램 [ 테이블 간 관계 확인 ]
	-- 메뉴 --> Database -> Reverse Engineer -> 로그인 -> DB 선택 -> 다음 다음 finish

-- select : 검색
-- 1. select 필드명 from 테이블명 // select 필드명1 , 필드명2 from 테이블명
	select mid from member;
	select mid, mname from member;
    
-- 2. select * from 테이블명
	select * from member;
    
-- 3. select 필드명 as '별칭' from 테이블명
	select mid as '회원아이디' from member;
	select mid '회원아이디' from member;	-- as 생략 가능
    
-- 4. select 필드명 from 테이블명 where 조건
	select * from member where mname = '블랙핑크';		
	select * from member where mnumber = 4;			
	select * from member where mheight <= 162;		
    select * from member where mheight >= 165 and mnumber > 6;
    select * from member where mheight >= 165 or mnumber > 6;
	select * from member where mheight >= 163 and mheight <= 165;
	select * from member where mheight between 163 and 165;
	select * from member where maddr = '경기' or maddr = '전남' or maddr = '경남';
    select * from member where maddr in ( '경기', '전남', '경남' );
	select * from member where mname = '우주소녀';
	select * from member where mname like '우%';	 		-- '우'로 시작하는 문자 찾기
    select * from member where mname like '%우';			-- '우'로 끝나는 문자 찾기
    select * from member where mname like '%우%';		-- '우'가 포함된 문자 찾기
    select * from member where mname like '우___';   	-- '우' 로 시작하고 _개수가 3개인 문자 찾기
    select * from member where mname like '___우';		-- 3글자로 시작하고 끝이 '우'인 문자 찾기
    select * from member where mname like '_우_';		-- 3글자이고 가운데 글자가 '우'인 문자 찾기
    
    select 
		mnumber , mnumber+1 , mnumber-1 , mnumber *2 , mnumber/2 , mnumber div 2 , mnumber mod 2
    from member;
	select mnumber * mheight 멤버키점수 from member;

-- 5. select * from 테이블명 order by 정렬기준필드
		-- select * from 테이블명 where 조건 order by 정렬기준( order by가 항상 제일 마지막에 입력!)
        -- select * from 테이블명 order by 정렬기준필드1 , 정렬기준필드2 ( 다중정렬 )
			-- 다중정렬 : 앞 정렬에서 동일한 데이터가 있을경우! 하위 정렬
	select * from member order by mdebut asc;		-- 기본값 오름차순(asc)
	select * from member order by mdebut desc;		-- 내림차순(desc) / 최근날짜가 더 크다.
	select * from member order by mheight desc;
    select * from member where mheight >= 164 order by mheight;
    -- 키를 내림차순으로 정렬후 동일한 키가 있을경우 동일한 키 중에서 데뷔날짜 오름차순
	select * from member order by mheight desc , mdebut asc ;
		/*
			학년 점수		학년 정렬 -> 점수 정렬		학년 점수
            1	50								1	50
            3	20								1 	60
            1	60	alter						3 	20
        */

-- 6. select * from 테이블명 limit 레코드수 ( 페이징처리시 필요 - ex) 게시물 10개씩 가져올때 ) : 검색 레코드 제한
		-- select * from 테이블명 limit 시작레코드 , 개수
	select * from member limit 3;
	select * from member limit 0 , 3;
    select * from member order by mheight desc limit 3;	-- 키 상위 레코드 3개

-- 7. select distinct 필드명 from 테이블명 : 중복 제거
	select maddr from member;
    select distinct maddr from member;

-- 8. select * from 테이블명 group by 그룹기준필드
	select bamout from buy;			-- 판매수량 필드 검색
    select sum(bamout) from buy; 	-- 판매수량 필드 합계
    select avg(bamout) from buy; 	-- 판매수량 필드 평균
    select max(bamout) from buy; 	-- 판매수량 필드내 최대값
    select min(bamout) from buy; 	-- 판매수량 필드내 최소값
    select count(bamout) from buy; 	-- 판매수량 필드의 레코드수 [null 미포함]
    select count( * ) from buy; 	-- 전체 레코드 수		 [null 포함]
    
	-- 1. 회원아이디[그룹] 별로 판매수량 합계
		select mid 회원아이디 , sum(bamout) as 구매수량총합 from buy group by mid;
	-- 2. 회원아이디[그룹] 별로 총매출액 [ 가격 * 수량 ] 
		select mid 회원이이디 , sum(bprice * bamout) 총매출액 from buy group by mid;
	-- 3. 회원아이디[그룹] 별로 수량 평균
		select mid 회원아이디 , avg(bamout) 판매수량편균 from  buy group by mid;
	-- 4. 구매한 회원수
		select mid 회원아이디 , count( * )  결제수량 from buy group by mid;

-- 9. select * from 테이블명 group by 그룹기준필드 having 그룹조건
select mid 회원아이디 , sum( bprice * bamout ) 총매출액 
from buy 
group by mid 
having sum( bprice * bamout ) >= 1000;

-- 10. [전체] -> 순서!
	-- select * from 테이블명 where 조건 group by 그룹 having 그룹조건 order by 정렬 limit 레코드수제한

-- insert
	-- 1. 특정필드에 값 삽입 	: insert into 테이블명 ( 필드명1, 필드명2 ) values ( 값1 , 값2 );
    -- 2. 전체필드에 값 삽입 	: insert into 테이블명 valuse( 값1, 값2 );
	-- 3. 다중 레코드 삽입 	: insert into 테이블명 values ( 값1 , 값2 ) , ( 값1 , 값2 );
	-- 4. 검색된 결과를 삽입 	: insert into 테이블명 select
        -- 검색된 필드와 삽입할 테이블내 필드명과 동일한 경우
	-- 5. 마지막으로 추가된 레코드의 pk값 확인 : select last_insert_id();

create table maddr ( mid char(8) , maddr char(2) );		-- 회원아이디 , 주소 필드 갖는 테이블
select * from member limit 5;

insert into maddr select mid , maddr from member limit 5;
select * from maddr;
select last_insert_id();

-- update
	-- update 테이블명 set 필드명 = 수정할값 where 조건식
    -- 1. 해당 테이블에서 주소가 서울이면 'seoul'
    select * from member;
	update member set maddr = 'seoul' where maddr = '서울';	-- 오류 [ char(2) 라서 ]
    -- 워크벤치 기준 : update, delete 기본적으로 사용 불가능
		-- 1. 메뉴 -> edit -> preferences -> 사이드메뉴 SQL editor 선택 -> 가장 아래 safe updates 체크 해제
		-- 2. SET SQL_SAFE_UPDATES = 0;
		SET SQL_SAFE_UPDATES = 0;
    update member set maddr = 'se' where maddr = '서울';
	update member set mnumber = mnumber/3;		-- 연산 가능
    update member set maddr = '안산';				-- 조건[where] 없을 경우 전체 변경

-- delete
	-- delete from 테이블명 where 조건식
    -- * 만일 관계테이블 일 경우 다른 테이블에서 fk필드로 사용된 레코드는 삭제 불가능 [ * 제약조건 필요! ]
	-- 1. 만약에 핑크가 포함된 이름 삭제
    delete from member where mname like '%핑크%';	-- Error Code 1451 : pk가 포함된 레코드 삭제하면 fk ???
    delete from member where mname = '잇지';		-- 성공! 관계가 있는 테이블에서 fk필드가 없기때문에 삭제 성공

/*
	연산자
		1.산술연산자 : +더하기 -빼기 *곱하기 /나누기 div(몫) mod(나버지)
        2. 비교연산자 : >= <= > < = !=
        3. 논리연산자 : and or not 
        4. 기타연산자 : 
				in( 값1 , 값2 , 값3 ) 	: 값1~값3 하나라도 존재하면 [ 값1 or 값2 or 값3 ]
                값 between 값1 and 값2 	: 값이 값1부터 값2 사이이면 true [ 값>=값1 and 값<값2 ]
                like 					: 패턴 검색
					% : 모든 문자수 대응		_ : _ 개수만큼 문자수 대응
		5. null 관련 연산자
			필드명 is null				: null이면
			필드명 is not null			: null이 아니면
        
	집계함수 
		sum ( 필드명 ) 	: 해당 필드내 데이터 총합
        avg ( 필드명 ) 	: 해당 필드내 데이터 평균
        max ( 필드명 ) 	: 해당 필드내 데이터 최대값
        min ( 필드명 ) 	: 해당 필드내 데이터 최소값
        count ( 필드명 ) 	: 해당 필드내 데이터 수[null x] / count(*) : 레코드 수 [ null 포함 ]
*/

-- 타입 확인
drop table if exists testtype;
create table testtype(
	tinyint_col tinyint , 		-- 1바이트 +-128 저장
    smallint_col smallint ,		-- 2바이트 +-3만정도 저장
    int_col int ,				-- 4바이트 +-21억정도 저장 
    int_col2 int unsigned ,		-- 4바이트 + 43억정도 저장 [ unsigned : 음수사용x : 음수용량 -> 양수용량증가 ]
    bigint_col bigint ,			-- 8바이트 +-21억이상 저장
	char_col char( 10 ) ,		-- char( 길이 ) 		: 고정길이 [1~255] 저장
    varchar_col varchar( 10 ) , -- varchar ( 길이 ) 	: 가변길이 [1~16383] 저장 * 버전 mysql 5.0 이상
    text_col text ,				-- 1~65535 글자 저장
    longtext_col longtext ,		-- 1~42억 정도 글자 저장
    float_col float ,			-- 소수점 7자리 저장
    double_col double ,			-- 소수점 15자리 저장
    date_col date , 			-- 날짜 저장 [ yyyy - MM - dd ]
    time_col time ,				-- 시간 저장 [ hh : mm : ss ]
    datetime_col datetime		-- 날짜/시간 저장 [ yyyy-MM-dd hh:mm:ss ]
);
select * from testtype;









