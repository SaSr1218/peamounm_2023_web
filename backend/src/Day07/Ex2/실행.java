// 실행(main함수)

package Day07.Ex2;

public class 실행 {
	public static void main(String[] args) {
		
		// 1. 클래스를 이용한 객체 생성
		Car myCar = new Car();		// 32번지 메모리 생성
		// 클래스명 변수명 = new 생성자명();
		
		// 2. 객체의 필드 호출 [ .접근연산자]
		System.out.println("모델명 : " + myCar.model);
		System.out.println("시동여부 : " + myCar.start);
		System.out.println("현재속도 : " + myCar.speed);
		
		// 3. 
		Car yourCar = new Car();	// 33번지 메모리 생성 -> 힙 주소가 다름!!!
		yourCar.model = "현대자동차";
		
		System.out.println("모델명 : " + myCar.model);	// null
		System.out.println("모델명 : " + yourCar.model);	// 현대자동차
				
	}
}
