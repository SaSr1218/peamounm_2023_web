// 1. userbox / logbox / monbox  Dom객체 가져오기
let userbox = document.querySelector('.userbox');
let monbox = document.querySelector('.monbox');

let logbox = document.querySelector('.logbox');
let logbox2 = document.querySelector('.logbox2');
let gameover = document.querySelector('.gameover');

let m_hpbox = document.querySelector('.m_hp');
let u_hpbox = document.querySelector('.u_hp');

// * userbox [기본/처음] 위치/좌표
let u_left = 10;	// 유저 
let m_left = 910;	// 몬스터 
let m_hp = 300;		//	몬스터 hp
let u_hp = 300;		// 유저 hp

// 캐릭터 소개
let 캐릭터 = {
	
}

// 몬스터 배열
let 몬스터 = 	[
	{ m_img : '파랑버섯.gif' , hp : 400 , left : 910} ,
	{ m_img : '좀비버섯.gif' , hp : 500 , left : 910} ,
	{ m_img : '주황버섯3.gif' , hp : 600 , left : 910} 
	]
console.log(몬스터)
// 2. 문서 안에서 키 입력 이벤트  keydown
document.addEventListener( 'keydown' , (e)=>{
	let key = e.keyCode;	// 입력된 키 코드를 변수에 저장 
	let u_attack = parseInt(Math.random()*20+1); // 캐릭 공격력 난수 +-20
	let m_attack = parseInt(Math.random()*10+1); // 몬스터 공격력 난수 +-10
	
	// 이동 관련
	if( key == 37 ){ // 왼쪽키 -> 이동 
		u_left -= 10; 	// 왼쪽 좌표 -10 차감 
		u_left = u_left < 0 ? 0 : u_left ;  // 만약에 차감된 왼쪽 좌표가 0보다 작으면 0 아니면 적용
	}else if( key == 39 ){ // 오른쪽키 -> 이동 
		u_left += 10
		u_left = u_left > 910 ? 910 : u_left ;
		userbox.style.backgroundImage = `url(img/캐릭터2_이동.png)` // 이동 모션 
		userbox.style.backgroundSize = `110%`;
	}else if( key == 65 && (u_left-m_left) >= -50 && (u_left-m_left) <= 50 ){ // a키 -> 공격 
		userbox.style.backgroundImage = `url(img/캐릭터3_공격.png)` // 공격 모션
		m_hp = m_hp - u_attack
		m_hpbox.style.width = `${ m_hp }px`
	}
	
	//유저 관련
	if( key == 83 ){ // 유저 방어
		userbox.style.backgroundImage = `url(img/캐릭터4_방어.png)`
		u_hp = u_hp
		u_hpbox.style.width = `${ u_hp }px`
	}else if((u_left-m_left) >= -20 && (u_left-m_left) <= 20 ){ // 유저 방어
		u_hp = u_hp - m_attack
		u_hpbox.style.width = `${ u_hp }px`
	}
		
	// 유저, 몬스터 hp 콘솔 표시
	console.log('유저 hp :' + u_hp)
	console.log('몬스터 hp :' + m_hp)

	if( u_hp < 0){ // 캐릭터 HP 0되면 게임 오버
		userbox.style.display = 'none'
		alert('캐릭터가 사망했습니다.')
		gameover.innerHTML = '<div>' +  '게임 여부 : Game Over'  + '</div>'
	}
	// 몬스터 추가 ????
	if ( m_hp < 0){ // 1번째 몬스터 주황버섯 HP 0되면 다음 몬스터 등장
		gameover.innerHTML = '<div>' +  '게임 여부 : 2단계 몬스터'  + '</div>' 
		
	}

	userbox.style.left = `${ u_left }px`	// * 키 입력후에 css : left 변경 
	// * 현재 좌표를 로그에 출력 
	logbox.innerHTML = `<div> 캐릭터 좌표 : ${ u_left }</div>`
})
// 2. 문서 안에서 키 떼였을때 이벤트  keyup 
document.addEventListener( 'keyup' , (e)=>{ 
	userbox.style.backgroundImage = `url(img/캐릭터1.png)`
	userbox.style.backgroundSize = `90%`;
})

// 3. 몬스터 이동 난수[랜덤 -> 1초]
	// 특정 시간마다 함수 실행해주는 함수 : setInterval( ()=>{} , 밀리초(1000/1초) )
setInterval( mon_moving , 1000 );
function mon_moving(){ 
	// 1. 난수 +-10
	let rand = parseInt( Math.random()*100+1); // 1~100	// 이동거리
	let rand2 = parseInt( Math.random()*2 ); // 0 또는 1  // 이동방향
	if( rand2 == 1 ) m_left += rand
	else m_left -= rand
	// 2. 게임 화면 고정 
	if( m_left < 0 ) m_left = 0;
	if( m_left > 910 ) m_left = 910;
	// 3. 몬스터 이동
	monbox.style.left = `${ m_left }px`
	// * 현재 좌표를 로그에 출력 
	logbox2.innerHTML = `<div> 몹 좌표 : ${ m_left }</div>`
}

console.log(monbox)

/* 잘 모르겠는 부분 : 몬스터를 배열로 만들어서 호출하기

let new = ''
for ( let i = 0 ; i<몬스터.length; i++ ){
	if( m_hp < 0){ // hp가 0이 되면 호출
		new =  `<div class="monbox"><img src="img/${ 몬스터[i].m_img  }"</div> />
				<div class="m_hp">${몬스터[i].hp}</div>`
				}
	mon_moving(); // 움직임 다시 호출??
}
*/




/*
	함수 형태 
		1.일반함수 : function 함수명(){ }
		2.익명함수 : function(){ }
		3.람다식함수: ( ) => { }
		4.변수함수 : let 변수명 = () => { }
	Math.random()
		Math.random() : 0~1 사이의 실수 
		Math.random()*10 : 0~10 사이의 실수
*/












