package Day06;

public class Ex1_참조타입 {
	public static void main(String[] args) {
		
		// 1. 148
		int[] arr1;	//	int형 배열 선언 [ 스택영역에 변수 만들기 ] 
		int[] arr2;	//	int형 배열 선언 [ 스택영역에 변수 만들기 ] 
		int[] arr3;	//	int형 배열 선언 [ 스택영역에 변수 만들기 ] 
		
		arr1 = new int[] { 1, 2, 3 };	//	[ 힙 영역에 '1', '2', '3' 메모리 3개 ]
		arr2 = new int[] { 1, 2, 3 };	//	[ 배열은 첫번째 메모리주소('1')를 스택영역에 대입 ]
		arr3 = arr2;					// arr2가 가지고 있던 메모리주소를 arr3에 대입
		
		System.out.println( arr1 == arr2 );	//	==	스택영역의 데이터//주소 비교
		System.out.println( arr2 == arr3 );	//	
		
		// 2. p. 151
		int[] inArray = null;	//	int형 배열 선언 [ 스택영역 변수 만들기 ]
		// inArray[0] = 10;		// 오류 발생 : 힙 영역이 없기때문에 저장 불가능
		
		String str = null;
		// System.out.println( str.length() );	// 오류 발생 : 힙영역이 없기때문에 길이 불가능
		
		// 3. p.153
		String hobby = "여행";	// 스택영역에 hobby 이름으로 변수 선언하고 "여행" 힙의 메모리 주소 저장
		hobby = null;			// hobby 변수의 null 변경하면 힙에 있던 "여행" 메모리 제거
		
		String kind1 = "자동차";	// "자동차"힙의 메모리 32번지를 스택영역의 kind1 저장
		String kind2 = kind1;	// kind1 가지고 있던 32번지를 kind2에 저장 
		kind1 = null;			// kind1 번지 지우기
		System.out.println("kind2 : " + kind2);
		
		
	}
}
/*
 		스택영역					힙영역
 		arr1	<---주소대입---	new int [] { 1, 2, 3 }
 		arr2	<---주소대입---	new int [] { 1, 2, 3 } --> new를 2번 썼으면 주소가 각기 다르다.(저장 항목은 같지만)
 		arr3
 	
 	* new : 힙영역에 메모리 생성하는 키워드
 	
 	* 스택영역									힙영역[ 별도의 제거 없음 ] 
 	  유재석 : 안산아파트 : 안산시 상록구 65번지		안산아파트 : 안산시 상록구 65번지(쓰레기 취급받아 garbage collector가 자동 제거시킴)	
 	 			이사							수원아파트 : 수원시 100번지(이사 갔을 경우, 안산아파트 주소 사라짐.)
 	  유재석 : 수원시 구 100번지
 	  		   해외 이사
 	  유재석 : null
 	
 */