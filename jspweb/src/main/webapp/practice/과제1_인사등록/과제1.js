// 1. 인사 등록
function create_employee(){
	
	let asd = document.querySelector('.empSdate').value;
	console.log(asd);
	
	let signupForm = document.querySelectorAll('.signupForm')[0];
	let signupFormData = new FormData( signupForm );

	
	$.ajax({
		url : "/jspweb/employee",
		method : "post" ,
		data : signupFormData ,
		contentType : false ,
		processData : false ,
		success : (r) => {
			if ( r == 'true' ){
				alert('회원가입 성공!')
				print_employee();
			}
			
			
		}
	}) // ajax end
} // create_employee end

// 2. 인사 출력
print_employee();
function print_employee(){
	$.ajax({
		url : "/jspweb/employee",
		method : "get" ,
		success : (r) => {
			console.log('통신성공'); console.log(r);
			
			let html = `<tr>
							<th width=10%> 사원번호 </th>
							<th width=10%> 사원사진 </th>
							<th width=10%> 사원명 </th>
							<th width=10%> 직급 </th>
							<th width=10%> 고용형태 </th>
							<th width=10%> 부서 </th>
							<th width=10%> 입사일 </th>
							<th width=10%> 퇴사일 </th>
							<th width=10%> 퇴사사유 </th>
							<th width=10%> 비고 </th>
						</tr>`
			r.forEach( ( o , i ) =>{
				html += `<tr>
							<td> ${o.empNo} </td>
							<td> ${o.empImg} </td>
							<td> ${o.empName} </td>
							<td> ${o.empGrade} </td>
							<td> ${o.empConstruct} </td>
							<td> ${o.empDepart} </td>
							<td> ${o.empSdate} </td>
							<td> ${o.empLdate} </td>
							<td> ${o.empLcomment} </td>
							<td> 삭제/수정 </td>
						</tr>`
			}); // for end
			document.querySelector('.empList').innerHTML = html;
		}
	})
}






