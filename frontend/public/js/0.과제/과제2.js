console.log('----------------과제2-------------')

// 테이블의 첫 행 제목 thml 대입
// let html변수명 = 'HTML마크업작성'		// ! : 마크업은 문자처리로!



// 3.
function onResult( ){
	console.log('클릭했다') //3. 확인
	// function 밖에 넣으면 1번 실행 = 결과누적, function 안에 넣으면 클릭마다 초기화
	let 테이블행 = '<tr> <th>단</th> <th>곱</th> <th>결과</th> </tr>';
	// 4. 단:<input> 곱<input> 에 입력된 value 각 변수에 저장
	let dan = document.querySelector('.dan').value
	let gob = document.querySelector('.gob').value
	
	// 3. <table> 행 <tr> 반복문으로 만들기
	for ( let 곱 = 1 ; 곱 <= gob; 곱++ ){ // for s
		// 곱은 1부터 입력받은 값까지 1씩증가 반복처리
		
		// ! : 변수명과 수식은 문자처리 X
		// 4. 마크업과 변수 이용한 HTML 구성하고 변수에 추가
		테이블행 += '<tr> <th> '+dan+' </th> <th>' +곱+ '</th> <th>'+ (dan*곱)+ '</th> </tr>'
		// += 추가 / = 대입
	} // for e
	
	// 5. 반복문으로 누적된 HTML 변수를 TABLE에 넣어주기
	document.querySelector('.gu_table').innerHTML = 테이블행
}




/*function btn( ){                                            // 내가 한 부분
	let 단 = document.querySelector('.단')
	let 곱 = document.querySelector('.곱')
	
	let 단Value = 단.value;
	let 곱Value = 곱.value;
	
	
	let 단Index = 테이블행.indexOf( 단Value )
	let 곱Index = 테이블행.indexOf( 곱Value )
	
	if ( 테이블행 == -1 && 테이블행 == -1){
		document.querySelector('.단Index').innerHTML = 단
		document.querySelector('.곱Index').innerHTML = 곱
	}
}*/









