
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
	
	// * 2. 시작 요일 전에 공백 채우기
	for (let b = 1 ; b<=weekday ; b++){
		html += `<div class="day"></div>`
	}
	// * 1. 일 만들기 [ 1 ~ 마지막 일[월마다 다름] 까지 ]
	for (let day =1; day<=lastday; day++){
		html += `<div class="day"> ${day} </div> `
	}
				
				
	// 4. 마크업에 출력
	document.querySelector('.cal_day').innerHTML = html
}

// 3. 이전달 버튼 클릭 이벤트에 따른 연도와 월이 변경
document.querySelector('.prevbtn').addEventListener( 'click', (e) => {
	// 1. 월 1차감 했을 경우 0이면 연도 1차감 월 12로 설정
	month --;
	if( month < 1 ){year--; month = 12; }
	cal_print();
})
// 3. 다음달 버튼 클릭 이벤트에 따른 연도와 월이 변경
document.querySelector('.nextbtn').addEventListener( 'click', (e) => {
	month ++;
	if (month > 12){ year++; month = 1;	}
	cal_print(); 
})


/*
	new Date() 날짜/시간 관련 클래스
		1. let date = new Date() 				: 현재 날짜 / 시간 객체
		2. let date = new Date( 2023, 01, 31 )  : 사용자정의 날짜/시간 객체
		3. let date = new Date( 연도, 월, 0 )	: 연도와 월 날짜에 따른 마지막 일 날짜
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
 
  */
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 