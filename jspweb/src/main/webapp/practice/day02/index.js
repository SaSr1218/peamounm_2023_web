function ex1(){
	
	let info = {
	data1 : document.querySelector('.data1').value ,
	data2 : document.querySelector('.data2').value ,
	data3 : document.querySelector('.data3').value ,
	data4 : document.querySelector('.data4').value ,
	data5 : document.querySelector('.data5').value ,
	data6 : document.querySelector('.data6').value ,
	data7 : document.querySelector('input[name="data7"]:checked').value ,	// radio에서 선택된 버튼의 value
	data8 : document.querySelector('.data8').checked,					// 체크 여부[true/false]
	data9 : document.querySelector('.data9').value,
	data10 : document.querySelector('.data10').value
	}
	
	// console.log(info);
	
	$.ajax({						// jquery 라이브러리 필수
		url : "/jspweb/Ex2" ,		// 1. 서블릿 주소 [ /프로젝트명/서블릿주소(@WebServlet("/서블릿주소") )]
		method : "post" ,			// 2. 메소드 방식 [ doGet vs doPost ]
		data : info , 				// 3. 보낼 데이터 [ 객체 vs { } ] 
		success : function(result){	
			// console.log(result);
			if( result == true ){
				alert("등록성공"); getData();	
			}else{
				alert("등록실패");
			}
		}
	})
	
}

getData();
function getData() {
	$.ajax({
		url : "/jspweb/Ex2" ,
		method : "get" ,
		success : function(result) {
			//console.log(result);
			//document.querySelector('.ex1_box').innerHTML = result;
			
			let html = `<table border="1">
							<tr>
								<th> data1 </th> <th> data2 </th> <th> data3  </th> <th> data4 </th>
								<th> data5 </th> <th> data6 </th> <th> data7  </th> <th> data8 </th>
								<th> data9 </th> <th> data10 </th>
							</tr>
						`
			result.forEach( ( o , i ) =>  { // 객체명.forEach( (반복변수 , 반복인덱스) => { 실행문 } )
				// console.log( i )
				// console.log( o );
			
				html += `
							<tr>
								<th> ${ o.data1 } </th> <th> ${ o.data2 } </th> <th> ${ o.data3 } </th>
								<th> ${ o.data4 } </th> <th> ${ o.data5 } </th> <th> ${ o.data6 } </th> 
								<th> ${ o.data7 } </th> <th> ${ o.data8 } </th> <th> ${ o.data9 } </th> <th> ${ o.data10 } </th>
							</tr>
						`
			})
			html += `</table>`
			document.querySelector('.ex1_box').innerHTML = html;
		
		}
	})
}

function 과제2(){
	let info2 = {
		ex2_1 : document.querySelector('.ex2_1').value,
		ex2_2 : document.querySelector('.ex2_2').value,
		ex2_3 : document.querySelector('.ex2_3').value,
		ex2_4 : document.querySelector('.ex2_4').value,
		ex2_5 : document.querySelector('.ex2_5').value,
		ex2_6 : document.querySelector('input[name="ex2_6"]:checked').value,
		ex2_7 : document.querySelector('.ex2_7').checked,
		ex2_8 : document.querySelector('.ex2_8').value,
		ex2_9 : document.querySelector('.ex2_9').value
	}
	
	 console.log( info2 ); // html -> js 값 전달 확인
	
	$.ajax({
		url : "/jspweb/Q2" ,
		method : "post" ,
		data : info2 ,
		success : function(result2){
			console.log( result2 );
			if( result2){
				alert("등록성공22"); 과제2_2(); 
			}else{
				alert("등록실패22")
			}
		}
		
	})
	
}

과제2_2();
function 과제2_2(){
	$.ajax({
		url : "/jspweb/Q2" ,
		method : "get" ,
		success : function(result2){
			let html = `<table border="1">
							<tr>
								<th> 이름 </th> <th> 전화번호 </th> <th> 키  </th> <th> 나이 </th>
								<th> 등록일 </th> <th> 성별 </th> <th> 개인정보동의  </th> <th> 지역 </th>
								<th> 자기소개 </th> 
							</tr>
						`
			result2.forEach( ( o , i ) =>  { // 객체명.forEach( (반복변수 , 반복인덱스) => { 실행문 } )
				// console.log( i )
				// console.log( o );
			
				html += `
							<tr>
								<th> ${ o.ex2_name } </th> <th> ${ o.ex2_phone } </th> <th> ${ o.ex2_phone } </th>
								<th> ${ o.ex2_age } </th> <th> ${ o.ex2_date } </th> <th> ${ o.ex2_sex } </th> 
								<th> ${ o.ex2_ok } </th> <th> ${ o.ex2_live } </th> <th> ${ o.ex2_info } </th>
							</tr>
						`
			})
			html += `</table>`
			document.querySelector('.q2_box').innerHTML = html;			
		}
	})
}














