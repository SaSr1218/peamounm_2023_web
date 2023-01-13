console.log('js 작동'); // 스크립트 확인

// 1. 숫자 버튼 출력하는 함수
function 버튼출력(){ // f s
	
	console.log('2. 함수작동') // 함수확인
	
	let 버튼목록 = ''; // for 밖에 만든 이유 : 45개의 버튼을 누적 저장하려고
	
	// for
	for (let i = 1 ; i <= 45 ; i ++ ) { // for s
		console.log ( '3. : ' + i) // for문 확인
				
			버튼목록 += `<button onclick="버튼클릭(${i})">${i}</button>`
			// 만약에 i가 5 배수일때마다 줄바꿈
			if( i % 5 == 0 ){ 버튼목록 += '<br>'}
	} // for e
	console.log('4. : ' + 버튼목록) // 버튼목록 확인
	
	// 해당 <div>에 버튼목록 넣어주기
	document.querySelector('.버튼목록상자').innerHTML = 버튼목록
	
	// 취소 버튼 보이게 활성화
	document.querySelector('.취소버튼').style.display = 'inline'
	
} // f e

function 버튼출력취소( ){
	// 해당 <div>에 ''공백으로 변경하기
	document.querySelector('.버튼목록상자').innerHTML = ''
	// 취소 버튼 안보이게 비활성화
	document.querySelector('.취소버튼').style.display = 'none'
}



	// 1. 문자열 처리
			// '<button onclick="버튼클릭('+i+')">'+i+'</button>'
			// 'html 문자열 ~~~~' + 변수 + 'html문자열' + 변수 + 
		
		// 2. 백틱 이용한 문자열처리
			// `( 백틱 - esc 아래 )
			// 1. 전체를 백틱으로 감싼다.
			// 2. 변수/수식/함수호출은  ${변수/수식} 감싼다.