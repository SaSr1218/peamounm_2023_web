/*

	1. produclistprint(  ) 	: 모든 제품 목록 html 출력 함수  
	2. productprint( )		: productList내 i번째 제품1개 html 출력 함수 
	3. chatprint()			: 채팅창 html 출력 함수 
	
	4. sendchat()			: 채팅창에서 입력된 데이터 [DB]저장하는 함수 
	
	5. getproductlist()		: 기준[동서남북,검색]에 따른 제품목록 요청해서 결과를 받는 함수 / 마커 생성
	6. get동서남북()			: 현재 보고 있는 지도의 좌표 구하기
	
	7. setplike()			: 찜하기 등록 
	8. getplike()			: 찜하기 상태호출 
	
*/

// * 전역변수
let productList = null;	// getproductlist() 의 ajax 로부터 요청된 결과를 담는곳
let index = 0;		// 현재 보고 있는 제품의 제품 인덱스 
let chatmno = 0;	// 현재 채팅하고 있는 상대방의 mno , 채팅대상자

//  1. 모든 제품 목록 html 출력 함수  
function produclistprint(  ){
    let html = `<p style="font-size:12px; text-align:right" > 제품목록수 : ${ productList.length } 개 </h6>`;
    productList.forEach( ( p , i) => {
		console.log(p);
		html += `
			<div onclick="productprint( ${ i } )" class="productbox">
				<div class="pimgbox">
					<img src="/jspweb/product/pimg/${ p.pimglist[0] }">
				</div>
				<div class="pcontentbox">
					<div class="pdate"> ${ p.pdate } </div>
					<div class="pname"> ${ p.pname } </div>
					<div class="pprice"> ${ p.pprice.toLocaleString() } 원 </div>
					<div class="petc">
						<i class="far fa-eye"></i> ${ p.pview }
						<i class="far fa-thumbs-up"></i> 5
						<i class="far fa-comment-dots"></i> 2
					</div>
				</div>
			</div>
			`
	});
	document.querySelector('.produclistbox').innerHTML = html;
} // end 

// 2. 제품 개별 조회 : productList내 i번째 제품1개 html 출력 함수 
function productprint( i ){
	let p = productList[i];
	// 이미지 슬라이드에 대입할 html 구성 
	let imghtml = ``;
	p.pimglist.forEach( (img , i )=>{
		// bs class : active	현재 보여지는 이미지
		if( i == 0 ){	// 첫 이미지에만 actvie 클래스 적용
			imghtml += `<div class="carousel-item active">
				      <img src="/jspweb/product/pimg/${ img }" class="d-block w-100" alt="...">
				    </div>
				    `
		}else{
			imghtml += `<div class="carousel-item">
					      <img src="/jspweb/product/pimg/${ img }" class="d-block w-100" alt="...">
					    </div>
					    `
	    }
	})
	
	let html = ``;
	html += `
			<div class="pviewbox">
				<div class="pviewinfo">
					<div class="mimgbox">
						<img src="/jspweb/member/mimg/${ p.mimg == null ? 'default.webp' : p.mimg }" class="hpimg">
						<span class="mid"> ${ p.mid } </span>
					</div>
					<div>
						<button onclick="produclistprint()" class="pbadge" type="button"> 목록보기 </button>
					</div>
				</div>
				<div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
				  <div class="carousel-inner">
				  
				  	${ imghtml }
				  	
				  </div>
				  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
				    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
				    <span class="visually-hidden">Previous</span>
				  </button>
				  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
				    <span class="carousel-control-next-icon" aria-hidden="true"></span>
				    <span class="visually-hidden">Next</span>
				  </button>
				</div>
					<div class="pdate"> ${ p.pdate } </div>
					<div class="pname"> ${ p.pname } </div>
					<div class="pcomment"> ${ p.pcomment } </div>
					<div class="pstate"> 
						<span class="pbadge">
							${ p.pstate == 1 ? '판매중' : p.pstate == 2 ? '거래중' : '판매완료'  }
						</span> 
					</div>
					<div class="pprice"> ${ p.pprice.toLocaleString() }원 </div>
					<div class="petc"> 
						<i class="far fa-eye"></i> ${ p.pview }
						<i class="far fa-thumbs-up"></i> 5
						<i class="far fa-comment-dots"></i> 2
					 </div>
					<div class="pviewbtnbox">
						<button class="plikebtn" onclick="setplike(${p.pno})"  type="button"> <i class="far fa-heart"></i> </button>
						<button onclick="chatprint( ${i} )" type="button"> 채팅 </button>
					</div>
				</div>
			`	
	document.querySelector('.produclistbox').innerHTML = html;
	getplike( p.pno ); // 찜하기 상태호출 
	
} // end 

// 9. 제품별 채팅 목록 페이지 이동 
function chatlistprint( i ){
	let p = productList[i];
	let html =``;
	$.ajax({
		url : "/jspweb/product/chat" , 
		method : "get" ,
		data : { "pno" : p.pno , "chatmno" : 0 }  ,
		async : false , 
		success : (r) =>{
			let printfrommno =[] // 출력된 mno
			r.forEach( (o) => {
				console.log(o);
				if( !printfrommno.includes( o.frommno ) ){ // 구매자 채팅을 출력한적이 없으면 
					printfrommno.push( o.frommno ); // 구매자번호 저장후  // 구매자별 1번씩 만 출력
					html += `										
							<div onclick="chatinfoprint( ${ i } , ${ o.frommno } )" class="chatlist">
								<div class="frommimg"> <img src="/jspweb/member/mimg/${ o.frommimg == null ? 'default.webp' : o.frommimg }" class="hpimg"> </div>
								<div class="frominfo">
									<div class="fromndate"> ${ o.ndate } </div>
									<div class="frommid"> ${ o.frommid } </div>
									<div class="fromncontent"> ${ o.ncontent } </div>
								</div>
							</div>`
				}
			})	
			// 구매자 번호가 존재하지 않으면
			if( printfrommno.length == 0 ){ html+= `채팅 목록이 없습니다.`}
		}
	}) // ajax end 
	document.querySelector('.produclistbox').innerHTML = html;
}



// 10. 채팅방 내용물 요청해서 해당 html에 출력
function getcontent( ){
	let chathtml = '';
	let pno = productList[index].pno;
	$.ajax({
		url : "/jspweb/product/chat" ,
		method : "get",
		data : { "pno" : pno , "chatmno" : chatmno },
		async : false , /* 동기식 */
		success : (r)=>{
			r.forEach( ( o )=> {
				if( o.frommno == memberInfo.mno ){ // 현재 로그인된 회원과 보낸 사람과 일치하면 
					chathtml += `<div class="sendbox"> ${ o.ncontent }</div>`
				}else { // 메시지 보낸 사람과 채팅 대상자가 일치하면
					chathtml += `<div class="receivebox"> ${ o.ncontent }</div>`
				}
			})	
		}
	}) // end 
	document.querySelector('.chatcontent').innerHTML = chathtml;
}
// 11. 채팅방 html 구성
function chatinfoprint( i , tomno ){
	console.log( tomno + '에게 메시지 전송 페이지 ');
	// 전역변수에 담기 
	index = i ;
	chatmno = tomno;
	
	let p = productList[index];
	
	let html = `
			<div class="chatbox">
				<div class="pviewinfo">
					<div class="mimgbox">
						<img src="/jspweb/product/pimg/${ p.pimglist[0] }" class="hpimg">
						<span class="pname"> ${ p.pname } </span>
					</div>
					<div>
						<button onclick="produclistprint()" class="pbadge" type="button"> 목록보기 </button>
					</div>
				</div>
				
				<div class="chatcontent">
					
				</div>
				<div class="chatbtn">
					<textarea class="ncontentinput" rows="" cols=""></textarea>
					<button onclick="sendchat( ${p.pno} )" type="button">전송</button>
				</div>
			</div>
				`;
	document.querySelector('.produclistbox').innerHTML = html;
	getcontent( );
}
// 3. 채팅 페이지 이동 [ 로그인 검사 , 등록자인지 검사 ]
function chatprint( i ){
	if( memberInfo.mid == null ){ // 로그인 검사
		alert('회원기능 입니다. 로그인후 사용해주세요.'); return;
	}
	let p = productList[i];
	// 만약에 등록한 회원이면  [ 판매자 ]
	if( memberInfo.mno == p.mno  ){ 
		alert('본인이 등록한 제품입니다.');
		chatlistprint(i); // 9.채팅목록으로 이동 
		return;
	}
	// 만약에 등록한 회원이 아니면  [ 구매자 ] 받는사람 : 제품등록자
	chatinfoprint( i , p.mno )
	
} // end 

// 4. 채팅 보내기[ db 처리 ]
function sendchat( pno  ){
	let ncontent = document.querySelector('.ncontentinput').value;
	$.ajax({
		url : "/jspweb/product/chat" ,
		method : "post",
		data : { "pno" : pno , "tomno" : chatmno , "ncontent" : ncontent  } ,
		success : (r)=>{ 
			console.log( r )
			if( r =="true"){
				document.querySelector('.ncontentinput').value = '';
				getcontent(); // 채팅창 목록 새로고침
			}
		}
	})
}

// ------------------------------------ 지도 출력 --------------------------------------
var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
    center : new kakao.maps.LatLng(37.3218778,126.8308848), // 지도의 중심좌표 
    level : 6 // 지도의 확대 레벨 
});
// ------------------------------------- 마커 클러스터러를 생성합니다  -------------------
var clusterer = new kakao.maps.MarkerClusterer({
    map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
    averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
    minLevel: 7 // 클러스터 할 최소 지도 레벨 
});
// $.ajax( { url:"/jspweb/product/info" , success : (r) => { } );

// ----------------------- 마커 이미지 변경 ------------------------------------ //
var imageSrc = '/jspweb/img/전기차.png', // 마커이미지의 주소입니다    
    imageSize = new kakao.maps.Size(40, 40), // 마커이미지의 크기입니다
    imageOption = {offset: new kakao.maps.Point(10, 20)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      
// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
    markerPosition = new kakao.maps.LatLng(37.54699, 127.09598); // 마커가 표시될 위치입니다

// 5. 제품목록 호출 [ 1. 현재 보이는 지도좌표내 포함된 제품만 2. ]
function getproductlist( 동 , 서  , 남 , 북 ){
	clusterer.clear() // 클러스터 비우기 [ 기존 마커들 제거 ]
	$.ajax({
		url : "/jspweb/product/info" ,
		method : "get",
		async : false ,
		data : { "동" : 동 , "서" : 서 ,"남" : 남 , "북":북 },
		success : (r)=>{
		    // ------------ 사이드바 제품목록 --------------------------------
		    productList = r;	// 제품목록 결과를 전역변수 담아주기 
			produclistprint(  );
		   //------------ 마커 작업 ----------------------
		    var markers = r.map( ( p , i ) => {		
				// 마커에 추가코드 작성하기 위해 변수화
		        let marker = new kakao.maps.Marker({	
		            position : new kakao.maps.LatLng( p.plat, p.plng) ,
		            image: markerImage
		        });
		        // 마커에 클릭이벤트를 등록합니다
				kakao.maps.event.addListener(marker, 'click', function() {
				      productprint( i )
				}); // 클릭이벤트 end 
		        return marker;
		    }); // map end 
		    clusterer.addMarkers(markers);   // 클러스터러에 마커들을 추가합니다
		    //-------------------------------------------------
		} // success end 
	}); // ajax end  
} // getproductlist end 

// 6. 현재 지도의 좌표얻기
get동서남북();
function get동서남북(){
	var bounds = map.getBounds();  // 지도의 현재 영역을 얻어옵니다 
    var swLatLng = bounds.getSouthWest();   // 영역의 남서쪽 좌표를 얻어옵니다 
    var neLatLng = bounds.getNorthEast();   // 영역의 북동쪽 좌표를 얻어옵니다 
    let 남 = swLatLng.getLat();
    let 서 = swLatLng.getLng();
    let 북 = neLatLng.getLat();
    let 동 = neLatLng.getLng();
    getproductlist( 동 , 서  , 남 , 북 );
}
// ------------  지도 중심좌표 이동 이벤트 //
// 지도가 이동, 확대, 축소로 인해 중심좌표가 변경되면 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
kakao.maps.event.addListener( map, 'dragend', ()=>{ get동서남북(); });

//7. 찜하기 버튼를 눌렀을때[ 첫 클릭시 찜하기등록 / 다음 클릭시 찜하기 취소 / 다음 클릭시 찜하기 등록 ]
function setplike( pno ){
	// alert(pno);
	if( memberInfo.mid == null ){
		alert('회원기능입니다. 로그인후 사용해주세요'); return;
	}
	
	// 1. pot 방식 전송 
	$.ajax({
		url : "/jspweb/product/like",
		method : "post" ,
		data : { "pno" : pno } , 
		success : (r)=>{ 
			if( r == 'true'){
				alert('찜하기 등록');
				document.querySelector('.plikebtn').innerHTML = '♥';
			}else{
				alert("찜하기 취소")
				document.querySelector('.plikebtn').innerHTML = '♡';
			}
		}
	})
}

// 8. 현재 회원이 해당 제품의 찜하기 상태 호출 
function getplike( pno ){
	if( memberInfo.mid == null ){ document.querySelector('.plikebtn').innerHTML = '♡'; }
	$.ajax({
		url : "/jspweb/product/like",
		method : "get",
		async : 'false',
		data : { "pno" : pno },
		success : (r)=>{ 
			if(r == "true"){ document.querySelector('.plikebtn').innerHTML = '♥'; }
			else{ document.querySelector('.plikebtn').innerHTML = '♡'; }
		 }
	})
}

	 		
	// vs
	// $.get( "/jspweb/product/like?pno="+pno , (r)=>{} )
	// $.ajax({ url : "/jspweb/product/like?pno="+pno , success : (r)=>{ console.log(r); } })
	
	// $.get( "/jspweb/product/like , { "data" : data } , (r)=>{} )
	// $.ajax({ url : "/jspweb/product/like" , data : { "data" : data } , success : (r)=>{ console.log(r); } })
	
	// $.post( "/jspweb/product/like , { "data" : data } , (r)=>{} )
	// $.ajax({ url : "/jspweb/product/like", method : "post" , data : { "data" : data } , success : (r)=>{ console.log(r); } })

	 
        // $(r).map( (인덱스,반복객체명) =>{ } ) 		실행문에서 return 값을 배열에 대입  
        // r.map( (반복객체명,인덱스) =>{ } ) 		실행문에서 return 값을 배열에 대입  
        // vs 
        // .forEach( (반복객체명,인덱스) => { } ) 	실행문에서 return X
	 
	 
	 
	 
	 

/*
$.ajax({
	url : "/jspweb/product/info",
	method : "get" ,
	success : (r)=>{
		console.log(r)
	}
})

// 		VS

$.get( 
	"/jspweb/product/info"  ,
	 (r)=>{ console.log(r) } );
*/

	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 