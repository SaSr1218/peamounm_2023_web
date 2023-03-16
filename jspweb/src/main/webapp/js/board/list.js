
// 1. 모든 게시물 출력
getBoardList(1);
function getBoardList( page ){
	// 해당함수로부터 페이징번호 받기
	console.log('해당페이지 : ' + page);
	$.ajax({
		url : "/jspweb/board/info" ,
		method : "get" ,
		data : { "type" : 1 , "page" : page } , // 1 : 전체출력 / 2 : 개별출력
		success : (r) => {
				console.log('통신성공'); console.log(r);
				let html = `<tr>
							<th> 게시물번호 </th>
							<th> 게시물제목 </th>
							<th> 작성자 </th>
							<th> 작성일 </th>
							<th> 조회수 </th>
							<th> 좋아요 </th>
							<th> 싫어요 </th>
						</tr>`
				r.boardList.forEach ( ( o , i ) => {
				html += `<tr>
							<td> ${ o.bno } </td>
							<td> <a href="/jspweb/board/view.jsp?bno=${ o.bno }">${ o.btitle } </a></td>
							<td> ${ o.mid } </td>
							<td> ${ o.bdate } </td>
							<td> ${ o.bview } </td>
							<td> ${ o.bgood } </td>
							<td> ${ o.bbad } </td>			
						</tr>`		
				})
				document.querySelector('.boardTable').innerHTML = html;
				// ---------------- 페이지 버튼 출력 ---------------- //
				html = ''; // 기존에 들어있던 내용 제거
				// 이전
					html += page <=1 ?
						`<button onclick="getBoardList(${page})" type="button"> 이전 </button>`				
						: 
						`<button onclick="getBoardList(${page-1})" type="button"> 이전 </button>`
				// 페이징 번호 버튼 틀
				for ( let i = r.startbtn ; i<=r.endbtn ; i++ ){ // 시작 버튼번호부터 마지막 버튼번호까지 버튼 생성
					html += 
						`<button onclick="getBoardList(${i})" type="button"> ${i} </button>`
				}
				// 다음
					html += page >= r.totalpage ?
						`<button onclick="getBoardList(${page})" type="button"> 다음 </button>`				
						:
						`<button onclick="getBoardList(${page+1})" type="button"> 다음 </button>`				

				document.querySelector('.pagebox').innerHTML = html;
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














