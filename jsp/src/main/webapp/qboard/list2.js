console.log('작동 확인')

qboard_print( null, null );
// 1. 출력 함수
function qboard_print( 키워드, 검색어 ){
	if ( 키워드 == null && 검색어 == null){
		alert('검색어가 없습니다.')
	} else{
		alert('검색이 있는 게시물을 출력합니다.')
	}
	
	let qboardlist = [
	{ 	번호: 5 , 제목: 'hello5' , 작성자: '최성아5' , 작성일: '2023-01-30' , 조회수: 354 , 문의상태: '접수', 처리일: '처리 중' } ,
	{	번호: 4 , 제목: 'hello4' , 작성자: '최성아4' , 작성일: '2023-01-29' , 조회수: 123 , 문의상태: '접수', 처리일: '처리 중' } ,
	{	번호: 3 , 제목: 'hello3' , 작성자: '최성아3' , 작성일: '2023-01-28' , 조회수: 423 , 문의상태: '완료', 처리일: '2023-01-30' } ,
	{	번호: 2 , 제목: 'hello2' , 작성자: '최성아2' , 작성일: '2023-01-27' , 조회수: 648 , 문의상태: '완료', 처리일: '2023-01-30' } ,
	{	번호: 1 , 제목: 'hello1' , 작성자: '최성아1' , 작성일: '2023-01-26' , 조회수: 100 , 문의상태: '완료', 처리일: '2023-01-30' }	
	]
	
	let html = ''
	for (let i = 0 ; i < qboardlist.length; i++){
		html += 
		`<tr>
			<td>${i+1}</td>
			<td><a href="../board/view.html">${qboardlist[i].제목}</a></td>
			<td>${qboardlist[i].작성자}</td>
			<td>${qboardlist[i].작성일}</td>
			<td>${qboardlist[i].조회수}</td>
			<td>${qboardlist[i].문의상태}</td>
			<td>${qboardlist[i].처리일}</td>
		</tr>`
		
	}
	document.querySelector('.qboard_table').innerHTML = html
}

// 2. 검색 버튼 클릭했을때 검색
document.querySelector('.qsearbtn').addEventListener( 'click', (e) => {
	console.log('검색확인')
	// 1.키워드
	let 키워드 = document.querySelector('.키워드').value
	// 2. 검색어
	let 검색어 = document.querySelector('.검색어').value
	console.log(키워드); console.log(검색어);
	qboard_print( 키워드, 검색어)
})