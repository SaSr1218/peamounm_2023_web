let pay = 0; // 결제 금액

function setpay(p){
	pay = p;
	alert('결제금액 선택') + p;
}


// 2. 객체 초기화 하기 (1번만 실행하게 하기)
  var imp = window.IMP; // 생략 가능
  imp.init("imp83184621"); // 예: imp00000000a

// 3. 결제 요청하기  
function requestPay2() {
	if ( pay == 0 ){
		alert('충전할 금액을 선택해주세요.'); return;
	}
    imp.request_pay({
      pg: "kcp.INIBillTst",				// 결제 연동 창 PG상점아이디 ( MID ) , or git 주소
      pay_method: "card",
      merchant_uid: "ORD20180131-0000011",   // 주문번호 // 결제 DB에서 사용할 PK 값
      name: "텀블러 결제",
      amount: pay,                         // 숫자 타입
      buyer_email: "gildong@gmail.com",
      buyer_name: "홍길동",
      buyer_tel: "010-4242-4242",
      buyer_addr: "서울특별시 강남구 신사동",
      buyer_postcode: "01181"
    }, function (rsp) { // callback
      if (rsp.success) {
        console.log(rsp)
      } else {
        
        // 결제 성공했다는 가정
        let info = {
			mpcontent : '포인트 충전' ,
			mpamount : pay ,
			mno : memberInfo.mno
		}
        $.ajax({
			url : "/jspweb/point" ,
			data : info ,
			method : "post" ,
			success:(r) => {
				console.log(r);
				if ( r=='true' ){alert('충전성공')}
			}
		})
 
      }
    });
  }

 // ------------------------------------------------------------
 /* 개인정보 인증 -> 사업자 번호를 넣어야 실행 가능
 IMP.certification({
    merchant_uid : 'merchant_' + new Date().getTime() //본인인증과 연관된 가맹점 내부 주문번호가 있다면 넘겨주세요
}, function(rsp) {
    if ( rsp.success ) {
    	 // 인증성공
        console.log(rsp.imp_uid);
        console.log(rsp.merchant_uid);
        
        $.ajax({
				type : 'POST',
				url : '/certifications/confirm',
				dataType : 'json',
				data : {
					imp_uid : rsp.imp_uid
				}
		 }).done(function(rsp) {
		 		// 이후 Business Logic 처리하시면 됩니다.
		 });
        	
    } else {
    	 // 인증취소 또는 인증실패
        var msg = '인증에 실패하였습니다.';
        msg += '에러내용 : ' + rsp.error_msg;

        alert(msg);
    }
});
 */
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  