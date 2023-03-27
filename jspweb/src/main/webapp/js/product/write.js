
// 회원제페이지
if (memberInfo.mid == null){
	alert('로그인 후 제품 등록 가능합니다.')
	location.href = "/jspweb/member/login.jsp"
}

// 위도, 경도 전역 변수 만들기
let plat = 0;
let plng = 0;

// 제품 등록 함수
function onwrite() {
	
	// 1. 폼 객체
	let writeForm = document.querySelectorAll('.writeForm')[0];
	// 2. 폼 데이터 객체 선언
	let writeFormData = new FormData ( writeForm )
	// 3. 좌표 [ 위도/경도 ] 추가
	// 폼 데이터 객체 에 필드 추가 [ set 필드명 중복 불가능 ]
	writeFormData.set( "plat" , plat );
	writeFormData.set( "plng" , plng );
	
	if ( plat == 0 || plng == 0 ) { alert('위치 선택후 등록해주세요.'); return; }
	if ( fileList.length < 1 ) { alert('하나 이상의 이미지를 등록해주세요.'); return; } 
	
	// 폼에 첨부파일 등록
	fileList.forEach ( (f) => {
		// 배열에 존재하는 파일들을 하나씩 폼에 등록 [ append 필드명 중복 가능 ]
		writeFormData.append("fileList" , f ); 
	})
		
	$.ajax({
		url : "/jspweb/product/info" ,
		method : "post" ,
		data : writeFormData ,
		contentType : false ,
		processData : false ,
		success : (r) => {
			console.log(r)
			if ( r == 'true'){
				alert('등록성공'); location.href="/jspweb/index.jsp"
			}else { alert ('등록실패'); }
		}
	})
	
}


// ----------------- 카카오 지도 표시할 div 객체 --------------- //
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// ------------------ 지도를 클릭한 위치에 표출할 마커입니다 -------------- //
var marker = new kakao.maps.Marker({ 
    // 지도 중심좌표에 마커를 생성합니다 
    position: map.getCenter() 
}); 
// 지도에 마커를 표시합니다
marker.setMap(map);

// ------------------ 지도에 클릭 이벤트를 등록합니다 ----------------- //
// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
    
   
    var latlng = mouseEvent.latLng;  // 클릭한 위도, 경도 정보를 가져옵니다
    marker.setPosition(latlng);		// 마커 위치를 클릭한 위치로 옮깁니다
    
    plat = latlng.getLat()
    console.log("위도 : " + latlng.getLat() )
    plng = latlng.getLng()
    console.log("경도 : " + latlng.getLng() )

});

// -------------------드래그앤드랍 구현 ------------------- //
// 1. 드로그앤드랍 할 구역 [DOM] 객체 호출
let fileDrop = document.querySelector('.fileDrop');

// 2. 해당 구역에 이벤트 등록

	// 1.dragenter [ '드래그 요소가 해당 구역에 닿았을때 실행' ]
	fileDrop.addEventListener("dragenter" , (e) => {
		e.preventDefault(); 
	})
	
	// 2.dragover [ '드래그 요소가 해당 구역에 위치하고 있을때 실행' ]
	fileDrop.addEventListener("dragover" , (e) => {
		fileDrop.style.backgroundColor = "#e8e8e8"; // over했을때 회색 넣기
		e.preventDefault();
	})	
		
	// 3.dragleave [ '드래그 요소가 해당 구역에 나갔을때' ]
	fileDrop.addEventListener("dragleave" , (e) => {
		console.log()
		e.preventDefault(); 
		fileDrop.style.backgroundColor = "#ffffff";
		
	})	

// 해당 구역에 드래그된 파일들을 저장하는 리스트/배열
let fileList = [];

	// 4.drop
	fileDrop.addEventListener("drop" , (e) => {
		console.log('드래그 요소가 해당 구역에 드랍 되었을때')
		// 문제점 : 브라우저에 드랍했을때 해당 페이지가 열리는 문제 발생
		e.preventDefault(); // 브라우저 고유 이벤트 제거
		// 1. 드랍된 파일을[dataTransfer]을 호출
		let files = e.dataTransfer.files // forEach 사용불가
		console.log(files)
		for ( let i = 0 ; i<files.length; i++ ){
			console.log(files[i]);
			if ( files[i] != null && files[i] != undefined ){
				fileList.push( files[i] );
			}
	 	} // for end
		fileDrop.style.backgroundColor = "#ffffff";	 // 배경색 흰색으로 돌리기	
	 	printfiles(); // 파일 목록 출력
	})// drop 이벤트 end

// 3. 해당구역에 드랍된 파일 목록 출력
function printfiles(){
	let html = '';
	fileList.forEach( (f , i) => {
		let fname = f.name; // 파일명 호출		
		let fsize = (f.size / 1024 / 1024).toFixed(3) ; // 파일 용량 [바이트 --> MB 변경]
														// .toFixed(표시할소수자리수)
		html += `<div>
					<span>${fname} </span>
					<span>${fsize}MB </span>
					<span> <button onclick="filedelete(${i})" type="button"> 삭제 </button> </span>
				 </div>`
	})
	fileDrop.innerHTML = html;
}

// 4. 첨부파일 드래그앤드랍된 파일 목록에서 특정 파일 제거
function filedelete(i){ fileList.splice( i , 1); printfiles(); } // 삭제 후 렌더링

























