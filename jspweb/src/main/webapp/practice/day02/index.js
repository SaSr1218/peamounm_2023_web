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
	
	console.log(info);
	
	$.ajax({
		url : "/jspweb/Ex2" ,		// jquery 라이브러리 필수
		method : "post" ,			// 1. 서블릿 주소 [ /프로젝트명/서블릿주소(@WebServlet("/서블릿주소") )]
		data : info , 				// 2. 메소드 방식 [ doGet vs doPost ]
		success : function(result){	// 3. 보낼 데이터 [ 객체 vs { } ] 
			console.log(result);
			if( result == true ){
				alert("등록성공");	
			}else{
				alert("등록실패");
			}
		}
	})
	
}