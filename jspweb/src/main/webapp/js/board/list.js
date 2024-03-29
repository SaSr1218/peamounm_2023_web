
// pageObject : 현재 페이지 , 검색 , 전송타입 보관된 객체
let pageObject = {
	page : 1 ,		// page : 검색할 페이지
	key : "" ,
	keyword : "" , 
	type : 1 ,		// 1 : 전체출력 / 2 : 개별출력
	cno : document.querySelector('.cno').value ,
	listsize : 3
};

// 카테고리 제목 넣어주기
let cnameHTML = '';
if ( pageObject.cno == 1 ){ cnameHTML = '공지사항'; }
if ( pageObject.cno == 2 ){ cnameHTML = '커뮤니티'; }
if ( pageObject.cno == 3 ){ cnameHTML = 'QnA'; }
if ( pageObject.cno == 4 ){ cnameHTML = '노하우'; }
document.querySelector('.cname').innerHTML = cnameHTML;


// 1. 모든 게시물 출력
getBoardList(1);
function getBoardList( page ){
	// 해당함수로부터 페이징번호 받기
	pageObject.page = page;
	$.ajax({
		url : "/jspweb/board/info" ,
		method : "get" ,
		data :  pageObject  , 
		success : (r) => {
			console.log('통성'); console.log(r);
				let html = ``;
				r.boardList.forEach ( ( o , i ) => {
				html += `			
						<div class="boardcontent">
							<div>
								<img alt="" class="hpimg" 
								src="/jspweb/member/mimg/${ o.mimg == null ? 'default.webp' : o.mimg }">
								
								<span class="mid"> ${ o.mid } </span>
								<span class="bdate"> ${ o.bdate } </span>
							</div>
							<div class="btitle"> 
								<a href="/jspweb/board/view.jsp?bno=${ o.bno }"> ${ o.btitle } </a>
							</div>
							<div class="contentbottom">
								<span> <i class="far fa-eye"></i> <span class="bview">${ o.bview }</span> </span>
								<span> <i class="far fa-thumbs-up"></i> <span class="bup">${ o.bgood }</span> </span>
								<span> <i class="far fa-thumbs-down"></i> <span class="bdown">${ o.bbad }</span> </span>
								<span> <i class="far fa-comment-dots"></i> <span class="rcount">${ o.rcount } </span> </span>
							</div>
						</div>`;	
				})
				document.querySelector('.boardTable').innerHTML = html;
				// ---------------- 페이지 버튼 출력 ---------------- //
				html = ''; // 기존에 들어있던 내용 제거
				// 이전
					html += page <=1 ?
						`<button onclick="getBoardList(${page})" type="button" class="pagebtn"> < </button>`				
						: 
						`<button onclick="getBoardList(${page-1})" type="button" class="pagebtn"> < </button>`
				// 페이징 번호 버튼 틀
				for ( let i = r.startbtn ; i<=r.endbtn ; i++ ){ // 시작 버튼번호부터 마지막 버튼번호까지 버튼 생성
					html += 
						`<button onclick="getBoardList(${i})" type="button" class="pagebtn"> ${i} </button>`
				}
				// 다음
					html += page >= r.totalpage ?
						`<button onclick="getBoardList(${page})" type="button" class="pagebtn"> > </button>`				
						:
						`<button onclick="getBoardList(${page+1})" type="button" class="pagebtn"> > </button>`				

				document.querySelector('.pagebox').innerHTML = html;
				// -------------------- 총 게시물 수 출력 ------------- //
				document.querySelector('.searchcount').innerHTML = `게시물 수 : ${r.totalsize}`;
		} // success end
		
	}) // ajax end
} // 모든 게시물 출력 end

/* 
	1. 클릭한 pk[식별자] 이동하는 경우의 수
		1. HTTP get 메소드 방식의 a 태그를 이용한 pk 이동
			<a href="jspweb/board/view.jsp">
			http://localhost:8080/jspweb/board/view.jsp
			---> 추가 a태그에 변수 넘기기
			<a href="jspweb/board/view.jsp?변수명=데이터">
			<a href="jspweb/board/view.jsp?변수명=데이터&변수명=데이터&변수명=데이터">
		
		실제 적용
			<a href="jspweb/board/view.jsp?bno=${o.bno}">
		실제 결과물
			http://localhost:8080/jspweb/board/view.jsp?bno=1
		
		
*/

function getsearch(){
	// 입력받은 데이터를 전역객체내 필드에 대입
	pageObject.key = document.querySelector('.key').value;
	pageObject.keyword = document.querySelector('.keyword').value;
	// * 게시물리스트 호출
	getBoardList(1);
}


// 3. 검색 풀기 : 전체보기
function setsearch(){
	pageObject.key = '';
	pageObject.keyword = '';
	getBoardList(1);
}

// 4. 화면에 표시할 게시물 개수 변경 함수
function setlistsize(){
	let listsize = document.querySelector('.listsize').value;
	pageObject.listsize = listsize;
	getBoardList(1);
}


// 전체 게시물 출력 순서 = 페이징 처리(페이지 수 , 버튼) -> 검색 처리(key , keyword) -> 카테고리








