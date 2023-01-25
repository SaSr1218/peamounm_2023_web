
// * 함수 밖에 만드는 이유 : 전역변수 : 모든 함수에서 동일한 메모리 사용
let contents = [ ]

// 이벤트 함수
	 // + document.querySelector('.onwritebtn').addEventListener( 'click , (e) => { })

// 1.
function onwrite(){ // f s

	// 1.
	let info={
		bwriter : document.querySelector('.bwriter').value , 
		bpassword : document.querySelector('.bpassword').value ,
		btitle : document.querySelector('.btitle').value ,
		bcontent : document.querySelector('.bcontent').value
	}
	console.log( info ) // 객체 정보 출력시 문자열 연결연산자 금지
	
	// 2. 유효성검사	// 객체명.속성명
		// 1. 입력받은 데이터 길이 체크
	if( info.bwriter.length <= 0 || info.bpassword.length <= 0 || info.btitle.length <= 0 || info.bcontent.length <=0 ){
		alert('작성이 안된 구역이 있습니다. 작성해주세요.')
		return; // 함수 종료	
	}
	
	// 3. 배열에 저장 * 추후 : 백엔드에게 통신해서 데이터 전달. [ 백엔드 : java, db ]
	contents.push( info )
	alert('글 등록 성공')
	console.log( contents )
	// 4. input 초기화
	document.querySelector('.bwriter').value = ' '
	document.querySelector('.bpassword').value = ' '
	document.querySelector('.btitle').value = ' '
	document.querySelector('.bcontent').value = ' '
	
} // f e

