
let productList = [
	{ img : 'p1.gif' ,  title : '소느아 숄카라 퍼장식 롱 패딩 코트' , size : '[ M, L ]' , price : 189000 , discount : 0.23 , like : 54 , review : 412 } ,
	{ img : 'p2.jpg' , title : '리복 패딩' , size : '100' , price : 85000 , discount : 0.2 , like : 65 , review : 15 } ,
	{ img : 'p3.jpg' , title : '나이키 검정 운동화' , size : '270' , price : 150000 , discount : 0.25 , like : 89 , review : 425 } ,
	{ img : 'p4.jpg' , title : '퓨마 운동복' , size : '100' , price : 60000 , discount : 0.30 , like : 23 , review : 124 } ,
	{ img : 'p5.jpg' , title : '갈색 내복' , size : '95' , price : 25000 , discount : 0.33 , like : 15 , review : 35 } ,
	{ img : 'p6.jpg' , title : '소니 헤드셋' , size : '50' , price : 335000 , discount : 0.2 , like : 30 , review : 724 } 
]

product_print();
// 1. 제품 출력 // 1. js 열릴때
function product_print(){
	let html = ``
	productList.forEach( ( o , i ) => {
		
	 html += `
			<div class="item"> 					<!-- 제품 1개 -->
				<img src="img/${ o.img }"><!-- 제품이미지 -->
				<div class="item_info"><!-- 제품정보 구역 -->
					<div class="item_title">${ o.title }</div>	<!-- 제품명 -->
					<div class="item_size">${ o.size }</div>	<!-- 제품사이즈 -->
					<div class="item_price">${ o.price.toLocaleString() } 원</div>	<!-- 원가 -->
					<div>
						<span class="item_sale">${ ( o.price - (o.price*o.discount) ) .toLocaleString() } 원</span>	<!-- 판매가 -->
						<span class="item_discount">${ o.discount*100 }%</span>	<!-- 할인율 -->
					</div>
				</div>
				<div class="item_bottom">	<!-- 제품정보 하단 구역  -->
					<div>
						<span class="badge rounded-pill text-bg-warning">주문폭주</span> <!-- 배지 -->
						<span class="badge rounded-pill text-bg-danger">1+1</span>
					</div>
					<div class="item_review"> 찜 ${ o.like } 리뷰수 ${ o.review }</div>	
				</div>
			</div>`
		})
	document.querySelector('.itembox').innerHTML = html
}