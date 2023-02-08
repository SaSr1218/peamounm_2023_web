package Day06;

public class Ex2_문자열 {
	public static void main(String[] args) {
		
		// 1. 자바 문자열을 사용하는 방법
			// 1. 배열
		char[] 문자열1 = new char[] {'안', '녕' };
			// 2. 클래스 [ 객체 ]
		String 문자열2 = new String( "안녕" );
			// 3. 문자리터럴 " "
		String 문자열3 = "안녕";
		
		// 2. p.156
		String strVar1 = "홍길동";	// 10번지
		String strVar2 = "홍길동";	// 10번지
		
			// 1. 주소 비교
		if( strVar1 == strVar2 ) {
			// 만약에 두 주소가 같으면
			System.out.println(" 참조[주소] 같음 ");
		}else {
			System.out.println(" 참조[주소] 다름 ");
		}
		
			// 2. 주소 안에 있는 데이터 비교
		if( strVar1.equals(strVar2) ) {
			// 만약에 두 변수가 가지고 있는 주소의 데이터가 같으면
			System.out.println(" 참조[주소] 내부도 같음");
		}
		
		String strVar3 = new String("홍길동");	// 20번지
		String strVar4 = new String("홍길동");	// 30번지
		if( strVar3 == strVar4 ) // 만약에 두 주소가 같으면
			  {	System.out.println(" 참조[주소] 같음 ");
		}else { System.out.println(" 참조[주소] 다름 "); }
		
		if ( strVar3.equals(strVar4) ) { System.out.println(" 참조[주소] 내부도 같음"); }
		
		// p.157
		String hobby = "";	// vs null        --> "" 공백과 null은 완전 다르다!
		if( hobby.equals("") ) { System.out.println(" \"\"의 객체 "); }
		
		String hobby2 = null;
		// 오류!! if (hobby2.equals(null)) { System.out.println(" null 객체"); }
		
		
		// --------------------------------------------------------------------- //
		// 문자열 관련된 api함수
		
		// 1. 158p	.charAt( 인덱스 )
		String ssn = "950624123012";
		char sex = ssn.charAt(6); System.out.println( sex );
		if ( sex == '1' || sex == '3' ) { System.out.println("남자"); }
		else { System.out.println("여자");}
		
		// 2. 159p	.length()
		int length = ssn.length(); // 길이: 13 , 인덱스 : 0 ~ 12
		if ( length == 13 ) { System.out.println("주민등록번호 자릿수 맞음"); }
		else { System.out.println("주민등록번호 자릿수 틀림"); }
		
		// 3. 160p	
		String oldStr = "자바 문자열 불변입니다. 자바 문자열은 String입니다.";
		String newStr = oldStr.replace("자바", "JAVA"); // 변경된 문자열 반환
		System.out.println( oldStr );
		System.out.println( newStr );
		
		// 4. 161p subString( 인덱스 ) --> 고정된 문자 vs split ( 기준문자 ) --> 문자 패턴
		String ssn2 = "880815-1234567";
		System.out.println( ssn2.substring(7)); // 7 인덱스 후부터 보여주기 (자르기)
		System.out.println( ssn2.substring(0 , 6)); // 0~6 인덱스 전까지 보여주기 (자르기)
		
		System.out.println( ssn2.split("-")); // "-" 자르면 2조각
		System.out.println( ssn2.split("-")[0]); // "-" 자르면 2조각/인덱스 배열 반환
		System.out.println( ssn2.split("-")[1]); // "-" 자르면 2조각/이넫ㄱ스 배열 반환
		
		String board = "1,자바 학습,참조 타입 String을 학습합니다. ,홍길동";
		String[] tokens = board.split(","); // , 기준으로 분리했을때 4조각/인덱스 --> 배열 반환
		System.out.println(tokens[0]); 		// 1
		System.out.println(tokens[1]); 		// 자바 학습
		System.out.println(tokens[2]); 		// 참조 타입 String을 학습합니다.
		System.out.println(tokens[3]); 		// 홍길동
		
		// 5. 163p indexOf( 찾을문자 ) vs contains( 찾을문자 )
		String subject = "자바 프로그래밍";	// 띄어쓰기도 문자
		System.out.println( subject.indexOf("자바"));		// 찾으면 0~ 인덱스
		System.out.println( subject.indexOf("파이썬"));		// 없으면 -1
		
		System.out.println( subject.contains("자바"));	// 찾으면 true
		System.out.println( subject.contains("파이썬"));	// 찾으면 false
		
		
	}
}

/*
 	
 	' ' : 문자	char	기본타입
 	" " : 문자열	String	참조타입
 	
 	자바 문자열을 사용하는 방법
 		1. 배열 , 클래스 , 문자리터럴
 		2. 클래스
 				String 문자열3 = new String("안녕");	//10번지
 				String 문자열4 = new String("안녕");	// 20번지
 					문자열3 == 문자열4		  	 [ X ]
 					문자열3.equals( 문자열4 ) 	 [ O ]                 equals ---> 참조[주소] 내부도(문자열, 내용 등등) 같을때 판단!!
 		3. 문자리터럴
 			- 문자리터럴이 동일한 경우 객체 공유 [ 주소가 같다 == ]
 				String 문자열3 = "안녕";	// 10번지
 				String 문자열4 = "안녕";	// 10번지
  					문자열3 == 문자열4		  	 [ O ]
 					문자열3.equals( 문자열4 ) 	 [ O ]					
 		
 			
 		자바 문자열의 함수들
 			1. .charAt(인덱스);							: 인덱스 위치의 문자 1개 추출
 			2. .length();								: 문자열의 길이
 			3. .replace("기존문자" , "새로운문자");			: 기존문자를 새로운문자로 치환/교체
 			4. .subString(인덱스);						: 0 ~ 인덱스 자르기
 				.subString( 시작인덱스, 끝인덱스 );			: 시작인덱스 ~ 끝인덱스 자르기
  			5. .split("자를기준");							: 기준문자 기준으로 자르기
 			6. .indexOf("찾을문자");						: 찾을문자의 찾은 인덱스 번호 반환
			7. .contains("찾을문자");						: 찾을문자가 존재하면 true / false
 
 */













