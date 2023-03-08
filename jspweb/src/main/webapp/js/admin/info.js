
// 2. 회원호출
getMemberList();
function getMemberList(){
	$.ajax({
		url : "/jspweb/member" ,
		method : "get" ,
		success : (r) => {
			console.log('통신');
			console.log(r);
			
			let html = ` <table border="1">
							<tr>
								<th> 회원번호 </th>
								<th> 프로필 </th>
								<th> 아이디 </th>
								<th> 이메일주소 </th>
								<th> 비고 </th>
							</tr>`
				r.forEach( ( o , i ) => {
					html += `
							<tr>
								<th> ${o.mno} </th>
								<th> ${o.mimg} </th>
								<th> ${o.mid} </th>
								<th> ${o.memail} </th>
								<th> </th>
							</tr>
							`
				})
				html += `</table>`
				document.querySelector('.mListTable').innerHTML = html;
		}
	})
}