
let contents = [
	{ date: '20230101' , content : '인문학 명저 독서'} ,
	{ date: '20230104' , content : '집안 대청소'} ,
	{ date: '20230107' , content : '코딩 공부하기, 정처기 공부하기, 백준 공부하기'}
]



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

// 6. 모달 열기 함수
function openModal( fdate ){
	// 모달 배경 구역 css 변경해서 모달 숨기기
	document.querySelector('.modal_wrap').style.display = 'flex';
}


// 5. 일정 출력 함수
function contents_print(fdate){
	console.log(fdate)
	// 1. 인수로 전달된 날짜와 동일한 일정 날짜 찾기
		// 1. html
		let html = ``
	contents.forEach( (o) => { // 일정목록 반복문
		if(fdate == o.date){ // 만약에 인수로 전달된 날짜와 일정목록에서 동일한 날짜가 존재하면
			html += `<div class="content">${o.content}</div>`
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
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 