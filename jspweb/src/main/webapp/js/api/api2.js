// ------------------- 테스트 객체 ------------------- //
/*var positions = [
    {
        title: '카카오', 
        latlng: new kakao.maps.LatLng(33.450705, 126.570677)
    },
    {
        title: '생태연못', 
        latlng: new kakao.maps.LatLng(33.450936, 126.569477)
    },
    {
        title: '텃밭', 
        latlng: new kakao.maps.LatLng(33.450879, 126.569940)
    },
    {
        title: '근린공원',
        latlng: new kakao.maps.LatLng(33.451393, 126.570738)
    }
];*/

// ------------------- 전기 충전소 객체 -------------------- //

// let positions = [];

// ajax 축소버전
// $.get( {"url" , (r) => { }} );
// $.post( {"url" , (r) => { }} );
// $.put( {"url" , (r) => { }} );
// $.delete( {"url" , (r) => { }} );

/*$.ajax({
	url : "https://api.odcloud.kr/api/15090398/v1/uddi:6fe0e3f2-0285-4999-9edf-995afe19a6ea?page=1&perPage=96&serviceKey=z427Q0DLkQqM0SDOc1Lz8jPzk%2BKj0ng%2Bvz7h3I8CpVs3T90219bWi2o%2BmStIxJW%2B9lwayA%2FsAT6apxsxuvydQg%3D%3D" ,
	method : "get" ,
	async : 'false' ,
	success : (r) => { // 공공데이터 데이터 요청 결과
	
		console.log(r);	
		r.data.forEach( (o) => { // 결과 내 리스트 객체 반복
			let info = { // 마커를 표시할 객체 생성
				title : o.충전소명 ,
				// 필드명 특수문자 있는경우 : 객체명['필드명']
				latlng : new kakao.maps.LatLng( o['위도(WGS84)'] , o['경도(WGS84)'] )
			}
			console.log( info )
			positions.push( info ) // 생성된 객체를 리스트[배열]에 담는다.
			console.log ( positions )
		}) // for end
		
		// ------------------- 여러 개 마커 생성 -------------------
		for( let i = 0; i<positions.length ; i++ ){
			
			var marker = new kakao.maps.Marker({
		        map: map, // 마커를 표시할 지도
		        position: positions[i].latlng, // 마커를 표시할 위치
		        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
		        image : markerImage // 마커 이미지 
		    });
		    // ----------------------- 마커 클릭 이벤트  ------------------------------------ //  
			// 마커에 클릭이벤트를 등록합니다
			kakao.maps.event.addListener(marker, 'click', function() {
			      alert( positions[i].title + ' 의 마커 클릭했습니다.' ); 
			}); 
			
		} // for end 
	}
})*/

// ------------------- 지도 옵션 및 생성 ------------------- //
var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스

var options = { //지도를 생성할 때 필요한 기본 옵션
	center: new kakao.maps.LatLng(37.3218778, 126.8308848), //지도의 중심좌표.
	level: 5 //지도의 레벨(확대, 축소 정도)
};

var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴


// ------------------- 지도 클릭 이벤트 ------------------- //

// 지도에 클릭 이벤트를 등록합니다
// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
    
    // 클릭한 위도, 경도 정보를 가져옵니다 
    var latlng = mouseEvent.latLng; 
    
    // 마커 위치를 클릭한 위치로 옮깁니다
    marker.setPosition(latlng);
    
    var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
    message += '경도는 ' + latlng.getLng() + ' 입니다';
    
    var resultDiv = document.getElementById('clickLatlng'); 
    resultDiv.innerHTML = message;
    
});

// ------------------- 마커 이미지 바꿔서 생성 ------------------- //
				//  [ 마커 생성 전 실행해야 함]

var imageSrc = '/jspweb/img/전기차.png', // 마커이미지의 주소입니다    
    imageSize = new kakao.maps.Size(40, 40), // 마커이미지의 크기입니다
    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      
// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
    markerPosition = new kakao.maps.LatLng(37.54699, 127.09598); // 마커가 표시될 위치입니다
   

// ------------------- 마커 1개 생성 ------------------- //

// 마커를 생성합니다
var marker = new kakao.maps.Marker({ 
    // 지도 중심좌표에 마커를 생성합니다 
    position: map.getCenter() ,
    image: markerImage	// 해당 마커객체의 이미지객체 대입 
}); 
// 지도에 마커를 표시합니다
marker.setMap(map);

// ------------------- 마커 클러스트[ 마커 관리 ] ------------------- // -> 객체가 필요 X
    // 마커 클러스터러를 생성합니다 
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
        minLevel: 6 // 클러스터 할 최소 지도 레벨 
    });

	/*
		.forEach ( (o)=> { } )	: 실행문에서 반환이 없다.		return X
		.map( (o) => { } )		: 실행문에서 반환이 있다.		return O 반복문에서 리턴된 모든 값을 배열제 저장
	 */

    // 데이터를 가져오기 위해 jQuery를 사용합니다
    // 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
    $.get(
		// 공공데이터 url
		"https://api.odcloud.kr/api/15090398/v1/uddi:6fe0e3f2-0285-4999-9edf-995afe19a6ea?page=1&perPage=96&serviceKey=z427Q0DLkQqM0SDOc1Lz8jPzk%2BKj0ng%2Bvz7h3I8CpVs3T90219bWi2o%2BmStIxJW%2B9lwayA%2FsAT6apxsxuvydQg%3D%3D",
	     function(r) {
			 console.log ( r )
	        // 데이터에서 좌표 값을 가지고 마커를 표시합니다
	        // 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
	        var markers = $(r.data).map(function(i, o) {
	            return new kakao.maps.Marker({
	                position : new kakao.maps.LatLng( o['위도(WGS84)'], o['경도(WGS84)']) ,
	                image: markerImage
	            });
	        });
	
	        // 클러스터러에 마커들을 추가합니다
	        clusterer.addMarkers(markers);
	    });




    

