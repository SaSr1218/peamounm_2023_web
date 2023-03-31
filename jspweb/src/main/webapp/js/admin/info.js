// --------------------- 차트 ------------------ //

	// new Chart( 'dom객체' , { 차트옵션 } );
	// {type : '차트이름' , data : { 차트에 표시할 데이터 } , options : { 차트옵션 } }
		// labels : 가로축
		
/*
	JSON = JS 객체
	let 객체명 = { 필드명/키 : 데이터 , 필드명/키 : 데이터 ,,,}
	1. 해당 객체의 필드명만 호출/추출
		Object.keys(객체명) : 객체 내 모든 필드명/키 이름을 배열로 추출
	2. 해당 객체의 값만 호출/추출
		Object.values(객체명) : 객체 내 모든 데이터 를 배열로 추출 	 
	 
*/
const ctx = document.getElementById('myChart');
	
$.get ( "/jspweb/point" , (r) => {
	console.log(r);
	console.log( Object.keys(r) );
	console.log( Object.values(r) );

  new Chart(ctx, {
    type: 'bar', // bar : 막대차트 vs line : 선차트
    data: {
      labels: Object.keys(r) ,
      datasets: [{
        label: '4 mno 멤버 포인트 충전 내역' ,			// 데이터 항목명
        data: Object.values(r) ,	// 해당 항목 데이터 
        borderWidth: 4							// 막대 굵기
      }] ,
    },
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });
	
})




// -----------------------관리자 기준 모든 회원 호출----------------- //

// js -> admin -> info.js

let memberName = {
	page : 1 ,
	key : "" ,
	keyword : "" ,
	listsize : 3
};

getMemberList(1);
function getMemberList( page ){	// 회원 전체 호출
	memberName.page = page;
	document.querySelector('.now_page').innerHTML = page;

	$.ajax({
		url: "/jspweb/member",
		method : "get",
		data : memberName ,
		success : (r)=>{
			// 1. 응답데이터 처리 
				// 1. 테이블 헤더 구성 
			let html = `<tr>
							<th width="10%"> 번호 </th>
							<th width="10%"> 프로필 </th>
							<th width="10%"> 아이디 </th>
							<th width="10%"> 이메일주소 </th>
							<th width="10%"> 비고 </th>
						</tr>`
			r.memberList.forEach( (o,i) =>{
				// 2. 테이블 내용물 추가 구성 
					// 만약에 회원 mimg 프로필이미지가 null 이면 기본프로필 사용 / 아니면 mimg 사용 
				html +=	`<tr>
							<td> ${ o.mno } </td>
							<td> <img src="/jspweb/member/mimg/${ o.mimg == null ? 'default.webp' : o.mimg }" width="100%">  </td>
							<td> ${ o.mid } </td>
							<td> ${ o.memail } </td>
							<td> </td>
						</tr>`
			} ); // for end 
				// 3. 구성된html를 table 대입 
			document.querySelector('.mListTable').innerHTML = html;
			console.log("현재 페이지 체크 : " + page)
			console.log(r)
			
			// --------------- 멤버 버튼 출력 ---------- //
			html = '';
				html += page <= 1 ?
						`<button onclick="getMemberList(${page})" type="button"> 이전 </button>`				
						: 
						`<button onclick="getMemberList(${page-1})" type="button"> 이전 </button>`						
			// ---------------- 페이징 번호 버튼 틀 ----------- //
			
			for ( let i = r.startbtn ; i<=r.endbtn ; i++ ){ // 시작 버튼번호부터 마지막 버튼번호까지 버튼 생성
				html += 
					`<button onclick="getMemberList(${i})" type="button"> ${i} </button>`
			}
			// 다음
				html += page >= r.totalpage ?
					`<button onclick="getMemberList(${page})" type="button"> 다음 </button>`				
					:
					`<button onclick="getMemberList(${page+1})" type="button"> 다음 </button>`				
			document.querySelector('.pagebox').innerHTML = html;			
			
			// ------------ 총 회원 수 표시 ------------//
			document.querySelector('.msearchcount').innerHTML = `총 회원 수 : ${r.totalsize}`
			
		} // success end
	}) // ajax end
} 

// 검색 값 받기 ( key , keyword )
function getmember(){
	// 입력받은 데이터를 전역객체내 필드에 대입
	memberName.key = document.querySelector('.key').value;
	memberName.keyword = document.querySelector('.keyword').value;
	// * 게시물리스트 호출
	getMemberList(1);
}


//  화면에 표시할 게시물 개수 변경 함수
function setlistsize(){
	let listsize = document.querySelector('.listsize').value;
	memberName.listsize = listsize;
	getMemberList(1);
}





