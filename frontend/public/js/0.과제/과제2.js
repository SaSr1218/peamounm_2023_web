console.log('----------------과제2-------------')

// 테이블의 첫 행 제목 thml 대입
// let html변수명 = 'HTML마크업작성'		// ! : 마크업은 문자처리로!
let 테이블행 = '<tr> <th>단</th> <th>곱</th> <th>결과</th> </tr>'; 


// 3. <table> 행 <tr> 반복문으로 만들기
for ( let 곱 = 1 ; 곱 <= 9; 곱++ ){ // for s
	// 곱은 1부터 9까지 1씩 증가 반복 처리
	
	// ! : 변수명과 수식은 문자처리 X
	// 4. 마크업과 변수 이용한 HTML 구성하고 변수에 추가
	테이블행 += '<tr> <th> 2 </th> <th>' +곱+ '</th> <th>'+ (2*곱)+ '</th> </tr>'
	// += 추가 / = 대입
} // for e

// 5. 반복문으로 누적된 HTML 변수를 TABLE에 넣어주기
document.querySelector('.gu_table').innerHTML = 테이블행