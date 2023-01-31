
let contents = [ ]



/* --------------------전역변수 : 모든 함수{ } 공용으로 사용되는 메모리 [변수] --------- */
// 1. js 열렸을때 현재 연도와 월을 구해서 변수에 저장
let year = new Date().getFullYear(); // 현재 연도
let month = new Date().getMonth()+1; // 현재 월


// 2. 캘린더 상단에 표시
cal_print();
function cal_print(){
	document.querySelector('.top_date').innerHTML = `${year}년 ${month}월` ;
	
	// 3. html 구성
	let html = `<div class="day weekday sunday"> 일 </div> <div class="day weekday"> 월 </div>
				<div class="day weekday"> 화 </div> <div class="day weekday"> 수 </div>
				<div class="day weekday"> 목 </div> <div class="day weekday"> 금 </div>
				<div class="day weekday"> 토 </div>`
				
	// 2. 현재 캘린더 설정된 날짜의 마지막 일 구하기
	let lastday = new Date ( year, month, 0 ).getDate(); console.log('현재 캘린더 마지막일 : ' +  lastday)
	// * 현재 캘린더 설정된 날짜의 시작 요일 구하기
	let weekday = new Date ( year , month-1 , 1 ).getDay(); console.log('현재 캘린더 시작일 : ' +  weekday)
	
	// * 4.2. 시작 요일 전에 공백 채우기
	for (let b = 1 ; b<=weekday ; b++){
		html += `<div class="day"></div>`
	}
	// * 4.1. 일 만들기 [ 1 ~ 마지막 일[월마다 다름] 까지 ]
	for (let day =1; day<=lastday; day++){
		
		// 4. 날짜 확인
		let date = new Date( year , month-1 , day );
		let fdate = date_format( date );
		
		html += `<div class="day" onclick="openModal( ${fdate} )">
					 ${day} ${ contents_print( fdate ) } 
				 </div> `
	}
	// 3. 마크업에 출력
	document.querySelector('.cal_day').innerHTML = html
}


// 7. 모달 닫기 함수
document.querySelector('.modal_close').addEventListener('click', (e) =>{
	// 모달 배경 구역 css 변경해서 모달 숨기기
document.querySelector('.modal_wrap').style.display = 'none';
})


// 9. 일정 삭제
function onDelete( i ) {
	// 1. 배열내 해당 인덱스 사겢
	contents.splice( i , 1)
	// 2. 화면 업데이트
	document.querySelector('.modal_wrap').style.display = 'none';
	cal_print();
}



// 8. 등록 버튼 눌렀을때 함수
document.querySelector('.modal_write').addEventListener('click', (e) =>{
	// 1. 입력받은 내용과 선택된 날짜 가져와서 객체화
	let content = {
		date: document.querySelector('.modal_date').innerHTML ,
		content: document.querySelector('.modal_input').value ,
		bg_color : document.querySelector('.modal_color').value
	}; console.log( content );
	// 2. 유효성검사 생략
	// 3. 배열 저장
	contents.push( content );
	// 4. 화면 업데이트
		// 1. 입력된 데이터 초기화
		document.querySelector('.modal_input').value = ''
		// 2. 모달 닫기
		document.querySelector('.modal_wrap').style.display = 'none';
		// 3. 캘린더 재출력[렌더링]
		cal_print();
})


// 6. 모달 열기 함수
function openModal( fdate ){
	// 1. 모달 배경 구역 css 변경해서 모달 숨기기
	document.querySelector('.modal_wrap').style.display = 'flex';
	// 2. 모달에 선택된 날짜 표시하기
	document.querySelector('.modal_date').innerHTML = fdate
	// 3. 해당 하는 날짜의 모든 일정 출력
		// 1. 기본 html 구성
	let html = `<tr>
					<th width="5%"> # </th> <th> 일정내용 </th> <th width="15%"> 비고 </th>
				</tr>`

		// 2. 일정목록 반복문 돌려서 선택된 날짜와 동일한 일정 찾기
	let j = 0; // j : 동일한일정의 일정[객체]들의 개수
// [반복문 1번 방법]
	contents.forEach( ( o , i ) =>{ // i : 일정객체들의 인덱스 순서
		if(fdate == o.date){ //만약에 모달클릭시 선택된 날짜와 일정목록에 있는 날짜가 같다면
			j++;	// 찾은 개수 증가
			html += `<tr>
						<td> ${j} </td>
						<td> ${o.content} </td>
						<td> <button onclick="onDelete( ${i} )"> 삭제 </button></td>
					</tr>`
		}
	})
	document.querySelector('.table').innerHTML = html;
}
/* [반복문 2번 방법]
   for(let i = 0 ; i<contents.length; i++){
      if( fdate == contents[i].date){
			html += `<tr>
						<td> ${i+1} </td>
						<td> ${contents[i].content} </td>
						<td> <button onclick="onDelete( ${i} )">삭제</button></td>
					</tr>`
      }
   }
*/
	

// 5. 일정 출력 함수
function contents_print(fdate){
	console.log(fdate)
	// 1. 인수로 전달된 날짜와 동일한 일정 날짜 찾기
		// 1. html
		let html = ``
	contents.forEach( (o) => { // 일정목록 반복문
		if(fdate == o.date){ // 만약에 인수로 전달된 날짜와 일정목록에서 동일한 날짜가 존재하면
			html += `<div class="content" style="background-color : ${o.bg_color}">${o.content}</div>`
		}
	}) // for end
	return html;
}


// 4. 날짜 포맷 함수 [ 인수 : 날짜 ---- 로직[포맷] ----> 반환 : 변경된 날짜형식]
function date_format( date ){
	
	let d_year = date.getFullYear();
		// 만약에 월/요일이 한자리수(<=9)이면 앞에 0 붙이기
	let d_month = (date.getMonth()+1) <= 9 ? '0' + (date.getMonth()+1) : (date.getMonth()+1) ;
	let d_day = date.getDate() <= 9 ? '0' + date.getDate() : date.getDate();
	return `${d_year}${d_month}${d_day}`
}


// 3-1 이전달 버튼 클릭 이벤트에 따른 연도와 월이 변경
document.querySelector('.prevbtn').addEventListener( 'click', (e) => {
	// 1. 월 1차감 했을 경우 0이면 연도 1차감 월 12로 설정
	month --;
	if( month < 1 ){year--; month = 12; }
	cal_print();
})
// 3-2 다음달 버튼 클릭 이벤트에 따른 연도와 월이 변경
document.querySelector('.nextbtn').addEventListener( 'click', (e) => {
	month ++;
	if (month > 12){ year++; month = 1;	}
	cal_print(); 
})



/*
	new Date() 날짜/시간 관련 클래스
		1. let date = new Date() 				: 현재 날짜 / 시간 객체
		2. let date = new Date( 2023, 01, 31 )  : 사용자정의 날짜/시간 객체
		3. let date = new Date( 연도, 월, 0 )		: 연도와 월 날짜에 따른 마지막 일 날짜( 월 변수에 +1 했을 경우!)
		3. let date = new Date( 연도, 월-1, 1 )	: 연도와 월 날짜에 따른 1일 날짜	 ( 월 변수에 +1 했을 경우!)
			1. get 함수 제공
				1. date.getFullYear()	: 연도
				2. date.getMonth()		: 월 [0~11 / 보통 +1 처리 함]
				3. date.getDate()		: 일
				4. date.getDay()		: 요일 [0 ~ 6 / 숫자 값을 요일로 변환해줘야함. 0 = 일 , 6 = 토]

 let date = new Date(); console.log( 'date :' + date)
 let date2 = new Date( 2023, 01, 31 ); console.log('date2 :' +  date2 )
 console.log ( '연도 :' + date.getFullYear())
 console.log ( '월 : ' + date.getMonth()+1 )
 console.log ( '일 : ' + date.getDate() )
 console.log ( '요일 : ' + date.getDay() )
 
 
 	` 백틱
 		` 문자열 $ {js 작성할 수 있는 구역} `
 
  */
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 