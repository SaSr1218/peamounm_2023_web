
let productlist = null;

// ---------------------- 사이드바 제품목록페이지 --------------------- //
function productlistprint( ){
	let html = '<h3>제품목록페이지</h3>'
        productlist.forEach( (p) => {
			html += `<div> 
						<span> ${p.pname} </span>
						<span> ${p.pcomment} </span>
						<span> ${p.pprice} </span>
						<span> ${p.pstate} </span>
						<span> ${p.pview} </span>
						<span> ${p.pdate} </span>
												
					</div>`
			});	
		document.querySelector('.productlistbox').innerHTML = html;	
}

    var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center : new kakao.maps.LatLng(37.3218778,126.8308848), // 지도의 중심좌표 
        level : 6 // 지도의 확대 레벨 
 });
    // 마커 클러스터러를 생성합니다 
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
        minLevel: 8 // 클러스터 할 최소 지도 레벨 
    });
 
    // 데이터를 가져오기 위해 jQuery를 사용합니다
    // 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다

// 1. 제품목록 호출 [ 1. 현재 보이는 지도좌표내 포함된 제품만 ]
function getproductlist( 동 , 서 , 남 , 북 ) {
	// 클러스터 비우기 ( 이동할때마다 클러스터가 계속 늘어나는 오류 발생 )
	clusterer.clear();

	$.ajax({
		url : "/jspweb/product/info" ,
		method : "get" ,
		async : false ,
		data : { "동" : 동 , "서" : 서 , "남" : 남 , "북" : 북 } ,
		success : (r) => {
			
	 	// --------------------- 사이드바 제품목록 ---------------------- //
		productlist = r;
		productlistprint();
        
        // ------------------------ 마커 작업 ------------------------ //
            var markers = $(r).map( ( i , p ) =>  {
				console.log(p)
				
            let marker =  new kakao.maps.Marker({
                position : new kakao.maps.LatLng(p.plat, p.plng)
            });
            
			// 마커에 클릭이벤트를 등록합니다
			kakao.maps.event.addListener(marker, 'click', function() {
			      let html = `<button onclick="productlistprint()"> 뒤로가기 </button> <h3>제품목록페이지</h3>`;
			      
			html += `<div> 
						<div> 제품명 : ${p.pname} </div>
						<div> 제품내용 : ${p.pcomment} </div>
						<div> 제품가격 : ${p.pprice} </div>
						<div> 제품상태 : ${p.pstate} </div>
						<div> 제품 조회수 : ${p.pview} </div>
						<div> 제품 등록일자 : ${p.pdate} </div>
						<div> <button class="plikebtn" onclick="setplike(${p.pno})" type="button"> </button> </div>												
					</div>`			      
			document.querySelector('.productlistbox').innerHTML = html;
			getplike(p.pno) ;   			        
			}); 
            return marker;
        });

        // 클러스터러에 마커들을 추가합니다
        clusterer.addMarkers(markers);
        
 	   } // success end
    });	// ajax end
  
} // m end
   
	
// 2. 현재 지도의 좌표 얻기
get동서남북(); // 드래그 안해도 마커 뜨게 하기 위함
function get동서남북(){

    // 지도의 현재 영역을 얻어옵니다 
    var bounds = map.getBounds();  
    
    // 영역의 남서쪽 좌표를 얻어옵니다 
    var swLatLng = bounds.getSouthWest(); 
    
    // 영역의 북동쪽 좌표를 얻어옵니다 
    var neLatLng = bounds.getNorthEast(); 

    let 동 = neLatLng.getLng()
    let 서 = swLatLng.getLng()    
    let 남 = swLatLng.getLat()
    let 북 = neLatLng.getLat()
	getproductlist( 동 , 서 , 남 , 북);	
}
// ---------------- 지도 중심 좌표 이동 이벤트 ---------------- // 
// [ 지도 정보 얻어오기 + 지도 중심좌표 변경 이펙트 ] center_changed -> dragend 로 변경
// 지도가 이동, 확대, 축소로 인해 중심좌표가 변경되면 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
kakao.maps.event.addListener(map, 'dragend', function() {
	get동서남북();
});

// 3. 찜하기 버튼 [ 1번클릭 : 찜하기 / 2번클릭 : 찜하기 취소 ]
function setplike ( pno ) {
	if ( memberInfo.mid == null ){
		alert('회원기능입니다. 로그인 후 사용해주세요.'); return;
	}
	
	$.ajax({ // post = url에 ? 가 담기지 않음! 따라서 get 방식으로 사용
		url : "/jspweb/product/like" ,
		method : "post" ,
		data : { "pno" : pno } ,
		success : (r) => { 
			if ( r == 'true'){
				alert('찜하기 등록')
				document.querySelector('.plikebtn').innerHTML = '♥'
			}else{
				alert('찜하기 취소')
				document.querySelector('.plikebtn').innerHTML = '♡'
				
			}
		} // success end 
	}) // ajax end
} // setplike end

// 4. 현재 회원의 해당 제품 찜하기 상태 호출
function getplike ( pno ) {
	if ( memberInfo.mid == null ){ document.querySelector('.plikebtn').innerHTML = '♡' }
	
	$.ajax({
		url : "/jspweb/product/like" ,
		data : { "pno" : pno } ,
		async : false ,
		success : (r) => {
			console.log(r);
			if ( r == 'true'){ 
				document.querySelector('.plikebtn').innerHTML = '♥'
				}
			else {
				document.querySelector('.plikebtn').innerHTML = '♡' 
				}			
		}
	})
	
/*	
$.get ( "/jspweb/product/like?pno"=+pno , (r) => {
		if ( r == 'true'){ return "♥"; }
		else { return '♡'; }
	});
*/
	
}








/*
        // .map ( ( 인덱스 , 반복객체명) => { } ) 실행문에서 return 값을 배열에 대입
        // vs
        // .forEach ( ( 반복객체명 , 인덱스) => { } ) 실행문에서 return 값 X
*/

// ---------------------- 지도 정보 얻어오기 ------------------ //

/*getInfo();
function getInfo() {
    // 지도의 현재 중심좌표를 얻어옵니다 
    var center = map.getCenter(); 
    
    // 지도의 현재 레벨을 얻어옵니다
    var level = map.getLevel();
    
    // 지도타입을 얻어옵니다
    var mapTypeId = map.getMapTypeId(); 
    
    // 지도의 현재 영역을 얻어옵니다 
    var bounds = map.getBounds();
    
    // 영역의 남서쪽 좌표를 얻어옵니다 
    var swLatLng = bounds.getSouthWest(); 
    
    // 영역의 북동쪽 좌표를 얻어옵니다 
    var neLatLng = bounds.getNorthEast(); 
    
    // 영역정보를 문자열로 얻어옵니다. ((남,서), (북,동)) 형식입니다
    var boundsStr = bounds.toString();
    
    
    var message = '지도 중심좌표는 위도 ' + center.getLat() + ', <br>';
    message += '경도 ' + center.getLng() + ' 이고 <br>';
    message += '지도 레벨은 ' + level + ' 입니다 <br> <br>';
    message += '지도 타입은 ' + mapTypeId + ' 이고 <br> ';
    message += '지도의 남서쪽 좌표는 ' + swLatLng.getLat() + ', ' + swLatLng.getLng() + ' 이고 <br>';
    message += '북동쪽 좌표는 ' + neLatLng.getLat() + ', ' + neLatLng.getLng() + ' 입니다';
    
    console.log(message);
    
}*/